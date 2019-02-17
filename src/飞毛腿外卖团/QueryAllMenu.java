package 飞毛腿外卖团;
import java.sql.*;
import java.util.*;
public class QueryAllMenu {
//获取数据库中菜单表中的所有菜单的功能；
	public ArrayList<Menu> getAllMenu(String storeName)
	{
		ArrayList<Menu> al = new ArrayList<Menu>();
		GetConnection getCon = new GetConnection();
		Statement st = getCon.getStatement();
		String sql = "select * from 菜单表,饭店表 where 饭店  = 菜单ID";
		ResultSet rs = null;
		try {
			rs = st.executeQuery(sql);
			
			while(rs.next()){
				if(storeName.equals(rs.getString("饭店名"))){
						String name = rs.getString("食物名称");
						int price = rs.getInt("价格");
						String effic  = rs.getString("出餐速率");
						String id = rs.getString("ID");
						int hotel = rs.getInt("饭店");
						Menu m = new Menu(name,price,effic,id,hotel);
						al.add(m);
					}
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getCon.releaseAll(rs);
		return al;
		
	}
	

}
