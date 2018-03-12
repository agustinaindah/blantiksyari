package com.android.blantik.features.search;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.blantik.R;
import com.android.blantik.base.BaseActivity;
import com.android.blantik.features.home.ProductAdapter;
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

public class SearchActivity extends BaseActivity implements
        SearchView.OnQueryTextListener,
        SearchPresenter.View{

    @BindView(R.id.rvSearch)
    RecyclerView rvSearch;
    @BindView(R.id.txtNoData)
    TextView txtNoData;
    @BindView(R.id.layProgress)
    RelativeLayout layProgress;
    @BindView(R.id.layError)
    LinearLayout layError;
    @BindView(R.id.btnError)
    Button btnError;

    private MenuItem mSearch;
    private SearchView searchView;
    private String searchInput;
    private SearchPresenter mPresenter;
    private ProductAdapter mAdapter;
    private String mQuery;

    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mPresenter = new SearchPresenterImpl(this);
    }

    @Override
    protected int setView() {
        return R.layout.activity_search;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        mSearch = menu.findItem(R.id.menu_search);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView =
                (SearchView) mSearch.getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        setupSearchView();
        return true;
    }

    private void setupSearchView() {
        EditText search_edit_text = ((EditText)
                searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text));
        search_edit_text.setHint("Insert Keyword");

        searchView.setQuery(searchInput, false);
        searchView.setIconified(false);
        searchView.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        mQuery = query;
        mPresenter.getSearch(query);
        Helper.hideKeyboard(this);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
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
                mPresenter.getSearch(mQuery);
            }
        });
    }

    @OnClick(R.id.btnError)
    public void reload(){
        layError.setVisibility(View.GONE);
        mPresenter.getSearch(mQuery);
    }

    @Override
    public void notConnect(String msg) {
        layError.setVisibility(View.VISIBLE);
    }

    @Override
    public void success(List<ItemDataHewan> mData) {
        mAdapter = new ProductAdapter(mData, this);

        txtNoData.setVisibility((mData.size()==0)? View.VISIBLE : View.GONE);
        rvSearch.setHasFixedSize(true);
        rvSearch.setLayoutManager(new GridLayoutManager(this, 2));
        rvSearch.setAdapter(mAdapter);
        rvSearch.setNestedScrollingEnabled(false);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.up_from_bottom);
        rvSearch.startAnimation(animation);
    }
}
