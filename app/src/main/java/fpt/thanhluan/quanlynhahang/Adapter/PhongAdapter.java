package fpt.thanhluan.quanlynhahang.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import fpt.thanhluan.quanlynhahang.DAO.PhongDAO;
import fpt.thanhluan.quanlynhahang.DTO.Phong;
import fpt.thanhluan.quanlynhahang.R;

public class PhongAdapter extends BaseAdapter {

    Context context;
    ArrayList<Phong> listPhong;
    PhongDAO phongDAO;

    public PhongAdapter(Context context, ArrayList<Phong> listPhong, PhongDAO phongDAO) {
        this.context = context;
        this.listPhong = listPhong;
        this.phongDAO = phongDAO;
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
        ImageButton tvXoa = itemview.findViewById(R.id.tvXoa);
        ImageButton tvSua = itemview.findViewById(R.id.tvSua);

        //set text
        tvMaPhong.setText("Mã phòng : "+objPhong.getMaPhong() + "");
        tvSoPhong.setText("Tên phòng : "+objPhong.getSoPhong() + "");


        tvXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete");
                builder.setIcon(android.R.drawable.ic_delete);
                builder.setMessage("Bạn có muốn xóa không ?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int kq = phongDAO.deleteRow(objPhong);
                        if(kq>0){
                            listPhong.remove(position);
                            notifyDataSetChanged();
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                Dialog dialog = builder.create();
                dialog.show();
            }
        });

        tvSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDialogUpdate(position,objPhong);
            }
        });

        return itemview;
    }


    public void showDiaLogAdd(){

        Dialog dialog = new Dialog(context,R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_phong);

        TextInputEditText edSoPhong = dialog.findViewById(R.id.edSoPhong);
        Button btnLuu = dialog.findViewById(R.id.btnSavePhong);
        Button btnHuy = dialog.findViewById(R.id.btnCancelPhong);

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Phong objPhong = new Phong();
                objPhong.setSoPhong(edSoPhong.getText().toString());

                long kq = phongDAO.insertRow(objPhong);

                if(kq>0){
                    listPhong.clear();
                    listPhong.addAll(phongDAO.getAll());
                    notifyDataSetChanged();
                    Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void showDialogUpdate(int vitri,Phong objPhong){
        Dialog dialog = new Dialog(context,R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_phong);

        TextInputEditText edSoPhong = dialog.findViewById(R.id.edSoPhong);
        Button btnLuu = dialog.findViewById(R.id.btnSavePhong);
        Button btnHuy = dialog.findViewById(R.id.btnCancelPhong);


        edSoPhong.setText(objPhong.getSoPhong()+"");

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objPhong.setSoPhong(edSoPhong.getText().toString());

                int kq = phongDAO.updateRow(objPhong);

                if(kq>0){
                    listPhong.set(vitri,objPhong);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}