package model;

import javafx.geometry.Rectangle2D;

public class Obstacle extends Personnage {
	private final boolean estAmovible;
	
	public final static String obstacleUrl="/box.png";
	public final static String obstacleNAUrl="/ob.png";
	
	public Obstacle(int w,int h,boolean amovible,String url){
		super( w, h,url);
		estAmovible = amovible;
		Rectangle2D mask = new Rectangle2D(0,0,w-5,h-5);
		setViewport(mask);
	}

	public boolean EstAmovible() {
		return estAmovible;
	}
	
	
	public void movePosX(int posX){
        for(int i =0;i<Math.abs(posX);i++){
            if(posX>0){
            	if((getBoundsInParent().getMaxX()+1 < 800))
                this.setTranslateX(getTranslateX()+1);                
            } else{
            	if(getBoundsInParent().getMinX()-1 > 0)
            		this.setTranslateX(getTranslateX()-1);
            	}
            	
        }
    }
    
    
public void movePosY(int posY){
     for(int i =0;i<Math.abs(posY);i++){
            if(posY>0){
            	if(getBoundsInParent().getMaxY()+1 < 600)
                this.setTranslateY(getTranslateY()+1);
            }
            else{
            	if(getBoundsInParent().getMinY()-1 > 0)
                this.setTranslateY(getTranslateY()-1);
            }
     }
}

	
}
