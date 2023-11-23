
package com.mobilemanagement.Dao;

import com.mobilemanagement.Model.GiamGia;
import com.mobilemanagement.Utility.JDBC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class GiamGiaDAO  extends abstractDAO<GiamGia, String> {
    final String INSERT_SQL = "INSERT INTO GiamGia(MaGG,Giatri,NgayBatDau,NgayKetThuc,GhiChu) VALUES (?, ?, ?, ?, ?);";
    final String UPDATE_SQL = "UPDATE GiamGia SET  GiaTri = ?, NgayBatDau = ?, NgayKetThuc = ?, GhiChu = ? WHERE MaGG = ?;";
    final String DELETE_SQL = "DELETE From GiamGia WHERE MaGG = ?;";
    final String SELECT_ALL_SQL = "SELECT * FROM GiamGia;";
    final String Select_BYID_SQL = "SELECT * FROM GiamGia WHERE MaGG = ?";

    @Override
    public void insert(GiamGia entity) {
        JDBC.update(INSERT_SQL, entity.getMaGG(), entity.getGiatri(), entity.getNgayBatDau(), entity.getNgayKetThuc(), entity.getGhiChu());
    }

    @Override
    public void update(GiamGia entity) {
        JDBC.update(UPDATE_SQL, entity.getGiatri(), entity.getNgayBatDau(), entity.getNgayKetThuc(), entity.getGhiChu(),entity.getMaGG());
    }

    @Override
    public void delete(String id) {
        JDBC.update(DELETE_SQL, id);
    }

    @Override
    public List<GiamGia> selectAll() {
        return selectBySql(SELECT_ALL_SQL); 
    }

    @Override
    public GiamGia selectById(String id) {
        List<GiamGia> list = selectBySql(Select_BYID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<GiamGia> selectBySql(String sql, Object... args) {
         List<GiamGia> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.query(sql, args);
            while (rs.next()) {
                GiamGia entity = new GiamGia();
                entity.setMaGG(rs.getString(1));
                entity.setGiatri(rs.getDouble(2));
                entity.setNgayBatDau(rs.getDate(3));
                entity.setNgayKetThuc(rs.getDate(4));
                entity.setGhiChu(rs.getString(5));
                list.add(entity);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
    public boolean isDuplicateMaGG(String maGG) {
    String query = "SELECT COUNT(*) FROM GiamGia WHERE MaGG = ?";
    try (ResultSet rs = JDBC.query(query, maGG)) {
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
