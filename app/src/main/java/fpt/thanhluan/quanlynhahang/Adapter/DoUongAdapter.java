package fpt.thanhluan.quanlynhahang.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import fpt.thanhluan.quanlynhahang.AdapterSpinner.LoaiDoUongAdapterSpinner;
import fpt.thanhluan.quanlynhahang.DAO.DoUongDAO;
import fpt.thanhluan.quanlynhahang.DAO.LoaiDoUongDAO;
import fpt.thanhluan.quanlynhahang.DTO.DoUong;
import fpt.thanhluan.quanlynhahang.DTO.LoaiDoUong;
import fpt.thanhluan.quanlynhahang.R;

public class DoUongAdapter extends BaseAdapter {

    Context context;
    ArrayList<DoUong> listDoUongs;
    DoUongDAO doUongDAO;

    public DoUongAdapter(Context context, ArrayList<DoUong> listDoUongs, DoUongDAO doUongDAO) {
        this.context = context;
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

        ImageButton tvXoa = itemview.findViewById(R.id.tvXoa);
        ImageButton tvSua = itemview.findViewById(R.id.tvSua);


        //set text
        tvMaDU.setText("ID : "+objDoUong.getMaDU() + "");
        tvTenDU.setText("Tên: "+objDoUong.getTenDU() + "");
        tvGiaDU.setText("Gía : "+objDoUong.getGiaDU() + "");
        tvSoLuongDU.setText("Số lượng : "+objDoUong.getSoLuongDU() + "");
        tvSizeDU.setText("Size : "+objDoUong.getSizeDU() + "");
        tvMaLoaiDU.setText("Loại : "+objDoUong.getTenLoaiDU() + "");

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
                        int kq = doUongDAO.deleteRow(objDoUong);
                        if(kq>0){
                            listDoUongs.remove(position);
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
            showDialogEdit(position,objDoUong);
            }
        });
        return itemview;
    }

    public void showDialogAdd(){

        Dialog dialog = new Dialog(context,R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_do_uong);

        TextInputEditText edTenDU = dialog.findViewById(R.id.edTenDU);
        TextInputEditText edGiaDU = dialog.findViewById(R.id.edGiaDU);
        TextInputEditText edSoLuongDU = dialog.findViewById(R.id.edSoLuongDU);
        TextInputEditText edSizeDU = dialog.findViewById(R.id.edSizeDU);

        Spinner spinner = dialog.findViewById(R.id.spinnerLoaiDU);

        LoaiDoUongDAO loaiDoUongDAO = new LoaiDoUongDAO(context);
        loaiDoUongDAO.open();

        LoaiDoUongAdapterSpinner loaiDoUongAdapterSpinner = new LoaiDoUongAdapterSpinner((ArrayList<LoaiDoUong>) loaiDoUongDAO.getAll());
        spinner.setAdapter(loaiDoUongAdapterSpinner);

        Button btnLuu = dialog.findViewById(R.id.btnSaveDU);
        Button btnHuy = dialog.findViewById(R.id.btnCancelDU);

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoUong objDoUong = new DoUong();

                objDoUong.setTenDU(edTenDU.getText().toString());
                objDoUong.setGiaDU(Integer.parseInt(edGiaDU.getText().toString()));
                objDoUong.setSoLuongDU(Integer.parseInt(edSoLuongDU.getText().toString()));
                objDoUong.setSizeDU(edSizeDU.getText().toString());

                LoaiDoUong objLoaiDoUong = (LoaiDoUong) spinner.getSelectedItem();

                objDoUong.setMaLoaiDU(objLoaiDoUong.getMaLoaiDU());
                objDoUong.setTenLoaiDU(objLoaiDoUong.getTenLoaiDU());
                long kq = doUongDAO.insertRow(objDoUong);

                if(kq>0){
                    listDoUongs.clear();
                    listDoUongs.addAll(doUongDAO.getAll());
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

    public void showDialogEdit(int vitri,DoUong objDoUong){
        Dialog dialog = new Dialog(context,R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_do_uong);

        TextInputEditText edTenDU = dialog.findViewById(R.id.edTenDU);
        TextInputEditText edGiaDU = dialog.findViewById(R.id.edGiaDU);
        TextInputEditText edSoLuongDU = dialog.findViewById(R.id.edSoLuongDU);
        TextInputEditText edSizeDU = dialog.findViewById(R.id.edSizeDU);

        Spinner spinner = dialog.findViewById(R.id.spinnerLoaiDU);

        LoaiDoUongDAO loaiDoUongDAO = new LoaiDoUongDAO(context);
        loaiDoUongDAO.open();

        LoaiDoUongAdapterSpinner loaiDoUongAdapterSpinner = new LoaiDoUongAdapterSpinner((ArrayList<LoaiDoUong>) loaiDoUongDAO.getAll());
        spinner.setAdapter(loaiDoUongAdapterSpinner);

        Button btnLuu = dialog.findViewById(R.id.btnSaveDU);
        Button btnHuy = dialog.findViewById(R.id.btnCancelDU);

        edTenDU.setText(objDoUong.getTenDU()+"");
        edGiaDU.setText(objDoUong.getGiaDU()+"");
        edSoLuongDU.setText(objDoUong.getSoLuongDU()+"");
        edSizeDU.setText(objDoUong.getSizeDU()+"");

        ArrayList<LoaiDoUong> listLoaiDoUong = (ArrayList<LoaiDoUong>) loaiDoUongDAO.getAll();

        for (int j = 0; j<listLoaiDoUong.size();j++){
            LoaiDoUong objLoaiDoUong = listLoaiDoUong.get(j);
            if(objLoaiDoUong.getMaLoaiDU()==objDoUong.getMaLoaiDU()){
                spinner.setSelection(j);
                spinner.setSelected(true);
                break;
            }
        }

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                objDoUong.setTenDU(edTenDU.getText().toString());
                objDoUong.setGiaDU(Integer.parseInt(edGiaDU.getText().toString()));
                objDoUong.setSoLuongDU(Integer.parseInt(edSoLuongDU.getText().toString()));
                objDoUong.setSizeDU(edSizeDU.getText().toString());

                LoaiDoUong objLoaiDoUong = (LoaiDoUong) spinner.getSelectedItem();

                objDoUong.setMaLoaiDU(objLoaiDoUong.getMaLoaiDU());
                objDoUong.setTenLoaiDU(objLoaiDoUong.getTenLoaiDU());

                int kq = doUongDAO.updateRow(objDoUong);

                if(kq>0){
                    listDoUongs.set(vitri,objDoUong);
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
