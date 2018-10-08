package view.graphical;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class Fenetre extends StackPane{
	Image img;
	ImageView icon;
	
	public Fenetre(Arene a,Information i){
		
		HBox hb = new HBox();
		VBox vb = new VBox();

		 
		MenuBar menuBar = new MenuBar();
		
		Menu fichier = new Menu("Acceuil");
		Menu change_bg = new Menu("Change Background");
		
		MenuItem quit = new MenuItem("Quitter");
		MenuItem bg1 =  new MenuItem("background1");
		MenuItem bg2 =  new MenuItem("background2");
		MenuItem bg3 =  new MenuItem("background3");
		MenuItem bg4 =  new MenuItem("background4");
	
		
		change_bg.getItems().addAll(bg1,bg2,bg3,bg4);
		fichier.getItems().add(quit);
		
		menuBar.getMenus().addAll(fichier,change_bg);
		
		hb.getChildren().addAll(i,a);
	    vb.getChildren().addAll(menuBar,hb);
	    
	    
	    img= new Image("/background/4573.jpg",true);
	    icon = new ImageView(img);
	    
		
		quit.setOnAction(new EventHandler<ActionEvent>() {
		
			public void handle(ActionEvent e) {
			Platform.exit();
			}
			});
		
		bg1.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				
			    img= new Image("/background/3267.jpg",true);
			    icon .setImage(img);
			   
			  
			}
			});
		bg2.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				
			    img= new Image("/background/3049.jpg",true);
			    icon .setImage(img);
			   
			  
			}
			});
		bg3.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				
			    img= new Image("/background/4573.jpg",true);
			    icon .setImage(img);
	
			  
			}
			});
		bg4.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				
			    img= new Image("/background/491.jpg",true);
			    icon .setImage(img);
			    
			  
			}
			});
		
		icon.fitWidthProperty().bind(widthProperty());
	    icon.fitHeightProperty().bind(heightProperty());
	    icon.setOpacity(0.7);
		getChildren().addAll(icon,vb);
		
		
	  
	}
}
