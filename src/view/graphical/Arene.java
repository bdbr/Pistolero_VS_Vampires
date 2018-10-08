package view.graphical;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import model.Balle;
import model.MyDirection;
import model.Obstacle;
import model.Pistolero;
import model.Vampire;
import javafx.geometry.Bounds;


public class Arene extends Pane {

	//GraphicsContext context;
	public static List<Obstacle> obstacles;
	public static List<Vampire> vampiresFem;
	public static List<Vampire> vampiresMal;
    static Vampire vampireInt  = new Vampire(true, 2, Vampire.vampireUrlFem);
    public static SimpleIntegerProperty nbVampireDansArene;
	
	Pistolero p;
	public Arene(Pistolero p){
		
	this.p=p;

		Random d = new Random();
		
		setPrefSize(800,600);
		setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
		        + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
		        + "-fx-border-radius: 5;" + "-fx-border-color: blue;");
		//context = getGraphicsContext2D();
		obstacles = new ArrayList<Obstacle>();
		vampiresFem = new ArrayList<Vampire>();
		vampiresMal = new ArrayList<Vampire>();
		for(int i=0;i<3;i++){
			
			int x = (int)(Math.random()*750);
			int y = (int)(Math.random()*550);
			Vampire vampire = new Vampire(true,d.nextInt(3),Vampire.vampireUrlFem);
			getChildren().addAll(vampire);
			vampire.movePosX(x);
			vampire.movePosY(y);
			vampiresFem.add(vampire);
		}
		
		for(int i=0;i<3;i++){
			
			int x = (int)(Math.random()*750);
			int y = (int)(Math.random()*550);
			Vampire vampire = new Vampire(false,d.nextInt(3),Vampire.vampireUrlMal);
			getChildren().addAll(vampire);
			vampire.movePosX(x);
			vampire.movePosY(y);
			vampiresMal.add(vampire);
			
			
		}
		
		getChildren().add(vampireInt);
		vampiresFem.add(vampireInt);
		vampireInt.setX(300);
		vampireInt.setY(300);
		vampireInt.bougeInt(p);
		
		for(int i=0;i<5;i++){
			boolean ok = true;
			int x = (int)(Math.random()*750);
			int y = (int)(Math.random()*550);
			Obstacle obstacle = new Obstacle(25,25,true,Obstacle.obstacleUrl);
			getChildren().addAll(obstacle);
			obstacle.movePosX(x);
			obstacle.movePosY(y);
			for (Obstacle o : obstacles) {
				if(o.getBoundsInParent().intersects(obstacle.getBoundsInParent())){
					ok = false;
					getChildren().remove(obstacle);
					break;    
				}
				
				
			} 
			if(ok)
			obstacles.add(obstacle);
		}
		
		for(int i=0;i< 4;i++){
			boolean ok = true;
			int x = (int)(Math.random()*750);
			int y = (int)(Math.random()*550);
			Obstacle obstacle = new Obstacle(40,60,false,Obstacle.obstacleNAUrl);
			getChildren().addAll(obstacle);
			obstacle.movePosX(x);
			obstacle.movePosY(y);
			for (Obstacle o : obstacles) {
				if(o.getBoundsInParent().intersects(obstacle.getBoundsInParent())){
					ok = false;
					getChildren().remove(obstacle);
					break;    
				}
				
				
			} 
			if(ok)
			obstacles.add(obstacle);
		}
		nbVampireDansArene = new SimpleIntegerProperty(vampiresFem.size()
				+vampiresMal.size());
	
	}
	


