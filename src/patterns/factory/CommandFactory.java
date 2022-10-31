package patterns.factory;
import java.util.Iterator;
import java.util.Vector;
import patterns.command.Command;
import patterns.command.wrappers.CommandData;

/** Comment Start
 * 
 * @author Armando Gerard
 * @version 1.0 2022-09-09 Created for (abstract) factories Armando Gerard 
 * @version 1.1 2022-10-27 Applied design to class
 * Comment Ends */

public abstract class CommandFactory {
	private static Vector<CommandFactory> factories = new Vector<>();
	
	public static void addFactory(CommandFactory newCommandFactory) {
		if(factories == null)
			factories = new Vector<CommandFactory>(); 
		
		
		// Ensure there's always one factory of each type
		for (Iterator<CommandFactory> iterator = factories.iterator(); iterator.hasNext();) {
			CommandFactory commandFactory = (CommandFactory) iterator.next();
			
			if(commandFactory.getClass().equals(newCommandFactory.getClass()))
				return;
		}
		
		factories.add(newCommandFactory);
	}
	
	public static void addFactories(CommandFactory[] factories)
	{
		for (Iterator<?> iterator = CommandFactory.factories.iterator(); iterator.hasNext();) {
			CommandFactory commandFactory = (CommandFactory) iterator.next();
			addFactory(commandFactory);
			
		}
	}
	
	public static <T extends CommandFactory> T getFactory(Class<T> classType) {
		for (Iterator<CommandFactory> iterator = factories.iterator(); iterator.hasNext();) {
			CommandFactory factory = (CommandFactory) iterator.next();
			
			if(classType.equals(factory.getClass())) {
				@SuppressWarnings("unchecked")
				T t = (T)factory;
				return t;
			}
		}
		
		// Should probably throw an error
		return null;
	}
	
	public abstract Command createCommand(CommandData data);
}
	
//	private final ChangeSlide changeSlideCommand;
//	private final Abort quitCommand;

//	public CommandFactory() {
//		changeSlideCommand = new ChangeSlide(new Subject());
//		quitCommand = new Abort();
//	}
//
//	public ChangeSlide createChangeSlideCommand() {
//		return changeSlideCommand.copy();
//	}
//	
//	public Abort createQuitCommand() {
//		return quitCommand.copy();
//	}
//	public final static CommandFactory getFactory() {
//		if(factory == null)
//			factory = new CommandFactory();
//		
//		return factory;
//	}
	
