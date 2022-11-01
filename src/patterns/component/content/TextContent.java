package patterns.component.content;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import patterns.component.Component;
import patterns.component.Content;
import patterns.component.ContentLeaf;
import patterns.component.Style;
import patterns.component.TextStyle;

/**
 * 
 * @author ajainandunsing
 * The TextContent class which contains text that is shown on a slide.
 *
 */
public class TextContent extends ContentLeaf {
	private String text;
	
	public TextContent(String text, Style style, int indent) {
		this.text = text;
		super.setStyle(style);
		super.setIndent(indent);
	}
	
	public String getText() {
		return this.text;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public Rectangle calculateExtent(Graphics g, ImageObserver observer, float scale, Style style) {
		List<TextLayout> layouts = getLayouts(g, style, scale);
		int xsize = 0, ysize = (int) (style.getLeading() * scale);
		Iterator<TextLayout> iterator = layouts.iterator();
		while (iterator.hasNext()) {
			TextLayout layout = iterator.next();
			Rectangle2D bounds = layout.getBounds();
			if (bounds.getWidth() > xsize) {
				xsize = (int) bounds.getWidth();
			}
			if (bounds.getHeight() > 0) {
				ysize += bounds.getHeight();
			}
			ysize += layout.getLeading() + layout.getDescent();
		}
		this.extent = new Rectangle((int) (this.getIndent()*scale), 0, xsize, ysize );
		return extent;
	}
	
	public List<TextLayout> getLayouts(Graphics g, Style s, float scale) {
		List<TextLayout> layouts = new ArrayList<TextLayout>();
		AttributedString attrStr = getAttributedString(scale);
    	Graphics2D g2d = (Graphics2D) g;
    	FontRenderContext frc = g2d.getFontRenderContext();
    	LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr.getIterator(), frc);
    	float wrappingWidth = (1200 - this.getIndent()) * scale;
    	while (measurer.getPosition() < getText().length()) {
    		TextLayout layout = measurer.nextLayout(wrappingWidth);
    		layouts.add(layout);
    	}
    	return layouts;
	}
	
	private AttributedString getAttributedString(float scale) {
		AttributedString attrStr = new AttributedString(getText());
		attrStr.addAttribute(TextAttribute.FONT, ((TextStyle) super.getStyle()).getFont(scale), 0, text.length());
		return attrStr;
	}

	@Override
	public Rectangle getExtent() {
		return this.extent;
	}
}