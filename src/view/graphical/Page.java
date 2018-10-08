package view.graphical;
  


import java.util.HashMap;
import java.util.Random;

import common.Joueur;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import model.MyDirection;
import model.Pistolero;


public class Page extends Application  {


	Button jouer;
	Arene arene;
	Information info;
	Pistolero p ;
	AnimationTimer timer;
	boolean isPaused;
	
	public static String nom;
	TextField saisirPseudo = new TextField ();
    static HashMap<KeyCode,Boolean> keys = new HashMap<KeyCode, Boolean>();
	
	
	public void start(Stage stage) throws Exception {
		
		jouer=new Button("jouer");
		Scene s = new Scene(acceuil(), 500, 500,Color.BLACK);
		stage.setTitle("Shoot Them Up"); 
		stage.setScene(s);    
		isPaused = false;
		Random r = new Random();
		
		jouer.setOnAction(e->{
				
				nom = saisirPseudo.getText();                    
				System.out.println(nom);
				Joueur j = new Joueur(nom);
				boolean b=j.ajoutJoueur();
				System.out.println(b);
				stage.close();
				
				initGame();
				Scene jeu = new Scene(new Fenetre(arene,info));
				jeu.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
				      if(key.getCode()==KeyCode.SPACE) {
				    	  arene.shootBalle(p);
				      }
				      
				});
				stage.setScene(jeu);
				
				
		         jeu.setOnKeyPressed(event->keys.put(event.getCode(),true));
		         jeu.setOnKeyReleased(event->{
		                 keys.put(event.getCode(),false);
		         });
		          timer = new AnimationTimer(){
	                   private long now;
	                   private long diff = 0, timerD = 0, timerBouge = 0;
	               		private long uniteTime = 100000000;
					@Override
	                   public void handle(long now) {
	                	   Arene.vampiresFem.forEach(vam->{
	                		   this.now = now;
	                		   diff = now - timerD;
	                			diff = now - timerBouge;
	                			if (diff > 20 * uniteTime){
	                				vam.setDirection(r.nextInt(4)+1);
	                				timerD = now;
	                			}
	                			if( diff > 1000000){
	                				vam.bougeVampire(arene);
	                				timerBouge = now;
	                			}
		                    	 vam.bougeVampire(arene);
		                     });
	                	   Arene.vampiresMal.forEach(vam->{
	                		   this.now = now;
	                		   diff = now - timerD;
	                			diff = now - timerBouge;
	                			if (diff > 20 * uniteTime){
	                				vam.setDirection(r.nextInt(4)+1);
	                				timerD = now;
	                			}
	                			if( diff > 1000000){
	                				vam.bougeVampire(arene);
	                				timerBouge = now;
	                			}
		                    	 vam.bougeVampire(arene);
		                     });
	                	   Arene.getInt().bougeInt(p);
	                	   arene.updateVampire();
	                     update(p,arene);
	                     if(arene.aGagne()){
	                    	 timer.stop();
	                     }
	                    
	                   }
						
		         };
		        timer.start();
		        
				stage.show();

			
		});
		
		stage.show(); 


	}
	
	
	public  void update(Pistolero p,Arene a){
		
        if(isPressed(KeyCode.UP)){
           // p.getAnimation().play();
           // p.getAnimation().setOffsetY(96);
        	p.setDirection(MyDirection.UP);
            p.movePosY(-(int) info.getSliderVitesse());
            
        }else if(isPressed(KeyCode.DOWN)){
            //p.getAnimation().play();
            //p.getAnimation().setOffsetY(0);
        	p.setDirection(MyDirection.DOWN);
            p.movePosY((int) info.getSliderVitesse());
        
        }else if(isPressed(KeyCode.LEFT)){
            //p.getAnimation().play();
            //p.getAnimation().setOffsetX(48);
        	p.setRotationAxis(Rotate.Y_AXIS);
        	p.setRotate(-360);
        	p.setDirection(MyDirection.LEFT);
            p.movePosX(-(int) info.getSliderVitesse());
        
        }else if(isPressed(KeyCode.RIGHT)){
            //p.getAnimation().play();
            //p.getAnimation().setOffsetX(48);
        	p.setRotationAxis(Rotate.Y_AXIS);
        	p.setRotate(180);
        	p.setDirection(MyDirection.RIGHT);
            p.movePosX((int) info.getSliderVitesse());
        
        }/*else if(isPressed(KeyCode.SPACE)){
        	a.shootBalle(p);
        	
        	
        }*/
    }
	
    public static boolean isPressed(KeyCode k){
            return keys.getOrDefault(k,false);
                    
    }
	
	public void initGame(){
		
		info = new Information();
		p = new Pistolero(20,20);
		arene = new Arene(p);
        arene.getChildren().addAll(p);
        info.bindings(p);
        pauseButton();
       
	}
	public BorderPane acceuil(){
		BorderPane borderPane = new BorderPane();
		VBox vbox = new VBox(8);

		Text titre = new Text("SHOOT THEM UP"); 
		titre.setFill(Color.BLACK); 
		Font times30BoldItalicFont = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 40);
		titre.setFont(times30BoldItalicFont);
		titre.setTextAlignment(TextAlignment.CENTER);

		Text pseudo= new Text("PSEUDO");
		pseudo.setFill(Color.BLACK);
		pseudo.setTextAlignment(TextAlignment.CENTER);
		
		nom = saisirPseudo.getText();
		System.out.print("la la la"+nom+saisirPseudo.getText());
		//saisirPseudo=saisirPseudo.getText();
		saisirPseudo.setMaxWidth(200);
		vbox.getChildren().addAll(titre,pseudo,saisirPseudo,jouer);
		vbox.setAlignment(Pos.CENTER);
		borderPane.setCenter(vbox);
		return borderPane;
	}

	public void pauseButton(){
		HBox hbox7 = new HBox();
		Image img = new Image("/pause.png",true);
		Image img2 = new Image("/play.png",true);
		ImageView icon = new ImageView(img);
	    Button btn = new Button(){
	    	public void requestFocus() { }
	    };
	    btn.setOnAction(e -> { 
	    	if(isPaused){
	    		icon.setImage(img2);
	    		isPaused = false;
	    		timer.stop();
	    	}else{
	    		icon.setImage(img);
	    		isPaused = true;
	    		timer.start();
	    	}
	     } 
	        );
	    btn.setAlignment(Pos.CENTER_LEFT);
	    btn.setGraphic(icon);
        hbox7.getChildren().addAll(btn);
        info.getChildren().add(hbox7);
        
	}
	public static void main(String args[]){
		
		System.out.println("bonjouuuuuur");
		
		launch(args); 

	}

}
