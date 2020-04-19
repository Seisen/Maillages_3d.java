public class Arete {

    //Atributs
    private int s1;
    private int s2;

    //Constructeur par défaut, initialise les indices de sommets
    public Arete(){
        this.s1 = 0;
        this.s2 = 0;
    }

    //Constructeur prenant en paramètre 2 entiers, 2 indices de sommets.
    //s1 : indice du prenmier sommet
    //s2 : indice du deuxième sommet
    public Arete(int s1, int s2){
        this.s1 = s1;
        this.s2 = s2;
    }

    //Constructeur par copie
    public Arete(Arete a){
        this.s1 = a.s1;
        this.s2 = a.s2;
    }

    //Retourne l'indice du premier sommet
    public int getS1(){
        return s1;
    }
    //Retourne l'indice du deuxième sommet
    public int getS2(){
        return s2;
    }

    //Permet de modifier la valeur de l'indice du sommet s1
    public void setS1(int s1){
        this.s1 = s1;
    }
    //Permet de modifier la valeur de l'indice du sommet s2
    public void setS2(int s2){
        this.s2 = s2;
    }

    //Retourne une description de l'arete.
    //La chaine de caractère est donnée sous la forme : (s1,s2).
    public String toString(){
        return "(" + getS1() + ", " + getS2() + ")";
    }

    //Permet de comparer deux aretes
    //Retourne vrai si les sommets des deux aretes sont identiques
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Arete other = (Arete) obj;
        return ((s1==other.s1 && s2==other.s2)||(s1==other.s2 && s2==other.s1));
    }
}
