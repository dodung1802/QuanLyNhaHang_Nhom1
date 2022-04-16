package fpt.thanhluan.quanlynhahang.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import fpt.thanhluan.quanlynhahang.DTO.DoAn;
import fpt.thanhluan.quanlynhahang.DTO.TopDoAn;
import fpt.thanhluan.quanlynhahang.DTO.TopDoUong;
import fpt.thanhluan.quanlynhahang.database.DbHelper;

public class TopDoAnDAO {
DbHelper dbHelper;
    SQLiteDatabase db;
    Context context;

    public TopDoAnDAO(Context context) {
        this.context = context;
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public List<TopDoAn> GetTop() {
        // gới hạn 10 kết quả từ trên xuống

        String sql="SELECT MaDA,TenDA,COUNT(MaDA) AS SoLuongDA FROM HoaDon INNER JOIN DoAn ON DoAn.MaDA=HoaDon.MaDA GROUP BY MaDA ORDER BY SoLuongDA DESC LIMIT 10";

        List<TopDoAn> list = new ArrayList<>();
        DoAnDAO doAnDAO = new DoAnDAO(context);
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            TopDoAn topDoAn = new TopDoAn();
//            DoAn doAn = doAnDAO.getId(cursor.getString(cursor.getColumnIndex("MaDoAn")));

            topDoAn.tendoan= cursor.getString(1);
            topDoAn.soluongdoan=cursor.getInt(2);
            list.add(topDoAn);
        }
        Log.d("zzzzzzzzz", list.toString());
        return list;
    };




}
