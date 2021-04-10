import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

class Interface
{
  // Déclaration des attributs

  private int scoreFinal;                                                       // Variable qui contient le score final de fin de partie
  private String pseudoS;                                                       // Variable qui stocke les pseudos de (solo) ou des joueurs (coop)
  private ClassementServ classement;                                            // Permet d'obtenir le classement des meilleurs joueurs

  // Méthodes d'accès

  public int getScoreFinal() { return this.scoreFinal; }

  String tiret = "-----------------------------------------------------------------------------------------------------";
  String tabu1 = "                                ";                                // 12 tabulations
  String tabu2 = "              ";                                              // 7 tabulations


  // Main
  public static void main(String[] args)
  {
      Interface i = new Interface();
      i.start();
  }

  // Méthode principales de classe Interface

  // Le menu1 est affiche le menu principal du jeu lorsque le programme est lancer
  public int Menu1()
  {
    int choix = 0;
    Scanner sc = new Scanner(System.in);

    System.out.println(tiret+"\n"+tiret+"\n");
    System.out.println(this.tabu1+"BIENVENUE DANS BURGER TIME !\n");
    System.out.println(tiret+"\n"+tiret+"\n");
    System.out.println("Choisissez un mode : \n");
    System.out.println(this.tabu2+" 1 - Solo   ");
    System.out.println(this.tabu2+" 2 - Coop ");                                // lance une partie à deux joueurs
    System.out.println(this.tabu2+" 3 - Classement ");                          // Réseaux avec le score des meilleurs joueurs + type (solo ou local)
    System.out.println(this.tabu2+" 4 - Touche du jeux \n");

    System.out.println("Votre choix :");
    do {
      choix = sc.nextInt();
    } while(choix<0 && choix>4);

    System.out.println(tiret+"\n"+tiret+"\n");
    return choix;
  }

  // La méthode Menu3 est appeler quand l'utilisateur à choisi s'il souhaite créer ou rejoindre une partie
  public int Menu2()
  {
    int choix = 0;
    Scanner sc = new Scanner(System.in);

    System.out.println(tiret+"\n"+tiret+"\n");
    System.out.println(this.tabu1+" Choisissez une action \n");
    System.out.println(this.tabu2+" 1 - Creer une partie (2 Joueurs) ");
    System.out.println(this.tabu2+" 2 - Rejoindre une partie (2 Joueurs) ");
    System.out.println(this.tabu2+" 3 - Retour ");

    // On récupère le choix de l'utilisateur et on la retourne
    System.out.println("Votre choix :");
    do {
      choix = sc.nextInt();
    } while(choix<0 && choix>3);

    System.out.println(tiret+"\n"+tiret+"\n");
    return choix;
  }

  // La méthode Menu3 est appeler quand l'utilisateur à choisi s'il souhaite créer ou rejoindre une partie
  public void Menu3(int val)
  {
      Scanner sc = new Scanner(System.in);

      for(int i=0; i<50; i++) { System.out.println(""); }
      System.out.println(tiret+"\n"+tiret+"\n");

      // Si le choix est de créer une partie, alors l'utilisateur lance un serveur
      if(val == 1)
      {
        try{
          Serveur s = new Serveur();                                            // Création du serveur
          this.scoreFinal = s.lance();
          this.pseudoS = s.fermer();
          this.classement.start(this.pseudoS, this.scoreFinal);
        }catch(Exception e) {	e.printStackTrace(); }
      }

      // Si le choix est de rejoindre une partie, alors l'utilisateur lance un client
      if( val == 2)
      {
        try
        {
          Client c = new Client(8080);                                          // Création du client
        }catch(Exception e) {	e.printStackTrace(); }
      }

      for(int i=0; i<50; i++) { System.out.println(""); }
      this.Menu4();
      System.out.println(tiret+"\n"+tiret+"\n");
  }

  // Méthode principal d'interface qui permet d'accéder en fonction des choix de l'utilisateur aux différents menu
  public void start()
  {
    Scanner sc = new Scanner(System.in);
    String pseudo;

    for(int i=0; i<50; i++) { System.out.println(""); }
    int v = this.Menu1();                                                       // Affiche le menu principal du jeu
    if(v == 1)
    {
      System.out.println("SAISISSEZ VOTRE PSEUDO ");
      pseudo = sc.next();
      try{
        Game g = new Game(pseudo);
        this.pseudoS = pseudo;
        this.scoreFinal = g.getSimulationPartie().getScoreFinal();
        this.classement.start(this.pseudoS, this.scoreFinal);
      }catch (Exception e) { e.printStackTrace();}
      this.Menu4();
    }
    else if (v == 2)
    {
      v = this.Menu2();
      if( v == 4) this.start();
      else this.Menu3(v);
    }
    else if (v == 3)
    {
      for(int i=0; i<50; i++) { System.out.println(""); }
      System.out.println(tiret+"\n"+tiret+"\n");
      System.out.println(this.tabu1+" CLASSEMENT DES MEILLEURS JOUEURS !\n");
      System.out.println(tiret+"\n"+tiret+"\n");
      this.classement.afficheClassement();
      this.Menu4();
    }
    else if (v == 4)
    {
      for(int i=0; i<50; i++) { System.out.println(""); }
      System.out.println(tiret+"\n"+tiret+"\n");                                // Affiche un max de tirets
      System.out.println(this.tabu1+"BIENVENUE DANS BURGER TIME !\n");
      System.out.println("Voici les touches du jeux : \n");
      System.out.println(this.tabu2+" Z - Permet d'effectuer un déplacement en haut. ");
      System.out.println(this.tabu2+" Q - Permet d'effectuer un déplacement en bas. ");                         // lance une partie à deux joueurs
      System.out.println(this.tabu2+" S - Permet d'effectuer un déplacement à gauche. ");                        // Réseaux avec le score des meilleurs joueurs + type (solo ou local)
      System.out.println(this.tabu2+" D - Permet d'effectuer un déplacement à droite. ");
      this.Menu4();
    }
                                                                                // Pour la partie serveur avec la liste des meilleurs joueurs et leurs scores
  }

  public void Menu4()
  {
    Scanner sc = new Scanner(System.in);
    System.out.println(tiret+"\n"+tiret+"\n");
    System.out.println(this.tabu1+" Voulez-vous revenir au menu principal ? \n ");
    System.out.println(this.tabu2+" 1 - Oui  ");
    System.out.println(this.tabu2+" 2 - Non (Quitter le programme) \n ");

    System.out.print(this.tabu2+" CHOIX : ");
    // En fonction du choix, soit l'utilisateur est rediriger au menu principal, soit le programme(ou l'instance d'interface ?) se ferme
    do {
      if(sc.nextInt() == 1)  this.start();
      else { System.exit(0); }
    } while(sc.nextInt()<0 && sc.nextInt()>2);
  }

  public Interface()
  {
    this.classement = new ClassementServ();
    this.classement.lectureFich();
    this.classement.clean();
  }
}
