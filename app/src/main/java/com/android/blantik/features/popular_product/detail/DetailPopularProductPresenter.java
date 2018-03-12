package com.android.blantik.features.popular_product.detail;

import com.android.blantik.base.BaseView;
import com.android.blantik.model.PopularProduct;

/**
 * Created by agustinaindah on 25/07/2017.
 */

public interface DetailPopularProductPresenter {

    interface View extends BaseView {
        void succes(PopularProduct popularProduct);
    }

    void getProductPopularDetail(int id);
}
