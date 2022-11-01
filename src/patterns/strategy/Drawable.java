package patterns.strategy;

import java.awt.Graphics;
import java.awt.Rectangle;

import presentation.Surface;

/** <p>Done</p>
 * @author Armando Gerard
 * @version 1.1 2022/10/?? Applied design, made sure that observer could be attached  @Armando Gerard
*/
public interface Drawable {
	public void draw(Graphics graphics, Rectangle rectangle, Surface surface);
}
