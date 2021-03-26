/*
    Projet Info4B - Burger Time
    Auteurs : Pinon Alexandre, Salaï Loïc

    (Mettre à jour le numéro de version à chaque modification de Monstre)
    Version : V.1.00
*/

public class Monstre extends Thread
{
  private int NbMonstre; //nombre de monstre que l'on pourra faire varier en fonction de la difficulté
  private int indicePos; // Attribut qui correspond à l'indice de l'entité sur le plateau

  // La ligne et la colonne d'une entité
  private int posLigne; // Correspond à la ligne actuel de l'entité
  private int posColonne; // Correspond à la colonne actuel de l'entité
  private char CharMonstre; //Correspond au caractères du monstre sur le plateau

  char AFF_LIMITE = 'X';
  char AFF_SOL = '_';
  char AFF_ECHELLE ='#';
  char AFF_VIDE =' ';

  //public Plateau p; //permet d'utiliser les accesseur de plateau

  // méthode d'écriture et de lécture de la class mere Monstre
  public int getIndicePos(){ return this.indicePos; }

  //public int getCalculIndice(int ligne, int col) { return (p.getNbCol()*ligne)+col; } //a vérifier

  public void setIndicePos(int i) { this.indicePos = i; }

  public int getPosLigne() { return this.posLigne; }

  public void setPosLigne(int lig) { this.posLigne = lig; }

  public int getPosColonne() { return this.posColonne; }

  public void setPosColonne(int col) {  this.posColonne = col; }

  public int getNbMonstre() { return this.NbMonstre; }

  public void setNbMonstre(int nb) {  this.NbMonstre = nb; }

  public char getCharMonstre() { return this.CharMonstre; }

  public void setCharMonstre(char c) {  this.CharMonstre = c; }

  public void run(Plateau p)
  {
    //a voir plus tard
    //appliquer la fonction de calcul du plus cours chemin afin de rattraper le Joueur
    //utiliser des wait quand un monstre aura prit du poivre dans la tronche, ou quand il aura toucher le Joueur
    //ne pas oublier de start les monstre avec .start dans le jeu
  }

  public synchronized void DeplacementMonstre(Plateau p)
  {
      int deplacement;
      boolean valide = false;
      while(valide == false)
      {
        deplacement = (int)(Math.random()*4);
        System.out.println("Deplacement = " + deplacement);
        p.modifieCaseDynamique(this.getPosLigne(), this.getPosColonne(), ' ');

        switch(deplacement)
        {
            case 0:  // Pour aller à gauche
            {
              if(this.ValideDep(p, deplacement)) { this.setPosColonne(this.getPosColonne()-1); valide = true; System.out.println(" Je suis dans le cas 0"); }

            }
            case 1:       // Pour aller à droite
            {
              if(this.ValideDep(p, deplacement)) { this.setPosColonne(this.getPosColonne()+1); valide = true; System.out.println(" Je suis dans le cas 1");}

            }
            case 2:       // Pour aller en haut
            {
              if(this.ValideDep(p, deplacement)) { this.setPosLigne(this.getPosLigne()-1); valide = true; System.out.println(" Je suis dans le cas 2");}

            }
            case 3:      // Pour aller en bas
            {
              if(this.ValideDep(p, deplacement)) { this.setPosLigne(this.getPosLigne()+1); valide = true; System.out.println(" Je suis dans le cas 3");}

            }
            default: System.out.println(" Je n'arrive pas à sortir 1");
        }
        System.out.println(" Je n'arrive pas à sortir 2");
        p.modifieCaseDynamique(this.getPosLigne(), this.getPosColonne(), this.getCharMonstre());
      }
  }



