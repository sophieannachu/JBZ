package tool;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataSourceFactory {
	private static DataSource ds;
	
	static{
		try{
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/JBZDB");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static DataSource getDataSource(){
		return ds;
	}
}
