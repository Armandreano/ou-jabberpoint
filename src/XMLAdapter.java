import java.awt.Color;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import patterns.component.ImageStyle;
import patterns.component.TextStyle;
import patterns.component.content.ImageContent;
import patterns.component.content.TextContent;

/**
 * 
 * @author ajainandunsing
 * The XMLAdapter class that implements the FileAdapter interface in order to create a presentation
 * from a XML file.
 *
 */
public class XMLAdapter implements FileAdapter {
	
    /** Default API to use. */
    protected static final String DEFAULT_API_TO_USE = "dom";
    
    /** namen van xml tags of attributen */
    protected static final String SHOWTITLE = "showtitle";
    protected static final String SLIDETITLE = "title";
    protected static final String SLIDE = "slide";
    protected static final String ITEM = "item";
    protected static final String LEVEL = "level";
    protected static final String KIND = "kind";
    protected static final String TEXT = "text";
    protected static final String IMAGE = "image";
    
    /** tekst van messages */
    protected static final String PCE = "Parser Configuration Exception";
    protected static final String UNKNOWNTYPE = "Unknown Element type";
    protected static final String NFE = "Number Format Exception";
    
	@Override
	public boolean isSupported(String fileName) {
		if(fileName.contains(".xml")) {
			return true;
		} else {
			return false;
		}
	}

    @Override
	public void createPresentation(Presentation presentation, File file) throws IOException {
		int slideNumber, itemNumber, max = 0, maxItems = 0;
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document = builder.parse(file); 
			Element doc = document.getDocumentElement();
			presentation.setTitle(getTitle(doc, SHOWTITLE));

			NodeList slides = doc.getElementsByTagName(SLIDE);
			max = slides.getLength();
			for (slideNumber = 0; slideNumber < max; slideNumber++) {
				Element xmlSlide = (Element) slides.item(slideNumber);
				SlideComposite slide = new SlideComposite();
				slide.setTitle(getTitle(xmlSlide, SLIDETITLE));
				presentation.append(slide);
				
				NodeList slideItems = xmlSlide.getElementsByTagName(ITEM);
				maxItems = slideItems.getLength();
				for (itemNumber = 0; itemNumber < maxItems; itemNumber++) {
					Element item = (Element) slideItems.item(itemNumber);
					loadSlideItem(slide, item);
				}
			}
		}
		catch (SAXException sax) {
			System.err.println(sax.getMessage());
		}
		catch (ParserConfigurationException pcx) {
			System.err.println(PCE);
		}
	}
    
	@Override
	public File readFile(String fileName) {
		return new File(fileName);
	}
    
    private String getTitle(Element element, String tagName) {
    	NodeList titles = element.getElementsByTagName(tagName);
    	return titles.item(0).getTextContent();
    	
    }

	private void loadSlideItem(SlideComposite slide, Element item) {
		int level = 1; // default
		NamedNodeMap attributes = item.getAttributes();
		String leveltext = attributes.getNamedItem(LEVEL).getTextContent();
		if (leveltext != null) {
			try {
				level = Integer.parseInt(leveltext);
			}
			catch(NumberFormatException x) {
				System.err.println(NFE);
			}
		}
		String type = attributes.getNamedItem(KIND).getTextContent();

		if (TEXT.equals(type)) {
			TextStyle style =  new TextStyle(Color.black, 20, "Helvetica", 40, 0);
			TextContent component = new TextContent(item.getTextContent(), style);
			slide.append(component);
		}
		else {
			if (IMAGE.equals(type)) {
				ImageStyle style = new ImageStyle(20, 0, "Opacity");
				ImageContent content = new ImageContent(item.getTextContent(), style);
				slide.append(content);
			}
			else {
				System.err.println(UNKNOWNTYPE);
			}
		}
	}
}
