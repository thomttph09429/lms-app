package com.poly.lmsapp.ui.detail_class;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.poly.lmsapp.R;
import com.poly.lmsapp.commons.base.LMSAdapter;
import com.poly.lmsapp.commons.resource.KeyResource;
import com.poly.lmsapp.commons.utils.Utils;
import com.poly.lmsapp.model.Document;
import com.poly.lmsapp.ui.detail_doc.DetailDocumentActivity;
import com.poly.lmsapp.ui.m_class.ClassActivity;

import java.util.ArrayList;

public class DetailClassAdapter extends LMSAdapter {
    private ConstraintLayout mContainer;
    private ImageView mIvSubject;
    private TextView mTvSubject;

    public DetailClassAdapter(ArrayList listData, int layout) {
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
        Document document = (Document) getListData().get(position);

        mTvSubject.setText(document.getName());
        mContainer.setOnClickListener(view -> {
            if(document.getType().equals("FILE")){
                    Utils.lunchUrl((Activity) context,document.getLink());
            }else{
                Intent intent = new Intent(context, DetailDocumentActivity.class);
                intent.putExtra(KeyResource.ID_DOCUMENT,document.getId());
                intent.putExtra(KeyResource.NAME_DOCUMENT,document.getName());
                intent.putExtra(KeyResource.TYPE_DOCUMENT,document.getType());
                context.startActivity(intent);
            }


        });
    }
}
