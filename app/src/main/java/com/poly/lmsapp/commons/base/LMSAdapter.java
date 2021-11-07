package com.poly.lmsapp.commons.base;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public abstract class LMSAdapter extends RecyclerView.Adapter<LMSAdapter.BaseViewHolder> {

    private ArrayList<?> listData;
    private int layout;

    public LMSAdapter(ArrayList listData, int layout) {
        this.listData = listData;
        this.layout = layout;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(layout, parent, false);
        return new BaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        bindingViewHolder(holder,position);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public ArrayList getListData() {
        return listData;
    }

    public class BaseViewHolder extends RecyclerView.ViewHolder {
        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
            declareViews(itemView, this);
        }
    }


    public abstract void declareViews(View view, BaseViewHolder holder);

    public abstract void bindingViewHolder(BaseViewHolder holder, int position);
}
