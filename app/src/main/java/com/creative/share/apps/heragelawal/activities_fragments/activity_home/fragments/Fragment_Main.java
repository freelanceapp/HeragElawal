package com.creative.share.apps.heragelawal.activities_fragments.activity_home.fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.creative.share.apps.heragelawal.R;
import com.creative.share.apps.heragelawal.activities_fragments.activity_home.activity.HomeActivity;
import com.creative.share.apps.heragelawal.adapter.Home_Catogry_Adapter;
import com.creative.share.apps.heragelawal.adapter.Home_Slider_Adapter;
import com.creative.share.apps.heragelawal.adapter.Side_Catogry_Adapter;
import com.creative.share.apps.heragelawal.databinding.FragmentMainBinding;
import com.creative.share.apps.heragelawal.models.Catohries_Model;
import com.creative.share.apps.heragelawal.models.UserModel;
import com.creative.share.apps.heragelawal.preferences.Preferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;

public class Fragment_Main extends Fragment {
    private HomeActivity activity;
    private FragmentMainBinding binding;
   private Preferences preferences;
   private UserModel userModel;
    private String lang;
    private List<Catohries_Model> catohries_modelList;
    private Home_Slider_Adapter sliderAdapter;
    private Home_Catogry_Adapter home_catogry_adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);


        initView();
        return binding.getRoot();
    }

    private void initView() {
catohries_modelList=new ArrayList<>();
        activity = (HomeActivity) getActivity();
        preferences = Preferences.newInstance();
        userModel = preferences.getUserData(activity);
        Paper.init(activity);
        lang = Paper.book().read("lang", Locale.getDefault().getLanguage());
        binding.progBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(activity, R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
if(lang.equals("ar")){
    binding.edtSearch.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.edit_shape2));
}
        home_catogry_adapter = new Home_Catogry_Adapter(catohries_modelList, activity);
        binding.recStores.setLayoutManager(new GridLayoutManager(activity, 1));
        binding.recStores.setAdapter(home_catogry_adapter);
        binding.recStores.setVisibility(View.VISIBLE);
        adddatat();

        sliderAdapter = new Home_Slider_Adapter(catohries_modelList, activity);
        binding.tablayout.setupWithViewPager(binding.pager);

        binding.pager.setAdapter(sliderAdapter);

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
        catohries_modelList.add(catohries_model);
        catohries_modelList.add(catohries_model1);
        catohries_modelList.add(catohries_model2);
        catohries_modelList.add(catohries_model);
        catohries_modelList.add(catohries_model1);
        catohries_modelList.add(catohries_model2);
        catohries_modelList.add(catohries_model);
        catohries_modelList.add(catohries_model1);
        catohries_modelList.add(catohries_model2);
        catohries_modelList.add(catohries_model);
        catohries_modelList.add(catohries_model1);
        catohries_modelList.add(catohries_model2);
        catohries_modelList.add(catohries_model);
        catohries_modelList.add(catohries_model1);
        catohries_modelList.add(catohries_model2);
        catohries_modelList.add(catohries_model);
        catohries_modelList.add(catohries_model1);
        catohries_modelList.add(catohries_model2);
        catohries_modelList.add(catohries_model);
        catohries_modelList.add(catohries_model1);
        catohries_modelList.add(catohries_model2);
        catohries_modelList.add(catohries_model);
        catohries_modelList.add(catohries_model1);
        catohries_modelList.add(catohries_model2);

        binding.progBar2.setVisibility(View.GONE);
        home_catogry_adapter.notifyDataSetChanged();

    }



    public static Fragment_Main newInstance() {
        return new Fragment_Main();
    }


}
