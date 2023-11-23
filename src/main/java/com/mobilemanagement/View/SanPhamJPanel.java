/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mobilemanagement.View;

import com.mobilemanagement.Dao.DanhMucSPDao;
import com.mobilemanagement.Dao.IEMISPDao;
import com.mobilemanagement.Dao.SanPhamCTDao;
import com.mobilemanagement.Dao.SanPhamDao;
import com.mobilemanagement.Model.DanhMucSP;
import com.mobilemanagement.Model.IEMISP;
import com.mobilemanagement.Model.SanPham;
import com.mobilemanagement.Model.SanPhamCT;
import com.mobilemanagement.Utility.MsgBox;
import com.mobilemanagement.Utility.XFile;
import com.mobilemanagement.Utility.XImage;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DINHVU
 */
public class SanPhamJPanel extends javax.swing.JPanel {

    JFileChooser fileChooser = new JFileChooser();
    SanPhamDao spDao = new SanPhamDao();
    SanPhamCTDao spctDao = new SanPhamCTDao();
    IEMISPDao iemiDao = new IEMISPDao();
    DanhMucSPDao dmDao = new DanhMucSPDao();
    int row = 0;
    List<Object[]> data = new ArrayList<>();
    List<Object[]> datatable = new ArrayList<>();
    List<DanhMucSP> danhMucList = dmDao.selectAll();

    /**
     * Creates new form SanPhamJPanel
     */
    public SanPhamJPanel() {
        initComponents();
        // fillTable();

        init();
    }

    void init() {
        fillComboBoxes();

        data = spDao.SelectTableSP();
        loadTable(data);
        datatable = spDao.SelectTableDSSP();
        LoadTable2(datatable);
        DefaultTableCellRenderer headerCellRenderer = new DefaultTableCellRenderer();
        headerCellRenderer.setBackground(new Color(192, 227, 149));
        for (int i = 0; i < tblSanPham.getModel().getColumnCount(); ++i) {
            tblSanPham.getColumnModel().getColumn(i).setHeaderRenderer(headerCellRenderer);
        }
        for (int i = 0; i < tblDSSP.getModel().getColumnCount(); ++i) {
            tblDSSP.getColumnModel().getColumn(i).setHeaderRenderer(headerCellRenderer);
        }
        //  updateStatus();

    }

    void loadTable(List<Object[]> data) {

        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);

        try {
            List<Object[]> list = data;
            for (Object[] row : list) {

                Object[] rows = {
                    row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7], row[8]
                };
                model.addRow(rows);
            };

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void fillComboBoxes() {
        fillComboBox(cboHDH, spDao.selectDistinctHDH());
        fillComboBox(cboCPU, spDao.selectDistinctCPU());
        fillComboBox(cboCamera, spDao.selectDistinctCamera());
        fillComboBox(cboPin, spDao.selectDistinctPin());
        fillComboBox(cboHangSX, spDao.selectDistinctHangSX());
        fillComboBox(cboManHinh, spDao.selectDistinctManHinh());
        fillComboBox(cboTinhTrang, spDao.selectDistinctTinhTrang());
        fillComboBox(cboRom, spctDao.selectDistinctRom());
        fillComboBox(cboRam, spctDao.selectDistinctRam());
        fillComboBox(cboMauSac, spctDao.selectDistinctMausac());
        fillComboBox(cboXuatXu, iemiDao.selectDistinctXuatXu());
        fillComboBox(cboDanhMuc, dmDao.selectDistinctTenDM());
        fillComboBox(cboTenDM, dmDao.selectDistinctTenDM());

        // Repeat this for other combo boxes
    }

    void fillComboBox(JComboBox<String> comboBox, List<String> data) {
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) comboBox.getModel();
        model.removeAllElements();

        try {
            if (!data.isEmpty()) {
                for (String value : data) {
                    model.addElement(value);
                }
            } else {
                MsgBox.alert(this, "Danh sách trống.");
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu: " + e.getMessage());
        }
    }

    void edit() {
        int selectedRow = tblSanPham.getSelectedRow();

        // Kiểm tra xem có dòng nào được chọn không
        if (selectedRow != -1) {
            // Lấy giá trị từ JTable
            String MaSP = (String) tblSanPham.getValueAt(selectedRow, 0);
//            String TenSP = tblSanPham.getValueAt(selectedRow, 1).toString();
            String Ram = tblSanPham.getValueAt(selectedRow, 3).toString();
            String Rom = tblSanPham.getValueAt(selectedRow, 4).toString();
//            String Pin = tblSanPham.getValueAt(selectedRow, 5).toString();
            String MauSac = tblSanPham.getValueAt(selectedRow, 6).toString();
            double GiaBan = (Double) tblSanPham.getValueAt(selectedRow, 8);

            SanPham sp = spDao.selectById(MaSP);
            String MaDM = sp.getMaDM();
            System.out.println(MaDM);
            this.setFrom(sp);

            SanPhamCT spct = spctDao.selectMaSPCTByConditions(MauSac, GiaBan, Ram, Rom);
            Integer MaSPCT = spct.getMaSPCT();
            System.out.println("" + MaSPCT);
            this.setFromSPCT(spct);

            DanhMucSP dmsp = dmDao.selectById(MaDM);
            System.out.println("MaDM" + dmsp);
            this.setFromDanhMuc(dmsp);

            IEMISP iemi = iemiDao.selectById(MaSPCT);

            this.setFromIEMI(iemi);
            // this.updateStatus();
        }
    }

