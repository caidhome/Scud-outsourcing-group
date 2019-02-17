package 飞毛腿外卖团;

import java.sql.*;

//与数据库获取连接
public class GetConnection {
	//申明连接数据库中需要的变量
	static ResultSet rs = null;
	static Statement st = null;
	static Connection con = null;
	
	public Statement getStatement(){
		//定义驱动路径
		String driverName = "com.mysql.jdbc.Driver";
		try {
			//注册驱动
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//定义用户名，密码及数据库路径
		String user = "root";
		String password = "root";
		String url = "jdbc:mysql:///飞毛腿外卖团";
		try {
			//连接数据库
			con = DriverManager.getConnection(url,user,password);
			//通过con获取Statement对象
			st = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return st;
	}
	//释放资源
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
