-- 함수: 날짜 함수

-- curdate(), current_date
select curdate(), current_date;

-- curtime(), current_time
select curtime(), current_time;

-- now(), sysdate()
select now(), sysdate();

select now(), sleep(2), now(); -- now()는 쿼리실행기준 시간

select sysdate(), sleep(2), sysdate(); -- sysdate()은 함수 실행 시간

-- date_format
-- 2022년 05월 13일 11:39분 40초
select date_format(now(), '%Y년 %m월 %d일 %h:%i분 %s초');
select date_format(now(), '%y년 %c월 %d일 %h:%i분 %s초');

-- period_diff
-- YYMM, YYYYMM
-- 예제 : 근무개월수 출력.
select period_diff(date_format(curdate(), '%Y%m'), date_format(hire_date, '%Y%m')) 
from employees order by
period_diff(date_format(curdate(), '%Y%m'), date_format(hire_date, '%Y%m')) desc;

-- date_add(=adddate), date_sub(=subdate)
-- 날짜를 date에 type(day, month, year) 형식의 표현식을 더하거나 뺀다.
-- 예제) 각 사원들의 근무 년 수가 5년이 되는 날은 언제인가요?

select first_name, hire_date, date_add(hire_date, interval 5 year) from employees;

-- cast
select '12345' + '10', cast('12345' as int) + 10, concat('12345','10');
select date_format('2022-01-01','%Y년 %m월 %d일'),
 date_format(cast('2022-01-01' as date),'%Y년 %m월 %d일');
select cast(cast(1-2 as unsigned)as signed); -- unsigned만 캐스팅하면 주소값으로 나옴.

-- mysql type
-- varchar(n), char(n), text, CLOB(Character Large Object) -> 갈수록 대용량
-- signed(unsigned), int(integer),  medium int, big int, int(11) -> (백억까지 가능)
-- float, double
-- time, date, datetime
-- LOB: CLOB, BLOB(Binary Large Object) 구조화되지 않은 용량이 큰 데이터를 저장할 수 있는 데이터 타입