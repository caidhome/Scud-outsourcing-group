package 飞毛腿外卖团;
//用户类
public class User {
	 private long Id;
	 private long password;
	 private String userName;
	 private int bala;
	 private String add;
	 private long tel;
	public User(long id, long password, String userName, int bala,String add, long tel) {
		super();
		Id = id;
		this.password = password;
		this.userName = userName;
		this.bala = bala;
		this.add = add;
		this.tel = tel;
	}
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public long getPassword() {
		return password;
	}
	public void setPassword(long password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getBala() {
		return bala;
	}
	public void setBala(int bala) {
		this.bala = bala;
	}
	public String getAdd() {
		return add;
	}
	public void setAdd(String add) {
		this.add = add;
	}
	public long getTel() {
		return tel;
	}
	public void setTel(long tel) {
		this.tel = tel;
	}
	 
	 
	 
	 
}
