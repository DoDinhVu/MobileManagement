/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mobilemanagement.Model;

/**
 *
 * @author DINHVU
 */
public class SanPham {

    private String MaSP, TenSP, HDH, CPU, Camera, Pin, HangSX, ManHinh, TinhTrang, MaDM;
    private SanPhamCT sanphamct;

    public SanPham() {
    }

    public SanPham(String MaSP, String TenSP, String HDH, String CPU, String Camera, String Pin, String HangSX, String ManHinh, String TinhTrang, String MaDM, SanPhamCT sanphamct) {
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.HDH = HDH;
        this.CPU = CPU;
        this.Camera = Camera;
        this.Pin = Pin;
        this.HangSX = HangSX;
        this.ManHinh = ManHinh;
        this.TinhTrang = TinhTrang;
        this.MaDM = MaDM;
        this.sanphamct = sanphamct;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public String getHDH() {
        return HDH;
    }

    public void setHDH(String HDH) {
        this.HDH = HDH;
    }

    public String getCPU() {
        return CPU;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public String getCamera() {
        return Camera;
    }

    public void setCamera(String Camera) {
        this.Camera = Camera;
    }

    public String getPin() {
        return Pin;
    }

    public void setPin(String Pin) {
        this.Pin = Pin;
    }

    public String getHangSX() {
        return HangSX;
    }

    public void setHangSX(String HangSX) {
        this.HangSX = HangSX;
    }

    public String getManHinh() {
        return ManHinh;
    }

    public void setManHinh(String ManHinh) {
        this.ManHinh = ManHinh;
    }

    public String getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(String TinhTrang) {
        this.TinhTrang = TinhTrang;
    }

    public String getMaDM() {
        return MaDM;
    }

    public void setMaDM(String MaDM) {
        this.MaDM = MaDM;
    }

    public SanPhamCT getSanphamct() {
        return sanphamct;
    }

    public void setSanphamct(SanPhamCT sanphamct) {
        this.sanphamct = sanphamct;
    }

    @Override
    public String toString() {
        return "SanPham{" + "MaSP=" + MaSP + ", TenSP=" + TenSP + ", HDH=" + HDH + ", CPU=" + CPU + ", Camera=" + Camera + ", Pin=" + Pin + ", HangSX=" + HangSX + ", ManHinh=" + ManHinh + ", TinhTrang=" + TinhTrang + ", MaDM=" + MaDM + ", sanphamct=" + sanphamct + '}';
    }

    
}
