package patterns.command.wrappers;

import java.awt.Cursor;

/** 
 * @author Armando Gerard
 * @version 1.1 2022/11/02 Created for cursor change @Armando Gerard
*/
public class MousePositionData extends ClickData {
	int handCursor = Cursor.HAND_CURSOR;
	int defaultCursor = Cursor.DEFAULT_CURSOR;
	
	public MousePositionData(int x, int y) {
		super(x, y);
	}

	public int getHandCursor() {
		return handCursor;
	}

	public void setHandCursor(int handCursor) {
		this.handCursor = handCursor;
	}

	public int getOtherCursor() {
		return defaultCursor;
	}

	public void setOtherCursor(int defaultCursor) {
		this.defaultCursor = defaultCursor;
	}

}
