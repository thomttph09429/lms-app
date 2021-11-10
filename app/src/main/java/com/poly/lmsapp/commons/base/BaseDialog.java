package com.poly.lmsapp.commons.base;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.poly.lmsapp.R;
import com.poly.lmsapp.commons.utils.Status;


public class BaseDialog {
    static private BaseDialogListener baseDialogListener;

    static public void showBaseDialog(Activity activity, String msg, Status status) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog);

        TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
        text.setText(msg);


        Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if(baseDialogListener != null)
                baseDialogListener.confirmListener();
            }
        });
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.show();
    }

    public static void setBaseDialogListener(BaseDialogListener baseDialogListener) {
        BaseDialog.baseDialogListener = baseDialogListener;
    }
}
