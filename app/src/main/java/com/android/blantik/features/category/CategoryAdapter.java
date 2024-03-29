package com.android.blantik.features.category;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.blantik.R;
import com.android.blantik.R;
import com.android.blantik.base.BaseActivity;
import com.android.blantik.features.category.productbycategory.ProductByCategoryActivity;
import com.android.blantik.model.Category;
import com.android.blantik.utils.Consts;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by agustinaindah on 25/07/2017.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private Context context;
    private List<Category> categories;

    public CategoryAdapter(Context context) {
        this.context = context;
    }

    public void updateData(List<Category> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }

    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_categories, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.ViewHolder holder, int position) {
        final Category category = (Category) categories.get(position);

        holder.imgCategorySub.setVisibility(isHaveCategorySub(category) ? View.GONE : View.VISIBLE);
        holder.txtCategoryTitle.setText(category.getCategoryTitle());
        holder.imgCategorySub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoCategorySub(category);
            }
        });

        holder.txtCategoryTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoProductByCategory(category);
            }
        });

        if (category.getType().equals("main")) {
            holder.txtCategoryTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gotoCategorySub(category);
                }
            });
        }
    }

    private void gotoCategorySub(Category category) {
        Fragment fragment = CategoryFragment.newInstance(Consts.CAT_CHILD,
                category);
        FragmentManager fm = ((BaseActivity) context).getSupportFragmentManager();
        ((BaseActivity) context).gotoFragment(fm, fragment, true, category.getType());
    }

    private void gotoProductByCategory(Category category) {
        Intent intent = new Intent(context, ProductByCategoryActivity.class);
        intent.putExtra(Consts.ARG_DATA, category);
        context.startActivity(intent);
    }

    private boolean isHaveCategorySub(Category category) {
        return category.getCategorySub().size() == 0;
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imgCategorySub)
        ImageView imgCategorySub;
        @BindView(R.id.txtCategoryTitle)
        TextView txtCategoryTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
