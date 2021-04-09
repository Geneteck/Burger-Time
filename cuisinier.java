/*
    Projet Info4B - Burger Time
    Auteurs : Pinon Alexandre, Salaï Loïc
*/

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class Cuisinier extends Thread
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

  public synchronized void DeplacementCuisinier()                               // Fonction qui permet de déplacer le cuisinier de la partie
  {
    Scanner sc = new Scanner(System.in);      // Create a Scanner object
    System.out.println("Deplacer le cuisinier :");

    this.clearCuisinier(this.plat);

    boolean verif = false;

    while( verif == false)
    {
      char touche = sc.next().charAt(0);  // Read user input

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

      else
        System.out.println("Mauvaise touche, pour rappel Z pour monter, S pour descendre, Q pour aller a gauche, et S pour descendre !");
    }
    notifyAll();
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

      plat.modifieCaseDynamique(cuisto.getPosLigne(), cuisto.getPosColonne(), cuisto.getCharCuisinier());
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
    else if (this.getScore() <= 210)
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
    char map[][] = this.tabMonstre.getMapStatic();
    char bur[][] = this.tabMonstre.getDynamBurgeur();
    char dyn[][] = this.tabMonstre.getMapDynamic();

    boolean valide = false;
    int col;
    int lig;

    while(valide == false)
    {
      col = (int)(Math.random()*this.plat.getNbCol());
      lig = (int)(Math.random()*this.plat.getNbLigne());
      System.out.println(col);
      System.out.println(lig);

      if( this.plat.getCharat(map, lig, col) == this.plat.AFF_SOL && plat.getCharat(dyn, lig, col) == this.plat.AFF_VIDE && plat.getCharat(bur, lig, col) == this.plat.AFF_VIDE)
        {
           this.setPosLigne(lig);
           this.setPosColonne(col);
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
    setCharCuis('J');
    setScore(0);
    this.plat = p;
  }
}
