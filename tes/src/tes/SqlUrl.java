package tes;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;

import org.apache.commons.lang3.ArrayUtils;

public class SqlUrl {
	public static String Driver() {
		String driver = "com.mysql.cj.jdbc.Driver";
		return driver;
	}
	public static String confdir() {
		String config = System.getProperty("user.dir")+"/bin/hrd.config";
		return config;
	}
	public static String url(){
		String url;
		Properties urls = new Properties();
			try (FileInputStream load = new FileInputStream(confdir())){
				urls.load(load);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			url = "jdbc:mysql://"+urls.getProperty("server.url")+":"+urls.getProperty("server.port")+"/"+urls.getProperty("server.database");
			
		return url;
	}
	
	public static Object config(String configs, String key){
		Object conf;
		Properties urls = new Properties();
		String config = System.getProperty("user.dir")+"/bin/hrd.config";
		
			try (FileInputStream load = new FileInputStream(config)){
				urls.load(load);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conf = urls.setProperty(configs, key);
			return conf;
	}
	public static String user() {
		String url;
		Properties urls = new Properties();
			try (FileInputStream load = new FileInputStream(confdir())){
				urls.load(load);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			url = urls.getProperty("server.user");
			return url;
	}
	public static String pass() {
		String url;
		Properties urls = new Properties();
			try (FileInputStream load = new FileInputStream(confdir())){
				urls.load(load);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			url=urls.getProperty("server.password");
			return url;
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
		String[] level = {"administrator","operator","viewer"};
		return level;
	}
	public static String dir() {
		String dis = System.getProperty("user.dir");
		return dis;
	}
	public static boolean admin(String[] arra, String nama) {
		boolean che = ArrayUtils.contains(arra, nama);
		return che;
	}
	public static Object sqlGet(String query, int columnIndex) {
		String sql = null;
		try {
			Class.forName(Driver());
			Connection con = DriverManager.getConnection(url(),user(),pass());
			Statement stat = con.createStatement();
			String qu = query;
			ResultSet res = stat.executeQuery(qu);
			while (res.next()) {
				 sql = res.getString(columnIndex);
				
			}
			 
		}catch (SQLException | ClassNotFoundException eror) {
			
		}
		return sql;
	}
	public static String[] sqlGetColumn(String query) {
		String[] cn = {};
		
		try {
			Class.forName(Driver());
			Connection con = DriverManager.getConnection(url(),user(),pass());
			Statement stat = con.createStatement();
			ResultSet res = stat.executeQuery(query);
			ResultSetMetaData resmd = res.getMetaData();
			int col=resmd.getColumnCount();
			for (int i=1;i<col;i++) {
			cn[i]=resmd.getColumnName(i);
			
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cn;
	}
	
}
