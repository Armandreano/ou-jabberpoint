package patterns.component.control;

import patterns.command.Command;
import patterns.component.Leaf;

public abstract class ControlComponent extends Leaf {

	@Override
	public void update() {

	}

	@Override
	public void remove() {

	}

	public abstract void receiveCommand(Command command);
}
