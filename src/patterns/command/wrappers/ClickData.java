package patterns.command.wrappers;

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
