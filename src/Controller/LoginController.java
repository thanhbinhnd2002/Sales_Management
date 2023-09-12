package Controller;

import Model.Database;
import Model.LoginModel;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    public static boolean checkData(Database database, LoginModel login) throws SQLException {
        Connection conn = DatabaseConnection.getConnection(database);
        if(conn!=null){
            CallableStatement statement = conn.prepareCall("{ CALL sp_Accounts_Check(?,?) }");
            statement.setString(1, login.getUserName());
            statement.setString(2, login.getPassword());
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }
        return false;
    }
}
