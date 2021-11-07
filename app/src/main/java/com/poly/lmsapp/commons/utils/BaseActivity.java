package com.poly.lmsapp.commons.utils;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.opengl.Visibility;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import com.poly.lmsapp.R;

public class BaseActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private int tbColor = -1;
    private ProgressBar loading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout();
        setToolBar();
        createView();

    }

    private void setToolBar() {
        toolbar = findViewById(R.id.toolbar);
        toolbarTitle = findViewById(R.id.toolbar_title);
        loading = findViewById(R.id.loading);
        setSupportActionBar(toolbar);
        if(tbColor != -1) toolbarTitle.setTextColor(tbColor);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("");
            Drawable nav = toolbar.getNavigationIcon();
            if(nav != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    nav.setTint(Color.WHITE);
                }
            }
            toolbar.setNavigationOnClickListener(view -> finish());
        }
    }

    public void setLayout() {
    }

    public void createView() {
    }

    public void setToolbarTitle(String toolbarTitle) {
        this.toolbarTitle.setText(toolbarTitle);
    }

    public  void setShowLoading(int isLoading){
        loading.setVisibility(isLoading);
    }
}