    void setFrom(SanPham sp) {
        txtMaSP.setText(sp.getMaSP());
        txtTenSP.setText(sp.getTenSP());
        cboHDH.setSelectedItem(sp.getHDH());
        cboCPU.setSelectedItem(sp.getCPU());
        cboCamera.setSelectedItem(sp.getCamera());
        cboPin.setSelectedItem(sp.getPin());
        cboHangSX.setSelectedItem(sp.getHangSX());
        cboManHinh.setSelectedItem(sp.getManHinh());
        cboTinhTrang.setSelectedItem(sp.getTinhTrang());
        cboTenDM.setSelectedItem(sp.getMaDM());

    }

    void setFromSPCT(SanPhamCT spct) {
        cboRom.setSelectedItem(spct.getRom());
        cboRam.setSelectedItem(spct.getRam());
        cboMauSac.setSelectedItem(spct.getMausac());

        String hinhAnh = spct.getHinhAnh();
        if (hinhAnh != null) {
            lblHinhAnh.setIcon(XImage.read(hinhAnh));
            lblHinhAnh.setToolTipText(hinhAnh);
        }
        txtGiaBan.setText(String.valueOf(spct.getGiaBan()));
    }

    void setFromIEMI(IEMISP iemi) {
//        cboIEMI.setSelectedItem(iemi.getIMEI());
        cboXuatXu.setSelectedItem(iemi == null ? "" : iemi.getXuatXu());
    }

    void setFromDanhMuc(DanhMucSP dm) {
        cboTenDM.setSelectedItem(dm.getTenDM());
    }

    public void showDetails() {
        if (row > -1) {
            tblSanPham.setRowSelectionInterval(row, row);
        }
    }

    private void first() {
        this.row = 0;
        this.edit();
        this.showDetails();
    }

    private void prev() {
        if (this.row > 0) {
            this.row--;
            this.edit();
        }
        this.showDetails();
    }

    private void next() {
        if (this.row < tblSanPham.getRowCount() - 1) {
            this.row++;
            this.edit();
        }
        this.showDetails();
    }

    private void last() {
        this.row = tblSanPham.getRowCount() - 1;
        this.edit();
        this.showDetails();
    }

    void chonAnh() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            XImage.saveImage(file);
            ImageIcon icon = XImage.read(file.getName());
            lblHinhAnh.setIcon(icon);
            lblHinhAnh.setToolTipText(file.getName());
        }
    }

    void openBtnIEMI() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File selectFile = fileChooser.getSelectedFile();
