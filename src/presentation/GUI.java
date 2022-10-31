package presentation;
//import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.Subject;

import patterns.command.Select;
import patterns.command.wrappers.ClickData;
import patterns.component.ControlService;
import patterns.factory.AbortCommandFactory;
import patterns.factory.ChangeCommandFactory;
import patterns.factory.CommandFactory;
import patterns.factory.SelectCommandFactory;
import patterns.observer.Observer;

public class GUI implements MouseListener{
	private Map<Integer, Subject> keyMap;
	private static GUI gui;
	Select currentSelect;
	ControlService controlService;
	
	public GUI(ControlService controlService) {
		this.controlService = controlService;
		
		keyMap = new HashMap<Integer, Subject>();
		gui = this;

		// Depending on the context, either GUI or Presenter will apply the setting first
		// TODO: Move to Settings
		CommandFactory.addFactory(new SelectCommandFactory());
		
		
	}
	
	public void registerMouseKey(int button, Subject subject){
		if(subject == null)
			keyMap.put(button, subject);
		else if(keyMap.containsKey(button)) {
			
		}
	}
	
	public static GUI getGui() {
		return gui;
	};

	@Override
	public void mouseClicked(MouseEvent e) {

		// TODO Auto-generated method stub
		System.out.println(String.format("Clicked at X: %d, Y: %d", e.getX(), e.getY()));
		ClickData clickData = new ClickData(e.getX(), e.getY());
		
//		Toolkit.getDefaultToolkit().beep();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
