package 飞毛腿外卖团;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
//余额充值
public class Recharge {

	private JFrame f;
	private JLabel tip,bala,recharge;
	private JButton ok,cancel;
	private JPanel jp1,jp2,jp3;
	private JTextField tfbala,tfrecharge;
	private long userId;
	private int ba,add;
	
	public Recharge(long userId)
	{
		this.userId = userId;
		init();
	}
	public void init()
	{
		f = new JFrame("充值");
		f.setBounds(550,250,220,250);
		f.setResizable(false);
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		
		tip = new JLabel();
		tip.setText("<html><font size = '12'> 充  值 </font><br><br></html>");
		jp1.add(tip);
		
		bala = new JLabel("账户余额：");
		tfbala = new JTextField(10);
		tfbala.setEditable(false);
		ba = getBala(userId);
		tfbala.setText("  " + ba);
		recharge = new JLabel("充值金额：");
		tfrecharge = new JTextField(10);
		jp2.add(bala);
		jp2.add(tfbala);
		jp2.add(recharge);
		jp2.add(tfrecharge);
		
		ok = new JButton("确认");
		cancel = new JButton("取消");
		jp3.add(ok);
		jp3.add(cancel);
		
		f.add(jp1,BorderLayout.NORTH);
		f.add(jp2,BorderLayout.CENTER);
		f.add(jp3,BorderLayout.SOUTH);
		myEvent();
		f.setVisible(true);
	}
	public void myEvent()
	{
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				String input = tfrecharge.getText();
				if(isNumber(input))
				{
					add = Integer.parseInt(input);
					int value = addcharge(ba+add,userId);
					if(value > 0)
					{
						JOptionPane.showMessageDialog(null, "充 值 成 功 ！", "提示", JOptionPane.INFORMATION_MESSAGE);
						ba = getBala(userId);
						tfbala.setText("  " + ba);
						tfrecharge.setText("");
					}else{
						JOptionPane.showMessageDialog(null, "充 值 失 败 ！", "提示", JOptionPane.INFORMATION_MESSAGE);
						ba = getBala(userId);
						tfbala.setText("  " + ba);
						tfrecharge.setText("");
						
					}
				}else{
					JOptionPane.showMessageDialog(null, "您 的 输 入 有 误 ！", "警告", JOptionPane.WARNING_MESSAGE);
					tfrecharge.setText("");
				}
			}
		});
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				f.setVisible(false);
			}
		});
	}
	
	public boolean isNumber(String str)
	{
		if(str.length()<=0)
			return false;
		for(int i = 0;i < str.length();i++)
		{
			if(!Character.isDigit(str.charAt(i)))
				return false;
		}
		return true;
	}
	
	public int getBala(long userId)
	{
		GetConnection getcon = new GetConnection();
		Statement st = getcon.getStatement();
		ResultSet rs = null;
		String sql = "select 余额 from 用户表 where 账号= "+userId;
		try {
			rs = st.executeQuery(sql);
			if(rs.next())
			{
					return rs.getInt("余额");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getcon.releaseAll(rs);
		return 0;
	}
	
	public int addcharge(int ba,long Id)
	{
		GetConnection getcon = new GetConnection();
		Statement st = getcon.getStatement();
		int rs = 0;
		String sql = "update 用户表 set 余额 = " + ba + " where 账号= "+Id;
		try {
			rs = st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getcon.release();
		return rs;
	}
	
	public static void main(String[] args) {
		new Recharge(201610);
	}

}
