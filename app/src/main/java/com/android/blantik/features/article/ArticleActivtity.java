package com.android.blantik.features.article;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
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
import com.android.blantik.model.ItemContent;
import com.android.blantik.utils.CallbackInterface;
import com.android.blantik.utils.Consts;
import com.android.blantik.utils.EndlessScrollListener;
import com.android.blantik.utils.Helper;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by agustinaindah on 04/08/2017.
 */

public class ArticleActivtity extends BaseActivity implements ArticlePresenter.View{

    @BindView(R.id.rvListArticle)
    RecyclerView rvListArticle;
    @BindView(R.id.layProgress)
    RelativeLayout layProgress;
    @BindView(R.id.layError)
    LinearLayout layError;
    @BindView(R.id.btnError)
    Button btnError;
    @BindView(R.id.txtNoData)
    TextView txtNoData;

    private ArticlePresenter mPresenter;
    private int lastCount = 0;
    private LinearLayoutManager linearLayoutManager;
    private ArticleAdapter mAdapter;

    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Article");

        mPresenter = new ArticlePresenterImpl(this);
        mPresenter.getContent(Consts.FIRST_PAGE);

        linearLayoutManager = new LinearLayoutManager(this);
        rvListArticle.addOnScrollListener(new EndlessScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int nextPage) {
                if (lastCount == Consts.LIMIT){
                    mPresenter.getContent(Consts.FIRST_PAGE);
                }
            }
        });
    }

    @Override
    protected int setView() {
        return R.layout.activity_list_article;
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
                mPresenter.getContent(Consts.FIRST_PAGE);
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
        mPresenter.getContent(Consts.FIRST_PAGE);
    }

    @Override
    public void showContent(List<ItemContent> itemContents, int page) {
        lastCount = itemContents.size();

        if (page == Consts.FIRST_PAGE){
            txtNoData.setVisibility((itemContents.size()==0) ? View.VISIBLE : View.GONE);

            mAdapter = new ArticleAdapter(itemContents, this);

            rvListArticle.setHasFixedSize(true);
            rvListArticle.setLayoutManager(linearLayoutManager);
            rvListArticle.setAdapter(mAdapter);
            rvListArticle.setNestedScrollingEnabled(false);

            Animation animation = AnimationUtils.loadAnimation(this, R.anim.up_from_bottom);
            rvListArticle.startAnimation(animation);
        } else {
            for (ItemContent item : itemContents){
                mAdapter.add(item);
                mAdapter.notifyDataSetChanged();
            }
        }
    }
}
