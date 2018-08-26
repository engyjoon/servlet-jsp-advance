create table tb_member (
	member_id varchar(20) primary key,
	member_pw varchar(20),
	member_name varchar(20),
	member_email varchar(30)
);

insert into tb_member
values('admin', 'admin', '관리자', 'admin@kt.com');