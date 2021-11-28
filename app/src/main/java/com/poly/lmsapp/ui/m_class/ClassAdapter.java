package com.poly.lmsapp.ui.m_class;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.poly.lmsapp.R;
import com.poly.lmsapp.commons.base.LMSAdapter;
import com.poly.lmsapp.commons.resource.KeyResource;
import com.poly.lmsapp.commons.utils.EnviromentSingleton;
import com.poly.lmsapp.model.ClassModel;
import com.poly.lmsapp.model.GroupType;
import com.poly.lmsapp.model.Subject;
import com.poly.lmsapp.ui.detail_class.DetailClassActivity;
import com.poly.lmsapp.ui.group_type.GroupTypeActivity;

import java.util.ArrayList;

public class ClassAdapter extends LMSAdapter {

    private CardView mContainer;
    private ImageView mIvSubject;
    private TextView mTvSubject;

    public ClassAdapter(ArrayList listData, int layout) {
        super(listData, layout);
    }

    @Override
    public void declareViews(View view, BaseViewHolder holder) {
        mIvSubject = view.findViewById(R.id.iv_subject);
        mTvSubject = view.findViewById(R.id.tv_subject);
        mContainer = view.findViewById(R.id.container);
    }

    @Override
    public void bindingViewHolder(BaseViewHolder holder, int position) {
        ClassModel subject = (ClassModel) getListData().get(position);
        mTvSubject.setText(subject.getName());
        mContainer.setOnClickListener(view -> {
            EnviromentSingleton.getEnviromentSingleton().setIdClass(subject.getId());
            Intent intent = new Intent(context, GroupTypeActivity.class);
            intent.putExtra(KeyResource.ID_CLASS,subject.getId());
            intent.putExtra(KeyResource.NAME_CLASS,subject.getName());
            context.startActivity(intent);
        });
    }
}
