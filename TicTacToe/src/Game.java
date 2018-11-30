import javax.swing.BorderFactory;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Game extends Stage {
	
	//for self refrencing inside button action events
	private final Game me = this;
	private Scene scene;
	
	private final double BUTTON_PANE_PRECENT_HEIGHT = 0.1;
	private final double BOARD_PANE_PRECENT_HEIGHT = 0.9;
		
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

	
	private Player player1;
	private Player player2;
	private Board board;
	
	private BorderPane menu;
	
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
		this.menu = new BorderPane();
		this.menu.setPrefSize(this.getWidth(),this.getHeight());
		initButtonPane();
		this.menu.setTop(this.buttonPane);
		//initBoard();
		//this.menu.setCenter(this.board);
		initPlayerNamePane();
		this.menu.setCenter(this.namePane);	
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
				// TODO remove namePane and add borad
				
			}
		});
		
		this.resetGameButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO reset board
				
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
		double namePaneHeight = this.stageHeight * BOARD_PANE_PRECENT_HEIGHT;
		
		this.namePane = new HBox();
		this.namePane.setPrefSize(namePaneWidth, namePaneHeight);
		this.namePane.setStyle("-fx-background-color: green");
		
		double innerHBoxPanesWidth = namePaneWidth/2;
		double innerHBoxPanesHeight = namePaneHeight;
		
		this.playerName1Label = createLabel("Player Name: ", innerHBoxPanesWidth/2, innerHBoxPanesHeight/10);
		this.playerName2Label = createLabel("Player Name: ", innerHBoxPanesWidth/2, innerHBoxPanesHeight/10);

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

	private Label createLabel(String labelText, double width, double height) {
		Label label = new Label(labelText);
		label.setPrefSize(width, height);
		label.setAlignment(Pos.CENTER);
		label.setFont(namePaneFont);
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
}
