import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
	
	private final int WIDTH = 1000;
	private final int HEIGHT = 700;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Game game = new Game(WIDTH, HEIGHT);
	;
		game.show();
	}
}
