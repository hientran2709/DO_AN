delete from phong where 1=1;
insert into phong(sophong,toanha,tinhtrang) values('P101','A2',true);
insert into phong(sophong,toanha,tinhtrang) values('P102','A2',true);
insert into phong(sophong,toanha,tinhtrang) values('P103','A2',true);

delete from csvc where 1=1;
insert into csvc(phong_id,soban,loaiban,soghe,maychieu,amthanh,dieuhoa) values('1',46,'hoa phat',92,true,true,true);
insert into csvc(phong_id,soban,loaiban,soghe,maychieu,amthanh,dieuhoa) values('2',38,'hoa phat',76,true,true,false);
insert into csvc(phong_id,soban,loaiban,soghe,maychieu,amthanh,dieuhoa) values('3',50,'hoa phat',102,true,true,false);

delete from trangthai_phong where 1=1;
insert into trangthai_phong(phong_id,giangvien_id,lop_id,thu,ca1,ca2,ca3,ca4,ca5)
values(1,1,1,'2',false,true,true,false,true);
insert into trangthai_phong(phong_id,giangvien_id,lop_id,thu,ca1,ca2,ca3,ca4,ca5)
values(2,2,2,'3',true,false,false,false,true);
insert into trangthai_phong(phong_id,giangvien_id,lop_id,thu,ca1,ca2,ca3,ca4,ca5)
values(3,3,3,'4',false,false,false,true,true);

delete from dksd_phong where 1=1;
insert into dksd_phong(phong_id,giangvien_id,cahoc,siso,lydo) values(3,2,3,70,'day bu');
insert into dksd_phong(phong_id,giangvien_id,cahoc,siso,lydo) values(2,1,1,48,'day bu');
insert into dksd_phong(phong_id,giangvien_id,cahoc,siso,lydo) values(1,3,2,65,'day bu');

delete from admin where 1=1;
insert into admin(ten,email,password,sdt) values('QLDT','hientran2709@gmail.com','abc123','0123456789');
insert into admin(ten,email,password,sdt) values('CSVC','hientran1905@gmail.com','abc123','0123456789');
insert into admin(ten,email,password,sdt) values('DIEUDO','hientran1505@gmail.com','abc123','0123456789');

delete from giangvien where 1=1;
insert into giangvien(ten,email,password,sdt) values('giang vien1','giangvien2709@gmail.com','abc123','0123456789');
insert into giangvien(ten,email,password,sdt) values('giang vien2','giangvien2709@gmail.com','abc123','6548545213');
insert into giangvien(ten,email,password,sdt) values('giang vien3','giangvien2709@gmail.com','abc123','6543213545');

delete from lophoc where 1=1;
insert into lophoc(tenlop,siso,khoahoc) values('CNTT1',49,'K57');
insert into lophoc(tenlop,siso,khoahoc) values('CNTT2',56,'K57');
insert into lophoc(tenlop,siso,khoahoc) values('CNTT3',62,'K57');

delete from roles where 1=1;
insert into roles(role_name) values('ROLE_CSVC');
insert into roles(role_name) values('ROLE_QLDT');
insert into roles(role_name) values('ROLE_GV');

delete from admin_role where 1=1;
insert into admin_role(admin_id,role_id) values(1,2);
insert into admin_role(admin_id,role_id) values(2,1);
insert into admin_role(admin_id,role_id) values(3,2);
insert into admin_role(admin_id,role_id) values(3,2);

