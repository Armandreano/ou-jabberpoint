package patterns.command;

import patterns.command.wrappers.CommandData;

/** <p>Abort command class</p>
 * @author Armando Gerard
 * @version 1.1 2022/09/09 Added the attach method and Factory Armando Gerard
 * @version 1.2 2022/10/27 Applied design Armando Gerard
*/

public class Abort extends Command{
	public Abort(CommandData data) {
		super(data);
	}
	
	public void execute() {
//		data.Apply();
	}
	
	public void Undo() {
		
	}
}
