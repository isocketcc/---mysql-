package DBConnectServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class ConnectFactory {
	//主数据库
	private DataSource masterDataSource = null;
	//从数据库
	private DataSource slave01DataSource = null;
	
	private DataSource slave02DataSource = null;
	
	//定义从数据源列表
	private List<DataSource> slaveDataSourceList;
	
	private int slaveDataSourceSize = 0;
	ConnectFactory()
	{
		//初始化对象
		masterDataSource =new DataSource();
		slave01DataSource = new DataSource();
		slave02DataSource = new DataSource();
		//初始化对象
		masterDataSource.setDriver("com.mysql.jdbc.Driver");
		masterDataSource.setUrl("jdbc:mysql://192.168.1.25:3306/test_repl");
		masterDataSource.setUser("root");
		masterDataSource.setPassword("root");
		
		slave01DataSource.setDriver("com.mysql.jdbc.Driver");
		slave01DataSource.setUrl("jdbc:mysql://192.168.1.27:3306/test_repl");
		slave01DataSource.setUser("root");
		slave01DataSource.setPassword("root");
		
		slave02DataSource.setDriver("com.mysql.jdbc.Driver");
		slave02DataSource.setUrl("jdbc:mysql://192.168.1.28:3306/test_repl");
		slave02DataSource.setUser("root");
		slave02DataSource.setPassword("root");
		slaveDataSourceList = new ArrayList<>();
		//将从数据库添加入从数据源列表中 以便后期系统选择链接的数据库
		slaveDataSourceList.add(slave01DataSource);
		slaveDataSourceList.add(slave02DataSource);

		slaveDataSourceSize =  slaveDataSourceList.size();  //记录当前列表的大小
		System.out.println("ceshi ");
	}

	
	/*
	 * 获取主数据库的链接
	 */
	public Connection getMasterConnection()
	{
		return getConnection(masterDataSource);   //参数是master对象
	}
	/*
	 * 获取从数据库的链接
	 * 
	 */
	public int getSlaveDataSourceSize()
	{
		return slaveDataSourceSize;
	}
	/*
	 * 得到从数据库链接
	 */
	public Connection getSlaveConnetion(int index)
	{
		return getConnection(slaveDataSourceList.get(index));
		//return getConnection(slave01DataSource);
	}
	//获取数据库 链接
	private Connection getConnection(DataSource dataSource)
	{
		 String driver = "com.mysql.jdbc.Driver";
		Connection connect = null;
		try{
			Class.forName(driver);
			connect = DriverManager.getConnection(dataSource.getUrl(), dataSource.getUser(), dataSource.getPassword());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return connect;
	}
}
