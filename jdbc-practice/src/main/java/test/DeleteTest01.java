package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteTest01 {

	public static void main(String[] args) {
		delete(5L);
		delete(6L);
		delete(7L);

	}
	private static boolean delete(Long no) { //DB는 int의 범위가 광범위해서 long으로 받아주는게 맞음.
		boolean result= false;
		Connection connection = null;
		Statement stmt = null;
		try {
			// 1. JDBC Driver loading (= JDBC CLASS loading : class loader)
			Class.forName("org.mariadb.jdbc.Driver");
			
			// 2.
			String url = "jdbc:mysql://192.168.10.45:3306/webdb?charset=utf8";
			connection = DriverManager.getConnection(url, "webdb","webdb");
			
			// 3. Statement 생성
			stmt = connection.createStatement(); //SQL을 실행할 수 있는 객체
			
			// 4. SQL 실행
			String sql = "delete from department where no = "+ no;
			int count = stmt.executeUpdate(sql);
			result = count == 1;
		} catch (ClassNotFoundException e) {
				System.out.println("fail Driver loading" + e);
		} catch (SQLException e) {
			System.out.println("뭐함");
		} finally {
			try {
				if(stmt != null) {
					stmt.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				
			}
		}
		return result;
	}

}
