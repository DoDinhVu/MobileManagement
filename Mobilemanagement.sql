CREATE DATABASE MobileManagementDB;


USE MobileManagementDB;

GO


-- Tạo tài khoản UserLogin
CREATE TABLE UserLogin (
    MaID INT IDENTITY(1,1) NOT NULL PRIMARY KEY, 
    UserName VARCHAR(8) NOT NULL UNIQUE,
    CHECK (LEN(UserName) = 8),
    PASSWORD VarChar(12) NOT NULL,
    CHECK (LEN(PASSWORD) >= 6 AND LEN(PASSWORD) <= 12),
    Vaitro BIT NOT NULL,
);


GO

INSERT UserLogin VALUES('VUDD1001','acb12467',1);
GO

 -- Tạo Tài Khoản Chi tiết
CREATE TABLE TaiKhoanCT( 
    MaTK INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
    HoTen NVARCHAR(50) NOT NULL,
    GioiTinh BIT NOT NULL,
    NamSinh DATE NOT NULL,
    DiaChi NVARCHAR(100) NOT NULL,
    Email VARCHAR(50) NOT NULL UNIQUE,
    SDT VARCHAR(10) NOT NULL UNIQUE,
    HinhAnh VARCHAR(50) NULL,
    ChucVu NVARCHAR(20) NOT NULL,
    MaID INT NOT NULL UNIQUE,
    FOREIGN KEY (MaID) REFERENCES UserLogin(MaID)
);


SELECT * FROM TaiKhoanCT

--Tạo Table DanhMuc
CREATE TABLE DanhMucSP(
    MaDM VARCHAR(8) NOT NULL PRIMARY KEY,
    TenDM NVARCHAR(30) NOT NULL UNIQUE
);

INSERT DanhMucSP VALUES ('DM01', 'Iphone');
INSERT DanhMucSP VALUES ('DM02', 'SamSung');
INSERT DanhMucSP VALUES ('DM03', 'Xiaomi');
INSERT DanhMucSP VALUES ('DM04', 'Oppo');
INSERT DanhMucSP VALUES ('DM05', 'Vivo');

SELECT * FROM DanhMucSP


DROP TABLE SanPham

-- Tạo Bảng Sản Phẩm
CREATE TABLE SanPham(
    MaSP VARCHAR(8) NOT NULL PRIMARY KEY,
    CHECK(LEN(MaSP) = 8),
    TenSP NVARCHAR(50) NOT NULL UNIQUE,
    HDH VARCHAR(30) NOT NULL,
    CPU VARCHAR(30) NOT NULL,
    Camera VARCHAR(20) NOT NULL,
    Pin VARCHAR(50) NOT NULL,
    HangSX VARCHAR(20) NOT NULL, -- Sản Xuất
    ManHinh VARCHAR(100) NOT NULL,
    TinhTrang NVARCHAR(10) NOT NULL,  
    MaDM VARCHAR(8) NOT NULL   
    FOREIGN KEY (MaDM) REFERENCES DanhMucSP(MaDM) ON UPDATE CASCADE
);

INSERT INTO SanPham(MaSP, TenSP, HDH, CPU, Camera, Pin, HangSX, ManHinh, TinhTrang, MaDM) VALUES
('SPIP14PM', 'iPhone 14 Pro Max', 'iOS', 'A16 Bionic', '48MP + 12MP + 12MP', '4323mAh', 'Apple', '6.7 inch Super Retina XDR OLED', N'Mới', 'DM01'),
('SPSSS23U', 'Samsung Galaxy S23 Ultra', 'Android', 'Qualcomm Snapdragon 8 Gen 2', '200MP + 10MP + 10MP', '5000mAh', 'Samsung', '6.8 inch Dynamic AMOLED 2X', N'Mới', 'DM02'),
('SPXAM13P', 'Xiaomi 13 Pro', 'Android', 'MediaTek Dimensity 9200', '50MP + 50MP + 50MP', '4800mAh', 'Xiaomi', '6.7 inch AMOLED 120Hz', N'Mới', 'DM03'),
('SPOP011P', 'OPPO Find 11 Pro', 'Android', 'Qualcomm Snapdragon 8 Gen 2', '50MP + 50MP + 8MP', '4800mAh', 'OPPO', '6.7 inch AMOLED 120Hz', N'Mới', 'DM04'),
('SPVVX90P', 'Vivo X90 Pro', 'Android', 'MediaTek Dimensity 9200', '50MP + 50MP + 12MP', '4700mAh', 'Vivo', '6.7 inch AMOLED 120Hz', N'Mới', 'DM05'),
('SPIP13PM', 'iPhone 13 Pro Max', 'iOS', 'A15 Bionic', '12MP + 12MP + 12MP', '4323mAh', 'Apple', '6.7 inch Super Retina XDR OLED', N'Mới', 'DM01'),
('SPSSS22U', 'Samsung Galaxy S22 Ultra', 'Android', 'Qualcomm Snapdragon 8 Gen 1', '108MP + 12MP + 10MP', '5000mAh', 'Samsung', '6.8 inch Dynamic AMOLED 2X', N'Mới', 'DM02'),
('SPXAM12P', 'Xiaomi 12 Pro', 'Android', 'MediaTek Dimensity 9000', '50MP + 50MP + 50MP', '4600mAh', 'Xiaomi', '6.7 inch AMOLED 120Hz', N'Mới', 'DM03'),
('SPOPO10P', 'OPPO Find 10 Pro', 'Android', 'Qualcomm Snapdragon 8 Gen 1', '50MP + 50MP + 8MP', '4500mAh', 'OPPO', '6.7 inch AMOLED 120Hz', N'Mới', 'DM04'),
('SPVVX88P', 'Vivo X80 Pro', 'Android', 'MediaTek Dimensity 9000', '50MP + 48MP + 12MP', '4500mAh', 'Vivo', '6.7 inch AMOLED 120Hz', N'Mới', 'DM05');




