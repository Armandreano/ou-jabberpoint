package patterns.strategy;

import java.awt.Graphics;
import java.awt.Rectangle;

import presentation.Surface;

public interface Drawable {
	public void draw(Graphics graphics, Rectangle rectangle, Surface surface);
}
