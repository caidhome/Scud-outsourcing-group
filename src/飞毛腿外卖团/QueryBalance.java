package ��ë��������;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

//��ѯ������Ϣ
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
		//��������
		f = new JFrame("������Ϣ");
		f.setBounds(550,130,280,410);
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		
		tittle = new JLabel("������Ϣ");
		tittle.setFont(new Font("����",Font.BOLD,30));
		jp1.add(tittle);
		
		Object[] info = getInfo(userId);
		
		user = new JLabel("������");
		tfuser = new JTextField(10);
		tfuser.setText("  " + info[0]);
		//���ı�������Ϊ���ɱ༭
		tfuser.setEditable(false);
		key = new JLabel("���룺");
		tfkey = new JTextField(10);
		tfkey.setText("  " + info[1]);
		tfkey.setEditable(false);
		Id = new JLabel("�˺ţ�");
		tfId = new JTextField(10);
		tfId.setText("  " + info[2]);
		tfId.setEditable(false);
		bala = new JLabel("��");
		tfbala = new JTextField(10);
		tfbala.setText("  " + info[3]);
		tfbala.setEditable(false);
		add = new JLabel("��ַ��");
		tfadd = new JTextField(10);
		tfadd.setText("  " + info[4]);
		tfadd.setEditable(false);
		tel = new JLabel("�绰��");
		tftel = new JTextField(10);
		tftel.setText("  " + info[5]);
		tftel.setEditable(false);
		
		//����������
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
		
		update = new JButton("����");
		jp3.add(update);
		recharge = new JButton("��ֵ���");
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
		//������ֵ��ť
		recharge.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				new Recharge(userId);
			}
		});
		//���ذ�ť�ļ���
		update.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				f.setVisible(false);
			}
		});
		
	}
	//��ȡuserId��Ӧ����Ϣ
	public Object[] getInfo(long userId)
	{
		GetConnection getcon = new GetConnection();
		Statement st = getcon.getStatement();
		Object info[] = new Object[6];
		ResultSet rs = null;
		String sql = "select * from �û��� where �˺� ="+userId;
		try {
			rs = st.executeQuery(sql);
			if(rs.next())
			{
				info[0] = rs.getString("�û���");
				info[1] = rs.getLong("����");
				info[2] = rs.getLong("�˺�");
				info[3] = rs.getInt("���");
				info[4] = rs.getString("Ĭ�ϵ�ַ");
				info[5] = rs.getLong("��ϵ�绰");
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
