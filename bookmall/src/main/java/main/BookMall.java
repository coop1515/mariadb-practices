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
		
		findMember();
		findCategory();
		findBook();
		findCart();
		findOrder();
		findCart2();
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
			System.out.println(vo.getOrder_no() + "-000" + vo.getNo() + "\n" + vo.getOrderer() + ")\n"
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
