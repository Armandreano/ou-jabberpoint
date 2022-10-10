package patterns.command;

import patterns.factory.Prototype;
import patterns.observer.Observer;
import patterns.observer.Subject;

/** <p>This is the KeyController (KeyListener)</p>
 * @author Armando Gerard
 * @version 1.8 2022/09/08 Created nextslide, that works with observer (to prevent hardcoded binding) Armando Gerard
 * @version 1.1 2022/09/09 Added the attach method and Factory Armando Gerard
*/

public class ChangeSlide implements Command, Prototype<ChangeSlide>{
	Subject subject;

	public ChangeSlide(Subject subject) {
		this.subject = subject;
	}
	
	public void attach(Observer o) {
		subject.attach(o);
	}

	@Override
	public void execute() {
		subject.notification();
	}

	@Override
	public ChangeSlide copy() {
		return new ChangeSlide(new Subject());
	}

}
