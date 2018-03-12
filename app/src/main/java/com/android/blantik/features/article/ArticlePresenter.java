package com.android.blantik.features.article;

import com.android.blantik.base.BaseView;
import com.android.blantik.model.ItemContent;

import java.util.List;

/**
 * Created by agustinaindah on 31/07/2017.
 */

public interface ArticlePresenter {

    void getContent(int page);

    interface View extends BaseView {

        void showContent(List<ItemContent> itemContents, int page);
    }

}
