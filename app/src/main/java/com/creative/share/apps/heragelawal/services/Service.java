package com.creative.share.apps.heragelawal.services;


import com.creative.share.apps.heragelawal.models.AdModel;
import com.creative.share.apps.heragelawal.models.AdTypeDataModel;
import com.creative.share.apps.heragelawal.models.MainCategoryDataModel;
import com.creative.share.apps.heragelawal.models.SliderModelData;
import com.creative.share.apps.heragelawal.models.SubSubCategoryModel;
import com.creative.share.apps.heragelawal.models.UserModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Service {

    @FormUrlEncoded
    @POST("api/login/or/register")
    Call<UserModel> login(@Field("mobile_code") String mobile_code,
                          @Field("mobile_number") String mobile_number,
                          @Field("software_type") int software_type
    );


    @FormUrlEncoded
    @POST("api/cofirm-code")
    Call<UserModel> confirmCode(@Field("user_id") int user_id,
                                @Field("confirmation_code") String confirmation_code
    );

    @FormUrlEncoded
    @POST("api/code/send")
    Call<UserModel> resendCode(@Field("user_id") int user_id);


    @GET("api/all-categories")
    Call<MainCategoryDataModel> getMainCategory();


    @FormUrlEncoded
    @POST("api/ads/feature")
    Call<SliderModelData> getSliderData(@Field("user_id") int user_id);


    @FormUrlEncoded
    @POST("api/single-ads/and/more")
    Call<AdModel> getAdDetails(@Field("user_id") int user_id,
                               @Field("ads_id") int ads_id
    );


    @GET("api/all-types")
    Call<AdTypeDataModel> getAdTypes();

    @FormUrlEncoded
    @POST("api/sub-category/children")
    Call<SubSubCategoryModel> getSubSubCategories(@Field("cat_id") int sub_id);


}


