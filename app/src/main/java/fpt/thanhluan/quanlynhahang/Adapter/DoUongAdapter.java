package fpt.thanhluan.quanlynhahang.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import fpt.thanhluan.quanlynhahang.DAO.DoAnDAO;
import fpt.thanhluan.quanlynhahang.DAO.DoUongDAO;
import fpt.thanhluan.quanlynhahang.DTO.DoAn;
import fpt.thanhluan.quanlynhahang.DTO.DoUong;
import fpt.thanhluan.quanlynhahang.R;

public class DoUongAdapter extends BaseAdapter {

    ArrayList<DoUong> listDoUongs;
    DoUongDAO doUongDAO;

    public DoUongAdapter(ArrayList<DoUong> listDoUongs, DoUongDAO doUongDAO) {
        this.listDoUongs = listDoUongs;
        this.doUongDAO = doUongDAO;
    }

    @Override
    public int getCount() {
        return listDoUongs.size();
    }

    @Override
    public Object getItem(int position) {
        DoUong objDoUong = listDoUongs.get(position);
        return objDoUong;
    }

    @Override
    public long getItemId(int position) {
        DoUong objDoUong = listDoUongs.get(position);
        return objDoUong.getMaDU();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View itemview;
        //khởi tạo cho item

        if (view == null) {
            itemview = View.inflate(viewGroup.getContext(), R.layout.item_lv_douong, null);

        } else itemview = view;
        //lấy thông tin bản ghi dữ liệu
        final DoUong objDoUong = listDoUongs.get(position);
        final int _index = position;

        //ánh xạ các biến
        TextView tvMaDU = itemview.findViewById(R.id.tvMaDU);
        TextView tvTenDU = itemview.findViewById(R.id.tvTenDU);
        TextView tvGiaDU = itemview.findViewById(R.id.tvGiaDU);
        TextView tvSoLuongDU = itemview.findViewById(R.id.tvSoLuongDU);
        TextView tvSizeDU = itemview.findViewById(R.id.tvSizeDU);
        TextView tvMaLoaiDU = itemview.findViewById(R.id.tvMaLoaiDU);

        TextView tvXoa = itemview.findViewById(R.id.tvXoa);
        TextView tvSua = itemview.findViewById(R.id.tvSua);


        //set text
        tvMaDU.setText(objDoUong.getMaDU() + "");
        tvTenDU.setText(objDoUong.getTenDU() + "");
        tvGiaDU.setText(objDoUong.getGiaDU() + "");
        tvSoLuongDU.setText(objDoUong.getSoLuongDU() + "");
        tvSizeDU.setText(objDoUong.getSizeDU() + "");
        tvMaLoaiDU.setText(objDoUong.getMaLoaiDU() + "");

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
