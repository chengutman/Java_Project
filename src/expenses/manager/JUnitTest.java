package expenses.manager;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Vector;
import org.junit.jupiter.api.Test;

/** 
* 
* @author Avital&Chen 
* JUnit test class.
* checking all the database queries executes
*/
class JUnitTest {
	IModel model = new Model();

	@Test
	void checkExpensesByMonth() {
		assertNotNull(model.getTotalExpensesByMonth());
	}
	
	@Test
	public void checkExpensesByType() {
		assertNotNull(model.getTotalExpensesByType());
	}
	
	@Test
	public void checkExpensesByTypeCurrMonth() {
		assertNotNull(model.getTotalExpensesByTypeCurrMonth());
	}
	
	@Test
	public void checkExpensesCurrYear() {
		assertNotNull(model.getTotalExpensesCurrYear());
	}
	
	@Test
	public void checkExpensesOverall() {
		assertNotNull(model.getTotalExpensesOverall());
	}
	
	@Test
	public void checkInclomeByMonth() {
		assertNotNull(model.getTotalInclomeByMonth());
	}
	
	@Test
	public void checkIncomeCurrYear() {
		assertNotNull(model.getTotalIncomeCurrYear());
	}
	
	@Test
	public void checkIncomeOverall() {
		assertNotNull(model.getTotalIncomeOverall());
	}

}
