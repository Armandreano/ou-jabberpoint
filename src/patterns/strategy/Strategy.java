package patterns.strategy;

import patterns.component.Composite;

/** <p>Done</p>
 * @author Armando Gerard
 * @version 1.1 2022/10/31 Applied design  @Armando Gerard
*/

public abstract class Strategy {
	protected Composite composite;
	
	public Strategy(Composite composite) {
			
			this.composite = composite;
	}
	
	public abstract void show();
	public abstract void initialize();
}
