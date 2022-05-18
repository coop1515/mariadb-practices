package hr.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hr.vo.EmployeeVo;

public class EmployeeDao {

	public List<EmployeeVo> findbyNameOrLastName(String name) {

		List<EmployeeVo> result = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 1. JDBC Driver loading (= JDBC CLASS loading : class loader)
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. connection
			String url = "jdbc:mysql://192.168.10.45:3306/employees?charset=utf8";
			connection = DriverManager.getConnection(url, "hr", "hr");

			// 3. SQL 준비
			String sql = "select emp_no, first_name, last_name, date_format(hire_date, '%Y-%m-%d')"
					+ "	from employees"
					+ "	where first_name like ?"
					+ "	or last_name like ?"
					+ "	order by hire_date"
					+ " limit 0,100"; // test code consol buffer사이즈 때문에. 임시적으로 걸어둠.
			pstmt = connection.prepareStatement(sql); // SQL을 실행할 수 있는 객체

			// 4. parameter mapping
			pstmt.setString(1, "%"+name+"%");
			pstmt.setString(2, "%"+name+"%");
			
			// 5. SQL 실행
			rs = pstmt.executeQuery();

			// 6. 결과처리
			while (rs.next()) {
				Long no = rs.getLong(1);
				String firstname = rs.getString(2);
				String lastname = rs.getString(3);
				String hireDate = rs.getString(4);
				
				EmployeeVo vo = new EmployeeVo();
				vo.setNo(no);
				vo.setFirstName(firstname);
				vo.setLastName(lastname);
				vo.setHireDate(hireDate);

				result.add(vo);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("fail Driver loading" + e);
		} catch (SQLException e) {
			System.out.println("뭐함");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {

			}
		}
		return result;
	}

	public List<EmployeeVo> findBySalary(int minSalary, int maxSalary) {
		List<EmployeeVo> result = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 1. JDBC Driver loading (= JDBC CLASS loading : class loader)
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. connection
			String url = "jdbc:mysql://192.168.10.45:3306/employees?charset=utf8";
			connection = DriverManager.getConnection(url, "hr", "hr");

			// 3. SQL 준비
			String sql = "select a.emp_no, a.first_name, a.last_name, b.salary"
					+ "	from employees a, salaries b"
					+ "	where a.emp_no = b.emp_no"
					+ "		and b.salary > ?" // minSalary
					+ "        and b.salary < ?" // maxSalary
					+ "    order by salary"; // test code consol buffer사이즈 때문에. 임시적으로 걸어둠.
			pstmt = connection.prepareStatement(sql); // SQL을 실행할 수 있는 객체

			// 4. parameter mapping
			pstmt.setInt(1, minSalary);
			pstmt.setInt(2, maxSalary);
			
			// 5. SQL 실행
			rs = pstmt.executeQuery();

			// 6. 결과처리
			while (rs.next()) {
				Long no = rs.getLong(1);
				String firstname = rs.getString(2);
				String lastname = rs.getString(3);
				int salary = rs.getInt(4);
				
				EmployeeVo vo = new EmployeeVo();
				vo.setNo(no);
				vo.setFirstName(firstname);
				vo.setLastName(lastname);
				vo.setSalary(salary);

				result.add(vo);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("fail Driver loading" + e);
		} catch (SQLException e) {
			System.out.println("뭐함");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {

			}
		}
		return result;
	}
}
