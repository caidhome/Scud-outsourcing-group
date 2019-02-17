package 飞毛腿外卖团;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.JTableHeader;

//查询订单界面
public class QueryOrder {
	
	long userId;
	JFrame f;
	JButton but;
	JTable jt;
	JLabel jl;
	JPanel jp1,jp2,jp3;
	public QueryOrder(long userId)
	{
		this.userId = userId;
		init();
	}
	public void init()
	{
		f = new JFrame("订单查询");
		f.setBounds(380, 180, 730, 370);
		f.setResizable(false);
		
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		f.setResizable(false);
		
		but = new JButton("确定");
		jl = new JLabel("<html><font size = '14'> 订 单 明 细<font> <br><br></html>");
		Object th[] = new Object[]{
			"订单号","菜名","价格","数量","总额","订单人姓名","联系电话","送餐地址","备注"
		};
		
		GetConnection getcon = new GetConnection();
		Statement st = getcon.getStatement();
		ResultSet rs = null;
		ArrayList<Order> al = getList(userId);
		Object td[][] = new Object[al.size()][9];
		for(int i = 0;i < al.size();i++)
		{
			Order o = al.get(i);
			td[i][0] = o.getOrderId();
			String menuId = o.getMenuId();
			String sql = "select * from 菜单表";
			try {
				rs = st.executeQuery(sql);
				while(rs.next())
				{
					if(menuId.equals(rs.getString("ID")))
						td[i][1] = rs.getString("食物名称");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			td[i][2] = o.getPrice();
			td[i][3] = o.getAmount();
			td[i][4] = o.getSum();
			td[i][5] = o.getDdName();
			td[i][6] = o.getDdTel();
			td[i][7] = o.getDdAddress();
			td[i][8] = o.getRemark();
		}
		getcon.releaseAll(rs);
		jt = new JTable(td,th){
            public boolean isCellEditable(int row, int column)
            {
                       return false;
            }
		};
		

		jt.getColumnModel().getColumn(2).setPreferredWidth(50);
		jt.getColumnModel().getColumn(3).setPreferredWidth(50);
	    jt.getColumnModel().getColumn(4).setPreferredWidth(50);
	    jt.getColumnModel().getColumn(7).setPreferredWidth(120);
	    jt.getColumnModel().getColumn(6).setPreferredWidth(120);

		
		JTableHeader head = jt.getTableHeader();
		
		jt.setRowHeight(20);
		jt.setFont(new Font("黑体",Font.BOLD,16));
		jp2.add(new JScrollPane(jt));
		
		jp2.add(head,BorderLayout.NORTH);
		jp2.add(jt,BorderLayout.SOUTH);
		
		jp1.add(jl);
		jp3.add(but);
		f.add(jp1,BorderLayout.NORTH);
		f.add(jp2,BorderLayout.CENTER);
		f.add(jp3,BorderLayout.SOUTH);
		f.setVisible(true);
		myEvent();
	}
	
	public void myEvent()
	{
		but.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				f.setVisible(false);
			}
		});
	}
	
	public ArrayList<Order> getList(long userId)
	{
		ArrayList<Order> al = new ArrayList<Order>();
		GetConnection getcon = new GetConnection();
		Statement st = getcon.getStatement();
		String sql = "select * from 订单表 where 用户ID =" + userId;
		ResultSet rs = null;
		try {
			rs = st.executeQuery(sql);
			while(rs.next())
			{
				long userID = rs.getLong("用户ID");
				long orderId = rs.getLong("订单号");
				int price = rs.getInt("价格");
				int count = rs.getInt("数量");
				int sum = rs.getInt("总额");
				String userName = rs.getString("订单人姓名");
				long tel = rs.getLong("送餐电话");
				String add = rs.getString("送餐地址");
				String time = rs.getString("下单时间");
				String remark = rs.getString("备注");
				String menuId = rs.getString("菜单ID");
				Order o = new Order(userID,orderId,price,count,sum,userName,tel,add,time,remark,menuId);
				al.add(o);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getcon.releaseAll(rs);
		return al;
		
	}
	public JFrame getFrame(){
		return f;
	}

	public static void main(String args[])
	{
		new QueryOrder(201602);
	}
}
