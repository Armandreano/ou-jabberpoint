package patterns.command;
import patterns.observer.ISubject;

/** <p>This is the KeyController (KeyListener)</p>
 * @author Armando Gerard
 * @version 1.8 2022/09/08 Created nextslide, that works with observer (to prevent hardcoded binding) Armando Gerard
*/

public class ChangeSlide implements ICommand {
	ISubject subject;

	public ChangeSlide(ISubject subject) {
		this.subject = subject;
	}

	@Override
	public void execute() {
		subject.notification();
	}

}
