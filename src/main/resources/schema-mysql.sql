--TABLE PHONG
drop table if exists phong;
create table phong(
    id int primary key auto_increment not null ,
    sophong varchar(50),
    toanha varchar(50),
    tinhtrang boolean
);
--TABLE Co So Vat Chat
drop table if exists csvc;
create table csvc(
    id int primary key auto_increment not null ,
    phong_id int,
    soban int,
    loaiban varchar(50),
    soghe int,
    maychieu boolean,
    amthanh boolean,
    dieuhoa boolean
);
--TABLE TRANG THAI PHONG
drop table if exists trangthai_phong;
create table trangthai_phong(
    id int primary key auto_increment not null ,
    phong_id int ,
    thu varchar(50),
    thoigian TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    giangvien_id int,
    lop_id int,
    ca1 boolean,
    ca2 boolean,
    ca3 boolean,
    ca4 boolean,
    ca5 boolean
);
--TABLE DKSD_PHONG
drop table if exists dksd_phong;
create table dksd_phong(
    id int primary key auto_increment,
    giangvien_id int ,
    phong_id int ,
    ngaysudung TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    cahoc int,
    siso int,
    lydo varchar(100)
);
--TABLE ADMIN
drop table if exists admin;
create table admin(
    id int primary key auto_increment,
    ten varchar(50),
    email varchar(50),
    password varchar(100),
    sdt varchar(50)
);
--TABLE GIANG VIEN
drop table if exists giangvien;
create table giangvien(
    id int primary key auto_increment,
    ten varchar(50),
    email varchar(50),
    password varchar(100),
    sdt varchar(50)
);
--TABLE LOP HOC
drop table if exists lophoc;
create table  lophoc(
    id int primary key auto_increment,
    tenlop varchar(50),
    siso int ,
    khoahoc varchar(50)
);
--TABLE ROLE
drop table if exists roles;
create table roles(
    id int primary key auto_increment,
    role_name varchar(50)
);
--TABLE ADMIN_ROLE
drop table if exists  admin_role;
create table admin_role(
    admin_id int not null ,
    role_id int not null
);


