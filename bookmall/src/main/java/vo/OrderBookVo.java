package vo;

public class OrderBookVo {
	private Long no;
	private String title;
	private Long amount;
	private Long orderer_no;
	private Long book_no;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public Long getOrderer_no() {
		return orderer_no;
	}
	public void setOrderer_no(Long orderer_no) {
		this.orderer_no = orderer_no;
	}
	public Long getBook_no() {
		return book_no;
	}
	public void setBook_no(Long book_no) {
		this.book_no = book_no;
	}
	@Override
	public String toString() {
		return "OrderBookVo [no=" + no + ", title=" + title + ", amount=" + amount + ", orderer_no=" + orderer_no
				+ ", book_no=" + book_no + "]";
	}
}
