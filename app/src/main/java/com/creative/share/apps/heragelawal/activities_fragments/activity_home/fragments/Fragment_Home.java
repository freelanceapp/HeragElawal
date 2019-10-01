package com.creative.share.apps.heragelawal.activities_fragments.activity_home.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.creative.share.apps.heragelawal.R;
import com.creative.share.apps.heragelawal.activities_fragments.activity_home.activity.HomeActivity;
import com.creative.share.apps.heragelawal.adapter.Side_Catogry_Adapter;
import com.creative.share.apps.heragelawal.databinding.FragmentHomeBinding;
import com.creative.share.apps.heragelawal.models.Catohries_Model;
import com.creative.share.apps.heragelawal.models.UserModel;
import com.creative.share.apps.heragelawal.preferences.Preferences;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;

public class Fragment_Home extends Fragment {
    private HomeActivity activity;
    private FragmentHomeBinding binding;
    private Preferences preferences;
    private UserModel userModel;
    private String lang;

    private Side_Catogry_Adapter side_catogry_adapter;
    private List<Catohries_Model> catohries_modelList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        initView();
        return binding.getRoot();
    }

    private void initView() {
        ;
        activity = (HomeActivity) getActivity();
        Paper.init(activity);
        lang = Paper.book().read("lang", Locale.getDefault().getLanguage());
        binding.setLang(lang);
        catohries_modelList = new ArrayList<>();
        preferences = Preferences.newInstance();
        userModel = preferences.getUserData(activity);
        side_catogry_adapter = new Side_Catogry_Adapter(catohries_modelList, activity);
        binding.recCatogry.setLayoutManager(new GridLayoutManager(activity, 1));
        binding.recCatogry.setAdapter(side_catogry_adapter);
        binding.recCatogry.setNestedScrollingEnabled(false);

        adddatat();
        setUpBottomNavigation();
        binding.ahBottomNav.setOnTabSelectedListener((position, wasSelected) -> {
            switch (position) {
                case 0:
                    activity.DisplayFragmentMain();
                    break;
                case 1:

                    activity.DisplayFragmentCompanies();
                    break;
                case 2:
                    activity.DisplayFragmentMyadvesriment();


                    break;
                case 3:
                    activity.DisplayFragmentFavourite();

                    break;
                case 4:
                    activity.DisplayFragmentChat();
                    break;

            }
            return false;
        });
        binding.imMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.drawerLayout.openDrawer(GravityCompat.START);

            }
        });
        binding.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.ahBottomNav.getCurrentItem() == 1) {
                    activity.DisplayFragmentAddCompany();
                } else {
                    activity.DisplayFragmentAddads();
                }
            }
        });
        binding.llAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.drawerLayout.closeDrawers();
                activity.DisplayFragmentAbout();
            }
        });
        binding.imNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.DisplayFragmentNotifications();
            }
        });

    }

    private void adddatat() {
        List<Catohries_Model.Order_details> order_details = new ArrayList<>();
        order_details.add(new Catohries_Model.Order_details());
        order_details.add(new Catohries_Model.Order_details());
        order_details.add(new Catohries_Model.Order_details());

        Catohries_Model catohries_model = new Catohries_Model();
        catohries_model.setOrder_details(order_details);

        Catohries_Model catohries_model1 = new Catohries_Model();
        catohries_model1.setOrder_details(order_details);

        Catohries_Model catohries_model2 = new Catohries_Model();
        catohries_model2.setOrder_details(order_details);
        catohries_modelList.add(catohries_model);
        catohries_modelList.add(catohries_model1);
        catohries_modelList.add(catohries_model2);
        side_catogry_adapter.notifyDataSetChanged();

    }

    private void setUpBottomNavigation() {

        AHBottomNavigationItem item1 = new AHBottomNavigationItem(getString(R.string.Categories), R.drawable.ic_nav_home);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(getString(R.string.companies), R.drawable.compay);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(getString(R.string.my_Advdersiment), R.drawable.ic_user);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(getString(R.string.favourite), R.drawable.ic_favorite);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem(getString(R.string.chat), R.drawable.ic_chat);

        binding.ahBottomNav.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        binding.ahBottomNav.setDefaultBackgroundColor(ContextCompat.getColor(activity, R.color.white));
        binding.ahBottomNav.setTitleTextSizeInSp(14, 12);
        binding.ahBottomNav.setForceTint(true);
        binding.ahBottomNav.setAccentColor(ContextCompat.getColor(activity, R.color.colorAccent));
        binding.ahBottomNav.setInactiveColor(ContextCompat.getColor(activity, R.color.black));

        binding.ahBottomNav.addItem(item1);
        binding.ahBottomNav.addItem(item2);
        binding.ahBottomNav.addItem(item3);
        binding.ahBottomNav.addItem(item4);
        binding.ahBottomNav.addItem(item5);

    }

    public void updateBottomNavigationPosition(int pos) {
        binding.ahBottomNav.setCurrentItem(pos, false);
        if (pos == 1) {
            binding.tvTitle.setText(activity.getResources().getString(R.string.add_company));
        } else {
            binding.tvTitle.setText(activity.getResources().getString(R.string.advertise_now_for_free));

        }
    }

    public static Fragment_Home newInstance() {
        return new Fragment_Home();
    }

}
