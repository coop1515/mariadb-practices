package bookshop;

import java.util.List;
import java.util.Scanner;

import bookshop.dao.BookDao;
import bookshop.example.Book;
import bookshop.vo.BookVo;

public class Main {

	public static void main(String[] args) {
		displayBookInfo();
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("대여 하고 싶은 책의 번호를 입력하세요:");
		int num = scanner.nextInt();
		scanner.close();


//		for (int i = 0; i < books.length; i++) {
//			if (books[i].getBookNo() == num) {
//				books[i].setStateCode(0);
//				books[i].rent();
//
//			}
//		}

	
		System.out.println("*****도서 정보 출력하기******");
		displayBookInfo();
	}

	public static void displayBookInfo() {
		List<BookVo> list = new BookDao().findAll();
		for(BookVo vo : list) {
			System.out.println("["+vo.getNo()+"]책 제목 : "+vo.getTitle()+ ", 작가 : "+vo.getAuthorName()+","
					+ " 대여 유무 " + vo.getStateCode());
		}
	}

}
