package fpt.thanhluan.quanlynhahang.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import fpt.thanhluan.quanlynhahang.DAO.PhongDAO;
import fpt.thanhluan.quanlynhahang.DTO.Phong;
import fpt.thanhluan.quanlynhahang.R;

public class PhongAdapter extends BaseAdapter {

    ArrayList<Phong> listPhong;
    PhongDAO phongDAO;
Context context;

    public PhongAdapter(ArrayList<Phong> listPhong, PhongDAO phongDAO, Context context) {
        this.listPhong = listPhong;
        this.phongDAO = phongDAO;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listPhong.size();
    }

    @Override
    public Object getItem(int position) {

        Phong objPhong = listPhong.get(position);
        return objPhong;

    }

    @Override
    public long getItemId(int position) {
        Phong objPhong = listPhong.get(position);
        return objPhong.getMaPhong();

    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View itemview;
        //khởi tạo cho item
        if (view == null) {
            itemview = View.inflate(viewGroup.getContext(), R.layout.item_lv_phong, null);

        } else itemview = view;
        //lấy thôgn tin bản ghi dữ liệu
        final Phong objPhong = listPhong.get(position);
        final int _index = position;


//ánh xạ các bviuến
        TextView tvMaPhong = itemview.findViewById(R.id.tvMaPhong);
        TextView tvSoPhong = itemview.findViewById(R.id.tvSoPhong);
        TextView tvXoa = itemview.findViewById(R.id.tvXoa);
        TextView tvSua = itemview.findViewById(R.id.tvSua);

        //set text
        tvMaPhong.setText(objPhong.getMaPhong() + "");
        tvSoPhong.setText(objPhong.getSoPhong() + "");


        tvXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tvSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return itemview;
    }



}