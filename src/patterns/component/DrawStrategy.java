package patterns.component;

import java.awt.Graphics;
import java.awt.image.ImageObserver;

public interface DrawStrategy {

	public void draw(int x, int y, float scale, Graphics g,  ImageObserver o, ContentLeaf textContent);
}
