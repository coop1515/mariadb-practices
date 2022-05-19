select * from orderbook;
select * from `member`;
select * from `order`;
select * from cart;
select * from book;
select * from category;

desc member;
desc `order`;
desc orderbook;
desc cart;
desc book;
desc category;

-- 1번
select name, email 
from member;

-- 2번
select *
from category;

-- 3번
select a.no, a.title, a.price, b.category_name
from book a, category b
where a.category_no = b.no;

-- 4번
select c.name '이름', b.title '책이름', b.amount '수량'
from book a ,cart b, member c
where a.no = b.book_no
	and c.no = b.member_no;

-- 5번
select concat(order_no,'-000',no), orderer, payment_price, destination
from `order`;

-- 6번    
select a.no, b.title '책이름', b.amount '수량', (b.price * b.amount) '합계액'
from book a ,cart b, member c 
where a.no = b.book_no
	and c.no = b.member_no;
    
select a.no, b.title, b.amount, b.price * b.amount
					from book a ,cart b, member c
					where a.no = b.book_no
					and c.no = b.member_no;    
