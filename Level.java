import java.io.File;

import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Scanner;
import javafx.util.Duration;

import javafx.scene.shape.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

import javafx.animation.PathTransition;


//Oguzhan Toker 150118049 and Mevlut Eren Topal 150117025

public class Level {

	private String level;
	private boolean completed;
	
	Level (String s) {
	
		level = s;
		
	}
	
	public static void showLevel (Level lvl, VBox[] vb, ArrayList<ImageView> imgArr) throws FileNotFoundException { //This method changes the board VBoxs' ImageView according to given Level instance.
		File input = new File("Leveltxts/"+ lvl.getLevel() + ".txt");
		//System.out.println(input.getAbsolutePath()); DEBUGGING
		Scanner scan = new Scanner(input);
		imgArr.clear();
		while(scan.hasNext()) { //Reads from .txt file and creates the board
			String s = scan.next();
			System.out.println(s);
			String[] stringArray = s.split(",");
			vb[Integer.parseInt(stringArray[0])-1].getChildren().clear();
			vb[Integer.parseInt(stringArray[0])-1].getChildren().add(InsertImage(stringArray[1],stringArray[2]));
			imgArr.add(InsertImage(stringArray[1],stringArray[2]));
		}
		scan.close();
	}
	
	public static ImageView InsertImage(String s1, String s2) { //Selects image from directory with the given file names as String.
		String s = s1 + s2 + ".png";
		//System.out.println(s);  DEBUGGING
		Image img = new Image("tiles/" + s);		
		ImageView image = new ImageView(img);
		image.setFitHeight(120);
		image.setFitWidth(120);
		return image;
	}
	
