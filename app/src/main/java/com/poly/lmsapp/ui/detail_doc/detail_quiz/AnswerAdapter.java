package com.poly.lmsapp.ui.detail_doc.detail_quiz;

import android.os.Build;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import com.poly.lmsapp.R;
import com.poly.lmsapp.commons.base.LMSAdapter;
import com.poly.lmsapp.model.Answer;

import java.util.ArrayList;

public class AnswerAdapter extends LMSAdapter {

    private CardView mContainer;
    private CheckBox mCbAnswer;
    boolean checked = false;

    public AnswerAdapter(ArrayList listData, int layout) {
        super(listData, layout);
    }

    @Override
    public void declareViews(View view, BaseViewHolder holder) {
        mContainer = view.findViewById(R.id.container);
        mCbAnswer = view.findViewById(R.id.cb_answer);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void bindingViewHolder(BaseViewHolder holder, int position) {
        Answer answer = (Answer) getListData().get(position);
        mCbAnswer.setText(position + ". " + answer.getContent());
        mCbAnswer.setChecked(checked);
        mCbAnswer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                checked = false;

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mCbAnswer.setChecked(true);
            }
        });
    }
}
