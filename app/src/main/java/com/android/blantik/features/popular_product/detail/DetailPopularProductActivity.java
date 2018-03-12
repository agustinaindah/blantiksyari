package com.android.blantik.features.popular_product.detail;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.blantik.R;
import com.android.blantik.base.BaseActivity;
import com.android.blantik.model.PopularProduct;
import com.android.blantik.utils.CallbackInterface;
import com.android.blantik.utils.Consts;
import com.android.blantik.utils.Helper;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import butterknife.BindView;
import butterknife.OnClick;


public class DetailPopularProductActivity extends BaseActivity implements DetailPopularProductPresenter.View{

    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.imgDetailProduct)
    ImageView imgDetailProduct;
    @BindView(R.id.txtContentTitleProduct)
    TextView txtContentTitleProduct;
    @BindView(R.id.txtContentPriceProduct)
    TextView txtContentPriceProduct;
    @BindView(R.id.txtNameSeller)
    TextView txtNameSeller;
    @BindView(R.id.txtAddressSeller)
    TextView txtAddressSeller;
    @BindView(R.id.txtContactSeller)
    TextView txtContactSeller;
    @BindView(R.id.expandProductDetailDesc)
    ExpandableTextView expandProductDetailDesc;
    @BindView(R.id.layProgress)
    RelativeLayout layProgress;
    @BindView(R.id.layError)
    LinearLayout layError;
    @BindView(R.id.btnError)
    Button btnError;

    private DetailPopularProductPresenter mPresenter;
    private int id;
    private PopularProduct mPopularProduct;

    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        mPresenter = new DetailPopularProductPresenterImpl(this);
        initView();
        initData();
        displayData();
    }

    private void displayData() {
        Helper.displayImage(this, mPopularProduct.getImage(), imgDetailProduct);
        collapsingToolbarLayout.setTitle(mPopularProduct.getTitle());
        txtContentTitleProduct.setText(mPopularProduct.getTitle());
        txtContentPriceProduct.setText("Rp " + Helper.numberFormat(mPopularProduct.getPrice()).toString());
        txtNameSeller.setText(mPopularProduct.getName());
        txtAddressSeller.setText(mPopularProduct.getAddressUser());
        txtContactSeller.setText(mPopularProduct.getPhone());
        expandProductDetailDesc.setText(Html.fromHtml(mPopularProduct.getDescription()));
    }

    private void initView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initData() {
        id = getIntent().getIntExtra(Consts.ARG_ID, 0);
        mPopularProduct = (PopularProduct) getIntent().getSerializableExtra(Consts.ARG_DATA);
        mPresenter.getProductPopularDetail(id);
    }

    @Override
    protected int setView() {
        return R.layout.activity_detail_popular_product;
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
        Helper.createAlert(this, Consts.STR_INFO, msg, true, new CallbackInterface() {
            @Override
            public void callback() {
                mPresenter.getProductPopularDetail(id);
            }
        });
    }

    @Override
    public void notConnect(String msg) {
        layError.setVisibility(View.VISIBLE);
    }

    @Override
    public void succes(PopularProduct popularProduct) {
        mPopularProduct = popularProduct;
    }

    @OnClick(R.id.btnError)
    public void reload(){
        layError.setVisibility(View.GONE);
        mPresenter.getProductPopularDetail(id);
    }
}
