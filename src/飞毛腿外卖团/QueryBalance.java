package 飞毛腿外卖团;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

//查询个人信息
public class QueryBalance {
	
	private JFrame f;
	private JPanel jp1,jp2,jp3,p1,p2,p3,p4,p5,p6;
	private JLabel tittle,user,key,Id,bala,add,tel;
	private JTextField tfuser,tfkey,tfId,tfbala,tfadd,tftel;
	private JButton update,recharge;
	private long userId;
	
	
	public QueryBalance(long userId)
	{
		this.userId = userId;
		init();
	}
	public void init()
	{
		//窗体设置
		f = new JFrame("个人信息");
		f.setBounds(550,130,280,410);
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		
		tittle = new JLabel("个人信息");
		tittle.setFont(new Font("宋体",Font.BOLD,30));
		jp1.add(tittle);
		
		Object[] info = getInfo(userId);
		
		user = new JLabel("姓名：");
		tfuser = new JTextField(10);
		tfuser.setText("  " + info[0]);
		//将文本框设置为不可编辑
		tfuser.setEditable(false);
		key = new JLabel("密码：");
		tfkey = new JTextField(10);
		tfkey.setText("  " + info[1]);
		tfkey.setEditable(false);
		Id = new JLabel("账号：");
		tfId = new JTextField(10);
		tfId.setText("  " + info[2]);
		tfId.setEditable(false);
		bala = new JLabel("余额：");
		tfbala = new JTextField(10);
		tfbala.setText("  " + info[3]);
		tfbala.setEditable(false);
		add = new JLabel("地址：");
		tfadd = new JTextField(10);
		tfadd.setText("  " + info[4]);
		tfadd.setEditable(false);
		tel = new JLabel("电话：");
		tftel = new JTextField(10);
		tftel.setText("  " + info[5]);
		tftel.setEditable(false);
		
		//创建面板对象
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		p4 = new JPanel();
		p5 = new JPanel();
		p6 = new JPanel();
		
		p1.add(user);
		p1.add(tfuser);
		p2.add(key);
		p2.add(tfkey);
		p3.add(Id);
		p3.add(tfId);
		p4.add(bala);
		p4.add(tfbala);
		p5.add(add);
		p5.add(tfadd);
		p6.add(tel);
		p6.add(tftel);
		
		jp2.add(p1);
		jp2.add(p2);
		jp2.add(p3);
		jp2.add(p4);
		jp2.add(p6);
		jp2.add(p5);
		jp2.setLayout(new GridLayout(6,1));
		
		update = new JButton("返回");
		jp3.add(update);
		recharge = new JButton("充值余额");
		jp3.add(recharge);
		
		f.add(jp1,BorderLayout.NORTH);
		f.add(jp2,BorderLayout.CENTER);
		f.add(jp3,BorderLayout.SOUTH);
		f.setResizable(false);
		myEvent();
		f.setVisible(true);
	}
	public void myEvent()
	{
		//监听充值按钮
		recharge.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				new Recharge(userId);
			}
		});
		//返回按钮的监听
		update.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				f.setVisible(false);
			}
		});
		
	}
	//获取userId对应的信息
	public Object[] getInfo(long userId)
	{
		GetConnection getcon = new GetConnection();
		Statement st = getcon.getStatement();
		Object info[] = new Object[6];
		ResultSet rs = null;
		String sql = "select * from 用户表 where 账号 ="+userId;
		try {
			rs = st.executeQuery(sql);
			if(rs.next())
			{
				info[0] = rs.getString("用户名");
				info[1] = rs.getLong("密码");
				info[2] = rs.getLong("账号");
				info[3] = rs.getInt("余额");
				info[4] = rs.getString("默认地址");
				info[5] = rs.getLong("联系电话");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getcon.releaseAll(rs);
		return info;
		
	}
	
	
//	public static void main(String args[])
//	{
//		new QueryBalance(201610);
//	}
}
