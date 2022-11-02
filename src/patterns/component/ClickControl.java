package patterns.component;

import java.util.Iterator;
import patterns.command.Command;
import patterns.command.Select;
import patterns.command.wrappers.ClickData;
import patterns.command.wrappers.CommandData;
import patterns.command.wrappers.MousePositionData;
import patterns.component.content.ClickableContent;
import presentation.Presentation;
import presentation.Surface;

/** <p>This is the KeyController (KeyListener)</p>
 * @author Armando Gerard
 * @version 1.1 2022/10/30 Applied design @Armando Gerard
 * @version 1.1 2022/11/01 Added click content iteration @Armando Gerard
 * @version 1.1 2022/11/02 Addedmouse position update @Armando Gerard
*/

public class ClickControl extends ControlComponent {
	@Override
	public void ReceiveCommand(Command command) {
		if(!command.getClass().equals(Select.class))
			return;
		
		CommandData data = command.getData();
		
		if(ClickData.class.equals(data.getClass()))
			ProcessClick(command);
		else if (MousePositionData.class.isAssignableFrom(data.getClass())) {
			
		}
	}
	
	private Iterator<?> createIterator(){
		Presentation presentation = 
				((ControlService)getParentComponent()).getPresentation();
		
		SlideComposite currentSlide = presentation.getCurrentSlide();
		return currentSlide.getIterator();
	}
	
	void ProcessClick(Command command) {
		ClickData clickData = (ClickData)command.getData();
		
		Iterator<?> iterator = createIterator();
		
		while (iterator.hasNext()) {
			Component component = (Component)iterator.next();
			
			if(ClickableContent.class.isAssignableFrom(component.getClass())) {
				ClickableContent clickableContent = (ClickableContent)component;
				clickableContent.processClick(clickData.getX(), clickData.getY());
			}
		}
	}
	
	void ProcessMousePosition(Command command) {
		MousePositionData mousePositionData = (MousePositionData)command.getData();
		
		Iterator<?> iterator = createIterator();
		while (iterator.hasNext()) {
			Component component = (Component)iterator.next();
			
			if(ClickableContent.class.isAssignableFrom(component.getClass())) {
				ClickableContent clickableContent = (ClickableContent)component;
				
//				if(clickableContent.isInExtent(mousePositionData.getX(), mousePositionData.apply())) {
//					mousePositionData.handleSubject(true);
//				}
				// TODO: set hand cursor
			}
			
			// TODO: set default cursor
		}
	}
}
