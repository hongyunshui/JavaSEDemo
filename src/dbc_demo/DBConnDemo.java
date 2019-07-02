package dbc_demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnDemo {
	
	private String url = "jdbc:oracle:thin:@192.168.10.10:1521:orcl"; // 数据库的URL
	
	Connection con; //声明Connection对象
	
	/**
	 * 获得一个数据库连接对象
	 * @param db_type 数据库类型
	 * @param ip 数据库IP
	 * @param db_name 数据库名称
	 * @param user 数据库用户
	 * @param pwd 用户密码
	 * @return
	 */
	public Connection getConnection(String db_type, String ip, String db_name, String user, String pwd){
		if("oracle".equals(db_type)){
			url = "jdbc:oracle:thin:@" + ip + ":1521:" + db_name;
		} else if("mysql".equals(db_type)) {
			System.out.println("此种数据库操作未添加入库");
			url = "";
		}
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("加载Oracle驱动成功");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("驱动加载失败");
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection(url, user, pwd);
			System.out.println("连接Oracle数据库成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("获得Oracle数据库连接失败");
			e.printStackTrace();
		}
		return con;
	}
	
	/**
	 * 测试程序
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String user = "hys"; // 登陆数据库的用户名
		String pwd = "hys"; // 登陆数据库的密码
		String db_type = "oracle"; // 要登陆的数据库的类型
		String ip = "192.168.10.10"; // 数据库的IP地址
		String db_name = "orcl"; // 数据库名称 实例名
		
		String par1 = "adm";
		String par2 = "adm";
		
		DBConnDemo dcd = new DBConnDemo();
		Connection con1 = dcd.getConnection(db_type, ip, db_name, user, pwd); // 获得数据库连接
		PreparedStatement sql;
		try {
			sql = con1.prepareStatement("insert into java_user(account, password) values(?, ?)" );
			System.out.println("插入数据预处理成功");
			sql.setString(1, par1);
			sql.setString(2, par2);
			sql.execute();
			System.out.println("插入数据成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("插入数据失败");
			e.printStackTrace();
		}
		
	}

}
