package fpt.thanhluan.quanlynhahang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import fpt.thanhluan.quanlynhahang.DAO.TopDoAnDAO;
import fpt.thanhluan.quanlynhahang.DTO.TopDoAn;
import fpt.thanhluan.quanlynhahang.Fragment.TopFragment;
import fpt.thanhluan.quanlynhahang.R;

public class Top_DoAn_Adapter extends ArrayAdapter {
    Context context;
    ArrayList<TopDoAn> list;
TopDoAnDAO topDoAnDAO;

    public Top_DoAn_Adapter(@NonNull Context context, int resource, Context context1, ArrayList<TopDoAn> list, TopDoAnDAO topDoAnDAO) {
        super(context, resource);
        this.context = context1;
        this.list = list;
        this.topDoAnDAO = topDoAnDAO;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_lv_top_do_an, null);
        }
        TextView tvTenDoANtop,tvSoLuongDoANtop;
        TopDoAn top = list.get(position);
        if (top != null) {
            tvTenDoANtop = view.findViewById(R.id.tvTenDoANtop);
            tvTenDoANtop.setText("Sách: " + top.tendoan);
            tvSoLuongDoANtop = view.findViewById(R.id.tvSoLuongDoANtop);
            tvSoLuongDoANtop.setText("Số Lượng " + top.soluongdoan);
        }
        return super.getView(position, convertView, parent);
    }
}
