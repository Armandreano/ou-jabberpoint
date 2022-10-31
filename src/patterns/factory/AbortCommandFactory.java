package patterns.factory;

import patterns.command.Abort;
import patterns.command.Command;
import patterns.command.wrappers.CommandData;

/** <p>Factory command class</p>
 * @author Armando Gerard
 * @version 1.0 2022/10/27 Applied design Armando Gerard
*/

public class AbortCommandFactory extends CommandFactory{
	@Override
	public Command createCommand(CommandData data) {
		// TODO Auto-generated method stub
		return new Abort(data);
	}
}
