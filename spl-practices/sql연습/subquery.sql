-- sub query

-- 1) select절
select (select max(salary) from salaries);
-- 2) from 절의 서브 쿼리
select now() as a, sysdate() as b, 3+1 as c;
select s.a, s.b
 from (select now() as a, sysdate() as b, 3+1 as c) s;

-- 3) where 절의 서브쿼리
-- 예제
-- 현재 Fai Bale이 근무하는 부서에서 근무하는 직원의 사번, 전체 이름을 출력해보세요.

-- 부서 구하기 
select dept_no
from dept_emp a, employees b
where a.emp_no = b.emp_no
	and a.to_date = '9999-01-01'
    and concat(b.first_name,' ', b.last_name) = 'Fai Bale';

-- 구한 부서의 사람들 구하기
select b.emp_no, b.first_name
from dept_emp a, employees b
where a.emp_no = b.emp_no
	and a.to_date = '9999-01-01'
    and dept_no = 'd004';

-- sol)서브쿼리 이용해 한번에 구하기
select b.emp_no, b.first_name
from dept_emp a, employees b
where a.emp_no = b.emp_no
	and a.to_date = '9999-01-01'
    and dept_no =
    (
		select dept_no
		from dept_emp a, employees b
		where a.emp_no = b.emp_no
		and a.to_date = '9999-01-01'
		and concat(b.first_name,' ', b.last_name) = 'Fai Bale');
        
-- 3-1) 단일행 연산자: =, >, <, >=, <=, <>, !=
-- 실습문제: 현재 전체사원의 평균 연봉보다 적은 급여를 받는 사원의 이름, 급여를 나타내세요.

select avg(salary)
from salaries 
where to_date = '9999-01-01';

select a.first_name, b.salary 
from employees a, salaries b
where 
        b.salary < (
		select avg(b.salary)
		from salaries 
		where to_date = '9999-01-01')
        order by b.salary ;

select b.first_name, a.salary
from salaries a, employees b
where a.emp_no = b.emp_no
	and a.to_date = '9999-01-01'
    and a.salary < (
		select avg(salary)
		from salaries 
		where to_date = '9999-01-01')
order by a.salary desc;

-- 실습문제 2 : 현재 가장 적은 평균급여를 받고있는 직책과 그 직책의 평균급여 를 출력하세요
-- 1) 현재 가장 적은 평균급여 출력
-- 1-1) 직책별 평균 급여
select a.title, avg(b.salary)
from titles a, salaries b
where a.emp_no = b.emp_no
	and a.to_date = '9999-01-01'
    and b.to_date = '9999-01-01'
group by a.title;
-- 1-2) 가장 적은 평균 급여
select min(c.avg_salary)
from (select a.title, avg(b.salary) as avg_salary
	from titles a, salaries b
	where a.emp_no = b.emp_no
		and a.to_date = '9999-01-01'
		and b.to_date = '9999-01-01'
	group by a.title) c;
    
-- 2-1 ) sol : subquery
select a.title, avg(b.salary)
from titles a, salaries b
where a.emp_no = b.emp_no
	and a.to_date = '9999-01-01'
    and b.to_date = '9999-01-01'
group by a.title
having avg(salary) = (
    select min(c.avg_salary)
	from (select a.title, avg(b.salary) as avg_salary
	from titles a, salaries b
	where a.emp_no = b.emp_no
		and a.to_date = '9999-01-01'
		and b.to_date = '9999-01-01'
	group by a.title) c);
    
-- 2-2 ) sol2 : top-k
select a.title, avg(b.salary)
from titles a, salaries b
where a.emp_no = b.emp_no
	and a.to_date = '9999-01-01'
    and b.to_date = '9999-01-01'
group by a.title
order by avg(salary) asc
limit 0,1;

-- 3-2) 복수행 연산자: in, not in, any, all
-- any 사용법 
-- 1. =any : in 하나라도 있으면 됨.
-- 2. >any, >=any : 최소값
-- 3. <any, <=any : 최대값
-- 4. <>any : not in 하나도 없으면 됨

-- all 사용법 -> 많이 안씀
-- 1. =all : 말이 안되는 사용법  사용 x
-- 2. >all, >=all : 최대값
-- 3. <all, <=all : 최소값
-- 4. <>all : 다 없으면 됨. 가능은 함.

-- 실습문제 3: 현재 급여가 50000 이상인 직원의 이름을 출력하세요. (급여가 큰 순서대로)
-- ex) 현석 50000
-- ex) 누구 50001
-- sol1) join
select a.first_name, b.salary
from employees a, salaries b
where a.emp_no = b.emp_no
	and b.to_date = '9999-01-01'
    and b.salary >= 50000
order by b.salary ;

-- sol2) subquery
select emp_no, salary
from salaries
where to_date = '9999-01-01'
	and salary >= 50000;

select a.first_name, b.salary 
from employees a, salaries b
where a.emp_no = b.emp_no
	and b.to_date = '9999-01-01'
    and (a.emp_no, b.salary) in (
		select emp_no, salary
		from salaries
		where to_date = '9999-01-01'
		and salary >= 50000)
order by b.salary;

-- 실습문제 4: 현재 각 부서별로 최고 월급을 받는 직원의 부서이름, 이름과 월급을 출력하세요.
-- 
select a.dept_no, max(b.salary)
from dept_emp a, salaries b
where a.emp_no = b.emp_no
	and a.to_date = '9999-01-01'
	and b.to_date = '9999-01-01'
group by a.dept_no;

-- sol1) where subquery: in(any)
SELECT d.dept_name, b.first_name, c.salary
FROM
    dept_emp a,
    employees b,
    salaries c,
    departments d
WHERE
    a.emp_no = b.emp_no
        AND b.emp_no = c.emp_no
        AND a.dept_no = d.dept_no
        AND a.to_date = '9999-01-01'
        AND c.to_date = '9999-01-01'
        AND (a.dept_no , c.salary) IN (SELECT 
            a.dept_no, MAX(b.salary)
        FROM
            dept_emp a,
            salaries b
        WHERE
            a.emp_no = b.emp_no
                AND a.to_date = '9999-01-01'
                AND b.to_date = '9999-01-01'
        GROUP BY a.dept_no);

-- sol2) from subquery
SELECT d.dept_name, b.first_name, c.salary
FROM
    dept_emp a,
    employees b,
    salaries c,
    departments d,
    (select a.dept_no, max(b.salary) as max_salary
	from dept_emp a, salaries b
	where a.emp_no = b.emp_no
	and a.to_date = '9999-01-01'
	and b.to_date = '9999-01-01'
	group by a.dept_no) e
WHERE	a.emp_no = b.emp_no
        AND b.emp_no = c.emp_no
        AND a.dept_no = d.dept_no
        and a.dept_no = e.dept_no
        AND a.to_date = '9999-01-01'
        AND c.to_date = '9999-01-01'
        and c.salary = e.max_salary;