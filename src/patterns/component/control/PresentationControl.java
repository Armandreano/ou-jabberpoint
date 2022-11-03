package patterns.component.control;

import patterns.command.Abort;
import patterns.command.Command;
import patterns.command.wrappers.CommandData;
import patterns.command.wrappers.QuitCommandData;
import presentation.Presentation;

/** Quitting program
 * @author Armando Gerard
 * @version 1.0 2022/11/03 re-added closing of program @Armando Gerard
*/

public class PresentationControl extends ControlComponent {

	@Override
	public void receiveCommand(Command command) {
		if(command.getClass().equals(Abort.class))
			abort(command);

	}
	
	public void abort(Command command) {
		CommandData data = command.getData();
		
		if(!QuitCommandData.class.isInstance(data))
			return;
		
		QuitCommandData quitCommandData = (QuitCommandData)data;
		
		Presentation presentation = getControlService().getPresentation();
		if(quitCommandData.getImmediately() && presentation.canQuit())
			presentation.exit(0);
		else {
			// Implement Async method (not required currently)
		}
	}

}
