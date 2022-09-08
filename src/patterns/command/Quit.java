package patterns.command;

public class Quit implements ICommand {

	@Override
	public void execute() {
		System.exit(0);
	}

}
