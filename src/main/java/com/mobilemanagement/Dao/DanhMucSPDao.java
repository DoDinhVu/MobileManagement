/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mobilemanagement.Dao;

import com.mobilemanagement.Model.DanhMucSP;
import com.mobilemanagement.Utility.JDBC;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DINHVU
 */
public class DanhMucSPDao extends abstractDAO<DanhMucSP, String> {

    final String INSERT_SQL = "INSERT INTO DanhMucSP(MaDM,TenDM) VALUES (?,?);";
    final String UPDATE_SQL = "UPDATE DanhMucSP SET TenDM = ? WHERE MaDM = ?";
    final String DELETE_SQL = "DELETE From DanhMucSP WHERE MaDM = ?;";
    final String SELECT_ALL_SQL = "SELECT * FROM DanhMucSP;";
    final String Select_BYID_SQL = "SELECT * FROM DanhMucSP WHERE MaDM = ?";
    final String Select_BYMaDM_SQL = "SELECT * FROM DanhMucSP WHERE ? IN (SELECT MaDM FROM SanPham)";

    @Override
    public void insert(DanhMucSP entity) {
        JDBC.update(INSERT_SQL, entity.getMaDM(), entity.getMaDM());
    }

    @Override
    public void update(DanhMucSP entity) {
        JDBC.update(UPDATE_SQL, entity.getTenDM(), entity.getMaDM());
    }

    @Override
    public void delete(String id) {
        JDBC.update(DELETE_SQL, id);
    }

    @Override
    public List<DanhMucSP> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public DanhMucSP selectById(String id) {
        List<DanhMucSP> list = selectBySql(Select_BYID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    
     public DanhMucSP selectMaDM(String MaDM) {
        List<DanhMucSP> list = selectBySql(Select_BYMaDM_SQL, MaDM);

    if (list.isEmpty()) {
        return null;
    }

    return list.get(0);
}

    @Override
    public List<DanhMucSP> selectBySql(String sql, Object... args) {
        List<DanhMucSP> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.query(sql, args);
            while (rs.next()) {
                DanhMucSP entity = new DanhMucSP();
                entity.setMaDM(rs.getString("MaDM"));
                entity.setTenDM(rs.getString("TenDM"));
                list.add(entity);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
    public List<String> selectDistinctTenDM() {
        return selectDistinctValues("TenDM");
    }

    private List<String> selectDistinctValues(String columnName) {
        String query = "SELECT DISTINCT " + columnName + " FROM DanhMucSP;";
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
