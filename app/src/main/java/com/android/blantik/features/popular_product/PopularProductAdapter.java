package com.android.blantik.features.popular_product;

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
import com.android.blantik.features.popular_product.detail.DetailPopularProductActivity;
import com.android.blantik.model.PopularProduct;
import com.android.blantik.utils.Consts;
import com.android.blantik.utils.Helper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by agustinaindah on 25/07/2017.
 */

public class PopularProductAdapter extends BaseRecyclerViewAdapter<PopularProduct> {

    public PopularProductAdapter(List<PopularProduct> mData, Context mContext) {
        super(mData, mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_popular_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        renderData(get(position), (ViewHolder) holder);
    }

    private void renderData(final PopularProduct popularProduct, ViewHolder holder) {
        Helper.displayImage(mContext, popularProduct.getImage(), holder.imgItemNewProduct, true);
        holder.txtItemNewProductTitle.setText(popularProduct.getTitle());
        holder.txtItemNewProductLoc.setText(popularProduct.getCity());
        holder.txtItemNewProductPrice.setText("Rp " + Helper.numberFormat(popularProduct.getPrice()).toString());
        holder.layItemProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailPopularProductActivity.class);
                intent.putExtra(Consts.ARG_ID, popularProduct.getProductId());
                intent.putExtra(Consts.ARG_DATA, popularProduct);
                mContext.startActivity(intent);
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.layItemProduct)
        RelativeLayout layItemProduct;
        @BindView(R.id.imgItemNewProduct)
        ImageView imgItemNewProduct;
        @BindView(R.id.txtItemNewProductTitle)
        TextView txtItemNewProductTitle;
        @BindView(R.id.txtItemNewProductLoc)
        TextView txtItemNewProductLoc;
        @BindView(R.id.txtItemNewProductPrice)
        TextView txtItemNewProductPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
