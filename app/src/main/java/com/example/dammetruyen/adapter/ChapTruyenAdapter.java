package com.example.dammetruyen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dammetruyen.R;
import com.example.dammetruyen.object.ChapTruyen;

import java.util.ArrayList;
import java.util.List;

public class ChapTruyenAdapter extends ArrayAdapter<ChapTruyen> {
    private Context ct;
    private ArrayList<ChapTruyen> arr;

    public ChapTruyenAdapter(Context context, int resource, List<ChapTruyen> objects) {
        super(context, resource, objects);
        this.ct = context;
        this.arr = new ArrayList<>(objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_chap_truyen, null);

        }

        if (arr.size() > 0) {
            ChapTruyen chapTruyen = this.arr.get(position);

            TextView txvtenSoChap = convertView.findViewById(R.id.txvtenSoChap);
            TextView txvNgayDang = convertView.findViewById(R.id.txvNgayDang);

            txvtenSoChap.setText(chapTruyen.getTenSoChap());
            txvNgayDang.setText(chapTruyen.getNgayDang());
        }


        return convertView;
    }
}
