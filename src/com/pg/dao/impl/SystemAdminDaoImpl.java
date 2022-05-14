package com.pg.dao.impl;

import com.pg.dao.SystemAdminDao;
import com.pg.entity.SystemAdmin;
import com.pg.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SystemAdminDaoImpl implements SystemAdminDao {
    @Override
    public SystemAdmin findByUsername(String username) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "select * from system_admin where username = '"+username+"'";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                Integer id = resultSet.getInt(1);
                username  = resultSet.getString(2);
                String password = resultSet.getString(3);
                String name = resultSet.getString(4);
                String telephone = resultSet.getString(5);
                SystemAdmin systemAdmin = new SystemAdmin(id,username,password,name,telephone);
                return  systemAdmin;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.release(connection,statement,resultSet);
        }
        return null;

    }
}
