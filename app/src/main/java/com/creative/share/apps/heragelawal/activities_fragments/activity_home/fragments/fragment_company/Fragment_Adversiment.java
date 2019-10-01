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
import androidx.recyclerview.widget.RecyclerView;

import com.creative.share.apps.heragelawal.R;
import com.creative.share.apps.heragelawal.activities_fragments.activity_home.activity.HomeActivity;
import com.creative.share.apps.heragelawal.adapter.Ads_Adapter;
import com.creative.share.apps.heragelawal.adapter.Ads_Catogry_Adapter;
import com.creative.share.apps.heragelawal.adapter.Notifications_Adapter;
import com.creative.share.apps.heragelawal.databinding.FragmentAdversimentBinding;
import com.creative.share.apps.heragelawal.databinding.FragmentChatBinding;
import com.creative.share.apps.heragelawal.models.Catohries_Model;
import com.creative.share.apps.heragelawal.models.UserModel;
import com.creative.share.apps.heragelawal.preferences.Preferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;

public class Fragment_Adversiment extends Fragment {
    private HomeActivity activity;
    private FragmentAdversimentBinding binding;
   private Preferences preferences;
   private UserModel userModel;
    private String lang;
    private Ads_Catogry_Adapter ads_catogry_adapter;
    private Ads_Adapter ads_adapter;
    private List<Catohries_Model> catohries_modelList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_adversiment, container, false);


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

        binding.setLang(lang);



        ads_catogry_adapter = new Ads_Catogry_Adapter(catohries_modelList, activity);
        ads_adapter=new Ads_Adapter(catohries_modelList,activity);
        binding.recType.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.HORIZONTAL,false));
        binding.recType.setAdapter(ads_catogry_adapter);
        binding.recType.setVisibility(View.VISIBLE);
        binding.recStores.setLayoutManager(new GridLayoutManager(activity,1));
        binding.recStores.setAdapter(ads_adapter);
        binding.recStores.setVisibility(View.VISIBLE);
        adddatat();



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

        ads_catogry_adapter.notifyDataSetChanged();
        ads_adapter.notifyDataSetChanged();

    }



    public static Fragment_Adversiment newInstance() {
        return new Fragment_Adversiment();
    }


}
