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
import com.creative.share.apps.heragelawal.databinding.SideCatogryRowBinding;
import com.creative.share.apps.heragelawal.models.Catohries_Model;

import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;

public class Side_Catogry_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Catohries_Model> orderlist;
    private Context context;
    private LayoutInflater inflater;
    private String lang;
    private HomeActivity activity;
    private int i = -1;

    public Side_Catogry_Adapter(List<Catohries_Model> orderlist, Context context) {
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


        SideCatogryRowBinding binding = DataBindingUtil.inflate(inflater, R.layout.side_catogry_row, parent, false);
        return new EventHolder(binding);


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        EventHolder eventHolder = (EventHolder) holder;
        Catohries_Model catohries_model = orderlist.get(position);
        Side_Sub_Catogry_Adapter side_sub_catogry_adapter = new Side_Sub_Catogry_Adapter(catohries_model.getOrder_details(), activity);
        eventHolder.binding.recSub.setLayoutManager(new GridLayoutManager(activity,1));

        eventHolder.binding.recSub.setAdapter(side_sub_catogry_adapter);
        eventHolder.binding.setLang(lang);
        eventHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = position;
                notifyDataSetChanged();
            }
        });
        if (position == i) {
            if (eventHolder.binding.recSub.getVisibility() == View.VISIBLE) {
                eventHolder.binding.llData.setBackgroundColor(activity.getResources().getColor(R.color.colorPrimary));
                eventHolder.binding.recSub.setVisibility(View.GONE);
                eventHolder.binding.arrow.setRotation(360.0f);
                if(lang.equals("ar")){
                    eventHolder.binding.arrow.setRotation(180.0f);
                }
            } else {
                eventHolder.binding.llData.setBackgroundColor(activity.getResources().getColor(R.color.main));
                eventHolder.binding.recSub.setVisibility(View.VISIBLE);
                eventHolder.binding.arrow.setRotation(90.0f);

            }
        }


    }

    @Override
    public int getItemCount() {
        return orderlist.size();
    }

    public class EventHolder extends RecyclerView.ViewHolder {
        public SideCatogryRowBinding binding;

        public EventHolder(@NonNull SideCatogryRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            if(lang.equals("ar")){
                binding.arrow.setRotation(180.0f);
            }
        }
    }


}
