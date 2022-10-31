package patterns.component.content;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import patterns.component.ContentLeaf;
import patterns.component.Style;
import patterns.component.TextStyle;

/**
 * 
 * @author ajainandunsing
 * The ImageContent class which contains images shown on a slide.
 *
 */
public class ImageContent extends ContentLeaf {
	  private BufferedImage bufferedImage;
	  private String imageName;
	  
	  protected static final String FILE = "Bestand ";
	  protected static final String NOTFOUND = " niet gevonden";
	
	public ImageContent(String imageName , TextStyle style, int indent) {
		try {
			bufferedImage = ImageIO.read(new File(imageName));
		}
		catch (IOException e) {
			System.err.println(FILE + imageName + NOTFOUND) ;
		}
		super.setStyle(style);
		super.setIndent(indent);
	}
	
	public BufferedImage getBufferedImage( ) {
		return this.bufferedImage;
	}
	
	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
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
	public Rectangle getExtent(Graphics g, ImageObserver observer, float scale, Style myStyle) {
		return new Rectangle((int) (this.getIndent() * scale), 0,
				(int) (bufferedImage.getWidth(observer) * scale),
				((int) (myStyle.getLeading() * scale)) + 
				(int) (bufferedImage.getHeight(observer) * scale));
	}
}
