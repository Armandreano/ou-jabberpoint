package patterns.component;

import java.awt.Color;

/**
 * 
 * @author ajainandunsing
 * The Style class used for creating styles for different kinds of objects within the Composite pattern
 *
 */
public abstract class Style {
	
	private Color color;
	private int leading; 
	private String opacity;
	
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public int getLeading() {
		return leading;
	}
	public void setLeading(int leading) {
		this.leading = leading; 
	}
	
	public String getOpacity() {
		return opacity;
	}
	public void setOpacity(String opacity) {
		this.opacity = opacity;
	}
}
