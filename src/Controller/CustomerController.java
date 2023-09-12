package Controller;

import Model.CustomerModel;
import Model.Database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerController {
    public static List<CustomerModel> getAllCustomer(Database database) throws SQLException {
        List<CustomerModel> listCustomer = new ArrayList<>();
        Connection conn = DatabaseConnection.getConnection(database);
        if (conn != null) {
            CallableStatement statement = ((Connection) conn).prepareCall("{ CALL sp_Customer_GetAll() }");
            ResultSet rs = statement.executeQuery();
            while (rs != null && rs.next()) {
                CustomerModel customerModel = new CustomerModel();
                customerModel.setMaKhachHang(rs.getString("Ma"));
                customerModel.setTenKhachHang(rs.getString("Ten"));
                customerModel.setDiaChi(rs.getString("DiaChi"));
                customerModel.setSoDienThoai(rs.getString("SDT"));
                listCustomer.add(customerModel);
            }
        }
        return listCustomer;
    }

    public static boolean deleteCustomer(Database database, String maKhachHang) throws SQLException {
        Connection conn = DatabaseConnection.getConnection(database);
        if (conn != null) {
            CallableStatement statement = conn.prepareCall("{ CALL sp_Customer_Delete(?) }");
            statement.setString(1, maKhachHang);
            int result = statement.executeUpdate();
            return result != 0;
        }
        return false;
    }

    public static boolean checkCustomer(Database database, String maKhachHang) throws SQLException {
        Connection conn = DatabaseConnection.getConnection(database);
        if (conn != null) {
            CallableStatement statement = conn.prepareCall("{ CALL sp_Customer_Check(?)}");
            statement.setString(1, maKhachHang);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }
        return false;
    }

    public static boolean addCustomer(Database database, CustomerModel customer) throws SQLException {
        Connection conn = DatabaseConnection.getConnection(database);
        if (conn != null) {
            CallableStatement statement = conn.prepareCall("{CALL sp_Customer_Add(?,?,?,?)}");
            statement.setString(1, customer.getMaKhachHang());
            statement.setString(2, customer.getTenKhachHang());
            statement.setString(3, customer.getDiaChi());
            statement.setString(4, customer.getSoDienThoai());
            int result = statement.executeUpdate();
            return result != 0;
        }
        return false;
    }

    public static boolean editCustomer(Database database, CustomerModel customer) throws SQLException {
        Connection conn = DatabaseConnection.getConnection(database);
        if (conn != null) {
            CallableStatement statement = conn.prepareCall("{CALL sp_Customer_Update(?,?,?,?)}");
            statement.setString(1, customer.getMaKhachHang());
            statement.setString(2, customer.getTenKhachHang());
            statement.setString(3, customer.getDiaChi());
            statement.setString(4, customer.getSoDienThoai());
            int result = statement.executeUpdate();
            return result != 0;
        }
        return false;
    }
}



