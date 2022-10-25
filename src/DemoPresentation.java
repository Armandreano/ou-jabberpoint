import java.awt.Color;

import patterns.component.ImageStyle;
import patterns.component.TextStyle;
import patterns.component.content.ImageContent;

/** Een ingebouwde demo-presentatie
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

class DemoPresentation extends Accessor {

	public void loadFile(Presentation presentation, String unusedFilename) {
		TextStyle style = new TextStyle(Color.black,  20, "Helvetica", 40, 0);
		ImageStyle imageStyle = new ImageStyle( 20, 0, "");
		presentation.setTitle("Demo Presentation");
		SlideComposite slide;
		slide = new SlideComposite();
		slide.setTitle("JabberPoint"); 
		slide.append("Het Java Presentatie Tool", style);
		slide.append(new ImageContent("JabberPoint.jpg", imageStyle));
		presentation.append(slide);
		
		slide = new SlideComposite();
		slide.setTitle("Tweede"); 
		slide.append("Dit is hem echt!", style);
		slide.append(new ImageContent("JabberPoint.jpg", imageStyle));
		presentation.append(slide);
	}

	public void saveFile(Presentation presentation, String unusedFilename) {
		throw new IllegalStateException("Save As->Demo! aangeroepen");
	}
}
