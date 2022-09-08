package patterns.observer;
import java.util.ArrayList;

import patterns.observer.IObserver;
import patterns.observer.ISubject;

/** Comment Start
 * 
 * @author Armando Gerard
 * @version 1.0 2022-09-06 Created to decoupling of code and input Armando Gerard 
 * @version 1.1 2022-09-08 Updated to match Java naming Convention Armando Gerard
 * Comment Ends */

public class KeySubject implements ISubject {

	ArrayList<IObserver> observers = new ArrayList<IObserver>();
	
	public void Clear()
	{
		observers.clear();
	}
	
	@Override
	public void attach(IObserver o) {
		observers.add(o);
	}

	@Override
	public void detach(IObserver o) {
		observers.remove(o);
	}

	@Override
	public void notification() {
		for(IObserver o : observers){
			o.update();
		}
	}
}
