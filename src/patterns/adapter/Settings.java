package patterns.adapter;

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import patterns.command.Command;
import patterns.command.wrappers.CommandData;
import patterns.command.wrappers.FileData;
import patterns.component.control.ControlComponent;
import patterns.component.control.FileControl;
import patterns.component.ControlService;
import patterns.factory.CommandFactory;
import patterns.factory.PresentationFactory;
import patterns.adapter.FileAdapter;
import presentation.GUI;
import presentation.Presentation;
import presentation.Presenter;
import presentation.Window;

/** <p>This is the KeyController (KeyListener)</p>
 * @author Armando Gerard
 * @version 1.1 2022/10/30 Applied design (loading from Settings) @Armando Gerard
*/

public class Settings {
	XMLAdapter adapter;
	protected static final String JABVERSION = "Jabberpoint 1.7 - Amriet & Armando version";
	
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
		try {
			NodeList presentationFactoryList = element.getElementsByTagName("factories");
			String presentationfactoryString = presentationFactoryList.item(0).getAttributes().item(0).getNodeValue();
			NodeList controlServiceList = element.getElementsByTagName("controlservices");
			String controlServiceString = controlServiceList.item(0).getAttributes().item(0).getNodeValue();
			
			
			Class<?> factoryClass = Class.forName(presentationfactoryString);
			PresentationFactory presentationFactory = (PresentationFactory)factoryClass.getConstructor().newInstance();
			
			createCommandFactories(element.getElementsByTagName("factory"));
			FileAdapter[] fileAdapters = createFileAdapters(presentationFactory, element.getElementsByTagName("adapter"));
			
			
			Class<?> controlServiceClass = Class.forName(controlServiceString);
			ControlService controlService = (ControlService)controlServiceClass.getConstructor().newInstance();
			createControlComponents(element.getElementsByTagName("controlservice"), controlService, presentationFactory, fileAdapters);
			
			NodeList guiList = element.getElementsByTagName("gui");
			String guiString = guiList.item(0).getTextContent();
			NodeList presenterList = element.getElementsByTagName("presenter");
			String presenterString = presenterList.item(0).getTextContent();
			
			Class<?> guieClass = Class.forName(guiString);
			GUI gui = (GUI)guieClass.getConstructor(ControlService.class).newInstance(controlService);
			
			Class<?> presenterClass = Class.forName(presenterString);
			Presenter presenter = (Presenter)presenterClass.getConstructor(ControlService.class).newInstance(controlService);
			
			sendCommands(element.getElementsByTagName("command"), controlService);
			
			Presentation presentation = controlService.getPresentation();
			new Window(JABVERSION, presentation, gui, presenter);
			presentation.setSlideNumber(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void createCommandFactories(NodeList factories) {
		for (int i = 0; i < factories.getLength(); i++) {
			try {
				String factoryTypeString = factories.item(i).getTextContent();
				Class<?> commandfactoryClass = Class.forName(factoryTypeString);
				CommandFactory commandFactory =  (CommandFactory)commandfactoryClass.getConstructor().newInstance();
				if(commandFactory != null)
					CommandFactory.addFactory(commandFactory);
			}
			catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	public FileAdapter[] createFileAdapters(PresentationFactory presentationFactory, NodeList adapters) {
		try {
			ArrayList<FileAdapter> fileAdapterList = new ArrayList<FileAdapter>();
			
			for (int i = 0; i < adapters.getLength(); i++) {
				String adapterTypeString = adapters.item(i).getTextContent();
				Class<?> adapterClass = Class.forName(adapterTypeString);
				FileAdapter adapter =  (FileAdapter)adapterClass.getConstructor().newInstance();
				
				if(adapter != null)
				{
					presentationFactory.addAdapter(adapter);
					fileAdapterList.add(adapter);
				}
			}
			
			FileAdapter[] fileAdapters = new FileAdapter[fileAdapterList.size()];
			fileAdapterList.toArray(fileAdapters);
			
			return fileAdapters;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
	
	public void createControlComponents(NodeList controlComponents, ControlService controlService, PresentationFactory presentationFactory, FileAdapter[] fileAdapters) {
		try {
			for (int i = 0; i < controlComponents.getLength(); i++) {
				String controlComponentTypeString = controlComponents.item(i).getTextContent();
				Class<?> controlComponentClass = Class.forName(controlComponentTypeString);
	
				ControlComponent controlComponent =  
						(ControlComponent)controlComponentClass.getConstructor().newInstance();
						
				if(controlComponent != null){
					controlService.addComponent(controlComponent);
					
					if(FileControl.class.isInstance(controlComponent)) {
						((FileControl)controlComponent).Initialize(fileAdapters, presentationFactory);
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void sendCommands(NodeList commandList, ControlService controlService) {		
		for (int i = 0; i < commandList.getLength(); i++) {
			NamedNodeMap namedNodeMap = commandList.item(i).getAttributes();
			String commandTypeString = "";
			String commandDataString = "";
			String constructor1Value = "";
			String constructor2Value = "";
			String constructor1Type = "";
			String constructor2Type = "";
			
			for (int j = 0; j < namedNodeMap.getLength(); j++) {
				switch (namedNodeMap.item(j).getNodeName()) {
				case "type": {
					commandTypeString = namedNodeMap.item(j).getNodeValue();
					break;
				}
				case "data": {
					commandDataString = namedNodeMap.item(j).getNodeValue();
					break;
				}
				case "constructor1": {
					constructor1Value = namedNodeMap.item(j).getNodeValue();
					break;
				}
				case "constructor2": {
					constructor2Value = namedNodeMap.item(j).getNodeValue();
					break;
				} 
				case "constructor1type": {
					constructor1Type = namedNodeMap.item(j).getNodeValue();
					break;
				}
				case "constructor2type": {
					constructor2Type = namedNodeMap.item(j).getNodeValue();
					break;
				} // Can continue with constructor 3, 4 etc. not necessary for assignment
				default:
					throw new IllegalArgumentException("Unexpected value: " + namedNodeMap.item(j).getNodeName());
				}
				
			}
			
			try {
				switch (namedNodeMap.getLength() - 2) {
				case 0: {
					
					break;
				}
				case 1: {
					
					break;
				}
				case 4: {
					Class<?> commandClass = Class.forName(commandTypeString);
					Class<?> commandData = Class.forName(commandDataString);
					Class<?> constructor1 = Class.forName(constructor1Type);
					Class<?> constructor2 = Class.forName(constructor2Type);
					
					CommandData data = 
							(CommandData)commandData.getConstructor(constructor1, constructor2).newInstance(constructor1Value, constructor2Value);
					Command command = (Command)commandClass.getConstructor(CommandData.class).newInstance(data);
					
					controlService.receiveCommand(command);
					System.out.println(command);
					
					break;
				} // Can continue with constructor 3, 4 etc. not necessary for assignment
				default:
					break;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
