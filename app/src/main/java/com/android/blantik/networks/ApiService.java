package com.android.blantik.networks;

import com.android.blantik.model.BaseResponse;
import com.google.gson.JsonObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by agustinaindah on 25/07/2017.
 */

public interface ApiService {

    /*-------------------------------------API Product--------------------------------*/

    @GET("API_get_product")
    Call<BaseResponse> getNewProduct(@Query("page") int page);

    @GET("API_single_product")
    Call<BaseResponse> getProductDetails(@Query("product_id") int id);

    @GET("API_get_product_popular")
    Call<BaseResponse> getPopularProduct(@Query("page") int page);

    @GET("API_single_product_popular")
    Call<BaseResponse> getDetailPopular(@Query("product_id") int id);

    /*http://owner99.com/api/API_related_post?product_id=669&cat_id=30&sub_cat_id=288*/

    @GET("API_related_post")
    Call<BaseResponse> getRelatedProduct(@QueryMap Map<String, String> queryRequest);
    /*@GET("API_related_post")
    Call<BaseResponse> getRelatedProduct(@Query("product_id") int id,
                                         @Query("cat_id") int catId,
                                         @Query("sub_cat_id") int subCatId);*/
    /*--------------------------------------------------------------------------------*/

    @GET("API_get_category")
    Call<BaseResponse> getCategory();

    @GET("API_get_product_by_category")
    Call<BaseResponse> getProductByCategory(@Query("cat_id") int id);

    /*---------------------------------------------------------------------------------*/

    @GET("API_get_content")
    Call<BaseResponse> getContent();

    @GET("API_single_content")
    Call<BaseResponse> getDetailArticle(@Query("content_id") int id);

    /*---------------------------------------------------------------------------------*/

    @GET("API_search")
    Call<BaseResponse> getSearch(@Query("search") String search);

    @GET("API_get_city")
    Call<BaseResponse> getCity();

    /*---------------------------------------------------------------------------------*/

    @POST("API_insert_contact")
    Call<BaseResponse> postContactUs(@Body JsonObject jsonData);
}
