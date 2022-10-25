package patterns.component;

public class ImageStyle extends Style {

	private String opacity;
	
	public ImageStyle(int leading, int indent, String opacity) {
		super.setLeading(leading);
		super.setIndent(indent);
		this.setOpacity(opacity);
	}

	public String getOpacity() {
		return opacity;
	}

	public void setOpacity(String opacity) {
		this.opacity = opacity;
	}
}
