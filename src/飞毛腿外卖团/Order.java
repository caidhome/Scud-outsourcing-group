package 飞毛腿外卖团;
//订单类
public class Order {
	
	private long userId;
	private long orderId;
	private int price;
	private int amount;
	private int sum;
	private String ddName;
	private long ddTel;
	private String ddAddress;
	private String ddTime;
	private String remark;
	private String menuId;
	public Order(long userId, long orderId, int price, int amount, int sum,
			String ddName, long ddTel, String ddAddress, String ddTime,
			String remark, String menuId) {
		super();
		this.userId = userId;
		this.orderId = orderId;
		this.price = price;
		this.amount = amount;
		this.sum = sum;
		this.ddName = ddName;
		this.ddTel = ddTel;
		this.ddAddress = ddAddress;
		this.ddTime = ddTime;
		this.remark = remark;
		this.menuId = menuId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public String getDdName() {
		return ddName;
	}
	public void setDdName(String ddName) {
		this.ddName = ddName;
	}
	public long getDdTel() {
		return ddTel;
	}
	public void setDdTel(long ddTel) {
		this.ddTel = ddTel;
	}
	public String getDdAddress() {
		return ddAddress;
	}
	public void setDdAddress(String ddAddress) {
		this.ddAddress = ddAddress;
	}
	public String getDdTime() {
		return ddTime;
	}
	public void setDdTime(String ddTime) {
		this.ddTime = ddTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
	

}
