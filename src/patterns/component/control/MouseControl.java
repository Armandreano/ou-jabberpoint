package patterns.component.control;

import java.awt.Cursor;
import java.util.Iterator;
import patterns.command.Command;
import patterns.command.Select;
import patterns.command.wrappers.ClickData;
import patterns.command.wrappers.CommandData;
import patterns.command.wrappers.MousePositionData;
import patterns.component.content.ClickableContent;
import presentation.Presentation;
import patterns.component.ControlService;
import patterns.component.SlideComposite;
import patterns.component.Component;
import presentation.Surface;

/** <p>This is the KeyController (KeyListener)</p>
 * @author Armando Gerard
 * @version 1.1 2022/10/30 Applied design @Armando Gerard
 * @version 1.1 2022/11/01 Added click content iteration @Armando Gerard
 * @version 1.1 2022/11/02 Addedmouse position update @Armando Gerard
*/

public class MouseControl extends ControlComponent {
	Cursor hoverCursor;
	Cursor defaultCursor;
	
	public MouseControl() {
		hoverCursor = new Cursor(Cursor.HAND_CURSOR);
		defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);
	}
	
	@Override
	public void receiveCommand(Command command) {
		if(!command.getClass().equals(Select.class))
			return;
		
		CommandData data = command.getData();
		
		if(ClickData.class.equals(data.getClass()))
			processClick(command);
		else if (MousePositionData.class.isAssignableFrom(data.getClass())) {
			processMousePosition(command);
		}
	}
	
	private Iterator<?> createIterator(){
		Presentation presentation = 
				((ControlService)getParentComponent()).getPresentation();
		
		SlideComposite currentSlide = presentation.getSlideshowComposite().getCurrentSlide();
		return currentSlide.getIterator();
	}
	
	private void processClick(Command command) {
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
	
	private void processMousePosition(Command command) {
		MousePositionData mousePositionData = (MousePositionData)command.getData();
		boolean isHovering = false;
		
		Iterator<?> iterator = createIterator();
		while (iterator.hasNext()) {
			Component component = (Component)iterator.next();
			
			if(ClickableContent.class.isAssignableFrom(component.getClass())) {
				ClickableContent clickableContent = (ClickableContent)component;
				
				if(clickableContent.contains(mousePositionData.getX(), mousePositionData.getY())) {
					isHovering = true;
					break;
				}
			}
		}
		
		// Set hand cursor
		if(isHovering)
			Surface.applyCursor(hoverCursor);
		else
		// Set default cursor
			Surface.applyCursor(defaultCursor);
	}
	
	public void SetCursors(Cursor defaultCursor, Cursor hoverCursor){
		this.defaultCursor = defaultCursor;
		this.hoverCursor = hoverCursor;
	}
}
