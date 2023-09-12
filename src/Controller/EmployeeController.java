package Controller;

import Model.Database;
import Model.EmployeeModel;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeController {
    public static List<EmployeeModel> getAllEmployee(Database database) throws SQLException {
        List<EmployeeModel> listEmployee = new ArrayList<>();
        Connection conn = DatabaseConnection.getConnection(database);
        if (conn != null) {
            CallableStatement statement = conn.prepareCall("{ CALL sp_Employee_GetAll() }");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                EmployeeModel employeeModel = new EmployeeModel();
                employeeModel.setMaNhanVien(rs.getString("MaNV"));
                employeeModel.setHoTenNhanVien(rs.getString("HoTenNV"));
                employeeModel.setDiaChiNhanVien(rs.getString("DiaChiNV"));
                employeeModel.setSdtNhanVien(rs.getString("SdtNV"));
                employeeModel.setChucVuNhanVien(rs.getString("ChucVu"));
                try {
                    employeeModel.setNgaySinhNhanVien(new SimpleDateFormat("dd/MM/yyyy").parse(rs.getString("NgaySinh")));
                    employeeModel.setNgayBatDauLam(new SimpleDateFormat("dd/MM/yyyy").parse(rs.getString("NgayLamViec")));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                employeeModel.setGioiTinh(rs.getString("GioiTinh"));
                listEmployee.add(employeeModel);
            }
        }
        return listEmployee;
    }

    public static boolean deleteEmployee(Database database, String maNhanVien) throws SQLException {
        Connection conn = DatabaseConnection.getConnection(database);
        if (conn != null) {
            CallableStatement statement = conn.prepareCall("{ CALL sp_Employee_Delete(?) }");
            statement.setString(1, maNhanVien);
            int result = statement.executeUpdate();
            return result != 0;
        }
        return false;
    }

    public static boolean checkEmployee(Database database, String maNhanVien) throws SQLException {
        Connection conn = DatabaseConnection.getConnection(database);
        if (conn != null) {
            CallableStatement statement = conn.prepareCall("{ CALL sp_Employee_Check(?)}");
            statement.setString(1, maNhanVien);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }
        return false;
    }

    public static boolean addEmployee(Database database, EmployeeModel employee) throws SQLException {
        Connection conn = DatabaseConnection.getConnection(database);
        if (conn != null) {
            CallableStatement statement = conn.prepareCall("{CALL sp_Employee_Add(?,?,?,?,?,?,?,?)}");
            statement.setString(1, employee.getMaNhanVien());
            statement.setString(2, employee.getHoTenNhanVien());
            statement.setString(3, employee.getDiaChiNhanVien());
            statement.setString(4, employee.getSdtNhanVien());
            statement.setString(5, employee.getChucVuNhanVien());
            Date ngaySinhNV = employee.getNgaySinhNhanVien();
            Date ngayBatDauLam = employee.getNgayBatDauLam();
            statement.setString(6,
                    new SimpleDateFormat("dd/MM/yyyy").format(ngaySinhNV));
            statement.setString(7,
                    new SimpleDateFormat("dd/MM/yyyy").format(ngayBatDauLam));
            statement.setString(8, employee.getGioiTinh());
            int result = statement.executeUpdate();
            return result != 0;
        }
        return false;
    }

    public static boolean editEmployee(Database database, EmployeeModel employee) throws SQLException {
        Connection conn = DatabaseConnection.getConnection(database);
        if (conn != null) {
            CallableStatement statement = conn.prepareCall("{CALL sp_Employee_Update(?,?,?,?,?,?,?,?)}");
            statement.setString(1, employee.getMaNhanVien());
            statement.setString(2, employee.getHoTenNhanVien());
            statement.setString(3, employee.getDiaChiNhanVien());
            statement.setString(4, employee.getSdtNhanVien());
            statement.setString(5, employee.getChucVuNhanVien());
            Date ngaySinhNV = employee.getNgaySinhNhanVien();
            Date ngayBatDauLam = employee.getNgayBatDauLam();
            statement.setString(6,
                    new SimpleDateFormat("dd/MM/yyyy").format(ngaySinhNV));
            statement.setString(7,
                    new SimpleDateFormat("dd/MM/yyyy").format(ngayBatDauLam));
            statement.setString(8, employee.getGioiTinh());
            int result = statement.executeUpdate();
            return result != 0;
        }
        return false;
    }
}
