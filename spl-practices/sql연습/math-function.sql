-- 수학 함수

-- abs
select abs(-1), abs(1);

-- mod 나머지 구하는 함수
select mod(10,4), mod(10,3);

-- ceil
select ceil(3.14), ceiling(3.14);

-- floor
select floor(3.14);

-- round(x) : x에 가장 근접한 정수
-- rount(x, d) : x값 중에 소수점 d자리 가장 근접한 실수
select round(1.498), round(1.498,1), round(1.498, 0);

-- power(x,y), pow(x,y) : x의 y제곱
select power(2,10), pow(2, 10);

-- sign(x) 양수면 1 음수면 -1 0이면 0 리턴
select sign(20), sign(-100), sign(0);

-- greatest(x,y,...) : 최댓값 출력, least (x,y,...)최소값 출력
select greatest(10,40,20,50), least(10,40,20,50);
select greatest('a','A','b'), greatest('hello','hellp');
