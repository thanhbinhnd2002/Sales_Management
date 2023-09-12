package Controller;

import Model.Database;
import Model.SupplierModel;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierController {
    public static List<SupplierModel> getAllSuppliers(Database database) throws SQLException {
        List<SupplierModel> listSupplier = new ArrayList<>();
        Connection conn = DatabaseConnection.getConnection(database);
        if (conn != null) {
            CallableStatement statement = conn.prepareCall("{ CALL sp_Supplier_GetAll() }");
            ResultSet rs = statement.executeQuery();
            while (rs != null && rs.next()) {
                SupplierModel supplierModel = new SupplierModel();
                supplierModel.setMaNhaCungCap(rs.getString("Ma"));
                supplierModel.setTenNhaCungCap(rs.getString("Ten"));
                supplierModel.setDiaChi(rs.getString("DiaChi"));
                supplierModel.setSoDienThoai(rs.getString("SoDienThoai"));
                supplierModel.setSoTaiKhoan(rs.getString("SoTaiKhoan"));
                listSupplier.add(supplierModel);
            }
        }
        return listSupplier;
    }

    public static boolean deleteSupplier(Database database, String maNCC) throws SQLException {
        Connection conn = DatabaseConnection.getConnection(database);
        if (conn != null) {
            CallableStatement statement = conn.prepareCall("{ CALL sp_Supplier_Delete(?)} ");
            statement.setString(1, maNCC);
            int result = statement.executeUpdate();
            return result != 0;
        }
        return false;
    }

    public static boolean checkSupplier(Database database, String maNCC) throws SQLException {
        Connection conn = DatabaseConnection.getConnection(database);
        if (conn != null) {
            CallableStatement statement = conn.prepareCall("{ CALL sp_Supplier_Check(?) }");
            statement.setString(1, maNCC);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }
        return false;
    }

    public static boolean addSupplier(Database database, SupplierModel supplier) throws SQLException {
        Connection conn = DatabaseConnection.getConnection(database);
        if (conn != null) {
            CallableStatement statement = conn.prepareCall("{ CALL sp_Supplier_Add(?,?,?,?,?) }");
            statement.setString(1, supplier.getMaNhaCungCap());
            statement.setString(2, supplier.getTenNhaCungCap());
            statement.setString(3, supplier.getDiaChi());
            statement.setString(4, supplier.getSoDienThoai());
            statement.setString(5, supplier.getSoTaiKhoan());
            int result = statement.executeUpdate();
            return result != 0;
        }
        return false;
    }

    public static boolean updateSupplier(Database database, SupplierModel supplier) throws SQLException {
        Connection conn = DatabaseConnection.getConnection(database);
        if (conn != null) {
            CallableStatement statement = conn.prepareCall("{CALL sp_Supplier_Update(?,?,?,?,?)}");
            statement.setString(1, supplier.getMaNhaCungCap());
            statement.setString(2, supplier.getTenNhaCungCap());
            statement.setString(3, supplier.getDiaChi());
            statement.setString(4, supplier.getSoDienThoai());
            statement.setString(5, supplier.getSoTaiKhoan());
            int result = statement.executeUpdate();
            return result!=0;
        }
        return false;
    }
}
