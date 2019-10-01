package com.creative.share.apps.heragelawal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.creative.share.apps.heragelawal.R;
import com.creative.share.apps.heragelawal.activities_fragments.activity_home.activity.HomeActivity;
import com.creative.share.apps.heragelawal.databinding.InsideRecMainRowBinding;
import com.creative.share.apps.heragelawal.databinding.SideSubCatogryRowBinding;
import com.creative.share.apps.heragelawal.models.Catohries_Model;

import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;

public class Home_Sub_Catogry_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Catohries_Model.Order_details> orderlist;
    private Context context;
    private LayoutInflater inflater;
    private String lang;
    private HomeActivity activity;
private int i=0;
    public Home_Sub_Catogry_Adapter(List<Catohries_Model.Order_details> orderlist, Context context) {
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


        InsideRecMainRowBinding binding  = DataBindingUtil.inflate(inflater, R.layout.inside_rec_main_row,parent,false);
            return new EventHolder(binding);


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            EventHolder eventHolder = (EventHolder) holder;




    }

    @Override
    public int getItemCount() {
        return orderlist.size();
    }

    public class EventHolder extends RecyclerView.ViewHolder {
        public  InsideRecMainRowBinding binding;
        public EventHolder(@NonNull InsideRecMainRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }




}
