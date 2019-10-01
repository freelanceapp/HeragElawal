package com.creative.share.apps.heragelawal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.creative.share.apps.heragelawal.R;
import com.creative.share.apps.heragelawal.activities_fragments.activity_home.activity.HomeActivity;
import com.creative.share.apps.heragelawal.databinding.AdversimentMainRowBinding;
import com.creative.share.apps.heragelawal.databinding.SideCatogryRowBinding;
import com.creative.share.apps.heragelawal.models.Catohries_Model;

import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;

public class Home_Catogry_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Catohries_Model> orderlist;
    private Context context;
    private LayoutInflater inflater;
    private String lang;
    private HomeActivity activity;
    private int i = -1;

    public Home_Catogry_Adapter(List<Catohries_Model> orderlist, Context context) {
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


        AdversimentMainRowBinding binding = DataBindingUtil.inflate(inflater, R.layout.adversiment_main_row, parent, false);
        return new EventHolder(binding);


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        EventHolder eventHolder = (EventHolder) holder;
        Catohries_Model catohries_model = orderlist.get(position);
        Home_Sub_Catogry_Adapter side_sub_catogry_adapter = new Home_Sub_Catogry_Adapter(catohries_model.getOrder_details(), activity);
        eventHolder.binding.recCart.setLayoutManager(new GridLayoutManager(activity,2));

        eventHolder.binding.recCart.setAdapter(side_sub_catogry_adapter);



    }

    @Override
    public int getItemCount() {
        return orderlist.size();
    }

    public class EventHolder extends RecyclerView.ViewHolder {
        public AdversimentMainRowBinding binding;

        public EventHolder(@NonNull AdversimentMainRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }


}