	public static boolean isSolved (ArrayList<ImageView> imgArr) { //Checks whether the current level is finished or not.
				
		int startIndex = 7;
		int index = 0;
		boolean isSolved = true;
				
		Image forCompare = new Image ("tiles/EmptyNone.png");
		Image EH = new Image("tiles/EndHorizontal.png");
		Image EV = new Image("tiles/EndVertical.png");
		Image PS01 = new Image("tiles/PipeStatic01.png");
		Image PSH = new Image("tiles/PipeStaticHorizontal.png");
		Image PSV = new Image("tiles/PipeStaticVertical.png");
		Image SH = new Image("tiles/StarterHorizontal.png");
		Image SV = new Image("tiles/StarterVertical.png");
		Image PV = new Image("tiles/PipeVertical.png");
		Image PH = new Image("tiles/PipeHorizontal.png");
		Image P00 = new Image("tiles/Pipe00.png");
		Image P01 = new Image("tiles/Pipe01.png");
		Image P10 = new Image("tiles/Pipe10.png");
		Image P11 = new Image("tiles/Pipe11.png");

		for (int i = 0; i < imgArr.size(); i++) {
			if (Panes.ImaCompare(imgArr.get(i).getImage(), SH) || Panes.ImaCompare(imgArr.get(i).getImage(), SV)) { //Finds the starter tile.
				startIndex = i;
				forCompare = imgArr.get(i).getImage();
				break;
			}
		}
		
		//Next statements follows the solution path, if there is a incompatible tile, changes isSolved method to false, otherwise the method returns true.
		int to = 0;
		if (Panes.ImaCompare(forCompare, SH)) {
			to = -1;
			index = startIndex;
			while (isSolved) {
				if (index < 0 || index > imgArr.size() - 1)
					isSolved = false;
				if (Panes.ImaCompare(EH, imgArr.get(index).getImage()) || Panes.ImaCompare(EV, imgArr.get(index).getImage()))
					break;
				
				switch(to) {
				case -1: 
					if (Panes.ImaCompare(PH, imgArr.get(index + to).getImage()) || Panes.ImaCompare(PSH, imgArr.get(index + to).getImage())) {
						index--;
					}
					else if (Panes.ImaCompare(P01, imgArr.get(index + to).getImage()) || Panes.ImaCompare(PS01, imgArr.get(index + to).getImage())) {
						index--;
						to = -4;
					}
					else if (Panes.ImaCompare(P11, imgArr.get(index + to).getImage())) {
						index--;
						to = 4;
					}
					else
						isSolved = false;
					break;
				case  1:
					if (Panes.ImaCompare(PH, imgArr.get(index + to).getImage()) || Panes.ImaCompare(PSH, imgArr.get(index + to).getImage())) {
						index++;
					}
					else if (Panes.ImaCompare(P00, imgArr.get(index + to).getImage())) {
						index++;
						to = -4;
					}
					else if (Panes.ImaCompare(P10, imgArr.get(index + to).getImage())) {
						index++;
						to = 4;
					}
					else
						isSolved = false;
					break;
				case -4:
					if (Panes.ImaCompare(PSV, imgArr.get(index + to).getImage()) || Panes.ImaCompare(PV, imgArr.get(index + to).getImage())) {
						index -= 4;
					}
					else if (Panes.ImaCompare(P10, imgArr.get(index + to).getImage())) {
						index -= 4;
						to = -1;
					}
					else if (Panes.ImaCompare(P11, imgArr.get(index + to).getImage())) {
						index -= 4;
						to = 1;
					}
					else
						isSolved = false;
					break;
				case  4:
					if (Panes.ImaCompare(PV, imgArr.get(index + to).getImage()) || Panes.ImaCompare(PSV, imgArr.get(index + to).getImage())) {
						index += 4;
					}
					else if (Panes.ImaCompare(P01, imgArr.get(index + to).getImage()) || Panes.ImaCompare(PS01, imgArr.get(index + to).getImage())) {
						index += 4;
						to = 1;
					}
					
					else if (Panes.ImaCompare(P00, imgArr.get(index + to).getImage())) {
						index += 4;
						to = -1;
					}
					else
						isSolved = false;
					break;
				}
			}
		}
		
		else {
			to = 4;
			index = startIndex;
			while (isSolved) {
				if (index < 0 || index > imgArr.size() - 1)
					isSolved = false;
				
				switch(to) {
				case -1: 
					if (Panes.ImaCompare(PH, imgArr.get(index + to).getImage()) || Panes.ImaCompare(PSH, imgArr.get(index + to).getImage())) {
						index--;
					}
					else if (Panes.ImaCompare(P01, imgArr.get(index + to).getImage()) || Panes.ImaCompare(PS01, imgArr.get(index + to).getImage())) {
						index--;
						to = -4;
					}
					else if (Panes.ImaCompare(P11, imgArr.get(index + to).getImage())) {
						index--;
						to = 4;
					}
					else {
						isSolved = false;
					}
					break;
				case  1:
					if (Panes.ImaCompare(PH, imgArr.get(index + to).getImage()) || Panes.ImaCompare(PSH, imgArr.get(index + to).getImage())) {
						index++;
					}
					else if (Panes.ImaCompare(P00, imgArr.get(index + to).getImage())) {
						index++;
						to = -4;
					}
					else if (Panes.ImaCompare(P10, imgArr.get(index + to).getImage())) {
						index++;
						to = 4;
					}
					else {
						isSolved = false;
					}
					break;
				case -4:
					if (Panes.ImaCompare(PSV, imgArr.get(index + to).getImage()) || Panes.ImaCompare(PV, imgArr.get(index + to).getImage())) {
						index -= 4;
					}
					else if (Panes.ImaCompare(P10, imgArr.get(index + to).getImage())) {
						index -= 4;
						to = -1;
					}
					else if (Panes.ImaCompare(P11, imgArr.get(index + to).getImage())) {
						index -= 4;
						to = 1;
					}
					else {
						isSolved = false;
					}
					break;
				case  4:
					if (Panes.ImaCompare(PV, imgArr.get(index + to).getImage()) || Panes.ImaCompare(PSV, imgArr.get(index + to).getImage())) {
						index += 4;
					}
					else if (Panes.ImaCompare(P01, imgArr.get(index + to).getImage()) || Panes.ImaCompare(PS01, imgArr.get(index + to).getImage())) {
						index += 4;
						to = 1;
					}
					else if (Panes.ImaCompare(P00, imgArr.get(index + to).getImage())) {
						index += 4;
						to = -1;
					}
					else {
						isSolved = false;
					}
					break;
				}
				
			}
			
			index += to;
			if (Panes.ImaCompare(EH, imgArr.get(index).getImage()) || Panes.ImaCompare(EV, imgArr.get(index).getImage())) {
				return true;
			}
				
		}
		
		return isSolved;
	}
	
	public void solve (Level currentLevel, ImageView ballView) { //Shows the animation when user finish currentLevel, works statically.
		PathTransition pt1 = new PathTransition();
		if (currentLevel.getLevel().equals("level1") || currentLevel.getLevel().equals("level2") || currentLevel.getLevel().equals("level3")) {
			Path path = new Path(new MoveTo(57, 50),
	                new LineTo(57, 360),
	                new LineTo(77, 410),
	                new LineTo(120,423),
	                new LineTo(422,423));
			pt1.setDuration(Duration.millis(4000));
			pt1.setPath(path);
			pt1.setNode(ballView);
			pt1.play();
		}
		else {
			Path path = new Path(new MoveTo(57, 50),
	                new LineTo(57, 240),
	                new LineTo(77, 290),
	                new LineTo(120, 303),
	                new LineTo(360, 303),
					new LineTo(403, 290),
					new LineTo(422, 240),
					new LineTo(422, 180));
			pt1.setDuration(Duration.millis(3500));
			pt1.setPath(path);
			pt1.setNode(ballView);
			pt1.play();
		}
		pt1.setOnFinished(e -> {
			pt1.stop();
		});
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
}
