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
  private String StringMonstre; //Correspond au caractères du monstre sur le plateau
  private Monstre TabMonstre[]; //tableau qui permet le stockage de tous les monstres

  char AFF_LIMITE = 'X';
  char AFF_SOL = '_';
  char AFF_ECHELLE = '#';
  String AFF_VIDE = " ";

  // méthode d'écriture et de lécture de la class Monstre
  public int getIndicePos(){ return this.indicePos; }

  //public int getCalculIndice(int ligne, int col) { return (p.getNbCol()*ligne)+col; } //a vérifier

  public void setIndicePos(int i) { this.indicePos = i; }

  public int getPosLigne() { return this.posLigne; }

  public void setPosLigne(int lig) { this.posLigne = lig; }

  public int getPosColonne() { return this.posColonne; }

  public void setPosColonne(int col) {  this.posColonne = col; }

  public int getNbMonstre() { return this.NbMonstre; }

  public void setNbMonstre(int nb) {  this.NbMonstre = nb; }

  public String getStringMonstre() { return this.StringMonstre; }

  public void setStringMonstre(String c) {  this.StringMonstre = c; }

  public Monstre[] getTabMonstre() { return this.TabMonstre; }

  public Monstre getMonstre(int x) { return this.TabMonstre[x]; }


  public void run(Plateau p)
  {
    //a voir plus tard
    //appliquer la fonction de calcul du plus cours chemin afin de rattraper le Joueur
    //utiliser des wait quand un monstre aura prit du poivre dans la tronche, ou quand il aura toucher le Joueur
    //ne pas oublier de start les monstre avec .start dans le jeu
  }

  public void DeplacementMonstre(Plateau p, Cuisinier c)
  {
        int deplacement;
        boolean valide = true;

        while(valide)
        {
          p.modifieCaseDynamique(this.getPosLigne(), this.getPosColonne(), " ");

          deplacement = (int)(Math.random()*4);

          switch(deplacement)
          {
              case 0:  // Pour aller à gauche
                // Vérification à gauche
                if(this.ValideDep(p, 0) == true)
                {
                  p.modifieCaseDynamique(this.getPosLigne(), this.getPosColonne()-1, this.getStringMonstre());
                  this.setPosColonne(this.getPosColonne()-1);
                  valide = false;
                }
                break;

              case 1:       // Pour aller à droite
                // Vérification à droite
                if(this.ValideDep(p, 1) == true)
                {
                  p.modifieCaseDynamique(this.getPosLigne(), this.getPosColonne()+1, this.getStringMonstre());
                  this.setPosColonne(this.getPosColonne()+1);
                  valide = false;
                }
                break;

              case 2:       // Pour aller en haut
                // Vérification en haut
                if(this.ValideDep(p, 2) == true)
                {
                  p.modifieCaseDynamique(this.getPosLigne()-1, this.getPosColonne(), this.getStringMonstre());
                  this.setPosLigne(this.getPosLigne()-1);
                  valide = false;
                }
                break;

              case 3:      // Pour aller en bas
                // Vérification en bas
                if(this.ValideDep(p, 3) == true)
                {
                  p.modifieCaseDynamique(this.getPosLigne()+1, this.getPosColonne(), this.getStringMonstre());
                  this.setPosLigne(this.getPosLigne()+1);
                  valide = false;
                }
                break;
          }

          if(c.RencontreMonstre(this.getPosLigne(), this.getPosColonne()))
            c.setVie(c.getVie()-1);
      }
    }

    public void test(Plateau p)
    {
      String dyn[][] = p.getMapDynamic();

      int col = this.getPosColonne();
      int lig = this.getPosLigne();

      if( p.getString(dyn, lig, col-1) == "C" || p.getString(dyn, lig, col-1) == "J" || p.getString(dyn, lig, col-1) == "O" || p.getString(dyn, lig, col-1) == "S" )
        if( p.getString(dyn, lig, col+1) == "C" || p.getString(dyn, lig, col+1) == "J" || p.getString(dyn, lig, col+1) == "O" || p.getString(dyn, lig, col-1) == "S" )
        p.modifieCaseDynamique(this.getPosLigne(), this.getPosColonne(), " ");

    }

    public void test2(Plateau p)
    {
      String dyn[][] = p.getMapDynamic();

      int col = this.getPosColonne();
      int lig = this.getPosLigne();


    }

  public boolean ValideDep(Plateau p, int val)
  {
      char tab[][] = p.getMapStatic();
      String dyn[][] = p.getMapDynamic();

      int col = this.getPosColonne();
      int lig = this.getPosLigne();

        // Vérification à gauche
        if( val == 0 && col != 0)
        {
          if( (p.getCharat(tab, lig, col-1) == AFF_SOL || p.getCharat(tab, lig, col-1) == AFF_ECHELLE) && (p.getString(dyn, lig, col-1).equals("J") || p.getString(dyn, lig, col-1).equals(AFF_VIDE)) )
            return true;
          else
            return false;
        }

        // Vérification à droite
        if( val == 1 && col != p.getNbCol()-2)
        {
          if ( (p.getCharat(tab, lig, col+1) == AFF_SOL || p.getCharat(tab, lig, col+1) == AFF_ECHELLE) && (p.getString(dyn, lig, col-1).equals("J") || p.getString(dyn, lig, col+1).equals(AFF_VIDE)) )
            return true;
          else
            return false;
        }

        // Vérification en haut
        if( val == 2 && lig != 0)
        {
          if ( p.getCharat(tab, lig, col) == AFF_ECHELLE && p.getCharat(tab, lig-1, col) == AFF_ECHELLE && (p.getString(dyn, lig, col-1).equals("J") || p.getString(dyn, lig-1, col).equals(AFF_VIDE)) )
            return true;
          else
            return false;
        }

        // Vérification en bas
        if( val == 3 && lig != p.getNbLigne()-2)
        {
           if( p.getCharat(tab, lig, col) == AFF_ECHELLE && p.getCharat(tab, lig+1, col) == AFF_ECHELLE && (p.getString(dyn, lig, col-1).equals("J") || p.getString(dyn, lig+1, col).equals(AFF_VIDE)) )
             return true;
           else
             return false;
        }

        //Si on ne rentre dans aucun if alors on retourne false
        return false;
  }

  public void SpawnRandom(Plateau p)
  {
   System.out.println("Spawn random appeler");

   for(int i = 0; i < this.getNbMonstre(); i++)
   {
     char map[][] = p.getMapStatic();
     char bur[][] = p.getDynamBurgeur();
     String dyn[][] = p.getMapDynamic();

     boolean valide = false;

     while(valide == false)
     {
       int col = (int)(Math.random()*p.getNbCol());
       int lig = (int)(Math.random()*p.getNbLigne());


       int x = (int)(Math.random()*3);

       if( p.getCharat(map, lig, col) == AFF_SOL && AFF_VIDE.equals(p.getString(dyn, lig, col))) // && p.getCharat(bur, lig, col) == ' ' a rajouter quand burger sera finie
         {
           switch (x)
           {
             case 0:
              {
                this.TabMonstre[i] = new Oeuf(lig, col);
                //p.modifieCaseDynamique(lig, col, this.getStringMonstre());
                valide = true;
                break;
              }

              case 1:
              {
                this.TabMonstre[i] = new Saucisse(lig, col);
                //p.modifieCaseDynamique(lig, col, this.getStringMonstre());
                valide = true;
                break;
              }

              case 2:
              {
                this.TabMonstre[i] = new Cornichon(lig, col);
                //p.modifieCaseDynamique(lig, col, this.getStringMonstre());
                valide = true;
                break;
              }
           } //end swtich
         } //end if
     } //end while
   } //end for
 } //end fonction

 public Monstre(int nb)
 {
   setNbMonstre(nb);
   this.TabMonstre = new Monstre[nb];
 }

  public static void main(String[] args)
  {}
}

class Oeuf extends Monstre
{
  public Oeuf(int ligne, int col)
  {
    super(1);
    setPosLigne(ligne);
    setPosColonne(col);
    setStringMonstre("O");
  }
}

class Saucisse extends Monstre
{
  public Saucisse(int ligne, int col)
  {
    super(1);
    setPosLigne(ligne);
    setPosColonne(col);
    setStringMonstre("S");
  }
}

class Cornichon extends Monstre
{
  public Cornichon(int ligne, int col)
  {
    super(1);
    setPosLigne(ligne);
    setPosColonne(col);
    setStringMonstre("C");
  }
}
