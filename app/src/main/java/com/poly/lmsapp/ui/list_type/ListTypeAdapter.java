package com.poly.lmsapp.ui.list_type;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import com.poly.lmsapp.R;
import com.poly.lmsapp.commons.base.LMSAdapter;
import com.poly.lmsapp.commons.resource.KeyResource;
import com.poly.lmsapp.commons.utils.DateTimeUtils;
import com.poly.lmsapp.commons.utils.EnviromentSingleton;
import com.poly.lmsapp.commons.utils.Utils;
import com.poly.lmsapp.model.DocumentType;
import com.poly.lmsapp.model.GroupType;
import com.poly.lmsapp.ui.detail_doc.detail_quiz.DetailAssignmentActivity;

import java.util.ArrayList;
import java.util.Date;

public class ListTypeAdapter extends LMSAdapter {

    private TextView mEdtName;
    private CardView mContainer;
    private TextView mTvTime;
    private TextView mTvStatus;


    public ListTypeAdapter(ArrayList listData, int layout) {
        super(listData, layout);
    }

    @Override
    public void declareViews(View view, BaseViewHolder holder) {
        mEdtName = view.findViewById(R.id.edt_name);
        mContainer = view.findViewById(R.id.container);
        mTvTime = view.findViewById(R.id.tv_time);
        mTvStatus = view.findViewById(R.id.tv_status);
    }

    @Override
    public void bindingViewHolder(BaseViewHolder holder, int position) {
        DocumentType groupType = (DocumentType) getListData().get(position);
        mEdtName.setText(groupType.getName());
        String time = DateTimeUtils.toDateFormat(groupType.startTime, DateTimeUtils.SERVER_DATE_2, DateTimeUtils.TIME_DATE);
        String etime = DateTimeUtils.toDateFormat(groupType.endTime, DateTimeUtils.SERVER_DATE_2, DateTimeUtils.TIME_DATE);
        mTvTime.setText(etime);
        Date startDate = DateTimeUtils.parseDate(groupType.startTime, DateTimeUtils.SERVER_DATE_2);
        Date endDate = DateTimeUtils.parseDate(groupType.endTime, DateTimeUtils.SERVER_DATE_2);
        String status;
        if (DateTimeUtils.nowAfter(endDate)) {
            status = "Đã hết hạn";mTvStatus.setTextColor(context.getResources().getColor(R.color.red));
        } else{
            status = "Đang diễn ra";
            mTvStatus.setTextColor(context.getResources().getColor(R.color.green));
        }
        mTvStatus.setText(status);
        mContainer.setOnClickListener(view -> {
            EnviromentSingleton.getEnviromentSingleton().setIdDocumentType(groupType.getId());
            Intent intent = new Intent(context, DetailAssignmentActivity.class);
            intent.putExtra(KeyResource.OBJECT_DOCUMENT, Utils.jsonEncode(groupType));
            context.startActivity(intent);
        });
    }
}
