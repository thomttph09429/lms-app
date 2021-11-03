package com.poly.lmsapp.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.poly.lmsapp.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txtFpoly_Hanoi,txtKho_Hoclieu ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();
        initActions();
    }

    private void initActions() {
        txtFpoly_Hanoi.setOnClickListener(this);
        txtKho_Hoclieu.setOnClickListener(this);
    }

    private void initViews() {
        txtFpoly_Hanoi = findViewById(R.id.txtFpoly_Hanoi);
        txtKho_Hoclieu = findViewById(R.id.txtKho_Hoclieu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_settings,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Toast.makeText(this,"Item 1 click",Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtFpoly_Hanoi:
                Toast.makeText(this,"Fploy ha noi",Toast.LENGTH_SHORT).show();
                break;
            case R.id.txtKho_Hoclieu:
//                startActivity(new Intent(this, NganhHocKhoHocLieuActivity.class));
                break;
            default:
                break;
        }

    }
}