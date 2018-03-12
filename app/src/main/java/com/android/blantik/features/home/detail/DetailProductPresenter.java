package com.android.blantik.features.home.detail;

import com.android.blantik.base.BaseView;
import com.android.blantik.model.ItemDataHewan;
import com.android.blantik.model.NewProduct;

import java.util.List;

/**
 * Created by agustinaindah on 25/07/2017.
 */

public interface DetailProductPresenter {

    interface View extends BaseView {

        void success(ItemDataHewan itemDataHewan);

        void showRelatedProduct(List<NewProduct> newProducts);
    }

    void getProductDetail(int id);
}
