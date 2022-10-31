import patterns.command.Command;
import patterns.component.Composite;

public class ControlService extends Composite {
	
	Presentation currentPresentation;
	
	public void receiveCommand(Command command) {
		System.out.println("HI");
	}
}
