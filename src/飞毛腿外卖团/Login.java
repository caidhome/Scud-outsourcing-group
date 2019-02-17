package ·ÉÃ«ÍÈÍâÂôÍÅ;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
//µÇÂ¼¼°ÏÔÊ¾Ê×Ò³
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
		f = new JFrame("µÇÂ¼");
		tf1 = new JTextField(10);
		tf2 = new JPasswordField(10);
		Font font = new Font("ËÎÌå",Font.BOLD,16);
		
		jl1 = new JLabel();
		jl1.setText(" ÕË ºÅ :");
		jl2 = new JLabel();
		jl2.setText(" ÃÜ Âë :");
		f.setResizable(false);
		f.setLayout(new GridLayout(3,1));
		denglu = new JButton("µÇ Â¼");
		zhuce = new JButton("×¢ ²á");
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
		
		//ÕËºÅºÍÃÜÂëÔÚÊý¾Ý¿âÖÐÕÒ²»µ½Ê±µ¯³öµÄ¶Ô»°¿ò
		logerror = new JDialog(f,"ÌáÊ¾!",true);
		logtip = new JLabel("µÇÂ¼Ê§°Ü£¡ÄúµÄÕËºÅ»òÃÜÂëÓÐÎó£¡");
		logOk = new JButton("È·¶¨");
		logerror.setLayout(new FlowLayout());
		logerror.add(logtip);
		logerror.add(logOk);
		logerror.setBounds(600,280,240,150);
		
		//ÊäÈëµÄÕËºÅºÍÃÜÂë¸ñÊ½´íÎóÊ±µ¯³öµÄ¶Ô»°¿ò
		inputError = new JDialog(jd,"ÌáÊ¾",true);
		labselecterror = new JLabel("ÄúµÄÊäÈëÓÐÎó£¬ÇëÖØÐÂÊäÈë£¡");
		selecterrorOk = new JButton("È·¶¨");
		inputError.setLayout(new FlowLayout());
		inputError.add(labselecterror);
		inputError.add(selecterrorOk);
		inputError.setBounds(600,280,240,150);
		
		jd = new JFrame("Ö÷²Ëµ¥");
		jd.setLayout(new FlowLayout());
		jd.setBounds(280, 120, 850, 500);
		
		inter = new JLabel();
		inter.setSize(600, 400);
		String info = "<html>*¡¡*¡¡*¡¡*¡¡*¡¡*¡¡*¡¡*¡¡*¡¡*¡¡¡¡»¶¡¡Ó­¡¡½ø¡¡Èë¡¡Íâ¡¡Âô¡¡Ïµ¡¡Í³¡¡*¡¡¡¡*¡¡¡¡*¡¡¡¡*¡¡¡¡*¡¡*¡¡<br>"
						+"*¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡*<br>" 
						+"*¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡*<br>" 
						+"*¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡*<br>" 
						+"*¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡1.---ÎÒ  Òª  ½Ð  Íâ  Âô---+&nbsp¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡*<br>" 
						+"*¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡*<br>"
						+"*¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡*<br>" 
						+"*¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡2.---²é  Ñ¯  ¶©  µ¥-----+&nbsp¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡*<br>" 
						+"*¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡*<br>"
						+"*¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡*<br>" 
						+"*¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡3.---È¡  Ïû  ¶©  µ¥-----+&nbsp¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡*<br>" 
						+"*¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡*<br>"
						+"*¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡*<br>" 
						+"*¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡4.---²é  Ñ¯  ÐÅ  Ï¢-----+&nbsp¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡*<br>"
						+"*¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡*<br>" 
						+"*¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡*<br>" 
						+"*¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡5.----ÍË  ³ö  Ïµ  Í³----+&nbsp¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡*<br>" 
						+"*¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡*<br>" 
						+"*¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡*<br>" 
						+"*¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡*<br>"
						+"*¡¡ *¡¡¡¡ *¡¡ *¡¡ ¡¡¡¡¡¡*¡¡¡¡¡¡ *¡¡¡¡ ¡¡* ¡¡¡¡¡¡*¡¡¡¡*¡¡¡¡ *¡¡ ¡¡* ¡¡¡¡*¡¡¡¡¡¡*¡¡¡¡ *¡¡¡¡ * </html>";
		
		inter.setText(info);
		inter.setFont(font);
		jd.add(inter);
		selectOk = new JButton("È·¶¨");
		labselect = new JLabel("Äú¡¡µÄ¡¡Ñ¡¡¡Ôñ¡¡ÊÇ¡¡£º¡¡");
		font = new Font("¿¬Ìå",Font.BOLD,18);
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
		//µÇÂ¼°´Å¥µÄ»î¶¯¼àÌý
		denglu.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e)
			{
				String user = tf1.getText();
				String key = tf2.getText();
				//ÅÐ¶ÏÊäÈëµÄÕËºÅºÍÃÜÂë¸ñÊ½ÊÇ·ñÕýÈ·
				if(isNumber(user) && isNumber(key))
				{
					int id = Integer.parseInt(user);
					userId = id;
					int password = Integer.parseInt(key);
					//Á¬½ÓÊý¾Ý¿â
					GetConnection con = new GetConnection();
					Statement st = con.getStatement();
					String sql = "select * from ÓÃ»§±í where ÕËºÅ ="+id;
					boolean flag = false;
					try {
						ResultSet rs = st.executeQuery(sql);
						//ÅÐ¶ÏÊäÈëµÄÕËºÅ¶ÔÓ¦µÄÃÜÂëÊÇ·ñÕýÈ·
						if(rs.next()){
							if(password == rs.getInt("ÃÜÂë"))
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
		//µÇÂ¼Ê§°ÜÌáÊ¾È·ÈÏ°´Å¥µÄ»î¶¯¼àÌý
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
		
		
		
		//jdµÄ´°¿Ú¼àÌý
		jd.addWindowListener(new WindowAdapter(){
			public void windowClosed(WindowEvent e)
			{
				System.exit(0);
			}

		});
		//ÊäÈëµÄÐèÒªµÄ·þÎñ¶ÔÓ¦µÄÐòºÅµÄ¼àÌý
		selectOk.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				selectfun();
			}

		});
		//ÊäÈëÄÚÈÝµÄ¼üÅÌ¼àÌý
		tfselect.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					selectfun();
				}
			}
		});
		
		//Ñ¡Ôñ´íÎóÌáÊ¾È·ÈÏ°´Å¥µÄ»î¶¯¼àÌý
		selecterrorOk.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				inputError.setVisible(false);
				tfselect.setText("");
			}
		});
		
	}
	//ÅÐ¶Ï×Ö·û´®ÊÇ·ñÎªÊý×Ö¸ñÊ½
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
	//Ö´ÐÐÑ¡ÔñµÄ·þÎñ
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