package tes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang3.ArrayUtils;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.HTMLRenderOption;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.eclipse.birt.report.engine.api.ReportEngine;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class SqlUrl {
	
	public static void runReport(String reportname) {
		 try {
	            // Initialize BIRT Engine
	            EngineConfig config = new EngineConfig();
	            IReportEngine engine = new ReportEngine(config);

	            // Create a report task
	            IReportRunnable reportRunnable = engine.openReportDesign(System.getProperty("user.dir")+confloader("server.reportdir")+reportname);
	            IRunAndRenderTask task = engine.createRunAndRenderTask(reportRunnable);
	            // Define render options
	            HTMLRenderOption options = new HTMLRenderOption();
	            options.setOutputFileName("output/report.html");
	            options.setOutputFormat("html");
	            task.setRenderOption(options);

	            // Run the report
	            task.run();
	            task.close();
	        } catch (Exception e) {
	        	JOptionPane.showMessageDialog(null, e+reportname);
	            e.printStackTrace();
	        }
	}
	public static List<Map<String, Object>> tblConvert(TableModel model) {
		List<Map<String, Object>> dataList = new ArrayList<>();
		int columnCount = model.getColumnCount();
		int rowCount = model.getRowCount();
		for (int row=0;row<rowCount;row++){
			Map<String,Object> rowdata = new HashMap<>();
			for(int col = 0; col<columnCount;col++) {
				String columnName = model.getColumnName(col);
				Object cellData = model.getValueAt(row, col);
				rowdata.put(columnName, cellData);
			}
			dataList.add(rowdata);
		}
		return dataList;
	}
	public static void Excel(JTable table, File file) {
	        try {
	        	TableModel md = table.getModel();
	        	FileWriter o = new FileWriter(file);
	           for (int i=0; i<md.getColumnCount();i++) {
	        	   o.write(md.getColumnName(i)+",");
	           }
	           o.write("\n");
	           for (int s=0;s<md.getRowCount();s++) {
	        	   for(int i=0;i<md.getColumnCount();i++) {
	        		   o.write(md.getValueAt(s, i).toString()+",");
	        	   }
	        	   o.write("\n");
	           }
	          
	           o.close();
	        } catch (Exception ex) {
	        	JOptionPane.showMessageDialog(null, ex);
	            ex.printStackTrace();
	        }}
	
	public static String Driver() {
		String driver = "com.mysql.cj.jdbc.Driver";
		return driver;
	}
	public static String confdir() {
		String config = System.getProperty("user.dir")+"/config/hrd.config";
		return config;
	}
	public static String url(){
		String url;
			url = "jdbc:mysql://"+confloader("server.url")+":"+confloader("server.port")+"/"+confloader("server.database");
			
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
			url = confloader("server.user");
			return url;
	}
	public static String pass() {
		String url;
			url=confloader("server.password");
			return url;
	}
	public static String confloader(String confname) {
		String conf;
		Properties confs = new Properties();
			try (FileInputStream load = new FileInputStream(confdir())){
				confs.load(load);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conf = confs.getProperty(confname);
			return conf;
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
		Object sql = null;
		try {
			Class.forName(Driver());
			Connection con = DriverManager.getConnection(url(),user(),pass());
			Statement stat = con.createStatement();
			String qu = query;
			ResultSet res = stat.executeQuery(qu);
			while(res.next()) {
				sql=res.getObject(columnIndex);
			}
			
		}catch (SQLException | ClassNotFoundException eror) {
			JOptionPane.showMessageDialog(null, eror);
		}
		return sql;
	}
	public static String[] sqlGetColumn(String query) {
		String[] cn = null;
		int col = 0;
		try {
			Class.forName(Driver());
			Connection con = DriverManager.getConnection(url(),user(),pass());
			Statement stat = con.createStatement();
			ResultSet res = stat.executeQuery(query);
			ResultSetMetaData resmd = res.getMetaData();
			col=resmd.getColumnCount();
			cn=new String[col];
			for (int i=0;i<col;i++) {
			cn[i]=resmd.getColumnName(i+1);
			
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException | java.lang.ArrayIndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cn;
	}
	public static void importToXML(JTable tabla, File file) throws FileNotFoundException {
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element root = document.createElement("Table");
            document.appendChild(root);
            for (int ro=0;ro<tabla.getRowCount();ro++) {
            	Element row =document.createElement("row");
            	root.appendChild(row);
            for (int c = 0;c<tabla.getColumnCount();c++) {
            	Element column = document.createElement(tabla.getColumnName(c));
            	row.appendChild(column);
            	column.appendChild(document.createTextNode(tabla.getValueAt(ro, c).toString()));
            }}
           

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);

            StreamResult streamResult = new StreamResult(file);
            transformer.transform(domSource, streamResult);
        } catch (ParserConfigurationException | TransformerException pce) {
            JOptionPane.showMessageDialog(null, "Error: " + pce.toString());
        }
    }
	
}
