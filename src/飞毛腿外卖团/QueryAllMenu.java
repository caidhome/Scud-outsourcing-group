package ��ë��������;
import java.sql.*;
import java.util.*;
public class QueryAllMenu {
//��ȡ���ݿ��в˵����е����в˵��Ĺ��ܣ�
	public ArrayList<Menu> getAllMenu(String storeName)
	{
		ArrayList<Menu> al = new ArrayList<Menu>();
		GetConnection getCon = new GetConnection();
		Statement st = getCon.getStatement();
		String sql = "select * from �˵���,����� where ����  = �˵�ID";
		ResultSet rs = null;
		try {
			rs = st.executeQuery(sql);
			
			while(rs.next()){
				if(storeName.equals(rs.getString("������"))){
						String name = rs.getString("ʳ������");
						int price = rs.getInt("�۸�");
						String effic  = rs.getString("��������");
						String id = rs.getString("ID");
						int hotel = rs.getInt("����");
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
