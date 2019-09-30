package com.creative.share.apps.heragelawal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.creative.share.apps.heragelawal.R;
import com.creative.share.apps.heragelawal.activities_fragments.activity_home.activity.HomeActivity;
import com.creative.share.apps.heragelawal.databinding.AdsTypeRowBinding;
import com.creative.share.apps.heragelawal.databinding.CompanyRowBinding;
import com.creative.share.apps.heragelawal.models.Catohries_Model;

import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;

public class Company_Ads_Type_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Catohries_Model> orderlist;
    private Context context;
    private LayoutInflater inflater;
    private String lang;
    private HomeActivity activity;
    private int i = -1;

    public Company_Ads_Type_Adapter(List<Catohries_Model> orderlist, Context context) {
        this.orderlist = orderlist;
        this.context = context;
        inflater = LayoutInflater.from(context);
        Paper.init(context);
        lang = Paper.book().read("lang", Locale.getDefault().getLanguage());
        this.activity = (HomeActivity) context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        AdsTypeRowBinding binding = DataBindingUtil.inflate(inflater, R.layout.ads_type_row, parent, false);
        return new EventHolder(binding);


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        EventHolder eventHolder = (EventHolder) holder;
        Catohries_Model catohries_model = orderlist.get(position);



    }

    @Override
    public int getItemCount() {
        return orderlist.size();
    }

    public class EventHolder extends RecyclerView.ViewHolder {
        public AdsTypeRowBinding binding;

        public EventHolder(@NonNull AdsTypeRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }


}
