package patterns.factory;

/** Comment Start
 * 
 * @author Armando Gerard
 * @version 1.0 2022-09-09 Created for (abstract) factories Armando Gerard 
 * Comment Ends */

public interface Prototype<T> {
	// Renamed to copy because ICloneable has "Clone"
	T copy();
}
