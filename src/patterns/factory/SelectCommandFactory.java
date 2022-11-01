package patterns.factory;

import patterns.command.Command;
import patterns.command.Select;
import patterns.command.wrappers.CommandData;

/** <p>Done</p>
 * @author Armando Gerard
 * @version 1.1 2022/10/?? Applied design  @Armando Gerard
*/
public class SelectCommandFactory extends CommandFactory {

	@Override
	public Command createCommand(CommandData data) {
		return new Select(data);
	}

}
