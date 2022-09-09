package patterns.command;
import patterns.factory.IFactory;
import patterns.observer.IObserver;
import patterns.observer.ISubject;
import patterns.observer.KeySubject;

/** <p>This is the KeyController (KeyListener)</p>
 * @author Armando Gerard
 * @version 1.8 2022/09/08 Created nextslide, that works with observer (to prevent hardcoded binding) Armando Gerard
 * @version 1.1 2022/09/09 Added the attach method and Factory Armando Gerard
*/

public class ChangeSlide implements ICommand, IFactory<ChangeSlide>{
	ISubject subject;

	public ChangeSlide(ISubject subject) {
		this.subject = subject;
	}
	
	public void attach(IObserver o) {
		subject.attach(o);
	}

	@Override
	public void execute() {
		subject.notification();
	}

	@Override
	public ChangeSlide copy() {
		return new ChangeSlide(new KeySubject());
	}

}
