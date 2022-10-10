package patterns.component.content;

import patterns.component.Component;
import patterns.component.Content;

public class Text implements Content, Component {
	int indent;

	@Override
	public void indent(int level) {
		indent = level;
	}

	@Override
	public int getIndentLevel() {
		// TODO Auto-generated method stub
		return indent;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
}