package com.android.blantik.features.article.detail;

import com.android.blantik.BlantikApps;
import com.android.blantik.model.BaseResponse;
import com.android.blantik.model.ItemContent;
import com.android.blantik.networks.ApiService;
import com.android.blantik.utils.Helper;
import com.android.blantik.utils.ServiceInterface;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by agustinaindah on 04/08/2017.
 */

public class DetailArticlePresenterImpl implements DetailArticlePresenter {

    private View mView;

    public DetailArticlePresenterImpl(View mView) {
        this.mView = mView;
    }

    @Override
    public void getDetailArticle(final int id) {
        BlantikApps.getInstance().service(new ServiceInterface() {
            @Override
            public Call<BaseResponse> callBackResponse(ApiService apiService) {
                return apiService.getDetailArticle(id);
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
                    JsonObject item = jsonRes.get(0).getAsJsonObject();
                    ItemContent itemContent = (new Gson()).fromJson(item, ItemContent.class);
                    mView.success(itemContent);
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
