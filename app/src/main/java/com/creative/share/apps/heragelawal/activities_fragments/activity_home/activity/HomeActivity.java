package com.creative.share.apps.heragelawal.activities_fragments.activity_home.activity;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;


import com.creative.share.apps.heragelawal.R;
import com.creative.share.apps.heragelawal.activities_fragments.activity_home.fragments.Fragment_About;
import com.creative.share.apps.heragelawal.activities_fragments.activity_home.fragments.Fragment_Notifications;
import com.creative.share.apps.heragelawal.activities_fragments.activity_home.fragments.fragment_company.Fragment_Add_Company;
import com.creative.share.apps.heragelawal.activities_fragments.activity_home.fragments.Fragment_Add_ads;
import com.creative.share.apps.heragelawal.activities_fragments.activity_home.fragments.Fragment_Chat;
import com.creative.share.apps.heragelawal.activities_fragments.activity_home.fragments.fragment_company.Fragment_Adversiment;
import com.creative.share.apps.heragelawal.activities_fragments.activity_home.fragments.fragment_company.Fragment_Companies;
import com.creative.share.apps.heragelawal.activities_fragments.activity_home.fragments.Fragment_Favourite;
import com.creative.share.apps.heragelawal.activities_fragments.activity_home.fragments.Fragment_Home;
import com.creative.share.apps.heragelawal.activities_fragments.activity_home.fragments.Fragment_Main;
import com.creative.share.apps.heragelawal.activities_fragments.activity_home.fragments.Fragment_My_Adversiment;
import com.creative.share.apps.heragelawal.activities_fragments.activity_home.fragments.fragment_company.Fragment_Company;
import com.creative.share.apps.heragelawal.databinding.ActivityHomeBinding;
import com.creative.share.apps.heragelawal.language.LanguageHelper;
import com.creative.share.apps.heragelawal.models.UserModel;
import com.creative.share.apps.heragelawal.preferences.Preferences;


import java.util.Locale;

import io.paperdb.Paper;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;
    private FragmentManager fragmentManager;
    private int fragment_count = 0;
    private Fragment_Home fragment_home;
    private Fragment_Main fragment_main;
