import patterns.factory.Prototype;

/** Comment Start
 * 
 * @author Armando Gerard
 * @version 1.0 2022-09-09 Created to have cloning in every object that needs it (inherit from Entity) Armando Gerard 
 * TODO: Add Composite Pattern
 * Comment Ends */

public class Entity implements Prototype<Entity> {
	private static Entity entity;
	
	public Entity copy() {
		return new Entity();
	}
	
	public static Entity instantiate() {
		if(entity == null)
			entity = new Entity();
		
		return entity.copy();
	}
}
