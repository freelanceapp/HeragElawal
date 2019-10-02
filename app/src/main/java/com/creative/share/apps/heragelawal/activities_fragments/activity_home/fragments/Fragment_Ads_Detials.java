package com.creative.share.apps.heragelawal.activities_fragments.activity_home.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.creative.share.apps.heragelawal.R;
import com.creative.share.apps.heragelawal.activities_fragments.activity_home.activity.HomeActivity;
import com.creative.share.apps.heragelawal.adapter.Comment_Ads_Adapter;
import com.creative.share.apps.heragelawal.adapter.Companies_Adapter;
import com.creative.share.apps.heragelawal.adapter.Company_Ads_Type_Adapter;
import com.creative.share.apps.heragelawal.adapter.More_Ads_Adapter;
import com.creative.share.apps.heragelawal.adapter.Product_Adapter;
import com.creative.share.apps.heragelawal.databinding.FragmentAdversimentDetialsBinding;
import com.creative.share.apps.heragelawal.models.Catohries_Model;
import com.creative.share.apps.heragelawal.models.UserModel;
import com.creative.share.apps.heragelawal.preferences.Preferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;

public class Fragment_Ads_Detials extends Fragment {
    private HomeActivity activity;
    private FragmentAdversimentDetialsBinding binding;
    private Preferences preferences;
    private UserModel userModel;
    private String lang;
    private Product_Adapter product_adapter;
    private More_Ads_Adapter more_ads_adapter;
    private Comment_Ads_Adapter comment_ads_adapter;
    private List<Catohries_Model> catohries_modelList;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_adversiment_detials, container, false);


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
        comment_ads_adapter = new Comment_Ads_Adapter(catohries_modelList, activity);
        product_adapter = new Product_Adapter(catohries_modelList, activity);
        more_ads_adapter = new More_Ads_Adapter(catohries_modelList, activity);
        binding.recComment.setLayoutManager(new GridLayoutManager(activity, 1));
        binding.recComment.setAdapter(comment_ads_adapter);
        binding.recComment.setNestedScrollingEnabled(false);

        binding.recMore.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.HORIZONTAL,false));
        binding.recMore.setAdapter(more_ads_adapter);
        binding.recProduct.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.HORIZONTAL,false));
        binding.recProduct.setAdapter(product_adapter);
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
        more_ads_adapter.notifyDataSetChanged();
        product_adapter.notifyDataSetChanged();
        comment_ads_adapter.notifyDataSetChanged();

    }

    public static Fragment_Ads_Detials newInstance() {
        return new Fragment_Ads_Detials();
    }


}
