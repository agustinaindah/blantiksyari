package com.android.blantik.features.home.detail;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.blantik.R;
import com.android.blantik.base.BaseActivity;
import com.android.blantik.model.Images2;
import com.android.blantik.model.ItemDataHewan;
import com.android.blantik.model.NewProduct;
import com.android.blantik.utils.CallbackInterface;
import com.android.blantik.utils.Consts;
import com.android.blantik.utils.Helper;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class DetailProductActivity extends BaseActivity implements DetailProductPresenter.View {

    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
   /* @BindView(R.id.imgDetailProduct)
    ImageView imgDetailProduct;*/
    @BindView(R.id.sliderDetailProduct)
    SliderLayout sliderDetailProduct;
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
    /*@BindView(R.id.rvProductDetail)
    RecyclerView rvProductDetail;*/
    @BindView(R.id.expandProductDetail)
    ExpandableTextView expandProductDetail;
    @BindView(R.id.layProgress)
    RelativeLayout layProgress;
    @BindView(R.id.layError)
    LinearLayout layError;
    @BindView(R.id.btnError)
    Button btnError;

    private DetailProductPresenter mPresenter;
    private int id;
    private ItemDataHewan mItemDataHewan;

    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {

        mPresenter = new DetailProductPresenterImpl(this);

        initView();
        initData();
        displayData();
        setupSlider();
    }

    private void initData() {
        id = getIntent().getIntExtra(Consts.ARG_ID, 0);
        mItemDataHewan = (ItemDataHewan) getIntent().getSerializableExtra(Consts.ARG_DATA);
        mPresenter.getProductDetail(id);
    }

    private void displayData() {
        /*Helper.displayImage(this, mItemDataHewan.getImage(), imgDetailProduct);*/
        collapsingToolbarLayout.setTitle(mItemDataHewan.getTitle());
        txtContentTitleProduct.setText(mItemDataHewan.getTitle());
        txtContentPriceProduct.setText("Rp " + Helper.numberFormat(Integer.parseInt(
                mItemDataHewan.getPrice())).toString());
        txtNameSeller.setText(mItemDataHewan.getName());
        txtAddressSeller.setText(mItemDataHewan.getAddressUser());
        txtContactSeller.setText(mItemDataHewan.getPhone());
        expandProductDetailDesc.setText(Html.fromHtml(mItemDataHewan.getDescription()));
        expandProductDetail.setText(Html.fromHtml(mItemDataHewan.getDataHewanText()));
    }

    private void initView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        Helper.createAlert(this, Consts.STR_INFO, msg, true, new CallbackInterface() {
            @Override
            public void callback() {
                mPresenter.getProductDetail(id);
            }
        });
    }

    @Override
    public void notConnect(String msg) {
        layError.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.btnError)
    public void reload(){
        layError.setVisibility(View.GONE);
        mPresenter.getProductDetail(id);
    }

    @Override
    public void success(ItemDataHewan itemDataHewan) {
        mItemDataHewan = itemDataHewan;
    }

    private void setupSlider() {

        for (int i = 0; i < mItemDataHewan.getImages2().size(); i++) {
            Images2 images2 = mItemDataHewan.getImages2().get(i);
            DefaultSliderView sliderView = new DefaultSliderView(this);
            sliderView
                    .image(images2.getThumbnail())
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            sliderDetailProduct.addSlider(sliderView);
        }
        sliderDetailProduct.setPresetTransformer(SliderLayout.Transformer.Default);
        sliderDetailProduct.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderDetailProduct.setCustomAnimation(new DescriptionAnimation());
        sliderDetailProduct.stopAutoCycle();
    }

    @Override
    public void showRelatedProduct(List<NewProduct> newProducts) {

    }
}
