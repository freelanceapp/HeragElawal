package com.creative.share.apps.heragelawal.activities_fragments.activity_home.fragments.fragment_company;

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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.creative.share.apps.heragelawal.R;
import com.creative.share.apps.heragelawal.activities_fragments.activity_home.activity.HomeActivity;
import com.creative.share.apps.heragelawal.databinding.FragmentCompanyBinding;
import com.creative.share.apps.heragelawal.models.UserModel;
import com.creative.share.apps.heragelawal.preferences.Preferences;

import java.util.Locale;

import io.paperdb.Paper;

public class Fragment_Company extends Fragment {
    private HomeActivity activity;
    private FragmentCompanyBinding binding;
    private Preferences preferences;
    private UserModel userModel;
    private String lang;

private LinearLayoutManager manager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_company, container, false);


        initView();
        return binding.getRoot();
    }

    private void initView() {
        activity = (HomeActivity) getActivity();
        preferences = Preferences.newInstance();
        userModel = preferences.getUserData(activity);
        Paper.init(activity);
        lang = Paper.book().read("lang", Locale.getDefault().getLanguage());
        binding.setLang(lang);
        binding.progBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(activity, R.color.colorPrimary), PorterDuff.Mode.SRC_IN);

        binding.progBar3.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(activity, R.color.colorPrimary), PorterDuff.Mode.SRC_IN);

        binding.recStores.setLayoutManager(new GridLayoutManager(activity, 3));
        binding.recStores.setNestedScrollingEnabled(false);

        manager=new GridLayoutManager(activity,2,GridLayoutManager.HORIZONTAL,false);
        binding.recType.setLayoutManager(manager);
        binding.tablayout.setupWithViewPager(binding.pager);



    }



    public static Fragment_Company newInstance() {
        return new Fragment_Company();
    }


}
