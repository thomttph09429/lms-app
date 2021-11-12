package com.poly.lmsapp.ui.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.poly.lmsapp.R;
import com.poly.lmsapp.commons.base.LMSAdapter;
import com.poly.lmsapp.commons.utils.RenderImage;
import com.poly.lmsapp.model.Repository;

import java.util.ArrayList;

public class HomeAdapter extends LMSAdapter {
    private ImageView mImgDescription;
    private TextView mTvTitle;


    public HomeAdapter(ArrayList listData, int layout) {
        super(listData, layout);
    }

    @Override
    public void declareViews(View view, BaseViewHolder holder) {
        mImgDescription = view.findViewById(R.id.img_description);
        mTvTitle = view.findViewById(R.id.tv_title);
    }

    @Override
    public void bindingViewHolder(BaseViewHolder holder, int position) {
        Repository repository = (Repository) getListData().get(position);
        mTvTitle.setText(repository.getTitle());
        RenderImage.loadImageNetwork(repository.getImage(), mImgDescription);
    }
}
