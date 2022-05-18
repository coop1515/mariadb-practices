package bookshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bookshop.vo.AuthorVo;

public class AuthorDao {

	public boolean insert(AuthorVo vo) {
		boolean result= false;
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			// 1. JDBC Driver loading (= JDBC CLASS loading : class loader)
			Class.forName("org.mariadb.jdbc.Driver");
			
			// 2. connection
			String url = "jdbc:mysql://192.168.10.45:3306/webdb?charset=utf8";
			connection = DriverManager.getConnection(url, "webdb","webdb");
			
			// 3. SQL 준비
			String sql = "insert into author values (null, ?)";
			pstmt = connection.prepareStatement(sql); //SQL을 실행할 수 있는 객체
			
			// 4. Mapping(bind)
			pstmt.setString(1, vo.getName());
			
			// 4. SQL 실행
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
}
