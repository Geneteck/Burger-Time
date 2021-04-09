import java.io.*;
import java.net.*;
import java.util.*;

class Game{
  private Joueur j;
  private Burger tabBurger[];

  public Joueur getJoueur(){ return this.j; }
  public void setJoueur(String pseudo){ this.j = new Joueur(pseudo); }
  public void setBurger() { this.tabBurger = new Burger[2];}


  public Game(String pseudo)
  {
    Plateau p = new Plateau();

    setJoueur(pseudo);
    Cuisinier c = new Cuisinier(p);
    this.j.setCuisinier(c);

    SimulationPartie partie = new SimulationPartie(5, p, c);
    partie.start();

    System.out.println("END GAME, THE PLAYER IS DEAD !!");
  }

  public GameMulti()
  {
    Scanner sc = new Scanner(System.in);
    System.out.println(" Choisir votre Pseudo");
    String pseudo1 = sc.nextLine();
    // Création du premier Joueur

    Plateau p = new Plateau();

    this.j1 = new Joueur(pseudo1);
    String str = this.sisr.readLine();                                           // Lecture du pseudo du 2ème joueur
    this.j2 = new Joueur(str);

    Cuisinier c1 = new Cuisinier(p);
    Cuisinier c2 = new Cuisinier(p);
    this.j1.setCuisinier(c1);
    this.j2.setCuisinier(c2);
    System.out.println(" Second joueur connecter !");
    sisw.println(" Votre joueur "+j2.getPseudo() +" a ete ajoute a la partie ");                                                     // envoi d'un message

    SimulationPartieMulti partie = new SimulationPartieMulti(5, p, c1, c2);
    partie.start();

    char carac;

    while(c1.partieFinie() == false)
    {
      sisw.println(" Deplace ton cuisinier");
      str = this.sisr.readLine();
      carac = str.charAt(0);

      partie.toucheDeplace(carac);
    }
    this.sisw.println("END");
    return c1.getScore();
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
        this.cuis.DeplacementCuisinier();

        //on dit au thread que le cuisinier a bouger et qu'il peuvement bouger a leurs tours
        mouv1.notif();

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

     public SimulationPartie(int nb, Plateau p, Cuisinier c1, Cuisinier c2)
     {
       this.nbMonstre = nb;
       this.plat = p;
       this.cuis1 = c1;
       this.cuis2 = c2;
     }

     public synchronized char toucheDeplace(char t)
     {
       return this.touche = t;
     }

     public void run()
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

      this.cuis1.spawnCuisinier();
      this.cuis2.spawnCuisinier();

      // Ajout de monstres dans le nouveau plateau dans lequel le joueur/cuisinier va jouer
      Monstre m = new Monstre(this.nbMonstre, this.plat, this.cuis);

      MouvementMonstre mouv1 = new MouvementMonstre(m, plat, cuis);
      mouv1.start();

      this.plat.affiche(this.cuis);

      while(this.cuis1.partieFinie() == false)
      {
        this.cuis1.DeplacementCuisinier();
        this.cuis2.DeplacementCuisinier2(this.touche);

        //on dit au thread que les cuisinier ont bouger et qu'ils peuvement rebouger a leurs tours
        mouv1.notif();

        this.plat.calcScore(this.cuis1);
        this.plat.affiche(this.cuis1);
      }
    }
  }
}
