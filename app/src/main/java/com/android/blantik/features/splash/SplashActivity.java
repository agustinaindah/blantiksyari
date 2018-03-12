package com.android.blantik.features.splash;

import android.content.Intent;
import android.os.Bundle;

import com.android.blantik.R;
import com.android.blantik.base.BaseActivity;
import com.android.blantik.features.MainActivity;

/**
 * Created by agustinaindah on 25/07/2017.
 */

public class SplashActivity extends BaseActivity{
    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        android.os.Handler handler = new android.os.Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                next();
            }
        }, 2500);
    }
    private void next() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected int setView() {
        return R.layout.activity_splashscreen;
    }
}
