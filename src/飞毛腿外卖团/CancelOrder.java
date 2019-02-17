package ��ë��������;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.*;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

public class CancelOrder {
	//�����û����˺�
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
		//����QureryOrder�Ķ���
		QueryOrder qo = new QueryOrder(userId);
		//��QueryOrder�е�f��ֵ����ǰ��f
		f = qo.f;
		f.setResizable(false);
		//����һ���ı�����������ѡ���Ķ�����IDֵ
		tf = new JTextField(18);
		tf.setFont(new Font("����",Font.BOLD,14));
		tf.setEditable(false);
		//��QueryOrder�еĵ��������jp3��ֵ����ǰ�����jp
		jp = qo.jp3;
		jp.add(tf);
		//��QueryOrder�еİ�ťbut��ֵ����ǰҳ��İ�ť
		but = qo.but;
		jt = qo.jt;
		jp.remove(but);
		but = new JButton("ɾ������");
		jp.add(but);
		
		jd1 = new JDialog(f, "��ʾ");
		jd1.setBounds(600,290,200,150);
		jd1.setLayout(new FlowLayout());
		okinfo = new JButton("ȷ��");
		celinfo = new JButton("ȡ��");
		jltip1 = new JLabel("<html><br>��ȷ��Ҫɾ���ö�����<br><br></html>");
		jd1.add(jltip1);
		jd1.add(okinfo);
		jd1.add(celinfo);
		
		
		
		jd2 = new JDialog(jd1, "��ʾ");
		//����jd2��Layout�ĸ�ʽ
		jd2.setLayout(new FlowLayout());
		jd2.setBounds(600,290,200,150);
		okdele = new JButton("ȷ��");
				
		
		
		myEvent();
		f.setVisible(true);
	}
	public void myEvent()
	{
		//���ñ���������
		jt.addMouseListener(new MouseAdapter(){
			//��дMouseAdapter�е�������ķ���
			public void mouseClicked(MouseEvent e)
			{
				//��ȡѡ�е��к�
				int select = jt.getSelectedRow();
				if(select >= 0)
				{
					//��ѡ���еĵ�1�и�ֵ��menuId��
					menuId = (long) jt.getValueAt(select, 0);
					//��menuId ��ֵ��䵽�ı����У�
					tf.setText("������ : " + menuId);
				}else{
					//���ûѡ�����ı�������Ϊ��
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
				//�ж����deleteOrder�����ķ���ֵ�Ƿ�Ϊ�������ֵ����Ϊ�����㣬��˵��ɾ���ɹ�����֮��Ȼ
				if(deleteOrder(menuId) >= 1)
				{
					jd1.setVisible(false);
					jltip2 = new JLabel("<html><br>��ɾ�������ɡ��� ��!<br><br></html>");
					jd2.add(jltip2);
					jd2.add(okdele);
					jd2.setVisible(true);
				}else{
					jd1.setVisible(false);
					jltip2 = new JLabel("<html><br>��ɾ������ʧ���� ��!<br><br></html>");
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
	//����ɾ�������ķ������������ֵ����0��ɾ���ɹ�
	public int deleteOrder(long menuId)
	{
		//�������ݿ�
		GetConnection getcon = new GetConnection();
		Statement st = getcon.getStatement();
		String sql = "delete from ������ where ������ =" + menuId;
		int row = 0;
		try {
			//��ִ��sql���ķ���ֵ��ֵ��row�����ɾ���ɹ�������Ὣ���ݿⱻӰ�����������
			//���ɾ��ʧ�ܣ��򷵻�0�У��򷵻�0��
			row = st.executeUpdate(sql);
			if(row >= 1)
			{
				return row;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//�ͷ���Դ
		getcon.release();;
		return row;
		
	}
	
	
	public static void main(String args[])
	{
		new CancelOrder(201600);
	}
}
