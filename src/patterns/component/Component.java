package patterns.component;

/**
 * 
 * @author ajainandunsing
 * The component abstract class used in the Composite pattern
 *
 */
public abstract class Component {
	private Style style;
	
	public abstract void update();
	public abstract void remove();
	
	public Style getStyle() {
		return style;
	}
	public void setStyle(Style style) {
		this.style = style;
	}
}