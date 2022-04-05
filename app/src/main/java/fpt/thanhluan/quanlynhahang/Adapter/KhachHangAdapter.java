package fpt.thanhluan.quanlynhahang.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import fpt.thanhluan.quanlynhahang.DAO.KhachHangDAO;
import fpt.thanhluan.quanlynhahang.DAO.NhanVienDAO;
import fpt.thanhluan.quanlynhahang.DTO.KhachHang;
import fpt.thanhluan.quanlynhahang.DTO.NhanVien;
import fpt.thanhluan.quanlynhahang.R;

public class KhachHangAdapter extends BaseAdapter {

    ArrayList<KhachHang> listKhachHangs;
    KhachHangDAO khachHangDAO;

    @Override
    public int getCount() {
        return listKhachHangs.size();
    }

    @Override
    public Object getItem(int position) {
        return listKhachHangs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listKhachHangs.get(position).getMaKH();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View itemview;
        //khởi tạo cho item
        if (view == null) {
            itemview = View.inflate(viewGroup.getContext(), R.layout.item_lv_khachhang, null);

        } else itemview = view;
        //lấy thôgn tin bản ghi dữ liệu
        final KhachHang objKhachHang = listKhachHangs.get(position);
        final int _index = position;


//ánh xạ các bviuến
        TextView tvMaKH = itemview.findViewById(R.id.tvMaKH);
        TextView tvTenKH = itemview.findViewById(R.id.tvTenKH);
        TextView tvSDT = itemview.findViewById(R.id.tvSDT);
        TextView tvXoa = itemview.findViewById(R.id.tvXoa);
        TextView tvSua = itemview.findViewById(R.id.tvSua);

        //set text
        tvMaKH.setText(objKhachHang.getMaKH());
        tvTenKH.setText(objKhachHang.getTenKH());
        tvSDT.setText(objKhachHang.getSDT());


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
