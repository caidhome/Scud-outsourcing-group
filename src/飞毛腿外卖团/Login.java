package 敬谷揚翌沢妖;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
//鞠村式�塋省徠�
public class Login{
	private JFrame jd,f;
	private long userId;
	private JButton denglu,logOk,zhuce,selectOk,selecterrorOk;
	private JLabel jl1,jl2,logtip,inter,labselect,labselecterror;
	private JTextField tf1,tfselect;
	private JPasswordField tf2;
	private JDialog logerror,inputError;
	private JPanel p1,p2,p3;
	
	public Login()
	{
		f = new JFrame("鞠村");
		tf1 = new JTextField(10);
		tf2 = new JPasswordField(10);
		Font font = new Font("卜悶",Font.BOLD,16);
		
		jl1 = new JLabel();
		jl1.setText(" 嬲 催 :");
		jl2 = new JLabel();
		jl2.setText(" 畜 鷹 :");
		f.setResizable(false);
		f.setLayout(new GridLayout(3,1));
		denglu = new JButton("鞠 村");
		zhuce = new JButton("廣 過");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(400,200,530,220);
		
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		p1.add(jl1);
		p1.add(tf1);
		p2.add(jl2);
		p2.add(tf2);
		p3.add(denglu);
		p3.add(zhuce);
		f.add(p1 );
		f.add(p2 );
		f.add(p3 );
		
		//嬲催才畜鷹壓方象垂嶄孀音欺扮起竃議斤三崇
		logerror = new JDialog(f,"戻幣!",true);
		logtip = new JLabel("鞠村払移�…�議嬲催賜畜鷹嗤列��");
		logOk = new JButton("鳩協");
		logerror.setLayout(new FlowLayout());
		logerror.add(logtip);
		logerror.add(logOk);
		logerror.setBounds(600,280,240,150);
		
		//補秘議嬲催才畜鷹鯉塀危列扮起竃議斤三崇
		inputError = new JDialog(jd,"戻幣",true);
		labselecterror = new JLabel("艇議補秘嗤列��萩嶷仟補秘��");
		selecterrorOk = new JButton("鳩協");
		inputError.setLayout(new FlowLayout());
		inputError.add(labselecterror);
		inputError.add(selecterrorOk);
		inputError.setBounds(600,280,240,150);
		
		jd = new JFrame("麼暇汽");
		jd.setLayout(new FlowLayout());
		jd.setBounds(280, 120, 850, 500);
		
		inter = new JLabel();
		inter.setSize(600, 400);
		String info = "<html>*　*　*　*　*　*　*　*　*　*　　散　哭　序　秘　翌　沢　狼　由　*　　*　　*　　*　　*　*　<br>"
						+"*　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　*<br>" 
						+"*　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　*<br>" 
						+"*　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　*<br>" 
						+"*　　　　　　　　　　　　　　　　　　1.---厘  勣  出  翌  沢---+&nbsp　　　　　　　　　　　　　*<br>" 
						+"*　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　*<br>"
						+"*　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　*<br>" 
						+"*　　　　　　　　　　　　　　　　　　2.---臥  儂  匡  汽-----+&nbsp　　　　　　　　　　　　　　*<br>" 
						+"*　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　*<br>"
						+"*　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　*<br>" 
						+"*　　　　　　　　　　　　　　　　　　3.---函  ��  匡  汽-----+&nbsp　　　　　　　　　　　　　　*<br>" 
						+"*　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　*<br>"
						+"*　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　*<br>" 
						+"*　　　　　　　　　　　　　　　　　　4.---臥  儂  佚  連-----+&nbsp　　　　　　　　　　　　　　*<br>"
						+"*　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　*<br>" 
						+"*　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　*<br>" 
						+"*　　　　　　　　　　　　　　　　　　5.----曜  竃  狼  由----+&nbsp　　　　　　　　　　　　　　*<br>" 
						+"*　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　*<br>" 
						+"*　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　*<br>" 
						+"*　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　*<br>"
						+"*　 *　　 *　 *　 　　　*　　　 *　　 　* 　　　*　　*　　 *　 　* 　　*　　　*　　 *　　 * </html>";
		
		inter.setText(info);
		inter.setFont(font);
		jd.add(inter);
		selectOk = new JButton("鳩協");
		labselect = new JLabel("艇　議　僉　夲　頁　�此�");
		font = new Font("尻悶",Font.BOLD,18);
		labselect.setFont(font);
		jd.add(labselect);
		tfselect = new JTextField(5);
		tfselect.setFont(font);
		jd.add(tfselect);
		jd.add(selectOk);
	 
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myEvent();
		f.setVisible(true);
	}
	public void myEvent()
	{
		//鞠村梓泥議試強酌油
		denglu.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e)
			{
				String user = tf1.getText();
				String key = tf2.getText();
				//登僅補秘議嬲催才畜鷹鯉塀頁倦屎鳩
				if(isNumber(user) && isNumber(key))
				{
					int id = Integer.parseInt(user);
					userId = id;
					int password = Integer.parseInt(key);
					//銭俊方象垂
					GetConnection con = new GetConnection();
					Statement st = con.getStatement();
					String sql = "select * from 喘薩燕 where 嬲催 ="+id;
					boolean flag = false;
					try {
						ResultSet rs = st.executeQuery(sql);
						//登僅補秘議嬲催斤哘議畜鷹頁倦屎鳩
						if(rs.next()){
							if(password == rs.getInt("畜鷹"))
								{
									jd.setVisible(true);
									f.setVisible(false);
									flag = true;
								}
						}
						if(!flag)
						{
							logerror.setVisible(true);
							tf2.setText("");
						}
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}else{
					inputError.setVisible(true);
					tf2.setText("");
				}
			}
		});
		//鞠村払移戻幣鳩範梓泥議試強酌油
		logOk.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				logerror.setVisible(false);
				tf2.setText("");
			}
		});
		
		zhuce.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				new regist();
			}
		});
		
		
		
		//jd議完笥酌油
		jd.addWindowListener(new WindowAdapter(){
			public void windowClosed(WindowEvent e)
			{
				System.exit(0);
			}

		});
		//補秘議俶勣議捲暦斤哘議會催議酌油
		selectOk.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				selectfun();
			}

		});
		//補秘坪否議囚徒酌油
		tfselect.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					selectfun();
				}
			}
		});
		
		//僉夲危列戻幣鳩範梓泥議試強酌油
		selecterrorOk.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				inputError.setVisible(false);
				tfselect.setText("");
			}
		});
		
	}
	//登僅忖憲堪頁倦葎方忖鯉塀
	public boolean isNumber(String str)
	{
		if(str.isEmpty())
		{
			return false;
		}
		for(int i = 0;i < str.length();i++)
		{
			if(!Character.isDigit(str.charAt(i)))
				return false;
		}
		return true;
	}
	//峇佩僉夲議捲暦
	public void selectfun()
	{
		String str = tfselect.getText();
		int select;
		
		if(str != "")
		{
			select = Integer.parseInt(str);
			switch(select)					{
				case 1:new Take_out(userId);break;
				case 2:new QueryOrder(userId).getFrame().setVisible(true);;break;
				case 3:new CancelOrder(userId);break;
				case 4:new QueryBalance(userId);break;
				case 5:System.exit(0);break;
				default:inputError.setVisible(true);;break;
			}
			tfselect.setText("");
		}
		
	}
	
	
	
	public static void main(String[] args) {
		new Login();
	}	
}