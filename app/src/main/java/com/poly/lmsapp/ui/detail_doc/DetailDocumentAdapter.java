package com.poly.lmsapp.ui.detail_doc;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.LinearLayout;
import android.widget.TextView;
import com.poly.lmsapp.R;
import com.poly.lmsapp.commons.base.LMSAdapter;
import com.poly.lmsapp.commons.resource.KeyResource;
import com.poly.lmsapp.commons.utils.Utils;
import com.poly.lmsapp.model.Assignment;
import com.poly.lmsapp.model.Quiz;
import com.poly.lmsapp.ui.detail_doc.detail_quiz.DetailAssignmentActivity;

import java.util.ArrayList;

public class DetailDocumentAdapter extends LMSAdapter {
    TextView tvTitle;
    TextView tvTime;
    private LinearLayout mContainer;


    public DetailDocumentAdapter(ArrayList listData, int layout) {
        super(listData, layout);
    }

    @Override
    public void bindingViewHolder(BaseViewHolder holder, int position) {
        Assignment quiz = (Assignment) getListData().get(position);
        tvTime.setText(quiz.getEndTime());
        tvTitle.setText(quiz.getTitle());
        mContainer.setOnClickListener(view1 -> {
            Intent intent = new Intent(context, DetailAssignmentActivity.class);
            intent.putExtra(KeyResource.OBJECT_DOCUMENT, Utils.jsonEncode(quiz));
            context.startActivity(intent);

        });
    }


    @Override
    public void declareViews(View view, BaseViewHolder holder) {
        tvTitle = view.findViewById(R.id.tv_title);
        tvTime = view.findViewById(R.id.tv_time);
        mContainer = view.findViewById(R.id.container);


    }

}
