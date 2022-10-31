package patterns.adapter;
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
	
	public boolean isSupported(String fileName);
	
	public String readFile(String fileName) throws IOException;
	
	public Element adapt(String file) throws Exception;
}
