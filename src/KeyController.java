import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import patterns.IObserver;
import patterns.ISubject;

import java.awt.event.KeyAdapter;

/** <p>This is the KeyController (KeyListener)</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
*/

public class KeyController extends KeyAdapter {
	private Presentation presentation; // Er worden commando's gegeven aan de presentatie

	Map<Integer, KeySubject> keyMap;
	
	public KeyController(Presentation p) {
		presentation = p;
		
		keyMap = new HashMap<Integer, KeySubject>();
		
		// Next slides
		bindKey(KeyEvent.VK_PAGE_DOWN, ()->presentation.nextSlide());
		bindKey(KeyEvent.VK_DOWN, ()->presentation.nextSlide());
		bindKey(KeyEvent.VK_ENTER, ()->presentation.nextSlide());
		bindKey('+', ()->presentation.nextSlide());
		
		// Previous Slides
		bindKey(KeyEvent.VK_PAGE_UP, ()->presentation.prevSlide());
		bindKey(KeyEvent.VK_UP, ()->presentation.prevSlide());
		bindKey('-', ()->presentation.prevSlide());
		
		
		bindKey('q', ()->System.exit(0));
		bindKey('Q', ()->System.exit(0));
	}
	
	public void bindKey(int key, IObserver o) {
		// TODO: Switch to Factory
		if(!keyMap.containsKey(key))
			keyMap.put(key, new KeySubject());
		
		keyMap.get(key).Attach(o);
	}

	public void keyPressed(KeyEvent keyEvent) {
		ISubject subject = keyMap.get(keyEvent.getKeyCode());
		
		if(subject == null)
			return;
		
		subject.Notify();
		
		// Previous Code
//		switch(keyEvent.getKeyCode()) {
//			case KeyEvent.VK_PAGE_DOWN:
//			case KeyEvent.VK_DOWN:
//			case KeyEvent.VK_ENTER:
//			case '+':
//				presentation.nextSlide();
//				break;
//			case KeyEvent.VK_PAGE_UP:
//			case KeyEvent.VK_UP:
//			case '-':
//				presentation.prevSlide();
//				break;
//			case 'q':
//			case 'Q':
//				System.exit(0);
//				break; // wordt nooit bereikt als het goed is
//			default:
//				break;
//		}
	}
}
