package 飞毛腿外卖团;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

//注册新用户
public class regist {
	
	private JFrame f;
	private JPanel jp1,jp2,jp3,p1,p2,p4,p5;
	private JLabel tittle,user,key,bala,add,tip;
	private JTextField tfuser,tfkey,tfbala,tfadd;
	private JButton regist,cancel,ok;
	private JDialog jd;
	private User u;
	
	public regist()
	{
		init();
	}
	public void init()
	{
		f = new JFrame("用户注册");
		f.setBounds(550,130,280,410);
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		
		tittle = new JLabel("用户注册");
		tittle.setFont(new Font("宋体",Font.BOLD,30));
		jp1.add(tittle);
		
		
		user = new JLabel("姓名：");
		tfuser = new JTextField(10);
		key = new JLabel("密码：");
		tfkey = new JTextField(10);
		bala = new JLabel("电话：");
		tfbala = new JTextField(10);
		add = new JLabel("地址：");
		tfadd = new JTextField(10);
		
		p1 = new JPanel();
		p2 = new JPanel();
		p4 = new JPanel();
		p5 = new JPanel();
		
		p1.add(user);
		p1.add(tfuser);
		p2.add(key);
		p2.add(tfkey);
		p4.add(bala);
		p4.add(tfbala);
		p5.add(add);
		p5.add(tfadd);
		
		jp2.add(p1);
		jp2.add(p2);
		jp2.add(p4);
		jp2.add(p5);
		jp2.setLayout(new GridLayout(4,1));
		
		regist = new JButton("注册");
		jp3.add(regist);
		cancel = new JButton("取消");
		jp3.add(cancel);
		
		jd = new JDialog(f,"提示",true);
		tip = new JLabel();
		ok = new JButton("确定");
		jd.add(tip);
		jd.add(ok);
		jd.setLayout(new FlowLayout());
		
		f.add(jp1,BorderLayout.NORTH);
		f.add(jp2,BorderLayout.CENTER);
		f.add(jp3,BorderLayout.SOUTH);
		myEvent();
		f.setVisible(true);
	}
	
	public void myEvent()
	{
		regist.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				String userName = tfuser.getText();
				String key = tfkey.getText();
				String tel = tfbala.getText();
				String add = tfadd.getText();
				long userId = getId();
				if(isNumber(key) && isNumber(tel) && tel.length() == 11
						&& userName != null && add != null)
				{
					long password = Long.parseLong(key);
					long usertel = Long.parseLong(tel);
					u = new User(userId,password,userName,0,add,usertel);
				}
				if(addUser(u))
				{
					jd.setBounds(550, 250, 280, 260);
					tip.setText("<html><br><font size = '16'>　恭　喜　你　</font><br><br>　注　册　成　功　，　"
							+ "请　记　住<br>　您　的　登　录　账　号：<br><font size = '12'>　　"+userId+"</font>");
					jd.setVisible(true);
				}else{
					jd.setBounds(550, 250, 260, 190);
					tip.setText("<html><br>　对不起，您的输入有误，请重新输入！<br><br><font size = '6'>　"
							+ "注　册　失　败　</font><br><br></html>");
					jd.setVisible(true);
				}
			}
		});
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				jd.setVisible(false);
				tfuser.setText("");
				tfkey.setText("");
				tfbala.setText("");
				tfadd.setText("");
			}
		});
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				f.setVisible(false);
			}
		});
		
		
	}
	
	public boolean addUser(User u)
	{
		if(u == null)
		{
			return false;
		}
		GetConnection getcon = new GetConnection();
		Statement st = getcon.getStatement();
		int rs = 0;
		String sql = "insert into 用户表 values("+u.getId()+","+u.getPassword()+",'" + 
		u.getUserName() + "'," + u.getBala() +",'" + u.getAdd()+"'," + u.getTel()+")";;
		try {
			rs = st.executeUpdate(sql);
			if(rs >= 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			getcon.release();
		}
		return false;
		
	}
	public boolean isNumber(String str)
	{
		if(str.length()<=0)
			return false;
		for(int i = 0;i < str.length();i++)
		{
			char c = str.charAt(i);
			if(!Character.isDigit(c))
				return false;
		}
		
		return true;
	}
	
	public long getId()
	{
		GetConnection getcon = new GetConnection();
		Statement st = getcon.getStatement();
		ResultSet rs = null;
		String sql = "select max(账号) from 用户表 ";
		try {
			rs = st.executeQuery(sql);
			if(rs.next())
			{
				return rs.getLong(1)+1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			getcon.releaseAll(rs);
		}
		return 0;
	}
	
	
	public static void main(String args[])
	{
		new regist();
	}
}
