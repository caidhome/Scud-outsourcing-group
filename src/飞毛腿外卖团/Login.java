package ��ë��������;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
//��¼����ʾ��ҳ
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
		f = new JFrame("��¼");
		tf1 = new JTextField(10);
		tf2 = new JPasswordField(10);
		Font font = new Font("����",Font.BOLD,16);
		
		jl1 = new JLabel();
		jl1.setText(" �� �� :");
		jl2 = new JLabel();
		jl2.setText(" �� �� :");
		f.setResizable(false);
		f.setLayout(new GridLayout(3,1));
		denglu = new JButton("�� ¼");
		zhuce = new JButton("ע ��");
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
		
		//�˺ź����������ݿ����Ҳ���ʱ�����ĶԻ���
		logerror = new JDialog(f,"��ʾ!",true);
		logtip = new JLabel("��¼ʧ�ܣ������˺Ż���������");
		logOk = new JButton("ȷ��");
		logerror.setLayout(new FlowLayout());
		logerror.add(logtip);
		logerror.add(logOk);
		logerror.setBounds(600,280,240,150);
		
		//������˺ź������ʽ����ʱ�����ĶԻ���
		inputError = new JDialog(jd,"��ʾ",true);
		labselecterror = new JLabel("���������������������룡");
		selecterrorOk = new JButton("ȷ��");
		inputError.setLayout(new FlowLayout());
		inputError.add(labselecterror);
		inputError.add(selecterrorOk);
		inputError.setBounds(600,280,240,150);
		
		jd = new JFrame("���˵�");
		jd.setLayout(new FlowLayout());
		jd.setBounds(280, 120, 850, 500);
		
		inter = new JLabel();
		inter.setSize(600, 400);
		String info = "<html>*��*��*��*��*��*��*��*��*��*��������ӭ�������롡�⡡����ϵ��ͳ��*����*����*����*����*��*��<br>"
						+"*����������������������������������������������������������������������������������������*<br>" 
						+"*����������������������������������������������������������������������������������������*<br>" 
						+"*����������������������������������������������������������������������������������������*<br>" 
						+"*������������������������������������1.---��  Ҫ  ��  ��  ��---+&nbsp��������������������������*<br>" 
						+"*����������������������������������������������������������������������������������������*<br>"
						+"*����������������������������������������������������������������������������������������*<br>" 
						+"*������������������������������������2.---��  ѯ  ��  ��-----+&nbsp����������������������������*<br>" 
						+"*����������������������������������������������������������������������������������������*<br>"
						+"*����������������������������������������������������������������������������������������*<br>" 
						+"*������������������������������������3.---ȡ  ��  ��  ��-----+&nbsp����������������������������*<br>" 
						+"*����������������������������������������������������������������������������������������*<br>"
						+"*����������������������������������������������������������������������������������������*<br>" 
						+"*������������������������������������4.---��  ѯ  ��  Ϣ-----+&nbsp����������������������������*<br>"
						+"*����������������������������������������������������������������������������������������*<br>" 
						+"*����������������������������������������������������������������������������������������*<br>" 
						+"*������������������������������������5.----��  ��  ϵ  ͳ----+&nbsp����������������������������*<br>" 
						+"*����������������������������������������������������������������������������������������*<br>" 
						+"*����������������������������������������������������������������������������������������*<br>" 
						+"*����������������������������������������������������������������������������������������*<br>"
						+"*�� *���� *�� *�� ������*������ *���� ��* ������*����*���� *�� ��* ����*������*���� *���� * </html>";
		
		inter.setText(info);
		inter.setFont(font);
		jd.add(inter);
		selectOk = new JButton("ȷ��");
		labselect = new JLabel("�����ġ�ѡ�����ǡ�����");
		font = new Font("����",Font.BOLD,18);
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
		//��¼��ť�Ļ����
		denglu.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e)
			{
				String user = tf1.getText();
				String key = tf2.getText();
				//�ж�������˺ź������ʽ�Ƿ���ȷ
				if(isNumber(user) && isNumber(key))
				{
					int id = Integer.parseInt(user);
					userId = id;
					int password = Integer.parseInt(key);
					//�������ݿ�
					GetConnection con = new GetConnection();
					Statement st = con.getStatement();
					String sql = "select * from �û��� where �˺� ="+id;
					boolean flag = false;
					try {
						ResultSet rs = st.executeQuery(sql);
						//�ж�������˺Ŷ�Ӧ�������Ƿ���ȷ
						if(rs.next()){
							if(password == rs.getInt("����"))
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
		//��¼ʧ����ʾȷ�ϰ�ť�Ļ����
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
		
		
		
		//jd�Ĵ��ڼ���
		jd.addWindowListener(new WindowAdapter(){
			public void windowClosed(WindowEvent e)
			{
				System.exit(0);
			}

		});
		//�������Ҫ�ķ����Ӧ����ŵļ���
		selectOk.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				selectfun();
			}

		});
		//�������ݵļ��̼���
		tfselect.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					selectfun();
				}
			}
		});
		
		//ѡ�������ʾȷ�ϰ�ť�Ļ����
		selecterrorOk.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				inputError.setVisible(false);
				tfselect.setText("");
			}
		});
		
	}
	//�ж��ַ����Ƿ�Ϊ���ָ�ʽ
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
	//ִ��ѡ��ķ���
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