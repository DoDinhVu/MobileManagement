/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mobilemanagement.Dao;

import com.mobilemanagement.Model.DonHang;
import com.mobilemanagement.Utility.JDBC;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class DonHangDAO extends abstractDAO<DonHang, String> {

    final String INSERT_SQL = "INSERT INTO DonHang (MaDH, NgayLap, TrangThai, GhiChu, MaKH, MaTK, MaGG) VALUES (?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE DonHang SET NgayLap = ?, TrangThai = ?, GhiChu = ?, MaKH = ?, MaTK = ?, MaGG = ? WHERE MaDH = ?;";
    final String DELETE_SQL = "DELETE FROM DonHang WHERE MaDH = ?;";
    final String SELECT_ALL_SQL = "select * from DonHang;";
    final String Select_BYID_SQL = "SELECT * FROM DonHang WHERE MaDH = ?;";

    @Override
    public void insert(DonHang entity) {
        JDBC.update(INSERT_SQL, entity.getMaDH(), entity.getNgayLap(), entity.getTrangThai(), entity.getGhiChu(), entity.getMaKH(), entity.getMaTK(), entity.getMaGG());
    }

    @Override
    public void update(DonHang entity) {
        JDBC.update(UPDATE_SQL, entity.getNgayLap(), entity.getTrangThai(), entity.getGhiChu(), entity.getMaKH(), entity.getMaTK(), entity.getMaGG(),entity.getMaDH());
    }

    @Override
    public void delete(String id) {
        JDBC.update(DELETE_SQL, id);
    }

    @Override
    public List<DonHang> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public DonHang selectById(String id) {
        List<DonHang> list = selectBySql(Select_BYID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<DonHang> selectBySql(String sql, Object... args) {
        List<DonHang> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.query(sql, args);
            while (rs.next()) {
                DonHang entity = new DonHang();
                entity.setMaDH(rs.getString("MaDH"));
                entity.setNgayLap(rs.getDate("NgayLap"));
                entity.setTrangThai(rs.getString("TrangThai"));
                entity.setGhiChu(rs.getString("GhiChu"));
                entity.setMaKH(rs.getInt("MaKH"));
                entity.setMaTK(rs.getInt("MaTK"));
                entity.setMaGG(rs.getString("MaGG"));
                list.add(entity);

            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi truy vấn dữ liệuDAO: " + e.getMessage());
        }
        return list;
    }

}
