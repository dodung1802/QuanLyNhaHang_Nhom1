package fpt.thanhluan.quanlynhahang.DTO;

public class HoaDon {

    public int MaHD;
    public String Ngay;
    public int TrangThai;
    public int TongTien;
    public int MaPhong;
    public int MaDU;
    public int MaDA;
    public int MaBan;
    public int MaKH;

    public static final String TB_NAME = "HoaDon";
    public static final String COL_MAHD = "MaHD";
    public static final String COL_NGAY = "Ngay";
    public static final String COL_TRANGTHAI = "TrangThai";
    public static final String COL_TONGTIEN = "TongTien";
    public static final String COL_MAPHONG = "MaPhong";
    public static final String COL_MADU = "MaDU";
    public static final String COL_MADA = "MaDA";
    public static final String COL_MABAN = "MaBan";
    public static final String COL_MAKH = "MaKH";

    public HoaDon() {

    }

    public int getMaHD() {
        return MaHD;
    }

    public void setMaHD(int maHD) {
        MaHD = maHD;
    }

    public String getNgay() {
        return Ngay;
    }

    public void setNgay(String ngay) {
        Ngay = ngay;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int trangThai) {
        TrangThai = trangThai;
    }

    public int getTongTien() {
        return TongTien;
    }

    public void setTongTien(int tongTien) {
        TongTien = tongTien;
    }

    public int getMaPhong() {
        return MaPhong;
    }

    public void setMaPhong(int maPhong) {
        MaPhong = maPhong;
    }

    public int getMaDU() {
        return MaDU;
    }

    public void setMaDU(int maDU) {
        MaDU = maDU;
    }

    public int getMaDA() {
        return MaDA;
    }

    public void setMaDA(int maDA) {
        MaDA = maDA;
    }

    public int getMaBan() {
        return MaBan;
    }

    public void setMaBan(int maBan) {
        MaBan = maBan;
    }

    public int getMaKH() {
        return MaKH;
    }

    public void setMaKH(int maKH) {
        MaKH = maKH;
    }
}
