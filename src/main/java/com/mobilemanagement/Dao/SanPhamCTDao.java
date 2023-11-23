/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mobilemanagement.Dao;

import com.mobilemanagement.Model.SanPhamCT;
import com.mobilemanagement.Utility.JDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DINHVU
 */
public class SanPhamCTDao extends abstractDAO<SanPhamCT, Integer> {

    final String INSERT_SQL = "INSERT INTO SanPhamCT( Rom, Ram, Mausac, HinhAnh, GiaBan, MaSP) VALUES ( ?, ?, ?, ?, ?, ?);";
    final String UPDATE_SQL = "UPDATE SanPhamCT SET Rom = ?, Ram = ?, Mausac = ? , HinhAnh = ? , GiaBan = ?, MaSP = ? WHERE MaSPCT = ?;";
    final String DELETE_SQL = "DELETE From SanPhamCT WHERE MaSPCT = ?;";
    final String SELECT_ALL_SQL = "SELECT * FROM SanPhamCT;";
    final String Select_BYID_SQL = "SELECT * FROM SanPhamCT WHERE MaSPCT = ?";
    final String Select_MaSPCT = "SELECT * FROM SanPhamCT WHERE Mausac = ? AND GiaBan = ? AND Ram = ? AND Rom = ?";

    @Override
    public void insert(SanPhamCT entity) {
        JDBC.update(INSERT_SQL, entity.getRom(), entity.getRam(), entity.getMausac(), entity.getHinhAnh(), entity.getGiaBan(), entity.getMaSP());
    }

    public int insertAndGetGeneratedKey(SanPhamCT entity) throws SQLException {
        // final String INSERT_SQL = "INSERT INTO SanPhamCT( Rom, Ram, Mausac, HinhAnh, GiaBan, MaSP) VALUES ( ?, ?, ?, ?, ?, ?);";
        int generatedKey = -1;
        try (PreparedStatement pstmt = JDBC.getStmt(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, entity.getRom());
            pstmt.setString(2, entity.getRam());
            pstmt.setString(3, entity.getMausac());
            pstmt.setString(4, entity.getHinhAnh());
            pstmt.setDouble(5, entity.getGiaBan());
            pstmt.setString(6, entity.getMaSP());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    generatedKey = generatedKeys.getInt(1);
                }
            }

        } catch (SQLException e) {
            // Xử lý ngoại lệ hoặc ghi log
            e.printStackTrace();
            throw e; // Đẩy ngoại lệ lên để thông báo cho lớp gọi
        }

        return generatedKey;
    }

    @Override
    public void update(SanPhamCT entity) {
        JDBC.update(UPDATE_SQL, entity.getRom(), entity.getRam(), entity.getMausac(), entity.getHinhAnh(), entity.getGiaBan(), entity.getMaSP(), entity.getMaSPCT());
    }

    @Override
    public void delete(Integer id) {
        JDBC.update(DELETE_SQL, id);
    }

    @Override
    public List<SanPhamCT> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    public SanPhamCT selectMaSPCTByConditions(String mausac, double giaban, String ram, String rom) {
        List<SanPhamCT> list = selectBySql(Select_MaSPCT, mausac, giaban, ram, rom);

        if (list.isEmpty()) {
            return null;
        }

        return list.get(0);
    }

    @Override
    public SanPhamCT selectById(Integer id) {
        List<SanPhamCT> list = selectBySql(Select_BYID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<SanPhamCT> selectBySql(String sql, Object... args) {
        List<SanPhamCT> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.query(sql, args);
            while (rs.next()) {
                SanPhamCT entity = new SanPhamCT();
                entity.setMaSPCT(rs.getInt(1));
                entity.setRom(rs.getString(2));
                entity.setRam(rs.getString(3));
                entity.setMausac(rs.getString(4));
                entity.setHinhAnh(rs.getString(5));
                entity.setGiaBan(rs.getDouble(6));
                entity.setMaSP(rs.getString(7));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<String> selectDistinctRom() {
        return selectDistinctValues("Rom");
    }

    public List<String> selectDistinctRam() {
        return selectDistinctValues("Ram");
    }

    public List<String> selectDistinctMausac() {
        return selectDistinctValues("Mausac");
    }

    private List<String> selectDistinctValues(String columnName) {
        String query = "SELECT DISTINCT " + columnName + " FROM SanPhamCT;";
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
