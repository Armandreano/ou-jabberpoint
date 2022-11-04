package patterns.component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** XMLAccessor, reads and writes XML files
 * @author Armando Gerard
 * @version 1.1 2022/09/09 Applied design based on experience in Game Development
 * @version 1.2 2022/10/31 Added setting the parent of the leaf component
 * @version 1.3 2022/11/03 Fixed opening from files via file explorer (added possibility of removal of children) @Armando Gerard
*/

public class Composite extends Component {
	protected ArrayList<Component> children = new ArrayList<Component>();
	
	private boolean active;
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean isActive) {
		this.active = isActive;
	}

	public <T extends Component> T addComponent(T t){
		if(t == null) // This object doesn't have creation rights
			return null;
		
		children.add(t);
		
		t.setParentComponent(this);
		
		return t;
	}
	
	public <T extends Component> T[] addComponents(T[] t){
		if(t == null) // This object doesn't have creation rights
			return null;
		
		int index = 0;
		
		for (int i = 0; i < t.length; i++) {
			t[index] = addComponent(t[index]);
			
			index++;
		}
		
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
	
	public void removeAll() {
		children.clear();
	}
}
