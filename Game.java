class Game{
  private Joueur j;
  private Burger tabBurger[];

  public Joueur getJoueur(){ return this.j; }
  public void setJoueur(String pseudo){ this.j = new Joueur(pseudo); }
  public void setBurger() { this.tabBurger = new Burger[2];}


  public Game(String pseudo)
  {
    setJoueur(pseudo);
    Cuisinier c = new Cuisinier(1, 14);
    this.j.setCuisinier(c);

    Plateau p = new Plateau();
    Burger b1 = new Burger(3, 45);
    Burger b2 = new Burger(3, 30);
    Burger b3 = new Burger(1, 53);

    p.addCuisinier(c);
    p.Complete();


    p.addCuisinier(c);                          // Ajoute le cuisinier sur le plateau (test)

    // Ajout de monstres dans le nouveau plateau dans lequel le joueur/cuisinier va jouer
    Saucisse S = new Saucisse(1,1,15);
    Cornichon C = new Cornichon(1,2,5);
    Oeuf O  = new Oeuf(3);

    p.addMonstre(S);
    p.addMonstre(C);
    p.addMonstre(O);

    p.addBurger(b1);
    p.addBurger(b2);
    p.addBurger(b3);

    p.setTabBurger(0, b1);
    p.setTabBurger(1, b2);
    p.setTabBurger(2, b3);

    p.affiche(c);
    O.SpawnRandom(p);

    for(int i = 0; i<2000; i++)
    {
      p.affiche(c);
      p.DeplacementCuisinier(c);
      O.DeplacementMonstre(p);
      S.DeplacementMonstre(p);
      C.DeplacementMonstre(p);
    }

  }

}
