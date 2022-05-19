package bookshop.example;

public class Book {
	private int bookNo;
	private String title;
	private String author;
	private int stateCode =1 ;// 이 값이 0이면 대여중, 1이면 재고 있음.
	
	public int getBookNo() {
		return bookNo;
	}
	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getStateCode() {
		return stateCode;
	}
	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}
	
	public Book(int bookNo, String title, String author) {
		this.bookNo = bookNo;
		this.title = title;
		this.author = author;
	}
	public void print() {
		if (stateCode == 1) {
			System.out.println("["+bookNo+"]책 제목 : "+title+ ", 작가 : "+author+", 대여 유무 : 재고있음.");
		}
		
		else {
			System.out.println("["+bookNo+"]책 제목 : "+title+ ", 작가 : "+author+", 대여 유무 : 대여중.");
		}
		
	}
	
	public void rent() {
		
		if(stateCode == 0) {
			System.out.println(title+ "이 대여 됐습니다.");
		}
	}

}
