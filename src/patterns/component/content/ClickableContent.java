package patterns.component.content;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import patterns.component.Component;
import patterns.component.ContentLeaf;
import patterns.component.Style;
import patterns.observer.Subject;

public class ClickableContent extends ContentLeaf {

	private Component clickableContent;
	private Subject subject;
	
	public ClickableContent(Component component) {
		this.clickableContent = component;
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

}
