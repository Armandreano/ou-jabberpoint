package patterns.component;
import java.util.Iterator;

import patterns.command.Command;
import patterns.component.Composite;
import presentation.Presentation;
import presentation.SimplePresentation;

/** <p>This is the KeyController (KeyListener)</p>
 * @author Armando Gerard
 * @version 1.1 2022/10/30 Applied design (loading from Settings) @Armando Gerard
*/
public class ControlService extends Composite {
	Presentation presentation;
	
	public void receiveCommand(Command command) {
		for (Iterator<?> iterator = children.iterator(); iterator.hasNext();) {
			ControlComponent component = (ControlComponent) iterator.next();
			
			component.ReceiveCommand(command);
			
			System.out.println(String.format("Sending %s command to %s",
					command.getClass().toString(),
					component.getClass().toString()));
		}
	}
	
	public Presentation getPresentation() {
		return presentation;
	}
	
	public void setPresentation(Presentation presentation) {
		this.presentation = presentation;
	}
}
