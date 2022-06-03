package sampleapp.dao;

import sampleapp.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class CurdUtil {
    private static Status status;

      public static <T>T execute(String sql, Object... params) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        //-------------------------------------------set object-----------------------------------------
        for (int i = 0; i < params.length; i++) {
            stm.setObject((i+1), params[i]);
        }
        if (sql.toUpperCase().startsWith("SELECT ")){
            return (T) stm.executeQuery();
        }
        boolean isSave = stm.executeUpdate() > 0;
        Integer integer=-1;
        ResultSet rst = stm.getGeneratedKeys();
        if (rst.next()){
            integer = rst.getInt(1);
        }
        Status status=new Status(isSave,integer);

        return (T) status;
    }
}
