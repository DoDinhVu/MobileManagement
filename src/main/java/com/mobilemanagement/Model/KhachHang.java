/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mobilemanagement.Model;

/**
 *
 * @author DINHVU
 */
public class KhachHang {
    private int MaKH;
    private String HoTen;
    private String DiaChi;
    private String SDT;
    private String Email;
    private boolean GioiTinh;

    public KhachHang() {
    }

    public KhachHang(int MaKH, String HoTen, String DiaChi, String SDT, String Email, boolean GioiTinh) {
        this.MaKH = MaKH;
        this.HoTen = HoTen;
        this.DiaChi = DiaChi;
        this.SDT = SDT;
        this.Email = Email;
        this.GioiTinh = GioiTinh;
    }

    public int getMaKH() {
        return MaKH;
    }

    public void setMaKH(int MaKH) {
        this.MaKH = MaKH;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    
    public boolean isGioiTinh() {
        return GioiTinh;
    }
    
    public void setGioiTinh (boolean GioiTinh) {
        this.GioiTinh = GioiTinh;
    }
     
}
