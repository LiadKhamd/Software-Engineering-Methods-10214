import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.ColumnConstraintsBuilder;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class Board extends GridPane {

	private final int WIDTH = 3;
	private final int HEIGHT = 3;
	
	private final int NUMBER_OF_TILES = 9;
	
	private Tile[] tiles;
	
	public Board(double width, double height) {
		this.setMinWidth(width);
		this.setMinHeight(height);
		
		
		initGridColumns(width);
		initGridRows(height);
		
		
		this.setGridLinesVisible(true);
		
		
		this.tiles = new Tile[NUMBER_OF_TILES];
		for(int i = 1; i <= NUMBER_OF_TILES; i++) {
			this.tiles[i-1] = new Tile(i);
		
			int column = (i-1) % WIDTH;
			int row = (i-1) / HEIGHT ;
			
			this.add(this.tiles[i-1], column, row);
			GridPane.setHalignment(this.tiles[i-1], HPos.CENTER);

			
		}
	}

	private void initGridRows(double boardHeight) {
		for(int i = 0 ; i < HEIGHT; i++) {
			RowConstraints constraint = new RowConstraints(boardHeight/HEIGHT);
			this.getRowConstraints().add(constraint);
		}
	}
	
	private void initGridColumns(double boardWidth) {
		for(int i = 0 ; i < WIDTH; i++) {
			ColumnConstraints constraint = new ColumnConstraints(boardWidth/WIDTH);
			this.getColumnConstraints().add(constraint);
		}
	}
	
	public boolean choosTile(int position, char symbol) {
		if(validPosition(position) && tiles[position].checkFree()) {
			tiles[position].setSymbol(symbol);
			return true;
		}
		return false;
	}
	
	
	private boolean validPosition(int position) {
		if(position < 1 || position > 9) {
			return false;
		}
		return true;
	}
	
}
