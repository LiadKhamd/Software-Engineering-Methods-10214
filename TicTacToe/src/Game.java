
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Game extends Stage {
	
	//for self refrencing inside button action events
	private final Game me = this;
	private Scene scene;
	
	private final double BUTTON_PANE_PRECENT_HEIGHT = 0.1;
	private final double START_MENU_NAMES_PRECENT_HEIGHT = 0.9;
	private final double BOARD_PANE_PRECENT_HEIGHT = 0.7;
	private final double GAME_PLAY_PANEL_PRECENT_HEIGHT = 0.2;
		
	private double stageWidth;
	private double stageHeight;
	
	private final int NUM_OF_BUTTON_IN_BUTTON_PANE = 3;
	private HBox buttonPane;
	private Button newGameButton;
	private Button resetGameButton;
	private Button endGameButton;
	
	
	private final Font namePaneFont = new Font("Serif", 25);
	private HBox namePane;
	private Label playerName1Label;
	private TextField playerName1Text;
	private Label playerName2Label;
	private TextField playerName2Text;

	
	
	private Board board;
	
	private final Font playerPlayPaneFont = new Font("Serif", 15);
	private HBox playerPlayPane;
	
	private Label playPanelPlayer1Name;
	private TextField playPanelPlayer1Tiles; 
	private Button playPanelPlayer1PlayButton;
	
	private Label playPanelPlayer2Name;	
	private TextField playPanelPlayer2Tiles;
	private Button playPanelPlayer2PlayButton;

	

	
	private Player player1;
	private Player player2;
	
	private VBox menu;
	
	public Game(int width, int height) {
		this.stageWidth = width;
		this.stageHeight = height;
		
		this.setMinWidth(this.stageWidth);
		this.setMinHeight(this.stageHeight);
		
		initMenu();
	
		Scene scene = new Scene(menu);
		this.setScene(scene);
		this.setResizable(false);
	}
	
	


	public void resetGame() {
		
	}
	
	public void newGame() {
		
	}
	
	public void makeMove() {
		
	}
	
	public void endGame() {
		
	}
	
	
	private void checkWin() {
		
	}
	
	private void toggleTurn() {
		
	}
	

	private void initMenu() {
		this.menu = new VBox();
		this.menu.setPrefSize(this.getWidth(),this.getHeight());
		initButtonPane();
		this.menu.getChildren().add(this.buttonPane);
		//initBoard();
		//this.menu.setCenter(this.board);
		initPlayerNamePane();
		this.menu.getChildren().add(this.namePane);	
	}
	
	private void initButtonPane() {
		this.buttonPane = new HBox();
		double buttonPaneWidth = this.stageWidth;
		double buttonPaneHeight = this.stageHeight * BUTTON_PANE_PRECENT_HEIGHT;
		
		this.buttonPane.setPrefSize(buttonPaneWidth, buttonPaneHeight);
	
		this.newGameButton = new Button("New Game");
		this.newGameButton.setPrefSize(buttonPaneWidth/NUM_OF_BUTTON_IN_BUTTON_PANE, buttonPaneHeight);
		
		this.resetGameButton = new Button("Reset Game");
		this.resetGameButton.setPrefSize(buttonPaneWidth/NUM_OF_BUTTON_IN_BUTTON_PANE, buttonPaneHeight);
		this.resetGameButton.setDisable(true);
		
		this.endGameButton = new Button("End Game");
		this.endGameButton.setPrefSize(buttonPaneWidth/NUM_OF_BUTTON_IN_BUTTON_PANE, buttonPaneHeight);
		
		this.buttonPane.getChildren().addAll(newGameButton, resetGameButton, endGameButton);
		
		initButtonPaneFunctions();
	}
	
	private void initButtonPaneFunctions() {
		
		this.newGameButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				//TODO maybe add checks on players names
				player1 = new Player(playerName1Text.getText());
				player2 = new Player(playerName2Text.getText());
				
				menu.getChildren().remove(namePane);
				initBoard();
				initplayerPlayPane();
				menu.getChildren().add(board);
				menu.getChildren().add(playerPlayPane);
				
				//make reset button active after starting game
				resetGameButton.setDisable(false);
				
			}
		});
		
		this.resetGameButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//TODO maybe change how this work
				menu.getChildren().remove(board);
				menu.getChildren().remove(playerPlayPane);
				initBoard();
				menu.getChildren().add(board);
				menu.getChildren().add(playerPlayPane);
			}
		});
		
		this.endGameButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				me.close();
			}
		});
	}
	
	private void initPlayerNamePane() {
		double namePaneWidth = this.stageWidth;
		double namePaneHeight = this.stageHeight * START_MENU_NAMES_PRECENT_HEIGHT;
		
		this.namePane = new HBox();
		this.namePane.setPrefSize(namePaneWidth, namePaneHeight);
		this.namePane.setStyle("-fx-background-color: green");
		
		double innerHBoxPanesWidth = namePaneWidth/2;
		double innerHBoxPanesHeight = namePaneHeight;
		
		this.playerName1Label = createLabel("Player Name: ", innerHBoxPanesWidth/2, innerHBoxPanesHeight/10, namePaneFont);
		this.playerName2Label = createLabel("Player Name: ", innerHBoxPanesWidth/2, innerHBoxPanesHeight/10, namePaneFont);

		this.playerName1Text = createField("Enter Name",  innerHBoxPanesWidth/2, innerHBoxPanesHeight/10);
		this.playerName2Text = createField("Enter Name",  innerHBoxPanesWidth/2, innerHBoxPanesHeight/10);
	
		HBox leftPane = new HBox();
		leftPane.setPrefSize(innerHBoxPanesWidth, innerHBoxPanesHeight);
		leftPane.getChildren().addAll(playerName1Label, playerName1Text);
	
		HBox rightPane = new HBox();
		rightPane.setPrefSize(innerHBoxPanesWidth, innerHBoxPanesHeight);
		rightPane.getChildren().addAll(playerName2Label, playerName2Text);
			
		this.namePane.getChildren().addAll(leftPane,rightPane);
		
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
		this.playPanelPlayer1Tiles = createField("enter tile number", innerHBoxPanesWidth/2 , innerHBoxPanesHeight);
		this.playPanelPlayer1PlayButton = new Button("Play");
		this.playPanelPlayer1PlayButton.setPrefSize(innerHBoxPanesWidth/4, innerHBoxPanesHeight);;
		this.playPanelPlayer1PlayButton.setFont(namePaneFont);

		
		this.playPanelPlayer2Name = createLabel(player2.getName(),innerHBoxPanesWidth/4 , innerHBoxPanesHeight, playerPlayPaneFont);
		this.playPanelPlayer2Tiles = createField("enter tile number", innerHBoxPanesWidth/2 , innerHBoxPanesHeight);
		this.playPanelPlayer2PlayButton = new Button("Play");
		this.playPanelPlayer2PlayButton.setPrefSize(innerHBoxPanesWidth/4, innerHBoxPanesHeight);;
		this.playPanelPlayer2PlayButton.setFont(namePaneFont);
		
		HBox leftPane = new HBox();
		leftPane.setPrefSize(innerHBoxPanesWidth, playPlayPaneHeight);
		leftPane.getChildren().addAll(playPanelPlayer1Name, playPanelPlayer1Tiles,playPanelPlayer1PlayButton);
	
		HBox rightPane = new HBox();
		rightPane.setPrefSize(innerHBoxPanesWidth, playPlayPaneHeight);
		rightPane.getChildren().addAll(playPanelPlayer2Name, playPanelPlayer2Tiles,playPanelPlayer2PlayButton);
			
		this.playerPlayPane.getChildren().addAll(leftPane,rightPane);
		
		initPlayPanelButton();
	}

	private void initPlayPanelButton() {
		this.playPanelPlayer1PlayButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//TODO extract from playPanelPlayerTile1 the number and active method with it
				makeMove();
				
			}
		});
		
		this.playPanelPlayer2PlayButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//TODO extract from playPanelPlayerTile2 the number and active method with it
				makeMove();
				
			}
		});
	}
	

}
