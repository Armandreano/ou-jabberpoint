package patterns.component;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.font.TextLayout;
import java.awt.image.ImageObserver;
import java.util.List;

/**
 * 
 * @author ajainandunsing
 * The ContentLeaf class used as a parent for content types (e.g. images and text)
 *
 */
public abstract class ContentLeaf extends Leaf implements Content {
	
	//Moet eigenlijk private zijn maar door oudere Java kan dit niet en wordt niet gebruikt in ImageContent
	//protected abstract List<TextLayout> getLayouts(Graphics g, Style s, float scale);
}
