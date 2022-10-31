package patterns.factory;

import patterns.command.Change;
import patterns.command.Command;
import patterns.command.wrappers.CommandData;

/** <p>Factory command class</p>
 * @author Armando Gerard
 * @version 1.0 2022/10/27 Applied design Armando Gerard
*/

public class ChangeCommandFactory extends CommandFactory{
	@Override
	public Command createCommand(CommandData data) {
		return new Change(data);
	}
}