import patterns.component.Composite;
import patterns.component.DrawStrategy;
import patterns.factory.Prototype;

public class SlideshowComposite extends Composite implements Prototype<SlideshowComposite> {
	private DrawStrategy strategy;
	
	@Override
	public SlideshowComposite copy() {
		return new SlideshowComposite();
	}
	
	public void setStrategy(DrawStrategy strategy) {
		this.strategy = strategy;
	}
}
