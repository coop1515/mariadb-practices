package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteTest02 {

	public static void main(String[] args) {
		delete(11L);

		

	}
	private static boolean delete(Long no) { //DB는 int의 범위가 광범위해서 long으로 받아주는게 맞음.
		boolean result= false;
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			// 1. JDBC Driver loading (= JDBC CLASS loading : class loader)
			Class.forName("org.mariadb.jdbc.Driver");
			
			// 2. connection
			String url = "jdbc:mysql://192.168.10.45:3306/webdb?charset=utf8";
			connection = DriverManager.getConnection(url, "webdb","webdb");
			
			// 3. SQL prepare
			String sql = "delete from department where no = ?";
			pstmt = connection.prepareStatement(sql); //SQL을 실행할 수 있는 객체
			
			// 4. parameter mapping
			pstmt.setLong(1,no);
			
			// 5. SQL 실행
			int count = pstmt.executeUpdate();
			result = count == 1;
		} catch (ClassNotFoundException e) {
				System.out.println("fail Driver loading" + e);
		} catch (SQLException e) {
			System.out.println("뭐함");
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				
			}
		}
		return result;
	}
	private static void delete() { //전부 삭제
		boolean result= false;
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			// 1. JDBC Driver loading (= JDBC CLASS loading : class loader)
			Class.forName("org.mariadb.jdbc.Driver");
			
			// 2. connection
			String url = "jdbc:mysql://192.168.10.45:3306/webdb?charset=utf8";
			connection = DriverManager.getConnection(url, "webdb","webdb");
			
			// 3. SQL 준비
			String sql = "delete from department";
			stmt = connection.prepareStatement(sql); //SQL을 실행할 수 있는 객체
			
			//4. parameter Mapping

			// 4. SQL 실행
			
			int count = stmt.executeUpdate();
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
	}

}
