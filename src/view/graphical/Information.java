package view.graphical;

import common.Joueur;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Pistolero;

public class Information extends VBox {
 
	private Slider sliderVitesse;
	ProgressBar lifeProgressBar;
	Label nbrVampireTues;
	Label nbrVampire;
	Label score1;
	
	public  Information() {
	      super(15);
	      
	      setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
			        + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
			        + "-fx-border-radius: 5;" + "-fx-border-color: blue;");
	      
	      sliderVitesse = new Slider(1, 4, 3){
	  	    public void requestFocus() { }
	  	  };
	      HBox hbox1=new HBox(8);
	      Label pseudo=new Label("PSEUDO");
	      Label score=new Label(Page.nom);
	      hbox1.getChildren().addAll(pseudo,score);
	      
	      HBox s = new HBox(8);
	      Label score2=new Label("score");
	      score1=new Label();
	      s.getChildren().addAll(score2,score1);
	      
	      HBox hbox2=new HBox(8);
	      Label labelNbrVampire=new Label("nombre de vampires :");
	       nbrVampire=new Label();
	      hbox2.getChildren().addAll(labelNbrVampire,nbrVampire);
	      
	      
	      HBox hbox3=new HBox(8);
	      Label labelNbrVampireTues=new Label("nombre de vampires tués :");
	      nbrVampireTues=new Label();
	      hbox3.getChildren().addAll(labelNbrVampireTues,nbrVampireTues);
	      
	      HBox hbox4=new HBox(8);
	      Label vies=new Label("vie :");
	      lifeProgressBar = new ProgressBar(1);;
	      hbox4.getChildren().addAll(vies,lifeProgressBar);
	      
	      HBox hbox5=new HBox(8);
	      Label nbrBall=new Label("nombre de balles :");
	      ProgressBar pi = new ProgressBar(0.5);
	      hbox5.getChildren().addAll(nbrBall,pi);
	      
	      HBox hbox6=new HBox(8);
	      Label vitessePistolero=new Label("vitesse de pistolero :");
	      sliderVitesse.setFocusTraversable(false);
	      
	      
	      hbox6.getChildren().addAll(vitessePistolero,sliderVitesse);
	      
	     
	     
	      getChildren().addAll(hbox1,s,hbox2,hbox3,hbox4,hbox5,hbox6);
	      
	}
	
	public void bindings(Pistolero p){
		lifeProgressBar.progressProperty().bind(p.getVie());
		nbrVampireTues.textProperty().bind((p.getNbVampireTue() ).asString());
		nbrVampire.textProperty().bind((Arene.nbVampireDansArene).asString());
		score1.textProperty().bind((p.getScore()).asString());
	}

	public double getSliderVitesse(){
		return sliderVitesse.getValue();
	}

}
