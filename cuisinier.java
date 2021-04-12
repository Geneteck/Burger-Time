/*
    Projet Info4B - Burger Time
    Auteurs : Pinon Alexandre, Salaï Loïc
*/

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class Cuisinier
{
  // Déclaration des attributs de la classe Cuisinier

  private int pv;                                                              // Indique le nombre de poits de vie d'un cuisnier
  private int posLigne;                                                        // Correspond à la ligne actuel d'un Cuisinier
  private int posColonne;                                                      // Correspond à la colonne actuel du Cuisinier
  private char charCuis;
  private int Score;
  private Plateau plat;

  // Méthodes d'accès

  // Renvoie les poits de vie du cuisto
  public int getVie() { return this.pv; }
  public void setVie(int v) { this.pv = v; }
  public int getPosLigne(){ return this.posLigne; }
  public void setPosLigne(int lig) { this.posLigne = lig; }
  public int getPosColonne(){ return this.posColonne; }
  public void setPosColonne(int col) { this.posColonne = col; }
  public char getCharCuisinier() { return this.charCuis; }
  public void setCharCuis(char c) { this.charCuis = c;}
  public int getScore(){ return this.Score; }
  public void setScore(int score) { this.Score = score; }

  // Méthodes principales de la classe Cuisinier

  public void deplaceCuisinier()                               // Fonction qui permet de déplacer le cuisinier de la partie
  {
    Scanner sc = new Scanner(System.in);      // Create a Scanner object

    boolean verif = false;

    while( verif == false)
    {
      char touche = sc.next().charAt(0);  // Read user input

      if(touche == 'z' && plat.valide(touche, this.getPosLigne(),this.getPosColonne()))
      {
        this.clearCuisinier(this.plat);
        this.setPosLigne(this.getPosLigne()-1);
        verif = true;
      }

      else if(touche == 'q' && plat.valide(touche, this.getPosLigne(), this.getPosColonne()))
      {
        plat.tomber(touche, this.getPosLigne(), this.getPosColonne());
        this.clearCuisinier(this.plat);
        this.setPosColonne(this.getPosColonne()-1);
        verif = true;
      }
      else if(touche == 'd'  && plat.valide(touche, this.getPosLigne(), this.getPosColonne()))
      {
        plat.tomber(touche, this.getPosLigne(), this.getPosColonne());
        this.clearCuisinier(this.plat);
        this.setPosColonne(this.getPosColonne()+1);
        verif = true;
      }

      else if(touche == 's'  && plat.valide(touche, this.getPosLigne(), this.getPosColonne()))
      {
        this.clearCuisinier(this.plat);
        this.setPosLigne(this.getPosLigne()+1);
        verif = true;
      }

      else
        System.out.println("Mauvaise touche, pour rappel Z pour monter, S pour descendre, Q pour aller a gauche, et S pour descendre !");
    }
    plat.modifieCaseDynamique(this.getPosLigne(), this.getPosColonne(), this.getCharCuisinier());
  }

  public void deplaceCuisinier2(char touche)                  // Fonction qui permet de déplacer le cuisinier de la partie pour le client
  {
    this.clearCuisinier(this.plat);

    boolean verif = false;
    while( verif == false )
    {
      if(touche == 'z' && plat.valide(touche, this.getPosLigne(),this.getPosColonne()))
      {
        this.setPosLigne(this.getPosLigne()-1);
        verif = true;
      }

      else if(touche == 'q' && plat.valide(touche, this.getPosLigne(), this.getPosColonne()))
      {
        plat.tomber(touche, this.getPosLigne(), this.getPosColonne());
        this.setPosColonne(this.getPosColonne()-1);
        verif = true;
      }
      else if(touche == 'd'  && plat.valide(touche, this.getPosLigne(), this.getPosColonne()))
      {
        plat.tomber(touche, this.getPosLigne(), this.getPosColonne());
        this.setPosColonne(this.getPosColonne()+1);
        verif = true;
      }

      else if(touche == 's'  && plat.valide(touche, this.getPosLigne(), this.getPosColonne()))
      {
        this.setPosLigne(this.getPosLigne()+1);
        verif = true;
      }

      plat.modifieCaseDynamique(this.getPosLigne(), this.getPosColonne(), this.getCharCuisinier());
    }
  }

  // Méthode qui
  public void clearCuisinier(Plateau p)
  {
    char dyn[][] = p.getMapDynamic();

    int col = this.getPosColonne();
    int lig = this.getPosLigne();

    if( p.getCharat(dyn, lig, col-1) != 'C' || p.getCharat(dyn, lig, col-1) != 'O' || p.getCharat(dyn, lig, col-1) != 'S' || p.getCharat(dyn, lig, col+1) != 'C' || p.getCharat(dyn, lig, col+1) != 'O' || p.getCharat(dyn, lig, col-1) != 'S' )
      p.modifieCaseDynamique(this.getPosLigne(), this.getPosColonne(), ' ');
  }

  // Méthode qui vérifie si le cuisinier à rencontrer/toucher un monstre
  public boolean meetMonstre(int ligne, int col)
  {
    int ligneC = this.getPosLigne();
    int colC = this.getPosColonne();

    if(ligne == ligneC && col == colC)
       return true;
    else
       return false;
  }

  // Méthode qui vérifie les points de vie restat d'un cuisinier
  public boolean partieFinie()
  {
    if(this.getVie() < 1)
      {
        System.out.println("END GAME, THE PLAYER IS DEAD !!");
        return true;
      }
    else if (this.getScore() == 210)
      {
        System.out.println("END GAME, ALL BURGER IS READY!!");
        return true;
      }
    else
      return false;
  }

  // Méthode qui affiche le score et les points de vies du joueur
  public void afficheScorePv()
  {
    System.out.print(" SCORE : "+this.getScore() + "\n                                             " + " PV Cuisinier : "+ this.getVie());
  }

  // Méthode qui permet de faire apparaitre le cuisinier aleatoirement dans la map
  public void spawnCuisinier()
  {
    char map[][] = this.plat.getMapStatic();
    char bur[][] = this.plat.getDynamBurgeur();
    char dyn[][] = this.plat.getMapDynamic();

    boolean valide = false;
    int col;
    int lig;

    while(valide == false)
    {
      col = (int)(Math.random()*this.plat.getNbCol());
      lig = (int)(Math.random()*this.plat.getNbLigne());

      if( this.plat.getCharat(map, lig, col) == this.plat.AFF_SOL && plat.getCharat(dyn, lig, col) == this.plat.AFF_VIDE && plat.getCharat(bur, lig, col) == this.plat.AFF_VIDE)
        {
           this.setPosLigne(lig);
           this.setPosColonne(col);
           this.plat.modifieCaseDynamique(this.getPosLigne(), this.getPosColonne(), this.getCharCuisinier());
           valide = true;
        }
    }
  }

  // Constructeur de la classe Cuisinier
  public Cuisinier(int lig, int col, Plateau p)
  {
    setVie(3);
    setPosColonne(col);
    setPosLigne(lig);
    setCharCuis('J');
    setScore(0);
    this.plat = p;
  }

  public Cuisinier(Plateau p)
  {
    setVie(3);
    setPosLigne(1);
    setPosColonne(1);
    setCharCuis('J');
    setScore(0);
    this.plat = p;
  }
}

