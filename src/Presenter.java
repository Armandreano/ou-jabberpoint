import java.awt.event.KeyEvent;
import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import patterns.command.ICommand;
import patterns.command.Quit;
import patterns.CommandFactory;
import patterns.command.ChangeSlide;
import patterns.observer.IObserver;
import patterns.observer.ISubject;

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
*/

public class Presenter extends KeyAdapter {
	private Presentation presentation; // Er worden commando's gegeven aan de presentatie

	private Map<Integer, ICommand> keyMap;
	private List<Integer> nextButtons; 
	private List<Integer> previousButtons; 
	private List<Integer> quitButtons; 
	ChangeSlide nextSlide;
	ChangeSlide previousSlide;
	Quit quit;
	
	public Presenter(Presentation p) {
		presentation = p;
// Keymap used with Observer
//		keyMap = new HashMap<Integer, KeySubject>();
		
		
		keyMap = new HashMap<Integer, ICommand>();
		
		CommandFactory factory = CommandFactory.getFactory();
		
		nextSlide = factory.createChangeSlideCommand();
		nextSlide.attach(()->presentation.nextSlide());
		
		previousSlide = factory.createChangeSlideCommand();
		previousSlide.attach(()->presentation.prevSlide());
		
		quit = factory.createQuitCommand();
		
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
	
	public void setupCommand(List<Integer> buttons, ICommand command)
	{
		for (int i = 0; i < buttons.size(); i++) 
			keyMap.put(buttons.get(i), command);
	}
	
	public void defaultControlSetup() {
		defaultKeyBinding();
		
		setupCommand(nextButtons, nextSlide);
		setupCommand(previousButtons, previousSlide);
		setupCommand(quitButtons, quit);
		
// How the observer worked:
		// Next slides
//		bindKey(KeyEvent.VK_PAGE_DOWN, ()->presentation.nextSlide());
//		bindKey(KeyEvent.VK_DOWN, ()->presentation.nextSlide());
//		bindKey(KeyEvent.VK_ENTER, ()->presentation.nextSlide());
//		bindKey('+', ()->presentation.nextSlide());
//		
//		// Previous Slides
//		bindKey(KeyEvent.VK_PAGE_UP, ()->presentation.prevSlide());
//		bindKey(KeyEvent.VK_UP, ()->presentation.prevSlide());
//		bindKey('-', ()->presentation.prevSlide());
//		
//		// Exit presentation
//		bindKey('q', ()->System.exit(0));
//		bindKey('Q', ()->System.exit(0));
	}
	
	public void bindKey(int key, ICommand command) {
		if(keyMap.containsKey(key))
		{
			keyMap.replace(null, command);
			return;
		}
		
		keyMap.put(key, command);
		
		// Observer code
		// TODO: Switch to Factory
//		if(!keyMap.containsKey(key))
//			keyMap.put(key, o);
//		
//		keyMap.get(key).attach(o);
	}
	
	public void keyPressed(KeyEvent keyEvent) {
		
		ICommand command = keyMap.get(keyEvent.getKeyCode());
		// Sigh, C# null propagation, where art thou?
		if(command == null)
			return;
		
		command.execute();
		
		// How the observer pattern worked:
//		ISubject subject = keyMap.get(keyEvent.getKeyCode());
//		
//		if(subject == null)
//			return;
		
//		subject.notification();
	}
}
