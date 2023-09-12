package Controller;

import Model.Database;
import Model.ProductModel;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ProductController {
    public static List<ProductModel> getAllProducts(Database database) throws SQLException {
        List<ProductModel> listProduct = new ArrayList<>();
        Connection conn = DatabaseConnection.getConnection(database);
        if (conn != null) {
            CallableStatement statement = conn.prepareCall("{ CALL sp_Product_GetAll() }");
            ResultSet rs = statement.executeQuery();
            while (rs != null && rs.next()) {
                ProductModel productModel = new ProductModel();
                productModel.setMaSanPham(rs.getString("Ma"));
                productModel.setTenSanPham(rs.getString("Ten"));
                productModel.setDonVi(rs.getString("DonVi"));
                productModel.setLoai(rs.getString("Loai"));
                try {
                    productModel.setHan(new SimpleDateFormat("dd/MM/yyyy").parse(rs.getString("Han")));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                productModel.setGia(rs.getString("Gia"));
                productModel.setSoLuong(rs.getString("SoLuong"));
                listProduct.add(productModel);
            }
        }
        return listProduct;
    }

    public static Boolean deleteProduct(Database database, String MaSP) throws SQLException {
        Connection conn = DatabaseConnection.getConnection(database);
        if (conn != null) {
            CallableStatement statement = conn.prepareCall("{ CALL sp_Product_Delete(?)} ");
            statement.setString(1, MaSP);
            int result = statement.executeUpdate();
            return result != 0;
        } else return false;
    }

    public static Boolean checkProduct(Database database, String MaSP) throws SQLException {
        Connection conn = DatabaseConnection.getConnection(database);
        if (conn != null) {
            CallableStatement statement = conn.prepareCall("{ CALL sp_Product_Check(?) }");
            statement.setString(1, MaSP);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }
        return false;
    }

    public static Boolean addProduct(Database database, ProductModel product) throws SQLException {
        Connection conn = DatabaseConnection.getConnection(database);
        if (conn != null) {
            CallableStatement statement = conn.prepareCall("{ CALL sp_Product_Add(?,?,?,?,?,?,?) }");
            statement.setString(1, product.getMaSanPham());
            statement.setString(2, product.getTenSanPham());
            statement.setString(3, product.getDonVi());
            statement.setString(4, product.getLoai());
            statement.setString(5, new SimpleDateFormat("dd/MM/yyyy").format(product.getHan()));
            statement.setString(6, product.getGia());
            statement.setString(7, product.getSoLuong());
            int result = statement.executeUpdate();
            return result != 0;
        }
        return false;
    }

    public static Boolean updateProduct(Database database, ProductModel product) throws SQLException {
        Connection conn = DatabaseConnection.getConnection(database);
        if (conn != null) {
            CallableStatement statement = conn.prepareCall("{CALL sp_Product_Update(?,?,?,?,?,?,?)}");
            statement.setString(1, product.getMaSanPham());
            statement.setString(2, product.getTenSanPham());
            statement.setString(3, product.getDonVi());
            statement.setString(4, product.getLoai());
            statement.setString(5, new SimpleDateFormat("dd/MM/yyyy").format(product.getHan()));
            statement.setString(6, product.getGia());
            statement.setString(7, product.getSoLuong());
            int result = statement.executeUpdate();
            return result != 0;
        }
        return false;
    }
}