class MouvementCuisinier extends Thread
{
  private Plateau plat;
  private Cuisinier cuis;
  private boolean evt = false;

  public boolean getEvt() { return this.evt; }

  public MouvementCuisinier(Plateau p, Cuisinier c){
   this.plat = p;
   this.cuis = c;
   cuis.spawnCuisinier();
  }

  public synchronized void run(){
    while(true)
    {
      this.cuis.deplaceCuisinier();

      //permet de savoir que le déplacement du cuisinier a etait effectuer
      this.evt = true;

      try{
       wait();
      }catch(InterruptedException e){e.printStackTrace();}
      this.evt = false;
    }
  }

  public synchronized void notif(){
    notifyAll();
  }
}

class MouvementCuisinierClient extends Thread
{
  private Plateau plat;
  private Cuisinier cuis1;
  public char touche;
  private boolean evt = false;

  public boolean getEvt() { return this.evt; }

  public MouvementCuisinierClient(Plateau p, Cuisinier c){
   this.plat = p;
   this.cuis1 = c;
   cuis1.spawnCuisinier();
  }

  public synchronized void run(){
    while(true)
      {
        try{
         wait();
        }catch(InterruptedException e){e.printStackTrace();}

        this.cuis1.deplaceCuisinier2(this.touche);

        //permet de savoir que le déplacement du cuisinier a etait effectuer
        this.evt = true;

        try{
         wait();
        }catch(InterruptedException e){e.printStackTrace();}

        this.touche = ' ';
        this.evt = false;
      }
  }

  public void recupTouche(char touche)
  {
    this.touche = touche;
  }

  public synchronized void notif(){
    notifyAll();
  }
}
