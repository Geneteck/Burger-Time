/*
    Projet Info4B - Burger Time
    Auteurs : Pinon Alexandre, Salaï Loïc
*/

public class Monstre extends Thread
{
  // Déclaration des attributs de la classe Monstre

  private int NbMonstre;                                                       // Indique le nombre de monstres crée sur un plateau
  private int indicePos;                                                       // Indice d'un monstre sur un plateau
  private int posLigne;                                                        // Correspond à la ligne actuel d'un monstre
  private int posColonne;                                                      // Correspond à la colonne actuel d'un monstre
  private char charMonstre;                                                   // Variable qui contient le caractère d'un monstre : permet de savoir de quel type de monstre il s'agit
  private Monstre tabMonstre[];                                                // Structure qui contient l'ensemble des monstres crée lors d'une partie


  // Permet les vérifications dans la méthode valideDep

  char AFF_SOL = '_';
  char AFF_ECHELLE = '#';
  char AFF_VIDE = ' ';

  // Méthode d'accès

  public int getPosLigne() { return this.posLigne; }
  public void setPosLigne(int lig) { this.posLigne = lig; }
  public int getPosColonne() { return this.posColonne; }
  public void setPosColonne(int col) {  this.posColonne = col; }
  public int getNbMonstre() { return this.NbMonstre; }
  public void setNbMonstre(int nb) {  this.NbMonstre = nb; }
  public char getCharMonstre() { return this.charMonstre; }
  public void setCharMonstre(char c) {  this.charMonstre = c; }
  public Monstre getMonstre(int x) { return this.tabMonstre[x]; }

  // Méthodes principales de la classe Monstre

  // Méthode qui vérifie si le déplacement de monstre demandé est possible
  public boolean valideDep(Plateau p, int val)
  {
      char tab[][] = p.getMapStatic();
      char dyn[][] = p.getMapDynamic();

      int col = this.getPosColonne();
      int lig = this.getPosLigne();

        // Vérification à gauche
        if( val == 0 && col != 0)
        {
          if( (p.getCharat(tab, lig, col-1) == AFF_SOL || p.getCharat(tab, lig, col-1) == AFF_ECHELLE) && (p.getCharat(dyn, lig, col-1) == 'J' || p.getCharat(dyn, lig, col-1) == AFF_VIDE) )
            return true;
          else
            return false;
        }

        // Vérification à droite
        if( val == 1 && col != p.getNbCol()-2)
        {
          if ( (p.getCharat(tab, lig, col+1) == AFF_SOL || p.getCharat(tab, lig, col+1) == AFF_ECHELLE) && (p.getCharat(dyn, lig, col+1) == 'J' || p.getCharat(dyn, lig, col+1) == AFF_VIDE) )
            return true;
          else
            return false;
        }

        // Vérification en haut
        if( val == 2 && lig != 0)
        {
          if ( p.getCharat(tab, lig, col) == AFF_ECHELLE && p.getCharat(tab, lig-1, col) == AFF_ECHELLE && (p.getCharat(dyn, lig-1, col) == 'J' || p.getCharat(dyn, lig-1, col) == AFF_VIDE) )
            return true;
          else
            return false;
        }

        // Vérification en bas
        if( val == 3 && lig != p.getNbLigne()-2)
        {
           if( p.getCharat(tab, lig, col) == AFF_ECHELLE && p.getCharat(tab, lig+1, col) == AFF_ECHELLE && (p.getCharat(dyn, lig+1, col) == 'J' || p.getCharat(dyn, lig+1, col) == AFF_VIDE) )
             return true;
           else
             return false;
        }

        //Si on ne rentre dans aucun if alors on retourne false
        return false;
  }

