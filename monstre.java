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
  private String strMonstre;                                                   // Variable qui contient le caractère d'un monstre : permet de savoir de quel type de monstre il s'agit
  private Monstre tabMonstre[];                                                // Structure qui contient l'ensemble des monstres crée lors d'une partie


  // Permet les vérifications dans la méthode valideDep

  char AFF_SOL = '_';
  char AFF_ECHELLE = '#';
  String AFF_VIDE = " ";

  // Méthode d'accès

  public int getPosLigne() { return this.posLigne; }
  public void setPosLigne(int lig) { this.posLigne = lig; }
  public int getPosColonne() { return this.posColonne; }
  public void setPosColonne(int col) {  this.posColonne = col; }
  public int getNbMonstre() { return this.NbMonstre; }
  public void setNbMonstre(int nb) {  this.NbMonstre = nb; }
  public String getStrMonstre() { return this.strMonstre; }
  public void setStrMonstre(String c) {  this.strMonstre = c; }
  public Monstre getMonstre(int x) { return this.tabMonstre[x]; }

  // Méthodes principales de la classe Monstre

  // Méthode qui vérifie si le déplacement de monstre demandé est possible
  public boolean valideDep(Plateau p, int val)
  {
      char tab[][] = p.getMapStatic();
      String dyn[][] = p.getMapDynamic();
      int col = this.getPosColonne();
      int lig = this.getPosLigne();

      // La suite de la méthode consiste à vérifier si dans les tableaux statiques et dynamiques les mouvements sont réalisable

      if( val == 0 && col != 0)                                                // Vérification à gauche
      {
        if( (p.getCharat(tab, lig, col-1) == AFF_SOL || p.getCharat(tab, lig, col-1) == AFF_ECHELLE) && (p.getString(dyn, lig, col-1).equals("J") || p.getString(dyn, lig, col-1).equals(AFF_VIDE)) )
          return true;
        else
          return false;
      }

      if( val == 1 && col != p.getNbCol()-2)                                   // Vérification à droite
      {
        if ( (p.getCharat(tab, lig, col+1) == AFF_SOL || p.getCharat(tab, lig, col+1) == AFF_ECHELLE) && (p.getString(dyn, lig, col-1).equals("J") || p.getString(dyn, lig, col+1).equals(AFF_VIDE)) )
          return true;
        else
          return false;
      }

      if( val == 2 && lig != 0)                                                // Vérification en haut
      {
        if ( p.getCharat(tab, lig, col) == AFF_ECHELLE && p.getCharat(tab, lig-1, col) == AFF_ECHELLE && (p.getString(dyn, lig, col-1).equals("J") || p.getString(dyn, lig-1, col).equals(AFF_VIDE)) )
          return true;
        else
          return false;
      }

      if( val == 3 && lig != p.getNbLigne()-2)                                 // Vérification en bas
      {
         if( p.getCharat(tab, lig, col) == AFF_ECHELLE && p.getCharat(tab, lig+1, col) == AFF_ECHELLE && (p.getString(dyn, lig, col-1).equals("J") || p.getString(dyn, lig+1, col).equals(AFF_VIDE)) )
           return true;
         else
           return false;
      }

      // Par défaut (dans le cas où nous sommes rentrés dans aucuns des if), on retourne false
      return false;
  }

  // Méthode qui permet aux monstres d'un plateau de se déplacer sur celui-ci
  public void deplaceMonstre(Plateau p, Cuisinier c)
  {
        int deplacement;
        boolean valide = true;

        // Tant que valide = true, on génère une valeur aléatoire pour déplacer le monstre  tant que celle-ci est fausse
        while(valide)
        {
          p.modifieCaseDynamique(this.getPosLigne(), this.getPosColonne(), " ");
          deplacement = (int)(Math.random()*4);

          switch(deplacement)
          {
              case 0:                                                          // Déplacement vers la gauche
                  if(this.valideDep(p, 0) == true)                             // Vérification
                  {
                    p.modifieCaseDynamique(this.getPosLigne(), this.getPosColonne()-1, this.getStrMonstre());
                    this.setPosColonne(this.getPosColonne()-1);
                    valide = false;
                  }
                  break;

              case 1:                                                          // Déplacement vers la droite
                  if(this.valideDep(p, 1) == true)                             // Vérification
                  {
                    p.modifieCaseDynamique(this.getPosLigne(), this.getPosColonne()+1, this.getStrMonstre());
                    this.setPosColonne(this.getPosColonne()+1);
                    valide = false;
                  }
                  break;

              case 2:                                                          // Déplacement pour monter
                  if(this.valideDep(p, 2) == true)                             // Vérification
                  {
                    p.modifieCaseDynamique(this.getPosLigne()-1, this.getPosColonne(), this.getStrMonstre());
                    this.setPosLigne(this.getPosLigne()-1);
                    valide = false;
                  }
                  break;

              case 3:                                                          // Déplacement pour descendre
                  if(this.valideDep(p, 3) == true)                             // Vérification
                  {
                    p.modifieCaseDynamique(this.getPosLigne()+1, this.getPosColonne(), this.getStrMonstre());
                    this.setPosLigne(this.getPosLigne()+1);
                    valide = false;
                  }
                  break;
          }

          if(c.RencontreMonstre(this.getPosLigne(), this.getPosColonne()))
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
       String dyn[][] = p.getMapDynamic();
       boolean valide = false;

         while(valide == false)
         {
           int col = (int)(Math.random()*p.getNbCol());
           int lig = (int)(Math.random()*p.getNbLigne());
           int x = (int)(Math.random()*3);

            // && p.getCharat(bur, lig, col) == ' ' a rajouter quand burger sera finie
           if( p.getCharat(map, lig, col) == AFF_SOL && AFF_VIDE.equals(p.getString(dyn, lig, col)))
           {
               // En fonction de la valeur de x, on aura un type de monstre choisi aléatoirement
               switch (x)
               {
                 case 0: {
                            this.tabMonstre[i] = new Oeuf(lig, col);
                            valide = true;
                            break;
                         }                                                    // Génère un monstre qui est un oeuf

                 case 1: {
                            this.tabMonstre[i] = new Saucisse(lig, col);
                            valide = true;
                            break;
                         }                                                    // Génère un monstre qui est une saucisse

                 case 2: {
                            this.tabMonstre[i] = new Cornichon(lig, col);
                            valide = true;
                            break;
                         }
                                                                               // Génère un monstre qui est une saucisse
               } //end switch
           } //end if
         } //end while
     } //end for
 }

  // Constructeur de la classe Monstre
  public Monstre(int nb)
  {
    setNbMonstre(nb);
    this.tabMonstre = new Monstre[nb];
  }

}

  // Classes filles qui hérite de la classe Monstre : les types de monstres
  // Elles ne contiennent que des constructeurs, avec pour seul différence leur caractère

  class Oeuf extends Monstre
  {
    public Oeuf(int ligne, int col)
    {
      super(1);
      setPosLigne(ligne);
      setPosColonne(col);
      setStrMonstre("O");
    }
  }

  class Saucisse extends Monstre
  {
    public Saucisse(int ligne, int col)
    {
      super(1);
      setPosLigne(ligne);
      setPosColonne(col);
      setStrMonstre("S");
    }
  }

  class Cornichon extends Monstre
  {
    public Cornichon(int ligne, int col)
    {
      super(1);
      setPosLigne(ligne);
      setPosColonne(col);
      setStrMonstre("C");
    }
  }
