package com.poly.lmsapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.poly.lmsapp.R;

public class RegisterActivity extends AppCompatActivity {

    TextView btnDangNhap;
    private EditText edtName, edtPassword, edtRePassWord, edtEmail;
    Button btnDangKy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnDangNhap = findViewById(R.id.btnDangNhap);
        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtRePassWord = findViewById(R.id.edtRePassword);

        btnDangKy = findViewById(R.id.btnDangKy);
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCredentials();
            }
        });

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }

    private void checkCredentials() {
        String name=edtName.getText().toString();
        String email=edtEmail.getText().toString();
        String password=edtPassword.getText().toString();
        String repassword=edtRePassWord.getText().toString();

        if (name.isEmpty() || name.length()<3)
        {
            showError(edtName, "Tên của bạn không hợp lệ!");
        }else if (email.isEmpty() || !email.contains("@"))
        {
            showError(edtEmail, "Email của bạn không hợp lệ");
        }else if (password.isEmpty() || password.length()<7)
        {
            showError(edtPassword, "Mật khẩu của bạn phải từ 8 ký tự trở lên");
        }else if (repassword.isEmpty() || !repassword.equals(password))
        {
            showError(edtRePassWord, "Mật khẩu không trùng khớp");
        }else
        {
            Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_LONG).show();
        }
    }

    private void showError(EditText edt, String s) {
        edt.setError(s);
        edt.requestFocus();
    }
}