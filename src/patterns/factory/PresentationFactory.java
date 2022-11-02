package patterns.factory;

import java.awt.Color;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import patterns.adapter.FileAdapter;
import patterns.component.TextStyle;
import patterns.component.content.ClickableContent;
import patterns.component.content.ImageContent;
import patterns.component.content.TextContent;
import patterns.strategy.LinearDrawStrategy;
import presentation.Presentation;
import patterns.component.ContentLeaf;
import patterns.component.SlideComposite;
import patterns.component.SlideshowComposite;

/** <p>Done</p>
 * @author Amriet Jainandunsing
 * @version 1.1 2022/10/?? Applied design  @Amriet Jainandunsing
*/
public class PresentationFactory {
	
	private List<FileAdapter> adapters = new ArrayList<>();
	private FileAdapter adapter;
	
	/*    *//** Default API to use. */
	/*
	 * protected static final String DEFAULT_API_TO_USE = "dom";
	 * 
	 *//** namen van xml tags of attributen */
	
	  protected static final String SHOWTITLE = "showtitle"; 
	  protected static final String SLIDE = "slide"; 
	  protected static final String STRATEGY = "strategy"; 
	  protected static final String ITEM = "item";
	  protected static final String COLOR = "color"; 
	  protected static final String FONTNAME = "fontname"; 
	  protected static final String FONTSIZE = "fontsize";
	  protected static final String INDENT = "indent"; 
	  protected static final String LEADING = "leading";
	  
	  protected static final String ACTIONS = "actions"; 
	  protected static final String KIND = "kind"; 
	  protected static final String TEXT = "text"; 
	  protected static final String IMAGE = "image";
	  
	  protected static final String PCE = "Parser Configuration Exception";
	  protected static final String UNKNOWNTYPE = "Unknown Element type"; 
	  protected static final String NFE = "Number Format Exception"; 
	  protected static final String CE = "Color Exception";
								
	  
	  private String open(String fileName) throws IOException {
		  String file = "";
		  for(FileAdapter adapter : adapters) {
			  if(adapter.isSupported(fileName)) {
				  this.adapter = adapter;
				  file = adapter.readFile(fileName);
				  
			  }
		  }
		  return file;
	  }
	
	  public Presentation createPresentation(String fileName) throws Exception {
		  String file = open(fileName);
		  Element element = this.adapter.adapt(file);
		  Presentation presentation = new Presentation();
		  presentation.setTitle(getTitle(element, SHOWTITLE));
		  createPresentation(element, presentation);
		  return presentation;	  
	  }
	  
	  public void addAdapter(FileAdapter adapter) {
		  this.adapters.add(adapter);
	  }
	
	  private void createPresentation(Element element, Presentation presentation) throws IOException { 
		  
		 int slideNumber, itemNumber, max = 0, maxItems = 0; 
		 
		 presentation.setTitle(getTitle(element, SHOWTITLE));
	  
		NodeList slides = element.getElementsByTagName(SLIDE); 
		max = slides.getLength();
		SlideshowComposite slideshowComposite = new SlideshowComposite();
		for (slideNumber = 0; slideNumber < max; slideNumber++) { 
				Element xmlSlide = (Element) slides.item(slideNumber); 
				Node strategy = slides.item(slideNumber).getAttributes().getNamedItem(STRATEGY);
				String typeOfStrategy = strategy.getTextContent();
				SlideComposite slide = new SlideComposite(); 
				slideshowComposite.addComponent(slide);
				if(typeOfStrategy.equals("linear")) {
					LinearDrawStrategy linearDrawStrategy = new LinearDrawStrategy(slide);
					slide.setStrategy(linearDrawStrategy);
				}
	  
				NodeList slideItems = xmlSlide.getElementsByTagName(ITEM); 
				maxItems = slideItems.getLength(); 
				for (itemNumber = 0; itemNumber < maxItems; itemNumber++) { 
					Element item = (Element) slideItems.item(itemNumber);
					loadSlideContent(slide, item); 
					} 
				} 
		presentation.setSlideshowComposite(slideshowComposite);
	  }
	  
	  private String getTitle(Element element, String tagName) { 
		  NodeList titles = element.getElementsByTagName(tagName); 
	  	return titles.item(0).getTextContent();
	  }
	  
	  private TextStyle createStyle(NamedNodeMap attributes, Element item) { 
		  int fontSize = 40; 
		  int leading = 20; 
		  String fontName = "Helvetica"; 
		  Color color = Color.black; 
		  Node colorNode = attributes.getNamedItem(COLOR); 
		  Node fontNameNode = attributes.getNamedItem(FONTNAME); 
		  Node fontSizeNode =
		  attributes.getNamedItem(FONTSIZE); 
		  Node leadingNode = attributes.getNamedItem(LEADING);
	  
		  try { 
			  if (colorNode != null) { 
				  Field field = Class.forName("java.awt.Color").getField(colorNode.getTextContent()); 
				  color = (Color) field.get(null); 
			  } else { 
				  color = Color.black; 
				  } 
			  } catch (Exception e) { 
				  System.err.println(CE); 
			  }
	  
		  if (fontNameNode != null) { 
			  fontName = fontNameNode.getTextContent(); 
			  }
	  
		  if (fontSizeNode != null) { 
			  try { 
				  fontSize = Integer.parseInt(fontSizeNode.getTextContent()); 
				  } catch (NumberFormatException x) { 
					  System.err.println(NFE); 
					  } 
			  }
	  
		  if (leadingNode != null) { 
			  try { 
				  leading = Integer.parseInt(leadingNode.getTextContent()); 
				  } catch (NumberFormatException x) { 
					  System.err.println(NFE); 
					  } 
			  } 
		  return new TextStyle(color, leading, fontName, fontSize); 
	}
	  
	  private void loadSlideContent(SlideComposite slide, Element item) { 
		  NamedNodeMap attributes = item.getAttributes(); 
		  Node action = attributes.getNamedItem(ACTIONS);
	  
		  //TODO: Should be overriden
		  String[] actions = null; 
		  if (action != null) { 
			  actions = action.getTextContent().split("\\,"); 
		  }
	  
		  int indent = 0; Node indentNode = attributes.getNamedItem(INDENT);
	  
		  if (indentNode != null) { 
			  try { 
				  indent = Integer.parseInt(indentNode.getTextContent()); 
				  } catch (NumberFormatException x) { 
					  System.err.println(NFE); 
					  } 
		  }
	  
		  String type = attributes.getNamedItem(KIND).getTextContent();
	  
		  TextStyle style = createStyle(attributes, item);
		  if (TEXT.equals(type)) {
			  TextContent content = new TextContent(item.getTextContent(), style,indent); 
			  slide.addComponent(content); 
			  loadClickableContent(slide, content, actions);
		  } else { 
			  if (IMAGE.equals(type)) {
				  	ImageContent content = new ImageContent(item.getTextContent(), style, indent); 
				  	slide.addComponent(content); 
					loadClickableContent(slide, content, actions);
				  	} 
			  else { 
				  System.err.println(UNKNOWNTYPE); 
				  } 
			  }
	  }
	  
		
	private void loadClickableContent(SlideComposite slide, ContentLeaf leaf, String[] actions) {
		if(actions != null) {
			slide.addComponent(new ClickableContent(leaf)); 
			} 
		} 
}
