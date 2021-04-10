import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import java.util.ArrayList;
import java.nio.file.*;

class ClassementServ
{
  // Déclaration des attributs

  private ArrayList<String> lesPseudos;
  private ArrayList<Integer> lesScores;

  String tabu1 = "                                    ";                                // 12 tabulations
  // Méthodes d'accès

  public ArrayList<String> getLesPseudos() { return this.lesPseudos; }
  public ArrayList<Integer> getLesScores() { return this.lesScores; }

  // Méthodes principales

  // Permet de tenir à jour l'ordre des tableaux de pseudos et de joueurs
  public void trie()
  {
    String tmp;
    int tmp2;
    if(this.lesScores.size()>1)
    {
      while(this.valideTrie() == false)
      {
        for(int i=1; i<this.lesScores.size();i++)
        {
          if(this.lesScores.get(i) > this.lesScores.get(i-1))
          {
            tmp2 = this.getLesScores().get(i);
            tmp = this.getLesPseudos().get(i);
            this.lesScores.set(i, this.lesScores.get(i-1));
            this.lesPseudos.set(i, this.lesPseudos.get(i-1));

            this.lesScores.set(i-1, tmp2);
            this.lesPseudos.set(i-1, tmp);
          }// End If
        }// End For
      } // End While
    }// End If
  }

  // La méthode valideTri vérifie si le tableau des scores est trié
  public boolean valideTrie()
  {
    boolean verif = true;
    for(int i=1; i<this.lesScores.size();i++)
    {
      if(this.lesScores.get(i) > this.lesScores.get(i-1)) { verif = false; }
    }
    return verif;
  }

  // Permet d'afficher le score d'un joueur en particulier
  public void affichage(int i)
  {
    String txt;
    if( i < this.getLesScores().size())
    {
      txt = this.getLesPseudos().get(i)+" "+this.getLesScores().get(i)+"\n";
      System.out.println(txt);
    }
    else System.out.println(" Il n'y a pas de score ");
  }

  // Permet d'afficher l'ensemble du classement
  public void afficheClassement()
  {
    for(int i = -1; i<this.getLesScores().size(); i++)
    {
      if(i==-1)
      {
        System.out.println(this.tabu1+"   Pseudos/Score : \n");
      }
      else
      {
        System.out.print(this.tabu1+(i+1)+"- "); this.affichage(i);
      }
    }
  }

  // Permet de lire le fichier score.txt et de concaténer les valeurs lu
  public void lectureFich()
  {

    // Ici on va lire le fichier score.txt et concaténer dans le tableau lesPseudos
    try {
          // Le fichier d'entrée
          FileInputStream file = new FileInputStream("score.txt");
          Scanner scanner = new Scanner(file);

          //renvoie true s'il y a une autre ligne à lire
          while(scanner.hasNextLine())
          {
            this.getLesScores().add(Integer.parseInt(scanner.nextLine()));
          }
          scanner.close();

      }catch(Exception e) { e.getStackTrace(); }



     // Ici on va lire le fichier pseudo.txt et concaténer dans le tableau lesPseudos
     try {
         // FileReader p = new FileReader("pseudo.txt");
         FileInputStream file = new FileInputStream("pseudo.txt");
         Scanner scanner2 = new Scanner(file);

         //renvoie true s'il y a une autre ligne à lire
         while(scanner2.hasNextLine())
         {
           this.getLesPseudos().add(scanner2.nextLine());
         }
         scanner2.close();
     }catch(Exception e) { e.getStackTrace(); }
  }

  // Permet d'écrire dans les fichier score.txt et pseudo.txt, les éléments des tableaux respectifs
  public void ecritFich()
  {
    // Ici on va écrire dans le fichier score.txt, les scores du tableau lesScores
    try
    {
       FileWriter s;
       s = new FileWriter("score.txt");
       for(int i=0; i<this.getLesScores().size();i++ )
       {
         String str = this.getLesScores().get(i)+"\n";
         s.write(str);
       }
       s.close();
     }catch(Exception e) { e.getStackTrace(); }

     // Ici on va écrire dans le fichier pseudo.txt, les pseudos du tableau lesPseudos
     try
     {
        FileWriter p;
        p = new FileWriter("pseudo.txt");
        for(int i=0; i<this.getLesPseudos().size();i++ ) { p.write(this.getLesPseudos().get(i)+"\n"); }
        p.close();
      }catch(Exception e) { e.getStackTrace(); }
  }

  // Supprime les données actuelles des fichiers score.txt et pseudos.txt
  public void clean()
  {
    // Ici on va effacer les données du fichier score.txt
    try
    {
       PrintWriter writer = new PrintWriter("score.txt");
       writer.print("");
       writer.close();
     }catch(Exception e) { e.getStackTrace(); }

     // Ici on va effacer les données du fichier pseudo.txt
     try
     {
        PrintWriter writer = new PrintWriter("pseudo.txt");
        writer.print("");
        writer.close();
      }catch(Exception e) { e.getStackTrace(); }
  }


  public void start(String Pseudo, int score)
  {
    this.lectureFich();
    this.clean();
    this.getLesPseudos().add(Pseudo);
    this.getLesScores().add(score);
    this.trie();
    this.ecritFich();
  }

  // Constructeurs de la classe ClassementServ
  public ClassementServ()
  {
    this.lesPseudos = new ArrayList<String>();
    this.lesScores = new ArrayList<Integer>();
  }

  // Main
  public static void main(String[] args) {
    ClassementServ classement = new ClassementServ();

    classement.lectureFich();
    classement.clean();
    classement.ecritFich();
    classement.afficheClassement();
  }
}
