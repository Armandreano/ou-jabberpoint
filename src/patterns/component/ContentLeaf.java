package patterns.component;

import java.awt.Rectangle;

/**
 * 
 * @author ajainandunsing
 * The ContentLeaf class used as a parent for content types (e.g. images and text)
 *
 */
public abstract class ContentLeaf extends Leaf implements Content {
	
	private int indent;
	protected Rectangle extent;
	private int x;
	private int y;
	
	@Override
	public int getIndent() {
		return this.indent;
	}
	
	@Override
	public void setIndent(int indent) {
		this.indent = indent;
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}
	
	public void setCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
