package fpt.thanhluan.quanlynhahang.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import fpt.thanhluan.quanlynhahang.AdapterSpinner.BanAdapterSpinner;
import fpt.thanhluan.quanlynhahang.AdapterSpinner.KhachHangAdapterSpinner;


import fpt.thanhluan.quanlynhahang.AdapterSpinner.PhongAdapterSpinner;
import fpt.thanhluan.quanlynhahang.Adapter_Lv_HoaDon.DoAn_LV_Adapter;
import fpt.thanhluan.quanlynhahang.Adapter_Lv_HoaDon.DoUong_LV_Adapter;
import fpt.thanhluan.quanlynhahang.DAO.BanDAO;
import fpt.thanhluan.quanlynhahang.DAO.DoAnDAO;
import fpt.thanhluan.quanlynhahang.DAO.DoUongDAO;
import fpt.thanhluan.quanlynhahang.DAO.HoaDonDAO;
import fpt.thanhluan.quanlynhahang.DAO.KhachHangDAO;
import fpt.thanhluan.quanlynhahang.DAO.PhongDAO;
import fpt.thanhluan.quanlynhahang.DTO.Ban;
import fpt.thanhluan.quanlynhahang.DTO.DoAn;
import fpt.thanhluan.quanlynhahang.DTO.DoUong;
import fpt.thanhluan.quanlynhahang.DTO.HoaDon;
import fpt.thanhluan.quanlynhahang.DTO.KhachHang;
import fpt.thanhluan.quanlynhahang.DTO.Phong;
import fpt.thanhluan.quanlynhahang.R;

public class HoaDonAdapter extends BaseAdapter {
    EditText edngayHD;
    Context context;
    ArrayList<HoaDon> listHoaDons;
    HoaDonDAO hoaDonDAO;
    DatePicker datePicker;

    public HoaDonAdapter(Context context, ArrayList<HoaDon> listHoaDons, HoaDonDAO hoaDonDAO) {
        this.context = context;
        this.listHoaDons = listHoaDons;
        this.hoaDonDAO = hoaDonDAO;
    }

    @Override
    public int getCount() {
        return listHoaDons.size();
    }

    @Override
    public Object getItem(int position) {
        HoaDon objHoaDon = listHoaDons.get(position);
        return objHoaDon;
    }

    @Override
    public long getItemId(int position) {
        HoaDon objHoaDon = listHoaDons.get(position);
        return objHoaDon.getMaHD();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View itemview;

        if (view == null) {
            itemview = View.inflate(viewGroup.getContext(), R.layout.item_hoadon, null);
        } else itemview = view;
        final HoaDon objHoaDon = listHoaDons.get(position);

        final int _index = position;
        //anhs xa

        TextView tvMaHoaDon = itemview.findViewById(R.id.tvMaHoaDon);
        TextView tvTenKhachHang = itemview.findViewById(R.id.tvTenKH);
        TextView tvTenDA = itemview.findViewById(R.id.tvTenDA);
        TextView tvSoLuongDA = itemview.findViewById(R.id.tvSoLuongDA);
        TextView tvTenDU = itemview.findViewById(R.id.tvDU);
        TextView tvSoLuongDU = itemview.findViewById(R.id.tvSoLuongDU);
        TextView tvSizeDU = itemview.findViewById(R.id.tvSizeDU);
        TextView tvNgay = itemview.findViewById(R.id.tvNgay);
        TextView tvGia = itemview.findViewById(R.id.tvGia);
        TextView tvban = itemview.findViewById(R.id.tvban);
        TextView tvphong = itemview.findViewById(R.id.tvphong);
        TextView tvXoa = itemview.findViewById(R.id.tvXoa);


        //set text
        tvMaHoaDon.setText("M?? ho?? ????n:" + objHoaDon.getMaHD() + "");
        tvTenKhachHang.setText("T??n KH:" + objHoaDon.getTenKH() + "");
        tvTenDA.setText("T??n DA:" + objHoaDon.getTenDA() + "");
        tvSoLuongDA.setText("S??? L?????ng:" + objHoaDon.getSoLuongDA() + "");
        tvTenDU.setText("T??n DU:" + objHoaDon.getTenDU() + "");
        tvSoLuongDU.setText("S??? L?????ng:" + objHoaDon.getSoLuongDU() + "");
        tvSizeDU.setText("Size:" + objHoaDon.getSizeDU() + "");
        tvNgay.setText("Ng??y:" + objHoaDon.getNgay() + "");
        tvGia.setText("T???ng Ti???n:" + objHoaDon.getTongTien() + "");
        tvban.setText("B??n:" + objHoaDon.getMaBan() + "");
        tvphong.setText("Ph??ng:" + objHoaDon.getMaPhong() + "");

        tvXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete");
                builder.setIcon(android.R.drawable.ic_delete);
                builder.setMessage("B???n c?? mu???n x??a kh??ng ?");
                builder.setPositiveButton("C??", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int kq = hoaDonDAO.deleteRow(objHoaDon);
                        if (kq > 0) {
                            listHoaDons.remove(position);
                            notifyDataSetChanged();
                            Toast.makeText(context, "X??a th??nh c??ng", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "X??a th???t b???i", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Kh??ng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                Dialog dialog = builder.create();
                dialog.show();
            }
        });
        return itemview;
    }

