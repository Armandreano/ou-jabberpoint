package patterns.factory;

import java.awt.Toolkit;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import patterns.component.Component;
import patterns.component.ContentLeaf;
import patterns.component.SlideComposite;
import patterns.component.content.ClickableContent;
import presentation.Presentation;

public class PresentationActionFactory extends PresentationFactory {
	
	  @Override	
	  protected Component createComponent(NodeList node, int index, SlideComposite slide, Presentation presentation) {
		  Component component = super.createComponent(node, index, slide, presentation);

			  NodeList actions = node.item(index).getChildNodes();
			  
			  if(actions.getLength() > 0) {
			  System.out.println(actions.getLength());
				for(int itemNumber = 0; itemNumber < actions.getLength(); itemNumber++) {	
					//System.out.println(actions.item(itemNumber));
					loadClickableContent(slide, (ContentLeaf)component, actions.item(itemNumber), presentation);
				}
			  }
		  
		  
		  return component;
	  }

	
	private void loadClickableContent(SlideComposite slide, ContentLeaf leaf, Node node, Presentation presentation) {
		if(node == null) {
			return;
		} 
		
		ClickableContent clickableContent = new ClickableContent(leaf);
			System.out.println("Node name: " + node.getNodeName());
			
			switch (node.getAttributes().getNamedItem(NAME).getTextContent()) {
			case "next": {
				clickableContent.attachObserver(()->{presentation.setSlideNumber(
						presentation.getSlideshowComposite().getCurrentSlideNumber() + 1);} );
				break;
			}
			case "previous": {
				clickableContent.attachObserver(()->{presentation.setSlideNumber(
						presentation.getSlideshowComposite().getCurrentSlideNumber() - 1);} );
				break;
			}
			case "beep": {
				clickableContent.attachObserver(()->{Toolkit.getDefaultToolkit().beep();;} );
				break;
			}
			case "open": {
				clickableContent.attachObserver(()->{Toolkit.getDefaultToolkit().beep();;} );
				break;
			}
			case "goto": {
				clickableContent.attachObserver(()->{Toolkit.getDefaultToolkit().beep();;} );
				break;
			}
			default:
				return;
			} 
		
		slide.addComponent(clickableContent); 
		} 
}
