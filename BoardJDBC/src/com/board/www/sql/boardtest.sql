-- BOARD TABLE 생성

drop table board ;

create table board(
	bno number(5) primary key,
	btitle nvarchar2(20) not null,
	bcontent nvarchar2(1000) not null,
	bwriter nvarchar2(10) not null,
	bDate date not null
); -- board 테이블 생성

create sequence b_seq increment by 1 start with 1 nocycle nocache ; 
-- 번호 자동 생성기 


-- 더미데이터 입력
insert into board(bno, btitle, bcontent, bwriter, bDate) values(b_seq.nextval, '보드만들기 1', '비오지마', 'boardtest', sysdate);
insert into board(bno, btitle, bcontent, bwriter, bDate) values(b_seq.nextval, '보드만들기 2', '비오지마', 'boardtest', sysdate);
insert into board(bno, btitle, bcontent, bwriter, bDate) values(b_seq.nextval, '보드만들기 3', '비오지마', 'boardtest', sysdate);
insert into board(bno, btitle, bcontent, bwriter, bDate) values(b_seq.nextval, '보드만들기 4', '비오지마', 'boardtest', sysdate);
insert into board(bno, btitle, bcontent, bwriter, bDate) values(b_seq.nextval, '보드만들기 5', '비오지마', 'boardtest', sysdate);
insert into board(bno, btitle, bcontent, bwriter, bDate) values(b_seq.nextval, '보드만들기 6', '비오지마', 'boardtest', sysdate);

select * from board;

-- member 테이블

create table member(
	mno number(5) primary key,
	mid nvarchar2(10) not null,
	mpw nvarchar2(10) not null,
	mdate date not null
);

-- 더미데이터 
insert into member(mno, mid, mpw, mdate) values(b_seq.nextval, 'boardtest', '1234', sysdate);
insert into member(mno, mid, mpw, mdate) values(b_seq.nextval, 'mem1', '1234', sysdate);
insert into member(mno, mid, mpw, mdate) values(b_seq.nextval, 'mem2', '1234', sysdate);
insert into member(mno, mid, mpw, mdate) values(b_seq.nextval, 'mem3', '1234', sysdate);
insert into member(mno, mid, mpw, mdate) values(b_seq.nextval, 'mem4', '1234', sysdate);

select * from member;
