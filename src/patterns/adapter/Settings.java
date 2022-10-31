package patterns.adapter;

import patterns.XMLAdapter;
import patterns.command.Change;
import patterns.command.wrappers.FileData;
import patterns.component.ControlComponent;
import patterns.component.ControlService;
import patterns.component.FileControl;
import patterns.component.SlideControl;
import patterns.factory.AbortCommandFactory;
import patterns.factory.ChangeCommandFactory;
import patterns.factory.CommandFactory;
import patterns.factory.PresentationFactory;
import patterns.factory.SelectCommandFactory;
import presentation.GUI;
import presentation.Presenter;
import presentation.Window;

public class Settings {
//	FileAdapter adapter
	protected static final String IOERR = "IO Error: ";
	protected static final String JABERR = "Jabberpoint Error ";
	protected static final String JABVERSION = "Jabberpoint 1.6 - OU version";
	
	public Settings() {
		load();
	}
	
	public void load() {
		// TODO: Read from adapter
		CommandFactory.addFactories(new CommandFactory[] 
				{new ChangeCommandFactory(), new AbortCommandFactory(), new SelectCommandFactory()});
		
		
		PresentationFactory presentationFactory = new PresentationFactory();
		

		FileAdapter[] adapters = new FileAdapter[] {
			new XMLAdapter()	
		};
		
		for (int i = 0; i < adapters.length; i++) {
			presentationFactory.addAdapter(adapters[i]);
		}
		
		
		ControlService controlService = new ControlService();
		controlService.addComponents(new ControlComponent[] {new SlideControl(), new FileControl(adapters, presentationFactory)
//				, new Cl
				});
		
		// TODO: Remove this
		controlService.receiveCommand(new Change(new FileData("test.xml", "")));
		
		new Window(JABVERSION, controlService.getPresentation());

		
		Presenter presenter = new Presenter(controlService);
		GUI gui = new GUI(controlService);

	}
	
	public void apply() {
		
	}
}
