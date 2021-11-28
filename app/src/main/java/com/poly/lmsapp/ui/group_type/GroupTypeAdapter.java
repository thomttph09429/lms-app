package com.poly.lmsapp.ui.group_type;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import com.poly.lmsapp.R;
import com.poly.lmsapp.commons.base.LMSAdapter;
import com.poly.lmsapp.commons.resource.KeyResource;
import com.poly.lmsapp.model.GroupType;
import com.poly.lmsapp.ui.detail_class.DetailClassActivity;
import com.poly.lmsapp.ui.detail_doc.DetailDocumentActivity;
import com.poly.lmsapp.ui.list_type.ListTypeActivity;

import java.util.ArrayList;

public class GroupTypeAdapter extends LMSAdapter {
    private TextView mEdtName;
    private CardView mContainer;

    public GroupTypeAdapter(ArrayList listData, int layout) {
        super(listData, layout);
    }

    @Override
    public void declareViews(View view, BaseViewHolder holder) {
        mEdtName = view.findViewById(R.id.edt_name);
        mContainer = view.findViewById(R.id.container);

    }

    @Override
    public void bindingViewHolder(BaseViewHolder holder, int position) {
        GroupType groupType = (GroupType) getListData().get(position);
        mEdtName.setText(groupType.getName());
        mContainer.setOnClickListener(view -> {
            Intent intent = new Intent(context, ListTypeActivity.class);
            intent.putExtra(KeyResource.ID_GROUP_TYPE,groupType.getId());
            intent.putExtra(KeyResource.NAME_GROUP_TYPE,groupType.getName());
            context.startActivity(intent);
        });

    }
}
