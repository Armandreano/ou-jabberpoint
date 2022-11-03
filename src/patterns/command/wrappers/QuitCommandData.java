package patterns.command.wrappers;

/** Quitting program
 * @author Armando Gerard
 * @version 1.0 2022/11/03 re-added closing of program @Armando Gerard
*/

public class QuitCommandData extends CommandData {
	private boolean immediately;
	
	public QuitCommandData(boolean quit) {
		this.immediately = quit;
	}
	
	public boolean getImmediately() {
		return immediately;
	}
}
