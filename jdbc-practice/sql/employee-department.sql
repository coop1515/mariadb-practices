-- department.findAll
select * from department order by no desc;

-- DepartmentDao : update
update department set name = '솔루션 개발팀' where no = 3;

-- employeeDao : findAll
select no, name, department_no from employee order by no desc;

-- employeeDao:delete()
delete from employee;

select *
