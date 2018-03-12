package com.android.blantik.features.category;

import android.os.Bundle;
import android.view.MenuItem;

import com.android.blantik.R;
import com.android.blantik.base.BaseActivity;
import com.android.blantik.utils.Consts;

/**
 * Created by agustinaindah on 25/07/2017.
 */

public class CategoryActivity extends BaseActivity {

    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        gotoFragment(getSupportFragmentManager(), CategoryFragment.newInstance(Consts.CAT_MAIN, null));
    }

    @Override
    protected int setView() {
        return R.layout.activity_categories;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                super.onBackPressed();
                break;
        }
        return true;
    }
}
