import java.io.*;
import java.net.*;
import java.util.*;

class Game{
  private Joueur j;
  private Burger tabBurger[];
  private SimulationPartie partie;

  public Joueur getJoueur(){ return this.j; }
  public void setJoueur(String pseudo){ this.j = new Joueur(pseudo); }
  public void setBurger() { this.tabBurger = new Burger[2]; }
  public SimulationPartie getSimulationPartie() { return this.partie; }


  public Game(String pseudo)
  {
    Plateau p = new Plateau();

    setJoueur(pseudo);
    Cuisinier c = new Cuisinier(p);
    this.j.setCuisinier(c);

    this.partie = new SimulationPartie(0, p, c);
    this.partie.run();
  }

  class SimulationPartie extends Thread
  {
     private int nbMonstre;
     private Plateau plat;
     private Cuisinier cuis;

     public SimulationPartie(int nb, Plateau p, Cuisinier c)
     {
       this.nbMonstre = nb;
       this.plat = p;
       this.cuis = c;
     }

     public int getScoreFinal()
     {
       return this.cuis.getScore();
     }

     public void run()
     {
      Burger b1 = new Burger(1, 45);
      Burger b2 = new Burger(2, 19);
      Burger b3 = new Burger(4, 53);

      this.plat.addCuisinier(this.cuis);
      this.plat.Complete();

      this.plat.addBurger(b1);
      this.plat.addBurger(b2);
      this.plat.addBurger(b3);

      this.plat.setTabBurger(0, b1);
      this.plat.setTabBurger(1, b2);
      this.plat.setTabBurger(2, b3);

      this.cuis.spawnCuisinier();

      // Ajout de monstres dans le nouveau plateau dans lequel le joueur/cuisinier va jouer
      Monstre m = new Monstre(this.nbMonstre, this.plat, this.cuis);

      MouvementMonstre mouv1 = new MouvementMonstre(m, plat, cuis);
      mouv1.start();

      this.plat.affiche(this.cuis);

      while(this.cuis.partieFinie() == false)
      {
        this.cuis.deplaceCuisinier();

        //on dit au thread que le cuisinier a bouger et qu'il peuvement bouger a leurs tours
        mouv1.notif();

        this.plat.calcScore(this.cuis);
        this.plat.affiche(this.cuis);
      }
    }
  }
}
