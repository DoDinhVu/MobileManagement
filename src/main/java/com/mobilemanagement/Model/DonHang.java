/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mobilemanagement.Model;

import java.util.Date;

/**
 *
 * @author DINHVU
 */
public class DonHang {
    private String MaDH;
    private Date NgayLap;
    private String TrangThai;
    private String GhiChu;
    private Integer MaKH;
    private Integer MaTK;
    private String MaGG;

    public DonHang() {
    }

    public DonHang(String MaDH, Date NgayLap, String TrangThai, String GhiChu, Integer MaKH, Integer MaTK, String MaGG) {
        this.MaDH = MaDH;
        this.NgayLap = NgayLap;
        this.TrangThai = TrangThai;
        this.GhiChu = GhiChu;
        this.MaKH = MaKH;
        this.MaTK = MaTK;
        this.MaGG = MaGG;
    }

    public String getMaDH() {
        return MaDH;
    }

    public void setMaDH(String MaDH) {
        this.MaDH = MaDH;
    }

    public Date getNgayLap() {
        return NgayLap;
    }

    public void setNgayLap(Date NgayLap) {
        this.NgayLap = NgayLap;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public Integer getMaKH() {
        return MaKH;
    }

    public void setMaKH(Integer MaKH) {
        this.MaKH = MaKH;
    }

    public Integer getMaTK() {
        return MaTK;
    }

    public void setMaTK(Integer MaTK) {
        this.MaTK = MaTK;
    }

    public String getMaGG() {
        return MaGG;
    }

    public void setMaGG(String MaGG) {
        this.MaGG = MaGG;
    }

    
    
    
       
}
