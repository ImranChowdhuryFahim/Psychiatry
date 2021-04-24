package com.brainfluence.psychiatry;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.internal.NavigationMenu;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import static com.brainfluence.psychiatry.LoginActivity.ACCOUNT_TYPE;
import static com.brainfluence.psychiatry.LoginActivity.EMAIL;
import static com.brainfluence.psychiatry.LoginActivity.IS_LOGGED_IN;
import static com.brainfluence.psychiatry.LoginActivity.SHARED_PREFS;
import static com.brainfluence.psychiatry.LoginActivity.USER_NAME;

public class Home extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private NavigationMenu navigationMenu;
    private TextView userName,userEmail;
    private SharedPreferences sharedPref;
    private String accountType,name,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        View headerView = navigationView.getHeaderView(0);
        userName = headerView.findViewById(R.id.userName);
        userEmail = headerView.findViewById(R.id.userEmail);


        sharedPref = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        name = sharedPref.getString(USER_NAME,"User");
        email = sharedPref.getString(EMAIL,"user@gmail.com");
        accountType = sharedPref.getString(ACCOUNT_TYPE,"students");

        userName.setText(name);
        userEmail.setText(email);

        Menu menu = navigationView.getMenu();

        if(accountType.equals("students"))
        {
            menu.findItem(R.id.studentRequests).setVisible(false);
            menu.findItem(R.id.appointmentRequests).setVisible(false);
        }

        else if (accountType.equals("doctors")){
            menu.findItem(R.id.updateInfo).setVisible(false);
            menu.findItem(R.id.problemList).setVisible(false);
            menu.findItem(R.id.studentRequests).setVisible(false);
        }

        else {
            menu.findItem(R.id.updateInfo).setVisible(false);
            menu.findItem(R.id.problemList).setVisible(false);
            menu.findItem(R.id.appointmentRequests).setVisible(false);
        }

//        menu.findItem(R.id.nav_gallery).setVisible(false);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.profile,R.id.updateInfo,R.id.problemList,R.id.appointmentRequests,R.id.studentRequests,R.id.appointmentList, R.id.nav_depressionMeter,R.id.notifications,R.id.logout)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}