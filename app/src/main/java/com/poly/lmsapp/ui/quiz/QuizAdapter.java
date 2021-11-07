package com.poly.lmsapp.ui.quiz;


import android.view.View;

import android.widget.TextView;
import com.poly.lmsapp.R;
import com.poly.lmsapp.commons.base.LMSAdapter;
import com.poly.lmsapp.model.Quiz;

import java.util.ArrayList;

public class QuizAdapter extends LMSAdapter {
    TextView tvTitle;
    TextView tvTime;

    public QuizAdapter(ArrayList listData, int layout) {
        super(listData, layout);
    }

    @Override
    public void bindingViewHolder(BaseViewHolder holder, int position) {
        Quiz quiz = (Quiz) getListData().get(position);
        tvTime.setText(quiz.getTime());
        tvTime.setText(quiz.getTitle());
    }


    @Override
    public void declareViews(View view, BaseViewHolder holder) {
        tvTitle = view.findViewById(R.id.tv_title);
        tvTime = view.findViewById(R.id.tv_time);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}
