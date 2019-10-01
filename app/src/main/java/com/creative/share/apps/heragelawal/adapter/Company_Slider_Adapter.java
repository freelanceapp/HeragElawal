package com.creative.share.apps.heragelawal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.PagerAdapter;

import com.creative.share.apps.heragelawal.R;
import com.creative.share.apps.heragelawal.databinding.SliderCompaniesBinding;
import com.creative.share.apps.heragelawal.databinding.SliderCompanyBinding;
import com.creative.share.apps.heragelawal.models.Catohries_Model;

import java.util.List;

public class Company_Slider_Adapter extends PagerAdapter {
    private List<Catohries_Model> imageList;
    private Context context;

    public Company_Slider_Adapter(List<Catohries_Model> imageList, Context context) {
        this.imageList = imageList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        SliderCompanyBinding rowBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.slider_company,container,false);
        container.addView(rowBinding.getRoot());
        return rowBinding.getRoot();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
