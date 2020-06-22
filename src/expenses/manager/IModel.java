package expenses.manager;

import java.util.ArrayList;
import java.util.Vector;

public interface IModel {
	public void setIncome(String date, double amount);
	public void setExpenses(String type, String date,double amount);
	
	public double getTotalExpensesCurrYear();
	public double getTotalIncomeCurrYear();
	public double getTotalExpensesOverall();
	public double getTotalIncomeOverall();
	
	public Vector getTotalInclomeByMonth();
	public Vector getTotalExpensesByMonth();
	public Vector getTotalExpensesByType();
	public Vector getTotalExpensesByTypeCurrMonth();
}
