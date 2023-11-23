/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mobilemanagement.View;

import com.mobilemanagement.Bean.DanhMuc;
import com.mobilemanagement.Controller.ChuyenManHinhController;
import com.mobilemanagement.Utility.Auth;
import com.mobilemanagement.Utility.MsgBox;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DINHVU
 */
public class Main extends javax.swing.JFrame {

    
    public Main() {
        initComponents();
        init();
    }
    
    void init(){
        
        ChuyenManHinhController controller = new  ChuyenManHinhController(jpnView);
        controller.setView(JpnTrangChu, jlbTrangchu);
        
        List<DanhMuc> listItem = new ArrayList<>();
        listItem.add(new DanhMuc("TrangChu",JpnTrangChu,jlbTrangchu));
        listItem.add(new DanhMuc("SanPham",jpnSanPham,jlbSanPham));
        listItem.add(new DanhMuc("BanHang",jpanBanHang,jlbBanHang));
        listItem.add(new DanhMuc("DonHang",jpnDonHang,jlbDonHang));
        listItem.add(new DanhMuc("GiamGia",jpnGiamGia,jlbGiamGia));
        listItem.add(new DanhMuc("TaiKhoan",jpnTaiKhoan,jlbTaiKhoan));
        listItem.add(new DanhMuc("KhachHang",jpnKhachHang,JlbKhachHang));
        listItem.add(new DanhMuc("ThongKe",jpnThongKe,jlbTieude));
        
        controller.setEvent(listItem);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        MainPanel = new javax.swing.JLayeredPane();
        jpnMenu = new javax.swing.JPanel();
        JpnTrangChu = new javax.swing.JPanel();
        jlbTrangchu = new javax.swing.JLabel();
        jpnSanPham = new javax.swing.JPanel();
        jlbSanPham = new javax.swing.JLabel();
        jpanBanHang = new javax.swing.JPanel();
        jlbBanHang = new javax.swing.JLabel();
        jpnDonHang = new javax.swing.JPanel();
        jlbDonHang = new javax.swing.JLabel();
        jpnGiamGia = new javax.swing.JPanel();
        jlbGiamGia = new javax.swing.JLabel();
        jpnTaiKhoan = new javax.swing.JPanel();
        jlbTaiKhoan = new javax.swing.JLabel();
        jpnKhachHang = new javax.swing.JPanel();
        JlbKhachHang = new javax.swing.JLabel();
        jpnThongKe = new javax.swing.JPanel();
        jlbThongKe = new javax.swing.JLabel();
        jlbTieude = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jpnView = new javax.swing.JPanel();
        jpnHeader = new javax.swing.JPanel();
        lblTen = new javax.swing.JLabel();
        lblClock = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Phần mềm quản lý cửa hàng TVT");

        MainPanel.setBackground(new java.awt.Color(255, 255, 255));
        MainPanel.setOpaque(true);

        jpnMenu.setBackground(new java.awt.Color(66, 68, 172));

        JpnTrangChu.setBackground(new java.awt.Color(66, 68, 172));

        jlbTrangchu.setBackground(new java.awt.Color(255, 255, 255));
        jlbTrangchu.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jlbTrangchu.setForeground(new java.awt.Color(255, 255, 255));
        jlbTrangchu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbTrangchu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mobilemanagement/Icon/Home.png"))); // NOI18N
        jlbTrangchu.setText("Trang Chủ");
        jlbTrangchu.setIconTextGap(15);

        javax.swing.GroupLayout JpnTrangChuLayout = new javax.swing.GroupLayout(JpnTrangChu);
        JpnTrangChu.setLayout(JpnTrangChuLayout);
        JpnTrangChuLayout.setHorizontalGroup(
            JpnTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbTrangchu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        JpnTrangChuLayout.setVerticalGroup(
            JpnTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbTrangchu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        jpnSanPham.setBackground(new java.awt.Color(66, 68, 172));

        jlbSanPham.setBackground(new java.awt.Color(66, 68, 172));
        jlbSanPham.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jlbSanPham.setForeground(new java.awt.Color(255, 255, 255));
        jlbSanPham.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mobilemanagement/Icon/icons8-box-30.png"))); // NOI18N
        jlbSanPham.setText("Sản phẩm");
        jlbSanPham.setIconTextGap(15);

        javax.swing.GroupLayout jpnSanPhamLayout = new javax.swing.GroupLayout(jpnSanPham);
        jpnSanPham.setLayout(jpnSanPhamLayout);
        jpnSanPhamLayout.setHorizontalGroup(
            jpnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbSanPham, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnSanPhamLayout.setVerticalGroup(
            jpnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        jpanBanHang.setBackground(new java.awt.Color(66, 68, 172));

        jlbBanHang.setBackground(new java.awt.Color(66, 68, 172));
        jlbBanHang.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jlbBanHang.setForeground(new java.awt.Color(255, 255, 255));
        jlbBanHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbBanHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mobilemanagement/Icon/icons8-cart-30.png"))); // NOI18N
        jlbBanHang.setText("Bán hàng");
        jlbBanHang.setIconTextGap(15);

        javax.swing.GroupLayout jpanBanHangLayout = new javax.swing.GroupLayout(jpanBanHang);
        jpanBanHang.setLayout(jpanBanHangLayout);
        jpanBanHangLayout.setHorizontalGroup(
            jpanBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbBanHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpanBanHangLayout.setVerticalGroup(
            jpanBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbBanHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        jpnDonHang.setBackground(new java.awt.Color(66, 68, 172));

        jlbDonHang.setBackground(new java.awt.Color(66, 68, 172));
        jlbDonHang.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jlbDonHang.setForeground(new java.awt.Color(255, 255, 255));
        jlbDonHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbDonHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mobilemanagement/Icon/icons8-paid-bill-30.png"))); // NOI18N
        jlbDonHang.setText("Đơn hàng");
        jlbDonHang.setIconTextGap(15);

        javax.swing.GroupLayout jpnDonHangLayout = new javax.swing.GroupLayout(jpnDonHang);
        jpnDonHang.setLayout(jpnDonHangLayout);
        jpnDonHangLayout.setHorizontalGroup(
            jpnDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbDonHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnDonHangLayout.setVerticalGroup(
            jpnDonHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbDonHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        jpnGiamGia.setBackground(new java.awt.Color(66, 68, 172));

        jlbGiamGia.setBackground(new java.awt.Color(66, 68, 172));
        jlbGiamGia.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jlbGiamGia.setForeground(new java.awt.Color(255, 255, 255));
        jlbGiamGia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbGiamGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mobilemanagement/Icon/icons8-discounts-30.png"))); // NOI18N
        jlbGiamGia.setText("Giảm giá");
        jlbGiamGia.setIconTextGap(15);

        javax.swing.GroupLayout jpnGiamGiaLayout = new javax.swing.GroupLayout(jpnGiamGia);
        jpnGiamGia.setLayout(jpnGiamGiaLayout);
        jpnGiamGiaLayout.setHorizontalGroup(
            jpnGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnGiamGiaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jlbGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jpnGiamGiaLayout.setVerticalGroup(
            jpnGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbGiamGia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        jpnTaiKhoan.setBackground(new java.awt.Color(66, 68, 172));

        jlbTaiKhoan.setBackground(new java.awt.Color(66, 68, 172));
        jlbTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jlbTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        jlbTaiKhoan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbTaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mobilemanagement/Icon/icons8-person-30.png"))); // NOI18N
        jlbTaiKhoan.setText("Tài khoản");
        jlbTaiKhoan.setIconTextGap(15);

        javax.swing.GroupLayout jpnTaiKhoanLayout = new javax.swing.GroupLayout(jpnTaiKhoan);
        jpnTaiKhoan.setLayout(jpnTaiKhoanLayout);
        jpnTaiKhoanLayout.setHorizontalGroup(
            jpnTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnTaiKhoanLayout.setVerticalGroup(
            jpnTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        jpnKhachHang.setBackground(new java.awt.Color(66, 68, 172));

        JlbKhachHang.setBackground(new java.awt.Color(66, 68, 172));
        JlbKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        JlbKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        JlbKhachHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JlbKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mobilemanagement/Icon/icons8-customer-30.png"))); // NOI18N
        JlbKhachHang.setText("Khách hàng");
        JlbKhachHang.setIconTextGap(10);

        javax.swing.GroupLayout jpnKhachHangLayout = new javax.swing.GroupLayout(jpnKhachHang);
        jpnKhachHang.setLayout(jpnKhachHangLayout);
        jpnKhachHangLayout.setHorizontalGroup(
            jpnKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JlbKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnKhachHangLayout.setVerticalGroup(
            jpnKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JlbKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        jpnThongKe.setBackground(new java.awt.Color(66, 68, 172));

        jlbThongKe.setBackground(new java.awt.Color(66, 68, 172));
        jlbThongKe.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jlbThongKe.setForeground(new java.awt.Color(255, 255, 255));
        jlbThongKe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mobilemanagement/Icon/icons8-analytics-30.png"))); // NOI18N
        jlbThongKe.setText("Thống kê");
        jlbThongKe.setIconTextGap(15);

        javax.swing.GroupLayout jpnThongKeLayout = new javax.swing.GroupLayout(jpnThongKe);
        jpnThongKe.setLayout(jpnThongKeLayout);
        jpnThongKeLayout.setHorizontalGroup(
            jpnThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbThongKe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnThongKeLayout.setVerticalGroup(
            jpnThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbThongKe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        jlbTieude.setFont(new java.awt.Font("Perpetua Titling MT", 1, 48)); // NOI18N
        jlbTieude.setForeground(new java.awt.Color(255, 255, 102));
        jlbTieude.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbTieude.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mobilemanagement/Icon/LogoMobile70.png"))); // NOI18N

        jButton1.setBackground(new java.awt.Color(66, 68, 172));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mobilemanagement/Icon/icons8-shutdown-30.png"))); // NOI18N
        jButton1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnMenuLayout = new javax.swing.GroupLayout(jpnMenu);
        jpnMenu.setLayout(jpnMenuLayout);
        jpnMenuLayout.setHorizontalGroup(
            jpnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JpnTrangChu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpanBanHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnDonHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnGiamGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jlbTieude, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpnMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnMenuLayout.setVerticalGroup(
            jpnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnMenuLayout.createSequentialGroup()
                .addComponent(jlbTieude, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JpnTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jpnSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jpanBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jpnDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jpnGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jpnTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jpnKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jpnThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jpnView.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jpnViewLayout = new javax.swing.GroupLayout(jpnView);
        jpnView.setLayout(jpnViewLayout);
        jpnViewLayout.setHorizontalGroup(
            jpnViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 997, Short.MAX_VALUE)
        );
        jpnViewLayout.setVerticalGroup(
            jpnViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 611, Short.MAX_VALUE)
        );

        jpnHeader.setBackground(new java.awt.Color(66, 68, 172));
        jpnHeader.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 1, 0));
        jpnHeader.setForeground(new java.awt.Color(255, 255, 0));

        lblTen.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTen.setForeground(new java.awt.Color(255, 255, 255));
        lblTen.setText("Xin chào:");

        lblClock.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblClock.setForeground(new java.awt.Color(255, 255, 255));
        lblClock.setText("7/11/2023 10:20AM");

        javax.swing.GroupLayout jpnHeaderLayout = new javax.swing.GroupLayout(jpnHeader);
        jpnHeader.setLayout(jpnHeaderLayout);
        jpnHeaderLayout.setHorizontalGroup(
            jpnHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnHeaderLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpnHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblClock)
                    .addComponent(lblTen))
                .addGap(257, 257, 257))
        );
        jpnHeaderLayout.setVerticalGroup(
            jpnHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnHeaderLayout.createSequentialGroup()
                .addComponent(lblTen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(lblClock)
                .addContainerGap())
        );

        MainPanel.setLayer(jpnMenu, javax.swing.JLayeredPane.DEFAULT_LAYER);
        MainPanel.setLayer(jpnView, javax.swing.JLayeredPane.POPUP_LAYER);
        MainPanel.setLayer(jpnHeader, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addComponent(jpnMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        MainPanelLayout.setVerticalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addComponent(jpnHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jpnView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpnView.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        if (MsgBox.confirm(this, "Bạn muốn đăng xuất ?")) {
            Auth.clear();
                this.hide();
                
                new LoginFrom().setVisible(true);
        }
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JlbKhachHang;
    private javax.swing.JPanel JpnTrangChu;
    private javax.swing.JLayeredPane MainPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jlbBanHang;
    private javax.swing.JLabel jlbDonHang;
    private javax.swing.JLabel jlbGiamGia;
    private javax.swing.JLabel jlbSanPham;
    private javax.swing.JLabel jlbTaiKhoan;
    private javax.swing.JLabel jlbThongKe;
    private javax.swing.JLabel jlbTieude;
    private javax.swing.JLabel jlbTrangchu;
    private javax.swing.JPanel jpanBanHang;
    private javax.swing.JPanel jpnDonHang;
    private javax.swing.JPanel jpnGiamGia;
    private javax.swing.JPanel jpnHeader;
    private javax.swing.JPanel jpnKhachHang;
    private javax.swing.JPanel jpnMenu;
    private javax.swing.JPanel jpnSanPham;
    private javax.swing.JPanel jpnTaiKhoan;
    private javax.swing.JPanel jpnThongKe;
    private javax.swing.JPanel jpnView;
    private javax.swing.JLabel lblClock;
    private javax.swing.JLabel lblTen;
    // End of variables declaration//GEN-END:variables
}
