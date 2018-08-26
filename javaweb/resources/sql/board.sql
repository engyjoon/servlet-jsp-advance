drop table tb_board;
create table tb_board (
	board_num serial, --일련번호
	board_subject varchar(50) not null, --제목
	board_content varchar(2000) not null, --내용
	board_read_count int default 0, --조회수
	board_date timestamp with time zone, --작성일
	member_id varchar(20) not null,
	primary key(board_num)
);

insert into tb_board(board_num, board_subject, board_content, board_date, member_id)
(select nextval('tb_board_board_num_seq'), board_subject, board_content, board_date, member_id from tb_board);