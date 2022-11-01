package patterns.adapter;

import java.util.ArrayList;
import java.util.Iterator;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import patterns.command.Change;
import patterns.command.wrappers.FileData;
import patterns.component.ClickControl;
import patterns.component.ControlComponent;
import patterns.component.ControlService;
import patterns.component.FileControl;
import patterns.component.SlideControl;
import patterns.factory.AbortCommandFactory;
import patterns.factory.ChangeCommandFactory;
import patterns.factory.CommandFactory;
import patterns.factory.PresentationFactory;
import patterns.factory.SelectCommandFactory;
import patterns.adapter.FileAdapter;
import presentation.GUI;
import presentation.Presenter;
import presentation.Window;

public class Settings {
	XMLAdapter adapter;
	protected static final String IOERR = "IO Error: ";
	protected static final String JABERR = "Jabberpoint Error ";
	protected static final String JABVERSION = "Jabberpoint 1.6 - OU version";
	
	public Settings(XMLAdapter adapter) {
		this.adapter = adapter;
		load();
	}
	
	public void load() {
		  String file = "settings.xml";
		  try {
			  String dataString = adapter.readFile(file);
			  Element element = adapter.adapt(dataString);
			  apply(element);
			
		  } catch (Exception e) {
			// TODO: handle exception
		  }
	}
	
	public void apply(Element element) {
//		NodeList presentationFactoryList = element.getElementsByTagName("factories");
//		String presentationfactoryString = presentationFactoryList.item(0).getAttributes().item(0).getNodeValue();
//		NodeList factories = element.getElementsByTagName("factory");
//		NodeList adapters = element.getElementsByTagName("adapter");
//		NodeList controlServices = element.getElementsByTagName("controlservice");
		
		try {
//			Class<?> factoryClass = Class.forName(presentationfactoryString);
//			PresentationFactory presentationFactory = (PresentationFactory)factoryClass.getConstructor().newInstance();
//			
//			for (int i = 0; i < factories.getLength(); i++) {
//				String factoryTypeString = factories.item(i).getTextContent();
//				Class<?> commandfactoryClass = Class.forName(factoryTypeString);
//				CommandFactory commandFactory =  (CommandFactory)commandfactoryClass.getConstructor().newInstance();
//				if(commandFactory != null)
//					CommandFactory.addFactory(commandFactory);
//			}
//			
//			ArrayList<FileAdapter> fileAdapters = new ArrayList<>();
//			
//			for (int i = 0; i < adapters.getLength(); i++) {
//				String adapterTypeString = adapters.item(i).getTextContent();
//				Class<?> adapterClass = Class.forName(adapterTypeString);
//				FileAdapter adapter =  (FileAdapter)adapterClass.getConstructor().newInstance();
//				
//				if(adapter != null)
//				{
//					presentationFactory.addAdapter(adapter);
//					fileAdapters.add(adapter);
//				}
//			}
//			
//			for (int i = 0; i < controlServices.getLength(); i++) {
//				String controlServiceTypeString = controlServices.item(i).getTextContent();
//				Class<?> controlServiceClass = Class.forName(controlServiceTypeString);
//				ControlService service =  (ControlService)controlServiceClass.getConstructor().newInstance();
//				
//				if(service != null)
//				{
//					
//				}
//			}
			
			
			// TODO: Read from adapter
			// TODO: Remove this
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
			controlService.addComponents(new ControlComponent[] {new SlideControl(), 
					new FileControl(adapters, presentationFactory), new ClickControl()});
			
			controlService.receiveCommand(new Change(new FileData("test.xml", "")));
			
	
			Presenter presenter = new Presenter(controlService);
			GUI gui = new GUI(controlService);
			new Window(JABVERSION, controlService.getPresentation(), gui, presenter);
			controlService.getPresentation().setSlideNumber(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
