package model;


import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;


public abstract class Personnage extends ImageView {

//	int count = 3;
	//int columns = 3;
	//int width;
	//int height;
	//ImageView icon;
	//int offsetX;
   // int offsetY;
   // private AnimationPerso animation;
    
        
		public Personnage(int w,int h,String url){
			super();
		//width = w;
		//height = h;
       // offsetX = 0;
       // offsetY = 0;
        //setMaxSize(w, h);
		
		String u = getClass().getResource(url).toString();
		Image i = new Image(u,w,h,true,false);
//		Image i = new Image(u);
		
		//icon = new ImageView(i);
		setImage(i);
		

        //icon.setViewport(new Rectangle2D(offsetX,offsetY,width,height));
		//animation = new AnimationPerso(Duration.millis(200),columns,count,
		  //      offsetX,offsetY,width,height);
       // this.getChildren().addAll(icon);
	}
	
//	public ImageView getIcon() {
//		return icon;
//	}

	public void movePosX(double d){
            for(int i =0;i<Math.abs(d);i++){
                if(d>0){
                	if((getBoundsInParent().getMaxX()+1 < 820))
                    this.setTranslateX(getTranslateX()+1);                
                } else{
                	if(getBoundsInParent().getMinX()-1 > 0)
                		this.setTranslateX(getTranslateX()-1);
                	}
                	
            }
        }
        
        
    public void movePosY(double posY){
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

	
    
//    public AnimationPerso getAnimation(){
//    	return animation;
//    }
}
