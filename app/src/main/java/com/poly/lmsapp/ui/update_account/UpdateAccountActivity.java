package com.poly.lmsapp.ui.update_account;

import android.content.Intent;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.google.android.material.textfield.TextInputEditText;
import com.poly.lmsapp.R;
import com.poly.lmsapp.commons.base.BaseActivity;
import com.poly.lmsapp.commons.base.BaseDialog;
import com.poly.lmsapp.commons.base.BaseDialogListener;
import com.poly.lmsapp.commons.network.Client;
import com.poly.lmsapp.commons.utils.*;
import com.poly.lmsapp.model.BaseResponse;
import com.poly.lmsapp.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateAccountActivity extends BaseActivity {
    private ImageView mIvAvatar;
    private TextInputEditText mEdtName;
    private TextInputEditText mEdtEmail;
    private TextInputEditText mEdtPhoneNumber;
    private TextInputEditText mEdtBirth;
    private Button mBtnUpdate;
    private User user;
    String dataImage = null;
    private ImageView mIvPickAvatar;

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_update_account);
    }

    @Override
    public void createView() {
        setToolbarTitle("Cập nhật tài khoản");

        initView();
        fillData();
        mBtnUpdate.setOnClickListener(view -> fetchData());

        mIvPickAvatar.setOnClickListener(view -> {
            FilePicker.setActivity(UpdateAccountActivity.this);
            FilePicker.setFile(null);
            FilePicker.showImagePicker();
        });
    }

    private void fillData() {
        user = PersonSingleton.getInstance().getUser();
        RenderImage.loadImageNetwork(user.getAvatar(), mIvAvatar);
        mEdtName.setText(user.getName());
        mEdtEmail.setText(user.getEmail());
        mEdtPhoneNumber.setText(user.getPhoneNumber());
        mEdtBirth.setText(user.getBirth());

    }

    private void initView() {
        mIvAvatar = findViewById(R.id.iv_avatar);
        mEdtName = findViewById(R.id.edt_name);
        mEdtEmail = findViewById(R.id.edt_email);
        mEdtPhoneNumber = findViewById(R.id.edt_phone_number);
        mEdtBirth = findViewById(R.id.edt_birth);
        mBtnUpdate = findViewById(R.id.btn_update);    mIvPickAvatar = findViewById(R.id.iv_pick_avatar);

    }

    @Override
    public void fetchData() {
        String avatar = null;

        if (dataImage != null) {
            avatar = user.getAvatar();
        }
        User userTmp = new User(mEdtName.getText().toString(), mEdtEmail.getText().toString(), avatar, mEdtBirth.getText().toString(), mEdtPhoneNumber.getText().toString(), dataImage, user.getId());
        Client.getInstance().updateUser(userTmp).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse baseResponse = response.body();
                if(baseResponse != null && baseResponse.getError().getCode() == 0){
                    BaseDialog.showBaseDialog(UpdateAccountActivity.this, "Cập nhật thông tin thành công", Status.SUCCESS, new BaseDialogListener() {
                        @Override
                        public void confirmListener() {
                            finish();
                            finish();
                        }

                        @Override
                        public void cancelListener() {
                            finish();
                        }
                    });
                }else {
                    onFailResponse(UpdateAccountActivity.this,"Cập nhật thông tin không thành công");
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                onFailResponse(UpdateAccountActivity.this);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        FilePicker.callBackPicker(requestCode,resultCode,data);
        if(FilePicker.getFile() != null){
            RenderImage.loadImageNetwork(FilePicker.getPath(),mIvAvatar);
            dataImage = FilePicker.convertBase64(FilePicker.getFile());
        }
    }
}