package patterns.component;
import patterns.factory.Prototype;
import patterns.strategy.DrawStrategy;

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
