package model;

import com.sun.javafx.scene.traversal.Direction;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.media.AudioClip;
import javafx.scene.transform.Rotate;
import view.graphical.Arene;

public class Pistolero extends Personnage {
	
	private final static String pistoleroUrl="/perso.png";
	private final static String balleUrl="/magic_ball.png";
	private SimpleDoubleProperty vie;
	private int nbBalle;
	private MyDirection direction;
	private SimpleIntegerProperty nbVampireTue;
	private SimpleIntegerProperty score;
	
	
	public Pistolero(int w,int h) {
		super( 100, 100, pistoleroUrl);
		direction = MyDirection.LEFT;
		Rectangle2D mask = new Rectangle2D(20,20,50,65);
		setViewport(mask);
		setRotationAxis(Rotate.Y_AXIS);
		vie  = new SimpleDoubleProperty(1);
		nbVampireTue = new SimpleIntegerProperty(0);
		score = new SimpleIntegerProperty(0);

    }
	
	public SimpleIntegerProperty getNbVampireTue(){
		return nbVampireTue;
	}
	
	public SimpleIntegerProperty getScore(){
		return score;
	}
	
	public void increaseScore(int i){
		score.set(score.get()+i);
	}
	
	public void setNbVampireTue(int i){
		nbVampireTue.set(i);
	}
	public void setDirection(MyDirection d) {
		direction = d;
		
	}
	
	public static String getPistolerourl() {
		return pistoleroUrl;
	}

	public SimpleDoubleProperty getVie() {
		return vie;
	}

	public int getNbBalle() {
		return nbBalle;
	}
	public void movePosX(int posX){
//		for(int i = 0; i < Arene.obstaclesNA.size();i++){
//			if(this.getBoundsInParent().intersects(Arene.obstaclesNA.get(i).getBoundsInParent()))
//				return;
//			
//				
//		}
		if(touchObstacle(true,posX))
			return ;
		super.movePosX(posX);
//		touchObstacle(true,posX);
		touchVampire();
	}
	
	public void movePosY(int posY){
//		for(int i = 0; i < Arene.obstaclesNA.size();i++){
//			if(this.getBoundsInParent().intersects(Arene.obstaclesNA.get(i).getBoundsInParent()))
//				return;
//			
//				
//		}
		if(touchObstacle(false,posY))
			return ;
		super.movePosY(posY);
		//touchObstacle(false,posY);
		touchVampire();
	}
	
	

	public boolean touchObstacle(boolean moveX,int pos){
		for(int i = 0; i< Arene.obstacles.size();i++){
			Obstacle obs = Arene.obstacles.get(i);
			if(this.getBoundsInParent().intersects(obs.getBoundsInParent())){
				if(moveX){
					System.out.println(pos);
					if((pos < 0 &&
							!(obs.getBoundsInParent().getMaxX() >
							this.getBoundsInParent().getMaxX()))
							|| (pos > 0 && 
							!(this.getBoundsInParent().getMaxX()>
							obs.getBoundsInParent().getMaxX()))){
						if(!obs.EstAmovible())
							return true;
						obs.movePosX(pos);
					
					}
				}else{
					if((pos < 0 &&
							!(obs.getBoundsInParent().getMaxY() >
							this.getBoundsInParent().getMaxY()))
							|| (pos > 0 && 
							!(this.getBoundsInParent().getMaxY()>
							obs.getBoundsInParent().getMaxY()))){
						if(!obs.EstAmovible())
							return true;
					
						obs.movePosY(pos);
					}
				}
			}
			
			
		}
		return false;
		
	}
	
	public void touche(double i){
		if(vie.get()+i<0)
			vie.set(0);
		else
		 vie.set(vie.get()+i);
	}
	
	public void touchVampire(){
        String u = getClass().getResource("/cri.wav").toString();
        AudioClip audio = new AudioClip(u);
       for(int i = 0 ;i < Arene.vampiresFem.size();i++){
            if(this.getBoundsInParent().intersects(Arene.vampiresFem.get(i).getBoundsInParent())){
                audio.play();
                touche(-0.01);
            }
        }
       for(int i = 0 ;i < Arene.vampiresMal.size();i++){
            if(this.getBoundsInParent().intersects(Arene.vampiresMal.get(i).getBoundsInParent())){
                audio.play();
                touche(-0.01);
            }
        }
       
    }
	
	

	public MyDirection getDirection() {

		return direction;
	}
}
