package ��ë��������;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
//ȷ���µ�����
public class placeOrder {

	private Menu m;
	private long userId;
	private Order o;
	private JFrame f,f1;
	private JPanel jp1,jp2,jp3;
	private JButton ok,cencel,okbala,ddok,okinput;
	private JLabel name,tel,address,amount,price,tittle,sum,dishname,remark,bala,jlbala,ddtip,jlinput;
	private JTextField tfname,tftel,tfaddress,tfamount,tfprice,tfsum,tfdishname,tfbala;
	private JTextArea taremark;
	private JDialog jdbala,ddjd,jdinput;
	
	public placeOrder(JFrame f1,Menu m,long userId)
	{
		this.userId = userId;
		this.m = m;
		this.f1 = f1;
		f1.setVisible(false);
		init();
		
	}
	public void init()
	{
		f = new JFrame("�µ�");
		f.setBounds(500,150,300,470);
		f.setResizable(false);
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		//���ô����е�������ʾ
		tittle = new JLabel("<html><font size = '20'>ȷ���µ�</font><br><br>������������Ϣ</p><br><br></html>");
		tittle.setFont(new Font("����",Font.BOLD,15));
		jp1.add(tittle);
		
		name = new JLabel("���ա� ����");
		tel = new JLabel("���� ������");
		dishname = new JLabel("���� ������");
		price = new JLabel("���� ����");
		amount = new JLabel("������ ����");
		sum = new JLabel("���ܡ� �");
		bala = new JLabel("���ࡡ �");
		address = new JLabel("���� ��ַ��");
		remark = new JLabel("���� ��ע��");

		
		tfname = new JTextField(15);
		tftel = new JTextField(15);
		tfdishname = new JTextField(15);
		tfdishname.setText("         " + m.getFoodName());
		tfdishname.setEditable(false);
		tfprice = new JTextField(15);
		tfprice.setEditable(false);
		tfprice.setText(String.valueOf( "         " + m.getPrice()) + "        Ԫ/��");
		tfamount = new JTextField(15);
		taremark = new JTextArea(3,15);
		
		tfsum = new JTextField(15);
		tfsum.setEditable(false);
		
		tfbala = new JTextField(15);
		tfbala.setEditable(false);
		tfbala.setText("         "+getBalance(userId));
		
		tfaddress = new JTextField(15);
		
		jp2.add(name);
		jp2.add(tfname);
		jp2.add(tel);
		jp2.add(tftel);
		jp2.add(dishname);
		jp2.add(tfdishname);
		jp2.add(price);
		jp2.add(tfprice);
		jp2.add(amount);
		jp2.add(tfamount);
		jp2.add(sum);
		jp2.add(tfsum);
		jp2.add(bala);
		jp2.add(tfbala);
		jp2.add(address);
		jp2.add(tfaddress);
		jp2.add(remark);
		jp2.add(taremark);
		
		jdbala = new JDialog(f,true);
		jdbala.setBounds(570, 300, 200, 150);
		jdbala.setTitle("��ʾ");
		jlbala = new JLabel("<html><br>�� �� �� �㣬�� �� ֵ  ��<br><br></html>");
		okbala = new JButton("ȷ��");
		jdbala.add(jlbala);
		jdbala.add(okbala);
		jdbala.setLayout(new FlowLayout());
		
		jdinput = new JDialog(f,true);
		jdinput.setBounds(560, 300, 240, 150);
		jdinput.setTitle("��ʾ");
		jlinput = new JLabel("<html><br> �� �� �� �� �� �� �� �� �� �� �� �� ��<br><br></html>");
		okinput = new JButton("ȷ��");
		jdinput.add(jlinput);
		jdinput.add(okinput);
		jdinput.setLayout(new FlowLayout());

		
		ddjd = new JDialog(f,true);
		ddjd.setBounds(560, 270, 230, 290);
		ddjd.setLayout(new FlowLayout());
		ddok = new JButton("���");
		ddtip = new JLabel();
		
		
		
		ok = new JButton("�� ��");
		cencel = new JButton("�� ��");
		jp3.add(ok);
		jp3.add(cencel);
		
		f.add(jp1,BorderLayout.NORTH);
		f.add(jp2,BorderLayout.CENTER);
		f.add(jp3,BorderLayout.SOUTH);
		myEvent();
		f.setVisible(true);
	}
	public void myEvent()
	{
		//��������������ļ��̼���
		tfamount.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e)
			{
				//�ж��Ƿ������˻س�������������˻س������Զ������ܶ�
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					String a = tfamount.getText();
					char c = a.charAt(0);
					if(c > '0' && c <= '9' && a.length() <= 1)
					{
						int count = Integer.parseInt(a);
						int p = m.getPrice();
						//���ܶ��Զ��������ܶ���ı�����
						tfsum.setText("       " + String.valueOf(count*p) + "         Ԫ");
					}else{
						tfsum.setText("������ 1~9 ��������");
					}
				}
			}
		});
		cencel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				f.setVisible(false);
				f1.setVisible(true);
			}
		});
		
		//ȷ�����Ļ����
		ok.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{ 
				String nn = tfname.getText();
				String tt = tftel.getText();
				String aa = tfaddress.getText();
				String am = tfamount.getText();
				//�ж��������ݵĸ�ʽ�Ƿ���ȷ
				if(nn != null && tt != null && aa != null && am != null && tt.length() == 11)
				{
					int count = Integer.parseInt(am);
					int p = m.getPrice();
					int sum = count * p;
					long tel = Long.parseLong(tt);
					//�ж��ܶ��Ƿ�������
					if(sum > getBalance(userId))
					{
						jdbala.setVisible(true);
					}else if(sum <= getBalance(userId)){
						Date date = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
						String ddTime = sdf.format(date);
						long orderId = getOrderId();
						o = new Order(userId,orderId, m.getPrice(),count, sum,tfname.getText(),tel, tfaddress.getText(), ddTime,
								taremark.getText(),m.getId());
						//�ж����ɶ����Ƿ�ɹ�
						if(generOrder(o,sum))
						{
							ddtip.setText("<html><font size = '10' >   ��  ϲ  �� </font><br><br><font size = '4' >�����ɹ���<br><br>�� �� �� �� �� Ϊ ��</font><br><font size = '8' > " + orderId + " </font><br><br><html>");
							ddjd.add(ddtip);
							ddjd.add(ddok);
							ddjd.setVisible(true);
							tfname.setText("");
							tftel.setText("");
							tfaddress.setText("");
							tfamount.setText("");
							taremark.setText("");
							f.setVisible(false);
						}else{
							jdinput.setVisible(true);
						}
					}
				}else{
					jdinput.setVisible(true);
				}
			}
		});
	
		okbala.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				jdbala.setVisible(false);
			}
		});
		//�����ɹ�����ʾ��ȷ����ť�ļ���
		ddok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				ddjd.setVisible(false);
			}
		});
		okinput.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				jdinput.setVisible(false);
			}
		});

		
	}
	//��ȡ�˺�ΪuserId�����
	public int getBalance(long userId)
	{
		//�������ݿ�
		GetConnection getcon = new GetConnection();
		Statement st = getcon.getStatement();
		String sql = "select * from �û��� where �˺� = "+userId;
		ResultSet rs = null;
		try {
			rs = st.executeQuery(sql);
			//�ж��˺��Ƿ����
			if(rs.next())
			{
				//���ظ��˺Ŷ�Ӧ�����
				return rs.getInt("���");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//�ͷ���Դ
		getcon.releaseAll(rs);
		return 0;
	}
	//��ȡ������
	public long getOrderId()
	{
		//�������ݿ�
		GetConnection getcon = new GetConnection();
		Statement st = getcon.getStatement();
		//��ȡ�������ж��������е����ֵ
		String sql = "select max(������) from ������";
		ResultSet rs = null;
		try {
			rs = st.executeQuery(sql);
			if(rs.next()){
				 long id = rs.getLong(1);
				 return id + 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getcon.releaseAll(rs);
		return 0;
	}
	//���ɶ���
	public boolean generOrder(Order o,int sum)
	{
		if(o == null)
		{
			return false;
		}
		//�������ݿ�
		GetConnection getcon = new GetConnection();
		Statement st = getcon.getStatement();
		//��Ӷ���
		String sql1 = "insert into ������ values ("+ o.getUserId() + "," 
		+ getOrderId()+ ","+o.getPrice()+"," + o.getAmount() +"," 
				+o.getSum()+ ",'" + o.getDdName() +"',"+o.getDdTel()
				+",'" + o.getDdAddress() + "','" +o.getDdTime()+"','" + o.getRemark()
				+"','" + o.getMenuId() + "')";
		//�۳����
		String sql2 = "update �û��� set ��� ="+getbala(userId,sum) + " where �˺� =" +userId;
		int v1 = 0;
		int v2 = 0;
		try {
			v1 = st.executeUpdate(sql1);
			v2 = st.executeUpdate(sql2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		getcon.release();
		if(v1 > 0 && v2 > 0)
			return true;
		else
			return false;
		
	}
	//��ȡuserId��Ӧ���������û������۳�sum��
	public int getbala(long userId,int sum)
	{
		GetConnection getcon = new GetConnection();
		Statement st = getcon.getStatement();
		String sql = "select ��� from �û��� where �˺�="+userId;
		ResultSet rs = null;
		try {
			rs = st.executeQuery(sql);
			if(rs.next())
				return rs.getInt("���")-sum;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	//�ж��ַ����Ƿ�Ϊ����
	public boolean isNumber(String str)
	{
		if(str.length() <= 0)
			return false;
		for(int i = 0;i < str.length();i++)
		{
			if(!Character.isDigit(i))
				return false;
		}
		return true;
	}
	

}
