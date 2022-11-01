package patterns.component;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.Vector;

import patterns.component.content.TextContent;
import patterns.factory.Prototype;
import patterns.strategy.Strategy;

/** <p>Een slide. Deze klasse heeft tekenfunctionaliteit.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class SlideComposite extends Composite implements Prototype<SlideComposite> {
	// Slide should always be copied to prevent references from the domain
	private static SlideComposite slide;
	private Strategy strategy;
	private boolean isActive = false;
	Rectangle slideArea;
	
	@Override
	public SlideComposite copy() {
		return new SlideComposite();
	}
	
	public static SlideComposite createSlide() {
		if(slide == null)
			slide = new SlideComposite();
		
		return slide.copy();
	}
	
	public void SetDimensions(Rectangle slideArea) {
		this.slideArea = slideArea;
	}
	
	public int getWidth() {
		return slideArea.width;
	}
	
	public int getHeight() {
		return slideArea.height;
	}
	
	public void setActive(boolean active) {
		this.isActive = active;
		if(isActive) {
			strategy.initialize();
		}
	}
	
	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
	
	
	// OLD CODE
	public final static int WIDTH = 1200;
	public final static int HEIGHT = 800;
	protected String title; // de titel wordt apart bewaard
	protected Vector<Component> items; // de slide-items worden in een Vector bewaard //gebruik van composite

	
	 public SlideComposite() { items = new Vector<Component>(); }
	 

	// Voeg een SlideItem toe
	public void append(Component anItem) {
		items.addElement(anItem);
	}

	// geef de titel van de slide
	public String getTitle() {
		return title;
	}

	// verander de titel van de slide
	public void setTitle(String newTitle) {
		title = newTitle;
	}

	// Maak een TextItem van String, en voeg het TextItem toe
	public void append(String message, TextStyle style) {
		append(new TextContent(message, style, 0));
	}

	// geef het betreffende SlideItem
	public Component getSlideItem(int number) {
		return (Component)items.elementAt(number);
	}

	// geef alle SlideItems in een Vector
	public Vector<Component> getSlideItems() {
		return items;
	}

	// geef de afmeting van de Slide
	public int getSize() {
		return items.size();
	}

	// teken de slide
//	public void draw(Graphics g, Rectangle area, ImageObserver view) {
//
//	}

	public float getScale(Rectangle area) {
		return Math.min(((float)area.width) / ((float)WIDTH), ((float)area.height) / ((float)HEIGHT));
	}

	public Iterator<?> getIterator() {
		return children.iterator();
	}
}