private Fragment_Companies fragment_companies;
private Fragment_My_Adversiment fragment_my_adversiment;
private Fragment_Favourite fragment_favourite;
private Fragment_Chat fragment_chat;
    private Preferences preferences;
    private UserModel userModel;

    private String lang;
    private Fragment_Add_Company fragment_add_company;
    private Fragment_Add_ads fragment_add_ads;
    private Fragment_Company fragment_company;
    private Fragment_About fragment_about;
    private Fragment_Notifications fragment_notifications;
    private Fragment_Adversiment fragment_adversiment;


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
    public void DisplayFragmentAddCompany() {
        fragment_count += 1;

            fragment_add_company = Fragment_Add_Company.newInstance();


        if (fragment_add_company.isAdded()) {
            fragmentManager.beginTransaction().show(fragment_add_company).commit();

        } else {
            fragmentManager.beginTransaction().add(R.id.fragment_app_container, fragment_add_company, "fragment_add_company").addToBackStack("fragment_add_company").commit();

        }

    }
    public void DisplayFragmentshowCompany() {
        fragment_count += 1;

        fragment_company = Fragment_Company.newInstance();


        if (fragment_company.isAdded()) {
            fragmentManager.beginTransaction().show(fragment_company).commit();

        } else {
            fragmentManager.beginTransaction().add(R.id.fragment_app_container, fragment_company, "fragment_company").addToBackStack("fragment_company").commit();

        }

    }
    public void DisplayFragmentAdversiment() {
        fragment_count += 1;

        fragment_adversiment = Fragment_Adversiment.newInstance();


        if (fragment_adversiment.isAdded()) {
            fragmentManager.beginTransaction().show(fragment_adversiment).commit();

        } else {
            fragmentManager.beginTransaction().add(R.id.fragment_app_container, fragment_adversiment, "fragment_adversiment").addToBackStack("fragment_adversiment").commit();

        }

    }
    public void DisplayFragmentAddads() {
        fragment_count += 1;

        fragment_add_ads = Fragment_Add_ads.newInstance();


        if (fragment_add_ads.isAdded()) {
            fragmentManager.beginTransaction().show(fragment_add_ads).commit();

        } else {
            fragmentManager.beginTransaction().add(R.id.fragment_app_container, fragment_add_ads, "fragment_add_ads").addToBackStack("fragment_add_ads").commit();

        }

    }
    public void DisplayFragmentNotifications() {
        fragment_count += 1;

        fragment_notifications = Fragment_Notifications.newInstance();


        if (fragment_notifications.isAdded()) {
            fragmentManager.beginTransaction().show(fragment_notifications).commit();

        } else {
            fragmentManager.beginTransaction().add(R.id.fragment_app_container, fragment_notifications, "fragment_notifications").addToBackStack("fragment_notifications").commit();

        }

    }
    public void DisplayFragmentAbout() {
        fragment_count += 1;

        fragment_about = Fragment_About.newInstance();


        if (fragment_about.isAdded()) {
            fragmentManager.beginTransaction().show(fragment_about).commit();

        } else {
            fragmentManager.beginTransaction().add(R.id.fragment_app_container, fragment_about, "fragment_about").addToBackStack("fragment_about").commit();

        }

    }
    
    public void DisplayFragmentMain()
    {

        if (fragment_main == null) {
            fragment_main = Fragment_Main.newInstance();
        }
if(fragment_companies !=null&& fragment_companies.isAdded()){
    fragmentManager.beginTransaction().hide(fragment_companies).commit();
}
        if(fragment_my_adversiment!=null&&fragment_my_adversiment.isAdded()){
            fragmentManager.beginTransaction().hide(fragment_my_adversiment).commit();
        }
        if(fragment_favourite!=null&&fragment_favourite.isAdded()){
            fragmentManager.beginTransaction().hide(fragment_favourite).commit();
        }
        if(fragment_chat!=null&&fragment_chat.isAdded()){
            fragmentManager.beginTransaction().hide(fragment_chat).commit();
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
    public void DisplayFragmentCompanies()
    {
        if (fragment_companies == null) {
            fragment_companies = Fragment_Companies.newInstance();
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
        if(fragment_chat!=null&&fragment_chat.isAdded()){
            fragmentManager.beginTransaction().hide(fragment_chat).commit();
        }
        if (fragment_companies.isAdded()) {
            fragmentManager.beginTransaction().show(fragment_companies).commit();

        } else {
            fragmentManager.beginTransaction().add(R.id.fragment_home_container, fragment_companies, "fragment_companies").addToBackStack("fragment_companies").commit();

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
        if(fragment_companies !=null&& fragment_companies.isAdded()){
            fragmentManager.beginTransaction().hide(fragment_companies).commit();
        }
        if(fragment_favourite!=null&&fragment_favourite.isAdded()){
            fragmentManager.beginTransaction().hide(fragment_favourite).commit();
        }
        if(fragment_chat!=null&&fragment_chat.isAdded()){
            fragmentManager.beginTransaction().hide(fragment_chat).commit();
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
        if(fragment_companies !=null&& fragment_companies.isAdded()){
            fragmentManager.beginTransaction().hide(fragment_companies).commit();
        }
        if(fragment_chat!=null&&fragment_chat.isAdded()){
            fragmentManager.beginTransaction().hide(fragment_chat).commit();
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
    public void DisplayFragmentChat()
    {
        if (fragment_chat == null) {
            fragment_chat = Fragment_Chat.newInstance();
        }
        if(fragment_main!=null&&fragment_main.isAdded()){
            fragmentManager.beginTransaction().hide(fragment_main).commit();
        }
        if(fragment_my_adversiment!=null&&fragment_my_adversiment.isAdded()){
            fragmentManager.beginTransaction().hide(fragment_my_adversiment).commit();
        }
        if(fragment_companies !=null&& fragment_companies.isAdded()){
            fragmentManager.beginTransaction().hide(fragment_companies).commit();
        }
        if(fragment_favourite!=null&&fragment_favourite.isAdded()){
            fragmentManager.beginTransaction().hide(fragment_favourite).commit();
        }
        if (fragment_chat.isAdded()) {
            fragmentManager.beginTransaction().show(fragment_chat).commit();

        } else {
            fragmentManager.beginTransaction().add(R.id.fragment_home_container, fragment_chat, "fragment_chat").addToBackStack("fragment_chat").commit();

        }
        if (fragment_home != null && fragment_home.isAdded()) {
            fragment_home.updateBottomNavigationPosition(4);
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
