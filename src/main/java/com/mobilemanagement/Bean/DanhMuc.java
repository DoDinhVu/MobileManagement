/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mobilemanagement.Bean;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author DINHVU
 */
public class DanhMuc {
    private String Kind;
    private JPanel jpn;
    private JLabel Jlb;

    public DanhMuc() {
    }

    public DanhMuc(String Kind, JPanel jpn, JLabel Jlb) {
        this.Kind = Kind;
        this.jpn = jpn;
        this.Jlb = Jlb;
    }

    public String getKind() {
        return Kind;
    }

    public void setKind(String Kind) {
        this.Kind = Kind;
    }

    public JPanel getJpn() {
        return jpn;
    }

    public void setJpn(JPanel jpn) {
        this.jpn = jpn;
    }

    public JLabel getJlb() {
        return Jlb;
    }

    public void setJlb(JLabel Jlb) {
        this.Jlb = Jlb;
    }
    
    
}
