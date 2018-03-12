package com.android.blantik.features.contact_us;

import android.os.Bundle;
import android.view.MenuItem;

import com.android.blantik.R;
import com.android.blantik.base.BaseActivity;

/**
 * Created by agustinaindah on 04/08/2017.
 */

public class ContactUsActivity extends BaseActivity {

    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        gotoFragment(getSupportFragmentManager(), ContactUsFragment.newInstance());
    }

    @Override
    protected int setView() {
        return R.layout.activity_form_contact;
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
