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
      txt = this.getLesPseudos().get(i)+" Score : "+this.getLesScores().get(i);
      System.out.println(txt);
    }
    else System.out.println(" Il n'y a pas de score ");
  }

  // Permet d'afficher l'ensemble du classement
  public void afficheClassement()
  {
    for(int i = 0; i<this.getLesScores().size(); i++)
    {
      this.affichage(i);
    }
  }

  // Il faut appeler le fichier s'il existe sinon on le crée
  // S'il est déjà créer il faut récupérer un a un les pseudos du fichier concerner et les affecter dans getLesPseudos, de même pour lesScores avec son fichier propre
  // d'une instance de classement de Serv
  // Sinon on crée 2 fichiers, un pour les pseudos et un autre pour les scores
  // scores.txt et pseudos.txt

  // Permet de lire le fichier score.txt et de concaténer les valeurs lu
  public void lectureFichScore()
  {
     int i = 0;
     try {
       FileReader fichier = new FileReader("score.txt");
       StreamTokenizer entree = new StreamTokenizer(fichier);
       while(entree.nextToken() == StreamTokenizer.TT_NUMBER)
       {
         this.getLesScores().set(i, (int)entree.nval);
         i++;
       }
       fichier.close();
     }catch(Exception e) { e.getStackTrace(); }
  }

  public void ecritFichScore()
  {
    try
    {
       FileWriter score;
       score = new FileWriter("score.txt");
       for(int i=0; i<this.getLesScores().size();i++ )
       {
         String str = this.getLesScores().get(i)+"\n";
         score.write(str);
       }
       score.close();
     }catch(Exception e) { e.getStackTrace(); }
  }

  public void lectureFichPseudo()
  {
     int i = 0;
     try {
       FileReader fichier = new FileReader("pseudo.txt");
       StreamTokenizer entree = new StreamTokenizer(fichier);
       while(entree.nextToken() == StreamTokenizer.TT_NUMBER)
       {
         this.getLesScores().set(i, (int)entree.nval);
         i++;
       }
       fichier.close();
     }catch(Exception e) { e.getStackTrace(); }
  }

  public void ecritFichScore()
  {
    try
    {
       FileWriter score;
       score = new FileWriter("score.txt");
       for(int i=0; i<this.getLesScores().size();i++ )
       {
         String str = this.getLesScores().get(i)+"\n";
         score.write(str);
       }
       score.close();
     }catch(Exception e) { e.getStackTrace(); }
  }

  public void clean(String namefichier)
  {
    try
    {
       FileWriter score;
       score = new FileWriter("score.txt");
       for(int i=0; i<this.getLesScores().size();i++ )
       {
         score.write("");
       }
       score.close();
     }catch(Exception e) { e.getStackTrace(); }
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
    classement.getLesPseudos().add("POPEY");
    classement.getLesScores().add(550);
    classement.getLesPseudos().add("MAX");
    classement.getLesScores().add(70);
    classement.getLesPseudos().add("ALEX");
    classement.getLesScores().add(60);
    classement.trie();
    classement.afficheClassement();

    classement.clean("score.txt");
    classement.ecritFichScore();
    classement.getLesScores().remove(0);
    classement.getLesScores().remove(1);
    classement.lectureFichScore();
    classement.afficheClassement();
  }
}
