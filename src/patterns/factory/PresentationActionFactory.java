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
import presentation.Surface;

public class PresentationActionFactory extends PresentationFactory {

	@Override
	protected Component createComponent(Node node, SlideComposite slide, Presentation presentation) {
		Component component = super.createComponent(node, slide, presentation);

		ContentLeaf leaf = (ContentLeaf) component;
		
		for (int i = 0; i < node.getChildNodes().getLength(); i++) {
			Node actionNode = node.getChildNodes().item(i);

			if (actionNode.getNodeName() == "action") {
				String action = actionNode.getAttributes().getNamedItem(NAME).getTextContent();
				Node valueNode = actionNode.getAttributes().getNamedItem(VALUE);
				String value = "";
				
				if(valueNode != null)
					value = valueNode.getTextContent();
				ClickableContent clickableContent = new ClickableContent(leaf);
				loadClickableContent(slide, clickableContent, action, value, presentation);
				slide.addComponent(clickableContent);
			}

		}
		
		

		return component;
	}

	private void loadClickableContent(SlideComposite slide, ClickableContent clickableContent, String action,
			String value, Presentation presentation) {

		switch (action) {
		case "next": {

			clickableContent.attachObserver(() -> {
				presentation.setSlideNumber(presentation.getSlideshowComposite().getCurrentSlideNumber() + 1);
			});
			break;
		}
		case "previous": {
			clickableContent.attachObserver(() -> {
				presentation.setSlideNumber(presentation.getSlideshowComposite().getCurrentSlideNumber() - 1);
			});
			break;
		}
		case "beep": {
			clickableContent.attachObserver(() -> {
				Toolkit.getDefaultToolkit().beep();
				;
			});
			break;
		}
		case "open": {
			clickableContent.attachObserver(() -> {
				try {
					Presentation newPresentation = createPresentation(value);
					Surface.setPresentation(newPresentation);
					System.out.println(newPresentation);
				} catch (Exception e) {
					// TODO: handle exception
				}
			});

			break;
		}
		case "goto": {
			clickableContent.attachObserver(() -> {
				try {
					presentation.setSlideNumber(Integer.valueOf(value));
				} catch (Exception e) {
					// TODO: handle exception
				}
				;
			});
			break;
		}
		case "end": {
			clickableContent.attachObserver(() -> {
				presentation.setSlideNumber(presentation.getSlideshowComposite().getSize() - 1);
			});
			break;
		}
		case "start": {
			clickableContent.attachObserver(() -> {
				presentation.setSlideNumber(0);
			});
			break;
		}
		default:
			return;
		}
	}
}
