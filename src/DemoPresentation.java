import java.awt.Color;

import patterns.component.ImageStyle;
import patterns.component.TextStyle;
import patterns.component.content.ImageContent;


class DemoPresentation extends Accessor {

	public void loadFile(Presentation presentation, String unusedFilename) {
		TextStyle style = new TextStyle(Color.black,  20, "Helvetica", 40);
		presentation.setTitle("Demo Presentation");
		SlideComposite slide;
		slide = new SlideComposite();
		slide.setTitle("JabberPoint"); 
		slide.append("Het Java Presentatie Tool", style);
		slide.append(new ImageContent("JabberPoint.jpg", style, 0));
		presentation.append(slide);
		
		slide = new SlideComposite();
		slide.setTitle("Tweede"); 
		slide.append("Dit is hem echt!", style);
		slide.append(new ImageContent("JabberPoint.jpg", style, 0));
		presentation.append(slide);
	}

	public void saveFile(Presentation presentation, String unusedFilename) {
		throw new IllegalStateException("Save As->Demo! aangeroepen");
	}
}
