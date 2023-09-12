package Model;

public class SupplierModel {
    private  String maNhaCungCap;
    private  String tenNhaCungCap;
    private String DiaChi;
    private String SoDienThoai;
    private String SoTaiKhoan;
    public SupplierModel(){

    }

    public String getMaNhaCungCap(){
        return maNhaCungCap;
    }
    public void setMaNhaCungCap(String maNhaCungCap){
        this.maNhaCungCap = maNhaCungCap;
    }

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        SoDienThoai = soDienThoai;
    }

    public String getSoTaiKhoan() {
        return SoTaiKhoan;
    }

    public void setSoTaiKhoan(String soTaiKhoan) {
        SoTaiKhoan = soTaiKhoan;
    }
}