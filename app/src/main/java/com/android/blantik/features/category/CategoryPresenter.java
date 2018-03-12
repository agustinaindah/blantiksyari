package com.android.blantik.features.category;

import com.android.blantik.base.BaseView;
import com.android.blantik.model.Category;

import java.util.List;

/**
 * Created by agustinaindah on 25/07/2017.
 */

public interface CategoryPresenter {

    interface View extends BaseView {

        void success(List<Category> catProducts);

    }
    void getCategory();
}
