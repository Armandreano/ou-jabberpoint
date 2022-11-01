package patterns.component;

import patterns.adapter.FileAdapter;
import patterns.command.Change;
import patterns.command.Command;
import patterns.command.wrappers.FileData;
import patterns.factory.PresentationFactory;
import presentation.Presentation;

public class FileControl extends ControlComponent {
	FileAdapter[] adapters;
	PresentationFactory factory;
	
	public FileControl(FileAdapter[] adapters, PresentationFactory factory) {
		this.adapters = adapters;
		this.factory = factory;
	}
	
	public void ReceiveCommand(Command command) {
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
