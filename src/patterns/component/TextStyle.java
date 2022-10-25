package patterns.component;

import java.awt.Color;
import java.awt.Font;

public class TextStyle extends Style {

		private int fontSize;
		private Font font;
		private String fontName;
		
		
		public TextStyle(Color color, int leading, String fontName, 
						int fontSize, int indent) {
			super.setLeading(leading);
			super.setColor(color);
			super.setIndent(indent);
			this.fontSize = fontSize;
			this.setFontName(fontName);
			this.setFont(new Font(fontName, Font.BOLD, fontSize));
		}

		public String getFontName() {
			return fontName;
		}

		public void setFontName(String fontName) {
			this.fontName = fontName;
		}

		public int getFontSize() {
			return fontSize;
		}

		public void setFontSize(int fontSize) {
			this.fontSize = fontSize;
		}

		public Font getFont(float scale) {
			return font.deriveFont(fontSize * scale);
		}

		public void setFont(Font font) {
			this.font = font;
		}
}
