package patterns.strategy;

import patterns.component.Composite;

public abstract class Strategy {
	protected Composite composite;
	
	public Strategy(Composite composite) {
			
			this.composite = composite;
	}
	
	public abstract void show();
	public abstract void initialize();
}
