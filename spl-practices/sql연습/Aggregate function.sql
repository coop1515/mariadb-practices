-- 집계쿼리: select에 그룹함수가 적용된 경우
-- avg, max, min, count, sum, ....

select avg(salary), max(salary) from salaries;

-- select 절에 그룹함수가 있는 경우, 어떤 컬럼도 select절에 올 수 없다.
select emp_no, avg(salary) from salaries; -- oracle은 에러가 나는 구문, 잘못된 구문임.
-- emp_no는 아무런 의미가 없음.alter

-- query 순서
-- 1) from : 접근 테이블 확인.
-- 2) where : 조건에 맞는 row를 선택.
-- 3) 집계
-- 4) projection
select avg(salary) from salaries where emp_no = '10060';

-- group by 에 참여하고 있는 컬럼은 projection이 가능하다(select 절에 올 수 있다.)
select emp_no, avg(salary), sum(salary)
from salaries group by emp_no order by sum(salary) desc, avg(salary) desc;

-- having
-- 집계결과(결과 임시 테이블)에서 row를 선택해야하는 경우
-- 이미 where 절은 실행이 되었기 때문에 having절에서 이 조건을 주어야 한다.
-- 순서적으로 groupby절 밑에 위치해야 함.
select emp_no, avg(salary)
from salaries group by emp_no having avg(salary) > 60000;

-- order by
select emp_no, avg(salary)
from salaries group by emp_no having avg(salary) > 60000
order by avg(salary) asc;

-- 예제
-- salaries 테이블에서 사번이 10060인 직원의 급여 평균과 총합을 출력하세요.
-- 문법적으로는 오류!!
-- 의미적으로는 맞다(where)
select emp_no,avg(salary), sum(salary) from salaries
where emp_no = '10060';

-- 문법적으로 옳다.
select emp_no,avg(salary), sum(salary) from salaries
group by emp_no having emp_no = '10060';


