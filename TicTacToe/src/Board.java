import exceptions.AlreadyOccupiedException;
import exceptions.InvalidPositionException;
import javafx.geometry.HPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.RowConstraints;

public class Board extends GridPane {

	private final int WIDTH = 3;
	private final int HEIGHT = 3;
	
	private final int NUMBER_OF_TILES = 9;
	
	private Tile[] tiles;
	
	private final int NUM_OF_WINNING_STATES = 8;
	private int[][] winningStates;
	
	public Board(double width, double height) {
		this.setMinWidth(width);
		this.setMinHeight(height);
				
		initGrid(width, height);
		initWinningStates();

	}
	
	public boolean checkWin() {
		String one;
		String two;
		String three;
		for (int i = 0; i < NUM_OF_WINNING_STATES; i++) {
			one = this.tiles[this.winningStates[i][0]].getText();
			two = this.tiles[this.winningStates[i][1]].getText();
			three = this.tiles[this.winningStates[i][2]].getText();
			if(one.equals(two) && one.equals(three)) {
				markWinningSpots(i);
				return true;
			}
		}
		return false;
	}

	private void initGrid(double width, double height) {
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

	private void initWinningStates() {
		this.winningStates = new int[NUM_OF_WINNING_STATES][3];
		this.winningStates[0] = new int[]{0,1,2};
		this.winningStates[1] = new int[]{3,4,5};
		this.winningStates[2] = new int[]{6,7,8};
		this.winningStates[3] = new int[]{0,3,6};
		this.winningStates[4] = new int[]{1,4,7};
		this.winningStates[5] = new int[]{2,5,8};
		this.winningStates[6] = new int[]{0,4,8};
		this.winningStates[7] = new int[]{2,4,6};
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
	
	public void choosTile(int position, char symbol) throws Exception {
		if(!validPosition(position)) {
			throw new InvalidPositionException("Invalid Position");
		}
		else if(!tiles[position-1].checkFree()) {
			throw new AlreadyOccupiedException("Tile taken");
		}
		
		tiles[position-1].setSymbol(symbol);
	}
	
	
	private boolean validPosition(int position) {
		if(position < 1 || position > 9) {
			return false;
		}
		return true;
	}


	private void markWinningSpots(int winningStatePos) {
		for(int i = 0 ; i < 3 ; i++) {
			this.tiles[this.winningStates[winningStatePos][i]].setStyle("-fx-background-color: mediumpurple");
		}	
	}
	
}
