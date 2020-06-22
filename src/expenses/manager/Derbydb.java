package expenses.manager;
import static  expenses.manager.Log.logger;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;

public class Derbydb {
	public static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	public static String protocol = "jdbc:derby:expensesDB;create=true";
	public Connection connection = null;
    public Statement statement = null;
    
    public Derbydb() {
    	try
        {
			Class.forName(driver);
			connection = DriverManager.getConnection(protocol);
			 synchronized(logger){
				 logger.info("Derbydb Connection established");
			 }
			statement = connection.createStatement();
			tablesExist(connection);
        }
        catch (Exception except)
        {
        	 synchronized(logger){
        		 logger.error("Derbydb connection failed");
        	 }
        	if(connection!=null) try{connection.close();}catch(Exception e){}
        }  
    }
    
public void tablesExist(Connection conn) {
		
	int numRows = 0;
	String sql;
	try {
		DatabaseMetaData dbmd = conn.getMetaData();
		ResultSet rs = dbmd.getTables( null, null, "EXPENSES", null);
		 synchronized(logger){
			 logger.info("Derbydb EXPENSES Table Generate if not exists");
		 }
        while( rs.next() ) ++numRows;
		if(numRows == 0) {
			statement = connection.createStatement();
			sql = "CREATE TABLE EXPENSES " +
	                "(ID int not null generated always as identity, " +
	                " type VARCHAR(255), " + 
	                " date DATE, " + 
	                " amount DOUBLE, " + 
	                " PRIMARY KEY ( ID ))"; 
			statement.execute(sql);
		}
		numRows = 0;
		rs = dbmd.getTables( null, null, "INCOME", null);
		synchronized(logger){
			 logger.info("Derbydb INCOME Table Generate if not exists");
		 }
        while( rs.next() ) ++numRows;
		if(numRows == 0) {
			sql = "CREATE TABLE INCOME " +
					  "(ID int not null generated always as identity, " +
		              " date DATE, " + 
		              " amount DOUBLE, " + 
		              " PRIMARY KEY ( ID ))"; 
			statement.execute(sql);
		}
		
	} catch (Exception except)
    {
		
        except.printStackTrace();
    }	
}

}
