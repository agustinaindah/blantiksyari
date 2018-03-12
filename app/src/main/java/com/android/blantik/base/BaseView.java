package com.android.blantik.base;

/**
 * Created by agustinaindah on 25/07/2017.
 */

public interface BaseView {

    void showProgress();

    void hideProgress();

    void showMessage(String msg);

    void notConnect(String msg);
}
