/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mobilemanagement.View;

import com.mobilemanagement.Dao.DanhMucSPDao;
import com.mobilemanagement.Dao.SanPhamCTDao;
import com.mobilemanagement.Dao.SanPhamDao;
import com.mobilemanagement.Model.DanhMucSP;
import com.mobilemanagement.Model.SanPham;
import com.mobilemanagement.Model.SanPhamCT;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

/**
 *
 * @author DINHVU
 */
public class Test {

     public static void main(String[] args) {
        // Tạo một đối tượng của class chứa phương thức SelectTableSP
         SanPhamCTDao dao = new SanPhamCTDao();
         DanhMucSPDao dmdao = new DanhMucSPDao();
       SanPhamCT spct = new SanPhamCT();
        // Set các giá trị cho đối tượng SanPhamCT
        spct.setRom("TestRom");
        spct.setRam("TestRam");
        spct.setMausac("TestMausac");
        spct.setHinhAnh("TestHinhAnh");
        spct.setGiaBan(1000.0);
        spct.setMaSP("12312312");

        // Thực hiện insert
        dao.insert(spct);

    }
    }

