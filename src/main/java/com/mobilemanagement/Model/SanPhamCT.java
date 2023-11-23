/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mobilemanagement.Model;

/**
 *
 * @author DINHVU
 */
public class SanPhamCT {

    private String  Rom, Ram, Mausac, HinhAnh, MaSP;
    private double GiaBan;
    private int SoLuong,MaSPCT;
    private IEMISP iemiSP;

    public SanPhamCT() {
    }

    public SanPhamCT(String Rom, String Ram, String Mausac, String HinhAnh, String MaSP, double GiaBan, Integer SoLuong, Integer MaSPCT, IEMISP iemiSP) {
        this.Rom = Rom;
        this.Ram = Ram;
        this.Mausac = Mausac;
        this.HinhAnh = HinhAnh;
        this.MaSP = MaSP;
        this.GiaBan = GiaBan;
        this.SoLuong = SoLuong;
        this.MaSPCT = MaSPCT;
        this.iemiSP = iemiSP;
    }

    public String getRom() {
        return Rom;
    }

    public void setRom(String Rom) {
        this.Rom = Rom;
    }

    public String getRam() {
        return Ram;
    }

    public void setRam(String Ram) {
        this.Ram = Ram;
    }

    public String getMausac() {
        return Mausac;
    }

    public void setMausac(String Mausac) {
        this.Mausac = Mausac;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String HinhAnh) {
        this.HinhAnh = HinhAnh;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public double getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(double GiaBan) {
        this.GiaBan = GiaBan;
    }

    public Integer getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(Integer SoLuong) {
        this.SoLuong = SoLuong;
    }

    public Integer getMaSPCT() {
        return MaSPCT;
    }

    public void setMaSPCT(Integer MaSPCT) {
        this.MaSPCT = MaSPCT;
    }

    public IEMISP getIemiSP() {
        return iemiSP;
    }

    public void setIemiSP(IEMISP iemiSP) {
        this.iemiSP = iemiSP;
    }

    

}
