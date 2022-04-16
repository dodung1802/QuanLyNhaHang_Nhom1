package fpt.thanhluan.quanlynhahang.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fpt.thanhluan.quanlynhahang.DTO.Ban;
import fpt.thanhluan.quanlynhahang.DTO.DoAn;
import fpt.thanhluan.quanlynhahang.DTO.LoaiDoAn;
import fpt.thanhluan.quanlynhahang.DTO.Phong;
import fpt.thanhluan.quanlynhahang.database.DbHelper;

public class DoAnDAO {

    DbHelper dbHelper;
    SQLiteDatabase db;

    public DoAnDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long insertRow(DoAn objDoAn) {
        ContentValues values = new ContentValues();
        values.put(DoAn.COL_TENDA, objDoAn.getTenDA());
        values.put(DoAn.COL_GIADA, objDoAn.getGiaDA());
        values.put(DoAn.COL_SOLUONGDA, objDoAn.getSoLuongDA());
        values.put(DoAn.COL_MALOAIDA, objDoAn.getMaLoaiDA());
        long res = db.insert(DoAn.TB_NAME, null, values);
        return res;
    }

    public int updateRow(DoAn objDoAn) {
        ContentValues values = new ContentValues();
        values.put(DoAn.COL_TENDA, objDoAn.getTenDA());
        values.put(DoAn.COL_GIADA, objDoAn.getGiaDA());
        values.put(DoAn.COL_SOLUONGDA, objDoAn.getSoLuongDA());
        values.put(DoAn.COL_MALOAIDA, objDoAn.getMaLoaiDA());

        String[] arr = new String[]{objDoAn.getMaDA() + ""};

        int res = db.update(DoAn.TB_NAME, values, "MaDA = ?", arr);
        return res;
    }

    public int deleteRow(DoAn objDoAn) {
        String[] arr = new String[]{objDoAn.getMaDA() + ""};

        int res = db.delete(DoAn.TB_NAME, "MaDA = ?", arr);
        return res;
    }

    public ArrayList<DoAn> getAll() {
        ArrayList<DoAn> dsDAn = new ArrayList<>();

        String[] ds_cot = new String[]{"*"};

//        Cursor cursor = db.query(DoAn.TB_NAME, ds_cot, null, null, null, null, null);

//        String Bang_doAn="SELECT MaDA,TenDA,GiaDA,SoLuongDA,LoaiDoAn.MaLoaiDA,TenLoaiDA"+"FROM DoAn INNER JOIN LoaiDoAn ON DoAn.MaLoaiDA=LoaiDoAn.MaLoaiDA WHERE ";

        String str_sql = "SELECT MaDA,TenDA,GiaDA,SoLuongDA,DoAn.MaLoaiDA,LoaiDoAn.TenLoaiDA" +
                " FROM DoAn INNER JOIN LoaiDoAn ON DoAn.MaLoaiDA = LoaiDoAn.MaLoaiDA";

        Cursor cursor = db.rawQuery(str_sql, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                DoAn objDoAn = new DoAn();
                objDoAn.setMaDA(cursor.getInt(0));
                objDoAn.setTenDA(cursor.getString(1));
                objDoAn.setGiaDA(cursor.getInt(2));
                objDoAn.setSoLuongDA(cursor.getInt(3));
                objDoAn.setMaLoaiDA(cursor.getInt(4));
                objDoAn.setTenLoaiDA(cursor.getString(5));

                dsDAn.add(objDoAn);
                cursor.moveToNext();
            }

        }


        return dsDAn;
    }


    public DoAn getOneWithLoaiDoAn(int id) {
        String[] args = new String[]{id + ""};

        DoAn objDoAn = new DoAn();

        String str_sql = "SELECT MaDA,TenDA,GiaDA,SoLuongDA,DoAn.MaLoaiDA,LoaiDoAn.TenLoaiDA" +
                " FROM DoAn INNER JOIN LoaiDoAn ON DoAn.MaLoaiDA = LoaiDoAn.MaLoaiDA WHERE MaDA = ?";

        Cursor cursor = db.rawQuery(str_sql, args);

        if (cursor.moveToFirst()) {

            objDoAn.setMaDA(cursor.getInt(0));
            objDoAn.setTenDA(cursor.getString(1));
            objDoAn.setGiaDA(cursor.getInt(2));
            objDoAn.setSoLuongDA(cursor.getInt(3));
            objDoAn.setMaLoaiDA(cursor.getInt(4));
        }
        return objDoAn;
    }

    public DoAn getId(String id) {
        String str_sql = "SELECT * FROM DoAn WHERE MaDA=" + id;

        Cursor cursor = db.rawQuery(str_sql, null);
        DoAn objDoAn = new DoAn();
        if (cursor.moveToFirst()) {


            objDoAn.setMaDA(cursor.getInt(0));
            objDoAn.setTenDA(cursor.getString(1));
            objDoAn.setGiaDA(cursor.getInt(2));
            objDoAn.setSoLuongDA(cursor.getInt(3));
            objDoAn.setMaLoaiDA(cursor.getInt(4));
            objDoAn.setTenLoaiDA(cursor.getString(5));


        }


        return objDoAn;
    }

}
