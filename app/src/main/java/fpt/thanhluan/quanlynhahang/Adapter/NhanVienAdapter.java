package fpt.thanhluan.quanlynhahang.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import fpt.thanhluan.quanlynhahang.DAO.NhanVienDAO;
import fpt.thanhluan.quanlynhahang.DAO.PhongDAO;
import fpt.thanhluan.quanlynhahang.DTO.NhanVien;
import fpt.thanhluan.quanlynhahang.DTO.Phong;
import fpt.thanhluan.quanlynhahang.R;

public class NhanVienAdapter extends BaseAdapter {

    Context context;
    ArrayList<NhanVien> listNhanViens;
    NhanVienDAO nhanVienDAO;

    public NhanVienAdapter(Context context, ArrayList<NhanVien> listNhanViens, NhanVienDAO nhanVienDAO) {
        this.context = context;
        this.listNhanViens = listNhanViens;
        this.nhanVienDAO = nhanVienDAO;
    }

    @Override
    public int getCount() {
        return listNhanViens.size();
    }

    @Override
    public Object getItem(int position) {
        return listNhanViens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View itemview;
        //khởi tạo cho item
        if (view == null) {
            itemview = View.inflate(viewGroup.getContext(), R.layout.item_lv_nhanvien, null);

        } else itemview = view;
        //lấy thôgn tin bản ghi dữ liệu
        final NhanVien objNhanVien = listNhanViens.get(position);
        final int _index = position;


//ánh xạ các bviuến
        TextView tvMaNV = itemview.findViewById(R.id.tvMaNV);
        TextView tvHoTen = itemview.findViewById(R.id.tvHoTen);
        TextView tvMatKhau = itemview.findViewById(R.id.tvMatKhau);
        TextView tvXoa = itemview.findViewById(R.id.tvXoa);
        TextView tvSua = itemview.findViewById(R.id.tvSua);

        //set text
        tvMaNV.setText(objNhanVien.getMaNV());
        tvHoTen.setText(objNhanVien.getHoTen());
        tvMatKhau.setText(objNhanVien.getMatKhau());


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
