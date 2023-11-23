/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mobilemanagement.Model;

/**
 *
 * @author DINHVU
 */
public class DanhMucSP {
    private String MaDM;
    private  String TenDM;

    public DanhMucSP() {
    }

    public DanhMucSP(String MaDM, String TenDM) {
        this.MaDM = MaDM;
        this.TenDM = TenDM;
    }

    public String getMaDM() {
        return MaDM;
    }

    public void setMaDM(String MaDM) {
        this.MaDM = MaDM;
    }

    public String getTenDM() {
        return TenDM;
    }

    public void setTenDM(String TenDM) {
        this.TenDM = TenDM;
    }

    @Override
    public String toString() {
        return "DanhMucSP{" + "MaDM=" + MaDM + ", TenDM=" + TenDM + '}';
    }
    
    
}
