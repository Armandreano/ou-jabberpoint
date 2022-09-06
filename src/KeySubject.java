import java.util.ArrayList;

import patterns.IObserver;
import patterns.ISubject;

public class KeySubject implements ISubject {

	ArrayList<IObserver> observers = new ArrayList<IObserver>();
	
	public void Clear()
	{
		observers.clear();
	}
	
	@Override
	public void Attach(IObserver o) {
		observers.add(o);
	}

	@Override
	public void Detach(IObserver o) {
		observers.remove(o);
	}

	@Override
	public void Notify() {
		for(IObserver o : observers){
			o.Update();
		}
	}
}
