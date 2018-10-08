package model;

public class Balle extends Personnage{
	public String direction = "up";
	public boolean del;
	private final static String BalleUrl="/magic_ball.png";
	
	public Balle(int w, int h) {
		super(w, h, BalleUrl);
		
	}
	public static String getBalleurl() {
		return BalleUrl;
	}
	
	public void setDirection(String direct) 
	{
		direction = direct;
	}
	public String getDirection() 
	{
		return direction;
	}
	public void setposition(int x, int y) 
	{
		this.relocate(x, y);
		this.setVisible(true);
	}
	public void move() 
	{
		int x = (int) this.getLayoutX();
		int y = (int) this.getLayoutY();
	
	
		switch (direction) {
			case "up":    
				
					y -= 3;
				
			break;
	        case "down":  
	        	
	        		y += 3;
				
			break;
	        case "left":  
	        		x -= 3;
				
			break; 
	        case "right": 
	        		x += 3;
				 
	        break;
		}

	}
	}
