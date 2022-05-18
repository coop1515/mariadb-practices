package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SelectTest02 {

	public static void main(String[] args) {
		List<DepartmentVo> result = findAll();
		for(DepartmentVo vo : result) {
			System.out.println(vo);
		}
	}

	private static List<DepartmentVo> findAll() {
		
		List<DepartmentVo> result = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 1. JDBC Driver loading (= JDBC CLASS loading : class loader)
			Class.forName("org.mariadb.jdbc.Driver");
			
			// 2. connection
			String url = "jdbc:mysql://192.168.10.45:3306/webdb?charset=utf8";
			connection = DriverManager.getConnection(url, "webdb","webdb");
			
			// 3. SQL 준비
			String sql = "select no, name from department order by no desc";
			pstmt = connection.prepareStatement(sql); //SQL을 실행할 수 있는 객체
			
			// 4. parameter mapping
			
			// 5. SQL 실행
			
			rs = pstmt.executeQuery();
			
			//6. 결과처리
			while(rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				
				DepartmentVo vo = new DepartmentVo();
				vo.setNo(no);
				vo.setName(name);
				
				result.add(vo);
			}
		} catch (ClassNotFoundException e) {
				System.out.println("fail Driver loading" + e);
		} catch (SQLException e) {
			System.out.println("뭐함");
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
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