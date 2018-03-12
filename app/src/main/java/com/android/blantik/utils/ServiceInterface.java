package com.android.blantik.utils;

import com.android.blantik.model.BaseResponse;
import com.android.blantik.networks.ApiService;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by agustinaindah on 25/07/2017.
 */

public interface ServiceInterface {

    Call<BaseResponse> callBackResponse(ApiService apiService);

    void showProgress();

    void hideProgress();

    void responseSuccess(Response<BaseResponse> response);

    void responseFailed(Response<BaseResponse> response);

    void failed(Throwable t);
}
