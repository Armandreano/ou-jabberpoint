package patterns;

/** Comment Start
 * 
 * @author Armando Gerard
 * @version 1.0 2022-09-09 Created to have cloning in every object that needs it (inherit from Entity) Armando Gerard 
 * Comment Ends */

public interface IGenericFactory<T> {
	T copy();
}
