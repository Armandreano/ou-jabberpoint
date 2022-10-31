package patterns.command.wrappers;

public class FileData extends CommandData {
	String fileString;
	String pathString;
	
	public FileData(String fileString, String pathString){
		this.fileString = fileString; 
		this.pathString = pathString;
	}
	
	public String getFileString() {
		return fileString;
	}
	
	public String getPathString() {
		return pathString;
	}
	
	public String getLocation() {
		return pathString + fileString;
	}
}
