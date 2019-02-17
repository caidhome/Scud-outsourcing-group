package 飞毛腿外卖团;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.*;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

public class CancelOrder {
	//定义用户的账号
	private long userId;
	
	private JFrame f;
	private JTextField tf;
	private JPanel jp;
	private JButton but,okinfo,celinfo,okdele;
	private JTable jt;
	private JLabel jltip1,jltip2;
	private JDialog jd1,jd2;
	private long menuId;
	
	
	public CancelOrder(long userId)
	{
		this.userId = userId;
		init();
	}
	public void init()
	{
		//创建QureryOrder的对象
		QueryOrder qo = new QueryOrder(userId);
		//将QueryOrder中的f赋值给当前的f
		f = qo.f;
		f.setResizable(false);
		//定义一个文本框，用来接收选定的订单的ID值
		tf = new JTextField(18);
		tf.setFont(new Font("宋体",Font.BOLD,14));
		tf.setEditable(false);
		//将QueryOrder中的第三个面板jp3赋值给当前的面板jp
		jp = qo.jp3;
		jp.add(tf);
		//将QueryOrder中的按钮but赋值给当前页面的按钮
		but = qo.but;
		jt = qo.jt;
		jp.remove(but);
		but = new JButton("删除订单");
		jp.add(but);
		
		jd1 = new JDialog(f, "提示");
		jd1.setBounds(600,290,200,150);
		jd1.setLayout(new FlowLayout());
		okinfo = new JButton("确定");
		celinfo = new JButton("取消");
		jltip1 = new JLabel("<html><br>您确认要删除该订单吗？<br><br></html>");
		jd1.add(jltip1);
		jd1.add(okinfo);
		jd1.add(celinfo);
		
		
		
		jd2 = new JDialog(jd1, "提示");
		//设置jd2的Layout的格式
		jd2.setLayout(new FlowLayout());
		jd2.setBounds(600,290,200,150);
		okdele = new JButton("确定");
				
		
		
		myEvent();
		f.setVisible(true);
	}
	public void myEvent()
	{
		//设置表格的鼠标监听
		jt.addMouseListener(new MouseAdapter(){
			//重写MouseAdapter中的鼠标点击的方法
			public void mouseClicked(MouseEvent e)
			{
				//获取选中的列号
				int select = jt.getSelectedRow();
				if(select >= 0)
				{
					//将选中行的第1列赋值给menuId；
					menuId = (long) jt.getValueAt(select, 0);
					//将menuId 的值填充到文本框中；
					tf.setText("订单号 : " + menuId);
				}else{
					//如果没选中则文本框设置为空
					tf.setText("");
				}
			}
		});
		but.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(!"".equals(tf.getText()))
					jd1.setVisible(true);
			}
		});
		celinfo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				jd1.setVisible(false);
			}
		});
		okinfo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				//判断如果deleteOrder方法的返回值是否为大于零的值，若为大于零，则说明删除成功，反之不然
				if(deleteOrder(menuId) >= 1)
				{
					jd1.setVisible(false);
					jltip2 = new JLabel("<html><br>　删　除　成　功 　!<br><br></html>");
					jd2.add(jltip2);
					jd2.add(okdele);
					jd2.setVisible(true);
				}else{
					jd1.setVisible(false);
					jltip2 = new JLabel("<html><br>　删　除　失　败 　!<br><br></html>");
					jd2.add(jltip2);
					jd2.add(okdele);
					jd2.setVisible(true);

				}
			}
		});
		okdele.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				f.setVisible(false);
				jd2.setVisible(false);
				new CancelOrder(userId);
			}
		});
	}
	//定义删除订单的方法，如果返回值大于0则删除成功
	public int deleteOrder(long menuId)
	{
		//连接数据库
		GetConnection getcon = new GetConnection();
		Statement st = getcon.getStatement();
		String sql = "delete from 订单表 where 订单号 =" + menuId;
		int row = 0;
		try {
			//将执行sql语句的返回值赋值给row，如果删除成功个，则会将数据库被影响的行数返回
			//如果删除失败，则返回0行，则返回0；
			row = st.executeUpdate(sql);
			if(row >= 1)
			{
				return row;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//释放资源
		getcon.release();;
		return row;
		
	}
	
	
	public static void main(String args[])
	{
		new CancelOrder(201600);
	}
}
