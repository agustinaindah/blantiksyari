package com.android.blantik.features.home;

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
import com.android.blantik.features.home.detail.DetailProductActivity;
import com.android.blantik.model.ItemDataHewan;
import com.android.blantik.utils.Consts;
import com.android.blantik.utils.Helper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by agustinaindah on 25/07/2017.
 */

public class ProductAdapter extends BaseRecyclerViewAdapter<ItemDataHewan> {

    public ProductAdapter(List<ItemDataHewan> mData, Context mContext) {
        super(mData, mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        renderData(get(position), (ProductAdapter.ViewHolder) holder);
    }

    private void renderData(final ItemDataHewan itemDataHewan, ViewHolder holder) {
        Helper.displayImage(mContext, itemDataHewan.getImage(), holder.imgItemNewProduct, true);
        holder.txtItemNewProductTitle.setText(itemDataHewan.getTitle());
        //holder.txtItemNewProductPrice.setText("Rp " + Helper.numberFormat(itemDataHewan.getPrice()).toString());
        holder.layItemProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailProductActivity.class);
                intent.putExtra(Consts.ARG_ID, itemDataHewan.getProductId());
                intent.putExtra(Consts.ARG_DATA, itemDataHewan);
                mContext.startActivity(intent);
            }
        });
        holder.layItemProductPromo.setVisibility(
                (itemDataHewan.getIsVip().equals(Consts.STR_YA)) ?
                        View.VISIBLE : View.GONE
        );

        if (itemDataHewan.getPrice().equals("")){
            holder.txtItemNewProductPrice.setText("Hubungi Penjual");
        } else {
            holder.txtItemNewProductPrice.setText("Rp " + Helper.numberFormat(Integer.parseInt
                    (itemDataHewan.getPrice())).toString());
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.layItemProduct)
        RelativeLayout layItemProduct;
        @BindView(R.id.imgItemNewProduct)
        ImageView imgItemNewProduct;
        @BindView(R.id.txtItemNewProductTitle)
        TextView txtItemNewProductTitle;
        @BindView(R.id.txtItemNewProductPrice)
        TextView txtItemNewProductPrice;
        /* @BindView(R.id.imgIsVip)
         ImageView imgIsVip;*/
        @BindView(R.id.layItemProductPromo)
        RelativeLayout layItemProductPromo;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
