import java.io.IOException;

import org.w3c.dom.Element;

/**
 * 
 * @author ajainandunsing
 * The FileAdapter class used in order to create different types of adapters that can create a presentation
 * from different types of files (e.g. XML and JSON).
 *
 */
public interface FileAdapter {
	
	public void createPresentation(Presentation presentation, String filename) throws IOException;
	
	private void getTitle(Element element, String tagName);

}
