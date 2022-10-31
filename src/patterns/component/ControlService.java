package patterns.component;
import java.util.Iterator;

import patterns.command.Command;
import patterns.component.Composite;
import presentation.SimplePresentation;

public class ControlService extends Composite {
	SimplePresentation currentPresentation;
	
	public void receiveCommand(Command command) {
		System.out.println("test");
		
		for (Iterator<?> iterator = children.iterator(); iterator.hasNext();) {
			ControlComponent component = (ControlComponent) iterator.next();
			
			component.ReceiveCommand(command);
			
			System.out.println(String.format("Sending %s command to %s",
					command.getClass().toString(),
					component.getClass().toString()));
		}
	}
	
	public SimplePresentation getPresentation() {
		return currentPresentation;
	}
}
