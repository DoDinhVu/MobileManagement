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
public class TaiKhoanCT {
    private int MaTK;
    private String HoTen;
    private boolean GioiTinh;
    private Date  NamSinh;
    private String DiaChi;
    private String Email;
    private String SDT;
    private String HinhAnh;
    private String ChucVu;
    private int  MaID;

    public TaiKhoanCT() {
    }

    public TaiKhoanCT(int MaTK, String HoTen, boolean GioiTinh, Date NamSinh, String DiaChi, String Email, String SDT, String HinhAnh, String ChucVu, int MaID) {
        this.MaTK = MaTK;
        this.HoTen = HoTen;
        this.GioiTinh = GioiTinh;
        this.NamSinh = NamSinh;
        this.DiaChi = DiaChi;
        this.Email = Email;
        this.SDT = SDT;
        this.HinhAnh = HinhAnh;
        this.ChucVu = ChucVu;
        this.MaID = MaID;
    }

    public int getMaTK() {
        return MaTK;
    }

    public void setMaTK(int MaTK) {
        this.MaTK = MaTK;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public boolean isGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(boolean GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public Date getNamSinh() {
        return NamSinh;
    }

    public void setNamSinh(Date NamSinh) {
        this.NamSinh = NamSinh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String HinhAnh) {
        this.HinhAnh = HinhAnh;
    }

    public String getChucVu() {
        return ChucVu;
    }

    public void setChucVu(String ChucVu) {
        this.ChucVu = ChucVu;
    }

    public int getMaID() {
        return MaID;
    }

    public void setMaID(int MaID) {
        this.MaID = MaID;
    }

    
    
    
}
