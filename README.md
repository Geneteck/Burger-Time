# Burger-Time
Class Joueur :
  Attribut :
      - String pseudo
      - int score

  Méthode :
  - Constructeur par défaut avec pseudo et score à 0 ( )


Class Perso extend Thread (mère):
  Attribut :
    - String nom (cuisinier ou pain, oeuf, cornichon, oignon...) : pour la génération des noms de monstres --> aléatoire  
    Pour savoir où se trouve le joueur
    - int vie // par défaut 3pt de vie
    - int indicePerso // évolue à chaque déplacement valide/possible
    - int posLig // à chaque déplacement sur échelle, la position du perso prend col +1 ou -1 (Rien ne se passe si cuisinier n'est pas au dessus ou en dessous échelle)
    - int posCol // à chaque déplacement, la position du perso prend ligne +1 ou -1 (Rien ne se passe si l'indice )

  Méthode :
    - getVie()
    - setVie(int v)
    - getName()
    - getIndicePerso()
    - setIndicePerso(int i)
    - getPosLig()
    - setPosLig(int l)
    - getPosCol()
    - setPosCol(int c)
    - Valide() --> Vérifier si le déplacement du thread de type cuisinier est valide --> permet aux thread de type monstre d'effectuer un déplacement
      (Peut-être à impléménter dans classe cuisinier)

    - Class Cuisinier extend Perso :
      Méthode :

       - jetPoivre() --> le monstre qui est dans la direction du cuisinier (2 cases), est temporairement "désactiver", il ne bouge plus pendant 3-4 unité de temps et ne peut faire de dégâts au cuisinier

       - deplacementCuis() :
         --> Si on appuie sur q le cuisinier se déplace à gauche (toutes les 1 unités de temps),
         S'il est au bord/au limite d'une ligne rien rien ne se passe et les monstres n'effectue pas de déplacement également, tant que le déplacement du cuisinier n'est pas "valide" --> Problème de thread/interblocage wait etc...
         --> Si on appuie sur d le cuisinier se déplace à droite (toutes les 1 unités de temps)
         --> Si on | au dessus de notre cuisinier et qu'on appuie sur z, le cuisinier prend la place de | en hauteur
         Rien ne se passe si le déplacement n'est pas valide
      - blessCuis()
            --> si dégat() == true, getVie

      - constructeur par défaut : vie = 3

    - Class Monstre extend Perso :
      Méthode :

        - if degat() : true or false, si c'est vrai
        -

Class SimulPartie :
  Attribut
    - Plateau p :
    - Joueur j ?
  Méthode :
    - affichEtat() --> Etat du plateau, avec cuisinier et


Class Plateau
 - Les étages du plateau sont représentés par les lignes : tableau à 2 dimensions)

 Class Burger ou dans la class Plateau (?)
 // La classe Burger permet de "voir si le burger est compléter" :

 Méthode  :
 - affBurger



      Class Plateau ou Burger
               |
               v
   On peut constituer le plateau de différents carac :
     -'X' pour les limites
     -'|' pour les échelles
     -'S' pour la salade ?
             (Agis comme un sol mais si cuisinier se déplace sur le même indice que ce caractère, 'S' "tombe à l'étage inférieur", 'S' remplace le cara du dessous
     -'P' pour pain ? (Même idée que pour la salade)
     -'B' pour Bacon ? ""
     -'_' pour le sol ? Est ce que le sol n'est pas un thread ?
     -'*' pour le cuisiner ?
     -'M' pour symboliser les monstres 


Pour la partie où on doit créer une page qui stocke le score des meilleurs joueurs, réfléchir si la structure de stockage des ces infos
sera statique ou dynamique.
