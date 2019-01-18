import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * CI-245
 * Final Project
 * 
 * Castlevania Hack!
 * 
 * @author Carly Coccaro
 * @author Zachary Kolek
 *
 */

public class Castlevania extends Application {

	public static boolean goLeft = false;
	public static boolean goRight = false;
	public static boolean jump = false;
	public static boolean crouch = false;
	public static boolean attack = false;

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("castlevania2.fxml"));
		
		Scene scene = new Scene(root);
	
		// Set key pressed event handler
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch(event.getCode()) {
				case LEFT: goLeft = true;
					break;
				case RIGHT: goRight = true;
					break;
				case UP: jump = true;
					break;
				case DOWN: crouch = true;
					break;
				case SPACE: attack = true;
					break;
				default:
					break;
				}	
			}	
		});
		
		// Set key release event handler
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch(event.getCode()) {
				case LEFT: goLeft = false;
					break;
				case RIGHT: goRight = false;
					break;
				case UP: jump = false;
					break;
				case DOWN: crouch = false;
					break;
				case SPACE: attack = false;
					break;
				default:
					break;
				}
			}
		});
		
		primaryStage.setTitle("Castlevania");
		primaryStage.setScene(scene);
		primaryStage.show();	
	}
	
	public static void main(String[] args) {
		launch(args);
	}	
}
