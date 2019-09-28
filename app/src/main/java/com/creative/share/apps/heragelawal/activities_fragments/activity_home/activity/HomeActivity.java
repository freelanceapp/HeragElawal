package com.creative.share.apps.heragelawal.activities_fragments.activity_home.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import com.creative.share.apps.heragelawal.R;
import com.creative.share.apps.heragelawal.activities_fragments.activity_home.fragments.Fragment_Company;
import com.creative.share.apps.heragelawal.activities_fragments.activity_home.fragments.Fragment_Favourite;
import com.creative.share.apps.heragelawal.activities_fragments.activity_home.fragments.Fragment_Home;
import com.creative.share.apps.heragelawal.activities_fragments.activity_home.fragments.Fragment_Main;
import com.creative.share.apps.heragelawal.activities_fragments.activity_home.fragments.Fragment_My_Adversiment;
import com.creative.share.apps.heragelawal.databinding.ActivityHomeBinding;
import com.creative.share.apps.heragelawal.language.LanguageHelper;
import com.creative.share.apps.heragelawal.models.UserModel;
import com.creative.share.apps.heragelawal.preferences.Preferences;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;
    private FragmentManager fragmentManager;
    private int fragment_count = 0;
    private Fragment_Home fragment_home;
    private Fragment_Main fragment_main;
private Fragment_Company fragment_company;
private Fragment_My_Adversiment fragment_my_adversiment;
private Fragment_Favourite fragment_favourite;
    private Preferences preferences;
    private UserModel userModel;

    private String lang;


    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        super.attachBaseContext(LanguageHelper.updateResources(newBase, Paper.book().read("lang", Locale.getDefault().getLanguage())));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        initView();

        if (savedInstanceState == null) {
            DisplayFragmentHome();
            DisplayFragmentMain();

        }

    }


    private void initView() {
        Paper.init(this);
        lang = Paper.book().read("lang", Locale.getDefault().getLanguage());
        fragmentManager = this.getSupportFragmentManager();
        preferences = Preferences.newInstance();
        userModel = preferences.getUserData(this);


    }

    public void DisplayFragmentHome() {
        fragment_count += 1;
        if (fragment_home == null) {
            fragment_home = Fragment_Home.newInstance();
        }

        if (fragment_home.isAdded()) {
            fragmentManager.beginTransaction().show(fragment_home).commit();

        } else {
            fragmentManager.beginTransaction().add(R.id.fragment_app_container, fragment_home, "fragment_home").addToBackStack("fragment_home").commit();

        }

    }

    public void DisplayFragmentMain()
    {

        if (fragment_main == null) {
            fragment_main = Fragment_Main.newInstance();
        }
if(fragment_company!=null&&fragment_company.isAdded()){
    fragmentManager.beginTransaction().hide(fragment_company).commit();
}
        if(fragment_my_adversiment!=null&&fragment_my_adversiment.isAdded()){
            fragmentManager.beginTransaction().hide(fragment_my_adversiment).commit();
        }
        if(fragment_favourite!=null&&fragment_favourite.isAdded()){
            fragmentManager.beginTransaction().hide(fragment_favourite).commit();
        }

        if (fragment_main.isAdded()) {
            fragmentManager.beginTransaction().show(fragment_main).commit();

        } else {
            fragmentManager.beginTransaction().add(R.id.fragment_home_container, fragment_main, "fragment_main").addToBackStack("fragment_main").commit();

        }
        if (fragment_home != null && fragment_home.isAdded()) {
            fragment_home.updateBottomNavigationPosition(0);
        }

    }
    public void DisplayFragmentCompany()
    {
        if (fragment_company == null) {
            fragment_company = Fragment_Company.newInstance();
        }
        if(fragment_main!=null&&fragment_main.isAdded()){
            fragmentManager.beginTransaction().hide(fragment_main).commit();
        }
        if(fragment_my_adversiment!=null&&fragment_my_adversiment.isAdded()){
            fragmentManager.beginTransaction().hide(fragment_my_adversiment).commit();
        }
        if(fragment_favourite!=null&&fragment_favourite.isAdded()){
            fragmentManager.beginTransaction().hide(fragment_favourite).commit();
        }
        if (fragment_company.isAdded()) {
            fragmentManager.beginTransaction().show(fragment_company).commit();

        } else {
            fragmentManager.beginTransaction().add(R.id.fragment_home_container, fragment_company, "fragment_company").addToBackStack("fragment_company").commit();

        }
        if (fragment_home != null && fragment_home.isAdded()) {
            fragment_home.updateBottomNavigationPosition(1);
        }

    }

    public void DisplayFragmentMyadvesriment()
    {
        if (fragment_my_adversiment == null) {
            fragment_my_adversiment = Fragment_My_Adversiment.newInstance();
        }
        if(fragment_main!=null&&fragment_main.isAdded()){
            fragmentManager.beginTransaction().hide(fragment_main).commit();
        }
        if(fragment_company!=null&&fragment_company.isAdded()){
            fragmentManager.beginTransaction().hide(fragment_company).commit();
        }
        if(fragment_favourite!=null&&fragment_favourite.isAdded()){
            fragmentManager.beginTransaction().hide(fragment_favourite).commit();
        }
        if (fragment_my_adversiment.isAdded()) {
            fragmentManager.beginTransaction().show(fragment_my_adversiment).commit();

        } else {
            fragmentManager.beginTransaction().add(R.id.fragment_home_container, fragment_my_adversiment, "fragment_my_adversiment").addToBackStack("fragment_my_adversiment").commit();

        }
        if (fragment_home != null && fragment_home.isAdded()) {
            fragment_home.updateBottomNavigationPosition(2);
        }

    }
    public void DisplayFragmentFavourite()
    {
        if (fragment_favourite == null) {
            fragment_favourite = Fragment_Favourite.newInstance();
        }
        if(fragment_main!=null&&fragment_main.isAdded()){
            fragmentManager.beginTransaction().hide(fragment_main).commit();
        }
        if(fragment_my_adversiment!=null&&fragment_my_adversiment.isAdded()){
            fragmentManager.beginTransaction().hide(fragment_my_adversiment).commit();
        }
        if(fragment_company!=null&&fragment_company.isAdded()){
            fragmentManager.beginTransaction().hide(fragment_company).commit();
        }
        if (fragment_favourite.isAdded()) {
            fragmentManager.beginTransaction().show(fragment_favourite).commit();

        } else {
            fragmentManager.beginTransaction().add(R.id.fragment_home_container, fragment_favourite, "fragment_favourite").addToBackStack("fragment_favourite").commit();

        }
        if (fragment_home != null && fragment_home.isAdded()) {
            fragment_home.updateBottomNavigationPosition(3);
        }

    }

    public void onBackPressed() {
        Back();
    }

    public void Back() {
        if (fragment_count > 1) {
            fragment_count -= 1;
            super.onBackPressed();
        } else {

            if (fragment_home != null && fragment_home.isVisible()) {
                if (fragment_main != null && fragment_main.isVisible()) {

                    if (userModel != null) {
                        finish();

                    } else {
                     //   NavigateToSignInActivity();
                    }

                } else {
                    DisplayFragmentMain();
                }
            } else {
                DisplayFragmentHome();
            }
        }

    }


}
