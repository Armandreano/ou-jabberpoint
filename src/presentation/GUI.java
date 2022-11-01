package presentation;
//import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;


import patterns.command.Select;
import patterns.command.wrappers.ClickData;
import patterns.component.ControlService;
import patterns.factory.CommandFactory;
import patterns.factory.SelectCommandFactory;
import patterns.observer.Observer;
import patterns.observer.Subject;

public class GUI implements MouseListener{
	private Map<Integer, Subject> keyMap;
	private static GUI gui;
	Select currentSelect;
	ControlService controlService;
	ClickData clickData;
	
	public GUI(ControlService controlService) {
		this.controlService = controlService;
		
		keyMap = new HashMap<Integer, Subject>();
		gui = this;

		// Depending on the context, either GUI or Presenter will apply the setting first
		// TODO: Move to Settings

		setupDefaultMouseControl();
	}
	
	private void Click() {
		SelectCommandFactory selectCommandFactory = CommandFactory.getFactory(SelectCommandFactory.class);
		Select select = (Select)selectCommandFactory.createCommand(null);
		controlService.receiveCommand(select);
	}
	
	private void setupDefaultMouseControl() {
		registerMouseKey(MouseEvent.BUTTON1, null);
		
		bindMouseKey(MouseEvent.BUTTON1, ()->{ Click(); });
	}

	public void registerMouseKey(int button, Subject subject){
		if(subject == null)
			keyMap.put(button, Subject.createSubject());
		
		else if(keyMap.containsKey(button)) {
			keyMap.replace(button, subject);
		}
	}
	
	public void bindMouseKey(int button, Observer observer)
	{
		Subject subject = keyMap.get(button);
		
		if(subject != null)
			subject.attach(observer);
	}
	
	public static GUI getGui() {
		return gui;
	};
	
	public void mouseAction(int button) {
		Subject subject = keyMap.get(button);
		
		if(subject != null) {
			System.out.println(String.format("Clicked button: %d", button));
			subject.notification();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(String.format("Clicked at X: %d, Y: %d", e.getX(), e.getY()));
		clickData = new ClickData(e.getX(), e.getY());
		mouseAction(e.getButton());
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
