package tes;

public class SqlUrl {
	public static String Driver() {
		String driver = "com.mysql.cj.jdbc.Driver";
		return driver;
	}
	public static String url(){
		String url ="jdbc:mysql://100.90.166.57:3306/hrd";
		return url;
	}
	public static String userpass() {
		String userpass = "hrdjerapah";
		return userpass;
	}
	public static String usrlogin() {
		//database user login information
		String query = "select * from user";
		return query;
	}
	public static String dbms() {
		//database main system
		String query = "select * from hrds";
		return query;
	}
	public static String[] level() {
		//database main system
		String[] level = {"Administrator","Admin","Viewer"};
		return level;
	}
}
