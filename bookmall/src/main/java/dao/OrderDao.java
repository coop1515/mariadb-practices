package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.MemberVo;
import vo.OrderBookVo;
import vo.OrderVo;

public class OrderDao {
	//orderbook 여기 만들면됨.
public boolean insert(OrderVo vo) {
	boolean result = false;
	Connection connection = null;
	PreparedStatement pstmt = null;
	try {
		connection = getConnection();

		// 3. SQL 준비
		String sql = "insert into `order` values (null, ?, ?, ?, ?, ?)";
		pstmt = connection.prepareStatement(sql); // SQL을 실행할 수 있는 객체

		// 4. Mapping(bind)
		pstmt.setLong(1, vo.getOrder_no());
		pstmt.setString(2, vo.getOrderer());
		pstmt.setLong(3, vo.getPayment_price());
		pstmt.setString(4, vo.getDestination());
		pstmt.setLong(5, vo.getMember_no());

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

public boolean insertBook(OrderBookVo vo) {
	boolean result = false;
	Connection connection = null;
	PreparedStatement pstmt = null;
	try {
		connection = getConnection();

		// 3. SQL 준비
		String sql = "insert into orderbook values (null, ?, ?, ?, ?)";
		pstmt = connection.prepareStatement(sql); // SQL을 실행할 수 있는 객체

		// 4. Mapping(bind)
		pstmt.setString(1, vo.getTitle());
		pstmt.setLong(2, vo.getAmount());
		pstmt.setLong(3, vo.getOrderer_no());
		pstmt.setLong(4, vo.getBook_no());

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

public List<OrderVo> findSelect() {
	List<OrderVo> result = new ArrayList<>();
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	try {
		connection = getConnection();

		// 3. SQL 준비
		String sql = "select order_no, orderer, payment_price, destination, no"
				+ " from `order`";
		pstmt = connection.prepareStatement(sql); // SQL을 실행할 수 있는 객체

		// 4. parameter mapping

		// 5. SQL 실행

		rs = pstmt.executeQuery();

		// 6. 결과처리
		while (rs.next()) {
			
			Long order_no = rs.getLong(1);
			String orderer = rs.getString(2);
			Long pay_price = rs.getLong(3);
			String destination = rs.getString(4);
			Long no = rs.getLong(5);
			OrderVo vo = new OrderVo();
			vo.setOrder_no(order_no);
			vo.setOrderer(orderer);
			vo.setPayment_price(pay_price);
			vo.setDestination(destination);
			vo.setNo(no);
			
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
