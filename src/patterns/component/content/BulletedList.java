package patterns.component.content;
import patterns.component.Composite;
import patterns.component.Content;

public class BulletedList extends Composite implements Content {
	int indent; 

	@Override
	public void indent(int level) {
		indent = level;
		// TODO Iterate over children and apply new indentation
	}

	@Override
	public int getIndentLevel() {
		// TODO Auto-generated method stub
		return indent;
	}
	
}
