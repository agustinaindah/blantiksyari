package com.android.blantik.features.category;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.blantik.R;
import com.android.blantik.base.BaseFragment;
import com.android.blantik.model.Category;
import com.android.blantik.utils.Consts;
import com.android.blantik.utils.Helper;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by agustinaindah on 25/07/2017.
 */

public class CategoryFragment extends BaseFragment implements CategoryPresenter.View{

    @BindView(R.id.rvFragmentCategory)
    RecyclerView rvFragmentCategory;
    @BindView(R.id.layProgress)
    RelativeLayout layProgress;
    @BindView(R.id.layError)
    LinearLayout layError;

    private CategoryPresenter mPresenter;
    private String mType;

    public static CategoryFragment newInstance(String type, Category category) {
        Bundle args = new Bundle();
        args.putString(Consts.ARG_TYPE, type);
        args.putSerializable(Consts.ARG_DATA, category);
        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setView() {
        return R.layout.fragment_categories;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null){
            mPresenter = new CategoryPresenterImpl(this);
            Bundle args = getArguments();
            if (args !=null){
                mType = args.getString(Consts.ARG_TYPE);
                if (mType.equals(Consts.CAT_MAIN)){
                    mPresenter.getCategory();
                } else {
                    Category cat = (Category) args.getSerializable(Consts.ARG_DATA);
                    CategoryAdapter adapter = new CategoryAdapter(getActivity());
                    adapter.updateData(cat.getCategorySub());
                    rvFragmentCategory.setAdapter(adapter);
                    if (layProgress.isShown()){
                        layProgress.setVisibility(View.GONE);
                    }
                }
            }
            setupRecycleView();
        }
    }
    private void setupRecycleView() {
        rvFragmentCategory.setHasFixedSize(true);
        rvFragmentCategory.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvFragmentCategory.setNestedScrollingEnabled(false);
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
        rvFragmentCategory.addItemDecoration(itemDecoration);
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
        Helper.createAlert(getActivity(), Consts.STR_INFO, msg);
    }

    @Override
    public void notConnect(String msg) {
        rvFragmentCategory.setVisibility(View.GONE);
        layError.setVisibility(View.VISIBLE);
    }

    @Override
    public void success(List<Category> catProducts) {
        CategoryAdapter adapter = new CategoryAdapter(getActivity());
        adapter.updateData(catProducts);
        rvFragmentCategory.setAdapter(adapter);
    }

    @OnClick(R.id.btnError)
    public void reload(){
        layError.setVisibility(View.GONE);
        mPresenter.getCategory();
        rvFragmentCategory.setVisibility(View.VISIBLE);
    }
}
