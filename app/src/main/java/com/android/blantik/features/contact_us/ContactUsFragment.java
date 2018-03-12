package com.android.blantik.features.contact_us;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.blantik.R;
import com.android.blantik.base.BaseFragment;
import com.android.blantik.utils.CallbackInterface;
import com.android.blantik.utils.Helper;
import com.google.gson.JsonObject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by agustinaindah on 04/08/2017.
 */

public class ContactUsFragment extends BaseFragment implements ContactUsPresenter.View{

    @BindView(R.id.edtNama)
    EditText edtNama;
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtContent)
    EditText edtContent;
    @BindView(R.id.btnSave)
    Button btnSave;

    private ProgressDialog progressDialog;
    private ContactUsPresenter mPresenter;

    public static ContactUsFragment newInstance() {
        Bundle args = new Bundle();
        ContactUsFragment fragment = new ContactUsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setView() {
        return R.layout.fragment_form_contact_us;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initProgress();
        mPresenter = new ContactUsPresenterImpl(this);
    }

    private void initProgress() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
    }

    @OnClick(R.id.btnSave)
    public void sendContact(View view){
        mPresenter.postContactUs(getInput());
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showMessage(String msg) {
        Helper.createAlert(getActivity(),"Info", msg);
    }

    @Override
    public void notConnect(String msg) {
        Helper.createAlert(getActivity(), "Info", "Tidak ada jaringan");
    }

    @Override
    public boolean validate() {
        edtNama.setError(null);
        edtEmail.setError(null);
        edtContent.setError(null);

        boolean cancel = false;
        View focus = null;
        if (TextUtils.isEmpty(getInput().get("name").getAsString())) {
            edtNama.setError("harus diisi");
            focus = edtNama;
            cancel = true;
        }
        if (TextUtils.isEmpty(getInput().get("email").getAsString())) {
            edtEmail.setError("harus diisi");
            focus = edtEmail;
            cancel = true;
        } else if (!Helper.isEmail(getInput().get("email").getAsString())) {
            edtEmail.setError("Tidak valid");
            focus = edtEmail;
            cancel = true;
        }
        if (TextUtils.isEmpty(getInput().get("message").getAsString())) {
            edtContent.setError("harus diisi");
            focus = edtContent;
            cancel = true;
        }
        if (cancel) {
            focus.requestFocus();
        }
        return cancel;
    }

    @Override
    public void success(JsonObject jsonRes) {
        Helper.createAlert(getActivity(), "Info", "Berhasil Terkirim", false,
                new CallbackInterface() {
                    @Override
                    public void callback() {
                        getActivity().onBackPressed();
                    }
                });
    }

    private JsonObject getInput() {
        JsonObject jsonInput = new JsonObject();
        try {
            jsonInput.addProperty("name", edtNama.getText().toString());
            jsonInput.addProperty("email", edtEmail.getText().toString());
            jsonInput.addProperty("message", edtContent.getText().toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonInput;
    }
}
