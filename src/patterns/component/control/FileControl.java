package patterns.component.control;

import patterns.adapter.FileAdapter;
import patterns.command.Change;
import patterns.command.Command;
import patterns.command.wrappers.FileData;
import patterns.component.ControlService;
import patterns.factory.PresentationFactory;
import presentation.Presentation;

/** <p>This is the KeyController (KeyListener)</p>
 * @author Armando Gerard
 * @version 1.1 2022/10/30 Applied design (loading from Settings) @Armando Gerard
*/

public class FileControl extends ControlComponent {
	FileAdapter[] adapters;
	PresentationFactory factory;
	
	public void Initialize(FileAdapter[] adapters, PresentationFactory factory) {
		this.adapters = adapters;
		this.factory = factory;
	}
	
	public void receiveCommand(Command command) {
		if(!command.getClass().equals(Change.class) || 
				!command.getData().getClass().equals(FileData.class))
			return;
		
		FileData fileData = (FileData)command.getData();
		try {
			// Create the presentation
			Presentation presentation =  factory.createPresentation(fileData.getLocation());
			
			// Set presentation with the new presentation
			((ControlService)getParentComponent()).setPresentation(presentation);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
