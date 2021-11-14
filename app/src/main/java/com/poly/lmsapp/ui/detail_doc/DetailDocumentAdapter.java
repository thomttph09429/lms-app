package com.poly.lmsapp.ui.detail_doc;


import android.content.Intent;
import android.view.View;

import android.widget.TextView;
import com.poly.lmsapp.R;
import com.poly.lmsapp.commons.base.LMSAdapter;
import com.poly.lmsapp.model.Assignment;
import com.poly.lmsapp.model.Quiz;

import java.util.ArrayList;

public class DetailDocumentAdapter extends LMSAdapter {
    TextView tvTitle;
    TextView tvTime;

    public DetailDocumentAdapter(ArrayList listData, int layout) {
        super(listData, layout);
    }

    @Override
    public void bindingViewHolder(BaseViewHolder holder, int position) {
        Assignment quiz = (Assignment) getListData().get(position);
        tvTime.setText(quiz.getEndTime());
        tvTitle.setText(quiz.getTitle());
    }


    @Override
    public void declareViews(View view, BaseViewHolder holder) {
        tvTitle = view.findViewById(R.id.tv_title);
        tvTime = view.findViewById(R.id.tv_time);
//        view.setOnClickListener(view1 -> context.startActivity(new Intent(context, DetailDocumentActivity.class)));
    }

}
