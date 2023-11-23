/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mobilemanagement.Dao;

import com.mobilemanagement.Model.IEMISP;
import com.mobilemanagement.Utility.JDBC;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DINHVU
 */
public class IEMISPDao extends abstractDAO<IEMISP, Integer> {

    final String INSERT_SQL = "INSERT IMEISP(IMEI,XuatXu,MaSPCT) VALUES(?,?,?);";
    final String UPDATE_SQL = "UPDATE IMEISP SET XuatXu =? ,MaSPCT = ? WHERE IMEI = ?;";
    final String DELETE_SQL = "DELETE From IMEISP WHERE IMEI = ?;";
    final String SELECT_ALL_SQL = "SELECT * FROM IMEISP;";
    final String Select_BYID_SQL = "SELECT * FROM IMEISP WHERE MaSPCT = ?";

    @Override
    public void insert(IEMISP entity) {
        JDBC.update(INSERT_SQL, entity.getIMEI(), entity.getXuatXu(), entity.getMaSPCT());
    }

    @Override
    public void update(IEMISP entity) {
        JDBC.update(UPDATE_SQL, entity.getXuatXu(), entity.getMaSPCT(), entity.getIMEI());
    }

    @Override
    public void delete(Integer id) {
        JDBC.update(DELETE_SQL, id);
    }

    @Override
    public List<IEMISP> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }
    
    
   @Override
    public IEMISP selectById(Integer id) {
         List<IEMISP> list = selectBySql(Select_BYID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    

    @Override
    public List<IEMISP> selectBySql(String sql, Object... args) {
        List<IEMISP> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.query(sql, args);
            while (rs.next()) {
                IEMISP entity = new IEMISP();
                entity.setIMEI(rs.getString(1));
                entity.setXuatXu(rs.getString(2));
                entity.setMaSPCT(rs.getInt(3));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<String> selectDistinctXuatXu() {
        return selectDistinctValues("XuatXu");
    }

    private List<String> selectDistinctValues(String columnName) {
        String query = "SELECT DISTINCT " + columnName + " FROM IMEISP;";
        return executeSelectDistinctQuery(query);
    }

    private List<String> executeSelectDistinctQuery(String query) {
        List<String> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.query(query);
            while (rs.next()) {
                list.add(rs.getString(1));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    
   

    

}
