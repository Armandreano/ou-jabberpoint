package patterns.component;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.font.TextLayout;
import java.awt.image.ImageObserver;
import java.util.Iterator;
import java.util.List;

import patterns.component.content.ImageContent;
import patterns.component.content.TextContent;

public class LinearDrawStrategy implements DrawStrategy {
	
	@Override
	public void draw(int x, int y, float scale, Graphics g,  ImageObserver o, ContentLeaf textContent) {
		if(textContent.getClass().equals(TextContent.class)) {
			TextContent test = (TextContent) textContent;
			if (test.getText() == null || test.getText().length() == 0) {
				return;
			}
		List<TextLayout> layouts = test.getLayouts(g, textContent.getStyle(), scale);
		Point pen = new Point(x + (int)(textContent.getStyle().getIndent() * scale), 
				y + (int) (textContent.getStyle().getLeading() * scale));
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(textContent.getStyle().getColor());
		Iterator<TextLayout> it = layouts.iterator();
		while (it.hasNext()) {
			TextLayout layout = it.next();
			pen.y += layout.getAscent();
			layout.draw(g2d, pen.x, pen.y);
			pen.y += layout.getDescent();
		}
	  } else {
		  ImageContent imageContent = (ImageContent) textContent;
			int width = x + (int) (imageContent.getStyle().getIndent() * scale);
			int height = y + (int) (imageContent.getStyle().getLeading() * scale);
			g.drawImage(imageContent.getBufferedImage(), width, height,(int) (imageContent.getBufferedImage().getWidth(o)*scale),
	                (int) (imageContent.getBufferedImage().getHeight(o)*scale), o);
	  }
	}
}
