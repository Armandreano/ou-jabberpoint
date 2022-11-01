package patterns.command.wrappers;

/** <p>Done</p>
 * @author Armando Gerard
 * @version 1.1 2022/10/?? Applied design @Armando Gerard
*/
public class ClickData extends CommandData {
	int x, y;
	
	public ClickData(int x, int y) {
		this.x = x; 
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
