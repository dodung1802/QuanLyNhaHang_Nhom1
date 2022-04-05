package fpt.thanhluan.quanlynhahang.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import fpt.thanhluan.quanlynhahang.DAO.LoaiDoUongDAO;
import fpt.thanhluan.quanlynhahang.DAO.PhongDAO;
import fpt.thanhluan.quanlynhahang.DTO.LoaiDoAn;
import fpt.thanhluan.quanlynhahang.DTO.LoaiDoUong;
import fpt.thanhluan.quanlynhahang.DTO.Phong;
import fpt.thanhluan.quanlynhahang.R;

public class LoaiDoUongAdapter extends BaseAdapter {
    Context context;
    ArrayList<LoaiDoUong> listLoaiDoUongs;
    LoaiDoUongDAO loaiDoUongDAO;

    public LoaiDoUongAdapter(Context context, ArrayList<LoaiDoUong> listLoaiDoUongs, LoaiDoUongDAO loaiDoUongDAO) {
        this.context = context;
        this.listLoaiDoUongs = listLoaiDoUongs;
        this.loaiDoUongDAO = loaiDoUongDAO;
    }

    @Override
    public int getCount() {
        return listLoaiDoUongs.size();
    }

    @Override
    public Object getItem(int position) {
        LoaiDoUong objLoaiDoUong = listLoaiDoUongs.get(position);
        return objLoaiDoUong;
    }

    @Override
    public long getItemId(int position) {
        LoaiDoUong objLoaiDoUong = listLoaiDoUongs.get(position);
        return objLoaiDoUong.getMaLoaiDU();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View itemview;
        //khởi tạo cho item

        if (view == null) {
            itemview = View.inflate(viewGroup.getContext(), R.layout.item_lv_loaidouong, null);

        } else itemview = view;
        //lấy thôgn tin bản ghi dữ liệu
        final LoaiDoUong objLoaiDoUong = listLoaiDoUongs.get(position);
        final int _index = position;

//ánh xạ các bviuến
        TextView tvMaLoaiDU = itemview.findViewById(R.id.tvMaLoaiDU);
        TextView tvTenLoaiDU = itemview.findViewById(R.id.tvTenLoaiDU);
        TextView tvXoa = itemview.findViewById(R.id.tvXoa);
        TextView tvSua = itemview.findViewById(R.id.tvSua);

        //set text
        tvMaLoaiDU.setText(objLoaiDoUong.getMaLoaiDU() + "");
        tvTenLoaiDU.setText(objLoaiDoUong.getTenLoaiDU() + "");


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
                        int kq = loaiDoUongDAO.deleteRow(objLoaiDoUong);
                        if(kq>0){
                            listLoaiDoUongs.remove(position);
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

            }
        });

        return itemview;
    }



    public void showDiaLogAdd() {

        Dialog dialog = new Dialog(context, R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_loai_do_uong);

        TextInputEditText edTenLoaiDU = dialog.findViewById(R.id.edTenLoaiDU);
        Button btnLuu = dialog.findViewById(R.id.btnSaveLoaiDU);
        Button btnHuy = dialog.findViewById(R.id.btnCancelLoaiDU);

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoaiDoUong objLoaiDoUong = new LoaiDoUong();
                objLoaiDoUong.setTenLoaiDU(edTenLoaiDU.getText().toString());

                long kq = loaiDoUongDAO.insertRow(objLoaiDoUong);

                if (kq > 0) {
                    listLoaiDoUongs.clear();
                    listLoaiDoUongs.addAll(loaiDoUongDAO.getAll());
                    notifyDataSetChanged();
                    Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void showDialogUpdate(int vitri, LoaiDoUong objLoaiDoUong) {
        Dialog dialog = new Dialog(context, R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_loai_do_uong);

        TextInputEditText edTenLoaiDU = dialog.findViewById(R.id.edTenLoaiDU);
        Button btnLuu = dialog.findViewById(R.id.btnSaveLoaiDU);
        Button btnHuy = dialog.findViewById(R.id.btnCancelLoaiDU);

        edTenLoaiDU.setText(objLoaiDoUong.getTenLoaiDU() + "");

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objLoaiDoUong.setTenLoaiDU(edTenLoaiDU.getText().toString());

                int kq = loaiDoUongDAO.updateRow(objLoaiDoUong);

                if (kq > 0) {
                    listLoaiDoUongs.set(vitri, objLoaiDoUong);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }


}
