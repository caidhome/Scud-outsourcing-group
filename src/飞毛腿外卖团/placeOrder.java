package 飞毛腿外卖团;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
//确认下单界面
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
		f = new JFrame("下单");
		f.setBounds(500,150,300,470);
		f.setResizable(false);
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		//设置窗口中的主题提示
		tittle = new JLabel("<html><font size = '20'>确认下单</font><br><br>请完善您的信息</p><br><br></html>");
		tittle.setFont(new Font("黑体",Font.BOLD,15));
		jp1.add(tittle);
		
		name = new JLabel("　姓　 名　");
		tel = new JLabel("　电 　话　");
		dishname = new JLabel("　菜 　名　");
		price = new JLabel("　价 　格　");
		amount = new JLabel("　数　 量　");
		sum = new JLabel("　总　 额　");
		bala = new JLabel("　余　 额　");
		address = new JLabel("　地 　址　");
		remark = new JLabel("　备 　注　");

		
		tfname = new JTextField(15);
		tftel = new JTextField(15);
		tfdishname = new JTextField(15);
		tfdishname.setText("         " + m.getFoodName());
		tfdishname.setEditable(false);
		tfprice = new JTextField(15);
		tfprice.setEditable(false);
		tfprice.setText(String.valueOf( "         " + m.getPrice()) + "        元/份");
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
		jdbala.setTitle("提示");
		jlbala = new JLabel("<html><br>余 额 不 足，请 充 值  ！<br><br></html>");
		okbala = new JButton("确定");
		jdbala.add(jlbala);
		jdbala.add(okbala);
		jdbala.setLayout(new FlowLayout());
		
		jdinput = new JDialog(f,true);
		jdinput.setBounds(560, 300, 240, 150);
		jdinput.setTitle("提示");
		jlinput = new JLabel("<html><br> 您 的 输 入 有 误 ， 请 重 新 输 入 ！<br><br></html>");
		okinput = new JButton("确定");
		jdinput.add(jlinput);
		jdinput.add(okinput);
		jdinput.setLayout(new FlowLayout());

		
		ddjd = new JDialog(f,true);
		ddjd.setBounds(560, 270, 230, 290);
		ddjd.setLayout(new FlowLayout());
		ddok = new JButton("完成");
		ddtip = new JLabel();
		
		
		
		ok = new JButton("提 交");
		cencel = new JButton("返 回");
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
		//设置输入的数量的键盘监听
		tfamount.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e)
			{
				//判断是否输入了回车键，如果输入了回车键则自动生成总额
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					String a = tfamount.getText();
					char c = a.charAt(0);
					if(c > '0' && c <= '9' && a.length() <= 1)
					{
						int count = Integer.parseInt(a);
						int p = m.getPrice();
						//将总额自动生成在总额的文本框中
						tfsum.setText("       " + String.valueOf(count*p) + "         元");
					}else{
						tfsum.setText("请输入 1~9 份外卖！");
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
		
		//确定键的活动监听
		ok.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{ 
				String nn = tfname.getText();
				String tt = tftel.getText();
				String aa = tfaddress.getText();
				String am = tfamount.getText();
				//判断输入内容的格式是否正确
				if(nn != null && tt != null && aa != null && am != null && tt.length() == 11)
				{
					int count = Integer.parseInt(am);
					int p = m.getPrice();
					int sum = count * p;
					long tel = Long.parseLong(tt);
					//判断总额是否大于余额
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
						//判断生成订单是否成功
						if(generOrder(o,sum))
						{
							ddtip.setText("<html><font size = '10' >   恭  喜  你 </font><br><br><font size = '4' >订单成功！<br><br>您 的 订 单 号 为 ：</font><br><font size = '8' > " + orderId + " </font><br><br><html>");
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
		//订单成功的提示的确定按钮的监听
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
	//获取账号为userId的余额
	public int getBalance(long userId)
	{
		//连接数据库
		GetConnection getcon = new GetConnection();
		Statement st = getcon.getStatement();
		String sql = "select * from 用户表 where 账号 = "+userId;
		ResultSet rs = null;
		try {
			rs = st.executeQuery(sql);
			//判断账号是否存在
			if(rs.next())
			{
				//返回该账号对应的余额
				return rs.getInt("余额");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//释放资源
		getcon.releaseAll(rs);
		return 0;
	}
	//获取订单号
	public long getOrderId()
	{
		//连接数据库
		GetConnection getcon = new GetConnection();
		Statement st = getcon.getStatement();
		//获取订单表中订单号列中的最大值
		String sql = "select max(订单号) from 订单表";
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
	//生成订单
	public boolean generOrder(Order o,int sum)
	{
		if(o == null)
		{
			return false;
		}
		//连接数据库
		GetConnection getcon = new GetConnection();
		Statement st = getcon.getStatement();
		//添加订单
		String sql1 = "insert into 订单表 values ("+ o.getUserId() + "," 
		+ getOrderId()+ ","+o.getPrice()+"," + o.getAmount() +"," 
				+o.getSum()+ ",'" + o.getDdName() +"',"+o.getDdTel()
				+",'" + o.getDdAddress() + "','" +o.getDdTime()+"','" + o.getRemark()
				+"','" + o.getMenuId() + "')";
		//扣除余额
		String sql2 = "update 用户表 set 余额 ="+getbala(userId,sum) + " where 账号 =" +userId;
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
	//获取userId对应的余额及将该用户的余额扣除sum；
	public int getbala(long userId,int sum)
	{
		GetConnection getcon = new GetConnection();
		Statement st = getcon.getStatement();
		String sql = "select 余额 from 用户表 where 账号="+userId;
		ResultSet rs = null;
		try {
			rs = st.executeQuery(sql);
			if(rs.next())
				return rs.getInt("余额")-sum;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	//判断字符串是否为数字
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
