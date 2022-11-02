package patterns.component.content;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import patterns.component.Component;
import patterns.component.Content;
import patterns.component.ContentLeaf;
import patterns.component.Style;
import patterns.observer.Subject;

public class ClickableContent extends ContentLeaf {

	private ContentLeaf content;
	private Subject subject;
	
	public ClickableContent(ContentLeaf component) {
		this.content = component;
		this.subject = Subject.createSubject();
	}
	
	@Override
	public Rectangle calculateExtent(Graphics g, ImageObserver observer, float scale, Style style) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getExtent() {
		return null;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean contains(int x, int y) {
		return content.getExtent().contains(x, y);
	}

	public void processClick(int x, int y) {
		if(content.getExtent().contains(x, y)) {
			subject.notification();
		}
		
//		System.out.println(String.format("Content %s has X:%d, Y:%d, width: %d and height %d. XPos: %d, YPos: %d bool: %s",
//				content.toString(), content.getExtent().x, content.getExtent().y, content.getExtent().width, 
//				content.getExtent().height, x, y, content.getExtent().contains(x, y) ? "True" : "False"));
	}
}
