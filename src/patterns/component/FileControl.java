package patterns.component;

import patterns.command.Change;
import patterns.command.Command;
import patterns.command.wrappers.FileData;

public class FileControl extends ControlComponent {
	public void ReceiveCommand(Command command) {
		if(!command.getClass().equals(Change.class))
			return;
		
		FileData fileData = (FileData)command.getData();
		
		// TODO: Call the PrseentationFactory
	}
}
