package com.android.blantik.features.contact_us;

import com.android.blantik.base.BaseView;
import com.google.gson.JsonObject;

/**
 * Created by agustinaindah on 04/08/2017.
 */

public interface ContactUsPresenter {

    interface View extends BaseView {
        boolean validate();

        void success (JsonObject jsonRes);
    }

    void postContactUs(JsonObject jsonData);

}

