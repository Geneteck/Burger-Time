import java.io.*;
import java.net.*;
import java.util.*;

public class Serveur extends Thread
{
  // Déclaration des attributs

   private int port;
   private BufferedReader sisr;
   private PrintWriter sisw;
   private ServerSocket s;
   private Socket soc;
   private Joueur j1;
   private Joueur j2;

 // Méthodes principales  de la classe Serveur

 // Effectue les différentes actions d'une partie en multi avec un client
 public int lance() throws Exception
 {
     Scanner sc = new Scanner(System.in);
     System.out.println("Choisir votre Pseudo");
     String pseudo1 = sc.nextLine();
     // Création du premier Joueur
     char carac;
     Plateau p = new Plateau();

     this.j1 = new Joueur(pseudo1);
     String str = this.sisr.readLine();                                           // Lecture du pseudo du 2ème joueur
     this.j2 = new Joueur(str);

     Cuisinier c1 = new Cuisinier(p);
     Cuisinier c2 = new Cuisinier(p);
     this.j1.setCuisinier(c1);
     this.j2.setCuisinier(c2);
     System.out.println("Second joueur connecter !");
     sisw.println("Votre joueur "+j2.getPseudo() +" a ete ajoute a la partie.");                                                     // envoi d'un message

     SimulationPartieMulti partie = new SimulationPartieMulti(10, p, c1, c2);
     partie.start();

     while(c1.partieFinie() == false)
     {
       sisw.println("Deplace ton cuisinier :");
       str = this.sisr.readLine();
       carac = str.charAt(0);

       partie.toucheDeplace(carac);
       partie.notif();
     }
     this.sisw.println("END");
     return c1.getScore();
 }

 // Permet de fermer le serveur qui a été crée en fin de partie et de récupérer le pseudo des joueurs
 public String fermer() throws Exception
 {
   String msg = this.j1.getPseudo()+" , "+this.j2.getPseudo();
   this.sisr.close();
   this.sisw.close();
   this.soc.close();
   return msg;
 }

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
}