//            XFile.openFile();
            List<String> imeiList = XFile.loadExcelData(selectFile);

            // Clear existing items in the combo box
            cboIEMI.removeAllItems();

            // Add the loaded IMEI numbers to the combo box
            for (String imei : imeiList) {
                cboIEMI.addItem(imei);
            }
        }
    }

    private String findMaDanhMucByTenDanhMuc(String tenDanhMuc) {
        // Lặp qua danh sách danh mục và tìm mã tương ứng
        for (DanhMucSP danhMuc : danhMucList) {
            if (danhMuc.getTenDM().equals(tenDanhMuc)) {
                return danhMuc.getMaDM();
            }
        }
        // Nếu không tìm thấy, có thể trả về một giá trị mặc định hoặc thông báo lỗi
        return "MaDMMacDinh";
    }

    SanPham getFrom() {
        SanPham sp = new SanPham();
        sp.setMaSP(txtMaSP.getText());
        sp.setTenSP(txtTenSP.getText());
        sp.setHDH((String) cboHDH.getSelectedItem());
        sp.setCPU((String) cboCPU.getSelectedItem());
        sp.setCamera((String) cboCamera.getSelectedItem());
        sp.setPin((String) cboPin.getSelectedItem());
        sp.setHangSX((String) cboHangSX.getSelectedItem());
        sp.setManHinh((String) cboManHinh.getSelectedItem());
        sp.setTinhTrang((String) cboTinhTrang.getSelectedItem());
        // Lấy tên danh mục được chọn
        String tenDanhMuc = (String) cboTenDM.getSelectedItem();

        // Tìm mã danh mục tương ứng từ danh sách danh mục (đã có sẵn)
        String maDanhMuc = findMaDanhMucByTenDanhMuc(tenDanhMuc);

        // Gắn mã danh mục vào đối tượng SanPham
        sp.setMaDM(maDanhMuc);
        return sp;
    }

    private Integer getMaSPCT() {
        int selectedRow = tblSanPham.getSelectedRow();

        if (selectedRow != -1) {
            // Lấy giá trị từ JTable
            String Ram = tblSanPham.getValueAt(selectedRow, 3).toString();
            String Rom = tblSanPham.getValueAt(selectedRow, 4).toString();
            String MauSac = tblSanPham.getValueAt(selectedRow, 6).toString();
            double GiaBan = (Double) tblSanPham.getValueAt(selectedRow, 8);

            System.out.println("Debug: Ram=" + Ram + ", Rom=" + Rom + ", MauSac=" + MauSac + ", GiaBan=" + GiaBan);

            // Sử dụng spctDao để lấy MaSPCT dựa trên các điều kiện
            SanPhamCT spct = spctDao.selectMaSPCTByConditions(MauSac, GiaBan, Ram, Rom);

            if (spct != null) {
                // In ra để kiểm tra giá trị của MaSPCT
                System.out.println("Debug: MaSPCT found=" + spct.getMaSPCT());

                // Trả về MaSPCT nếu tìm thấy
                return spct.getMaSPCT();
            }
        }

        // Trả về -1 nếu không tìm thấy
        return -1;
    }

    SanPhamCT getFromSPCT() {
        SanPhamCT spct = new SanPhamCT();

        spct.setRom((String) cboRom.getSelectedItem());
        spct.setRam((String) cboRam.getSelectedItem());
        spct.setMausac((String) cboMauSac.getSelectedItem());
        spct.setHinhAnh(lblHinhAnh.getToolTipText());
        spct.setGiaBan(Double.parseDouble(txtGiaBan.getText()));
        spct.setMaSP(txtMaSP.getText());

        int MaSPCT = getMaSPCT();
        spct.setMaSPCT(MaSPCT);
        System.out.println("MaSPCT === " + MaSPCT);

        return spct;
    }

    IEMISP getFromIEMI() {
        IEMISP iemi = new IEMISP();

        iemi.setIMEI((String) cboIEMI.getSelectedItem());
        iemi.setXuatXu((String) cboXuatXu.getSelectedItem());

        int MaSPCT = getFromSPCT().getMaSPCT();
        iemi.setMaSPCT(MaSPCT);
        System.out.println("IEMI SPCT  === " + MaSPCT);
        return iemi;
    }

    void clearFrom() {
        SanPham sp = new SanPham();
        SanPhamCT spct = new SanPhamCT();
        IEMISP iemi = new IEMISP();
        this.setFrom(sp);
        this.setFromSPCT(spct);
        this.setFromIEMI(iemi);
        this.row = -1;
//        this.updateStatus();
    }

