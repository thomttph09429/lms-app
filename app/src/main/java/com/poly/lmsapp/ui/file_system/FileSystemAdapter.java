package com.poly.lmsapp.ui.file_system;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import com.poly.lmsapp.R;
import com.poly.lmsapp.commons.base.LMSAdapter;
import com.poly.lmsapp.commons.utils.Utils;
import com.poly.lmsapp.model.FileSystem;

import java.util.ArrayList;

public class FileSystemAdapter extends LMSAdapter {
    private ImageView mIvFileSystem;
    private TextView mTvNameFileSystem;
    private TextView mTvLinkFileSystem;
    private TextView mTvCreateTimeFileSystem;
    private CardView mContainer;


    public FileSystemAdapter(ArrayList listData, int layout) {
        super(listData, layout);
    }

    @Override
    public void declareViews(View view, BaseViewHolder holder) {
        mIvFileSystem = view.findViewById(R.id.iv_file_system);
        mTvNameFileSystem = view.findViewById(R.id.tv_name_file_system);
        mTvLinkFileSystem = view.findViewById(R.id.tv_link_file_system);
        mTvCreateTimeFileSystem = view.findViewById(R.id.tv_create_time_file_system);
        mContainer = view.findViewById(R.id.container);

    }

    @Override
    public void bindingViewHolder(BaseViewHolder holder, int position) {
        FileSystem fileSystem = (FileSystem) getListData().get(position);
        mTvNameFileSystem.setText(fileSystem.getName());
        mTvCreateTimeFileSystem.setText(fileSystem.getCreateAt());
        mTvLinkFileSystem.setText(fileSystem.getLinkFile());
        mContainer.setOnClickListener(view -> {
            Utils.lunchUrl(((FileSystemActivity)context),Utils.concatPath(fileSystem.getLinkFile()));
        });
    }
}
