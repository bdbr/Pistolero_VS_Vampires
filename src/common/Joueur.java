package common;


import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Joueur {
	Element racine = new Element("joueurs");
	Document document = new Document(racine);
	
	private String pseudo;
	public static String score;
	
	public Joueur(String pseudo){
		this.pseudo=pseudo;
	
	}
	
	public boolean ajoutJoueur(){
		 //Nous allons commencer notre arborescence en cr�ant la racine XML
		 //qui sera ici "personnes".
		  	String fichierXml="info.xml";
		  	
		  	try{
		  	     lireFichier(fichierXml);
		  	     if(!JoueurExist()){
		  	        ajouter();
		  	     }else{
		  	        modifier();
		  	     }
		  	     enregistreFichier(fichierXml);
		  	      return true;
		  	     }catch(Exception e){
		  	    	 System.out.println("ajout joueur"+e.getMessage());
		  	     }
		  	     return false;
		  	     }
		  	
		  	
	public void lireFichier(String fichier) throws Exception
	{
	SAXBuilder sxb = new SAXBuilder();
	document = sxb.build(new File(fichier));
	racine = document.getRootElement();
	}
		  	
	public void enregistreFichier(String fichier) throws Exception
	{
	XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
	sortie.output(document, new FileOutputStream(fichier));
	}
	
	public void ajouter(){
		  	
		   
		  
		//On cr�e un nouvel Element etudiant et on l'ajoute
	      //en tant qu'Element de racine
	      Element joueur = new Element("joueur");
	      racine.addContent(joueur);
	      
	      Element nom = new Element("pseudo");
	      nom.setText(this.pseudo);
	      joueur.addContent(nom);
	      
	      Element score_joueur = new Element("score");
	      score_joueur.setText(""+0);
	      joueur.addContent(score_joueur);
	      try
	      {
	         //On utilise ici un affichage classique avec getPrettyFormat()
	         XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
	         //Remarquez qu'il suffit simplement de cr�er une instance de FileOutputStream
	         //avec en argument le nom du fichier pour effectuer la s�rialisation.
	         sortie.output(document, new FileOutputStream("info.xml"));
	      }
	      catch (java.io.IOException e){
	    	  System.out.println("ajouter"+e.getMessage());
	      }
	      
	}
	public boolean JoueurExist(){
	     //Element j=racine.getChild(jour);
	      //On cr�e une List contenant tous les noeuds "numero" de l'Element jour
	     List<Element> listJoueur = racine.getChildren("joueur");
	     if(listJoueur.isEmpty())
	       return false;
	    //On cr�e un Iterator sur notre liste
	    Iterator<Element> i = listJoueur.iterator();
	    while(i.hasNext())
	    {
	 
	       Element courant = (Element)i.next();
	     //On affiche le nom de l'element courant
	       if(courant.getChild("pseudo").getTextTrim().equals(this.pseudo))
	        return true;
	    }
	       return false;
	}
	
	public void modifier(){
	     List<Element> listJoueur = racine.getChildren("joueur");
	    //On cr�e un Iterator sur notre liste
	    Iterator<Element> i = listJoueur.iterator();
	    while(i.hasNext())
	    {
	           Element courant = (Element)i.next();
	 
	     //On affiche le nom de l'element courant
	       if(courant.getChild("pseudo").getTextTrim().equals(this.pseudo))
	        {
	    	   Joueur.score=courant.getChild("score").getTextTrim();
	    	   System.out.println("score"+Joueur.score);
	              return;
	         }
	    }
	}
	 
	
	
	
	
}
	
