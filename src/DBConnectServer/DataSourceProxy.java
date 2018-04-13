package DBConnectServer;

import java.sql.Connection;

import javax.annotation.Resource;

import com.mysql.jdbc.StringUtils;

public class DataSourceProxy {
	ThreadLocal<String> dataSourceThreadLocal = new ThreadLocal<>();
	public static final String MASTER = "master";
	public static final String SLAVE = "slave"; 
	
	@Resource
	private ConnectFactory connectFactory = new ConnectFactory();   //定义数据库链接工厂
	
	/*
	 * 设置当前连接的数据库的MODE
	 *
	 */
	public void setMode(String dataMode)
	{
		dataSourceThreadLocal.set(dataMode);//当前线程中的局部变量 
	}
	/*
	 * 得到当前数据库连接的mode
	 */
	public String getMode()
	{
		return dataSourceThreadLocal.get();
	}
	
	/*
	 * 根据当前的mode 得到链接对象
	 */
	public Connection getThreadConnnection()
	{
		//判断当前是主数据库还是从数据库  默认是主数据库
		String mode = getMode();
		
		//if(!StringUtils.isNullOrEmpty(mode) && SLAVE.equals(mode))
		if(SLAVE.equals(mode))
		{
			//如果是从数据 那么使用随机数的方式得到从数据库的链接
			double random = Math.random();
			int index = (int)(random * connectFactory.getSlaveDataSourceSize());
			
			System.out.println("----使用的是"+(index + 1) + "从数据库-----");
			return connectFactory.getSlaveConnetion(index);
			//return connectFactory.getSlaveConnetion(1);
		}else{
			System.out.println("----使用的是主数据库-----");
			return connectFactory.getMasterConnection();
		}
		
	}
}
