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
 * @version 1.7 - ?? Applied design @Amriet Jainandunsing
 * @version 1.? 2022/11/03 Fixed opening from files via file explorer @Armando Gerard
 */

public class Presentation {
	private String showTitle; // de titel van de presentatie
	private SlideshowComposite slideshowComposite;
	
	private static Presentation presentation;
	private boolean isLocked = true;
	
	public Presentation() {
		if(presentation != null)
			presentation.clear(true);
		
		presentation = this;
	}
	
	public static Presentation getPresentation() {
		return presentation;
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

	public SlideshowComposite getSlideshowComposite() {
		return this.slideshowComposite;
	}
	
	public void setSlideNumber(int number) {
		this.slideshowComposite.setActive(true);
		this.slideshowComposite.setSlideNumber(number);
	}

	// Verwijder de presentatie, om klaar te zijn voor de volgende
	public void clear(boolean locked) {
		if(slideshowComposite != null) {
			isLocked = locked;
			slideshowComposite.setActive(!isLocked);
			slideshowComposite.removeAll();
		}
	}

	public void exit(int n) {
		System.exit(n);
	}
	
	public boolean canQuit() {
		// This would have changed if saving was implemented, 
		// it would check if there are processes active that need to be finished first
		return true;
	}
}
