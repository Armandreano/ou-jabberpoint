package patterns.component;

/**
 * 
 * @author ajainandunsing
 * The component abstract class used in the Composite pattern
 *
 */
public abstract class Component {
	private Style style;
	private Component parentComponent;
	
	public abstract void update();
	public abstract void remove();
	
	public Component getParentComponent() {
		return parentComponent;
	}
	
	public void setParentComponent(Component parentComponent) {
		this.parentComponent = parentComponent;
	}
	
	public Style getStyle() {
		return style;
	}
	public void setStyle(Style style) {
		this.style = style;
	}
}