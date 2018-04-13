package DBConnectServer;

import java.sql.Connection;

import javax.annotation.Resource;

import com.mysql.jdbc.StringUtils;

public class DataSourceProxy {
	ThreadLocal<String> dataSourceThreadLocal = new ThreadLocal<>();
	public static final String MASTER = "master";
	public static final String SLAVE = "slave"; 
	
	@Resource
	private ConnectFactory connectFactory = new ConnectFactory();   //�������ݿ����ӹ���
	
	/*
	 * ���õ�ǰ���ӵ����ݿ��MODE
	 *
	 */
	public void setMode(String dataMode)
	{
		dataSourceThreadLocal.set(dataMode);//��ǰ�߳��еľֲ����� 
	}
	/*
	 * �õ���ǰ���ݿ����ӵ�mode
	 */
	public String getMode()
	{
		return dataSourceThreadLocal.get();
	}
	
	/*
	 * ���ݵ�ǰ��mode �õ����Ӷ���
	 */
	public Connection getThreadConnnection()
	{
		//�жϵ�ǰ�������ݿ⻹�Ǵ����ݿ�  Ĭ���������ݿ�
		String mode = getMode();
		
		//if(!StringUtils.isNullOrEmpty(mode) && SLAVE.equals(mode))
		if(SLAVE.equals(mode))
		{
			//����Ǵ����� ��ôʹ��������ķ�ʽ�õ������ݿ������
			double random = Math.random();
			int index = (int)(random * connectFactory.getSlaveDataSourceSize());
			
			System.out.println("----ʹ�õ���"+(index + 1) + "�����ݿ�-----");
			return connectFactory.getSlaveConnetion(index);
			//return connectFactory.getSlaveConnetion(1);
		}else{
			System.out.println("----ʹ�õ��������ݿ�-----");
			return connectFactory.getMasterConnection();
		}
		
	}
}
