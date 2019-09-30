package com.creative.share.apps.heragelawal.activities_fragments.activity_home.fragments;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.creative.share.apps.heragelawal.R;
import com.creative.share.apps.heragelawal.activities_fragments.activity_home.activity.HomeActivity;
import com.creative.share.apps.heragelawal.databinding.FragmentAboutBinding;
import com.creative.share.apps.heragelawal.databinding.FragmentCompanyBinding;
import com.creative.share.apps.heragelawal.models.UserModel;
import com.creative.share.apps.heragelawal.preferences.Preferences;

import java.util.Locale;

import io.paperdb.Paper;

public class Fragment_About extends Fragment {
    private HomeActivity activity;
    private FragmentAboutBinding binding;
    private Preferences preferences;
    private UserModel userModel;
    private String lang;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_about, container, false);


        initView();
        return binding.getRoot();
    }

    private void initView() {

        activity = (HomeActivity) getActivity();
        preferences = Preferences.newInstance();
        userModel = preferences.getUserData(activity);
        Paper.init(activity);
        lang = Paper.book().read("lang", Locale.getDefault().getLanguage());



    }


    public static Fragment_About newInstance() {
        return new Fragment_About();
    }


}
