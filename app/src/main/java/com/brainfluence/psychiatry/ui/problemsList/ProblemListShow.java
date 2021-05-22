package com.brainfluence.psychiatry.ui.problemsList;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.brainfluence.psychiatry.R;
import com.brainfluence.psychiatry.bttom_nav_ui.Academic.Academic;
import com.brainfluence.psychiatry.bttom_nav_ui.psychological.Psychological;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProblemListShow extends Fragment {
    private BottomNavigationView navigationView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.psychological:
                    Psychological psychological = new Psychological();
                    FragmentManager fragmentManager1 = getChildFragmentManager();
                    fragmentManager1.beginTransaction().replace(R.id.problemShowFragment, psychological).commit();
                    return true;
                case R.id.academic:
                    Academic academic = new Academic();
                    FragmentManager fragmentManager2 = getChildFragmentManager();
                    fragmentManager2.beginTransaction().replace(R.id.problemShowFragment, academic).commit();

                    return true;
            }
            return false;
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        View root = inflater.inflate(R.layout.fragment_problem_list_show, container, false);

        navigationView = root.findViewById(R.id.navbartransporte);
        Psychological psychological = new Psychological();
        FragmentManager fragmentManager1 = getChildFragmentManager();
        fragmentManager1.beginTransaction().replace(R.id.problemShowFragment, psychological).commit();
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        return root;
    }
}