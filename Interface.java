import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

class Interface
{

  String tiret = "------------------------------------------------------------------------------------";
  String tabu1 = "                            ";         // 12 tabulations
  String tabu2 = "              ";                        // 7 tabulations
  private LocalServ ls;

  public static void main(String[] args)
  {
    Interface i = new Interface();
    i.start();
  }


  public int Menu1()                                                           // Interface appeler au lancement du programme
  {
    int choix = 0;
    Scanner sc = new Scanner(System.in);

    System.out.println(tiret+"\n"+tiret+"\n");                                  // Affiche un max de tirets
    System.out.println(this.tabu1+"BIENVENUE DANS BURGER TIME !\n");
    System.out.println("Choisissez un mode : \n");
    System.out.println(this.tabu2+" 1 - Solo   ");
    System.out.println(this.tabu2+" 2 - Multijoueur ");                         // lance une partie à deux joueurs
    System.out.println(this.tabu2+" 3 - Classement \n");                        // Réseaux avec le score des meilleurs joueurs + type (solo ou local)
    System.out.println(this.tabu2+" 4 - Touche du jeux \n");

    System.out.println("Votre choix :");
    do {
      choix = sc.nextInt();
    } while(choix<0 && choix>4);

    System.out.println(tiret+"\n"+tiret+"\n");
    return choix;
  }

  public int Menu2()                                                            // Appeler si l'utilisateur choisir de jouer en multi, il aura alors le choix du mode multijoueur
  {
    int choix = 0;
    Scanner sc = new Scanner(System.in);

    System.out.println(tiret+"\n"+tiret+"\n");                                  // Affiche un max de tirets
    System.out.println(this.tabu1+" QUEL MODE DE JEU MULTIJOUEUR ? \n");
    System.out.println(this.tabu2+" 1 - Cooperatif (2 Joueurs) ");
    System.out.println(this.tabu2+" 2 - Cooperatif Versus (4 Joueurs) ");
    System.out.println(this.tabu2+" 3 - Solo contre TOUS  (1 vs 2)\n ");
    System.out.println(this.tabu2+" 4 - Retour ");

    System.out.println("Votre choix :");
    do {
      choix = sc.nextInt();
    } while(choix<0 && choix>4);

    System.out.println(tiret+"\n"+tiret+"\n");
    return choix;
  }

  public void Menu3(int val)                                                   // En fonction de la valeur du paramètre val, le switch va appeler les élements nécessaire au mode de jeu sélectionner
  {
      System.out.println(tiret+"\n"+tiret+"\n");
      switch(val)
      {
        case 1 :
        {  break; }

        case 2 :
        {  break; }

        case 3 :
        {  break; }
      }
      System.out.println(tiret+"\n"+tiret+"\n");
  }



  public void start()
  {
    Scanner sc = new Scanner(System.in);                                        // Pour la saisie du pseudo du joueur
    String pseudo;
 

    for(int i=0; i<50; i++) { System.out.println(""); }
    int v = this.Menu1();                                                       // Affiche le menu principal du jeu
    if(v == 1)
    {
      System.out.println(" SAISISSEZ VOTRE PSEUDO ");
      pseudo = sc.next();
      Game g = new Game(pseudo);
      g.start();
    }
    else if (v == 2)
    {
      v = this.Menu2();
      if( v == 4) this.start();
      // else this.Menu3(v);
    }
    else if (v == 4)
    {

      System.out.println(tiret+"\n"+tiret+"\n");                                  // Affiche un max de tirets
      System.out.println(this.tabu1+"BIENVENUE DANS BURGER TIME !\n");
      System.out.println("Voici les touches du jeux : \n");
      System.out.println(this.tabu2+" Z - Permet d'effectuer un déplacement en haut. ");
      System.out.println(this.tabu2+" Q - Permet d'effectuer un déplacement en bas. ");                         // lance une partie à deux joueurs
      System.out.println(this.tabu2+" S - Permet d'effectuer un déplacement à gauche. ");                        // Réseaux avec le score des meilleurs joueurs + type (solo ou local)
      System.out.println(this.tabu2+" D - Permet d'effectuer un déplacement à droite. ");
      System.out.println(this.tabu2+" Si vous souhaitez retourner au menu principal tapé 'oui' \n");
      String verif;
      do
        {
           verif = sc.nextLine();
        }while( !(verif.equals("OUI") || verif.equals("oui") || verif.equals("ui")) );
      this.start();
    }
                                                               // Pour la partie serveur avec la liste des meilleurs joueurs et leurs scores
  }



  public Interface(){
    this.ls = new LocalServ();
  }
}
