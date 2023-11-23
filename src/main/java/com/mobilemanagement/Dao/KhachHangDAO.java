/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mobilemanagement.Dao;

import com.mobilemanagement.Model.KhachHang;
import com.mobilemanagement.Utility.JDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author THƯƠNG TÍN
 */
public class KhachHangDAO extends abstractDAO<KhachHang, String> {

    final String INSERT_SQL = "INSERT INTO KhachHang(HoTen,DiaChi,SDT,Email,GioiTinh) VALUES (?, ?, ?, ?, ?);";
    final String UPDATE_SQL = "UPDATE KhachHang SET  HoTen = ?, DiaChi = ?, SDT = ?, Email = ?, GioiTinh = ? WHERE MaKH= ?;";
    final String DELETE_SQL = "DELETE From KhachHang WHERE MaKH = ?;";
    final String SELECT_ALL_SQL = "SELECT * FROM KHACHHANG;";
    final String Select_BYID_SQL = "SELECT * FROM KHACHHANG WHERE MaKH = ?";

    @Override
    public void insert(KhachHang entity) {
        JDBC.update(INSERT_SQL, entity.getHoTen(), entity.getDiaChi(), entity.getSDT(), entity.getEmail(), entity.isGioiTinh());
    }

    @Override
    public void update(KhachHang entity) {
        JDBC.update(UPDATE_SQL, entity.getHoTen(), entity.getDiaChi(), entity.getSDT(), entity.getEmail(), entity.isGioiTinh(), entity.getMaKH());

    }

    @Override
    public void delete(String id) {
        JDBC.update(DELETE_SQL, id);
    }

    @Override
    public List<KhachHang> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public KhachHang selectById(String id) {
        List<KhachHang> list = selectBySql(Select_BYID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<KhachHang> selectBySql(String sql, Object... args) {
        List<KhachHang> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.query(sql, args);
            while (rs.next()) {
                KhachHang entity = new KhachHang();
                entity.setMaKH(rs.getInt(1));
                entity.setHoTen(rs.getString(2));
                entity.setDiaChi(rs.getString(3));
                entity.setSDT(rs.getString(4));
                entity.setEmail(rs.getString(5));
                entity.setGioiTinh(rs.getBoolean(6));
                list.add(entity);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public boolean isDuplicateHoTen(String hoTen) {
        String query = "SELECT COUNT(*) FROM KhachHang WHERE HoTen = ?";
        try (ResultSet rs = JDBC.query(query, hoTen)) {
            if (rs.next()) {
                int rowCount = rs.getInt(1);
                return rowCount > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

}
