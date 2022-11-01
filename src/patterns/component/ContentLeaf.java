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
	
	@Override
	public int getIndent() {
		return this.indent;
	}
	
	@Override
	public void setIndent(int indent) {
		this.indent = indent;
	}
}