    public void showDialogAdd() {
        Dialog dialog = new Dialog(context, R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_hoa_don);

        TextInputEditText edngayHD = dialog.findViewById(R.id.edngayHD);
        TextInputEditText edSoLuongDUHD = dialog.findViewById(R.id.edSoLuongDUHD);
        TextInputEditText edSoLuongDAHD = dialog.findViewById(R.id.edSoLuongDAHD);
        Button btnchonDoAn = dialog.findViewById(R.id.btnchonDoAn);
        Button btnchonDoUong = dialog.findViewById(R.id.btnchonDoUong);

        Button btnLuu = dialog.findViewById(R.id.btnSaveHoaDon);
        Button btnHuy = dialog.findViewById(R.id.btnCancelHoaDon);

//Spinerr Kh??ch H??ng
        KhachHangDAO khachHangDAO = new KhachHangDAO(context);
        khachHangDAO.open();

        Spinner spinnerKH = dialog.findViewById(R.id.spinnerMaKH);

        KhachHangAdapterSpinner khachHangAdapterSpinner = new KhachHangAdapterSpinner((ArrayList<KhachHang>) khachHangDAO.getAll());
        spinnerKH.setAdapter(khachHangAdapterSpinner);


//Spinerr Ph??ng
        PhongDAO phongDAO = new PhongDAO(context);
        phongDAO.open();

        Spinner spinnerPhong = dialog.findViewById(R.id.spinnerPhong);

        PhongAdapterSpinner phongAdapterSpinner = new PhongAdapterSpinner((ArrayList<Phong>) phongDAO.getAll());
        spinnerPhong.setAdapter(phongAdapterSpinner);
//Spinerr B??n
        BanDAO banDAO = new BanDAO(context);
        banDAO.open();

        Spinner spinnerBan = dialog.findViewById(R.id.spinnerBan);

        BanAdapterSpinner banAdapterSpinner = new BanAdapterSpinner((ArrayList<Ban>) banDAO.getAll());
        spinnerBan.setAdapter(banAdapterSpinner);

//N??t CH???n ????? ??n
        btnchonDoAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(context, R.style.Theme_AppCompat_Light_Dialog_Alert);
                dialog.setContentView(R.layout.dialog_lv_do_an_hoadon);
                DoAnDAO doAnDAO = new DoAnDAO(context);
                doAnDAO.open();
                ListView listViewDoAn = dialog.findViewById(R.id.lv_DoAn_HD);

                DoAn_LV_Adapter doAn_lv_adapter = new DoAn_LV_Adapter((ArrayList<DoAn>) doAnDAO.getAll());
                listViewDoAn.setAdapter(doAn_lv_adapter);
                Button btncanle = dialog.findViewById(R.id.btncanle);
                btncanle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });


                dialog.show();
            }
        });
//N??t CH???n ????? U???ng
        btnchonDoUong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(context, R.style.Theme_AppCompat_Light_Dialog_Alert);
                dialog.setContentView(R.layout.dialog_lv_do_uong_hoadon);
                DoUongDAO doUongDAO = new DoUongDAO(context);
                doUongDAO.open();
                ListView listViewDoUong = dialog.findViewById(R.id.lv_DoUong_HD);

                DoUong_LV_Adapter doUong_lv_adapter = new DoUong_LV_Adapter((ArrayList<DoUong>) doUongDAO.getAll());
                listViewDoUong.setAdapter(doUong_lv_adapter);
                Button btncanle = dialog.findViewById(R.id.btncanle);
                btncanle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });


                dialog.show();
            }
        });
//N??t B???m (L??u vs H???y)

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });



        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HoaDon objHoaDon = new HoaDon();

                objHoaDon.setNgay(edngayHD.getText().toString());

                objHoaDon.setSoLuongDU(Integer.parseInt(edSoLuongDUHD.getText().toString()));
                objHoaDon.setSoLuongDA(Integer.parseInt(edSoLuongDAHD.getText().toString()));

                KhachHang objKhachHang = (KhachHang) spinnerKH.getSelectedItem();
                objHoaDon.setMaKH(objKhachHang.getMaKH());
                objHoaDon.setTenKH(objKhachHang.getTenKH());

                Phong objPhong = (Phong) spinnerPhong.getSelectedItem();
                objHoaDon.setMaPhong(objPhong.getMaPhong());


                Ban objBan = (Ban) spinnerBan.getSelectedItem();
                objHoaDon.setMaBan(objBan.getMaBan());



                long kq = hoaDonDAO.insertRow(objHoaDon);

                if (kq > 0) {
                    listHoaDons.clear();
                    listHoaDons.addAll(hoaDonDAO.getAll());
                    notifyDataSetChanged();
                    Toast.makeText(context, "Th??m th??nh c??ng", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Th??m th???t b???i", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });


        dialog.show();
    }


}
