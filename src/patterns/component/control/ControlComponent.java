package patterns.component.control;
import patterns.command.Command;
import patterns.component.Leaf;

public abstract class ControlComponent extends Leaf {
	public abstract void receiveCommand(Command command);
}