  public void DeplacementMonstre2(Plateau p)
  {
    char tab[][] = p.getMapStatic();
    char dyn[][] = p.getMapDynamic();

    p.modifieCaseDynamique(this.getPosLigne(), this.getPosColonne(), ' ');

      // Vérification à gauche
      if(this.getPosColonne() != 0 )
      {
        if(p.getCharat(p.getMapStatic(), this.getPosLigne(), this.getPosColonne()-1) == AFF_SOL && p.getCharat(p.getMapDynamic(), this.getPosLigne(), this.getPosColonne()-1) == ' ' )
           { System.out.println(" Je suis dans le if 1/1"); this.setPosColonne(this.getPosColonne()-1);}
      }
      // Vérification à droite
      if(this.getPosColonne() != p.getNbCol()-1)
      {
        if (p.getCharat(p.getMapStatic(), this.getPosLigne(), this.getPosColonne()+1) == AFF_SOL && p.getCharat(p.getMapDynamic(), this.getPosLigne(), this.getPosColonne()+1) == ' ' )
             {  System.out.println(" Je suis dans le if 2/1"); this.setPosColonne(this.getPosColonne()+1);}
      }
      // Vérification en haut
      if(this.getPosLigne() != 0)
      {
        if (p.getCharat(p.getMapStatic(), this.getPosLigne()-1, this.getPosColonne()) == AFF_ECHELLE && p.getCharat(p.getMapDynamic(), this.getPosLigne()-1, this.getPosColonne()) == ' ' )
             { System.out.println(" Je suis dans le if 3/1"); this.setPosLigne(this.getPosLigne()-1);}
      }
      // Vérification en bas
      if(this.getPosLigne() != p.getNbLigne()-1)
      {
         if(p.getCharat(p.getMapStatic(), this.getPosLigne()+1, this.getPosColonne()) == AFF_ECHELLE && p.getCharat(p.getMapDynamic(), this.getPosLigne()+1, this.getPosColonne()) == ' ' )
              { System.out.println(" Je suis dans le if 4/1");  this.setPosLigne(this.getPosLigne()+1);}
      }

      p.modifieCaseDynamique(this.getPosLigne(), this.getPosColonne(), this.getCharMonstre());
  }



  public boolean ValideDep(Plateau p, int val)
  {
    char tab[][] = p.getMapStatic();
    char dyn[][] = p.getMapDynamic();

    while(true)
    {
      System.out.println(" Je suis dans le while");
      // Vérification à gauche
      if( val == 0 && this.getPosColonne() != 0 )
      { System.out.println(" Je suis dans le if 1/1");
        if(p.getCharat(tab, this.getPosLigne(), this.getPosColonne()-1) == AFF_SOL && p.getCharat(dyn, this.getPosLigne(), this.getPosColonne()-1) == ' ' )
           { System.out.println(" Je suis dans le if 1/2"); return true; }
      }
      // Vérification à droite
      if( val == 1 && this.getPosColonne() != p.getNbCol()-1)
      { System.out.println(" Je suis dans le if 2/1");
        if (p.getCharat(tab, this.getPosLigne(), this.getPosColonne()+1) == AFF_SOL && p.getCharat(dyn, this.getPosLigne(), this.getPosColonne()+1) == ' ' )
             { System.out.println(" Je suis dans le if 2/2"); return true; }
      }
      // Vérification en haut
      if( val == 2 && this.getPosLigne() != 0)
      { System.out.println(" Je suis dans le if 3/1");
        if (p.getCharat(tab, this.getPosLigne()-1, this.getPosColonne()) == AFF_ECHELLE && p.getCharat(dyn, this.getPosLigne()-1, this.getPosColonne()) == ' ' )
             {System.out.println(" Je suis dans le if 3/2"); return true;}
      }
      // Vérification en bas
      if( val == 3 && this.getPosLigne() != p.getNbLigne()-1)
      {  System.out.println(" Je suis dans le if 4/1");
         if(p.getCharat(tab, this.getPosLigne()+1, this.getPosColonne()) == AFF_ECHELLE && p.getCharat(dyn, this.getPosLigne()+1, this.getPosColonne()) == ' ' )
              {System.out.println(" Je suis dans le if 4/2"); return true;}
      }
      else
        return false;
     }
  }

  public static void main(String[] args)
  {
    Oeuf o = new Oeuf(0, 0, 0);
    Saucisse s = new Saucisse(0, 0, 0);
  }
}

class Oeuf extends Monstre
{
  public Oeuf(int nb, int ligne, int col)
  {
    super();
    setNbMonstre(nb);
    setPosLigne(ligne);
    setPosColonne(col);
    setCharMonstre('O');
  }
}

class Saucisse extends Monstre
{
  public Saucisse(int nb, int ligne, int col)
  {
    super();
    setNbMonstre(nb);
    setPosLigne(ligne);
    setPosColonne(col);
    //setIndicePos(getCalculIndice(ligne, col));
    setCharMonstre('S');
  }
}

class Cornichon extends Monstre
{
  public Cornichon(int nb, int ligne, int col)
  {
    super();
    setNbMonstre(nb);
    setPosLigne(ligne);
    setPosColonne(col);
    //setIndicePos(getCalculIndice(ligne, col));
    setCharMonstre('C');
  }
}