SELECT * FROM SanPhamCT

GO

GO

INSERT INTO SanPhamCT(MaSPCT, Rom, Ram, Mausac, HinhAnh, GiaBan, MaSP) VALUES
('14-128GB-Black', '128GB', '8GB', 'Đen', 'IP14PMBlack.png', 29999000, 'SPIP14PM'),
('14-256GB-White', '256GB', '8GB', N'Trắng', 'IP14PMBlack.png', 32999000, 'SPIP14PM'),
('14-512GB-Purple', '512GB', '8GB', N'Tím', 'IP14PMBlack.png', 36999000, 'SPIP14PM'),
('14-1TB-Gold', '1TB', '8GB', N'Vàng', 'IP14PMBlack.png', 42999000, 'SPIP14PM');

INSERT INTO SanPhamCT(MaSPCT, Rom, Ram, Mausac, HinhAnh, GiaBan,  MaSP) VALUES
('23-256GB-Black', '256GB', '8GB', N'Đen', 'SAMSUNGU23.png', 39999000, 'SPSSS23U'),
('23-512GB-White', '512GB', '8GB', N'Trắng', 'SAMSUNGU23.png', 42999000, 'SPSSS23U'),
('23-1TB-Green', '1TB', '12GB', N'Xanh lá', 'SAMSUNGU23.png', 48999000, 'SPSSS23U');



-- Tạo bảng lưu IMEI điện thoại
CREATE TABLE IMEISP(
    IMEI VARCHAR(15) NOT NULL PRIMARY KEY ,
    CHECK (LEN(IMEI) = 15),
    XuatXu NVARCHAR(50) NOT NULL,
    MaSPCT VarChar(15) NOT NULL,
    FOREIGN KEY (MaSPCT) REFERENCES SanPhamCT(MaSPCT) ON DELETE CASCADE ON UPDATE CASCADE
)
INSERT IMEISP VALUES('FE5EEEF00F9C7EB',N'Mỹ','14-128GB-Black'),
('C73FAFFA84D26B0',N'Hàn Quốc','14-128GB-Black'),
('2F1136564DF8778',N'Việt Nam','14-128GB-Black'),
('5C885E7E3CE03A3',N'','14-128GB-Black'),
('63BF885D29CA6D3',N'Trung Quốc','14-128GB-Black'),
('07AA4668132F1A7',N'Việt NamMỹ','14-128GB-Black'),
('EAEDE33B9BD8007',N'Việt Nam','14-128GB-Black');


DELETE FROM IMEISP
WHERE IMEI = 'EAEDE33B9BD8007';

SELECT REPLACE(RIGHT(NEWID(), 16), '-', '') AS MaSo;



-- Tạo Bảng KhachHang
CREATE TABLE KhachHang(
    MaKH INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
    HoTen NVARCHAR(50) NOT NULL,
    DiaChi NVARCHAR(100) NULL,
    SDT VARCHAR(10) NULL,
    Email VARCHAR(50),
)


-- Tạo bảng DonHang
CREATE TABLE DonHang(
    MaDH VARCHAR(10) NOT NULL PRIMARY KEY,
    NgayLap DATETIME NOT NULL,
    TrangThai VARCHAR(20) NOT NULL,
    GhiChu NVARCHAR(255) Null,
    MaKH INT NOT NULL,
    MaTK INT NOT NULL,
    MaGG VARCHAR(12) NOT NULL,
    FOREIGN KEY(MaKH) REFERENCES KhachHang(MaKH) ON UPDATE CASCADE,
    FOREIGN KEY (MaTK) REFERENCES TaiKhoanCT(MaTK) ON UPDATE CASCADE,
    FOREIGN KEY (MaGG) REFERENCES GiamGia(MaGG) ON UPDATE CASCADE
);



-- Tạo Bảng Đơn hàng chi tiết
CREATE TABLE DonHangCT(
    MaDHCT INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
    MaDH VARCHAR(10) NOT NULL,
    MaSP VARCHAR(8) NOT NULL,
    SoLuong INT NOT NULL,
    GiaDaGiam DECIMAL(10,2)  NULL,
    TongGia DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (MaDH) REFERENCES DonHang(MaDH) ON UPDATE CASCADE,
    FOREIGN KEY (MaSP) REFERENCES SanPham(MaSP) ON UPDATE CASCADE
    
);


-- Tạo bảng giảm giá
CREATE TABLE GiamGia (
    MaGG VARCHAR(12) NOT NULL PRIMARY KEY,
    Giatri FLOAT NOT NULL ,
    NgayBatDau DATE NOT NULL,
    NgayKetThuc DATE NOT NULL,
     CHECK (NgayBatDau < NgayKetThuc), 
    GhiChu NVARCHAR(255) NULL
);