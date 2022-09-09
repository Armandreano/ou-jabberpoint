package patterns;

import patterns.command.ChangeSlide;
import patterns.command.Quit;
import patterns.observer.KeySubject;

public class CommandFactory {
	private static CommandFactory factory;
	
	private final ChangeSlide changeSlideCommand;
	private final Quit quitCommand;

	public CommandFactory() {
		changeSlideCommand = new ChangeSlide(new KeySubject());
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
