package patterns.command;

import patterns.factory.IFactory;

public class Quit implements ICommand, IFactory<Quit> {
	@Override
	public void execute() {
		System.exit(0);
	}

	@Override
	public Quit copy() {
		return new Quit();
	}

}
