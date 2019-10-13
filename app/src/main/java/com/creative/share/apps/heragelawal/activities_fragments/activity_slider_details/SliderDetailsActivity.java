package com.creative.share.apps.heragelawal.activities_fragments.activity_slider_details;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.creative.share.apps.heragelawal.BuildConfig;
import com.creative.share.apps.heragelawal.R;
import com.creative.share.apps.heragelawal.adapter.CommentAdapter;
import com.creative.share.apps.heragelawal.adapter.PropertyAdapter;
import com.creative.share.apps.heragelawal.adapter.SliderAdapter2;
import com.creative.share.apps.heragelawal.databinding.ActivitySliderDetailsBinding;
import com.creative.share.apps.heragelawal.interfaces.Listeners;
import com.creative.share.apps.heragelawal.language.LanguageHelper;
import com.creative.share.apps.heragelawal.models.AdModel;
import com.creative.share.apps.heragelawal.models.SliderImageVideoModel;
import com.creative.share.apps.heragelawal.models.UserModel;
import com.creative.share.apps.heragelawal.preferences.Preferences;
import com.creative.share.apps.heragelawal.remote.Api;
import com.creative.share.apps.heragelawal.tags.Tags;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SliderDetailsActivity extends AppCompatActivity implements Listeners.BackListener , Listeners.SliderActionListener {

    private ActivitySliderDetailsBinding binding;
    private Preferences preferences;
    private UserModel userModel;
    private String lang;
    private List<SliderImageVideoModel> sliderModelList;
    private List<AdModel.AdDetails> adDetailsList;
    private List<AdModel.CommentModel> commentModelList;
    private Timer timer;
    private TimerTask timerTask;
    private PropertyAdapter propertyAdapter;
    private CommentAdapter commentAdapter;
    private int ad_id;
    private AdModel adModel = null;

    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        super.attachBaseContext(LanguageHelper.updateResources(newBase, Paper.book().read("lang", Locale.getDefault().getLanguage())));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_slider_details);
        getDataFromIntent();
        initView();
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        if (intent!=null&&intent.hasExtra("ad_id"))
        {
            ad_id = intent.getIntExtra("ad_id",0);

        }
    }

    private void initView()
    {
        sliderModelList = new ArrayList<>();
        adDetailsList = new ArrayList<>();
        commentModelList = new ArrayList<>();


        Paper.init(this);
        lang = Paper.book().read("lang",Locale.getDefault().getLanguage());
        preferences = Preferences.newInstance();
        userModel = preferences.getUserData(this);
        binding.setBackListener(this);
        binding.setLang(lang);
        binding.setActionListener(this);
        binding.tabLayout.setupWithViewPager(binding.pager);
        binding.progBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(this,R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        binding.progBarImage.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(this,R.color.color3), PorterDuff.Mode.SRC_IN);
        propertyAdapter = new PropertyAdapter(this,adDetailsList);
        binding.recViewProperty.setLayoutManager(new GridLayoutManager(this,2));
        binding.recViewProperty.setAdapter(propertyAdapter);
        binding.recViewProperty.setNestedScrollingEnabled(true);

        commentAdapter = new CommentAdapter(this,commentModelList);
        binding.recViewComments.setLayoutManager(new LinearLayoutManager(this));
        binding.recViewComments.setAdapter(commentAdapter);
        binding.recViewComments.setNestedScrollingEnabled(true);
        getAdDetails();

    }

    private void getAdDetails()
    {
        int user_id;
        if (userModel==null)
        {
            user_id =0;
        }else
        {
            user_id = userModel.getId();
        }

        try {

            Api.getService(Tags.base_url)
                    .getAdDetails(user_id,ad_id)
                    .enqueue(new Callback<AdModel>() {
                        @Override
                        public void onResponse(Call<AdModel> call, Response<AdModel> response) {
                            binding.progBarSlider.setVisibility(View.GONE);
                            if (response.isSuccessful()&&response.body()!=null)
                            {
                                updateUI(response.body());

                            }else
                            {
                                binding.progBarSlider.setVisibility(View.GONE);
                                binding.flSlider.setVisibility(View.GONE);
                                if (response.code() == 500) {
                                    Toast.makeText(SliderDetailsActivity.this, "Server Error", Toast.LENGTH_SHORT).show();


                                }else
                                {
                                    Toast.makeText(SliderDetailsActivity.this, getString(R.string.failed), Toast.LENGTH_SHORT).show();

                                    try {

                                        Log.e("error",response.code()+"_"+response.errorBody().string());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<AdModel> call, Throwable t) {
                            try {
                                binding.progBarSlider.setVisibility(View.GONE);
                                binding.flSlider.setVisibility(View.GONE);

                                if (t.getMessage()!=null)
                                {
                                    Log.e("error",t.getMessage());
                                    if (t.getMessage().toLowerCase().contains("failed to connect")||t.getMessage().toLowerCase().contains("unable to resolve host"))
                                    {
                                        Toast.makeText(SliderDetailsActivity.this,R.string.something, Toast.LENGTH_SHORT).show();
                                    }else
                                    {
                                        Toast.makeText(SliderDetailsActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }

                            }catch (Exception e){}
                        }
                    });
        }catch (Exception e){


        }
    }

    private void updateUI(AdModel body) {
        adModel = body;
        binding.setAdModel(adModel);
        adDetailsList.clear();
        adDetailsList.addAll(adModel.getAdvertisementDetails());
        commentModelList.clear();
        commentModelList.addAll(adModel.getComments());
        Picasso.with(this).load(Uri.parse(Tags.IMAGE_AVATAR+adModel.getUser_avatar())).fit().placeholder(R.drawable.ic_user).into(binding.image, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
                binding.progBarImage.setVisibility(View.GONE);
            }

            @Override
            public void onError() {
                binding.progBarImage.setVisibility(View.GONE);

            }
        });

        setUpSlider(adModel.getImage_array());

        if (adDetailsList.size()>0)
        {
            propertyAdapter.notifyDataSetChanged();
            binding.tvNoProperty.setVisibility(View.GONE);
        }else
            {
                binding.tvNoProperty.setVisibility(View.VISIBLE);

            }

        if (commentModelList.size()>0)
        {
            commentAdapter.notifyDataSetChanged();
            binding.tvNoComments.setVisibility(View.GONE);
        }else
            {
                binding.tvNoComments.setVisibility(View.VISIBLE);

            }
        new Handler().postDelayed(() -> {
            binding.nestedScrollView.getParent().requestChildFocus(binding.nestedScrollView,binding.nestedScrollView);
            binding.progBar.setVisibility(View.GONE);
            binding.nestedScrollView.setVisibility(View.VISIBLE);
        },50);



    }

    private void setUpSlider(List<String> images) {

        binding.progBarSlider.setVisibility(View.GONE);
        if (images.size()>0)
        {

            if (!adModel.getVideo().isEmpty()|| adModel.getVideo()!=null)
            {
                sliderModelList.add(new SliderImageVideoModel(adModel.getVideo(),true));


            }

            for (String img :images)
            {
                sliderModelList.add(new SliderImageVideoModel(img,false));
            }

            binding.flSlider.setVisibility(View.VISIBLE);
            SliderAdapter2 sliderAdapter2 = new SliderAdapter2(this,sliderModelList);
            binding.pager.setAdapter(sliderAdapter2);


            if (adModel.getVideo().isEmpty()|| adModel.getVideo()==null)
            {
                if (images.size()>1)
                {
                    timer = new Timer();
                    timerTask = new MyTimerTask();
                    timer.scheduleAtFixedRate(timerTask,6000,6000);
                }
            }



        }else
        {
            binding.flSlider.setVisibility(View.GONE);

        }


    }

    @Override
    public void back() {
        finish();
    }

    @Override
    public void call() {
        if (adModel.getUser_phone()!=null&&!adModel.getUser_phone().isEmpty())
        {
            Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+adModel.getUser_phone()));
            startActivity(intent);

        }else
            {
                Toast.makeText(this, getString(R.string.no_phone), Toast.LENGTH_SHORT).show();
            }
    }

    @Override
    public void message() {


        if (adModel.getUser_phone() != null && !adModel.getUser_phone().isEmpty()) {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:" + "01017357658"));
            startActivity(intent);

        } else {
            Toast.makeText(this, getString(R.string.no_phone), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void chat() {

    }

    @Override
    public void like() {
        if (userModel!=null)
        {

        }

        if (adModel.getIs_like_check()==0)
        {
            adModel.setIs_like_check(1);
            adModel.setLike_counts(adModel.getLike_counts()+1);
            binding.setAdModel(adModel);
        }
    }

    @Override
    public void favorite() {
        if (adModel.getIs_favourite_check()==0)
        {
            adModel.setIs_favourite_check(1);
            adModel.setLike_counts(adModel.getFavorite_counts()+1);
            binding.setAdModel(adModel);
        }
    }

    @Override
    public void report() {
        if (adModel.getIs_report_check()==0)
        {
            adModel.setIs_report_check(1);
            binding.setAdModel(adModel);
        }
    }

    @Override
    public void facebook() {


        String path=Tags.IMAGE_URL_ADS+adModel.getMain_image();

        Picasso.with(this).load(Uri.parse(path)).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

                Uri  uri =  FileProvider.getUriForFile(SliderDetailsActivity.this, BuildConfig.APPLICATION_ID+".provider",createFile(bitmap));
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setType("image/*");
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_STREAM,uri);
                sendIntent.putExtra(Intent.EXTRA_TEXT,adModel.getTitle()+getString(R.string.more_details)+"\n"+adModel.getLink_to_share());
                sendIntent.setPackage("com.facebook.katana");
                sendIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                try {
                    startActivity(sendIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(SliderDetailsActivity.this, "Please Install Facebook Messenger", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
    }


    @Override
    public void whatsapp() {

        String path=Tags.IMAGE_URL_ADS+adModel.getMain_image();

        Picasso.with(this).load(Uri.parse(path)).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

                Uri  uri =  FileProvider.getUriForFile(SliderDetailsActivity.this, BuildConfig.APPLICATION_ID+".provider",createFile(bitmap));
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setType("image/*");
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_STREAM,uri);
                sendIntent.putExtra(Intent.EXTRA_TEXT,adModel.getTitle()+getString(R.string.more_details)+"\n"+adModel.getLink_to_share());

                sendIntent.setPackage("com.whatsapp");
                sendIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                try {
                    startActivity(sendIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp")));


                }
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
    }

    @Override
    public void twitter() {

        String path=Tags.IMAGE_URL_ADS+adModel.getMain_image();

        Picasso.with(this).load(Uri.parse(path)).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

                Uri  uri =  FileProvider.getUriForFile(SliderDetailsActivity.this, BuildConfig.APPLICATION_ID+".provider",createFile(bitmap));
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setType("image/*");
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_STREAM,uri);
                sendIntent.putExtra(Intent.EXTRA_TEXT,adModel.getTitle()+getString(R.string.more_details)+"\n"+adModel.getLink_to_share());

                sendIntent.setPackage("com.twitter.android");
                sendIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                try {
                    startActivity(sendIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://play.google.com/store/apps/details?id=com.twitter.android")));
                }
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
    }

    @Override
    public void instagram() {

        String path=Tags.IMAGE_URL_ADS+adModel.getMain_image();

        Picasso.with(this).load(Uri.parse(path)).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

                Uri  uri =  FileProvider.getUriForFile(SliderDetailsActivity.this, BuildConfig.APPLICATION_ID+".provider",createFile(bitmap));
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setType("image/*");
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_STREAM,uri);
                sendIntent.putExtra(Intent.EXTRA_TEXT,adModel.getTitle()+getString(R.string.more_details)+"\n"+adModel.getLink_to_share());

                sendIntent.setPackage("com.instagram.android");
                sendIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                try {
                    startActivity(sendIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://play.google.com/store/apps/details?id=com.instagram.android")));
                }
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });



    }

    private File createFile(Bitmap bitmap)
    {
        File file = null;

        try {
            file =  new File(getExternalFilesDir(
                    Environment.DIRECTORY_PICTURES),System.currentTimeMillis() + ".png");
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }
    public class MyTimerTask extends TimerTask{
        @Override
        public void run() {
            runOnUiThread(() -> {
                if (binding.pager.getCurrentItem()<sliderModelList.size()-1)
                {
                    binding.pager.setCurrentItem(binding.pager.getCurrentItem()+1);
                }else
                {
                    binding.pager.setCurrentItem(0);

                }
            });
        }


    }


    private void stopTimer()
    {
        if (timer!=null)
        {
            timer.cancel();
            timer.purge();
        }

        if (timerTask!=null)
        {
            timerTask.cancel();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode ==1 &&grantResults.length>0)
        {
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED&&grantResults[1]==PackageManager.PERMISSION_GRANTED)
            {

            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopTimer();
    }
}
