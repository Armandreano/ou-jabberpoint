package patterns.observer;

/** Comment Start
 * 
 * @author Armando Gerard
 * @version 1.0 2022-09-06 Created to have listeners Armando Gerard
 * @version 1.1 2022-09-08 Updated to match Java naming Convention Armando Gerard
 * Comment Ends */

@FunctionalInterface
public interface IObserver {
	void update();
}
