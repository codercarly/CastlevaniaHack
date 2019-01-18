import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

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

public class CastlevaniaController {
	/*
	 *  Scene Builder FXML Controller Skeleton
	 */
	// Panes
    @FXML private Pane scorePane;
    @FXML private ScrollPane gamePane;
    @FXML private Rectangle floorGridPane;
    
    // Simon
    @FXML private ImageView simon;

    /*
     * Game Score Board
     */
    // Scoring Labels
    @FXML private Label scoreLabel;
    @FXML private Label timeLabel;
    @FXML private Label heartsLabel;
    @FXML private Label livesLabel;
    // Player Health Icons
    @FXML private Rectangle playerHeath1;
    @FXML private Rectangle playerHeath2;
    @FXML private Rectangle playerHeath3;
    @FXML private Rectangle playerHeath4;
    @FXML private Rectangle playerHeath5;
    @FXML private Rectangle playerHeath6;
    // Enemy Health Icons
    @FXML private Rectangle enemyHealth1;
    @FXML private Rectangle enemyHealth2;
    @FXML private Rectangle enemyHealth3;
    @FXML private Rectangle enemyHealth4;
    @FXML private Rectangle enemyHealth5;
    @FXML private Rectangle enemyHealth6;

    /*
     * Attack Items
     */
    @FXML private Group attackItems;
    // Ghosts
    @FXML private ImageView ghost1;
    @FXML private ImageView ghost2;
    @FXML private ImageView ghost3;
    @FXML private ImageView ghost4;
    @FXML private ImageView ghost5;
    @FXML private ImageView ghost6;
    @FXML private ImageView ghost7;
    // Wolves
    @FXML private ImageView wolf1;
    @FXML private ImageView wolf2;
    @FXML private ImageView wolf3;
    @FXML private ImageView wolf4;
    // Torches
    @FXML private ImageView torch1;
    @FXML private ImageView torch2;
    @FXML private ImageView torch3;
    @FXML private ImageView torch4;
    // Little Torches
    @FXML private ImageView littleTorch1;
    @FXML private ImageView littleTorch2;
    @FXML private ImageView littleTorch3;
    @FXML private ImageView littleTorch4;
    @FXML private ImageView littleTorch5;
    @FXML private ImageView littleTorch6;
    @FXML private ImageView littleTorch7;
    @FXML private ImageView littleTorch8;
    @FXML private ImageView littleTorch9;
    // Gargoyle
    @FXML private ImageView gargoyle;
    
    /*
     * Hit boxes
     */
    // Simon
    @FXML private Rectangle whipHitBox;
    @FXML private Rectangle simonHurtBox;
    // Ghosts
    @FXML private Rectangle ghost1HitBox;
    @FXML private Rectangle ghost2HitBox;
    @FXML private Rectangle ghost3HitBox;
    @FXML private Rectangle ghost4HitBox;
    @FXML private Rectangle ghost5HitBox;
    @FXML private Rectangle ghost6HitBox;
    @FXML private Rectangle ghost7HitBox;
    // Wolves
    @FXML private Rectangle wolf1HitBox;
    @FXML private Rectangle wolf2HitBox;
    @FXML private Rectangle wolf3HitBox;
    @FXML private Rectangle wolf4HitBox;
    // Torches
    @FXML private Rectangle torch1HitBox;
    @FXML private Rectangle torch2HitBox;
    @FXML private Rectangle torch3HitBox;
    @FXML private Rectangle torch4HitBox;
    // Little Torches
    @FXML private Rectangle littleTorch1HitBox;
    @FXML private Rectangle littleTorch2HitBox;
    @FXML private Rectangle littleTorch3HitBox;
    @FXML private Rectangle littleTorch4HitBox;
    @FXML private Rectangle littleTorch5HitBox;
    @FXML private Rectangle littleTorch6HitBox;
    @FXML private Rectangle littleTorch7HitBox;
    @FXML private Rectangle littleTorch8HitBox;
    @FXML private Rectangle littleTorch9HitBox;
    // Gargoyle
    @FXML private Rectangle gargoyleHitBox;
  
    /*
     * Additional Game Variables
     */
    @FXML private ImageView gameOver;
    private Timeline timelineAnimation;
    public AnimationTimer jump;  
    private int hpCount = 6;
    private boolean hurt;
    private int hurtCounter;
 
