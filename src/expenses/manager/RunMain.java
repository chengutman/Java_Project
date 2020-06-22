package expenses.manager;
import static expenses.manager.Log.logger;

import javax.swing.SwingUtilities;

public class RunMain {
	public static void main(String[] args) {
		SwingUtilities.invokeLater( new Runnable() {

			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
				 
				IModel m = new Model();
				
				IView v = new View();
				v.start();
				synchronized(logger){
					 logger.info("new ViewModel");
				 }
				IViewModel vm = new ViewModel();
				v.setViewModel(vm);
				vm.setModel(m);	
				vm.setView(v);
			}});
		
	}
	
}
