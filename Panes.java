import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import javafx.util.Duration;

import javafx.application.Application;

import javafx.collections.ObservableList;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.shape.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;

import javafx.geometry.Pos;

	//Oguzhan Toker 150118049 and Mevlut Eren Topal 150117025
	/*This program is a game that aims to bring the boxes to the appropriate position and thus bring the first ball to the end point. 
	Some boxes cannot move. These are the start, end box and empty box. 
	Also, the boxes cannot move diagonally*/

public class Panes extends Application { 
	
    Level level1 = new Level("level1");
    Level level2 = new Level("level2");
    Level level3 = new Level("level3");
    Level level4 = new Level("level4");
    Level level5 = new Level("level5");
	
    Level currentLevel = level1;
	
   
    static Media med = new Media(new File("solved.wav").toURI().toString());
    static MediaPlayer solved = new MediaPlayer(med);
   
    
    
   @Override     
   public void start(Stage primaryStage) throws Exception {            
      //creating a Group object 
      Group mainMenu = new Group();
      Group game = new Group();
      
       
      //Creating a Scene by passing the group object, height and width   
      Scene scene = new Scene(mainMenu, 500, 600); 

      Image ball = new Image(new FileInputStream("ball.gif"));
      ImageView ballV = new ImageView(ball);
      
      String themesong = "theme.mp3";                         //By using mediaplayer we added a theme song in the game.
      Media sounds = new Media(new File(themesong).toURI().toString());
      MediaPlayer theme = new MediaPlayer(sounds);
      theme.play();

      //For our sound to play again after they ended
      theme.setOnEndOfMedia(new Runnable() {
    	  public void run() {
    		  	theme.stop();
    		  theme.play();
    	  }
      });
        
      solved.setOnEndOfMedia(new Runnable() {
    	  public void run() {
    		  solved.stop();						
    	  }
      });     
      
      
      
      
      //Background Image we chose from Reddit/r-art. We made it black and white. And some scalings down below.
      Image backgr = new Image(new FileInputStream("BackGroundPH.jpg"));
      ImageView backgro = new ImageView(backgr);
      backgro.setX(0);
      backgro.setY(0);
      backgro.setFitHeight(700);
      backgro.setFitWidth(700);
      backgro.setPreserveRatio(true);
  
      //Name of our game: THE PIPEDPIPER
      Text text = new Text();
      text.setFont(new Font(60));
      text.setX(25);
      text.setY(175);
      text.setText("The PipedPiper");
      text.setFont(Font.loadFont(new FileInputStream("wolf.otf"),140));
      
      //We made button transparent so it looks like you are clicking to a text.
      Button playButton = new Button();
      Text play = new Text();
      play.setText("PLAY");
      play.setX(170);
      play.setY(350);
      play.setFill(Color.rgb(212,166,40));;
      play.setFont(Font.loadFont(new FileInputStream("wolf.otf"),100));
      playButton.setLayoutX(168);
      playButton.setLayoutY(305);
      playButton.setPrefSize(190, 65);
      playButton.setStyle("-fx-background-color: transparent;");
      

      
      
      //MUSIC BUTTON START
      Button musicB = new Button();
      musicB.setLayoutX(0);
      musicB.setLayoutY(560);
      musicB.setStyle("-fx-background-color: transparent");
      Image audioOn = new Image(new FileInputStream("audioOn.png"));
      Image audioOff = new Image(new FileInputStream("mute.png"));
      ImageView audioON = new ImageView(audioOn);
      ImageView audioOFF = new ImageView(audioOff);
      musicB.setGraphic(audioON);
      audioON.setFitHeight(40);
      audioON.setFitWidth(40);
      audioOFF.setFitHeight(40);
      audioOFF.setFitWidth(40);
      //MUSIC BUTTON END
      
      
      musicB.setOnMouseClicked(e->{
    	  if(theme.getVolume()==0) {
    		  theme.setVolume(10);
    		  musicB.setGraphic(audioON);
    		 // System.out.println("Button deneme");
    	  }else {
    		  theme.setVolume(0);
    		  musicB.setGraphic(audioOFF);
    		// System.out.println("Button Deneme 2")
    	  }
      });
      
      
     

		
		
		//Retrieving the observable list object 
	  ObservableList playScreenList = game.getChildren();	
      ObservableList mainMenuList = mainMenu.getChildren(); 
       
	
	Pane sPane = new Pane();
	Pane allNodes = new Pane();
	FlowPane tileBoard = new FlowPane();
	BorderPane borderPane = new BorderPane();
	borderPane.setStyle("-fx-background-color: silver");
			
	tileBoard.setAlignment(Pos.TOP_LEFT);
	tileBoard.setMaxWidth(480);
	tileBoard.setMinWidth(480);
	tileBoard.setMaxHeight(480);
	tileBoard.setMinHeight(480);
	
	HBox top = new HBox();
	top.setPrefHeight(40);
	
	Text feriha = new Text(20, 20, "The PipedPiper"); //Text at the top of game screen.
	feriha.setFont(Font.loadFont(new FileInputStream("wolf.otf"),50));
	
	top.getChildren().add(feriha);
	top.setAlignment(Pos.CENTER);
	
	borderPane.setTop(top);
			
	VBox first1 = new VBox();
	VBox first2 = new VBox();
	VBox first3 = new VBox();
	VBox first4 = new VBox();
	VBox second1 = new VBox();
	VBox second2 = new VBox();
	VBox second3 = new VBox();
	VBox second4 = new VBox();
	VBox third1 = new VBox();
	VBox third2 = new VBox();
	VBox third3 = new VBox();
	VBox third4 = new VBox();
	VBox fourth1 = new VBox();
	VBox fourth2 = new VBox();
	VBox fourth3= new VBox();
	VBox fourth4 = new VBox();
	
	VBox[] paneArray = {first1,first2,first3,first4,second1,second2,second3,second4,third1,third2,third3,third4,fourth1,fourth2,fourth3,fourth4}; //This ArrayList is for tileboard.
	
	ArrayList<Level> levels = new ArrayList<Level>();
	levels.add(level1); levels.add(level2); levels.add(level3); levels.add(level4); levels.add(level5);
	
	ArrayList<ImageView> imgArray = new ArrayList<ImageView>();
			
	
	//These all path statements for the slide animation when level change button pressed.
	PathTransition pathT1 = new PathTransition(); 
	PathTransition pathT2 = new PathTransition();
	PathTransition pathT3 = new PathTransition();
	PathTransition pathT4 = new PathTransition();
	Path path = new Path(new MoveTo(240, 239),
			new LineTo(-240,239)
          );
	Path path2 = new Path(new MoveTo(780,239),
			new LineTo(240,239)
          );
	Path reversePath = new Path(new MoveTo(240, 239),
			new LineTo(780,239)
          );
	Path reversePath2 = new Path(new MoveTo(-240,239),
			new LineTo(240,239)
          );
	pathT1.setDuration(Duration.millis(500));
	pathT1.setPath(path);
	pathT1.setNode(sPane);
	pathT2.setDuration(Duration.millis(500));
	pathT2.setPath(path2);
	pathT2.setNode(sPane);
	pathT3.setDuration(Duration.millis(500));
	pathT3.setPath(reversePath);
	pathT3.setNode(sPane);
	pathT4.setDuration(Duration.millis(500));
	pathT4.setPath(reversePath2);
	pathT4.setNode(sPane);						
	/////////////////////////////////////////////////////////////////////////////////////
	
    BorderPane borderpane = new BorderPane();
    borderpane.setStyle("-fx-background-color: silver");	//GECICI
    
    Button nextLv = new Button("Next Level");
    Button prevLv= new Button("Previous Level");
    Button returnMain = new Button("Main Menu");
    
    HBox buttons = new HBox();
    
    borderpane.setBottom(buttons);
    buttons.setAlignment(Pos.CENTER);
    
    nextLv.setMinSize(100, 40);
    prevLv.setMinSize(100, 40);
    returnMain.setMinSize(100, 40);
    buttons.getChildren().addAll(prevLv, returnMain, nextLv);
	
	borderPane.setBottom(buttons);

	
	nextLv.setOnMouseClicked(e -> {	//Event handler for nextLevel button. Changes the VBoxs' ImageVýews.
			if (currentLevel.isCompleted()) {
				System.out.println("clickedNext");
				againOrNot(paneArray, imgArray);
				currentLevel = levels.get(levels.indexOf(currentLevel) < 4 ? levels.indexOf(currentLevel) + 1 : 4);
				pathT1.play();
				pathT1.setOnFinished(event -> {
					pathT1.stop();
					pathT2.play();
						pathT2.setOnFinished(event2 -> {
							pathT2.stop();
						});
					try {
						Level.showLevel(currentLevel, paneArray, imgArray); //This statement changes the VBoxs' ImageViews.
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					feriha.setText(currentLevel.getLevel());
				});
			}
			else
				feriha.setText("You should complete previous level(s) first!");
		
	});
	
	prevLv.setOnMouseClicked(e -> {	//Event handler for prevLevel button. Changes the VBoxs' ImageVýews.
			System.out.println("clickedPrev");
			againOrNot(paneArray, imgArray);
			currentLevel = levels.get(levels.indexOf(currentLevel) > 0 ? levels.indexOf(currentLevel) - 1 : 0);
			pathT3.play();
			pathT3.setOnFinished(event -> {
				pathT3.stop();
				pathT4.play();
					pathT4.setOnFinished(event2 -> {
						pathT4.stop();
					});
				try {
					Level.showLevel(currentLevel, paneArray, imgArray); //This statement changes the VBoxs' ImageViews.
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				feriha.setText(currentLevel.getLevel());
			});
	});
	

	
	returnMain.setOnMouseClicked(e -> { //This event handler changes the scene to mainMenu scene if user clicks the button with house image.
		primaryStage.setScene(scene);
		System.out.println("Clicked");
	});
	
	for (int i = 0; i < 16; i++) { //This loop assigns the drag event to all VBox's.
		int j = i;
		paneArray[i].setOnMouseDragged(e -> {
			System.out.println("surukleniyor");
		Movement(j, e.getX(), e.getY(), paneArray, imgArray);
		});
	}
	for (int i = 0; i < 16; i++) {
		paneArray[i].setOnMouseReleased(f -> {
			if (Level.isSolved(imgArray)) { //This if statement checks if the level is completed or not.
				solved.play();
				currentLevel.setCompleted(true);
				currentLevel.solve(currentLevel, ballV); //Moves the ball from start point to end point.
				feriha.setText(currentLevel.getLevel() + " is completed."); //Sets the text top of the scene.
				System.out.println(currentLevel.getLevel() + " is completed.");
				for (int j = 0; j < 16; j++) { //Assigns null to all VBoxes because level is finished.
					paneArray[j].setOnMouseDragged(null);
				}
			}
		});
	}
	
	allNodes.getChildren().add(borderPane);
	
	tileBoard.getChildren().addAll(first1,first2,first3,first4,second1,second2,second3,second4,third1,third2,third3,third4,fourth1,fourth2,fourth3,fourth4);
	tileBoard.maxHeight(480);
	tileBoard.maxWidth(480);		
	borderPane.setCenter(sPane);
	
	sPane.getChildren().addAll(tileBoard, ballV);
			
	Scene temp = new Scene(allNodes, 480, 560);

    playButton.setOnMouseClicked(e -> { //Event handlers for interactive shape-like button.
			primaryStage.setScene(temp);
			try {
				Level.showLevel(currentLevel, paneArray, imgArray);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			
		};
	});
    
    
    
    
    mainMenuList.add(backgro);  
    mainMenuList.add(text);
    mainMenuList.add(play);
    mainMenuList.add(playButton);
    mainMenuList.add(musicB);
    //Setting the title to Stage. 
    primaryStage.setTitle("The PipedPiper"); 
 
    //Adding the scene to Stage 
    primaryStage.setScene(scene); 
    
    primaryStage.setResizable(false);
     
    //Displaying the contents of the stage 
    primaryStage.show(); 
 }   
	
   public static void againOrNot (VBox[] arr, ArrayList<ImageView> imgArr) { //After changing an image in Movement method, source VBox's listener assigned null. This method reassigns the Movement method if the level is not completed.
	for (int i = 0; i < 16; i++) {
		int j = i;
		arr[i].setOnMouseDragged(e -> {
		Movement(j, e.getX(), e.getY(), arr, imgArr);
		});
	}
}

   public static void Movement (int i, double x, double y, VBox[] arr, ArrayList<ImageView> imgArr) { //This method simply swappes the dragged VBox's images, according to event's x and y property.
	int x1 = i / 4 + 1; int x2 = i % 4 + 1; //These are the row and column index of dragged tile.
	if (x < 120 && x > 0 && y > 120 && !(i == 12 || i == 13 || i == 14 || i == 15) && check(imgArr, i+4, i)) {
		System.out.println("Index " + x1 + ", " + x2 + " slided down."); //This statement indicates which tile move to which direction.
		ImageView temp = imgArr.get(i+4);
		arr[i+4].getChildren().clear();
		arr[i+4].getChildren().add(imgArr.get(i));
		arr[i].getChildren().clear();
		arr[i].getChildren().add(imgArr.get(i+4));
		imgArr.set(i+4, imgArr.get(i));
		imgArr.set(i, temp);
	}
	else if (y < 120 && y > 0 && x > 120 && !(i == 3 || i == 7 || i == 11 || i == 15) && check(imgArr, i+1, i)){
		System.out.println("Index " + x1 + ", " + x2 + " slided right."); //This statement indicates which tile move to which direction.
		ImageView temp = imgArr.get(i+1);
		arr[i+1].getChildren().clear();
		arr[i+1].getChildren().add(imgArr.get(i));
		arr[i].getChildren().clear();
		arr[i].getChildren().add(imgArr.get(i+1));
		imgArr.set(i+1, imgArr.get(i));
		imgArr.set(i, temp);
	}
	else if (y < 120 && y > 0 && x < 0 && !(i == 0 || i == 4 || i == 8 || i == 12) && check(imgArr, i-1, i)) {
		System.out.println("Index " + x1 + ", " + x2 + " slided left."); //This statement indicates which tile move to which direction.
		ImageView temp = imgArr.get(i-1);
		arr[i-1].getChildren().clear();
		arr[i-1].getChildren().add(imgArr.get(i));
		arr[i].getChildren().clear();
		arr[i].getChildren().add(imgArr.get(i-1));
		imgArr.set(i-1, imgArr.get(i));
		imgArr.set(i, temp);
	}
	else if (y < 0 && x > 0 && x < 120 && !(i == 0 || i == 1 || i == 2 || i == 3) && check(imgArr, i-4, i)){
		System.out.println("Index " + x1 + ", " + x2 + " slided up."); //This statement indicates which tile move to which direction.
		ImageView temp = imgArr.get(i-4);
		arr[i-4].getChildren().clear();
		arr[i-4].getChildren().add(imgArr.get(i));
		arr[i].getChildren().clear();
		arr[i].getChildren().add(imgArr.get(i-4));
		imgArr.set(i-4, imgArr.get(i));
		imgArr.set(i, temp);
	}
}

   public static boolean check (ArrayList<ImageView> imgArray, int k, int currentPlace) { //Chekcs if the movement can be done or not.
	
	Image freeNode = new Image("tiles/EmptyFree.png");
	Image EH = new Image("tiles/EndHorizontal.png");
	Image EV = new Image("tiles/EndVertical.png");
	Image PS01 = new Image("tiles/PipeStatic01.png");
	Image PSH = new Image("tiles/PipeStaticHorizontal.png");
	Image PSV = new Image("tiles/PipeStaticVertical.png");
	Image SH = new Image("tiles/StarterHorizontal.png");
	Image SV = new Image("tiles/StarterVertical.png");
	
	//k is the destination tile's index.		
	
	if (ImaCompare(imgArray.get(k).getImage(), freeNode) && !ImaCompare(imgArray.get(currentPlace).getImage(), freeNode)) {
		if (!ImaCompare(imgArray.get(currentPlace).getImage(), EH) && !ImaCompare(imgArray.get(currentPlace).getImage(), EV)
				&& !ImaCompare(imgArray.get(currentPlace).getImage(), PS01) && !ImaCompare(imgArray.get(currentPlace).getImage(), PSH)
				&& !ImaCompare(imgArray.get(currentPlace).getImage(), PSV) && !ImaCompare(imgArray.get(currentPlace).getImage(), SH)
				&& !ImaCompare(imgArray.get(currentPlace).getImage(), SV)) {
			return true;
		}
	}
	
	return false;
}

   public static boolean ImaCompare (Image i1, Image i2) { //This method compares two images and if they are same returns true, otherwise returns false.
	for (int i = 0; i < i1.getHeight(); i++)
	{
	  for (int j = 0; j < i1.getWidth(); j++)
	  {
	    if (!i1.getPixelReader().getColor(i, j).equals(i2.getPixelReader().getColor(i, j))) 
	    	return false;
	  }
	}
	return true;
}
      
   public static void main(String args[]){          
      launch(args);     
   }         
} 