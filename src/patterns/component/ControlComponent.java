package patterns.component;

import patterns.command.Command;

public class ControlComponent extends Leaf {

	@Override
	public void update() {

	}

	@Override
	public void remove() {

	}

	public void ReceiveCommand(Command command) {
		System.out.println(String.format("Received commannd %s", command.getClass().toString()));
	}
}
