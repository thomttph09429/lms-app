package com.poly.lmsapp.ui.semester;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.poly.lmsapp.R;
import com.poly.lmsapp.commons.base.LMSAdapter;
import com.poly.lmsapp.commons.resource.KeyResource;
import com.poly.lmsapp.model.Semester;
import com.poly.lmsapp.ui.department.NganhHKHL_Activity;

import java.util.ArrayList;

public class SemesterAdapter extends LMSAdapter {

    private TextView mTvKyHoc;
    private TextView mTvTime;
    private TextView mTvNguoiTao;
    private ConstraintLayout mContainer;


    public SemesterAdapter(ArrayList listData, int layout) {
        super(listData, layout);
    }

    @Override
    public void declareViews(View view, BaseViewHolder holder) {
        mTvKyHoc = view.findViewById(R.id.tv_ky_hoc);
        mTvTime = view.findViewById(R.id.tv_time);
        mTvNguoiTao = view.findViewById(R.id.tv_nguoi_tao);
        mContainer = view.findViewById(R.id.container);

    }

    @Override
    public void bindingViewHolder(BaseViewHolder holder, int position) {
        Semester semester = (Semester) getListData().get(position);
        mTvKyHoc.setText(semester.getName());
        mTvTime.setText(semester.getCreateAt());
        mTvNguoiTao.setText(semester.getCreateBy().getName());
        mContainer.setOnClickListener(view -> {
            Intent intent = new Intent(context, NganhHKHL_Activity.class);
            intent.putExtra(KeyResource.ID_SEMESTER, semester.getId());
            context.startActivity(intent);

        });
    }
}
