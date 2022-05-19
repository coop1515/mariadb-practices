package vo;

public class BookVo {
	private Long no;
	private String title;
	private Long price;
	private Long Category_no;
	private String Category_name;
	public String getCategory_name() {
		return Category_name;
	}
	public void setCategory_name(String category_name) {
		Category_name = category_name;
	}
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
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Long getCategory_no() {
		return Category_no;
	}
	public void setCategory_no(Long category_no) {
		Category_no = category_no;
	}
	@Override
	public String toString() {
		return "BookVo [no=" + no + ", title=" + title + ", price=" + price + ", Category_no=" + Category_no
				+ ", Category_name=" + Category_name + "]";
	}
}
