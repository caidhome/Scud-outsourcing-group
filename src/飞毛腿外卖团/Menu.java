package 飞毛腿外卖团;
//菜单类
public class Menu {

	private String foodName;
	private int price;
	private String effic;
	private String id;
	private int hotel;
	
	public Menu(String foodName, int price, String effic, String id, int hotel) {
		super();
		this.foodName = foodName;
		this.price = price;
		this.effic = effic;
		this.id = id;
		this.hotel = hotel;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getEffic() {
		return effic;
	}
	public void setEffic(String effic) {
		this.effic = effic;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getHotel() {
		return hotel;
	}
	public void setHotel(int hotel) {
		this.hotel = hotel;
	}
	
	
	

}
