package com.android.blantik.features.home;

import com.android.blantik.base.BaseView;
import com.android.blantik.model.ItemDataHewan;

import java.util.List;

/**
 * Created by agustinaindah on 25/07/2017.
 */

public interface ProductPresenter {

    void getProducts(int page);

    interface View extends BaseView {

        void showProduct(List<ItemDataHewan> itemDataHewan, int page);
    }
}
