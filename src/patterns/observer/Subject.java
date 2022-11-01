package patterns.observer;

import java.util.ArrayList;

import patterns.factory.Prototype;

/** Comment Start
 * 
 * @author Armando Gerard
 * @version 1.0 2022-09-06 Created to have listeners Armando Gerard
 * @version 1.1 2022-09-08 Updated to match Java naming Convention Armando Gerard
 * @version 1.2 2022-09-26 Removed interface
 * Comment Ends */

public class Subject implements Prototype<Subject> {
	
	public static Subject subject;
	
	ArrayList<Observer> observers = new ArrayList<Observer>();
	
	public void clear()
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

	@Override
	public Subject copy() {
		return new Subject();
	}
	
	public static Subject createSubject() {
		if(subject == null)
			subject = new Subject();
		
		return subject.copy();
	}
}
