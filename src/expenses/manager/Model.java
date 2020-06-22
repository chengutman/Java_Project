package expenses.manager;
import static  expenses.manager.Log.logger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
/** 
* 
* @author Avital Chen 
* Model Class that implements the IModel Interface
* In the Class we call the database 
*/
public class Model implements IModel {
		public Derbydb db;
		private String currMonth;
		private String currYear;
	public Model() {
		synchronized(logger){
			 logger.info("new Model");
		 }
		db = new Derbydb();
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		currYear = String.valueOf(year);
		currMonth = String.valueOf(month+1);
	}
	/** 
	* 
	* setIncome(String date, double amount)
	 *  Insert an income to the income table in the database
	 *  @param date the date of the income
	 *  @param amount the amount of the income
	 */
	@Override

	public void setIncome(String date, double amount) {
		String incomeAmountAsString = String.valueOf(amount);
		String sql = "insert into INCOME "+ "(date, amount) " + "values ('" + date+ "'," +incomeAmountAsString+ ")";
		try
        {
            db.statement = db.connection.createStatement();
            db.statement.executeUpdate(sql);
            synchronized(logger){
            	logger.info("insert income: " + sql);
            }
            
            db.statement.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
	}
	/** 
	* 
	* setExpenses(String type, String date,double amount)
	 *  Insert an expense to the expenses table in the database
	 *  @param type the type of the expense 
	 *  @param date the date of the expense
	 *  @param amount the amount of the expense
	 */
	@Override
	public void setExpenses(String type, String date,double amount) {
		String expensesAmountAsString = String.valueOf(amount);
		String sql = "insert into EXPENSES "+ "(type, date, amount) " + "values ('"+type+"', '"+ date+"',"+ expensesAmountAsString +")";
		try
        {
            db.statement = db.connection.createStatement();
            
            db.statement.executeUpdate(sql);
            synchronized(logger){
            	logger.info("insert Expenses: " + sql);
            }
            db.statement.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }

	}
	/** 
	* 
	* getTotalExpensesCurrYear()
	 *  @return the sum of all the expenses this current year
	 */

	@Override
	public double getTotalExpensesCurrYear() {
		double returnedResult = 0.0;
		String sql = "SELECT SUM(amount), YEAR(date) FROM EXPENSES "
						+ "WHERE YEAR(date) = " + currYear 
						+ " GROUP BY YEAR(date)";
		try
        {
            db.statement = db.connection.createStatement();
            synchronized(logger){
            	logger.info("execute query: " + sql);
            }
            ResultSet results = db.statement.executeQuery(sql);
            while(results.next())
            {
            	returnedResult = results.getDouble(1);
            	  synchronized(logger){
                  	logger.info("query result: " + returnedResult);
                  }
            }
            results.close();
            db.statement.close();  
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
		 return returnedResult;
		
	}
	/** 
	* 
	* getTotalIncomeCurrYear()
	 *  @return the sum of all the income this current year
	 */
	@Override
	public double getTotalIncomeCurrYear() {

		double returnedResult = 0.0;
		String sql = "SELECT SUM(amount), YEAR(date) FROM Income "
				+  "WHERE YEAR(date) = " + currYear 
				+ " GROUP BY YEAR(date)";
		try
        {
            db.statement = db.connection.createStatement();
            synchronized(logger){
            	logger.info("execute query: " + sql);
            }
            ResultSet results = db.statement.executeQuery(sql);
            while(results.next())
            {
                returnedResult = results.getDouble(1);
                synchronized(logger){
                  	logger.info("query result: " + returnedResult);
                  }
            }
            results.close();
            db.statement.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
		return returnedResult;
	}
	/** 
	* 
	* getTotalExpensesOverall()
	 *  @return the sum of all the expenses overall
	 */
	@Override
	public double getTotalExpensesOverall() {
		double returnedResult = 0.0;
		String sql = "SELECT SUM(amount) FROM EXPENSES ";
		try
        {
            db.statement = db.connection.createStatement();
            synchronized(logger){
            	logger.info("execute query: " + sql);
            }
            ResultSet results = db.statement.executeQuery(sql);
            while(results.next())
            {
            	returnedResult = results.getDouble(1);
            	synchronized(logger){
                  	logger.info("query result: " + returnedResult);
                  }
            }
            results.close();
            db.statement.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
		return returnedResult;
	}
	/** 
	* 
	* getTotalIncomeOverall()
	 *  @return the sum of all the income overall
	 */
	@Override
	public double getTotalIncomeOverall() {
		double returnedResult = 0.0;
		String sql = "SELECT SUM(amount) FROM INCOME ";
		try
        {
            db.statement = db.connection.createStatement();
            synchronized(logger){
            	logger.info("execute query: " + sql);
            }
            ResultSet results = db.statement.executeQuery(sql);
            while(results.next())
            {
            	returnedResult = results.getDouble(1);
            	synchronized(logger){
                  	logger.info("query result: " + returnedResult);
                  }
            }
            results.close();
            db.statement.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
		System.out.println(returnedResult);
		return returnedResult;
	}
	/** 
	* 
	* getTotalInclomeByMonth()
	 *  @return the sum of income grouped by month this year
	 */
	@Override
	public Vector getTotalInclomeByMonth() {
		int index = 0;
		Vector<Vector> returnedResult = new Vector<Vector>();
		
		String sql = "SELECT SUM(amount), YEAR(date), MONTH(date) FROM INCOME "
				+ "WHERE YEAR(date) = " + currYear 
				+ " GROUP BY YEAR(date), MONTH"
				+ "(date)";
		try
        {
            db.statement = db.connection.createStatement(); 
            synchronized(logger){
            	logger.info("execute query: " + sql);
            }
            ResultSet results = db.statement.executeQuery(sql);
            while(results.next())
            {
            	Vector v = new Vector();
            	v.add(0, results.getDouble(1));
            	v.add(1, results.getString(3));
            	returnedResult.add(index, v);
            	++index;
            }
            results.close();
            db.statement.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
		synchronized(logger){
          	logger.info("query result: " + returnedResult);
          }
		return returnedResult;
	}
	/** 
	* 
	* getTotalExpensesByMonth()
	 *  @return the sum of expenses grouped by month this year
	 */
	@Override
	public Vector getTotalExpensesByMonth() {
		int index = 0;
		Vector<Vector> returnedResult = new Vector<Vector>();
		String sql = "SELECT SUM(amount), YEAR(date), MONTH(date) FROM EXPENSES "
				+ "WHERE YEAR(date) = " + currYear 
				+ " GROUP BY YEAR(date), MONTH"
				+ "(date)";
		try
        {
            db.statement = db.connection.createStatement();
            synchronized(logger){
            	logger.info("execute query: " + sql);
            }
            ResultSet results = db.statement.executeQuery(sql);
            while(results.next())
            {
                Vector v = new Vector();
                v.add(0, results.getDouble(1));
                v.add(1, results.getString(3));
                
                returnedResult.add(index, v);
            }
            results.close();
            db.statement.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
		
		synchronized(logger){
          	logger.info("query result: " + returnedResult);
        }
		return returnedResult;
	}
	/** 
	* 
	* getTotalExpensesByType()
	 *  @return the sum of expenses grouped by type this year
	 */
	@Override
	public Vector getTotalExpensesByType() {
		
		int index = 0;
		Vector<Vector> returnedResult = new Vector<Vector>();
		String sql = "SELECT SUM(amount), YEAR(date), type FROM EXPENSES "
				+ "WHERE YEAR(date) = " + currYear
				+ " GROUP BY YEAR(date), type";
		try
        {
            db.statement = db.connection.createStatement();
            synchronized(logger){
            	logger.info("execute query: " + sql);
            }
            ResultSet results = db.statement.executeQuery(sql);
            while(results.next())
            {
            	 Vector v = new Vector();
                 v.add(0, results.getDouble(1));
                 v.add(0, results.getString(3));
                 
                 returnedResult.add(index, v);
                 ++index;
            }
            results.close();
            db.statement.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
		synchronized(logger){
          	logger.info("query result: " + returnedResult);
        }
		return returnedResult;
	}
	/** 
	* 
	* getTotalExpensesByTypeCurrMonth()
	 *  @return the sum of expenses grouped by type this month
	 */
	@Override
	public Vector getTotalExpensesByTypeCurrMonth() {
		int index = 0;
		Vector<Vector> returnedResult = new Vector<Vector>();
		String sql = "SELECT SUM(amount), YEAR(date),MONTH(date), type FROM EXPENSES "
				+ "WHERE YEAR(date) = " + currYear +" AND MONTH(date) = " + currMonth
				+ " GROUP BY YEAR(date) ,MONTH(date), type";
		try
        {
            db.statement = db.connection.createStatement();
            synchronized(logger){
            	logger.info("execute query: " + sql);
            }
            ResultSet results = db.statement.executeQuery(sql);
            while(results.next())
            {
            	Vector v = new Vector();
                v.add(0, results.getDouble(1));
                v.add(0, results.getString(4));
                
                returnedResult.add(index, v);
                ++index;
            }
            results.close();
            db.statement.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
		synchronized(logger){
          	logger.info("query result: " + returnedResult);
        }
		return returnedResult;
	}
}
