package com.poly.lmsapp.ui.home.account2;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import com.poly.lmsapp.R;
import com.poly.lmsapp.commons.base.BaseDialog;
import com.poly.lmsapp.commons.base.BaseDialogListener;
import com.poly.lmsapp.commons.base.LMSAdapter;
import com.poly.lmsapp.commons.local.LocalManager;
import com.poly.lmsapp.model.MenuDraw;
import com.poly.lmsapp.ui.account.AccountActivity;
import com.poly.lmsapp.ui.activity.SplashActivity;
import com.poly.lmsapp.ui.change_password.ChangePasswordActivity;
import com.poly.lmsapp.ui.update_account.UpdateAccountActivity;

import java.util.ArrayList;

public class AccountMenuAdapter extends LMSAdapter {
    private CardView mContainer;
    private TextView mTvTitle;
    private ImageView mIvIcon;

    public AccountMenuAdapter(ArrayList listData, int layout) {
        super(listData, layout);
    }

    @Override
    public void declareViews(View view, BaseViewHolder holder) {

        mIvIcon = view.findViewById(R.id.iv_icon);

        mContainer = view.findViewById(R.id.container);
        mTvTitle = view.findViewById(R.id.tv_title);
    }

    @Override
    public void bindingViewHolder(BaseViewHolder holder, int position) {
        MenuDraw menu = (MenuDraw) getListData().get(position);
        mTvTitle.setText(menu.getTitle());
        mIvIcon.setImageResource(menu.getIcon());
        mContainer.setOnClickListener(view -> {
            if (position == 0) {
                context.startActivity(new Intent(context, AccountActivity.class));
            } else if (position == 1) {
                context.startActivity(new Intent(context, UpdateAccountActivity.class));
            } else if (position == 2) {
                context.startActivity(new Intent(context, ChangePasswordActivity.class));
            } else if (position == 3) {
                BaseDialog.showBaseDialog((Activity) context, "Xác nhận đăng xuất", new BaseDialogListener() {
                    @Override
                    public void confirmListener() {
                        LocalManager.getInstance(context).clear();
                        ((Activity) context).finish();
                        context.startActivity(new Intent(context, SplashActivity.class));
                    }

                    @Override
                    public void cancelListener() {

                    }
                });
            }
        });
    }
}
