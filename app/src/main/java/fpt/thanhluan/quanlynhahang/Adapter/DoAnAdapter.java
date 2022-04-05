package fpt.thanhluan.quanlynhahang.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import fpt.thanhluan.quanlynhahang.DAO.DoAnDAO;
import fpt.thanhluan.quanlynhahang.DTO.DoAn;
import fpt.thanhluan.quanlynhahang.R;

public class DoAnAdapter extends BaseAdapter {

    ArrayList<DoAn> listDoAns;
    DoAnDAO doAnDAO;

    public DoAnAdapter(ArrayList<DoAn> listDoAns, DoAnDAO doAnDAO) {
        this.listDoAns = listDoAns;
        this.doAnDAO = doAnDAO;
    }

    @Override
    public int getCount() {
        return listDoAns.size();
    }

    @Override
    public Object getItem(int position) {
        DoAn objDoAn = listDoAns.get(position);
        return objDoAn;
    }

    @Override
    public long getItemId(int position) {
        DoAn objDoAn = listDoAns.get(position);
        return objDoAn.getMaDA();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View itemview;
        //khởi tạo cho item

        if (view == null) {
            itemview = View.inflate(viewGroup.getContext(), R.layout.item_lv_doan, null);

        } else itemview = view;
        //lấy thông tin bản ghi dữ liệu
        final DoAn objDoAn = listDoAns.get(position);
        final int _index = position;

        //ánh xạ các biến
        TextView tvMaDA = itemview.findViewById(R.id.tvMaDA);
        TextView tvTenDA = itemview.findViewById(R.id.tvTenDA);
        TextView tvGiaDA = itemview.findViewById(R.id.tvGiaDA);
        TextView tvSoLuongDA = itemview.findViewById(R.id.tvSoLuongDA);
        TextView tvMaLoaiDA = itemview.findViewById(R.id.tvMaLoaiDA);

        TextView tvXoa = itemview.findViewById(R.id.tvXoa);
        TextView tvSua = itemview.findViewById(R.id.tvSua);


        //set text
        tvMaDA.setText(objDoAn.getMaDA() + "");
        tvTenDA.setText(objDoAn.getTenDA() + "");
        tvGiaDA.setText(objDoAn.getGiaDA() + "");
        tvSoLuongDA.setText(objDoAn.getSoLuongDA() + "");
        tvMaLoaiDA.setText(objDoAn.getMaLoaiDA() + "");

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