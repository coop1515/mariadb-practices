drop table member;
create table member(
	no int(11) not null auto_increment,
    email varchar(200) not null,
    password varchar(64) not null,
    name varchar(100) not null,
    department varchar(100),
    primary key(no)
);

desc member;
alter table member add juminbunho char(13) not null;
desc member;
alter table member drop juminbunho;
desc member;
alter table member add juminbunho char(13) not null after email;
desc member;
alter table member change department department char(13) not null;
desc member;
alter table member add self_intro text;
desc member;
alter table member drop juminbunho;
desc member;

-- insert 멤버 테이블의 칼럼 순서 똑같이 맞춰줘야함.
insert into member values (null, 'coop1515@naver.com', password('1234'), '김현석', '개발팀', null);
insert into member (no, email, name, password, department)
value(null, 'coop1515@naver.com', '김현석', password(1234), '개발팀');

select * from member;

-- update 무조건 where절을 써줘야함. 아니면 전부 다 바뀌게 됨.
update member set email = 'coop1111', password = password('1234') where no = 2;

select * from member;

-- delete 무조건 where절 사용.
delete from member where no = 2;
select * from member;

-- transaction
select @@AUTOCOMMIT; -- 값이 1나오면 켜져있는것
set autocommit = 0;

insert into member (no, email, name, password, department)
value(null, 'coop2222@naver.com', '김현석2', password(1234), '개발팀');

commit;

select * from member;