package ��ë��������;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
//ѡ��ʳ����༰�÷�������е꣬��ѡ����Ҫ����ʳ�������ĵ�
public class Take_out {
	private JFrame f;
	private JLabel labselect;
	private JButton butOkselect;
	private JTextField tfselect;
	private JPanel jp,jp0,jps,jp1,jp2,jp3,jba,jbb,jbc,jbd,jbe,jbf,jbg;
	private JComboBox<String> cb1,cb2,cb3,cb4,cb5,cb6,cb7;
	private long userId;
	public Take_out(long userId){ 
		this.userId = userId;
		init();
	}
	
	public void init()
	{
		f = new JFrame("���̷���");
		f.setBounds(400,160,550,380);
		f.setResizable(false);
		
		Font font = new Font("����",Font.BOLD,16);
		labselect = new JLabel("<html><br>��ѡ������Ҫ����������<br><br></html>");
		labselect.setFont(font);
		tfselect = new JTextField(10);
		tfselect.setFont(font);	
		tfselect.setEditable(false);
		butOkselect = new JButton("�������");
		jp = new JPanel();
		
		jp.add(labselect);
		f.add(jp,BorderLayout.NORTH);
		
		jp0 = new JPanel();
		jp1 = new JPanel();
		jp3 = new JPanel();
		jp2 = new JPanel();
		jba = new JPanel();
		jbb = new JPanel();
		jbc = new JPanel();
		jbd = new JPanel();
		jbe = new JPanel();
		jbf = new JPanel();
		jbg = new JPanel();
		
		jp0.add(jp1,BorderLayout.NORTH);
		jp0.add(jp2,BorderLayout.CENTER);
		jp0.add(jp3,BorderLayout.SOUTH);
		
		
		cb1 = new JComboBox<String>();
		cb2 = new JComboBox<String>();
		cb3 = new JComboBox<String>();
		cb4 = new JComboBox<String>();
		cb5 = new JComboBox<String>();
		cb6 = new JComboBox<String>();
		cb7 = new JComboBox<String>();
		cb2.addItem(" �� �� ��");
		String[] c2 = getItem("�տ�");
		for(String s : c2)
			if(s != null)
				cb2.addItem(s);
			else
				continue;
		cb3.addItem(" �� �� ��");
		String[] c3 = getItem("�ǽ�");
		for(String s : c3)
			if(s != null)
				cb3.addItem(s);
			else
				continue;
		cb4.addItem(" ˮ �� ��");
		String[] c4 = getItem("ˮ��");
		for(String s : c4)
			if(s != null)
				cb4.addItem(s);
			else
				continue;
		cb5.addItem(" �� ʳ ��");
		String[] c5 = getItem("��ʳ");
		for(String s : c5)
			if(s != null)
				cb5.addItem(s);
			else
				continue;
		cb6.addItem(" �� �� ��");
		String[] c6 = getItem("���");
		for(String s : c6)
			if(s != null)
				cb6.addItem(s);
			else
				continue;
		cb7.addItem(" �� Ʒ ��");
		String[] c7 = getItem("��Ʒ");
		for(String s : c7)
			if(s != null)
				cb7.addItem(s);
			else
				continue;
		cb1.addItem(" �� ʳ ��");
		String[] c1 = getItem("��ʳ");
		for(String s : c1)
			if(s != null)
				cb1.addItem(s);
			else
				continue;
		
		
		jba.add(cb1);
		jbb.add(cb2);
		jbc.add(cb3);
		
		jp1.add(jba);
		jp1.add(jbb);
		jp1.add(jbc);
		jp1.setLayout(new GridLayout(1,3));
		
		jbd.add(cb4);
		jbe.add(cb5);
		jbf.add(cb6);
		jp2.add(jbd);
		jp2.add(jbe);
		jp2.add(jbf);
		jp2.setLayout(new GridLayout(1,3));
		
		
		jbg.add(cb7);
		jp3.add(jbg);
		jp3.setLayout(new GridLayout(1,3));
		jp0.setLayout(new GridLayout(3,3));
		
		jps = new JPanel();
		jps.add(tfselect);
		jps.add(butOkselect,BorderLayout.NORTH);  
		f.add(jp0,BorderLayout.CENTER);
		
		f.add(jps,BorderLayout.SOUTH);
		myEvent();
		
		
		f.setVisible(true);
		
		
	}
	public void myEvent()
	{
		cb1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				String item = (String)cb1.getSelectedItem();
				if(" �� ʳ ��".equals(item))
				{
					tfselect.setText("");
				}else{
					tfselect.setText(item);
				}
			}
		});
		cb2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				String item = (String)cb2.getSelectedItem();
				if(" �� �� ��".equals(item))
				{
					tfselect.setText("");
				}else{
					tfselect.setText(item);
				}
			}
		});
		cb3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				String item = (String)cb3.getSelectedItem();
				if(" �� �� ��".equals(item))
				{
					tfselect.setText("");
				}else{
					tfselect.setText(item);
				}
			}
		});
		cb4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				String item = (String)cb4.getSelectedItem();
				if(" ˮ �� ��".equals(item))
				{
					tfselect.setText("");
				}else{
					tfselect.setText(item);
				}
			}
		});
		cb5.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				String item = (String)cb5.getSelectedItem();
				if(" �� ʳ ��".equals(item))
				{
					tfselect.setText("");
				}else{
					tfselect.setText(item);
				}
			}
		});
		cb6.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				String item = (String)cb6.getSelectedItem();
				if(" �� �� ��".equals(item))
				{
					tfselect.setText("");
				}else{
					tfselect.setText(item);
				}
			}
		});
		cb7.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				String item = (String)cb7.getSelectedItem();
				if(" �� Ʒ ��".equals(item))
				{
					tfselect.setText("");
				}else{
					tfselect.setText(item);
				}
			}
		});
		
		butOkselect.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				String storeName = tfselect.getText();
				if(!"".equals(storeName))
				{
					f.setVisible(false);
					new showMenu(storeName,userId);
				}
			}
		});
	}
	
	public String[] getItem(String sortName)
	{
		String[] storeName = new String[5];
		GetConnection getCon = new GetConnection();
		Statement st = getCon.getStatement();
		String sql = "select * from ����� where ������ = "+"'" + sortName +"'";
		ResultSet rs = null;
		int i = 0;
		try {
			rs = st.executeQuery(sql);
			while(rs.next())
			{
				String str = rs.getString("������");
				storeName[i] = str;
				i ++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getCon.releaseAll(rs);
		
		return storeName;
		
	}
	
}
