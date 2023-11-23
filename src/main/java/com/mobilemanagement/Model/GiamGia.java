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
public class GiamGia {

    private String MaGG;
    private double Giatri;
    private Date NgayBatDau;
    private Date NgayKetThuc;
    private String GhiChu;

    public GiamGia() {
    }

    public GiamGia(String MaGG, double Giatri, Date NgayBatDau, Date NgayKetThuc, String GhiChu) {
        this.MaGG = MaGG;
        this.Giatri = Giatri;
        this.NgayBatDau = NgayBatDau;
        this.NgayKetThuc = NgayKetThuc;
        this.GhiChu = GhiChu;
    }

    public String getMaGG() {
        return MaGG;
    }

    public void setMaGG(String MaGG) {
        this.MaGG = MaGG;
    }

    public double getGiatri() {
        return Giatri;
    }

    public void setGiatri(double Giatri) {
        this.Giatri = Giatri;
    }

    public Date getNgayBatDau() {
        return NgayBatDau;
    }

    public void setNgayBatDau(Date NgayBatDau) {
        this.NgayBatDau = NgayBatDau;
    }

    public Date getNgayKetThuc() {
        return NgayKetThuc;
    }

    public void setNgayKetThuc(Date NgayKetThuc) {
        this.NgayKetThuc = NgayKetThuc;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    @Override
    public String toString() {
        return "GiamGia{" + "MaGG=" + MaGG + ", Giatri=" + Giatri + ", NgayBatDau=" + NgayBatDau + ", NgayKetThuc=" + NgayKetThuc + ", GhiChu=" + GhiChu + '}';
    }

}