public  void shootBalle(Pistolero p) 
	
	{
		
		int n = 0;;
		int offset = 10;
		String u = getClass().getResource("/son.wav").toString();
		 AudioClip audio = new AudioClip(u);

		double x=0,y=0;
		Balle b = new Balle(10,10);
		TranslateTransition tt = new  TranslateTransition(Duration.millis(5000),b);
		tt.setCycleCount(1);
		tt.setOnFinished(new EventHandler<ActionEvent>(){
			 
	            @Override
	            public void handle(ActionEvent arg0) {
	            	getChildren().remove(b);
	            	arg0.consume();
	            }
	        });
		
		switch (p.getDirection()) {
		case UP:
			offset +=13;
			audio.play();
			x = (p.getBoundsInParent().getMaxX() +
					p.getBoundsInParent().getMinX())/2;
        	y = (p.getBoundsInParent().getMaxY() + 
        			p.getBoundsInParent().getMinY())/2;
        	tt.setFromX(x);
    		tt.setFromY(y-offset);
    		tt.setToY(y-500);
        	
		break;
        case DOWN:
        	offset+=23;
        	audio.play();
        	x = (p.getBoundsInParent().getMaxX()+
        			p.getBoundsInParent().getMinX())/2;
        	y = (p.getBoundsInParent().getMaxY()+
        			p.getBoundsInParent().getMinY())/2;
        	
        	tt.setFromX(x);
    		tt.setFromY(y+offset);

    		tt.setToY(y+500);

		break;
        case LEFT: 
        	offset+=10;
        	audio.play();
        	x = (p.getBoundsInParent().getMaxX()+
        			p.getBoundsInParent().getMinX())/2;
        	y = (p.getBoundsInParent().getMaxY()+
        			p.getBoundsInParent().getMinY())/2;
        	tt.setFromX(x-offset);
    		tt.setFromY(y);
    		tt.setToX(x-500);

        	

			
		break; 
        case RIGHT: 
        	audio.play();	
        	x = (p.getBoundsInParent().getMaxX()+
        			p.getBoundsInParent().getMinX())/2;
        	y = (p.getBoundsInParent().getMaxY()+
        			p.getBoundsInParent().getMinY())/2;
        	tt.setFromX(x+offset);
    		tt.setFromY(y);
    		tt.setToX(x+500);


        break;
	}
		getChildren().addAll(b);
		
		b.boundsInParentProperty().addListener(new ChangeListener<Bounds>() {
	        @Override
	        public void changed(ObservableValue<? extends Bounds> arg0,Bounds oldValue, Bounds newValue) {
	        	for (Obstacle e : Arene.obstacles) {
	        		if(newValue.getMinX() <=0){
	        			getChildren().remove(b);
	        			return;
	    			
	        		}
	        			
	    			if(e.getBoundsInParent().intersects(newValue)){
	    					b.setVisible(false);
	    					getChildren().remove(b);
	    			}

	    		} 
	        	List<Vampire> vampireToRemove = new ArrayList<Vampire>(); 
	        	for(Vampire e : Arene.vampiresFem){
	        		if(e.getBoundsInParent().intersects(newValue)){
    					e.setVisible(false);
    					getChildren().remove(e);
    					vampireToRemove.add(e);
    					p.setNbVampireTue(p.getNbVampireTue().get()+1);
    					getChildren().remove(b);
    				
	        		}
	        	}
	        	p.increaseScore(50*vampireToRemove.size());
	        	Arene.vampiresFem.removeAll(vampireToRemove);
	        	vampireToRemove.clear();
	        	for(Vampire e : Arene.vampiresMal){
	        		if(e.getBoundsInParent().intersects(newValue)){
    					e.setVisible(false);
    					getChildren().removeAll(e);
    					vampireToRemove.add(e);
    					p.setNbVampireTue(p.getNbVampireTue().get()+1);
    					getChildren().removeAll(b);
    					
	        		}
	        	}
	        	p.increaseScore(50*vampireToRemove.size());
	        	Arene.vampiresMal.removeAll(vampireToRemove);
	        	if(vampireInt.getBoundsInParent().intersects(newValue)){
	        		vampireInt.setVisible(false);
					getChildren().remove(vampireInt);
					vampireInt.tuer();
					getChildren().removeAll(b);
	        	}
	        	

	        }
	    });
		tt.play();
		
		nbVampireDansArene.set(vampiresFem.size()+vampiresMal.size()+1);
		
	}
	
	public static Vampire getInt(){
		return vampireInt;
	}
	
	public boolean aGagne(){
		if(p.getVie().get() <= 0){
			Text textVictoire = new Text("Vous avez perdu ");
			textVictoire.setFont(Font.font(null, FontWeight.BOLD, 45));
			textVictoire.setTextAlignment(TextAlignment.CENTER);
			textVictoire.setFill(Color.RED);

			 Light.Distant light = new Light.Distant();
			 light.setAzimuth(-135.0);

			 Lighting lighting = new Lighting();
			 lighting.setLight(light);
			 lighting.setSurfaceScale(5.0);
			 textVictoire.setEffect(lighting);
			 getChildren().add(textVictoire);
			 textVictoire.layoutYProperty().bind(this.heightProperty().subtract(textVictoire.getLayoutX()).divide(2));
			//textVictoire.layoutXProperty().bind(this.widthProperty().subtract(textVictoire.getLayoutY()).divide(13));
			 String u = getClass().getResource("/loose.wav").toString();
			  AudioClip audio = new AudioClip(u);
			   audio.play();
			 return true;
		}
		if(vampiresFem.size() !=0 || vampiresMal.size()!=0)
       			return false;
       			Text textVictoire = new Text("Félicitation, vous avez gagné!!");
			textVictoire.setFont(Font.font(null, FontWeight.BOLD, 45));
			textVictoire.setTextAlignment(TextAlignment.CENTER);
			textVictoire.setFill(Color.RED);

			 Light.Distant light = new Light.Distant();
			 light.setAzimuth(-135.0);

			 Lighting lighting = new Lighting();
			 lighting.setLight(light);
			 lighting.setSurfaceScale(5.0);
			 textVictoire.setEffect(lighting);
			 getChildren().add(textVictoire);
			 textVictoire.layoutYProperty().bind(this.heightProperty().subtract(textVictoire.getLayoutX()).divide(2));
			//textVictoire.layoutXProperty().bind(this.widthProperty().subtract(textVictoire.getLayoutY()).divide(13));
			 String u = getClass().getResource("/cf_gagne.wav").toString();
			  AudioClip audio = new AudioClip(u);
			   audio.play();
			 return true;
	}
	
	public void updateVampire(){
		List<Vampire> vampireToRemove = new ArrayList<Vampire>();
		vampiresFem.forEach(vamp->{
			if(vamp.mort)
				vampireToRemove.add(vamp);
				
		});
		vampiresFem.removeAll(vampireToRemove);
		vampireToRemove.clear();
		vampiresMal.forEach(vamp->{
			if(vamp.mort)
				vampireToRemove.add(vamp);
				
		});
		vampiresMal.removeAll(vampireToRemove);
	}
        
    
        
        

}
