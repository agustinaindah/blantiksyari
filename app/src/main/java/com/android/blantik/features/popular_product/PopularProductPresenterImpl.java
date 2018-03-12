package com.android.blantik.features.popular_product;

import com.android.blantik.BlantikApps;
import com.android.blantik.model.BaseResponse;
import com.android.blantik.model.PopularProduct;
import com.android.blantik.networks.ApiService;
import com.android.blantik.utils.Helper;
import com.android.blantik.utils.ServiceInterface;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by agustinaindah on 25/07/2017.
 */

public class PopularProductPresenterImpl implements PopularProductPresenter {

    private View mView;

    public PopularProductPresenterImpl(View mView) {
        this.mView = mView;
    }

    @Override
    public void getPopularProduct(final int page) {
        BlantikApps.getInstance().service(new ServiceInterface() {
            @Override
            public Call<BaseResponse> callBackResponse(ApiService apiService) {
                return apiService.getPopularProduct(page);
            }

            @Override
            public void showProgress() {
                mView.showProgress();
            }

            @Override
            public void hideProgress() {
                mView.hideProgress();
            }

            @Override
            public void responseSuccess(Response<BaseResponse> response) {
                try {
                    String data = Helper.getGsonInstance().toJson(response.body().getData());
                    JsonObject jsonData = Helper.parseToJsonObject(data);
                    JsonArray jsonRes = jsonData.get("results").getAsJsonArray();
                    ArrayList<PopularProduct> popularProducts = new ArrayList<PopularProduct>();
                    for (JsonElement element : jsonRes){
                        PopularProduct popularProduct =
                                Helper.getGsonInstance().fromJson(element, PopularProduct.class);
                        popularProducts.add(popularProduct);
                    }
                    mView.showProduct(popularProducts, page);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void responseFailed(Response<BaseResponse> response) {
                try {
                    JsonObject jsonRes = Helper.parseToJsonObject(response.errorBody().string());
                    mView.showMessage(jsonRes.get("msg").getAsString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable t) {
                mView.notConnect(t.getLocalizedMessage());
            }
        });
    }
}
