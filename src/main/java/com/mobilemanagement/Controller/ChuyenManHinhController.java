/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mobilemanagement.Controller;

import com.mobilemanagement.Bean.DanhMuc;
import com.mobilemanagement.View.TrangChuJPanel;
import com.mobilemanagement.View.BanHangJPanel;
import com.mobilemanagement.View.DonHangJPanel;
import com.mobilemanagement.View.GiamGiaJPanel;
import com.mobilemanagement.View.khachHangJPanel;
import com.mobilemanagement.View.SanPhamJPanel;
import com.mobilemanagement.View.TaikhoanJPanel;
import com.mobilemanagement.View.ThongKeJPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author DINHVU
 */
public class ChuyenManHinhController {

    private JPanel Root;
    private String kindSelected = "";

    private List<DanhMuc> listItem = null;

    public ChuyenManHinhController(JPanel jpnRoot) {
        this.Root = jpnRoot;
    }

    public void setView(JPanel jpnItem, JLabel JlbItem) {
        kindSelected = "TrangChu";
        jpnItem.setBackground(new Color(96, 100, 191));
        jpnItem.setBackground(new Color(96, 100, 191));

        Root.removeAll();
        Root.setLayout(new BorderLayout());
        Root.add(new TrangChuJPanel());
        Root.validate();
        Root.repaint();

    }

    public void setEvent(List<DanhMuc> listItem) {
        this.listItem = listItem;
        for (DanhMuc danhMuc : listItem) {
            danhMuc.getJpn().addMouseListener(new LabelEvent(danhMuc.getKind(), danhMuc.getJpn(), danhMuc.getJlb()));
        }
    }

    class LabelEvent implements MouseListener {

        private JPanel node;
        private String kind;

        private JPanel jpnItem;
        private JLabel jlbItem;

        public LabelEvent(String kind, JPanel jpnItem, JLabel jlbItem) {
            this.kind = kind;
            this.jpnItem = jpnItem;
            this.jlbItem = jlbItem;
        }

        @Override

        public void mouseClicked(MouseEvent e) {
            switch (kind) {
                case "TrangChu":
                    node = new TrangChuJPanel();
                    break;
                case "SanPham":
                    node = new SanPhamJPanel();
                    break;
                case "BanHang":
                    node = new BanHangJPanel();
                    break;
                case "DonHang":
                    node = new DonHangJPanel();
                    break;
                case "GiamGia":
                    node = new GiamGiaJPanel();
                    break;
                case "TaiKhoan":
                    node = new TaikhoanJPanel();
                    break;
                case "KhachHang":
                    node = new khachHangJPanel();
                    break;
                case "ThongKe":
                    node = new ThongKeJPanel();
                    break;
                default:
                    break;
            }
            Root.removeAll();
            Root.setLayout(new BorderLayout());
            Root.add(node);
            Root.validate();
            Root.repaint();
            setChangeBackroud(kind);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            kindSelected = kind;
            jpnItem.setBackground(new Color(96, 100, 191));
            jlbItem.setBackground(new Color(96, 100, 191));
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            jpnItem.setBackground(new Color(96, 100, 191));
            jlbItem.setBackground(new Color(96, 100, 191));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (!kindSelected.equalsIgnoreCase(kind)) {
                jpnItem.setBackground(new Color(66, 68, 172));
                jlbItem.setBackground(new Color(66, 68, 172));
            }
        }

    }

    private void setChangeBackroud(String kind) {
        for (DanhMuc danhMuc : listItem) {
            if (danhMuc.getKind().equalsIgnoreCase(kind)) {
                danhMuc.getJpn().setBackground(new Color(96, 100, 191));
                danhMuc.getJlb().setBackground(new Color(96, 100, 191));
            } else {
                danhMuc.getJpn().setBackground(new Color(66, 68, 172));
                danhMuc.getJlb().setBackground(new Color(76, 175, 80));
            }
        }
    }
}
