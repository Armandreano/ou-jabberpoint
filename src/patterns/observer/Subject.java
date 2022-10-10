package patterns.observer;

import java.util.ArrayList;

/** Comment Start
 * 
 * @author Armando Gerard
 * @version 1.0 2022-09-06 Created to have listeners Armando Gerard
 * @version 1.1 2022-09-08 Updated to match Java naming Convention Armando Gerard
 * @version 1.2 2022-09-26 Removed interface
 * Comment Ends */

public class Subject {
	ArrayList<Observer> observers = new ArrayList<Observer>();
	
	public void Clear()
	{
		observers.clear();
	}
	
	public void attach(Observer o) {
		observers.add(o);
	}

	public void detach(Observer o) {
		observers.remove(o);
	}

	public void notification() {
		for(Observer o : observers){
			o.update();
		}
	}
}
