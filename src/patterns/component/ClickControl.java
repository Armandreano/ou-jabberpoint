package patterns.component;

import java.util.Iterator;
import patterns.command.Command;
import patterns.command.Select;
import patterns.command.wrappers.ClickData;
import patterns.component.content.ClickableContent;
import presentation.Presentation;

/** <p>This is the KeyController (KeyListener)</p>
 * @author Armando Gerard
 * @version 1.1 2022/10/30 Applied design @Armando Gerard
 * @version 1.1 2022/11/01 Added click content iteration @Armando Gerard
*/

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
			Component component = (Component)iterator.next();
			
			if(ClickableContent.class.isAssignableFrom(component.getClass())) {
				ClickableContent clickableContent = (ClickableContent)component;
				clickableContent.processClick(clickData.getX(), clickData.getY());
			}
		}
	}
}
