package patterns.strategy;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.font.TextLayout;
import java.util.Iterator;
import java.util.List;

import patterns.component.Composite;
import patterns.component.ContentLeaf;
import patterns.component.SlideComposite;
import patterns.component.content.ClickableContent;
import patterns.component.content.ImageContent;
import patterns.component.content.TextContent;
import presentation.Surface;

/**
 * <p>
 * SlideViewerComponent is een grafische component die Slides kan laten zien.
 * </p>
 * 
 * @author Amriet Jainandunsing, Armando Gerard
 * @version 1.1 2022/10/?? Amriet Jainandunsing
 * @version 1.2 2022/11/01 Applied design Armando Gerard
 * @version 1.3 2022/11/03 Fixed opening from files via file explorer @Armando Gerard
 */
public class LinearDrawStrategy extends Strategy implements Drawable {
	public LinearDrawStrategy(Composite composite) {
		super(composite);
	}

	SlideComposite slideComposite;

	@Override
	public void initialize() {
		slideComposite = (SlideComposite) composite;

		Surface.registerDraw(this);
	}

	// geef de schaal om de slide te kunnen tekenen

	@Override
	public void draw(Graphics graphics, Rectangle rectangle, Surface surface) {
		if (slideComposite == null || !((SlideComposite)slideComposite).getActive())
			return;
		
		float scale = slideComposite.getScale(rectangle);
		int x = rectangle.x;
		int y = rectangle.y;

		for (int number = 0; number < slideComposite.getSize(); number++) {
			ContentLeaf leaf = (ContentLeaf) slideComposite.getSlideItem(number);

			if (!leaf.getClass().equals(ClickableContent.class)) {
				if (leaf.getClass().equals(TextContent.class)) {
					TextContent test = (TextContent) leaf;
					if (test.getText() == null || test.getText().length() == 0) {
						return;
					}
					List<TextLayout> layouts = test.getLayouts(graphics, leaf.getStyle(), scale);
					Point pen = new Point(x + (int) (leaf.getIndent() * scale),
							y + (int) (leaf.getStyle().getLeading() * scale));
					Graphics2D g2d = (Graphics2D) graphics;
					g2d.setColor(leaf.getStyle().getColor());
					Iterator<TextLayout> it = layouts.iterator();

					while (it.hasNext()) {
						TextLayout layout = it.next();
						pen.y += layout.getAscent();
						layout.draw(g2d, pen.x, pen.y);
						pen.y += layout.getDescent();
						test.setCoordinates(pen.x, pen.y);
					}
				} else {
					ImageContent imageContent = (ImageContent) leaf;
					int width = x + (int) (imageContent.getIndent() * scale);
					int height = y + (int) (imageContent.getStyle().getLeading() * scale);
					graphics.drawImage(imageContent.getBufferedImage(), width, height,
							(int) (imageContent.getBufferedImage().getWidth(surface) * scale),
							(int) (imageContent.getBufferedImage().getHeight(surface) * scale), surface);
					imageContent.setCoordinates(width, height);
				}
				y += leaf.calculateExtent(graphics, surface, scale, leaf.getStyle()).height;

			} /*
				 * else { ClickableContent content = (ClickableContent)leaf; Graphics2D g2d =
				 * (Graphics2D) graphics; //double thickness = 2; //Stroke oldStroke =
				 * g2d.getStroke(); //BasicStroke stroke = new BasicStroke(); Rectangle extent =
				 * content.getContent().getExtent(); //stroke.createStrokedShape(extent);
				 * //g2d.setStroke(stroke); g2d.drawRect((extent.x +
				 * content.getContent().getX()), (extent.y + content.getContent().getY()),
				 * (int)extent.getWidth(), (int)extent.getHeight()); //g2d.setStroke(oldStroke);
				 * }
				 */
		}
	}

	@Override
	public void show() {

	}
}
