import java.io.*;
import java.net.*;
import java.util.*;

public class Serveur extends Thread {
   private int port;
   private BufferedReader sisr;
   private PrintWriter sisw;
   private ServerSocket s;
   private Socket soc;

   private Joueur j1;
   private Joueur j2;


  // Appeler quand on choisis dans la section multijoueur : "Créer une partie"
  public Serveur() throws Exception
  {

    this.port = 8080;                                                           // Par défaut, le port sera 8080
    this.s = new ServerSocket(port);

    System.out.println("Vous etes l'hebergeur de la partie !");
    System.out.println("En attente du second joueur... ");

    this.soc = s.accept();                                                      // En attente d'une connexion
    this.sisr = new BufferedReader( new InputStreamReader(soc.getInputStream()));
    this.sisw = new PrintWriter( new BufferedWriter( new OutputStreamWriter(soc.getOutputStream())),true);

    String msg = "TRUE";
    sisw.println(msg);                                                               // Envoie d'un message au client
  }

 public int lance() throws Exception
 {
     GameMulti game = new GameMulti();
     //Scanner sc = new Scanner(System.in);
     //System.out.println(" Choisir votre Pseudo");
     //String pseudo1 = sc.nextLine();
     // Création du premier Joueur

     //Plateau p = new Plateau();

     //this.j1 = new Joueur(pseudo1);
     //String str = this.sisr.readLine();                                           // Lecture du pseudo du 2ème joueur
     //this.j2 = new Joueur(str);

     //Cuisinier c1 = new Cuisinier(p);
     //Cuisinier c2 = new Cuisinier(p);
     //this.j1.setCuisinier(c1);
     //this.j2.setCuisinier(c2);
     //System.out.println(" Second joueur connecter !");
     //sisw.println(" Votre joueur "+j2.getPseudo() +" a ete ajoute a la partie ");                                                     // envoi d'un message

     //SimulationPartieMulti partie = new SimulationPartieMulti(5, p, c1, c2);
     //partie.start();

     //Burger b1 = new Burger(1, 45);
     //Burger b2 = new Burger(2, 19);
     //Burger b3 = new Burger(4, 53);

     //p.addCuisinier(c1);
     //p.addCuisinier(c2);
     //p.Complete();

     //p.addBurger(b1);
     //p.addBurger(b2);
     //p.addBurger(b3);

     //p.setTabBurger(0, b1);
     //p.setTabBurger(1, b2);
     //p.setTabBurger(2, b3);

     // Ajout de monstres dans le nouveau plateau dans lequel le joueur/cuisinier va jouer
     //Monstre m = new Monstre(1);
     //Monstre leMonstre;
     //char carac;
     //m.spawnRandom(p);
     //while(c1.partieFinie() == false)
     //{
       //sisw.println(" Deplace ton cuisinier");
       //p.affiche(c1);
       //p.deplaceCuisinier();
       //str = this.sisr.readLine();
       //carac = str.charAt(0);

       //partie.toucheDeplace(carac);

       //p.deplaceCuisinier2(carac);

       //for(int i = 0; i < m.getNbMonstre(); i++)
       //{
         //leMonstre = m.getMonstre(i);
         //leMonstre.deplaceMonstre(p, c1);
       //}

       //p.calcScore(c1);
       //p.affiche(c1);
     //}
     //this.sisw.println("END");
     //System.out.println("END GAME, THE PLAYER IS DEAD !!");
     //return c1.getScore();
 }

 public String fermer() throws Exception
 {
   String msg = this.j1.getPseudo()+" , "+this.j2.getPseudo();
   this.sisr.close();
   this.sisw.close();
   this.soc.close();
   return msg;
 }

}
