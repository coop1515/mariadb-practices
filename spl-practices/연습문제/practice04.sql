-- 문제1.
-- 현재 평균 연봉보다 많은 월급을 받는 직원은 몇 명이나 있습니까?
select avg(salary)
from salaries
where to_date = '9999-01-01';

select *
from employees a, salaries b
where a.emp_no = b.emp_no
	and b.to_date = '9999-01-01'
	and b.salary > (select avg(salary)
	from salaries
    where to_date = '9999-01-01')
order by salary ;

-- 문제2. 
-- 현재, 각 부서별로 최고의 급여를 받는 사원의 사번, 이름, 부서 연봉을 조회하세요.
-- 단 조회결과는 연봉의 내림차순으로 정렬되어 나타나야 합니다.

-- 각 부서별 최고연봉 
select b.dept_name, max(a.salary)
from salaries a, departments b, dept_emp c
where a.emp_no = c.emp_no
	and b.dept_no = c.dept_no
	and a.to_date = '9999-01-01'
group by b.dept_name;

select a.emp_no, a.first_name, b.salary
from employees a, salaries b, dept_emp c, departments d,
(select c.dept_no, max(a.salary) as max_salary
from salaries a, departments b, dept_emp c
where a.emp_no = c.emp_no
	and b.dept_no = c.dept_no
	and a.to_date = '9999-01-01'
group by b.dept_name) e

where  a.emp_no = b.emp_no
	and a.emp_no = c.emp_no
    and c.dept_no = d.dept_no
	and c.dept_no = e.dept_no
    and b.to_date = '9999-01-01'
    and c.to_date = '9999-01-01'
    and b.salary = e.max_salary
order by b.salary desc;
    
-- 문제3.
-- 현재, 자신의 부서 평균 급여보다 연봉(salary)이 많은 사원의 사번, 이름과 연봉을 조회하세요 
-- 부서 평균
select * ,avg(salary)
from departments a, dept_emp b, salaries c
where a.dept_no = b.dept_no
	and b.emp_no = c.emp_no
    and b.to_date = '9999-01-01'
    and c.to_date = '9999-01-01'
group by b.dept_no
having c.salary > avg(salary);

select *
from employees a, salaries b,  (select avg(salary) as sal
from departments a, dept_emp b, salaries c
where a.dept_no = b.dept_no
	and b.emp_no = c.emp_no
    and b.to_date = '9999-01-01'
    and c.to_date = '9999-01-01'
group by b.dept_no)
where a.emp_no = b.emp_no
	and b.to_date = '9999-01-01'
    and b.salary > (select avg(salary) as sal
from departments a, dept_emp b, salaries c
where a.dept_no = b.dept_no
	and b.emp_no = c.emp_no
    and b.to_date = '9999-01-01'
    and c.to_date = '9999-01-01'
group by b.dept_no);

-- 문제4.
-- 현재, 사원들의 사번, 이름, 매니저 이름, 부서 이름으로 출력해 보세요.
select a.emp_no, a.first_name, c.dept_name
from employees a,dept_manager b, departments c
where a.emp_no = b.emp_no
	and b.dept_no = c.dept_no
	and b.to_date = '9999-01-01';
    
-- 문제5.
-- 현재, 평균연봉이 가장 높은 부서의 사원들의 사번, 이름, 직책, 연봉을 조회하고 연봉 순으로 출력하세요.
select b.dept_no,avg(salary)
from departments a, dept_emp b, salaries c
where a.dept_no = b.dept_no
	and b.emp_no = c.emp_no
    and b.to_date = '9999-01-01'
    and c.to_date = '9999-01-01'
group by b.dept_no
order by avg(salary) desc
limit 0,1;

select a.emp_no, a.first_name, b.title, c.salary, d.dept_no
from employees a, titles b, salaries c, dept_emp d
where a.emp_no = b.emp_no
	and a.emp_no = c.emp_no
    and a.emp_no = d.emp_no
	and b.to_date = '9999-01-01'
	and c.to_date = '9999-01-01'
    and dept_no = (
    select b.dept_no
from departments a, dept_emp b, salaries c
where a.dept_no = b.dept_no
	and b.emp_no = c.emp_no
    and b.to_date = '9999-01-01'
    and c.to_date = '9999-01-01'
group by b.dept_no
order by avg(salary) desc
limit 0,1)
order by c.salary desc;

-- 문제6.
-- 평균 연봉이 가장 높은 부서는? 
select a.dept_name,avg(salary)
from departments a, dept_emp b, salaries c
where a.dept_no = b.dept_no
	and b.emp_no = c.emp_no
    -- and b.to_date = '9999-01-01'
--     and c.to_date = '9999-01-01'
group by b.dept_no
order by avg(salary) desc
limit 0,1;

-- 문제7.
-- 평균 연봉이 가장 높은 직책?
select a.title,avg(b.salary)
from titles a, salaries b
where a.emp_no = b.emp_no
	-- and a.to_date = '9999-01-01'
--     and b.to_date = '9999-01-01'
group by title
order by avg(b.salary) desc
limit 0,1;
-- 문제8.
-- 현재 자신의 매니저보다 높은 연봉을 받고 있는 직원은?
-- 부서이름, 사원이름, 연봉, 매니저 이름, 메니저 연봉 순으로 출력합니다. 
-- 현재 매니저들의 연봉
select *
from dept_manager a, salaries b
where a.to_date = '9999-01-01'
	and b.to_date = '9999-01-01'
    and a.emp_no = b.emp_no;

select a.dept_name, c.first_name, d.salary
from departments a, dept_manager b, employees c, salaries d
where a.dept_no = b.dept_no
	and b.emp_no = c.emp_no
	and c.emp_no = d.emp_no
    and d.to_date = '9999-01-01'
    and a.dept_no = 'd001'
    and d.salary > '56654';



