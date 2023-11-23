/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mobilemanagement.Dao;

import com.mobilemanagement.Model.TaiKhoanCT;
import com.mobilemanagement.Utility.JDBC;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class NhanVienDAO extends abstractDAO<TaiKhoanCT, String> {

    final String INSERT_SQL = "INSERT INTO TaiKhoanCT (MaTK, HoTen, GioiTinh, NamSinh, DiaChi, Email, SDT, HinhAnh, ChucVu, MaID) VALUES (?,?,?,?,?,?,?,?,?,?);";
    final String UPDATE_SQL = "update TaiKhoanCT set HoTen = ?, GioiTinh = ?, NamSinh = ?, DiaChi = ?, Email = ?,SDT = ?,HinhAnh = ?,ChucVu =?, MaID = ?  where MaTK = ?";
    final String DELETE_SQL = "delete from TaiKhoanCT where MaTK = ?";
    final String SELECT_ALL_SQL = "select * from TaiKhoanCT";
    final String SELECT_BY_ID_SQL = "select * from TaiKhoanCT where MaTK = ?";
    final String SELECT_BY_MA_US_SQL = "SELECT * FROM TaiKhoanCT WHERE MaID = ?";

    @Override
    public void insert(TaiKhoanCT entity) {
        JDBC.update(INSERT_SQL, entity.getMaTK(), entity.getHoTen(), entity.isGioiTinh(), entity.getNamSinh(), entity.getDiaChi(), entity.getEmail(), entity.getSDT(), entity.getHinhAnh(), entity.getChucVu(), entity.getMaID());
    }

    @Override
    public void update(TaiKhoanCT entity) {
        JDBC.update(UPDATE_SQL, entity.getHoTen(), entity.isGioiTinh(), entity.getNamSinh(), entity.getDiaChi(), entity.getEmail(), entity.getSDT(), entity.getHinhAnh(), entity.getChucVu(), entity.getMaID(), entity.getMaTK());
    }

    @Override
    public void delete(String id) {
        JDBC.update(DELETE_SQL, id);
    }

    @Override
    public List<TaiKhoanCT> selectAll() {
        return selectBySql(SELECT_ALL_SQL);

    }

    @Override
    public TaiKhoanCT selectById(String id) {
        List<TaiKhoanCT> list = selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public List<TaiKhoanCT> selectKhoaHocByUserLogin(String maID) {
        return selectBySql(SELECT_BY_MA_US_SQL, maID);
    }

    @Override
    public List<TaiKhoanCT> selectBySql(String sql, Object... args) {
        List<TaiKhoanCT> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.query(sql, args);
            while (rs.next()) {
                TaiKhoanCT entity = new TaiKhoanCT();
                entity.setMaTK(rs.getInt("MaTK"));
                entity.setMaID(rs.getInt("MaID"));
                entity.setHoTen(rs.getString("HoTen"));
                entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                entity.setChucVu(rs.getString("ChucVu"));
                entity.setNamSinh(rs.getDate("NamSinh"));
                entity.setDiaChi(rs.getString("DiaChi"));
                entity.setEmail(rs.getString("Email"));
                entity.setSDT(rs.getString("SDT"));
                entity.setHinhAnh(rs.getString("HinhAnh"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
