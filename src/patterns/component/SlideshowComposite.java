package patterns.component;
import java.util.Iterator;

import patterns.factory.Prototype;

public class SlideshowComposite extends Composite implements Prototype<SlideshowComposite> {
	private int currentSlideNumber = 0;
	
	@Override
	public SlideshowComposite copy() {
		return new SlideshowComposite();
	}
	
	public int getSize() {
		return this.children.size();
	}
	
	public int getCurrentSlideNumber() {
		return this.currentSlideNumber;
	}
	
	public void setCurrentSlideNumber(int number) {
		this.currentSlideNumber = number;
	}
	// Geef een slide met een bepaald slidenummer
	public SlideComposite getSlide(int number) {
		if (number < 0 || number >= getSize()){
			return null;
	    }
			return (SlideComposite)this.children.get(number);
	}
	
	// ga naar de vorige slide tenzij je aan het begin van de presentatie bent
	public void prevSlide() {
		if (currentSlideNumber > 0) {
			setSlideNumber(currentSlideNumber - 1);
	    }
	}

	// Ga naar de volgende slide tenzij je aan het einde van de presentatie bent.
	public void nextSlide() {
		if (currentSlideNumber < (this.children.size()-1)) {
			setSlideNumber(currentSlideNumber + 1);
		}
	}
	
	public SlideComposite getCurrentSlide() {
		return getSlide(currentSlideNumber);
	}
	
	public void setSlideNumber(int number) {
		if(this.getSize() == 0 || number < 0 || number >= this.getSize()) 
			return;
		this.getSlide(currentSlideNumber).setActive(false);
		currentSlideNumber = number;
		this.getSlide(currentSlideNumber).setActive(true);
	}
	
	public Iterator<?> getIterator(){
		return this.children.iterator();
	}
}
