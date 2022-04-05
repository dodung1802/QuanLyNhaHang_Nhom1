package fpt.thanhluan.quanlynhahang.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import fpt.thanhluan.quanlynhahang.DAO.BanDAO;
import fpt.thanhluan.quanlynhahang.DAO.PhongDAO;
import fpt.thanhluan.quanlynhahang.DTO.Ban;
import fpt.thanhluan.quanlynhahang.DTO.Phong;
import fpt.thanhluan.quanlynhahang.R;

public class BanAdapter extends BaseAdapter {

    ArrayList<Ban> listBan;
    BanDAO banDAO;

    public BanAdapter(ArrayList<Ban> listBan, BanDAO banDAO) {
        this.listBan = listBan;
        this.banDAO = banDAO;
    }

    @Override
    public int getCount() {
        return listBan.size();
    }

    @Override
    public Object getItem(int position) {
        Ban objBan = listBan.get(position);
        return objBan;
    }

    @Override
    public long getItemId(int position) {
        Ban objBan = listBan.get(position);
        return objBan.getMaBan();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View itemview;
        //khởi tạo cho item
        if (view == null) {
            itemview = View.inflate(viewGroup.getContext(), R.layout.item_lv_ban, null);

        } else itemview = view;
        //lấy thôgn tin bản ghi dữ liệu
        final Ban objBan = listBan.get(position);
        final int _index = position;


       //ánh xạ các biến
        TextView tvMaBan = itemview.findViewById(R.id.tvMaBan);
        TextView tvTrangThai = itemview.findViewById(R.id.tvTrangThai);
        TextView tvMaPhong = itemview.findViewById(R.id.tvMaPhong);
        TextView tvXoa = itemview.findViewById(R.id.tvXoa);
        TextView tvSua = itemview.findViewById(R.id.tvSua);

        //set text
        tvMaBan.setText(objBan.getMaBan() + "");
        tvTrangThai.setText(objBan.getTrangThai() + "");
        tvMaPhong.setText(objBan.getMaPhong() + "");


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
