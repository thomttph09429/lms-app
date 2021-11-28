package com.poly.lmsapp.ui.home.repository;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.poly.lmsapp.R;
import com.poly.lmsapp.commons.base.LMSAdapter;
import com.poly.lmsapp.commons.resource.KeyResource;
import com.poly.lmsapp.commons.utils.RenderImage;
import com.poly.lmsapp.model.Repository;
import com.poly.lmsapp.ui.semester.SemesterActivity;

import java.util.ArrayList;

public class RepositoryAdapter extends LMSAdapter {
    private ImageView mImgDescription;
    private TextView mTvTitle;
    private LinearLayout mContainer;


    public RepositoryAdapter(ArrayList listData, int layout) {
        super(listData, layout);
    }

    @Override
    public void declareViews(View view, BaseViewHolder holder) {
        mImgDescription = view.findViewById(R.id.img_description);
        mTvTitle = view.findViewById(R.id.tv_title);
        mContainer = view.findViewById(R.id.container);
    }

    @Override
    public void bindingViewHolder(BaseViewHolder holder, int position) {
        Repository repository = (Repository) getListData().get(position);
        mTvTitle.setText(repository.getTitle());
        RenderImage.loadImageNetwork(repository.getImage(), mImgDescription);

        mContainer.setOnClickListener(view -> {
            Intent intent = new Intent(context, SemesterActivity.class);
            intent.putExtra(KeyResource.ID_REPOSITORY, repository.getId());
            intent.putExtra(KeyResource.NAME_REPOSITORY, repository.getTitle());
            context.startActivity(intent);
        });
    }
}
