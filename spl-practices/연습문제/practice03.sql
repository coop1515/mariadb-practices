-- 문제 1. 
-- 현재 급여가 많은 직원부터 직원의 사번, 이름, 그리고 연봉을 출력 하시오.
select *
from salaries
order by salary desc;

select a.emp_no '사번', concat(a.first_name,' ',a.last_name) '이름', b.salary '연봉'
from employees a, salaries b
where a.emp_no = b.emp_no
	and b.to_date = '9999-01-01'
order by salary desc;
	
-- 문제2.
-- 전체 사원의 사번, 이름, 현재 직책을 이름 순서로 출력하세요.
select a.emp_no '사번', concat(a.first_name,' ',a.last_name) '이름', b.title '직책'
from employees a, titles b
where a.emp_no = b.emp_no
order by a.first_name;

-- 문제3.
-- 전체 사원의 사번, 이름, 현재 부서를 이름 순서로 출력하세요..
select a.emp_no '사번', concat(a.first_name,' ',a.last_name) '이름', b.dept_name '부서'
from employees a, departments b, dept_emp c
where a.emp_no = c.emp_no
	and b.dept_no = c.dept_no
    and c.to_date = '9999-01-01'
order by a.first_name;
-- 문제4.
-- 전체 사원의 사번, 이름, 연봉, 직책, 부서를 모두 이름 순서로 출력합니다.
select a.emp_no '사번', concat(a.first_name,' ',a.last_name) '이름', d.salary '연봉', 
e.title '직책', b.dept_name '부서'
from employees a, departments b, dept_emp c, salaries d, titles e
where a.emp_no = c.emp_no
	and a.emp_no = e.emp_no
	and a.emp_no = d.emp_no
	and b.dept_no = c.dept_no
    and c.to_date = '9999-01-01'
    and d.to_date = '9999-01-01'
    and e.to_date = '9999-01-01'
order by a.first_name;

-- 문제5.
-- ‘Technique Leader’의 직책으로 과거에 근무한 적이 있는 모든 사원의 사번과 이름을 출력하세요.
-- (현재 ‘Technique Leader’의 직책(으로 근무하는 사원은 고려하지 않습니다.)
-- 이름은 first_name과 last_name을 합쳐 출력 합니다.
select *
from titles
where to_date != '9999-01-01'
	and title = 'Technique Leader';

select a.emp_no '사번' , concat(a.first_name,' ',a.last_name) '이름'
from employees a,titles b
where to_date != '9999-01-01'
	and title = 'Technique Leader';
    
-- 문제6.
-- 직원 이름(last_name) 중에서 S(대문자)로 시작하는 직원들의 이름, 부서명, 직책을 조회하세요.
select *
from employees
where substring(last_name,1,1) = upper('s');
--	last_name like 's%';
select concat(a.first_name,' ',a.last_name), d.dept_name, b.title
from employees a, titles b, dept_emp c, departments d
where a.emp_no = b.emp_no
	and a.emp_no = c.emp_no
	and c.dept_no = d.dept_no
    and substring(last_name,1,1) = upper('s');
    
-- 문제7.
-- 현재, 직책이 Engineer인 사원 중에서 현재 급여가 40000 이상인 사원을 급여가 큰 순서대로 출력하세요.
select *
from titles
where to_date = '9999-01-01'
	and title = 'engineer';

select a.emp_no, a.salary, b.title
from salaries a, titles b
where a.emp_no = b.emp_no
	and a.to_date = '9999-01-01'
	and b.to_date = '9999-01-01'
    and b.title = 'engineer'
    and a.salary >= 40000
order by salary desc ;

-- 문제8.
-- 현재 급여가 50000이 넘는 직책을 직책, 급여로 급여가 큰 순서대로 출력하시오

select a.emp_no, a.salary, b.title
from salaries a, titles b
where a.emp_no = b.emp_no
	and a.to_date = '9999-01-01'
	and b.to_date = '9999-01-01'
    and a.salary >= 50000
order by salary desc ;

-- 문제9.
-- 현재, 부서별 평균 연봉을 연봉이 큰 부서 순서대로 출력하세요.
select b.dept_name '부서 이름' , avg(salary) '평균연봉'
from salaries a, departments b, dept_emp c
where a.emp_no = c.emp_no
	and b.dept_no = c.dept_no
group by dept_name
order by avg(salary) desc;

-- 문제10.
-- 현재, 직책별 평균 연봉을 연봉이 큰 직책 순서대로 출력하세요.
select a.emp_no '사번', avg(salary) '평균연봉'
from salaries a, titles b
where a.emp_no = b.emp_no
group by title
order by avg(salary) desc;