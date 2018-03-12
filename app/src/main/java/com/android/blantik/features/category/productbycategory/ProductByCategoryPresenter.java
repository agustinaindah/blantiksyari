package com.android.blantik.features.category.productbycategory;

import com.android.blantik.base.BaseView;
import com.android.blantik.model.ItemDataHewan;

import java.util.List;

/**
 * Created by agustinaindah on 25/07/2017.
 */

public interface ProductByCategoryPresenter {

    interface View extends BaseView {

        void showProduct(List<ItemDataHewan> mData);
    }
    void getProductByCategory(int id);

}
