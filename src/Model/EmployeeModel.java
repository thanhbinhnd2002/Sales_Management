package Model;

import java.util.Date;

public class EmployeeModel {
    private String maNhanVien;
    private String hoTenNhanVien;
    private String diaChiNhanVien;
    private String sdtNhanVien;
    private String chucVuNhanVien;
    private Date ngaySinhNhanVien;
    private Date ngayBatDauLam;
    private String gioiTinh;

    public EmployeeModel(){

    }
    public EmployeeModel(String maNhanVien, String hoTenNhanVien, String diaChiNhanVien, String sdtNhanVien,
                         String chucVuNhanVien, Date ngaySinhNhanVien, Date ngayBatDauLam, String gioiTinh) {
        this.maNhanVien = maNhanVien;
        this.hoTenNhanVien = hoTenNhanVien;
        this.diaChiNhanVien = diaChiNhanVien;
        this.sdtNhanVien = sdtNhanVien;
        this.chucVuNhanVien = chucVuNhanVien;
        this.ngaySinhNhanVien = ngaySinhNhanVien;
        this.ngayBatDauLam = ngayBatDauLam;
        this.gioiTinh = gioiTinh;
    }

    public String getHoTenNhanVien() {
        return hoTenNhanVien;
    }

    public void setHoTenNhanVien(String hoTenNhanVien) {
        this.hoTenNhanVien = hoTenNhanVien;
    }

    public String getDiaChiNhanVien() {
        return diaChiNhanVien;
    }

    public void setDiaChiNhanVien(String diaChiNhanVien) {
        this.diaChiNhanVien = diaChiNhanVien;
    }

    public String getSdtNhanVien() {
        return sdtNhanVien;
    }

    public void setSdtNhanVien(String sdtNhanVien) {
        this.sdtNhanVien = sdtNhanVien;
    }

    public String getChucVuNhanVien() {
        return chucVuNhanVien;
    }

    public void setChucVuNhanVien(String chucVuNhanVien) {
        this.chucVuNhanVien = chucVuNhanVien;
    }

    public Date getNgaySinhNhanVien() {
        return ngaySinhNhanVien;
    }

    public void setNgaySinhNhanVien(Date ngaySinhNhanVien) {
        this.ngaySinhNhanVien = ngaySinhNhanVien;
    }

    public Date getNgayBatDauLam() {
        return ngayBatDauLam;
    }

    public void setNgayBatDauLam(Date ngayBatDauLam) {
        this.ngayBatDauLam = ngayBatDauLam;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }
}
