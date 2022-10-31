import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sound.midi.Soundbank;

import patterns.observer.Subject;
import patterns.command.Command;
import patterns.command.wrappers.SlideChangeData;
import patterns.command.wrappers.SwitchToSlideData;
import patterns.component.SlideControl;
import patterns.factory.AbortCommandFactory;
import patterns.factory.ChangeCommandFactory;
import patterns.factory.CommandFactory;
import patterns.observer.Observer;
import patterns.command.Abort;
import patterns.command.Change;

import java.awt.event.KeyAdapter;

/** <p>This is the KeyController (KeyListener)</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 * @version 1.7 2022/09/06 Updated input to make it rebindable Armando Gerard
 * @version 1.8 2022/09/08 Added Command pattern to replace binding by Observer rebindable Armando Gerard
 * @version 1.9 2022/09/09 Added Command Factory
 * @version 1.9 2022/09/09 Optimized
*/

public class Presenter extends KeyAdapter {

	private Map<Integer, Subject> keyMap;
	private List<Integer> nextButtons; 
	private List<Integer> previousButtons; 
	private List<Integer> quitButtons; 
	Change nextSlide;
	Change previousSlide;
	private Observer nextSlideObserver;
	private Observer previousSlideObserver;
	private ControlService control;
	
	public Presenter() {
		keyMap = new HashMap<Integer, Subject>();
		
//		CommandFactory factory = CommandFactory.getFactory();
		// Depending on the context, either GUI or Presenter will apply the setting first
		
		// TODO: Move this to Settings
		CommandFactory.addFactory(new AbortCommandFactory());
		CommandFactory.addFactory(new ChangeCommandFactory());
		// TODO Remove, place in Settings!
		control = new ControlService();
		control.addComponent(new SlideControl());
		
		ChangeCommandFactory changeCommandFactory = CommandFactory.getFactory(ChangeCommandFactory.class);
		nextSlide = (Change)changeCommandFactory.createCommand(new SlideChangeData(1));
		previousSlide = (Change)changeCommandFactory.createCommand(new SlideChangeData(-1));
		// TODO implement Quit with Abort
		
		
		nextSlideObserver =()->{ control.receiveCommand(previousSlide); };
		previousSlideObserver =()->{ control.receiveCommand(previousSlide); };
//		nextSlide = factory.createChangeSlideCommand();
//		nextSlide.attach(()->presentation.nextSlide());
//		
//		previousSlide = factory.createChangeSlideCommand();
//		previousSlide.attach(()->presentation.prevSlide());
//		
//		quit = factory.createQuitCommand();
		
		defaultControlSetup();
	}
	
	private void defaultKeyBinding() {
		nextButtons = Arrays.asList(
			KeyEvent.VK_PAGE_DOWN,
			KeyEvent.VK_DOWN,
			KeyEvent.VK_ENTER,
			KeyEvent.VK_PLUS
		);
		
		previousButtons = Arrays.asList(
			KeyEvent.VK_PAGE_UP,
			KeyEvent.VK_UP,
			KeyEvent.VK_MINUS
		);
		
		quitButtons = Arrays.asList(
				KeyEvent.VK_Q
		);
	}
	
	public void setupSubject(List<Integer> buttons, Observer observer)
	{
		for (int i = 0; i < buttons.size(); i++)
		{
			Subject subject = Subject.createSubject();
			keyMap.put(buttons.get(i), subject);
			bindKey(buttons.get(i), observer);
		}
	}
	
	public void defaultControlSetup() {
		defaultKeyBinding();
		
		setupSubject(nextButtons, nextSlideObserver);
		setupSubject(previousButtons, previousSlideObserver);
//		setupCommand(quitButtons, quit);
	}
	
	public void bindKey(int key, Observer observer) {
		if(!keyMap.containsKey(key))
			return;
		
		keyMap.get(key).attach(observer);
	}
	
	public void keyPressed(KeyEvent keyEvent) {
		
		Subject subject = keyMap.get(keyEvent.getKeyCode());

		if(subject == null)
			return;
		
		subject.notification();
	}
}
