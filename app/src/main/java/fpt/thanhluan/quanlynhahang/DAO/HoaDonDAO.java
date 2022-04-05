package fpt.thanhluan.quanlynhahang.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fpt.thanhluan.quanlynhahang.DTO.HoaDon;
import fpt.thanhluan.quanlynhahang.DTO.NhanVien;
import fpt.thanhluan.quanlynhahang.database.DbHelper;

public class HoaDonDAO {

    SQLiteDatabase db;
    DbHelper dbHelper;

    public HoaDonDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public void open(){
        db = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public long insertRow(HoaDon objHoaDon){
        ContentValues values = new ContentValues();
        values.put(HoaDon.COL_NGAY,objHoaDon.getNgay());
        values.put(HoaDon.COL_TRANGTHAI,objHoaDon.getTrangThai());
        values.put(HoaDon.COL_TONGTIEN,objHoaDon.getTongTien());
        values.put(HoaDon.COL_MAPHONG,objHoaDon.getMaPhong());
        values.put(HoaDon.COL_MADU,objHoaDon.getMaDU());
        values.put(HoaDon.COL_MADA,objHoaDon.getMaDA());
        values.put(HoaDon.COL_MABAN,objHoaDon.getMaBan());
        values.put(HoaDon.COL_MAKH,objHoaDon.getMaKH());

        long res = db.insert(HoaDon.TB_NAME,null,values);

        return res;
    }

    public int deleteRow(HoaDon objHoaDon){
        String [] arr = new String[]{objHoaDon.getMaHD()+""};

        int res = db.delete(HoaDon.TB_NAME,"MaHD = ?",arr);
        return res;
    }

    public int updateRow(HoaDon objHoaDon){
        ContentValues values = new ContentValues();
        values.put(HoaDon.COL_NGAY,objHoaDon.getNgay());
        values.put(HoaDon.COL_TRANGTHAI,objHoaDon.getTrangThai());
        values.put(HoaDon.COL_TONGTIEN,objHoaDon.getTongTien());
        values.put(HoaDon.COL_MAPHONG,objHoaDon.getMaPhong());
        values.put(HoaDon.COL_MADU,objHoaDon.getMaDU());
        values.put(HoaDon.COL_MADA,objHoaDon.getMaDA());
        values.put(HoaDon.COL_MABAN,objHoaDon.getMaBan());
        values.put(HoaDon.COL_MAKH,objHoaDon.getMaKH());

        String [] arr = new String[]{objHoaDon.getMaHD()+""};

        int res = db.update(HoaDon.TB_NAME,values,"MaHD = ?",arr);

        return res;
    }

    public ArrayList<HoaDon> getAll(){
        ArrayList<HoaDon> dsHoaDon = new ArrayList<>();

        String[] ds_hoadon = new String[]{"*"};

        Cursor cursor = db.query(HoaDon.TB_NAME,ds_hoadon,null,null,null,null,null);

        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                HoaDon objHoaDon = new HoaDon();

                objHoaDon.setMaHD(cursor.getInt(0));
                objHoaDon.setNgay(cursor.getString(1));
                objHoaDon.setTrangThai(cursor.getInt(2));
                objHoaDon.setTongTien(cursor.getInt(3));
                objHoaDon.setMaPhong(cursor.getInt(4));
                objHoaDon.setMaDU(cursor.getInt(5));
                objHoaDon.setMaDA(cursor.getInt(6));
                objHoaDon.setMaBan(cursor.getInt(7));
                objHoaDon.setMaKH(cursor.getInt(8));

                dsHoaDon.add(objHoaDon);
                cursor.moveToNext();
            }
        }
        return dsHoaDon;
    }

}
