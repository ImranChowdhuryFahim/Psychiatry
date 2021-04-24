package com.brainfluence.psychiatry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.brainfluence.psychiatry.model.StudentModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity {
    private TextView reg;
    private Button login;
    private TextInputEditText passInput,emailInput;
    private TextInputLayout password,email,accountType;
    private ArrayList<String> accountTypeList;
    private ArrayAdapter<String> accountTypeAdapter;
    private AutoCompleteTextView accountTypeInput;
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference,databaseReferenceWrite;
    private ProgressDialog progressDialog;
    private String token;
    private SharedPreferences sharedPref;
    private boolean flag;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String IS_LOGGED_IN = "isLoggedIn";
    public static final String ACCOUNT_TYPE = "accountType";
    public static final String UID = "uid";
    public static final String EMAIL = "email";
    public static final String USER_NAME = "userName";
    public static final String INFO_ADDED1    = "infoAdded";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        reg = findViewById(R.id.reg);
        accountTypeInput = findViewById(R.id.accountTypeInput);
        login = findViewById(R.id.login);
        email = findViewById(R.id.email);
        emailInput = findViewById(R.id.emailInput);
        password = findViewById(R.id.password);
        passInput = findViewById(R.id.passInput);
        accountType = findViewById(R.id.accountType);

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("");
        sharedPref = getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE);

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("Token", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        token = task.getResult();

                        Log.d("Token", token);
//                        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });

        accountTypeList = new ArrayList<String>();
        accountTypeList.add("Teacher");
        accountTypeList.add("Student");
        accountTypeList.add("Doctor");

        accountTypeAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.uni_name_list,accountTypeList);
        accountTypeInput.setAdapter(accountTypeAdapter);
        accountTypeInput.setThreshold(1);


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkConnectivity()){
                } else {
                    nointernetp();
                    return;
                }

                if(!validateEmail()  |  !validatePass() | !validateAccountType())
                {
                    return;
                }

                progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setMessage("Please wait..."); // Setting Message
                progressDialog.setTitle("Validating"); // Setting Title
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
                progressDialog.show(); // Display Progress Dialog
                progressDialog.setCancelable(false);


                mAuth.signInWithEmailAndPassword(emailInput.getText().toString().trim(), passInput.getText().toString().trim())
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    FirebaseUser user = mAuth.getCurrentUser();
                                    String child = null;

                                    if(accountTypeInput.getText().toString().trim().equals("Teacher"))
                                    {
                                        child = "teachers";
                                    }
                                    if(accountTypeInput.getText().toString().trim().equals("Student"))
                                    {
                                        child = "students";
                                    }
                                    if(accountTypeInput.getText().toString().trim().equals("Doctor"))
                                    {
                                        child = "doctors";
                                    }



                                        String finalChild = child;
                                    final Boolean[] showed = {false};
                                        databaseReference.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if (snapshot.hasChild(finalChild))
                                                {


                                                    DataSnapshot snapshot1 = snapshot.child(finalChild);
                                                    if(snapshot.child(finalChild).hasChild(user.getUid()))
                                                    {

                                                        showed[0] = true;

                                                        databaseReferenceWrite = firebaseDatabase.getReference(finalChild).child(user.getUid());
                                                        databaseReferenceWrite.child("fToken").setValue(token);

                                                        SharedPreferences.Editor editor = sharedPref.edit();
                                                        editor.putBoolean(IS_LOGGED_IN, true);
                                                        editor.putString(ACCOUNT_TYPE,finalChild);
                                                        editor.putString(EMAIL,snapshot1.child(user.getUid()).child("email").getValue().toString().trim());
                                                        editor.putString(UID,user.getUid());
                                                        editor.putString(USER_NAME,snapshot1.child(user.getUid()).child("name").getValue().toString().trim());
                                                        if(finalChild.equals("students"))
                                                        {
                                                            editor.putString(INFO_ADDED1, snapshot1.child(user.getUid()).child("infoAdded").getValue().toString());
                                                        }
                                                        else {
                                                            editor.putString(INFO_ADDED1,"false");
                                                        }
                                                        editor.apply();
                                                        progressDialog.dismiss();

                                                        if(finalChild.equals("students"))
                                                        {
                                                            if(snapshot1.child(user.getUid()).child("infoAdded").getValue().toString().equals("false"))
                                                            {
                                                                startActivity(new Intent(LoginActivity.this,QuestionnaireActivity.class));
                                                                finish();
                                                            }
                                                            else {
                                                                startActivity(new Intent(LoginActivity.this,Home.class));
                                                                finish();
                                                            }
                                                        }
                                                        else {
                                                            startActivity(new Intent(LoginActivity.this,Home.class));
                                                            finish();
                                                        }



                                                    }
                                                    else  {
                                                        if(progressDialog!=null && showed[0]!=true){
                                                            progressDialog.dismiss();
                                                            Toasty.error(LoginActivity.this,"Login Failed. No such Account.",Toasty.LENGTH_SHORT).show();
                                                        }

                                                    }
                                                }
                                                else {

                                                    progressDialog.dismiss();
                                                    AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
                                                    builder.setCancelable(true);
                                                    builder.setIcon(R.drawable.ic_baseline_info_24);
                                                    builder.setTitle("Login Failed");
                                                    builder.setMessage("No such account.");
                                                    builder.setInverseBackgroundForced(true);
                                                    builder.setPositiveButton("Close",new DialogInterface.OnClickListener(){

                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which){
                                                            dialog.dismiss();
                                                        }
                                                    });

                                                    AlertDialog alert=builder.create();
                                                    alert.show();


                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });




                                } else {

                                    try
                                    {
                                        throw task.getException();
                                    }
                                    // if user enters wrong password.
                                    catch (FirebaseAuthInvalidCredentialsException malformedEmail)
                                    {
                                        progressDialog.dismiss();
                                        AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
                                        builder.setCancelable(true);
                                        builder.setIcon(R.drawable.ic_baseline_info_24);
                                        builder.setTitle("Validation Error");
                                        builder.setMessage("Email and password do not match");
                                        builder.setInverseBackgroundForced(true);
                                        builder.setPositiveButton("Close",new DialogInterface.OnClickListener(){

                                            @Override
                                            public void onClick(DialogInterface dialog, int which){
                                                dialog.dismiss();
                                            }
                                        });

                                        AlertDialog alert=builder.create();
                                        alert.show();
                                    }

                                    catch (Exception e)
                                    {
                                        progressDialog.dismiss();
                                        AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
                                        builder.setCancelable(true);
                                        builder.setIcon(R.drawable.ic_baseline_info_24);
                                        builder.setTitle("Login Failed");
                                        builder.setMessage("Please try again");
                                        builder.setInverseBackgroundForced(true);
                                        builder.setPositiveButton("Close",new DialogInterface.OnClickListener(){

                                            @Override
                                            public void onClick(DialogInterface dialog, int which){
                                                dialog.dismiss();
                                            }
                                        });

                                        AlertDialog alert=builder.create();
                                        alert.show();

                                    }

                                }
                            }
                        });



            }
        });
    }

    private boolean validateAccountType() {
        String val = accountTypeInput.getText().toString();

        if(val.isEmpty())
        {
            accountType.setError("Field cannot be empty");
            return false;
        }
        else {
            accountType.setError(null);
            return true;
        }
    }

    @Override
    public void onBackPressed()
    {
        exitapp();
    }
    private void exitapp() {
        AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
        builder.setCancelable(true);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("Exit App");
        builder.setMessage("Are you sure you want to leave the application?");
        builder.setInverseBackgroundForced(true);
        builder.setPositiveButton("Yes",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which){
                finish();

            }
        });

        builder.setNegativeButton("No",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.dismiss();
            }
        });
        AlertDialog alert=builder.create();
        alert.show();
    }


    private boolean validatePass() {
        String val = passInput.getText().toString();

        if(val.isEmpty())
        {
            password.setError("Field cannot be empty");
            return false;
        }
        else {
            password.setError(null);
            return true;
        }
    }

    private boolean validateEmail() {
        String val = emailInput.getText().toString();
        String emailPattern = "^(.+)@(.+)$";

        if(val.isEmpty())
        {
            email.setError("Field cannot be empty");
            return false;
        }
        if(!val.matches(emailPattern))
        {
            email.setError("Invalid email address");
            return false;
        }
        else {
            email.setError(null);
            return true;
        }
    }
    private void nointernetp() {
        AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
        builder.setCancelable(true);
        builder.setIcon(R.drawable.ic_baseline_network_check_24);
        builder.setTitle("Bad Connection");
        builder.setMessage("No internet access, please activate the internet to use the app!");
        builder.setInverseBackgroundForced(true);
        builder.setPositiveButton("Close",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Reload",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which){

                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        AlertDialog alert=builder.create();
        alert.show();
    }

    private boolean checkConnectivity() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if ((info == null || !info.isConnected() || !info.isAvailable())) {
            // Toast.makeText(getApplicationContext(), "Sin conexión a Internet...", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
}