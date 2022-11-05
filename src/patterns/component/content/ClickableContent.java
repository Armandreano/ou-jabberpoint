package patterns.component.content;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import patterns.component.ContentLeaf;
import patterns.component.Style;
import patterns.observer.Observer;
import patterns.observer.Subject;

public class ClickableContent extends ContentLeaf {

	private ContentLeaf content;
	private Subject subject;
	
	public ClickableContent(ContentLeaf component) {
		this.subject = Subject.createSubject();
		this.content = component;
		
		if(TextContent.class.isAssignableFrom(component.getClass())) {
			TextContent textContent = (TextContent)component;
			textContent.setUnderlined(true);
		} 
		else if (ImageContent.class.isAssignableFrom(component.getClass())) {
			ImageContent imageContent = (ImageContent) component;
			imageContent.setBorder(true);
		}
	}
	
	public void attachObserver(Observer o) {
		this.subject.attach(o);
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
	
	public ContentLeaf getContent() {
		return this.content;
	}
	
	public boolean contains(int x, int y) {
		if(content == null || content.getExtent() == null)
			return false;
		
		Rectangle contentRectangleCopy = content.getExtent().getBounds();
		contentRectangleCopy.setLocation((content.getExtent().x + content.getX()), (content.getExtent().y + content.getY()));
		return contentRectangleCopy.contains(x, y);
	}

	public void processClick(int x, int y) {
		if(content == null || content.getExtent() == null)
			return;
		
		Rectangle contentRectangleCopy = content.getExtent().getBounds();
		contentRectangleCopy.setLocation((content.getExtent().x + content.getX()), (content.getExtent().y + content.getY()));

		if(contentRectangleCopy.contains(x, y)) {
			subject.notification();
		}
	}
}