  // Méthode qui permet aux monstres d'un plateau de se déplacer sur celui-ci
  public void deplaceMonstre(Plateau p, Cuisinier c)
  {
        int deplacement;
        boolean valide = true;

        while(valide)
        {
          //p.modifieCaseDynamique(this.getPosLigne(), this.getPosColonne(), " ");
          this.clearMonstre(p);
          deplacement = (int)(Math.random()*4);

          switch(deplacement)
          {
              case 0:                                                          // Déplacement vers la gauche
                  if(this.valideDep(p, 0) == true)                             // Vérification
                  {
                    p.modifieCaseDynamique(this.getPosLigne(), this.getPosColonne()-1, this.getCharMonstre());
                    this.setPosColonne(this.getPosColonne()-1);
                    valide = false;
                  }
                  break;

              case 1:                                                          // Pour aller à droite
                  if(this.valideDep(p, 1) == true)                             // Vérification
                  {
                    p.modifieCaseDynamique(this.getPosLigne(), this.getPosColonne()+1, this.getCharMonstre());
                    this.setPosColonne(this.getPosColonne()+1);
                    valide = false;
                  }
                  break;

              case 2:       // Pour aller en haut
                // Vérification en haut
                if(this.valideDep(p, 2) == true)
                {
                  p.modifieCaseDynamique(this.getPosLigne()-1, this.getPosColonne(), this.getCharMonstre());
                  this.setPosLigne(this.getPosLigne()-1);
                  valide = false;
                }
                break;

              case 3:      // Pour aller en bas
                // Vérification en bas
                if(this.valideDep(p, 3) == true)
                {
                  p.modifieCaseDynamique(this.getPosLigne()+1, this.getPosColonne(), this.getCharMonstre());
                  this.setPosLigne(this.getPosLigne()+1);
                  valide = false;
                }
                break;
          }

          if(c.meetMonstre(this.getPosLigne(), this.getPosColonne()))
            c.setVie(c.getVie()-1);
      }
    }

  // Méthode pour disposer de manière aléatoire les monstres (au préalable déjà crée), sur un plateau
  public void spawnRandom(Plateau p)
  {
   for(int i = 0; i < this.getNbMonstre(); i++)
   {
     char map[][] = p.getMapStatic();
     char bur[][] = p.getDynamBurgeur();
     char dyn[][] = p.getMapDynamic();

     boolean valide = false;

     while(valide == false)
     {
       int col = (int)(Math.random()*p.getNbCol());
       int lig = (int)(Math.random()*p.getNbLigne());


       int x = (int)(Math.random()*3);

       if( p.getCharat(map, lig, col) == AFF_SOL && p.getCharat(dyn, lig, col) == AFF_VIDE )
         {
           switch (x)
           {
             case 0:
              {
                this.tabMonstre[i] = new Oeuf(lig, col);
                valide = true;
                break;
              }

              case 1:
              {
                this.tabMonstre[i] = new Saucisse(lig, col);
                valide = true;
                break;
              }

              case 2:
              {
                this.tabMonstre[i] = new Cornichon(lig, col);
                valide = true;
                break;
              }
           } //end swtich
         } //end if
     } //end while
   } //end for
 }

 public void clearMonstre(Plateau p)
 {
   char dyn[][] = p.getMapDynamic();

   int col = this.getPosColonne();
   int lig = this.getPosLigne();

   if( p.getCharat(dyn, lig, col-1) != 'C' || p.getCharat(dyn, lig, col-1) != 'O' || p.getCharat(dyn, lig, col-1) != 'S' || p.getCharat(dyn, lig, col+1) != 'C' || p.getCharat(dyn, lig, col+1) != 'O' || p.getCharat(dyn, lig, col-1) != 'S' )
     p.modifieCaseDynamique(this.getPosLigne(), this.getPosColonne(), ' ');
 }

 public Monstre(int nb)
 {
   setNbMonstre(nb);
   this.tabMonstre = new Monstre[nb];
 }

  public static void main(char[] args)
  {}
}

class Oeuf extends Monstre
{
  public Oeuf(int ligne, int col)
  {
    super(1);
    setPosLigne(ligne);
    setPosColonne(col);
    setCharMonstre('O');
  }
}

class Saucisse extends Monstre
{
  public Saucisse(int ligne, int col)
  {
    super(1);
    setPosLigne(ligne);
    setPosColonne(col);
    setCharMonstre('S');
  }
}

class Cornichon extends Monstre
{
  public Cornichon(int ligne, int col)
  {
    super(1);
    setPosLigne(ligne);
    setPosColonne(col);
    setCharMonstre('C');
  }
}
