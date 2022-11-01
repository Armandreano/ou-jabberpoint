package patterns.component;

import java.util.Iterator;
import patterns.command.Command;
import patterns.command.Select;
import patterns.command.wrappers.ClickData;
import presentation.Presentation;

public class ClickControl extends ControlComponent {
	@Override
	public void ReceiveCommand(Command command) {
		if(!command.getClass().equals(Select.class))
			return;
		
		ClickData clickData = (ClickData)command.getData();
		
		Presentation presentation = 
				((ControlService)getParentComponent()).getPresentation();
		
		SlideComposite currentSlide = presentation.getCurrentSlide();
		Iterator<?> iterator = currentSlide.getIterator();
		
		
		while (iterator.hasNext()) {
			
			
			
//			slideComposite.getIterator();
			
		}
		
	}
}
