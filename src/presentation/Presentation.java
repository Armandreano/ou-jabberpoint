package presentation;

import patterns.component.SlideshowComposite; 

/**
 * <p>Presentation houdt de slides in de presentatie bij.</p>
 * <p>Er is slechts ��n instantie van deze klasse aanwezig.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Presentation {
	private String showTitle; // de titel van de presentatie
	private Surface slideViewComponent = null; // de viewcomponent voor de Slides
	private SlideshowComposite slideshowComposite;

	public Presentation() {
		slideViewComponent = null; 
		clear();
	}

	public Presentation(Surface slideViewerComponent) {
		this.slideViewComponent = slideViewerComponent;
		clear();
	}
	
	public void setSlideshowComposite(SlideshowComposite slideshowComposite) {
		this.slideshowComposite = slideshowComposite;
	}

	public String getTitle() {
		return showTitle;
	}

	public void setTitle(String nt) {
		showTitle = nt;
	}

	public void setShowView(Surface slideViewerComponent) {
		this.slideViewComponent = slideViewerComponent;
	}
	
	public SlideshowComposite getSlideshowComposite() {
		return this.slideshowComposite;
	}
	
	public void setSlideNumber(int number) {
		this.slideshowComposite.setSlideNumber(number);
	}

	// Verwijder de presentatie, om klaar te zijn voor de volgende
	void clear() {
		//showList = new ArrayList<SlideComposite>();
		//setSlideNumber(-1);
	}

	public void exit(int n) {
		System.exit(n);
	}
}
