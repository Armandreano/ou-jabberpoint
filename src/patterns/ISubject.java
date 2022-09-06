package patterns;

/** Comment Start
 * 
 * @author Armando Gerard
 * @version 1.0 2022-90-06 Created to have listeners Armando Gerard
 * Comment Ends */

public interface ISubject {
	void Attach(IObserver o);
	void Detach(IObserver o);
	void Notify();
}
