package patterns.component.control;

import java.util.Iterator;

import patterns.command.Change;
import patterns.command.Command;
import patterns.command.wrappers.CommandData;
import patterns.command.wrappers.SlideChangeData;
import patterns.command.wrappers.SwitchToSlideData;
import presentation.Presentation;

public class SlideControl extends ControlComponent {
	
	@Override
	public void receiveCommand(Command command) {
		if(command.getClass() == Change.class) 
			changeSlide(command);
	}

	private void changeSlide(Command command) {
		CommandData rawData = command.getData();
		
		
		if(!SwitchToSlideData.class.isAssignableFrom(rawData.getClass()))
			return;
		
		Presentation presentation = getControlService().getPresentation();
		
		SwitchToSlideData switchToSlideData = (SwitchToSlideData)rawData;
		
		int newSlide = switchToSlideData.getNewSlide();
		int slideNumber = 0;
		int currentSlide = presentation.getSlideshowComposite().getCurrentSlideNumber();
		

		for (Iterator<?> iterator = presentation.getSlideshowComposite().getIterator(); iterator.hasNext();) {
			if(switchToSlideData.getClass() == SwitchToSlideData.class && slideNumber == newSlide) {
				// Switch to the new slide
				// TODO: Implement
				presentation.setSlideNumber(slideNumber);
				return;
			}
			else {
				// Prevent overflow
				if(currentSlide + newSlide >= presentation.getSlideshowComposite().getSize()
						|| currentSlide + newSlide < 0)
					return;
				else if(newSlide == -1 && slideNumber == currentSlide - 1)
				{
					// Switch to previous slide
					presentation.setSlideNumber(slideNumber);
					return;
				}
				else if(newSlide == 1 && slideNumber == currentSlide + 1) {
					// Switch to the next slide
					presentation.setSlideNumber(slideNumber);
					return;
				}
			}
			
			slideNumber++;
		}
	}
}
