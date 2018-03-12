package com.android.blantik.features.popular_product;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.blantik.R;
import com.android.blantik.base.BaseFragment;
import com.android.blantik.model.PopularProduct;
import com.android.blantik.utils.Consts;
import com.android.blantik.utils.EndlessScrollListener;
import com.android.blantik.utils.Helper;

import java.util.List;

import butterknife.BindView;

/**
 * Created by agustinaindah on 25/07/2017.
 */

public class PopularProductFragment extends BaseFragment implements PopularProductPresenter.View {

    @BindView(R.id.rvHomeProduct)
    RecyclerView rvHomeProduct;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    /* @BindView(R.id.layProgress)
     RelativeLayout layProgress;*/
    @BindView(R.id.layError)
    LinearLayout layError;
    @BindView(R.id.btnError)
    Button btnError;
    @BindView(R.id.txtNoData)
    TextView txtNoData;

    private PopularProductAdapter mAdapter;
    private PopularProductPresenter mPresenter;
    private GridLayoutManager mGridLayoutManager;
    private int lastCount = 0;

    public static PopularProductFragment newInstance() {
        Bundle args = new Bundle();
        PopularProductFragment fragment = new PopularProductFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setView() {
        return R.layout.fragment_product;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new PopularProductPresenterImpl(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.getPopularProduct(Consts.FIRST_PAGE);
        mGridLayoutManager = new GridLayoutManager(getActivity(), 2);
        rvHomeProduct.addOnScrollListener(new EndlessScrollListener(mGridLayoutManager) {
            @Override
            public void onLoadMore(int nextPage) {
                if (lastCount == Consts.LIMIT){
                    mPresenter.getPopularProduct(nextPage);
                }
            }
        });
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String msg) {
        Helper.createAlert(getActivity(), Consts.STR_INFO, msg);
    }

    @Override
    public void notConnect(String msg) {
        layError.setVisibility(View.VISIBLE);
        mPresenter.getPopularProduct(Consts.FIRST_PAGE);
    }

    @Override
    public void showProduct(List<PopularProduct> popularProducts, int page) {
        lastCount = popularProducts.size();

        if(page == Consts.FIRST_PAGE){
            txtNoData.setVisibility((popularProducts.size()==0) ? View.VISIBLE : View.GONE);
            mAdapter = new PopularProductAdapter(popularProducts, getActivity());

            rvHomeProduct.setHasFixedSize(true);
            rvHomeProduct.setLayoutManager(mGridLayoutManager);
            rvHomeProduct.setAdapter(mAdapter);
            rvHomeProduct.setNestedScrollingEnabled(false);

            Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.up_from_bottom);
            rvHomeProduct.startAnimation(animation);
        } else {
            for (PopularProduct popularProduct : popularProducts){
                mAdapter.add(popularProduct);
                mAdapter.notifyDataSetChanged();
            }
        }
    }
}
