package patterns.command.wrappers;

/** <p>Done</p>
 * @author Armando Gerard
 * @version 1.1 2022/10/?? Applied design  @Armando Gerard
*/
public class SwitchToSlideData extends CommandData {
	public int newSlide;
	public int oldSlide;
	
	public SwitchToSlideData(int newSlide) {
		// TODO Auto-generated constructor stub
		this.newSlide = newSlide;
	}
	
	public int getNewSlide() {
		return newSlide;
	}
	
	public void setOldSlide(int oldSlide) {
		this.oldSlide = oldSlide;
	}
	
	public int getOldSlide() {
		return oldSlide;
	}
}
