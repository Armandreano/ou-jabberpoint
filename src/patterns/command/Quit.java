package patterns.command;

import patterns.factory.Prototype;

public class Quit implements Command, Prototype<Quit> {
	@Override
	public void execute() {
		System.exit(0);
	}

	@Override
	public Quit copy() {
		return new Quit();
	}

}
