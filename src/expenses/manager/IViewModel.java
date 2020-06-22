package expenses.manager;

import java.util.Vector;

public interface IViewModel {
	public void setIncome(String date, double amount);
	public void setExpenses(String type, String date,double amount);
	public void getTableInfo();
	public void getInclomeAndExpensesByMonth();
	public void getTotalExpensesByTypeCurrMonth();
	public void getTotalExpensesByTypeCurrYear();
	public void setModel(IModel m);
	public void setView(IView v);
}
