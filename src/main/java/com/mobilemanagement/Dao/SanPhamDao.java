/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mobilemanagement.Dao;

import com.mobilemanagement.Model.SanPham;
import com.mobilemanagement.Utility.JDBC;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DINHVU
 */
public class SanPhamDao extends abstractDAO<SanPham, String> {

    final String INSERT_SQL = "INSERT INTO SanPham(MaSP, TenSP, HDH, CPU, Camera, Pin, HangSX, ManHinh, TinhTrang, MaDM) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    final String UPDATE_SQL = "UpDate SanPham SET  TenSP = ?, HDH = ?, CPU = ?, Camera = ?, Pin = ?, HangSX = ?, ManHinh = ?, TinhTrang = ?, MaDM = ? WHERE MaSP = ?;";
    final String DELETE_SQL = "DELETE From SanPham WHERE MaSP = ?;";
    final String SELECT_ALL_SQL = "SELECT * FROM SanPham;";
    final String Select_BYID_SQL = "SELECT * FROM SanPham WHERE MaSP = ?";

    @Override
    public void insert(SanPham entity) {
        JDBC.update(INSERT_SQL, entity.getMaSP(), entity.getTenSP(), entity.getHDH(), entity.getCPU(), entity.getCamera(), entity.getPin(),
                entity.getHangSX(), entity.getManHinh(), entity.getTinhTrang(), entity.getMaDM());
    }

    @Override
    public void update(SanPham entity) {
        JDBC.update(UPDATE_SQL, entity.getTenSP(), entity.getHDH(), entity.getCPU(), entity.getCamera(), entity.getPin(), entity.getHangSX(),
                entity.getManHinh(), entity.getTinhTrang(), entity.getMaDM(), entity.getMaSP());
    }

    @Override
    public void delete(String id) {
        JDBC.update(DELETE_SQL, id);
    }

    @Override
    public List<SanPham> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public SanPham selectById(String id) {
        List<SanPham> list = selectBySql(Select_BYID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<SanPham> selectBySql(String sql, Object... args) {
        List<SanPham> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.query(sql, args);
            while (rs.next()) {
                SanPham entity = new SanPham();
                entity.setMaSP(rs.getString(1));
                entity.setTenSP(rs.getString(2));
                entity.setHDH(rs.getString(3));
                entity.setCPU(rs.getString(4));
                entity.setCamera(rs.getString(5));
                entity.setPin(rs.getString(6));
                entity.setHangSX(rs.getString(7));
                entity.setManHinh(rs.getString(8));
                entity.setTinhTrang(rs.getString(9));
                entity.setMaDM(rs.getString(10));
                list.add(entity);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<String> selectDistinctTenSP() {
        return selectDistinctValues("TenSP");
    }

    public List<String> selectDistinctPin() {
        return selectDistinctValues("Pin");
    }

    public List<String> selectDistinctHDH() {
        return selectDistinctValues("HDH");
    }

    public List<String> selectDistinctCPU() {
        return selectDistinctValues("CPU");
    }

    public List<String> selectDistinctCamera() {
        return selectDistinctValues("Camera");
    }

    public List<String> selectDistinctHangSX() {
        return selectDistinctValues("HangSX");
    }

    public List<String> selectDistinctManHinh() {
        return selectDistinctValues("ManHinh");
    }

    public List<String> selectDistinctTinhTrang() {
        return selectDistinctValues("TinhTrang");
    }

    private List<String> selectDistinctValues(String columnName) {
        String query = "SELECT DISTINCT " + columnName + " FROM SanPham;";
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

    public List<Object[]> SelectTableSP() {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{CALL SelectTableSP}";
                rs = JDBC.query(sql);
                while (rs.next()) {
                    Object[] model = {
                        rs.getString("MaSP"),
                        rs.getString("TenSP"),
                        rs.getString("TinhTrang"),
                        rs.getString("Ram"),
                        rs.getString("Rom"),
                        rs.getString("Pin"),
                        rs.getString("Mausac"),
                        rs.getInt("SoLuong"),
                        rs.getDouble("GiaBan"),};
                    list.add(model);
                }
            } catch (Exception e) {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Object[]> SelectTableDSSP() {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{CALL SelectTableDSSP}";
                rs = JDBC.query(sql);
                while (rs.next()) {
                    Object[] model = {
                        rs.getString("TenSP"),
                        rs.getString("IMEI"),
                        rs.getString("XuatXu"),
                        rs.getString("HDH"),
                        rs.getString("Rom"),
                        rs.getString("Ram"),
                        rs.getString("Mausac"),
                        rs.getString("Pin"),
                        rs.getString("TinhTrang"),
                        rs.getString("TenDM"),
                        rs.getDouble("GiaBan"),};
                    list.add(model);
                }
            } catch (Exception e) {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
