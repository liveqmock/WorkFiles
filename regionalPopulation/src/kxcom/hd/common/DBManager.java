package kxcom.hd.common;

/**
 * 
 */

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.logicalcobwebs.proxool.configuration.JAXPConfigurator;

/**
 */
public class DBManager {

	private static DBManager instance;

	private static final Log log = LogFactory.getLog(DBManager.class);

	private DBManager() throws Exception {
		init();
	}
	/**
	 * 
	 * 功能描述: 
	 * @throws Exception
	 * @author chenhui / 2009-12-23 下午02:19:20
	 */
	private void init() throws Exception {
		
		//初始化数据库连接配置参数
		InputStream in=this.getClass().getResourceAsStream("/proxool.xml");
		Reader reader = new InputStreamReader(in);


		JAXPConfigurator.configure(reader,false);
		log.info("Finash DBManager");
		return;
	}

	/**
	 * 
	 * 方法用途和描述: 单例模式获取 DBManager 对象实例
	 */
	public static synchronized DBManager getInstance() throws Exception {
		if (instance == null)
			instance = new DBManager();
		return instance;
	}


	/**
	 * 
	 * 功能描述: 建立数据库别名为传入参数的连接池
	 */
	public Connection getConnection(String DbAlias) throws Exception {

		Connection conn = DriverManager.getConnection("proxool."+DbAlias);
		return conn;
	}
	/**
	 * 
	 * 功能描述: 建立默认数据库别名的连接池
	 */
	public Connection getConnection() throws Exception {

		Connection conn = DriverManager.getConnection("proxool.proxool");
		return conn;
	}
	/**
	 * 
	 * 功能描述: 归还连接到连接池
	 */
	public void freeConnection(Connection conn){

		try{
			if(conn!=null){
				conn.close();
			}

		}catch(Exception ex){
			log.error("freeConnError:",ex);
		}
	}
	
	public static void main(String[] args){
		try {
			Connection conn = DBManager.getInstance().getConnection("proxool");
			System.out.println(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
