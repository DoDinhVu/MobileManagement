/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mobilemanagement.Model;

/**
 *
 * @author DINHVU
 */
public class DonHangCT {
    
    private String MaDH;
    private String MaSP;
    private int SoLuong;
    private double GiaDaGiam;
    private double TongGia;

    public DonHangCT() {
    }

    public DonHangCT( String MaDH, String MaSP, Integer SoLuong, double GiaDaGiam, double TongGia) {
        //this.MaDHCT = MaDHCT;
        this.MaDH = MaDH;
        this.MaSP = MaSP;
        this.SoLuong = SoLuong;
        this.GiaDaGiam = GiaDaGiam;
        this.TongGia = TongGia;
    }

    /*public Integer getMaDHCT() {
        return MaDHCT;
    }

    public void setMaDHCT(Integer MaDHCT) {
        this.MaDHCT = MaDHCT;
    }*/

    public String getMaDH() {
        return MaDH;
    }

    public void setMaDH(String MaDH) {
        this.MaDH = MaDH;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public Integer getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(Integer SoLuong) {
        this.SoLuong = SoLuong;
    }

    public double getGiaDaGiam() {
        return GiaDaGiam;
    }

    public void setGiaDaGiam(double GiaDaGiam) {
        this.GiaDaGiam = GiaDaGiam;
    }

    public double getTongGia() {
        return TongGia;
    }

    public void setTongGia(double TongGia) {
        this.TongGia = TongGia;
    }
    
    
}
