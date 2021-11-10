package com.poly.lmsapp.commons.base;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.poly.lmsapp.R;

public class BaseActivity extends AppCompatActivity {
    private TextView toolbarTitle;
    private int tbColor = -1;
    private ProgressBar loading;
    private ConstraintLayout parentLoading;
    private Drawable nav;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout();
        setToolBar();
        createView();

    }

    private void setToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbarTitle = findViewById(R.id.toolbar_title);
        loading = findViewById(R.id.loading);
        parentLoading = findViewById(R.id.parent_loading);
        setSupportActionBar(toolbar);
        if (tbColor != -1) toolbarTitle.setTextColor(tbColor);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("");
            nav = toolbar.getNavigationIcon();
            if (nav != null) {
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
        fetchData();
    }

    public void setToolbarTitle(String toolbarTitle) {
        if (toolbarTitle != null)
            this.toolbarTitle.setText(toolbarTitle);
        else this.toolbarTitle.setText("");
    }

    public void showLoading(boolean b) {
        if (parentLoading != null)
            if (b)
                parentLoading.setVisibility(View.VISIBLE);
            else parentLoading.setVisibility(View.GONE);
    }

    public void fetchData() {
    }

    public int getTbColor() {
        return tbColor;
    }

    public void setTbColor(int tbColor) {
        this.tbColor = tbColor;
        if (tbColor != -1) toolbarTitle.setTextColor(tbColor);
        if (nav != null) if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            nav.setTint(tbColor);
        }
    }

    public ConstraintLayout getParentLoading() {
        return parentLoading;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (parentLoading != null)
            if (parentLoading.getVisibility() == View.VISIBLE)
                return true;
        return super.dispatchTouchEvent(ev);
    }
}
