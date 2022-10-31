package patterns;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

/**
 * 
 * @author ajainandunsing The XMLAdapter class that implements the FileAdapter
 *         interface in order to create a presentation from a XML file.
 *
 */
public class XMLAdapter implements FileAdapter {

	@Override
	public boolean isSupported(String fileName) {
		if (fileName.contains(".xml")) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Element adapt(String file) throws Exception {
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = builder.parse(new InputSource(new StringReader(file)));
		return document.getDocumentElement();
	}

	@Override
	public String readFile(String fileName) throws IOException {
		return Files.readString(Path.of(fileName));
	}

}
