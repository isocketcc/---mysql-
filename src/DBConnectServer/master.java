package DBConnectServer;

public class master {
	
	//链接的数据库和地址
	private String url = null;
	//链接的用户名
	private  String user = null;
	//链接的密码
	private String password = null;
	//获取数据库链接时的驱动
	private String driver = null;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	
}
