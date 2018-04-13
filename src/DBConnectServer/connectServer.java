package DBConnectServer;
import java.sql.DriverManager;
import java.sql.ResultSet;  //数据库返回结果集
import java.sql.SQLException;
import java.util.jar.Attributes.Name;

import javax.swing.table.TableStringConverter;

import com.mysql.jdbc.PreparedStatement;

import java.sql.Connection;
public class connectServer {
	private static DataSourceProxy dataSourceProxy = new DataSourceProxy();
	public static  void test() {
		try{
			dataSourceProxy.setMode(DataSourceProxy.SLAVE);
			Connection connection = dataSourceProxy.getThreadConnnection(); //获取数据库链接
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("select * from user");
			//preparedStatement.setString(1,name);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				System.out.println(resultSet.getString("user"));
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public static  void main(String[] args) throws SQLException
	{
//		Connection con;   //创建数据库链接对象
//		//驱动名 设置
//		String driver = "com.mysql.jdbc.Driver";
//		//URL只想要访问的数据库名称
//		String URL = "jdbc:mysql://localhost:3306/test";
//	    //数据库链接对象
//		String user = "root";
//		//数据库链接密码
//		String password = "123";
//		
//		con =  DriverManager.getConnection(URL,user,password);
//		if(!con.isClosed())
//		{
//			System.out.println("succeeded connecting to the Database!");
//		}
//		else
//		{
//			System.out.println("faild connecting ti the Database!");
//		}
		test();
		
	}
}
