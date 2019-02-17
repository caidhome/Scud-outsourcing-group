package ��ë��������;
//��ʾ��ѡ�еĵ�����в˵����˼�
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import javax.swing.*;

public class showMenu {

	private String storeName,type,dishID;
	private long userId;
	private JFrame f;
	private JPanel jp,jp2,jp3;
	private JTable jt;
	private JLabel jl;
	private JTextField tf;
	private JButton butOk;
	private JRadioButton jrb1,jrb2;
	private ButtonGroup group;
	private Menu m;
	private boolean flag = false;
	
	public showMenu(String storeName,long userId)
	{
		this.userId = userId;
		this.storeName = storeName;
		init();
	}
	public void init(){
		f = new JFrame("�˵�");
		f.setBounds(420,150,600,420);
		f.setResizable(false);
		ArrayList<Menu> al = new QueryAllMenu().getAllMenu(storeName);
		jp = new JPanel();
		String tableHead[] = {"ʳ������","�۸�","��������","ID"};
		String tableBody[][] = new String[al.size()][4];
		for(int i = 0;i < al.size();i++)
		{
			Menu m = al.get(i);
			tableBody[i][0] = m.getFoodName();
			tableBody[i][1] = String.valueOf(m.getPrice());
			tableBody[i][2] = m.getEffic();
			tableBody[i][3] = m.getId();
		}
		jt = new JTable(tableBody,tableHead){
            public boolean isCellEditable(int row, int column)
            {
                       return false;
            }
		};
		
		jt.setRowHeight(20);
		jt.setFont(new Font("����",Font.BOLD,16));
		jp.add(new JScrollPane(jt));
		
		jp2 = new JPanel();
		jl = new JLabel(storeName);
		jl.setFont(new Font("����",Font.BOLD,30));
		jp2.add(jl,BorderLayout.CENTER);
		
		jp3 = new JPanel();
		butOk = new JButton(" ��  �� ");
		tf = new JTextField(20);
		tf.setEditable(false);
		tf.setFont(new Font("����",Font.BOLD,16));
		
		
		
		jp3.add(tf);
		jp3.add(butOk);
		
		group = new ButtonGroup();
		jrb1 = new JRadioButton("����");
		jrb2 = new JRadioButton("����");
		group.add(jrb1);
		group.add(jrb2);
		
		
		f.add(jp,BorderLayout.CENTER);
		f.add(jp2,BorderLayout.NORTH);
		f.add(jp3,BorderLayout.SOUTH);
		
		myEvent();
		
		f.setVisible(true);
	}
	public void event()
	{
		jrb1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				type = "����";
				setType(type);
			}
		});
		jrb2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				type = "����";
				setType(type);
			}
		});
	}
	public void myEvent()
	{
		jt.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				
				
				int select = jt.getSelectedRow();
				if(select >= 0){
					tf.setText("     "+(String)jt.getValueAt(select, 0));
					dishID = (String) jt.getValueAt(select, 3);
					m = new Menu((String)jt.getValueAt(select, 0),Integer.parseInt(""+jt.getValueAt(select, 1)),(String)jt.getValueAt(select, 2),(String)jt.getValueAt(select, 3),1);
				}
				
				if(tf.getText().indexOf("����") != -1 && !flag)
				{
					jp3.add(jrb1);
					jp3.add(jrb2);
					f.validate();
					flag = true;
					event();
				}else if(tf.getText().indexOf("����") == -1){
					setType("����");
					jp3.remove(jrb1);
					jp3.validate();;
					jp3.remove(jrb2);
					jp3.validate();;
					flag = false;
				}
			}
		});
		
		butOk.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(type != null)
				{
					new placeOrder(f,m,userId);
				}
			}
		});
	}
	public void setType(String type)
	{
		this.type = type;
	}
	
//����
//	public static void main(String[] args) {
//		new showMenu("�ڲ�����",201610);
//		
//	}

}
