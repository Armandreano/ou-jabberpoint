package patterns.component;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author ajainandunsing
 * The Content interface used in the Composite pattern
 *
 */
public interface Content {
	
	public Rectangle getExtent(Graphics g, ImageObserver observer, float scale, Style style);
}
