package presentation;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JFrame;

/**
 * <p>Het applicatiewindow voor een slideviewcomponent</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 * @version 1.6 2022/10/31 Armando Gerard
*/

//WindowFrame
public class Window extends JFrame {
	private static final long serialVersionUID = 3227L;
	
	private static final String JABTITLE = "Jabberpoint 1.6 - OU";
	public final static int WIDTH = 1200;
	public final static int HEIGHT = 800;
	
	public Window(String title, Presentation presentation, GUI gui, Presenter presenter) {
		super(title);
		Surface surface = new Surface(presentation, this);
		presentation.setShowView(surface);
		setupWindow(surface, presentation, gui, presenter);
	}

// De GUI opzetten
	public void setupWindow(Surface 
			surface, Presentation presentation, GUI gui, Presenter presenter) {
		setTitle(JABTITLE);
		addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
		getContentPane().add(surface);
		addMouseListener(gui);
		addKeyListener(presenter); // een controller toevoegen
		setMenuBar(new MenuController(this, presentation));	// nog een controller toevoegen
		setSize(new Dimension(WIDTH, HEIGHT)); // Dezelfde maten als Slide hanteert.
		setVisible(true);
	}
}
