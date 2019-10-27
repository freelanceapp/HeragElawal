package com.creative.share.apps.heragelawal.activities_fragments.activity_add_ads;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;

import com.creative.share.apps.heragelawal.R;
import com.creative.share.apps.heragelawal.activities_fragments.activity_add_company.FragmentMapTouchListener;
import com.creative.share.apps.heragelawal.adapter.SpinnerSubCategoryAdapter;
import com.creative.share.apps.heragelawal.databinding.ActivityAddAdsBinding;
import com.creative.share.apps.heragelawal.databinding.DialogAlertBinding;
import com.creative.share.apps.heragelawal.interfaces.Listeners;
import com.creative.share.apps.heragelawal.language.LanguageHelper;
import com.creative.share.apps.heragelawal.models.PlaceGeocodeData;
import com.creative.share.apps.heragelawal.models.PlaceMapDetailsData;
import com.creative.share.apps.heragelawal.models.SubCategoryDataModel;
import com.creative.share.apps.heragelawal.models.UserModel;
import com.creative.share.apps.heragelawal.preferences.Preferences;
import com.creative.share.apps.heragelawal.remote.Api;
import com.creative.share.apps.heragelawal.share.Common;
import com.creative.share.apps.heragelawal.tags.Tags;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAdsActivity extends AppCompatActivity  implements  OnMapReadyCallback,GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener, LocationListener, Listeners.BackListener {
    private ActivityAddAdsBinding binding;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private final String fineLocPerm = Manifest.permission.ACCESS_FINE_LOCATION;
    private final int loc_req = 1225;
    private GoogleMap mMap;
    private double lat,lng;
    private Marker marker;
    private final float zoom = 15.6f;
    private String lang;
    private FragmentMapTouchListener fragment;
    private List<SubCategoryDataModel.SubCategoryModel> subCategoryModelList;
    private SpinnerSubCategoryAdapter adapter;
    private UserModel userModel;
    private Preferences preferences;






    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        super.attachBaseContext(LanguageHelper.updateResources(newBase, Paper.book().read("lang", Locale.getDefault().getLanguage())));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_ads);
        initView();
    }

    private void initView() {
        subCategoryModelList = new ArrayList<>();
        preferences = Preferences.newInstance();
        userModel = preferences.getUserData(this);
        Paper.init(this);
        lang = Paper.book().read("lang",Locale.getDefault().getLanguage());
        binding.setLang(lang);
        binding.setBackListener(this);
        initMap();
        CheckPermission();


        binding.imageSearch.setOnClickListener(view -> {
            String query = binding.edtAddress.getText().toString().trim();
            if (!TextUtils.isEmpty(query))
            {
                binding.edtAddress.setError(null);
                Search(query);
            }else
            {
                binding.edtAddress.setError(getString(R.string.field_req));

            }
        });




        adapter = new SpinnerSubCategoryAdapter(subCategoryModelList,this);
        binding.spinner.setAdapter(adapter);
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i==0)
                {

                }else
                {
                    int cat_id = subCategoryModelList.get(i).getId();

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        getSubCategory();
    }




    private void CreateDialogAlert(String msg) {
        final AlertDialog dialog = new AlertDialog.Builder(this)
                .create();

        DialogAlertBinding binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.dialog_alert, null, false);

        binding.tvMsg.setText(msg);
        binding.btnCancel.setOnClickListener(v -> {
                    dialog.dismiss();
                    finish();
                }

        );
        dialog.getWindow().getAttributes().windowAnimations = R.style.dialog_congratulation_animation;
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_window_bg);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setView(binding.getRoot());
        dialog.show();
    }


    private void getSubCategory()
    {
        ProgressDialog dialog = Common.createProgressDialog(this,getString(R.string.wait));
        dialog.setCancelable(false);
        dialog.show();
        try {

            Api.getService(Tags.base_url)
                    .getAllSubCategory()
                    .enqueue(new Callback<SubCategoryDataModel>() {
                        @Override
                        public void onResponse(Call<SubCategoryDataModel> call, Response<SubCategoryDataModel> response) {
                            dialog.dismiss();
                            if (response.isSuccessful()&&response.body()!=null&&response.body().getData()!=null)
                            {
                                subCategoryModelList.clear();
                                subCategoryModelList.add(new SubCategoryDataModel.SubCategoryModel(0,getString(R.string.ch_cat)));
                                subCategoryModelList.addAll(response.body().getData());
                                adapter.notifyDataSetChanged();
                            }else
                            {
                                if (response.code() == 500) {
                                    Toast.makeText(AddAdsActivity.this, "Server Error", Toast.LENGTH_SHORT).show();


                                }else
                                {
                                    Toast.makeText(AddAdsActivity.this, getString(R.string.failed), Toast.LENGTH_SHORT).show();

                                    try {

                                        Log.e("error",response.code()+"_"+response.errorBody().string());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<SubCategoryDataModel> call, Throwable t) {
                            try {
                                dialog.dismiss();
                                if (t.getMessage()!=null)
                                {
                                    Log.e("error",t.getMessage());
                                    if (t.getMessage().toLowerCase().contains("failed to connect")||t.getMessage().toLowerCase().contains("unable to resolve host"))
                                    {
                                        Toast.makeText(AddAdsActivity.this,R.string.something, Toast.LENGTH_SHORT).show();
                                    }else
                                    {
                                        Toast.makeText(AddAdsActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }

                            }catch (Exception e){}
                        }
                    });
        }catch (Exception e){
            dialog.dismiss();
        }
    }

    private void initMap() {

        fragment = (FragmentMapTouchListener) getSupportFragmentManager().findFragmentById(R.id.map);
        fragment.getMapAsync(this);

    }
    private void CheckPermission()
    {
        if (ActivityCompat.checkSelfPermission(this,fineLocPerm) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{fineLocPerm}, loc_req);
        } else {

            initGoogleApi();
        }
    }
    private void initGoogleApi() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        googleApiClient.connect();
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (googleMap != null) {
            mMap = googleMap;
            mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.maps));
            mMap.setTrafficEnabled(false);
            mMap.setBuildingsEnabled(false);
            mMap.setIndoorEnabled(true);

            mMap.setOnMapClickListener(latLng -> {
                marker.setPosition(latLng);
                lat = latLng.latitude;
                lng = latLng.longitude;
                getGeoData(lat,lng);
            });

            fragment.setListener(() -> binding.scrollView.requestDisallowInterceptTouchEvent(true));

        }
    }

    private void AddMarker(double lat, double lng) {

        this.lat = lat;
        this.lng = lng;


        if (marker == null) {
            marker = mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), zoom));

        } else {
            marker.setPosition(new LatLng(lat, lng));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), zoom));


        }


    }

    private void getGeoData(final double lat, double lng) {

        String location = lat + "," + lng;
        Api.getService("https://maps.googleapis.com/maps/api/")
                .getGeoData(location, lang, getString(R.string.map_api_key))
                .enqueue(new Callback<PlaceGeocodeData>() {
                    @Override
                    public void onResponse(Call<PlaceGeocodeData> call, Response<PlaceGeocodeData> response) {
                        if (response.isSuccessful() && response.body() != null) {


                            if (response.body().getResults().size() > 0) {
                                String address = response.body().getResults().get(0).getFormatted_address().replace("Unnamed Road,", "");

                                binding.edtAddress.setText(address);


                            }
                        } else {

                            try {
                                Log.e("error_code", response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }


                    }

                    @Override
                    public void onFailure(Call<PlaceGeocodeData> call, Throwable t) {
                        try {


                            // Toast.makeText(activity, getString(R.string.something), Toast.LENGTH_LONG).show();
                        } catch (Exception e) {

                        }
                    }
                });
    }

    private void Search(String query) {

        String fields = "id,place_id,name,geometry,formatted_address";
        Api.getService("https://maps.googleapis.com/maps/api/")
                .searchOnMap("textquery", query, fields, lang, getString(R.string.map_api_key))
                .enqueue(new Callback<PlaceMapDetailsData>() {
                    @Override
                    public void onResponse(Call<PlaceMapDetailsData> call, Response<PlaceMapDetailsData> response) {
                        if (response.isSuccessful() && response.body() != null) {


                            if (response.body().getCandidates().size() > 0) {
                                String address = response.body().getCandidates().get(0).getFormatted_address().replace("Unnamed Road,", "");
                                lat = response.body().getCandidates().get(0).getGeometry().getLocation().getLat();
                                lng = response.body().getCandidates().get(0).getGeometry().getLocation().getLng();
                                binding.edtAddress.setText(address);

                                AddMarker(response.body().getCandidates().get(0).getGeometry().getLocation().getLat(), response.body().getCandidates().get(0).getGeometry().getLocation().getLng());
                            }
                        } else {


                            try {
                                Log.e("error_code", response.code() + response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }


                    }

                    @Override
                    public void onFailure(Call<PlaceMapDetailsData> call, Throwable t) {
                        try {


                            Log.e("Error", t.getMessage());
                        } catch (Exception e) {

                        }
                    }
                });
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        initLocationRequest();
    }

    private void initLocationRequest() {
        locationRequest = LocationRequest.create();
        locationRequest.setFastestInterval(1000);
        locationRequest.setInterval(60000);
        LocationSettingsRequest.Builder request = new LocationSettingsRequest.Builder();
        request.addLocationRequest(locationRequest);
        request.setAlwaysShow(false);


        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, request.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(@NonNull LocationSettingsResult locationSettingsResult) {
                Status status = locationSettingsResult.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        startLocationUpdate();
                        break;

                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        try {
                            status.startResolutionForResult(AddAdsActivity.this,100);
                        } catch (IntentSender.SendIntentException e) {
                            e.printStackTrace();
                        }
                        break;

                }
            }
        });

    }

    @Override
    public void onConnectionSuspended(int i) {
        if (googleApiClient!=null)
        {
            googleApiClient.connect();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    @SuppressLint("MissingPermission")
    private void startLocationUpdate()
    {
        locationCallback = new LocationCallback()
        {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                onLocationChanged(locationResult.getLastLocation());
            }
        };
        LocationServices.getFusedLocationProviderClient(this)
                .requestLocationUpdates(locationRequest,locationCallback, Looper.myLooper());
    }

    @Override
    public void onLocationChanged(Location location) {
        lat = location.getLatitude();
        lng = location.getLongitude();
        AddMarker(lat,lng);
        getGeoData(lat,lng);

        if (googleApiClient!=null)
        {
            LocationServices.getFusedLocationProviderClient(this).removeLocationUpdates(locationCallback);
            googleApiClient.disconnect();
            googleApiClient = null;
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == loc_req)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                initGoogleApi();
            }else
            {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100&&resultCode== Activity.RESULT_OK)
        {

            startLocationUpdate();
        }
    }

    @Override
    public void back() {
        finish();
    }




}
