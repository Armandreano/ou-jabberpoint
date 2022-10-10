package patterns;

import patterns.command.ChangeSlide;
import patterns.command.Quit;
import patterns.observer.Subject;

/** Comment Start
 * 
 * @author Armando Gerard
 * @version 1.0 2022-09-09 Created for (abstract) factories Armando Gerard 
 * Comment Ends */

public class CommandFactory {
	// Singleton
	private static CommandFactory factory;
	
	private final ChangeSlide changeSlideCommand;
	private final Quit quitCommand;

	public CommandFactory() {
		changeSlideCommand = new ChangeSlide(new Subject());
		quitCommand = new Quit();
	}

	public ChangeSlide createChangeSlideCommand() {
		return changeSlideCommand.copy();
	}
	
	public Quit createQuitCommand() {
		return quitCommand.copy();
	}
	
	public final static CommandFactory getFactory() {
		if(factory == null)
			factory = new CommandFactory();
		
		return factory;
	}
}
