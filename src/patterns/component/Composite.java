package patterns.component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Composite extends Component {
	protected ArrayList<Component> children = new ArrayList<Component>();
	
	public <T extends Component> T addComponent(T t){
		if(t == null) // This object doesn't have creation rights
			return null;
		
		children.add(t);
		return t;
	}
	
	public void remove() {
		for (Iterator<Component> iterator = children.iterator(); iterator.hasNext();) {
			Component component = (Component) iterator.next();
			component.remove();
		}
		
		children.clear();
	}
	
	public <T extends Component> void removeComponent() {
		Component foundComponent = null;
		
		for (Iterator<Component> iterator = children.iterator(); iterator.hasNext();) {
			Component component = (Component) iterator.next();
			
			if(List.class.isAssignableFrom(component.getClass())) {
				foundComponent = component; 
				component.remove();
			}
		}
		
		children.remove(foundComponent);
	}
	
	public <T extends Component> void removeComponents() {
		ArrayList<Component> foundArrayList = new ArrayList<>();
		for (Iterator<Component> iterator = children.iterator(); iterator.hasNext();) {
			Component component = (Component) iterator.next();
			
			if(List.class.isAssignableFrom(component.getClass())) {
				foundArrayList.add(component);
				component.remove();
			}
		}
		
		children.removeAll(foundArrayList);
	}
	
	public <T extends Component> T getComponent(){
		for (Iterator<Component> iterator = children.iterator(); iterator.hasNext();) {
			Component component = (Component) iterator.next();
			
			if(List.class.isAssignableFrom(component.getClass())) {
				// This code can't be executed unless the component inherits 
				// from the class T,that's why the warning is suppressed
				@SuppressWarnings("unchecked")
				T t = (T) component;
				return t;
			}
		}
		return null;
	}
	
	public <T extends Component> ArrayList<T> getComponents(){
		ArrayList<T> components = new ArrayList<>();
		
		for (Iterator<Component> iterator = children.iterator(); iterator.hasNext();) {
			Component component = (Component) iterator.next();
			
			if(List.class.isAssignableFrom(component.getClass())) {
				// This code can't be executed unless the component inherits 
				// from the class T,that's why the warning is suppressed
				@SuppressWarnings("unchecked")
				T t = (T) component;
				components.add(t);
			}
		}
		
		return components;
	}
	
	// Update all children
	public void update() {
		for (Iterator<Component> iterator = children.iterator(); iterator.hasNext();) {
			Component component = (Component) iterator.next();
			component.update();
		}
	}
}
