package expenses.manager;

import java.util.ArrayList;
import java.util.Vector;

public interface IView {
	public void getTableInfo(ArrayList<Double> infoArray);
	public void getExpensesByTypeCurrMonth( Vector data);
	public void getExpendsesByTypeCurrYear(Vector data);
	public void getIncomeAndExpensesByMonth(Vector data);
	public void setViewModel(IViewModel ob);
	public void start();
	
}
