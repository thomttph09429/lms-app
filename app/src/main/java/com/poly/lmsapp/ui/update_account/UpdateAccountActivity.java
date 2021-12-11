package com.poly.lmsapp.ui.update_account;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.view.View;
import android.widget.*;
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
import com.poly.lmsapp.ui.home.account2.AccountMenuFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class UpdateAccountActivity extends BaseActivity {
    private ImageView mIvAvatar;
    private TextInputEditText mEdtName;
    private TextInputEditText mEdtEmail;
    private TextInputEditText mEdtPhoneNumber;
    private TextInputEditText mEdtBirth;
    private Button mBtnUpdate;
    private User user;
    private String dataImage = null;
    private String gender = null;
    private ImageView mIvPickAvatar;
    private Spinner mSpnGender;
    private TextInputEditText mEdtAddress;


    @Override
    public void setLayout() {
        setContentView(R.layout.activity_update_account);
    }

    @Override
    public void createView() {
        setToolbarTitle("Cập nhật tài khoản");

        initView();
        user = PersonSingleton.getInstance().getUser();
        mBtnUpdate.setOnClickListener(view -> fetchData());

        mIvPickAvatar.setOnClickListener(view -> {
            FilePicker.setActivity(UpdateAccountActivity.this);
            FilePicker.setFile(null);
            FilePicker.showImagePicker();
        });
        Date date = DateTimeUtils.parseDate(user.getBirth(), DateTimeUtils.BIRTH_DATE);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        if (date != null) {
            year = date.getYear();
            month = date.getMonth();
            day = date.getDay();
        }
        int finalDay = day;
        int finalMonth = month;
        int finalYear = year;
        mEdtBirth.setOnClickListener(view -> {
            new DatePickerDialog(UpdateAccountActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                }
            }, finalYear, finalMonth, finalDay);
        });
        ArrayList<String> listGender = new ArrayList<>();
        listGender.add("Nam");
        listGender.add("Nữ");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                listGender);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.mSpnGender.setAdapter(adapter);
        mSpnGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                gender = listGender.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        fillData();
    }

    private void fillData() {

        RenderImage.loadImageNetwork(user.getAvatar(), mIvAvatar);
        mEdtName.setText(user.getName());
        mEdtEmail.setText(user.getEmail());
        mEdtPhoneNumber.setText(user.getPhoneNumber());
        mEdtBirth.setText(user.getBirth());
        mEdtAddress.setText(user.getAddress());
        if (user.getGender() != null) {
            if (user.getGender().equalsIgnoreCase("Nam")) {
                mSpnGender.setSelection(0);
            } else {
                mSpnGender.setSelection(1);
            }
        }


    }

    private void initView() {
        mIvAvatar = findViewById(R.id.iv_avatar);
        mEdtName = findViewById(R.id.edt_name);
        mEdtEmail = findViewById(R.id.edt_email);
        mEdtPhoneNumber = findViewById(R.id.edt_phone_number);
        mEdtBirth = findViewById(R.id.edt_birth);
        mBtnUpdate = findViewById(R.id.btn_update);
        mIvPickAvatar = findViewById(R.id.iv_pick_avatar);
        mSpnGender = findViewById(R.id.spn_gender);
        mEdtAddress = findViewById(R.id.edt_address);

    }

    @Override
    public void fetchData() {
        showLoading(true);
        String avatar = null;

        if (dataImage == null) {
            avatar = user.getAvatar();
        }
        gender = (String) mSpnGender.getSelectedItem();
        User userTmp = new User(mEdtName.getText().toString(), mEdtEmail.getText().toString(), avatar, mEdtAddress.getText().toString(), mEdtBirth.getText().toString(), mEdtPhoneNumber.getText().toString(), dataImage, user.getId(), user.getIdGroup(), gender, user.getChuyenNganhId(), user.getKiHocId());
        Client.getInstance().updateUser(userTmp).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse baseResponse = response.body();
                showLoading(false);
                if (baseResponse != null && baseResponse.getError().getCode() == 0) {
                    Client.getInstance().getUserInfo().enqueue(new Callback<BaseResponse>() {
                        @Override
                        public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                            BaseResponse baseResponse1 = response.body();
                            if (baseResponse1 != null && baseResponse1.getError().getCode() == 0) {
                                User user = (User) Utils.jsonDecode(baseResponse1.getData(), User.class);
                                PersonSingleton.getInstance().setUser(user);
                                AccountMenuFragment.getInstance().getTvName().setText(mEdtName.getText().toString());
                                if (dataImage != null) {
                                    RenderImage.loadImageUri(FilePicker.getUploadFileUri(), AccountMenuFragment.getInstance().getmIvAvatar());
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<BaseResponse> call, Throwable t) {

                        }
                    });


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
                } else {
                    onFailResponse(UpdateAccountActivity.this, "Cập nhật thông tin không thành công");
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
        FilePicker.callBackPicker(requestCode, resultCode, data);
        if (FilePicker.getFile() != null) {
            RenderImage.loadImageUri(FilePicker.getUploadFileUri(), mIvAvatar);
            dataImage = FilePicker.convertBase64(FilePicker.getFile());
        }
    }
}