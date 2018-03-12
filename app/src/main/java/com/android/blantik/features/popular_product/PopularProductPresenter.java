package com.android.blantik.features.popular_product;

import com.android.blantik.base.BaseView;
import com.android.blantik.model.PopularProduct;

import java.util.List;

/**
 * Created by agustinaindah on 25/07/2017.
 */

public interface PopularProductPresenter {

    void getPopularProduct(int page);

    interface View extends BaseView {

        void showProduct(List<PopularProduct> popularProducts, int page);
    }
}
