import java.io.*;
import java.net.*;
import java.util.*;

public class Game{
  private Joueur j;
  private Burger tabBurger[];

  public Joueur getJoueur(){ return this.j; }
  public void setJoueur(String pseudo){ this.j = new Joueur(pseudo); }
  public void setBurger() { this.tabBurger = new Burger[2]; }

  public Game(String pseudo)
  {
    Plateau p = new Plateau();

    setJoueur(pseudo);

    SimulationPartie partie = new SimulationPartie(10, p, this.j);
    partie.run();
  }
}

class SimulationPartie extends Thread
  {
     private int nbMonstre;
     private Plateau plat;
     private Joueur j;
     private Cuisinier cuis;

     public SimulationPartie(int nb, Plateau p, Joueur j)
     {
       this.nbMonstre = nb;
       this.plat = p;
       this.j = j;
       this.cuis = new Cuisinier(p);
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

      this.j.setCuisinier(this.cuis);

      this.plat.addBurger(b1);
      this.plat.addBurger(b2);
      this.plat.addBurger(b3);

      this.plat.setTabBurger(0, b1);
      this.plat.setTabBurger(1, b2);
      this.plat.setTabBurger(2, b3);

      // Ajout de monstres dans le nouveau plateau dans lequel le joueur/cuisinier va jouer
      Monstre m = new Monstre(this.nbMonstre, this.plat, this.cuis);

      MouvementMonstre mouvMonstre = new MouvementMonstre(m, this.plat, this.cuis);
      mouvMonstre.start();

      MouvementCuisinier mouvCuis = new MouvementCuisinier(this.plat, this.cuis);
      mouvCuis.start();

      this.plat.affiche(this.cuis);

      while(this.cuis.partieFinie() == false)
      {
        mouvCuis.notif();

        //on dit au thread des monstres que le cuisinier a bouger et qu'il peuvement bouger a leurs tours
        mouvMonstre.notif();

        this.plat.calcScore(this.cuis);
        this.plat.affiche(this.cuis);
      }
    }
  }

class SimulationPartieMulti extends Thread
  {
     private int nbMonstre;
     private Plateau plat;
     private Cuisinier cuis1;
     private Cuisinier cuis2;
     public char touche;

     public SimulationPartieMulti(int nb, Plateau p, Cuisinier c1, Cuisinier c2)
     {
       this.nbMonstre = nb;
       this.plat = p;
       this.cuis1 = c1;
       this.cuis2 = c2;
     }

     public void toucheDeplace(char t)
     {
       this.touche = t;
     }

     public synchronized void run()
     {
      Burger b1 = new Burger(1, 45);
      Burger b2 = new Burger(2, 19);
      Burger b3 = new Burger(4, 53);

      this.plat.addCuisinier(this.cuis1);
      this.plat.addCuisinier(this.cuis2);

      this.plat.Complete();

      this.plat.addBurger(b1);
      this.plat.addBurger(b2);
      this.plat.addBurger(b3);

      this.plat.setTabBurger(0, b1);
      this.plat.setTabBurger(1, b2);
      this.plat.setTabBurger(2, b3);

      // Ajout de monstres dans le nouveau plateau dans lequel le joueur/cuisinier va jouer
      Monstre m = new Monstre(this.nbMonstre, this.plat, this.cuis1);

      this.plat.affiche(this.cuis1);

      MouvementMonstre mouvMonstre = new MouvementMonstre(m, plat, cuis1);
      mouvMonstre.start();

      MouvementCuisinier mouvCuis1 = new MouvementCuisinier(this.plat, this.cuis1);
      mouvCuis1.start();

      MouvementCuisinierClient mouvCuis2 = new MouvementCuisinierClient(this.plat, this.cuis2);
      mouvCuis2.start();

      while(this.cuis1.partieFinie() == false)
      {
        try{
         Thread.sleep(8000);
        }catch(InterruptedException e){e.printStackTrace();}

        System.out.println("Dans la game la touche = -" + touche + "-");

        mouvCuis2.recupTouche(this.touche);

        if(mouvCuis1.getEvt() == true && mouvCuis2.getEvt() == true)
          {
            System.out.println("On est dans le ptn de if de mort");

          }

        mouvCuis1.notif();
        mouvCuis2.notif();

        //on dit au thread que les cuisinier ont bouger et qu'ils peuvement rebouger a leurs tours
        mouvMonstre.notif();

        this.plat.calcScore(this.cuis1);
        this.plat.affiche(this.cuis1);
      }
    }
  }
