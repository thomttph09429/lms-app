package com.poly.lmsapp.commons.base;

import android.app.Activity;
import android.app.Dialog;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.poly.lmsapp.R;
import com.poly.lmsapp.commons.utils.Status;


public class BaseDialog {

    static public void showBaseDialog(Activity activity, String msg, Status status,BaseDialogListener baseDialogListener) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog);

        TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
        text.setText(msg);
        Button mBtnDialogCancel = dialog.findViewById(R.id.btn_dialog_cancel);
        mBtnDialogCancel.setVisibility(View.GONE);
        Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if(baseDialogListener != null){
                    Log.d("AAAAAAAAAAAAAAAA", "onClick: Listener is not null");
                    baseDialogListener.confirmListener();
                }

            }
        });
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.show();
    }

    static public void showBaseDialog(Activity activity, String msg,BaseDialogListener baseDialogListener) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog);
        ImageView mA = dialog.findViewById(R.id.a);
        TextView mTextDialog = dialog.findViewById(R.id.text_dialog);
        Button mBtnDialog = dialog.findViewById(R.id.btn_dialog);
        Button mBtnDialogCancel = dialog.findViewById(R.id.btn_dialog_cancel);
        mTextDialog.setText(msg);

        mBtnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if(baseDialogListener != null)
                baseDialogListener.confirmListener();
            }
        });
        mBtnDialogCancel.setOnClickListener(view -> {
            dialog.dismiss();
            if(baseDialogListener != null) baseDialogListener.cancelListener();

        });
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.show();
    }
}
