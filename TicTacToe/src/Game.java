
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Game extends Stage {
		
	private final double BUTTON_PANE_PRECENT_HEIGHT = 0.1;
	private final double START_MENU_NAMES_PRECENT_HEIGHT = 0.1;
	private final double MENU_LOGO_PRECENT_HEIGHT = 0.8;
	private final String MENU_LOGO_STRING = "TicTacToe";
	
	private final double BOARD_PANE_PRECENT_HEIGHT = 0.7;
	private final double GAME_PLAY_PANEL_PRECENT_HEIGHT = 0.2;
	
	
	private final String NOT_YOUR_TURN = "Not Your Turn!"; 
	private final String ENTER_TILE_NUMBER = "Enter Tile Number"; 
	private final String INVALID_NAMES = "Invalid name"; 
	private final String ENTER_NAME = "Enter Name";
	private final String PLAYER_NAME = "Player Name: ";
		
	private double stageWidth;
	private double stageHeight;
	
	private final int NUM_OF_BUTTON_IN_BUTTON_PANE = 3;
	// Buttons pane
	private HBox buttonPane;
	private Button startPlayingButton;
	private Button resetGameButton;
	private Button endGameButton;
	
	// Starting menu Pane
	private final Font namePaneFont = new Font("Serif", 20);
	private HBox namePane;
	private Label playerName1Label;
	private TextField playerName1Text;
	private Label playerName2Label;
	private TextField playerName2Text;

	
	// Board of the game type of pane
	private Board board;
	
	
	// Players plays pane
	private final Font playerPlayPaneFont = new Font("Serif", 15);
	private HBox playerPlayPane;
	
	private Label playPanelPlayer1Name;
	private TextField playPanelPlayer1Tiles; 
	private Button playPanelPlayer1PlayButton;
	
	private Label playPanelPlayer2Name;	
	private TextField playPanelPlayer2Tiles;
	private Button playPanelPlayer2PlayButton;
	
	private Label winner;

	
	private Player player1;
	private Player player2;
	private enum Players {
		PLAYER1, PLAYER2;
		
		public char getSymbol() {
			switch (this) {
			case PLAYER1:
				return 'X';

			case PLAYER2:
				return 'O';
			}
			return ' ';
		}
	}
	
	private Players currentTurn;
	private int moveCounter;
	
	//VBox API is easier to handle than Stage API and have more functionalities, therefore elements will interact 
	// with the canvas and not the stage directly.
	private VBox canvas;
	private Label menuLogo;
	
	public Game(int width, int height) {
		this.stageWidth = width;
		this.stageHeight = height;
		
		this.setMinWidth(this.stageWidth);
		this.setMinHeight(this.stageHeight);
		
		initCanvas();
	
		Scene scene = new Scene(canvas);
		this.setScene(scene);
		this.setResizable(false);
	}
	
	public void resetGame() {
		
		if(canvas.getChildren().contains(winner)) {
			canvas.getChildren().remove(winner);
		}
		canvas.getChildren().remove(board);
		canvas.getChildren().remove(playerPlayPane);
		initBoard();
		canvas.getChildren().add(board);
		canvas.getChildren().add(playerPlayPane);
		
		currentTurn = Players.PLAYER1;
		moveCounter = 0;
	}
	
	public void startPlaying() {
		boolean badName = false;
		if(playerName1Text.getText().equals("") 
				|| playerName1Text.getText().equals(INVALID_NAMES) 
				|| playerName1Text.getText().equals(ENTER_NAME)) {
			playerName1Text.setText(INVALID_NAMES);
			badName = true;
		}
		if(playerName2Text.getText().equals("") 
				|| playerName2Text.getText().equals(INVALID_NAMES)
				|| playerName2Text.getText().equals(ENTER_NAME)) {
			playerName2Text.setText(INVALID_NAMES);
			badName = true;
		}
		
		if(badName) {
			return;
		}
		
		
		player1 = new Player(playerName1Text.getText());
		player2 = new Player(playerName2Text.getText());
		
		currentTurn = Players.PLAYER1;
		
		canvas.getChildren().remove(namePane);
		canvas.getChildren().remove(menuLogo);
		initBoard();
		initplayerPlayPane();
		canvas.getChildren().add(board);
		canvas.getChildren().add(playerPlayPane);
		
		//make reset button active after starting game
		resetGameButton.setDisable(false);
		startPlayingButton.setDisable(true);
		moveCounter = 0;
	}
	
	public void makeMove(Players player) {
		if(currentTurn == player) {
			try {
				int position = checkPlayerText(player);
				this.board.choosTile(position, player.getSymbol());
				checkWin();
				toggleTurn();
				playPanelPlayer1Tiles.setText(ENTER_TILE_NUMBER);
				playPanelPlayer2Tiles.setText(ENTER_TILE_NUMBER);
			} catch (Exception e) {
				if(player == Players.PLAYER1) {
					playPanelPlayer1Tiles.setText(e.getMessage());
				}
				else {
					playPanelPlayer2Tiles.setText(e.getMessage());
				}
			}
		}
		else {
			if(player == Players.PLAYER1) {
				playPanelPlayer1Tiles.setText(NOT_YOUR_TURN);
			}
			else {
				playPanelPlayer2Tiles.setText(NOT_YOUR_TURN);
			}
		}
	}
	
	private int checkPlayerText(Players player) throws Exception{
		int number;
		try {
			if(player == Players.PLAYER1) {
				number = Integer.parseInt(playPanelPlayer1Tiles.getText().trim());	
			}
			else {
				number = Integer.parseInt(playPanelPlayer2Tiles.getText().trim());	
			}
			return number;
		}
		catch(NumberFormatException e){
			throw new NumberFormatException("Not a Number");
		}
		
	}

	public void endGame() {
		this.close();
	}
		
	private void checkWin() {
		if(this.board.checkWin()) {
			String winnerName;
			if(currentTurn == Players.PLAYER1) {
				winnerName = playerName1Text.getText();
			}
			else {
				winnerName = playerName2Text.getText();
			}
			winner = createLabel("winner is: " + winnerName, this.stageWidth, 
					this.stageHeight * GAME_PLAY_PANEL_PRECENT_HEIGHT, new Font("Serif", 50));

			canvas.getChildren().remove(playerPlayPane);
			canvas.getChildren().add(winner);
		}
		else if(moveCounter == 9) {
			winner = createLabel("Draw", this.stageWidth, this.stageHeight * GAME_PLAY_PANEL_PRECENT_HEIGHT, new Font("Serif", 50));

			canvas.getChildren().remove(playerPlayPane);
			canvas.getChildren().add(winner);
		}
	}
	
	private void toggleTurn() {
		if(currentTurn == Players.PLAYER1) {
			currentTurn = Players.PLAYER2;
		}
		else
			currentTurn = Players.PLAYER1;
	}
	
	private void initCanvas() {
		this.canvas = new VBox();
		this.canvas.setPrefSize(this.getWidth(),this.getHeight());
		
		initButtonPane();	
		initPlayerNamePane();
		this.menuLogo = createLabel(MENU_LOGO_STRING, this.stageWidth, this.stageHeight * MENU_LOGO_PRECENT_HEIGHT, new Font("Serif", 100));
		
		this.menuLogo.setStyle("-fx-background-color: lightblue");
		this.canvas.getChildren().addAll(this.buttonPane, this.namePane, this.menuLogo);		
	}
	
	private void initButtonPane() {
		this.buttonPane = new HBox();
		double buttonPaneWidth = this.stageWidth;
		double buttonPaneHeight = this.stageHeight * BUTTON_PANE_PRECENT_HEIGHT;
		
		this.buttonPane.setPrefSize(buttonPaneWidth, buttonPaneHeight);
	
		this.startPlayingButton = new Button("Start Playing");
		this.startPlayingButton.setPrefSize(buttonPaneWidth/NUM_OF_BUTTON_IN_BUTTON_PANE, buttonPaneHeight);
		
		this.resetGameButton = new Button("Reset Game");
		this.resetGameButton.setPrefSize(buttonPaneWidth/NUM_OF_BUTTON_IN_BUTTON_PANE, buttonPaneHeight);
		this.resetGameButton.setDisable(true);
		
		this.endGameButton = new Button("End Game");
		this.endGameButton.setPrefSize(buttonPaneWidth/NUM_OF_BUTTON_IN_BUTTON_PANE, buttonPaneHeight);
		
		this.buttonPane.getChildren().addAll(startPlayingButton, resetGameButton, endGameButton);
		
		initButtonPaneFunctions();
	}
	
	private void initButtonPaneFunctions() {
		
		this.startPlayingButton.setOnAction(e -> startPlaying());
		
		this.resetGameButton.setOnAction(e -> resetGame());
		
		this.endGameButton.setOnAction(e -> endGame());
	}
	
	private void initPlayerNamePane() {
		double namePaneWidth = this.stageWidth;
		double namePaneHeight = this.stageHeight * START_MENU_NAMES_PRECENT_HEIGHT;
		
		this.namePane = new HBox();
		this.namePane.setPrefSize(namePaneWidth, namePaneHeight);
		this.namePane.setStyle("-fx-background-color: lightblue");
		
		double innerHBoxPanesWidth = namePaneWidth/2;
		double innerHBoxPanesHeight = namePaneHeight;
		
		this.playerName1Label = createLabel(PLAYER_NAME, innerHBoxPanesWidth/2, innerHBoxPanesHeight, namePaneFont);
		this.playerName2Label = createLabel(PLAYER_NAME, innerHBoxPanesWidth/2, innerHBoxPanesHeight, namePaneFont);

		this.playerName1Text = createField(ENTER_NAME,  innerHBoxPanesWidth/2, innerHBoxPanesHeight);
		this.playerName2Text = createField(ENTER_NAME,  innerHBoxPanesWidth/2, innerHBoxPanesHeight);
	
		initPlayerNameTextFieldClick();
		
		HBox leftPane = new HBox();
		leftPane.setPrefSize(innerHBoxPanesWidth, innerHBoxPanesHeight);
		leftPane.getChildren().addAll(playerName1Label, playerName1Text);
	
		HBox rightPane = new HBox();
		rightPane.setPrefSize(innerHBoxPanesWidth, innerHBoxPanesHeight);
		rightPane.getChildren().addAll(playerName2Label, playerName2Text);
			
		this.namePane.getChildren().addAll(leftPane,rightPane);
		
	}

	private void initPlayerNameTextFieldClick() {
		this.playerName1Text.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> playerName1Text.clear());	
		this.playerName2Text.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> playerName2Text.clear());		
	}




	private Label createLabel(String labelText, double width, double height, Font font) {
		Label label = new Label(labelText);
		label.setPrefSize(width, height);
		label.setAlignment(Pos.CENTER);
		label.setFont(font);
		label.setStyle("-fx-border-color: black");		

		return label;
	}
	
	private TextField createField(String textFieldText, double width, double height) {
		TextField textField = new TextField(textFieldText);
		textField.setPrefSize(width, height);
		textField.setAlignment(Pos.CENTER);
		textField.setFont(namePaneFont);
		
		return textField;
	}

	private void initBoard() {
		double boardWidth = this.stageWidth;
		double boardHeight = this.stageHeight * BOARD_PANE_PRECENT_HEIGHT;
		
		this.board = new Board(boardWidth, boardHeight);
	}
	
	private void initplayerPlayPane() {
		this.playerPlayPane = new HBox();
		double playPlayPaneWidth = this.stageWidth;
		double playPlayPaneHeight = this.stageHeight * GAME_PLAY_PANEL_PRECENT_HEIGHT;
		
		this.playerPlayPane.setPrefSize(playPlayPaneWidth, playPlayPaneHeight);
		
		double innerHBoxPanesWidth = playPlayPaneWidth/2;
		double innerHBoxPanesHeight = playPlayPaneHeight;
		
		this.playPanelPlayer1Name = createLabel(player1.getName(),innerHBoxPanesWidth/4 , innerHBoxPanesHeight, playerPlayPaneFont);
		this.playPanelPlayer1Tiles = createField(ENTER_TILE_NUMBER, innerHBoxPanesWidth/2 , innerHBoxPanesHeight);
		this.playPanelPlayer1PlayButton = new Button("Play");
		this.playPanelPlayer1PlayButton.setPrefSize(innerHBoxPanesWidth/4, innerHBoxPanesHeight);;
		this.playPanelPlayer1PlayButton.setFont(namePaneFont);

		
		this.playPanelPlayer2Name = createLabel(player2.getName(),innerHBoxPanesWidth/4 , innerHBoxPanesHeight, playerPlayPaneFont);
		this.playPanelPlayer2Tiles = createField(ENTER_TILE_NUMBER, innerHBoxPanesWidth/2 , innerHBoxPanesHeight);
		this.playPanelPlayer2PlayButton = new Button("Play");
		this.playPanelPlayer2PlayButton.setPrefSize(innerHBoxPanesWidth/4, innerHBoxPanesHeight);;
		this.playPanelPlayer2PlayButton.setFont(namePaneFont);
		
		initPlayerPanelTextClick();
		
		HBox leftPane = new HBox();
		leftPane.setPrefSize(innerHBoxPanesWidth, playPlayPaneHeight);
		leftPane.getChildren().addAll(playPanelPlayer1Name, playPanelPlayer1Tiles,playPanelPlayer1PlayButton);
	
		HBox rightPane = new HBox();
		rightPane.setPrefSize(innerHBoxPanesWidth, playPlayPaneHeight);
		rightPane.getChildren().addAll(playPanelPlayer2Name, playPanelPlayer2Tiles,playPanelPlayer2PlayButton);
			
		this.playerPlayPane.getChildren().addAll(leftPane,rightPane);
		
		initPlayPanelButton();
	}

	private void initPlayerPanelTextClick() {
		this.playPanelPlayer1Tiles.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> playPanelPlayer1Tiles.clear());	
		this.playPanelPlayer2Tiles.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> playPanelPlayer2Tiles.clear());			
	}

	private void initPlayPanelButton() {
		this.playPanelPlayer1PlayButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				makeMove(Players.PLAYER1);		
			}
		});
		
		this.playPanelPlayer2PlayButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				makeMove(Players.PLAYER2);
			}
		});
	}
	

}
