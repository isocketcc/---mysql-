package DBConnectServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class ConnectFactory {
	//�����ݿ�
	private DataSource masterDataSource = null;
	//�����ݿ�
	private DataSource slave01DataSource = null;
	
	private DataSource slave02DataSource = null;
	
	//���������Դ�б�
	private List<DataSource> slaveDataSourceList;
	
	private int slaveDataSourceSize = 0;
	ConnectFactory()
	{
		//��ʼ������
		masterDataSource =new DataSource();
		slave01DataSource = new DataSource();
		slave02DataSource = new DataSource();
		//��ʼ������
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
		//�������ݿ�����������Դ�б��� �Ա����ϵͳѡ�����ӵ����ݿ�
		slaveDataSourceList.add(slave01DataSource);
		slaveDataSourceList.add(slave02DataSource);

		slaveDataSourceSize =  slaveDataSourceList.size();  //��¼��ǰ�б�Ĵ�С
		System.out.println("ceshi ");
	}

	
	/*
	 * ��ȡ�����ݿ������
	 */
	public Connection getMasterConnection()
	{
		return getConnection(masterDataSource);   //������master����
	}
	/*
	 * ��ȡ�����ݿ������
	 * 
	 */
	public int getSlaveDataSourceSize()
	{
		return slaveDataSourceSize;
	}
	/*
	 * �õ������ݿ�����
	 */
	public Connection getSlaveConnetion(int index)
	{
		return getConnection(slaveDataSourceList.get(index));
		//return getConnection(slave01DataSource);
	}
	//��ȡ���ݿ� ����
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
