/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mobilemanagement.Dao;

import com.mobilemanagement.Model.UserLogin;
import com.mobilemanagement.Utility.JDBC;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DINHVU
 */
public class UserLoginDAO extends abstractDAO<UserLogin, String>{
    final String INSERT_SQL = "INSERT INTO UserLogin (UserName,PASSWORD,Vaitro) VALUES (?,?,?);";
    final String UPDATE_SQL = "UPDATE UserLogin SET UserName = ?, PASSWORD = ?, Vaitro = ? WHERE MaID = ?  ;";
    final String UPDATE_SQL_PASS = "UPDATE UserLogin SET PASSWORD = ? WHERE UserName = ?  ;";
    final String DELETE_SQL = "DELETE From UserLogin WHERE MaID = ?;";
    final String SELECT_ALL_SQL = "SELECT * FROM UserLogin;";
    final String Select_BYID_SQL = "SELECT * FROM UserLogin WHERE MaID = ?";
    final String Select_BYID_SQL_2 = "SELECT * FROM UserLogin WHERE UserName = ?";
    final String getVaiTro_SQL = "SELECT Vaitro FROM UserLogin WHERE UserName = ?;";
    
    
    @Override
    public void insert(UserLogin entity) {
        JDBC.update(INSERT_SQL ,entity.getUserName(),entity.getPASSWORD(),entity.isVaitro());
    }

    @Override
    public void update(UserLogin entity) {
        JDBC.update(UPDATE_SQL, entity.getUserName(),entity.getPASSWORD(),entity.isVaitro());
    }
    
    
    public void updatepass(UserLogin entity) {
        JDBC.update(UPDATE_SQL_PASS, entity.getPASSWORD(),entity.getUserName());
    }
    

    @Override
    public void delete(String id) {
        JDBC.update(DELETE_SQL, id);
    }

    @Override
    public List<UserLogin> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }


    public UserLogin selectById(String id) {
        List<UserLogin> list = selectBySql(Select_BYID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    public UserLogin selectById2(String id) {
        List<UserLogin> list = selectBySql(Select_BYID_SQL_2, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<UserLogin> selectBySql(String sql, Object... args) {
        List<UserLogin> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.query(sql, args);
            while (rs.next()) {                
                UserLogin entity = new UserLogin();
                entity.setMaID(rs.getInt(1));
                entity.setUserName(rs.getString(2));
                entity.setPASSWORD(rs.getString(3));
                entity.setVaitro(rs.getBoolean(4));
                list.add(entity);
                
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
}
