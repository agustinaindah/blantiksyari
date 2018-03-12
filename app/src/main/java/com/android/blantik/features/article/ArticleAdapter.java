package com.android.blantik.features.article;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.blantik.R;
import com.android.blantik.base.BaseRecyclerViewAdapter;
import com.android.blantik.features.article.detail.DetailArticleActivity;
import com.android.blantik.model.ItemContent;
import com.android.blantik.utils.Consts;
import com.android.blantik.utils.Helper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by agustinaindah on 31/07/2017.
 */

public class ArticleAdapter extends BaseRecyclerViewAdapter<ItemContent> {

    public ArticleAdapter(List<ItemContent> mData, Context mContext) {
        super(mData, mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_article, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        renderData(get(position),(ViewHolder) holder);
    }

    private void renderData(final ItemContent itemContent, ViewHolder holder) {
        Helper.displayImage(mContext, itemContent.getImage(), holder.imgItemArticle, true);
        holder.txtItemArticleTitle.setText(itemContent.getTitle());
        holder.txtItemArticleDesc.setText(itemContent.getDescription());
        holder.layItemArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailArticleActivity.class);
                intent.putExtra(Consts.ARG_ID, itemContent.getContentId());
                intent.putExtra(Consts.ARG_DATA, itemContent);
                mContext.startActivity(intent);
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.layItemArticle)
        RelativeLayout layItemArticle;
        @BindView(R.id.imgItemArticle)
        ImageView imgItemArticle;
        @BindView(R.id.txtItemArticleTitle)
        TextView txtItemArticleTitle;
        @BindView(R.id.txtItemArticleDesc)
        TextView txtItemArticleDesc;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
