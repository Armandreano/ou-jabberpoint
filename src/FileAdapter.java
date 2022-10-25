import java.io.File;
import java.io.IOException;

/**
 * 
 * @author ajainandunsing
 * The FileAdapter class used in order to create different types of adapters that can create a presentation
 * from different types of files (e.g. XML and JSON).
 *
 */
public interface FileAdapter {
	
	public boolean isSupported(String fileName);
	
	public File readFile(String fileName);
	
	public void createPresentation(Presentation presentation, File file) throws IOException;
}