//    void inseart() {
//        if (getFrom() != null) {
//            SanPham sp = getFrom();
//            try {
//                spDao.insert(sp);
//            } catch (Exception e) {
//                e.printStackTrace();
//
//                MsgBox.alert(this, "Thêm sản phẩm không thành công");
//            }
//        }
//        if (getFromSPCT() != null) {
//            SanPhamCT spct = getFromSPCT();
//            try {
//                spctDao.insert(spct);
//                data = spDao.SelectTableSP();
//                this.loadTable(data);
//                MsgBox.alert(this, "Thêm sản phẩm thành công");
//            } catch (Exception e) {
//                MsgBox.alert(this, "Thêm sản phẩm không thành công");
//                e.printStackTrace();
//            }
//        }
//
//        DefaultComboBoxModel<String> comboBoxModel = (DefaultComboBoxModel<String>) cboIEMI.getModel();
//
//        // Lấy số lượng phần tử trong ComboBox
//        int itemCount = comboBoxModel.getSize();
//
//        // Kiểm tra xem có phần tử nào không
//        if (itemCount == 0) {
//            // ComboBox rỗng, thực hiện hành động bạn muốn ở đây
//            return;
//        } else if (itemCount > 1) {
//            IEMISP iemi = getFromIEMI();
//            try {
//                iemiDao.insert(iemi);
//                data = spDao.SelectTableSP();
//                this.loadTable(data);
//                MsgBox.alert(this, "Thêm IEMI thành công");
//
//            } catch (Exception e) {
//                MsgBox.alert(this, "Thêm IEMI không thành công");
//                e.printStackTrace();
//
//            }
//        }
//
//    }
//    void insert() {
//        SanPham sp = getFrom();
//        if (sp != null) {
//            try {
//                spDao.insert(sp);
//            } catch (Exception e) {
//                e.printStackTrace();
//                MsgBox.alert(this, "Thêm sản phẩm không thành công");
//                return; // Thoát khỏi hàm nếu có lỗi khi chèn SanPham
//            }
//        } else {
//            MsgBox.alert(this, "Dữ liệu sản phẩm không hợp lệ");
//            return; // Thoát khỏi hàm nếu dữ liệu sản phẩm không hợp lệ
//        }
//
//        SanPhamCT spct = getFromSPCT();
//        if (spct != null) {
//            try {
//                int MaSPCT = spctDao.insertAndGetGeneratedKey(spct);
//
//                if (MaSPCT != -1) {
//                    IEMISP iemi = getFromIEMI();
//                    iemi.setMaSPCT(MaSPCT);
//                    iemiDao.insert(iemi);
//
//                    data = spDao.SelectTableSP();
//                    this.loadTable(data);
//                    MsgBox.alert(this, "Thêm sản phẩm thành công");
//                } else {
//                    MsgBox.alert(this, "Thêm sản phẩm không thành công");
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                MsgBox.alert(this, "Thêm sản phẩm không thành công");
//            }
//        } else {
//            MsgBox.alert(this, "Dữ liệu sản phẩm chi tiết không hợp lệ");
//        }
//    }
    void insert() {
        SanPham sp = getFrom();
        if (sp != null) {
            try {
                spDao.insert(sp);
            } catch (Exception e) {
                e.printStackTrace();
                MsgBox.alert(this, "Thêm sản phẩm không thành công");
                return; // Thoát khỏi hàm nếu có lỗi khi chèn SanPham
            }
        } else {
            MsgBox.alert(this, "Dữ liệu sản phẩm không hợp lệ");
            return; // Thoát khỏi hàm nếu dữ liệu sản phẩm không hợp lệ
        }

        SanPhamCT spct = getFromSPCT();
        if (spct != null) {
            try {
                int MaSPCT = spctDao.insertAndGetGeneratedKey(spct);

                if (MaSPCT != -1) {
                    IEMISP iemi = getFromIEMI();
                    iemi.setMaSPCT(MaSPCT);

                    try {
                        iemiDao.insert(iemi);
                        data = spDao.SelectTableSP();
                        this.loadTable(data);
                        MsgBox.alert(this, "Thêm sản phẩm thành công");
                    } catch (Exception e) {
                        // Xử lý ngoại lệ khi chèn IEMISP
                        e.printStackTrace();
                        MsgBox.alert(this, "Thêm IEMISP không thành công");
                        // Nếu có lỗi khi thêm IEMISP, có thể xóa SP và SPCT đã thêm trước đó để đảm bảo tính nhất quán

                    }
                } else {
                    MsgBox.alert(this, "Thêm sản phẩm không thành công");
                }
            } catch (Exception e) {
                e.printStackTrace();
                MsgBox.alert(this, "Thêm sản phẩm không thành công");
            }
        } else {
            MsgBox.alert(this, "Dữ liệu sản phẩm chi tiết không hợp lệ");
        }
    }

    void update() {

        SanPham sp = getFrom();
        try {
            spDao.update(sp);
        } catch (Exception e) {
            MsgBox.alert(this, "Cập nhập thất bại !");
            e.printStackTrace();
        }

        SanPhamCT spct = getFromSPCT();
        try {
            spctDao.update(spct);

            data = spDao.SelectTableSP();
            this.loadTable(data);
            MsgBox.alert(this, "Cập nhập thành công");
        } catch (Exception e) {
            MsgBox.alert(this, "Cập nhập thất bại");
            e.printStackTrace();
        }

    }

    void Delete() {
        int row = tblSanPham.getSelectedRow();
        String MaSP = (String) tblSanPham.getValueAt(row, 0);
        int SoLuong = (int) tblSanPham.getValueAt(row, 7);
        if (row == -1) {
            MsgBox.alert(this, "Vui lòng chọn sản phẩm cần xóa");
            return;
        }

        if (SoLuong >= 1) {
            MsgBox.alert(this, "Sản phẩm có số lượng tồn: " + SoLuong + " nên không thể xóa ");

        } else if (SoLuong == 0) {
            if (MsgBox.confirm(this, "Bạn muốn xóa sản phẩm ?")) {
                try {
                    spDao.delete(MaSP);
                    data = spDao.SelectTableSP();
                    this.loadTable(data);
                    MsgBox.alert(this, "Xóa thành công");

                } catch (Exception e) {
                    MsgBox.alert(this, "Xóa thất bại");
                    e.printStackTrace();
                }
            }
        }

    }

    //==================================TAB DSSP==================================================
    void LoadTable2(List<Object[]> datatable) {
        DefaultTableModel model = (DefaultTableModel) tblDSSP.getModel();
        model.setRowCount(0);
        try {
            for (Object[] row : datatable) {
                Object[] rows = {
                    row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7], row[8], row[9],};
                model.addRow(rows);
            }
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MenuPanel = new javax.swing.JTabbedPane();
        pnSanPham = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        lblHinhAnh = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        cboIEMI = new javax.swing.JComboBox<>();
        btnIEMI = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cboDanhMuc = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cboHDH = new javax.swing.JComboBox<>();
        btnHDH = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        cboManHinh = new javax.swing.JComboBox<>();
        btnManHinh = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        cboCPU = new javax.swing.JComboBox<>();
        btnCPU = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        cboRom = new javax.swing.JComboBox<>();
        btnRom = new javax.swing.JButton();
        cboRam = new javax.swing.JComboBox<>();
        btnRam = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        btnCamera = new javax.swing.JButton();
        cboCamera = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cboMauSac = new javax.swing.JComboBox<>();
        btnMauSac = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        btnPin = new javax.swing.JButton();
        cboPin = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        cboXuatXu = new javax.swing.JComboBox<>();
        btnXuatXu = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        btnHangSX = new javax.swing.JButton();
        cboHangSX = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        cboTenDM = new javax.swing.JComboBox<>();
        btnaddDM = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        cboTinhTrang = new javax.swing.JComboBox<>();
        btnTinhTrang = new javax.swing.JButton();
        btnThemSP = new javax.swing.JButton();
        btnEditSP = new javax.swing.JButton();
        btnDeleteSP = new javax.swing.JButton();
        btnNewSP = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnPre = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        txtGiaBan = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        txtIEMI = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        pnDSSP = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtTimKiemDS = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cboDanhMucDS = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDSSP = new javax.swing.JTable();
        btnFirst1 = new javax.swing.JButton();
        btnPre1 = new javax.swing.JButton();
        btnNext1 = new javax.swing.JButton();
        btnLast1 = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(1178, 780));
        setPreferredSize(new java.awt.Dimension(1178, 780));

        MenuPanel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        pnSanPham.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Danh Mục :");

        txtTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTimKiem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        tblSanPham.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(28, 35, 65)));
        tblSanPham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Sản Phẩm", "Tên Sản Phẩm", "Loại", "Ram", "Rom", "Pin", "MauSac", "Số lượng", "Giá Bán"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.setRowHeight(30);
        tblSanPham.setSelectionBackground(new java.awt.Color(28, 35, 65));
        tblSanPham.setShowGrid(false);
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblSanPhamMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);
        if (tblSanPham.getColumnModel().getColumnCount() > 0) {
            tblSanPham.getColumnModel().getColumn(0).setResizable(false);
            tblSanPham.getColumnModel().getColumn(0).setPreferredWidth(100);
            tblSanPham.getColumnModel().getColumn(1).setResizable(false);
            tblSanPham.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblSanPham.getColumnModel().getColumn(2).setResizable(false);
            tblSanPham.getColumnModel().getColumn(3).setResizable(false);
            tblSanPham.getColumnModel().getColumn(4).setResizable(false);
            tblSanPham.getColumnModel().getColumn(5).setResizable(false);
            tblSanPham.getColumnModel().getColumn(6).setResizable(false);
            tblSanPham.getColumnModel().getColumn(7).setResizable(false);
            tblSanPham.getColumnModel().getColumn(8).setResizable(false);
        }

        lblHinhAnh.setText("jLabel14");
        lblHinhAnh.setToolTipText("Click để chọn ảnh");
        lblHinhAnh.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(28, 35, 65)));
        lblHinhAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblHinhAnhMousePressed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setText("Tên sản phẩm:");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setText("Giá Bán :");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel28.setText("IMEI:");

        txtTenSP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTenSP.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        cboIEMI.setToolTipText("Nhập 15 chữ số");

        btnIEMI.setBackground(new java.awt.Color(28, 35, 65));
        btnIEMI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mobilemanagement/Icon/icons8-edit-64.png"))); // NOI18N
        btnIEMI.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnIEMIMousePressed(evt);
            }
        });
        btnIEMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIEMIActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Tìm kiếm :");

        cboDanhMuc.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cboDanhMuc.setMaximumRowCount(100);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Hệ điều hành");

        cboHDH.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        btnHDH.setBackground(new java.awt.Color(28, 35, 65));
        btnHDH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mobilemanagement/Icon/icons8-edit-64.png"))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Màn hình");

        cboManHinh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cboManHinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboManHinhActionPerformed(evt);
            }
        });

        btnManHinh.setBackground(new java.awt.Color(28, 35, 65));
        btnManHinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mobilemanagement/Icon/icons8-edit-64.png"))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("CPU");

        cboCPU.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        btnCPU.setBackground(new java.awt.Color(28, 35, 65));
        btnCPU.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mobilemanagement/Icon/icons8-edit-64.png"))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("ROM");

        cboRom.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        btnRom.setBackground(new java.awt.Color(28, 35, 65));
        btnRom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mobilemanagement/Icon/icons8-edit-64.png"))); // NOI18N
        btnRom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRomActionPerformed(evt);
            }
        });

        cboRam.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        btnRam.setBackground(new java.awt.Color(28, 35, 65));
        btnRam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mobilemanagement/Icon/icons8-edit-64.png"))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("RAM");

        btnCamera.setBackground(new java.awt.Color(28, 35, 65));
        btnCamera.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mobilemanagement/Icon/icons8-edit-64.png"))); // NOI18N

        cboCamera.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Camera");

        cboMauSac.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        btnMauSac.setBackground(new java.awt.Color(28, 35, 65));
        btnMauSac.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mobilemanagement/Icon/icons8-edit-64.png"))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Màu sắc");

        btnPin.setBackground(new java.awt.Color(28, 35, 65));
        btnPin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mobilemanagement/Icon/icons8-edit-64.png"))); // NOI18N
        btnPin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPinActionPerformed(evt);
            }
        });

        cboPin.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Pin");

        cboXuatXu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        btnXuatXu.setBackground(new java.awt.Color(28, 35, 65));
        btnXuatXu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mobilemanagement/Icon/icons8-edit-64.png"))); // NOI18N

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Xuất xứ");

        btnHangSX.setBackground(new java.awt.Color(28, 35, 65));
        btnHangSX.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mobilemanagement/Icon/icons8-edit-64.png"))); // NOI18N
        btnHangSX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHangSXActionPerformed(evt);
            }
        });

        cboHangSX.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cboHangSX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboHangSXActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("Hãng sản xuất");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setText("Danh mục");

        cboTenDM.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        btnaddDM.setBackground(new java.awt.Color(28, 35, 65));
        btnaddDM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mobilemanagement/Icon/icons8-edit-64.png"))); // NOI18N

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setText("Tình trạng");

        cboTinhTrang.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        btnTinhTrang.setBackground(new java.awt.Color(28, 35, 65));
        btnTinhTrang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mobilemanagement/Icon/icons8-edit-64.png"))); // NOI18N

        btnThemSP.setBackground(new java.awt.Color(28, 35, 65));
        btnThemSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mobilemanagement/Icon/icons8-plus-50.png"))); // NOI18N
        btnThemSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemSPMouseClicked(evt);
            }
        });
        btnThemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSPActionPerformed(evt);
            }
        });

        btnEditSP.setBackground(new java.awt.Color(28, 35, 65));
        btnEditSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mobilemanagement/Icon/icons8-edit-text-file-50.png"))); // NOI18N
        btnEditSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEditSPMouseClicked(evt);
            }
        });
        btnEditSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditSPActionPerformed(evt);
            }
        });

        btnDeleteSP.setBackground(new java.awt.Color(28, 35, 65));
        btnDeleteSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mobilemanagement/Icon/icons8-delete-50.png"))); // NOI18N
        btnDeleteSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteSPMouseClicked(evt);
            }
        });
        btnDeleteSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteSPActionPerformed(evt);
            }
        });

        btnNewSP.setBackground(new java.awt.Color(28, 35, 65));
        btnNewSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mobilemanagement/Icon/icons8-new-50.png"))); // NOI18N
        btnNewSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNewSPMouseClicked(evt);
            }
        });
        btnNewSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewSPActionPerformed(evt);
            }
        });

        btnFirst.setBackground(new java.awt.Color(28, 35, 65));
        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mobilemanagement/Icon/icons8-double-Down50.png"))); // NOI18N
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnPre.setBackground(new java.awt.Color(28, 35, 65));
        btnPre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mobilemanagement/Icon/icons8-back-50.png"))); // NOI18N
        btnPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreActionPerformed(evt);
            }
        });

        btnNext.setBackground(new java.awt.Color(28, 35, 65));
        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mobilemanagement/Icon/icons8-forward-50.png"))); // NOI18N
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLast.setBackground(new java.awt.Color(28, 35, 65));
        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mobilemanagement/Icon/icons8-double-up-50.png"))); // NOI18N
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        txtGiaBan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtGiaBan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setText("VNĐ");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setText("Mã sản phẩm:");

        txtMaSP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtMaSP.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        txtIEMI.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtIEMI.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout pnSanPhamLayout = new javax.swing.GroupLayout(pnSanPham);
        pnSanPham.setLayout(pnSanPhamLayout);
        pnSanPhamLayout.setHorizontalGroup(
            pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSanPhamLayout.createSequentialGroup()
                .addGroup(pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnSanPhamLayout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPre, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEditSP, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDeleteSP, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNewSP, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnSanPhamLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnSanPhamLayout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(97, 97, 97)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnSanPhamLayout.createSequentialGroup()
                                .addGroup(pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cboManHinh, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnHDH, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboHDH, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                                    .addComponent(btnManHinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnCPU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboCPU, 0, 125, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnRom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboRom, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnCamera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboCamera, 0, 125, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnRam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboRam, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnPin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboMauSac, 0, 125, Short.MAX_VALUE)
                                    .addComponent(btnMauSac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboPin, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnaddDM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboTenDM, 0, 127, Short.MAX_VALUE)
                                    .addComponent(btnTinhTrang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboTinhTrang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboXuatXu, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnHangSX, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboHangSX, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                                    .addComponent(btnXuatXu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel28)
                    .addGroup(pnSanPhamLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnSanPhamLayout.createSequentialGroup()
                        .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnSanPhamLayout.createSequentialGroup()
                        .addComponent(cboIEMI, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnIEMI, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel20)
                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIEMI, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnSanPhamLayout.setVerticalGroup(
            pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnSanPhamLayout.createSequentialGroup()
                .addGroup(pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnSanPhamLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(cboDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnSanPhamLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
                        .addGap(14, 14, 14)
                        .addGroup(pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnSanPhamLayout.createSequentialGroup()
                                .addGroup(pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cboTenDM, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(cboPin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cboCamera, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cboCPU, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cboHDH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cboHangSX, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(pnSanPhamLayout.createSequentialGroup()
                                        .addGroup(pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(34, 34, 34)))
                                .addGap(2, 2, 2)
                                .addGroup(pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnPin, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnCamera, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnaddDM, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btnHangSX, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnHDH, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCPU, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(pnSanPhamLayout.createSequentialGroup()
                                    .addGroup(pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cboManHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cboRom, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cboRam, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cboTinhTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cboXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnSanPhamLayout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(btnManHinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnRom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(pnSanPhamLayout.createSequentialGroup()
                                            .addGap(6, 6, 6)
                                            .addGroup(pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(btnXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(btnTinhTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGroup(pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnRam, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnMauSac))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnPre, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnThemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnEditSP, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnDeleteSP, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnNewSP, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(12, Short.MAX_VALUE))
                    .addGroup(pnSanPhamLayout.createSequentialGroup()
                        .addComponent(lblHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboIEMI, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnIEMI, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addComponent(txtIEMI, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        MenuPanel.addTab("Sản Phẩm", pnSanPham);

        pnDSSP.setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("Tìm kiếm :");

        txtTimKiemDS.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTimKiemDS.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Danh Mục :");

        cboDanhMucDS.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cboDanhMucDS.setMaximumRowCount(100);

        tblDSSP.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(28, 35, 65)));
        tblDSSP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblDSSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên Điện Thoại", "Mã IEMI", "Xuất Xứ", "HĐH", "Rom", "Ram", "Màu Sắc", "Pin", "Tình Trạng", "Danh Mục", "Giá Bán"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDSSP.setRowHeight(30);
        tblDSSP.setSelectionBackground(new java.awt.Color(28, 35, 65));
        jScrollPane2.setViewportView(tblDSSP);
        if (tblDSSP.getColumnModel().getColumnCount() > 0) {
            tblDSSP.getColumnModel().getColumn(0).setResizable(false);
            tblDSSP.getColumnModel().getColumn(0).setPreferredWidth(170);
            tblDSSP.getColumnModel().getColumn(1).setResizable(false);
            tblDSSP.getColumnModel().getColumn(1).setPreferredWidth(150);
            tblDSSP.getColumnModel().getColumn(2).setResizable(false);
            tblDSSP.getColumnModel().getColumn(3).setResizable(false);
            tblDSSP.getColumnModel().getColumn(4).setResizable(false);
            tblDSSP.getColumnModel().getColumn(5).setResizable(false);
            tblDSSP.getColumnModel().getColumn(6).setResizable(false);
            tblDSSP.getColumnModel().getColumn(7).setResizable(false);
            tblDSSP.getColumnModel().getColumn(8).setResizable(false);
            tblDSSP.getColumnModel().getColumn(9).setResizable(false);
            tblDSSP.getColumnModel().getColumn(10).setResizable(false);
        }

        btnFirst1.setBackground(new java.awt.Color(28, 35, 65));
        btnFirst1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mobilemanagement/Icon/icons8-double-Down50.png"))); // NOI18N
        btnFirst1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirst1ActionPerformed(evt);
            }
        });

        btnPre1.setBackground(new java.awt.Color(28, 35, 65));
        btnPre1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mobilemanagement/Icon/icons8-back-50.png"))); // NOI18N
        btnPre1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPre1ActionPerformed(evt);
            }
        });

        btnNext1.setBackground(new java.awt.Color(28, 35, 65));
        btnNext1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mobilemanagement/Icon/icons8-forward-50.png"))); // NOI18N
        btnNext1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext1ActionPerformed(evt);
            }
        });

        btnLast1.setBackground(new java.awt.Color(28, 35, 65));
        btnLast1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mobilemanagement/Icon/icons8-double-up-50.png"))); // NOI18N
        btnLast1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLast1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnDSSPLayout = new javax.swing.GroupLayout(pnDSSP);
        pnDSSP.setLayout(pnDSSPLayout);
        pnDSSPLayout.setHorizontalGroup(
            pnDSSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(pnDSSPLayout.createSequentialGroup()
                .addGroup(pnDSSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnDSSPLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiemDS, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(97, 97, 97)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboDanhMucDS, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnDSSPLayout.createSequentialGroup()
                        .addGap(407, 407, 407)
                        .addComponent(btnFirst1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPre1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNext1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLast1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(301, 301, 301))
        );
        pnDSSPLayout.setVerticalGroup(
            pnDSSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDSSPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnDSSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTimKiemDS, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboDanhMucDS, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnDSSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnDSSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnFirst1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPre1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNext1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLast1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnDSSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnDSSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        MenuPanel.addTab("Danh Sách Sản Phẩm", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MenuPanel)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MenuPanel)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnIEMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIEMIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnIEMIActionPerformed

    private void cboManHinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboManHinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboManHinhActionPerformed

    private void btnThemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnThemSPActionPerformed

    private void btnEditSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditSPActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnEditSPActionPerformed

    private void btnDeleteSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteSPActionPerformed

    private void btnNewSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNewSPActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        first();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreActionPerformed
        // TODO add your handling code here:
        prev();
    }//GEN-LAST:event_btnPreActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        next();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        last();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnPinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPinActionPerformed

    private void btnHangSXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHangSXActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHangSXActionPerformed

    private void btnRomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRomActionPerformed

    private void cboHangSXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboHangSXActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboHangSXActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            this.row = tblSanPham.getSelectedRow();
            this.edit();
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void tblSanPhamMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMousePressed
        // TODO add your handling code here:

    }//GEN-LAST:event_tblSanPhamMousePressed

    private void lblHinhAnhMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhAnhMousePressed
        // TODO add your handling code here:
        chonAnh();
    }//GEN-LAST:event_lblHinhAnhMousePressed

    private void btnThemSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemSPMouseClicked
        // TODO add your handling code here:
        insert();
    }//GEN-LAST:event_btnThemSPMouseClicked

    private void btnDeleteSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteSPMouseClicked
        // TODO add your handling code here:
        this.Delete();
    }//GEN-LAST:event_btnDeleteSPMouseClicked

    private void btnEditSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditSPMouseClicked
        // TODO add your handling code here:
        this.update();
    }//GEN-LAST:event_btnEditSPMouseClicked

    private void btnNewSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNewSPMouseClicked
        // TODO add your handling code here:
        clearFrom();
    }//GEN-LAST:event_btnNewSPMouseClicked

    private void btnFirst1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirst1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFirst1ActionPerformed

    private void btnPre1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPre1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPre1ActionPerformed

    private void btnNext1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNext1ActionPerformed

    private void btnLast1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLast1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLast1ActionPerformed

    private void btnIEMIMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIEMIMousePressed
        // TODO add your handling code here:
        openBtnIEMI();
    }//GEN-LAST:event_btnIEMIMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane MenuPanel;
    private javax.swing.JButton btnCPU;
    private javax.swing.JButton btnCamera;
    private javax.swing.JButton btnDeleteSP;
    private javax.swing.JButton btnEditSP;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnFirst1;
    private javax.swing.JButton btnHDH;
    private javax.swing.JButton btnHangSX;
    private javax.swing.JButton btnIEMI;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnLast1;
    private javax.swing.JButton btnManHinh;
    private javax.swing.JButton btnMauSac;
    private javax.swing.JButton btnNewSP;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnNext1;
    private javax.swing.JButton btnPin;
    private javax.swing.JButton btnPre;
    private javax.swing.JButton btnPre1;
    private javax.swing.JButton btnRam;
    private javax.swing.JButton btnRom;
    private javax.swing.JButton btnThemSP;
    private javax.swing.JButton btnTinhTrang;
    private javax.swing.JButton btnXuatXu;
    private javax.swing.JButton btnaddDM;
    private javax.swing.JComboBox<String> cboCPU;
    private javax.swing.JComboBox<String> cboCamera;
    private javax.swing.JComboBox<String> cboDanhMuc;
    private javax.swing.JComboBox<String> cboDanhMucDS;
    private javax.swing.JComboBox<String> cboHDH;
    private javax.swing.JComboBox<String> cboHangSX;
    private javax.swing.JComboBox<String> cboIEMI;
    private javax.swing.JComboBox<String> cboManHinh;
    private javax.swing.JComboBox<String> cboMauSac;
    private javax.swing.JComboBox<String> cboPin;
    private javax.swing.JComboBox<String> cboRam;
    private javax.swing.JComboBox<String> cboRom;
    private javax.swing.JComboBox<String> cboTenDM;
    private javax.swing.JComboBox<String> cboTinhTrang;
    private javax.swing.JComboBox<String> cboXuatXu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblHinhAnh;
    private javax.swing.JPanel pnDSSP;
    private javax.swing.JPanel pnSanPham;
    private javax.swing.JTable tblDSSP;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtIEMI;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTimKiemDS;
    // End of variables declaration//GEN-END:variables
}
