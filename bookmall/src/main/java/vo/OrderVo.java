package vo;

public class OrderVo {
	private Long no;
	private Long order_no;
	private String orderer;
	private Long payment_price;
	private String destination;
	private Long member_no;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getOrder_no() {
		return order_no;
	}
	public void setOrder_no(Long order_no) {
		this.order_no = order_no;
	}
	public String getOrderer() {
		return orderer;
	}
	public void setOrderer(String orderer) {
		this.orderer = orderer;
	}
	public Long getPayment_price() {
		return payment_price;
	}
	public void setPayment_price(Long payment_price) {
		this.payment_price = payment_price;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Long getMember_no() {
		return member_no;
	}
	public void setMember_no(Long member_no) {
		this.member_no = member_no;
	}
	@Override
	public String toString() {
		return "OrderVo [no=" + no + ", order_no=" + order_no + ", orderer=" + orderer + ", payment_price="
				+ payment_price + ", destination=" + destination + ", member_no=" + member_no + "]";
	}
}
