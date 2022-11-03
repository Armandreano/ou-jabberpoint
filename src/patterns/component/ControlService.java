package patterns.component;
import java.util.Iterator;

import patterns.command.Command;
import patterns.component.control.ControlComponent;
import presentation.Presentation;
import presentation.Surface;

/** <p>This is the KeyController (KeyListener)</p>
 * @author Armando Gerard
 * @version 1.1 2022/10/30 Applied design (loading from Settings) @Armando Gerard
 * @version 1.2 2022/11/02 Removed debug spam @Armando Gerard
*/
public class ControlService extends Composite {
	Presentation presentation;
	
	public void receiveCommand(Command command) {
		for (Iterator<?> iterator = children.iterator(); iterator.hasNext();) {
			ControlComponent component = (ControlComponent) iterator.next();
			
			component.receiveCommand(command);
		}
	}
	
	public Presentation getPresentation() {
		return presentation;
	}
	
	public void setPresentation(Presentation presentation) {
		Surface.setPresentation(presentation);
		this.presentation = presentation;
	}
}
