package dao.test;

import java.util.List;

import dao.OrderDao;
import vo.OrderBookVo;
import vo.OrderVo;

public class OrderDaoTest {

	public static void main(String[] args) {
		testInsert();
//		findSelect();
	}

	private static void findSelect() {
		List<OrderVo> list = new OrderDao().findSelect();
		for (OrderVo vo : list) {
			System.out.println(vo);
		}

	}

	public static void testInsert() {
		OrderDao dao = new OrderDao();
		OrderVo vo = new OrderVo();

		vo.setOrder_no(20220519L);
		vo.setOrderer("둘리(dooly@email.com)");
		vo.setPayment_price(3000L);
		vo.setDestination("우리집");
		vo.setMember_no(1L);
		dao.insert(vo);

	}

	public static void testInsertBook() {
		OrderDao dao = new OrderDao();
		OrderBookVo vo = new OrderBookVo();
		vo.setTitle("이것이 MariaDB다");
		vo.setAmount(2L);
		vo.setOrderer_no(1L);
		vo.setBook_no(1L);
		dao.insertBook(vo);

		vo.setTitle("코스모스");
		vo.setAmount(1L);
		vo.setOrderer_no(2L);
		vo.setBook_no(3L);
		dao.insertBook(vo);
	}
}
