package Model;

import java.util.Date;

public class ProductModel {
    private String MaSanPham;
    private String TenSanPham;
    private String Loai;
    private String DonVi;
    private Date Han;
    private String Gia;
    private String SoLuong;
    public ProductModel(){

    }

    public ProductModel(String maSanPham, String tenSanPham, String loai, String donVi, Date han, String gia, String soLuong) {
        this.MaSanPham = maSanPham;
        this.TenSanPham = tenSanPham;
        this.Loai = loai;
        this.DonVi = donVi;
        this.Han = han;
        this.Gia = gia;
        this.SoLuong = soLuong;
    }

    public String getMaSanPham() {
        return MaSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.MaSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.TenSanPham = tenSanPham;
    }

    public String getLoai() {
        return Loai;
    }

    public void setLoai(String loai) {
        this.Loai = loai;
    }

    public String getDonVi() {
        return DonVi;
    }

    public void setDonVi(String donVi) {
        this.DonVi = donVi;
    }

    public Date getHan() {
        return Han;
    }

    public void setHan(Date han) {
        this.Han = han;
    }

    public String getGia() {
        return Gia;
    }

    public void setGia(String gia) {
        this.Gia = gia;
    }

    public String getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(String soLuong) {
        this.SoLuong = soLuong;
    }
}
