package com.android.blantik.features.category;

import android.content.SharedPreferences;

import com.android.blantik.BlantikApps;
import com.android.blantik.model.BaseResponse;
import com.android.blantik.model.Category;
import com.android.blantik.networks.ApiService;
import com.android.blantik.utils.Consts;
import com.android.blantik.utils.Helper;
import com.android.blantik.utils.ServiceInterface;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by agustinaindah on 25/07/2017.
 */

public class CategoryPresenterImpl implements CategoryPresenter {

    private View mView;
    private SharedPreferences mPref;

    public CategoryPresenterImpl(View mView) {
        this.mView = mView;
    }

    @Override
    public void getCategory() {
        mPref = BlantikApps.getInstance().Prefs();
        final SharedPreferences.Editor edit = mPref.edit();
        String categoriesTemp = mPref.getString(Consts.CATEGORY, null);
        if (categoriesTemp != null) {
            JsonArray categories = Helper.parseToJsonArray(categoriesTemp);
            mView.success(parseCategory(categories));
        }
        BlantikApps.getInstance().service(new ServiceInterface() {
            @Override
            public Call<BaseResponse> callBackResponse(ApiService apiService) {
                return apiService.getCategory();
            }

            @Override
            public void showProgress() {
                mView.showProgress();
            }

            @Override
            public void hideProgress() {
                try {
                    mView.hideProgress();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void responseSuccess(Response<BaseResponse> response) {
                try {
                    String data = Helper.getGsonInstance().toJson(response.body().getData());
                    JsonObject jsonData = Helper.parseToJsonObject(data);
                    JsonArray jsonRes = jsonData.get("results").getAsJsonArray();
                    edit.putString(Consts.CATEGORY, jsonRes.toString()).commit();
                    List<Category> mData = parseCategory(jsonRes);
                    mView.success(mData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void responseFailed(Response<BaseResponse> response) {
                try {
                    JsonObject jsonRes = Helper.parseToJsonObject(response.errorBody().string());
                    mView.showMessage(jsonRes.get("msgsek").getAsString());
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

    private ArrayList<Category> parseCategory(JsonArray categories) {
        ArrayList<Category> result = new ArrayList<Category>();

        //main category
        for (JsonElement elCat : categories) {
            JsonObject cat = elCat.getAsJsonObject();
            ArrayList<Category> categorySub = new ArrayList<Category>();
            JsonArray catArray = new JsonArray();
            if (cat.get("child") != null)
                catArray = cat.get("child").getAsJsonArray();

            //child category
            for (JsonElement elCatSub : catArray) {
                try {
                    JsonObject catSub = elCatSub.getAsJsonObject();
                    categorySub.add(new Category(
                            catSub.get("id").getAsInt(),
                            catSub.get("title").getAsString(),
                            new ArrayList<Category>(),
                            Consts.CAT_CHILD
                    ));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            result.add(new Category(
                    cat.get("id").getAsInt(),
                    cat.get("title").getAsString(),
                    categorySub,
                    Consts.CAT_MAIN
            ));
        }
        return result;

    }
}
