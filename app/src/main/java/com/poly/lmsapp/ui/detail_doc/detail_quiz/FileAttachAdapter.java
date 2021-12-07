package com.poly.lmsapp.ui.detail_doc.detail_quiz;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.poly.lmsapp.R;
import com.poly.lmsapp.commons.base.LMSAdapter;
import com.poly.lmsapp.commons.network.Client;
import com.poly.lmsapp.commons.utils.Utils;
import com.poly.lmsapp.model.BaseResponse;
import com.poly.lmsapp.model.FileAttach;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;

public class FileAttachAdapter extends LMSAdapter {
    private SwipeRevealLayout mSrlLayout;
    private TextView mTvName;
    private TextView mTvCreateTime;
    private ImageView mIvDelete;
    private CardView mContainer;
    private boolean status;


    public FileAttachAdapter(ArrayList listData, int layout, boolean status) {
        super(listData, layout);
        this.status = status;
    }

    @Override
    public void declareViews(View view, BaseViewHolder holder) {
        mSrlLayout = view.findViewById(R.id.srl_layout);
        mTvName = view.findViewById(R.id.tv_name);
        mTvCreateTime = view.findViewById(R.id.tv_create_time);
        mIvDelete = view.findViewById(R.id.iv_delete);
        mContainer = view.findViewById(R.id.container);

    }

    @Override
    public void bindingViewHolder(BaseViewHolder holder, int position) {
        FileAttach fileAttach = (FileAttach) getListData().get(position);
        mTvName.setText(fileAttach.getName());
        mTvCreateTime.setText(fileAttach.getCreatedAt());
        mContainer.setOnClickListener(view -> {
            Utils.lunchUrl((DetailAssignmentActivity) context, Utils.concatPath(fileAttach.getLink()));
        });

        mIvDelete.setOnClickListener(view -> {
            if (status) {
                ((DetailAssignmentActivity) context).showLoading(true);
                Client.getInstance().deleteFileAttach(new FileAttach(fileAttach.getId())).enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        BaseResponse baseResponse = response.body();
                        ((DetailAssignmentActivity) context).showLoading(false);

                        if (baseResponse != null && baseResponse.getError().getCode() == 0) {
                            ((DetailAssignmentActivity) context).fetchData();
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {

                    }
                });
            } else {
                Utils.showToast((Activity) context, "Đã hết thời gian. Bạn không thể chỉnh sửa");
            }
        });


    }
}
