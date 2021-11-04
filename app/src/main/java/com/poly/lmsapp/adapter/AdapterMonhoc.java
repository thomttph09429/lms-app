package com.poly.lmsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.poly.lmsapp.R;
import com.poly.lmsapp.model.MonHocModel;

import java.util.ArrayList;

public class AdapterMonhoc extends BaseAdapter {
    private ArrayList<MonHocModel> monHocModels;
    private Context context ;

    public AdapterMonhoc(ArrayList<MonHocModel> monHocModels,Context context){
        this.monHocModels = monHocModels ;
        this.context = context ;
    }

    @Override
    public int getCount() {
        return monHocModels.size();
    }

    @Override
    public Object getItem(int position) {
        return monHocModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_monhoc,parent,false);
        ImageView imgeIconFolder = convertView.findViewById(R.id.img_folder);
        TextView txtTitle = convertView.findViewById(R.id.txt_monhoc);

        imgeIconFolder.setImageResource(R.drawable.folder_icon);
        txtTitle.setText(String.valueOf(monHocModels.get(position).getTitLe()));

        return convertView;
    }
}
