package com.android.blantik.features.search;

import com.android.blantik.base.BaseView;
import com.android.blantik.model.ItemDataHewan;

import java.util.List;

/**
 * Created by agustinaindah on 25/07/2017.
 */

public interface SearchPresenter {

    interface View extends BaseView {

        void success(List<ItemDataHewan> mData);

    }
    void getSearch(String search);
}
