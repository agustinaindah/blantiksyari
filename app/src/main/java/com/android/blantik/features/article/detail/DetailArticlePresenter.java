package com.android.blantik.features.article.detail;

import com.android.blantik.base.BaseView;
import com.android.blantik.model.ItemContent;

/**
 * Created by agustinaindah on 04/08/2017.
 */

public interface DetailArticlePresenter {

    interface View extends BaseView{
        void success(ItemContent itemContent);
    }

    void getDetailArticle(int id);
}
