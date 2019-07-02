package dbc_demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnDemo {
	
	private String url = "jdbc:oracle:thin:@192.168.10.10:1521:orcl"; // ���ݿ��URL
	
	Connection con; //����Connection����
	
	/**
	 * ���һ�����ݿ����Ӷ���
	 * @param db_type ���ݿ�����
	 * @param ip ���ݿ�IP
	 * @param db_name ���ݿ�����
	 * @param user ���ݿ��û�
	 * @param pwd �û�����
	 * @return
	 */
	public Connection getConnection(String db_type, String ip, String db_name, String user, String pwd){
		if("oracle".equals(db_type)){
			url = "jdbc:oracle:thin:@" + ip + ":1521:" + db_name;
		} else if("mysql".equals(db_type)) {
			System.out.println("�������ݿ����δ������");
			url = "";
		}
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("����Oracle�����ɹ�");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("��������ʧ��");
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection(url, user, pwd);
			System.out.println("����Oracle���ݿ�ɹ�");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("���Oracle���ݿ�����ʧ��");
			e.printStackTrace();
		}
		return con;
	}
	
	/**
	 * ���Գ���
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String user = "hys"; // ��½���ݿ���û���
		String pwd = "hys"; // ��½���ݿ������
		String db_type = "oracle"; // Ҫ��½�����ݿ������
		String ip = "192.168.10.10"; // ���ݿ��IP��ַ
		String db_name = "orcl"; // ���ݿ����� ʵ����
		
		String par1 = "adm";
		String par2 = "adm";
		
		DBConnDemo dcd = new DBConnDemo();
		Connection con1 = dcd.getConnection(db_type, ip, db_name, user, pwd); // ������ݿ�����
		PreparedStatement sql;
		try {
			sql = con1.prepareStatement("insert into java_user(account, password) values(?, ?)" );
			System.out.println("��������Ԥ����ɹ�");
			sql.setString(1, par1);
			sql.setString(2, par2);
			sql.execute();
			System.out.println("�������ݳɹ�");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("��������ʧ��");
			e.printStackTrace();
		}
		
	}

}
