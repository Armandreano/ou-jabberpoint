package patterns.component;
import patterns.factory.Prototype;

public class SlideshowComposite extends Composite implements Prototype<SlideshowComposite> {
	
	@Override
	public SlideshowComposite copy() {
		return new SlideshowComposite();
	}
}
