package expenses.manager;

import static expenses.manager.Log.logger;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.SwingUtilities;
/** 
* 
* @author Avital Chen 
* ViewModel class 
*/

public class ViewModel implements IViewModel {
	private IModel model;
	private IView view;
	@Override
	public void setIncome(String date, double amount) {
		new Thread( new Runnable() {

			@Override
			public void run() {				
				try {
					model.setIncome(date, amount);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}		
		}  ).start();
	}

	@Override
	public void setExpenses(String type, String date, double amount) {
		new Thread( new Runnable() {
			@Override
			public void run() {				
				try {
					model.setExpenses(type, date, amount);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}		
		}  ).start();

	}

	
	

	@Override
	public void getTotalExpensesByTypeCurrMonth() {
		new Thread( new Runnable() {

			@Override
			public void run() {
				
				try {
					Vector data = model.getTotalExpensesByType();
					SwingUtilities.invokeLater(new Runnable() {

						@Override
						public void run() {
							try {
								view.getExpendsesByTypeCurrYear(data);
							} catch (Exception e) {
								
								e.printStackTrace();
							}
						}});
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}  ).start();

	}

	@Override
	public void setModel(IModel m) {
		this.model = m;

	}

	@Override
	public void setView(IView v) {
		this.view = v;

	}

	@Override
	public void getTableInfo() {
		new Thread( new Runnable() {

			@Override
			public void run() {
				try {
					ArrayList<Double> infoArray = new ArrayList<Double>();
					
					infoArray.add(0, model.getTotalIncomeOverall());
					
					infoArray.add(1, model.getTotalExpensesOverall());
					infoArray.add(2, model.getTotalIncomeCurrYear());
					infoArray.add(3, model.getTotalExpensesCurrYear());
					
					
					SwingUtilities.invokeLater(new Runnable() {

						@Override
						public void run() {
							try {
								view.getTableInfo(infoArray);
							} catch (Exception e) {
								e.printStackTrace();
							}
							
						}});
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}  ).start();
		
	}

	@Override
	public void getInclomeAndExpensesByMonth() {
		new Thread( new Runnable() {

			@Override
			public void run() {
				try {
					Vector data = new Vector();
					data.add(0, model.getTotalInclomeByMonth());
					data.add(1, model.getTotalExpensesByMonth());
					SwingUtilities.invokeLater(new Runnable() {

						@Override
						public void run() {
							try {
								view.getIncomeAndExpensesByMonth(data);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}});
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}  ).start();
		
	}


	@Override
	public void getTotalExpensesByTypeCurrYear() {
		new Thread( new Runnable() {

			@Override
			public void run() {
				try {
					Vector data = model.getTotalExpensesByTypeCurrMonth();
					SwingUtilities.invokeLater(new Runnable() {

						@Override
						public void run() {
							try {
								view.getExpensesByTypeCurrMonth(data);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}});
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}  ).start();
		
		
	}

}
