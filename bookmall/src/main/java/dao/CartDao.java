package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.BookVo;
import vo.CartVo;

public class CartDao {
	public boolean insert(CartVo vo) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = getConnection();

			// 3. SQL 준비
			String sql = "insert into cart values (null, ?, ?, ?, ?, ?)";
			pstmt = connection.prepareStatement(sql); // SQL을 실행할 수 있는 객체

			// 4. Mapping(bind)
			pstmt.setString(1, vo.getTitle());
			pstmt.setLong(2, vo.getPrice());
			pstmt.setLong(3, vo.getAmount());
			pstmt.setLong(4, vo.getMember_no());
			pstmt.setLong(5, vo.getBook_no());
			

			// 4. SQL 실행
			int count = pstmt.executeUpdate();
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("뭐함");
		} finally {
			try {
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
	
	public List<CartVo> findSelect() {
		List<CartVo> result = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = getConnection();

			// 3. SQL 준비
			String sql = "select c.name '이름', a.title '책이름', b.amount '수량'"
					+ " from book a ,cart b, member c"
					+ " where a.no = b.book_no"
					+ "	and c.no = b.member_no";
			pstmt = connection.prepareStatement(sql); // SQL을 실행할 수 있는 객체

			// 4. parameter mapping

			// 5. SQL 실행

			rs = pstmt.executeQuery();

			// 6. 결과처리
			while (rs.next()) {
				
				String name = rs.getString(1);
				String title = rs.getString(2);
				Long amount = rs.getLong(3);

				CartVo vo = new CartVo();
				vo.setName(name);
				vo.setTitle(title);
				vo.setAmount(amount);
				
				result.add(vo);
			}
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
	
	public List<CartVo> findSelectAll() {
		List<CartVo> result = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = getConnection();

			// 3. SQL 준비
			String sql = "select a.no, b.title, b.amount, b.price * b.amount"
					+ " from book a ,cart b, member c"
					+ " where a.no = b.book_no"
					+ "	and c.no = b.member_no";
			pstmt = connection.prepareStatement(sql); // SQL을 실행할 수 있는 객체

			// 4. parameter mapping

			// 5. SQL 실행

			rs = pstmt.executeQuery();

			// 6. 결과처리
			while (rs.next()) {
				
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				Long amount = rs.getLong(3);
				Long price = rs.getLong(4);
				CartVo vo = new CartVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setAmount(amount);
				vo.setPrice(price);
				result.add(vo);
			}
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
	
	
	
	
	
	
	
	
	
	
	private Connection getConnection() throws SQLException {
		Connection connection = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mysql://192.168.10.45:3306/bookmall?charset=utf8";
			connection = DriverManager.getConnection(url, "bookmall", "bookmall");

		} catch (ClassNotFoundException e) {
			System.out.println("Fail Driver loading");
		}

		return connection;
	}
}
