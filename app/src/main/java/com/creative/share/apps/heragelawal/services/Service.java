package com.creative.share.apps.heragelawal.services;


import com.creative.share.apps.heragelawal.models.AboutModel;
import com.creative.share.apps.heragelawal.models.AdDataModel;
import com.creative.share.apps.heragelawal.models.AdModel;
import com.creative.share.apps.heragelawal.models.AdTypeDataModel;
import com.creative.share.apps.heragelawal.models.CompanyAdDataModel;
import com.creative.share.apps.heragelawal.models.CompanyDataModel;
import com.creative.share.apps.heragelawal.models.FavoriteDataModel;
import com.creative.share.apps.heragelawal.models.MainCategoryDataModel;
import com.creative.share.apps.heragelawal.models.PlaceGeocodeData;
import com.creative.share.apps.heragelawal.models.PlaceMapDetailsData;
import com.creative.share.apps.heragelawal.models.ReportReasonDataModel;
import com.creative.share.apps.heragelawal.models.SearchDataModel;
import com.creative.share.apps.heragelawal.models.SliderModelData;
import com.creative.share.apps.heragelawal.models.SubCategoryDataModel;
import com.creative.share.apps.heragelawal.models.SubSubCategoryModel;
import com.creative.share.apps.heragelawal.models.UserModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Service {

    @GET("place/findplacefromtext/json")
    Call<PlaceMapDetailsData> searchOnMap(@Query(value = "inputtype") String inputtype,
                                          @Query(value = "input") String input,
                                          @Query(value = "fields") String fields,
                                          @Query(value = "language") String language,
                                          @Query(value = "key") String key
    );

    @GET("geocode/json")
    Call<PlaceGeocodeData> getGeoData(@Query(value = "latlng") String latlng,
                                      @Query(value = "language") String language,
                                      @Query(value = "key") String key);


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

    @FormUrlEncoded
    @POST("api/ads/category/or/type")
    Call<AdDataModel> getAdsByType(@Field("cat_id") int cat_id,
                                   @Field("type_id") int type_id,
                                   @Field("user_id") int user_id,
                                   @Field("page") int page
    );

    @GET("api/sub-categories")
    Call<SubCategoryDataModel> getAllSubCategory();


    @FormUrlEncoded
    @POST("api/ads/search")
    Call<SearchDataModel> searchByName(@Field("name") String name,
                                       @Field("cat_id") int cat_id,
                                       @Field("user_id") int user_id);

    @FormUrlEncoded
    @POST("api/companies/subcategory")
    Call<CompanyDataModel> getCompanies(@Field("sub_cat_id") int sub_cat_id, @Field("page") int page);

    @FormUrlEncoded
    @POST("api/company/name")
    Call<CompanyDataModel> searchCompanyByName(@Field("name") String companyName,
                                               @Field("page") int page);


    @FormUrlEncoded
    @POST("api/myads")
    Call<CompanyAdDataModel> getCompanyAds(@Field("company_id") int company_id,
                                           @Field("page") int page);


    @FormUrlEncoded
    @POST("api/my-favorite-ads")
    Call<FavoriteDataModel> getFavoriteAds(@Field("user_id") int user_id,
                                           @Field("page") int page);


    @GET("api/app/info")
    Call<AboutModel> aboutApp();


    @FormUrlEncoded
    @POST("api/favourite/action")
    Call<ResponseBody> addFavoriteAds(@Field("user_id") int user_id,
                                      @Field("advertisement_id") int advertisement_id);

    @FormUrlEncoded
    @POST("api/like/action")
    Call<ResponseBody> addLikesAds(@Field("user_id") int user_id,
                                   @Field("advertisement_id") int advertisement_id);

    @FormUrlEncoded
    @POST("api/report/action")
    Call<ResponseBody> addReportAds(@Field("user_id") int user_id,
                                    @Field("advertisement_id") int advertisement_id,
                                    @Field("report_reason_id") int report_reason_id);

    @GET("api/all-report-reasons")
    Call<ReportReasonDataModel> getReportReasons();

    @FormUrlEncoded
    @POST("api/comment/add")
    Call<ResponseBody> addComment(@Field("user_id") int user_id,
                                  @Field("advertisement_id") int advertisement_id,
                                  @Field("comment") String comment);

    @FormUrlEncoded
    @POST("api/company/add")
    Call<ResponseBody> addCompany(@Field("name") String name,
                                  @Field("phone") String phone,
                                  @Field("email") String email,
                                  @Field("details") String details,
                                  @Field("latitude") double latitude,
                                  @Field("longitude") double longitude,
                                  @Field("category_id") int category_id,
                                  @Field("user_id") int user_id,
                                  @Field("address") String address
                                  );

}


