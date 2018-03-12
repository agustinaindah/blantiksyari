package com.android.blantik.features.article.detail;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.blantik.R;
import com.android.blantik.base.BaseActivity;
import com.android.blantik.model.ItemContent;
import com.android.blantik.utils.CallbackInterface;
import com.android.blantik.utils.Consts;
import com.android.blantik.utils.Helper;

import butterknife.BindView;
import butterknife.OnClick;

public class DetailArticleActivity extends BaseActivity implements DetailArticlePresenter.View {

    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.imgDetailArticle)
    ImageView imgDetailArticle;
    /*@BindView(R.id.expandArticleDesc)
    ExpandableTextView expandArticleDesc;*/
    @BindView(R.id.layProgress)
    RelativeLayout layProgress;
    @BindView(R.id.layError)
    LinearLayout layError;
    @BindView(R.id.btnError)
    Button btnError;

    private DetailArticlePresenter mPresenter;
    private int id;
    private ItemContent mItemContent;

    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        mPresenter = new DetailArticlePresenterImpl(this);
        initView();
        initData();
        displayData();
    }

    private void displayData() {
        Helper.displayImage(this, mItemContent.getImage(), imgDetailArticle);
        collapsingToolbarLayout.setTitle(mItemContent.getTitle());
        /*expandArticleDesc.setText(Html.fromHtml(mItemContent.getContent()));*/
    }

    private void initView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initData() {
        id = getIntent().getIntExtra(Consts.ARG_ID, 0);
        mItemContent = (ItemContent) getIntent().getSerializableExtra(Consts.ARG_DATA);
        mPresenter.getDetailArticle(id);
    }

    @Override
    protected int setView() {
        return R.layout.activity_detail_product;
    }

    @Override
    public void showProgress() {
        layProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        layProgress.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String msg) {
        Helper.createAlert(this, "Info", msg, true, new CallbackInterface() {
            @Override
            public void callback() {
                mPresenter.getDetailArticle(id);
            }
        });
    }

    @Override
    public void notConnect(String msg) {
        layError.setVisibility(View.VISIBLE);
    }

    @Override
    public void success(ItemContent itemContent) {
        mItemContent= itemContent;
    }

    @OnClick(R.id.btnError)
    public void reload(){
        layError.setVisibility(View.GONE);
        mPresenter.getDetailArticle(id);
    }
}
