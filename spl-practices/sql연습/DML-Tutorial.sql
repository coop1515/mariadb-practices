-- Basic Query

SELECT VERSION(), CURRENT_DATE, current_date();
-- 대소문 구분이 없다.
select version(), current_date;

-- 수학함수
select sin(pi()/4), (4+1)*5;

select version(); select now(); -- shell창에서 하면 둘다 실행 가능.

drop table pet;
-- 테이블 생성
create table pet(
	name varchar(20), 
    owner varchar(20),
    species varchar(20),
    gender char(1),
    birth date,
    death date    
);

-- schema 확인
desc pet;

-- 데이터 넣기(생성, Create)
insert into pet values('담이','김현석','cat','M','2021-1-1',null);
insert into pet(birth, name) values('2021-1-1','담이');

-- pet(birth, name) values()도 가능.

-- 데이터 검색(읽기, Read)
select * from pet;
select name,owner, species, gender, birth, death from pet;

-- 데이터 삭제(삭제, Delete)
delete from pet where name = '담이';

-- load data local infile
load data local infile 'D:\\pet.txt' into table pet;