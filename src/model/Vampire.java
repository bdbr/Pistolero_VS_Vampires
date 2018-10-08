package model;
		
import java.util.Random;

import javafx.scene.media.AudioClip;
import view.graphical.Arene;


		
		public class Vampire extends Personnage {
		
			private final boolean  isFemale;
			private int 		direction;
			public final static String vampireUrlFem="/whitedragon.png" ;
			public final static String vampireUrlMal="/22099885-min.png" ;
			public boolean mort;
			
			public Vampire(boolean sex,int direction,String url) {
				
				super( 50, 50, url);
				this.isFemale = sex ;
				this.direction =  direction;
				mort = false;
				
			}
			
			public void tuer(){
				mort = true;
			}
		
			public boolean getSex() {
				return isFemale;
			}
		
		public void bougeVampire(Arene a){
				
				switch(this.direction){
				case(0) : 	movePosX(1,a);break;
				case(1)	:   movePosX(-1,a);break;
				case(2) : 	movePosY(1,a);break;
				case(3)	:   movePosY(-1,a);break;
				}
				
		}
		public void setDirection(int d){
			this.direction = d;
		}
		public int getDirection(){
			return this.direction;
		}
		public void movePosX(double d,Arene a){
			Random r = new Random();
		    touchVampire(a);
			touchObstacle(a);
		    for(int i =0;i<Math.abs(d);i++){
		        if(d>0){
		        	if((getBoundsInParent().getMaxX()+1 < 820))
		            this.setTranslateX(getTranslateX()+1); 
		        	else{
		        		this.setDirection(r.nextInt(4));
		        	}
		        } else{
		        	if(getBoundsInParent().getMinX()-1 > 10)
		        		this.setTranslateX(getTranslateX()-1);
		        	else
		        		this.setDirection(r.nextInt(4));
		        	}
		        	
		    }
		    

		}
		
		
		public void movePosY(double posY,Arene a){
		     touchVampire(a);
			touchObstacle(a);
			Random r = new Random();
		 for(int i =0;i<Math.abs(posY);i++){
		        if(posY>0){
		        	if(getBoundsInParent().getMaxY()+1 < 600)
		            this.setTranslateY(getTranslateY()+1);
		        	else
		        		this.setDirection(r.nextInt(4));
		        }
		        else{
		        	if(getBoundsInParent().getMinY()-1 > 10)
		            this.setTranslateY(getTranslateY()-1);
		        	else
		        		this.setDirection(r.nextInt(4));
		        }
		 }
		
		}

		public void touchObstacle(Arene a){
			Random r = new Random();
			Arene.obstacles.forEach(obs->{
				if(this.getBoundsInParent().intersects(obs.getBoundsInParent()))
					this.setDirection(r.nextInt(3));
					
					
			});
			
				
					
			
		}
		public void touchVampire(Arene a){
			Random r = new Random();
			Arene.vampiresFem.forEach(vf->{
				if(this.getBoundsInLocal().intersects(vf.getBoundsInParent())){
					if(vf.isFemale){
						vf.setVisible(false);
						vf.tuer();
						a.getChildren().remove(vf);
						System.out.println("mort");
						
					}
					else{
					Vampire v = new Vampire(true, r.nextInt(4), vampireUrlFem);
					v.setX(vf.getX());
					v.setY(vf.getY());
					v.movePosX(r.nextInt(4),a);
					v.setVisible(true);
					a.getChildren().add(v);
					
					System.out.println("bebe");
					}
				
				}
			});
			Arene.vampiresMal.forEach(vf->{
				if(this.getBoundsInLocal().intersects(vf.getBoundsInParent())){
					if(!vf.isFemale){
						vf.setVisible(false);
						vf.tuer();
						a.getChildren().remove(vf);
						System.out.println("mort");
						
					}
					
				}
			});
			
		}
		public void bougeInt(Pistolero p){
		
				
	
				double posPistoX = p.getBoundsInParent().getMinX();
				double posPistoY = p.getBoundsInParent().getMinY();
				
				double posVampX = this.getBoundsInParent().getMinX();
				double posVampY = this.getBoundsInParent().getMinY();
				
				double diffX = posVampX - posPistoX;
				double diffY = posVampY - posPistoY;
				
				if(diffX > 0) {
					setTranslateX(getTranslateX()-1);
					
				}
				else{
					setTranslateX(getTranslateX()+1);
					
				}
				
				if(diffY > 0) {
					  setTranslateY(getTranslateY()-1);
				}
				else{
					  setTranslateY(getTranslateY()+1);
				}
				
				
			
		}
		
		public void touchVampire(){
	        String u = getClass().getResource("/cri.wav").toString();
	        AudioClip audio = new AudioClip(u);
	        Arene.vampiresFem.forEach(vf->{
	            if(this.getBoundsInLocal().intersects(vf.getBoundsInParent())){
	                audio.play();
	            }
	        });
	        Arene.vampiresMal.forEach(vf->{
	            if(this.getBoundsInLocal().intersects(vf.getBoundsInParent())){
	                audio.play();
	            }
	        });
	       
	    }


		}
		
		
			
			
		
			
		
