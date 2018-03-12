package com.android.blantik;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.multidex.MultiDex;

import com.android.blantik.model.BaseResponse;
import com.android.blantik.networks.ApiService;
import com.android.blantik.utils.Consts;
import com.android.blantik.utils.ServiceInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by agustinaindah on 25/07/2017.
 */

public class BlantikApps extends Application {

    private static BlantikApps ourInstance;
    /**
     * @return basic auth
     */
    /*public String getBasicAuth() {
        return Helper.basicAuth(Consts.BASIC_USERNAME, Consts.BASIC_PASSWORD);
    }*/

    public Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    private Call<BaseResponse> mRequest = null;

    public static BlantikApps getInstance() {
        return ourInstance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ourInstance = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ourInstance = null;
    }

    /**
     * get SharedPreferences
     *
     * @return SharedPreferences
     */
    public SharedPreferences Prefs() {
        return getSharedPreferences(getPackageName(), MODE_PRIVATE);
    }

    /**
     * @return String base_url
     */
    public String getBaseUrl() {
        return isLogin() ? getBaseUrlMember() : getString(R.string.base_url);
    }

    /**
     * @return String base_url /web
     */
    public String getBaseUrlWeb() {
        return getString(R.string.base_url);
    }

    /**
     * @return String base_url /member
     */
    public String getBaseUrlMember() {
        return getString(R.string.base_url);
    }

    /**
     * @return String X-API-KEY value
     */
    public String getApiKey() {
        return getString(R.string.X_API_KEY);
    }

    /**
     * @return String email from preferences
     */
   /* public String getEmail() {
        return Prefs().getString(Consts.EMAIL, null);
    }*/

    /**
     * @return String token from preferences
     */
    public String getToken() {
        return Prefs().getString(Consts.TOKEN, null);
    }

    /**
     * @return boolean login
     */
    public boolean isLogin() {
        return (getToken() != null);
    }

   /* public int getSaldo() {
        return Prefs().getInt(Consts.SALDO, 0);
    }

    public int getPoint(){
        return Prefs().getInt(Consts.POINT, 0);
    }*/

    /**
     * method for get DataManager instance
     *
     * @return DataManager
     */
    /*public DataManager getmDataManager() {
        return mDataManager;
    }*/

    /**
     * logout | remove token
     */
    public void logout() {
        //save banner again
        /*String banner = Prefs().getString(Consts.BANNER, "");
        SharedPreferences.Editor edit = Prefs().edit();
        edit.clear().commit();
        edit.putBoolean(Consts.FIRST_RUN, false).commit();
        edit.putString(Consts.BANNER, banner).commit();*/
    }

    public Retrofit retrofit() {
        return new Retrofit.Builder()
                .baseUrl(BlantikApps.getInstance().getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder()
                        .addInterceptor(new HttpLoggingInterceptor()
                                .setLevel(HttpLoggingInterceptor.Level.BODY))
                        .addInterceptor(new Interceptor() {
                            @Override
                            public okhttp3.Response intercept(Chain chain) throws IOException {

                                Request ori = chain.request();

                                Request.Builder reqBuilder = ori.newBuilder()
                                        /*.addHeader(Consts.X_API_KEY,
                                                Product.getInstance().getApiKey())
                                        .addHeader("Authorization", "Basic YWRtaW5mZHY6ZGlnaXRhbG1pbmQxMjM0NQ==")*/
                                        .addHeader("Content-Type", "application/json");

                                /*String token = Owner99.getInstance().getToken();

                                if (token != null) {
                                    reqBuilder.addHeader(Consts.TOKEN, token);
                                }*/

                                Request req = reqBuilder.build();

                                return chain.proceed(req);
                            }
                        }).build()
                ).build();
    }

    public void service(final ServiceInterface callApiService) {
        callApiService.showProgress();
        ApiService apiService = retrofit().create(ApiService.class);
        mRequest = callApiService.callBackResponse(apiService);
        mRequest.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (!response.isSuccessful()) {
                    callApiService.responseFailed(response);
                    callApiService.hideProgress();
                    return;
                }
                if (response.body().equals(null)) {
                    return;
                } else {
                    callApiService.responseSuccess(response);
                    callApiService.hideProgress();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                callApiService.failed(t);
                callApiService.hideProgress();
            }
        });

    }

    public Call<BaseResponse> getRequest() {
        return mRequest;
    }
}
