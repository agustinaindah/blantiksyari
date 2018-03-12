package com.android.blantik.features.category.productbycategory;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.blantik.R;
import com.android.blantik.base.BaseActivity;
import com.android.blantik.features.home.ProductAdapter;
import com.android.blantik.model.Category;
import com.android.blantik.model.ItemDataHewan;
import com.android.blantik.utils.CallbackInterface;
import com.android.blantik.utils.Consts;
import com.android.blantik.utils.Helper;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by agustinaindah on 25/07/2017.
 */

public class ProductByCategoryActivity extends BaseActivity
        implements ProductByCategoryPresenter.View{

    @BindView(R.id.rvProductByCategory)
    RecyclerView rvProductByCategory;
    @BindView(R.id.layProgress)
    RelativeLayout layProgress;
    @BindView(R.id.txtNoData)
    TextView txtNoData;
    @BindView(R.id.layError)
    LinearLayout layError;
    @BindView(R.id.btnError)
    Button btnError;

    private ProductByCategoryPresenter mPresenter;
    private ProductAdapter mAdapter;
    private Category data;

    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        data = (Category) getIntent().getSerializableExtra(Consts.ARG_DATA);
        setTitle(data.getCategoryTitle());
        mPresenter = new ProductByCategoryPresenterImpl(this);
        mPresenter.getProductByCategory(data.getCategoryId());
    }

    @Override
    protected int setView() {
        return R.layout.activity_product_by_category;
    }

    @Override
    public void showProgress() {
        layProgress.setVisibility(View.VISIBLE);
        txtNoData.setVisibility(View.GONE);
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
                mPresenter.getProductByCategory(data.getCategoryId());
            }
        });
    }


    @OnClick(R.id.btnError)
    public void reload() {
        layError.setVisibility(View.GONE);
        mPresenter.getProductByCategory(data.getCategoryId());
    }

    @Override
    public void notConnect(String msg) {
        layError.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProduct(List<ItemDataHewan> mData) {
        txtNoData.setVisibility((mData.size()==0) ? View.VISIBLE : View.GONE);
        mAdapter = new ProductAdapter(mData,this);
        rvProductByCategory.setHasFixedSize(true);
        rvProductByCategory.setLayoutManager(new GridLayoutManager(this, 2));
        rvProductByCategory.setAdapter(mAdapter);
        rvProductByCategory.setNestedScrollingEnabled(false);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.up_from_bottom);
        rvProductByCategory.startAnimation(animation);
    }
}
