package patterns.component.control;
import patterns.command.Command;
import patterns.component.Leaf;

/** <p>Done</p>
 * @author Armando Gerard
 * @version 1.1 2022/10/?? Applied design  @Armando Gerard
*/
public abstract class ControlComponent extends Leaf {
	public abstract void receiveCommand(Command command);
}
