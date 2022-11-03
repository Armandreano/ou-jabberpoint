package presentation;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.JFrame;

import patterns.component.SlideComposite;
import patterns.observer.Subject;
import patterns.strategy.Drawable;

/**
 * <p>
 * SlideViewerComponent is een grafische component die Slides kan laten zien.
 * </p>
 * 
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman, Armando Gerard
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 * @version 1.7 2022/10/31 Armando Gerard
 * @version 1.8 2022/11/03 Fixed opening from files via file explorer @Armando Gerard
 */
//Service
//static net als jframe
public class Surface extends JComponent {
	private static Surface surface;
	private Subject subject;
	private static Graphics graphics;
	private static Rectangle area;

	private Font labelFont = null; // het font voor labels
	private Presentation presentation = null; // de presentatie
	private JFrame frame = null;

	private static final long serialVersionUID = 227L;

	private static final Color BGCOLOR = Color.white;
	private static final Color COLOR = Color.black;
	private static final String FONTNAME = "Dialog";
	private static final int FONTSTYLE = Font.BOLD;
	private static final int FONTHEIGHT = 10;
	private static final int XPOS = 1100;
	private static final int YPOS = 20;

	public Surface(Presentation presentation, JFrame frame) {
		surface = this;
		setBackground(BGCOLOR);
		this.frame = frame;
		applyPresentation(presentation);
		subject = Subject.createSubject();
	}
	
	public void applyPresentation(Presentation presentation) {
		this.presentation = presentation;
		labelFont = new Font(FONTNAME, FONTSTYLE, FONTHEIGHT);
		frame.setTitle(presentation.getTitle());
	}
	
	public static void setPresentation(Presentation presentation) {
		if(surface != null)
			surface.applyPresentation(presentation);
	}

	public Dimension getPreferredSize() {
		return new Dimension(SlideComposite.WIDTH, SlideComposite.HEIGHT);
	}
	
	public Subject getSubject() {
		return subject;
	}
	
	public static void applyCursor(Cursor cursor) {
		surface.setCursor(cursor);
	}
	
	public static void registerDraw(Drawable drawable) {
		if(surface == null)
			return;
		
		surface.getSubject().clear();
		surface.getSubject().attach(()->{ drawable.draw(graphics, area, surface); });
		surface.repaint();
	}

// teken de slide
	public void paintComponent(Graphics g) {
		g.setColor(BGCOLOR);
		g.fillRect(0, 0, getSize().width, getSize().height);
		if (presentation.getSlideshowComposite().getCurrentSlideNumber() < 0 || presentation.getSlideshowComposite().getCurrentSlide() == null) {
			return;
		}
		g.setFont(labelFont);
		g.setColor(COLOR);
		g.drawString("Slide " + (1 + presentation.getSlideshowComposite().getCurrentSlideNumber() + " of " + presentation.getSlideshowComposite().getSize()), XPOS, YPOS);
		area = new Rectangle(0, YPOS, getWidth(), (getHeight() - YPOS));
		graphics = g;
		subject.notification();
	}
}
