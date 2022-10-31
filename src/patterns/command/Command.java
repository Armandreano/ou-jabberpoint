package patterns.command;

import patterns.command.wrappers.CommandData;

/** Comment Start
 * 
 * @author Armando Gerard
 * @version 1.0 2022-09-08 Created command pattern to decouple events from Input Armando Gerard
 * @version 1.1 2022-10-27 Applied design
 * Comment Ends */

public abstract class Command{
	protected CommandData data; 
	public  abstract void execute();
	public void undo() {}
	
	public Command(CommandData data){
		this.data = data;
	}
	
	public CommandData getData() {
		return data;
	}
	
	public CommandData setData() {
		return data;
	}
}