    /*
     * Initialize Game
     */
    public void initialize() {
    	// Get images for Simon
    	Image simonWalkRight = new Image("file:///Users/carly/eclipse-workspace/Castlevania/src/animatedSimonRight.gif");
    	Image simonWalkLeft = new Image("file:///Users/carly/eclipse-workspace/Castlevania/src/animatedSimonLeft.gif");
    	Image simonAttackRight = new Image("file:///Users/carly/eclipse-workspace/Castlevania/src/simonAttackRight.png");
    	Image simonSquat = new Image("file:///Users/carly/eclipse-workspace/Castlevania/src/simonSquat.png");
    	Image simonSquatLeft = new Image("file:///Users/carly/eclipse-workspace/Castlevania/src/simonSquatLeft.png");
    	
    	// Arrays for health bars
    	Rectangle[] pHealth = {playerHeath1, playerHeath2, playerHeath3, playerHeath4, playerHeath5, playerHeath6};
    	Rectangle[] eHealth = {enemyHealth1, enemyHealth2, enemyHealth3, enemyHealth4, enemyHealth5, enemyHealth6};
    	
    	// Theme Music
      	URL resource = getClass().getResource("themeMusic.mp3");
    	Media media = new Media(resource.toString());
    	MediaPlayer player = new MediaPlayer(media);
    	player.setOnEndOfMedia(new Runnable() {
    		public void run() {
    			player.stop();
    		}
    	});
    	
    	// Define a TimeLine animation for Simon
    	timelineAnimation = new Timeline();   

    	// Map HitBoxes/HurtBoxes to their respective imageViews
    	// Things that can hurt Simon
    	Rectangle[] hurtBoxes = {ghost1HitBox, ghost2HitBox, ghost3HitBox, ghost4HitBox, ghost5HitBox, ghost6HitBox, ghost7HitBox, 
    			wolf1HitBox, wolf2HitBox, wolf3HitBox, wolf4HitBox, gargoyleHitBox};
    	// Things that Simon can hurt
    	Rectangle[] hitBoxes = {torch1HitBox, torch2HitBox, torch3HitBox, torch4HitBox, littleTorch1HitBox, littleTorch2HitBox, 
    			littleTorch3HitBox, littleTorch4HitBox, littleTorch5HitBox, littleTorch6HitBox, littleTorch7HitBox, littleTorch8HitBox,
    			littleTorch9HitBox, ghost1HitBox, ghost2HitBox, ghost3HitBox, ghost4HitBox, ghost5HitBox, ghost6HitBox, ghost7HitBox,
    			wolf1HitBox, wolf2HitBox, wolf3HitBox, wolf4HitBox, gargoyleHitBox};
    	
    	// Put items in the HashMap
    	Map<Rectangle, ImageView> boxes = new HashMap<Rectangle, ImageView>();
    	boxes.put(torch1HitBox, torch1);
    	boxes.put(torch2HitBox, torch2);
    	boxes.put(torch3HitBox, torch3);
    	boxes.put(torch4HitBox, torch4);
    	boxes.put(littleTorch1HitBox, littleTorch1);
    	boxes.put(littleTorch2HitBox, littleTorch2);
    	boxes.put(littleTorch3HitBox, littleTorch3);
    	boxes.put(littleTorch4HitBox, littleTorch4);
    	boxes.put(littleTorch5HitBox, littleTorch5);
    	boxes.put(littleTorch6HitBox, littleTorch6);
    	boxes.put(littleTorch7HitBox, littleTorch7);
    	boxes.put(littleTorch8HitBox, littleTorch8);
    	boxes.put(littleTorch9HitBox, littleTorch9);
    	boxes.put(ghost1HitBox, ghost1);
    	boxes.put(ghost2HitBox, ghost2);
    	boxes.put(ghost3HitBox, ghost3);
    	boxes.put(ghost4HitBox, ghost4);
    	boxes.put(ghost5HitBox, ghost5);
    	boxes.put(ghost6HitBox, ghost6);
    	boxes.put(ghost7HitBox, ghost7);
    	boxes.put(wolf1HitBox, wolf1);
    	boxes.put(wolf2HitBox, wolf2);
    	boxes.put(wolf3HitBox, wolf3);
    	boxes.put(wolf4HitBox, wolf4);
    	boxes.put(gargoyleHitBox, gargoyle);

    	hurt = false;
    	hurtCounter = 1;

        /*
         * / Simon Belmont Action
         */
    	KeyFrame simonKeyFrame = new KeyFrame(Duration.millis(1), new EventHandler<ActionEvent>() {
    		// Initialize Hvalue for ScrollPane
    		double gamePaneHValue = 0.0;   	
    		
    		// Use the left and right arrows to move the paddle
			@Override
			public void handle(ActionEvent event) {
				// Start Music
		    	player.play();
		    	
				// If Simon goes left
    			if (Castlevania.goLeft) {
    				simon.setImage(simonWalkLeft);
    				gamePaneHValue -= 0.05;
    				gamePane.setHvalue(gamePaneHValue);  				
    			}
    			
    			// If Simon goes right
				if (Castlevania.goRight) {
					simon.setImage(simonWalkRight);
					gamePaneHValue += 0.05;
					gamePane.setHvalue(gamePaneHValue);
				}
        		
        		// If Simon jumps
        		if (Castlevania.jump) {
        			//Creating Translate Transition 
        			TranslateTransition translateTransition = new TranslateTransition();
        			//Setting the duration of the transition  
        			translateTransition.setDuration(Duration.millis(500)); 
        			translateTransition.interpolatorProperty().set(Interpolator.SPLINE(0.1, 0.0, 0.7, 0.0));
        			//Setting the node for the transition 
        			translateTransition.setNode(simon); 
        			//Setting the value of the transition along the y axis. 
        			translateTransition.setByY(-150); 
        			simon.setImage(simonSquat);
        			//Setting auto reverse value to true so Simon comes back down from the jump
        			translateTransition.setAutoReverse(true);
        			//Setting the cycle count for the transition 
        			translateTransition.setCycleCount(2);
        			//Playing the animation 
        			translateTransition.play(); 
        		}
        		
        		// If Simon crouches
        		if (Castlevania.crouch) {
        			if (simon.getImage() == simonWalkLeft) {
        				simon.setImage(simonSquatLeft);
        			} else {
        				simon.setImage(simonSquat);
        			}
        		}
        		
        		// Check if Simon was recently hurt
        		if (hurt) {
					if (hurtCounter < 500) {
						if (gamePaneHValue != 0) { 
							gamePaneHValue -= 0.5;
							gamePane.setHvalue(gamePaneHValue);
						}
					}
					if (hurtCounter < 2500) {
						hurtCounter++;
					} else {
						hurt = !hurt;
						hurtCounter = 1;
					}
				} 
        		
        		// If Simon attacks, check hitbox
	       		if (Castlevania.attack) {
	        			simon.setFitHeight(104);
	        			simon.setFitWidth(217);
	        			simon.setImage(simonAttackRight);
	        			whipHitBox.setLayoutX(simon.getLayoutX() + 108);
	        			whipHitBox.setLayoutY(simon.getLayoutY());
	        			for (Rectangle hitBox : hitBoxes) {
	        				if (attack(hitBox)) {
	        					attackItems.getChildren().remove(boxes.get(hitBox));
	        					attackItems.getChildren().remove(hitBox);
	        					int currentScore = Integer.parseInt(scoreLabel.getText());
	        					currentScore += 100;
	        					scoreLabel.setText(Integer.toString(currentScore));
	        					int heartCollection = Integer.parseInt(heartsLabel.getText());
	        					heartCollection += 1;
	        					heartsLabel.setText(Integer.toString(heartCollection));
	        				}
	        			}
	        		} // end hitbox check
	
	        		//If Simon's hurtbox collides with a hitbox
	        		simonHurtBox.setLayoutX(simon.getLayoutX());
	        		simonHurtBox.setLayoutY(simon.getLayoutY());
	        		for (Rectangle hurtBox : hurtBoxes) {
	        			if (simonCollide(hurtBox) && !hurt) {
	        				hurt = !hurt;
	        				if (hpCount > 1) {
	        					hpCount--;
	        					pHealth[hpCount].setVisible(false);
	        				} else {
	        					int lives = Integer.parseInt(livesLabel.getText());
	        					lives -= 1;
	        					livesLabel.setText(Integer.toString(lives));
	        					for (Rectangle bar : pHealth) {
	        						bar.setVisible(true);
	        					}
	        					hpCount = 6;
	        				}
	        				
	        				FadeTransition damaged = new FadeTransition(Duration.millis(100));
	        				damaged.setNode(simon);
	        				damaged.setFromValue(1.0);
	        				damaged.setToValue(0.1);
	        				damaged.setAutoReverse(true);
	        				damaged.setCycleCount(20);
	        				
	        				TranslateTransition knockback = new TranslateTransition(Duration.millis(500));
	        				knockback.setNode(simon);
	        				knockback.interpolatorProperty().set(Interpolator.SPLINE(0.1, 0.0, 0.7, 0.0));
	        				knockback.setAutoReverse(true);
	        				knockback.setCycleCount(2);
	        				knockback.setByY(-50);
	        				
	        				SequentialTransition playAll = new SequentialTransition(knockback, damaged);
	        				playAll.play();
	        			}
	        		}
				}
	    	});
    	
		// Indicate that the time line animation should run indefinitely & play
    	timelineAnimation.getKeyFrames().addAll(simonKeyFrame);
		timelineAnimation.setCycleCount(Timeline.INDEFINITE);
		timelineAnimation.play();
    }
	
	// Simon whip collision
	private boolean attack(Rectangle enemy) {   	
	    	Shape collision = Shape.intersect(whipHitBox, enemy);
	    	return collision.getBoundsInLocal().getWidth() > 0 && collision.getBoundsInLocal().getHeight() > 0;
	}
	
	// Simon hurtBox collision
	private boolean simonCollide(Rectangle enemy) {
	    	Shape collision = Shape.intersect(simonHurtBox, enemy);
	    	return collision.getBoundsInLocal().getWidth() > 0 && collision.getBoundsInLocal().getHeight() > 0;
	}
}