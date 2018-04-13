package DBConnectServer;

public class slave01 {
	//设置链接地址
	private String url = null;
	//设置链接用户名
	private String user = null;
	//设置链接密码
	private String password = null;
	//设置链接数据库驱动
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
