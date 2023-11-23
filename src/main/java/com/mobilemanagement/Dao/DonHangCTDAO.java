/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mobilemanagement.Dao;

import com.mobilemanagement.Model.DonHangCT;
import com.mobilemanagement.Utility.JDBC;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class DonHangCTDAO extends abstractDAO<DonHangCT, String> {

    final String INSERT_SQL = "INSERT INTO DonHangCT (MaDH, MaSP, SoLuong, GiaDaGiam) VALUES (?, ?, ?, ?);";
    final String UPDATE_SQL = "UPDATE DonHangCT SET  MaSP = ?, SoLuong = ?, GiaDaGiam = ? WHERE MaDH = ?;";
    final String DELETE_SQL = "DELETE FROM DonHangCT WHERE MaDH = ?;";
    final String SELECT_ALL_SQL = "SELECT * FROM DonHangCT;";
    final String SELECT_BY_ID_SQL = "SELECT * FROM DonHangCT WHERE MaDH = ?;";

    @Override
    public void insert(DonHangCT entity) {
        JDBC.update(INSERT_SQL, entity.getMaDH(), entity.getMaSP(), entity.getSoLuong(), entity.getGiaDaGiam());
    }

    @Override
    public void update(DonHangCT entity) {
        JDBC.update(UPDATE_SQL, entity.getMaSP(), entity.getSoLuong(), entity.getGiaDaGiam(), entity.getMaDH());
    }

    @Override
    public void delete(String id) {
        JDBC.update(DELETE_SQL, id);
    }

    @Override
    public List<DonHangCT> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public DonHangCT selectById(String id) {
        List<DonHangCT> list = selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<DonHangCT> selectBySql(String sql, Object... args) {
        List<DonHangCT> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.query(sql, args);
            while (rs.next()) {
                DonHangCT entity = new DonHangCT();
                //;
                entity.setMaDH(rs.getString("MaDH"));
                entity.setMaSP(rs.getString("MaSP"));
                entity.setSoLuong(rs.getInt("SoLuong"));
                entity.setGiaDaGiam(rs.getDouble("GiaDaGiam"));
                entity.setTongGia(rs.getDouble("TongGia"));

                list.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi truy vấn dữ liệuDAO: " + e.getMessage());
        }
        return list;
    }
}
