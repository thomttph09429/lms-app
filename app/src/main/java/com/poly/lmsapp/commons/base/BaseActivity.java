package com.poly.lmsapp.commons.base;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.poly.lmsapp.R;
import com.poly.lmsapp.commons.utils.Status;
import com.poly.lmsapp.ui.activity.LoginActivity;

public class BaseActivity extends AppCompatActivity {
    private TextView toolbarTitle;
    private int tbColor = -1;
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
        parentLoading = findViewById(R.id.parent_loading);
        showLoading(false);
        setSupportActionBar(toolbar);
        if (tbColor != -1) toolbarTitle.setTextColor(tbColor);
        if (getSupportActionBar() != null) {
            setShowBack(true);
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

   public void setShowBack(boolean isShow){
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(isShow);
            getSupportActionBar().setDisplayShowHomeEnabled(isShow);
        }

    }

    public  void onFailResponse(Activity activity, String message){
        showLoading(false);
        BaseDialog.showBaseDialog(activity, message, Status.ERROR,null);
    }
    public  void onFailResponse(Activity activity){
        showLoading(false);
        BaseDialog.showBaseDialog(activity, "Đã có lỗi xảy ra!", Status.ERROR,null);
    }

    public void setTbDrawable(int drawable){
        if(getSupportActionBar() != null)
        getSupportActionBar().setBackgroundDrawable(getDrawable(drawable));
    }
}
