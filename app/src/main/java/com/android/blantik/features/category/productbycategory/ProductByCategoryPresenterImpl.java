package com.android.blantik.features.category.productbycategory;

import com.android.blantik.BlantikApps;
import com.android.blantik.model.BaseResponse;
import com.android.blantik.model.ItemDataHewan;
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

public class ProductByCategoryPresenterImpl implements ProductByCategoryPresenter {

    private View mView;

    public ProductByCategoryPresenterImpl(View mView) {
        this.mView = mView;
    }

    @Override
    public void getProductByCategory(final int id) {
        BlantikApps.getInstance().service(new ServiceInterface() {
            @Override
            public Call<BaseResponse> callBackResponse(ApiService apiService) {
                return apiService.getProductByCategory(id);
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
                    ArrayList<ItemDataHewan> itemDataHewans = new ArrayList<ItemDataHewan>();
                    for (JsonElement element : jsonRes){
                        ItemDataHewan itemDataHewan =
                                Helper.getGsonInstance().fromJson(element, ItemDataHewan.class);
                        itemDataHewans.add(itemDataHewan);
                    }
                    mView.showProduct(itemDataHewans);
                }catch (Exception e){
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
