package patterns.strategy;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.font.TextLayout;
import java.awt.image.ImageObserver;
import java.util.Iterator;
import java.util.List;

import javax.swing.DebugGraphics;

import patterns.component.Composite;
import patterns.component.ContentLeaf;
import patterns.component.SlideComposite;
import patterns.component.content.ImageContent;
import patterns.component.content.TextContent;
import presentation.Surface;

/**
 * <p>
 * SlideViewerComponent is een grafische component die Slides kan laten zien.
 * </p>
 * 
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class LinearDrawStrategy extends Strategy implements Drawable {
	public LinearDrawStrategy(Composite composite) {
		super(composite);
	}


	SlideComposite slideComposite;
	
	@Override
	public void initialize() {
		slideComposite = (SlideComposite)composite;
		
		Surface.registerDraw(this);
	}

	// geef de schaal om de slide te kunnen tekenen

	
	@Override
	public void draw(Graphics graphics, Rectangle rectangle, Surface surface) {
		if(slideComposite == null)
			return;
		float scale = slideComposite.getScale(rectangle);
		int x = rectangle.x;
		int y = rectangle.y;
		

        for (int number = 0; number < slideComposite.getSize(); number++) { 
        	ContentLeaf leaf = (ContentLeaf)slideComposite.getSlideItem(number);
        	
        	
    		if(leaf.getClass().equals(TextContent.class)) {
    			TextContent test = (TextContent) leaf;
    			if (test.getText() == null || test.getText().length() == 0) {
    				return;
    			}
    		List<TextLayout> layouts = test.getLayouts(graphics, leaf.getStyle(), scale);
    		Point pen = new Point(x + (int)(leaf.getIndent() * scale), 
    				y + (int) (leaf.getStyle().getLeading() * scale));
    		Graphics2D g2d = (Graphics2D)graphics;
    		g2d.setColor(leaf.getStyle().getColor());
    		Iterator<TextLayout> it = layouts.iterator();
    		while (it.hasNext()) {
    			TextLayout layout = it.next();
    			pen.y += layout.getAscent();
    			layout.draw(g2d, pen.x, pen.y);
    			pen.y += layout.getDescent();
    		}
    	  } else {
    		  ImageContent imageContent = (ImageContent) leaf;
    			int width = x + (int) (imageContent.getIndent() * scale);
    			int height = y + (int) (imageContent.getStyle().getLeading() * scale);
    			graphics.drawImage(imageContent.getBufferedImage(), width, height,(int) (imageContent.getBufferedImage().getWidth(surface)*scale),
    	                (int) (imageContent.getBufferedImage().getHeight(surface)*scale), surface);
    	  }
    		y += leaf.calculateExtent(graphics, surface, scale, leaf.getStyle()).height; 
    	}
	  }


	@Override
	public void show() {
		
	}
}
