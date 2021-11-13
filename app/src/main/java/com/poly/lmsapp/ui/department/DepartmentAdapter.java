package com.poly.lmsapp.ui.department;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.poly.lmsapp.R;
import com.poly.lmsapp.commons.base.LMSAdapter;
import com.poly.lmsapp.commons.utils.RenderImage;
import com.poly.lmsapp.model.Department;

import java.util.ArrayList;

public class DepartmentAdapter extends LMSAdapter {
    private ImageView mIvDepartment;
    private TextView mTvTitle;
    private ConstraintLayout mContainer;



    public DepartmentAdapter(ArrayList listData, int layout) {
        super(listData, layout);
    }

    @Override
    public void declareViews(View view, BaseViewHolder holder) {
        mIvDepartment = view.findViewById(R.id.iv_department);
        mTvTitle = view.findViewById(R.id.tv_title);
        mContainer = view.findViewById(R.id.container);
    }

    @Override
    public void bindingViewHolder(BaseViewHolder holder, int position) {
        Department department = (Department) getListData().get(position);
        RenderImage.loadImageNetwork(department.getImage(),mIvDepartment);
        mTvTitle.setText(department.getName());
        mContainer.setOnClickListener(view -> {

        });
    }
}
