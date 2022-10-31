package patterns.factory;

import patterns.command.Command;
import patterns.command.Select;
import patterns.command.wrappers.CommandData;

public class SelectCommandFactory extends CommandFactory {

	@Override
	public Command createCommand(CommandData data) {
		return new Select(data);
	}

}
