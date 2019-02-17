package ��ë��������;

import java.sql.*;

//�����ݿ��ȡ����
public class GetConnection {
	//�����������ݿ�����Ҫ�ı���
	static ResultSet rs = null;
	static Statement st = null;
	static Connection con = null;
	
	public Statement getStatement(){
		//��������·��
		String driverName = "com.mysql.jdbc.Driver";
		try {
			//ע������
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//�����û��������뼰���ݿ�·��
		String user = "root";
		String password = "root";
		String url = "jdbc:mysql:///��ë��������";
		try {
			//�������ݿ�
			con = DriverManager.getConnection(url,user,password);
			//ͨ��con��ȡStatement����
			st = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return st;
	}
	//�ͷ���Դ
	public void release()
	{
		try {
			if(st != null)
			st.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			if(con != null)
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	public void releaseAll(ResultSet rs)
	{
		try {
			if(rs != null)
			rs.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		release();
	}
	
}
