package patterns.command;

import patterns.command.wrappers.CommandData;

/** <p>Change command class</p>
 * @author Armando Gerard
 * @version 1.8 2022/09/08 Created nextslide, that works with observer (to prevent hardcoded binding) Armando Gerard
 * @version 1.1 2022/09/09 Added the attach method and Factory Armando Gerard
 * @version 1.2 2022/10/27 Applied design
*/

public class Change extends Command{
	public Change(CommandData data) {
		super(data);
	}

	public void execute() {
//		data.apply();
	}
}
