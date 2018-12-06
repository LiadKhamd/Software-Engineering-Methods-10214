import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class Tile extends Label {
	
	/**
	 * Note
	 * by making Tile a Label it has symbol within it 
	 */
	
	private final Font font = new Font("Serif", 40);
	private final int postion;
	
	
	public Tile(int position) {
		this.postion = position;
		this.setFont(font);
		this.setText(position + "");
	}
	
	public boolean checkFree() {
		if(this.getText().compareTo(this.postion + "") == 0) {
			return true;
		}
		return false;
	}
	
	public void clear() {
		this.setText(this.postion + "");
	}
	
	public void setSymbol(char symbol) {
		this.setText(symbol + "");;
	}
	
}
