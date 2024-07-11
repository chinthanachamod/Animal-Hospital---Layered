package lk.ijse.dao;

import lk.ijse.dbConnection.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlUtil {
    public static <T>T executeSql(String sql,Object... obj) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        int index = 0;
        for (Object object : obj) {
            pstm.setObject(++index,object);
        }
        if (sql.startsWith("SELECT")){
            return (T)pstm.executeQuery();
        }
        return (T) (Boolean)(pstm.executeUpdate()<0);
    }
}
