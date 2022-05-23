package main;

import java.util.List;

import dao.BookDao;
import dao.CartDao;
import dao.CategoryDao;
import dao.MemberDao;
import dao.OrderDao;
import vo.BookVo;
import vo.CartVo;
import vo.CategoryVo;
import vo.MemberVo;
import vo.OrderVo;

public class BookMall {

	public static void main(String[] args) {
		
//		insertCategory();
//		insertMember();
//		insertOrder();
//		insertBook();
//		insertCart();
		
		findMember();
		findCategory();
		findBook();
		findCart();
		findOrder();
		findCart2();
	}
	private static void insertOrder() {
		new OrderDao().insert(20220519L, "둘리(dooly@email.com)", 3000L, "우리집", 1L);
		
	}
	private static void insertMember() {
		new MemberDao().insert("둘리", "010-1111-1111", "dooly@gmail.com", "1234");
		new MemberDao().insert("마이콜", "010-2222-2222", "michol@gmail.com", "1234");
		
	}
	private static void insertCategory() {
		new CategoryDao().insert("IT");
		new CategoryDao().insert("과학");
		new CategoryDao().insert("문학");
		
	}
	private static void insertBook() {
		new BookDao().insert("이것이 MariaDB다", 1000L, 1L);
		new BookDao().insert("러닝 ReactJS", 2000L, 1L);
		new BookDao().insert("코스모스", 1000L, 2L);
	}
	private static void insertCart() {
		new CartDao().insert("이것이 MariaDB다", 1000L, 2L, 1L, 1L);
		new CartDao().insert("코스모스", 1000L, 1L, 1L, 3L);
	}
	
	private static void findMember() {
		List<MemberVo> list = new MemberDao().findSelect();
		for (MemberVo vo : list) {
			System.out.println(vo.getName() + ", " + vo.getEmail());
		}
		System.out.println("=========================");
	}

	private static void findCategory() {
		List<CategoryVo> list = new CategoryDao().findSelect();
		for (CategoryVo vo : list) {
			System.out.println(vo.getNo() + "." + vo.getCategoryName());
		}
		System.out.println("=========================");
	}

	private static void findBook() {
		List<BookVo> list = new BookDao().findSelect();
		for (BookVo vo : list) {
			System.out.println(vo.getNo() + " " + vo.getTitle() + ", " + vo.getPrice() + ", " + vo.getCategory_name());
		}
		System.out.println("=========================");

	}

	private static void findCart() {
		List<CartVo> list = new CartDao().findSelect();
		for (CartVo vo : list) {
			System.out.println(vo.getName() + " " + vo.getTitle() + " " + vo.getAmount());
		}
		System.out.println("=========================");
	}

	private static void findOrder() {
		List<OrderVo> list = new OrderDao().findSelect();
		for (OrderVo vo : list) {
			System.out.println(vo.getOrder_no() + "-000" + vo.getNo() + "\n" + vo.getOrderer() + "\n"
					+ vo.getPayment_price() + "\n" + vo.getDestination());
		}
		System.out.println("=========================");

	}

	private static void findCart2() {
		List<CartVo> list = new CartDao().findSelectAll();
		for (CartVo vo : list) {
			System.out.println(vo.getNo() + " " + vo.getTitle() + " " + vo.getAmount() + " " + vo.getPrice());
		}
		System.out.println("=========================");

	}
}
