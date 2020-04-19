import java.util.ArrayList;
import MG3D.geometrie.*;

class MainPartie1{

    
    public static ArrayList<Arete> genereArete(Maillage m){
    
        //Création du tableau d'arêtes
        ArrayList<Arete> tabArete = new ArrayList<Arete>();
        int nbFaces = m.getNbFaces();

        for(int i=0; i<=nbFaces-1; i++){
            //Pour toutes les faces f de m, on récupère les 3 sommets
            Face f = m.getFace(i);
            int indiceS1=f.getS1();
            int indiceS2=f.getS2();
            int indiceS3=f.getS3();

            //Si l’arete (s1,s2) n’est pas dans le tableau dynamique alors
            //Ajouter l’arete (s1,s2) dans le tableau dynamique
            if(!tabArete.contains(new Arete(indiceS1, indiceS2))){

                tabArete.add(new Arete(indiceS1, indiceS2));
            }
            //Si l’arete (s1,s3) n’est pas dans le tableau dynamique alors
            //Ajouter l’arete (s1,s3) dans le tableau dynamique
            if(!tabArete.contains(new Arete(indiceS1, indiceS3))){

                tabArete.add(new Arete(indiceS1, indiceS3));
            }
            //Si l’arete (s2,s3) n’est pas dans le tableau dynamique alors
            //Ajouter l’arete (s2,s3) dans le tableau dynamique
            if(!tabArete.contains(new Arete(indiceS2, indiceS3))){

                tabArete.add(new Arete(indiceS2, indiceS3));
            }     
        }
        //On retourne le tableau
        return(tabArete);
    }

    
    public static boolean verificationSurfaceFermee(Maillage m){

        int nbSommets = m.getNbSommets(); //Avoir nb sommets
        int nbAretes = genereArete(m).size(); //Avoir nb aretes
        int nbFaces = m.getNbFaces(); //Avoir nb faces
        
        //Si une surface est fermée --> sommets - aretes + faces = 2 --> théorème de descartes
        if(nbSommets - nbAretes + nbFaces == 2){
            return true;    //La surface est fermée donc on retourne true
        }

        return false;   //La surface n'est pas fermée donc on retourne false
    }

    
    /**
     * Méthode permettant de calculer s'il y a une intersection 
     * entre le rayon défini par son origine et un vecteur directeur 
     * et le triangle formé par les trois sommets.
     * @param origineRayon Origine du rayon
     * @param rayon Direction du rayon
     * @param sommet0 premier sommet du triangle
     * @param sommet1 seconde sommet du triangle
     * @param sommet2 troisième sommet du triangle
     * @return retourne vrai s'il y a intersection, faux sinon.
     */
    public static boolean mollerTrumbore(Point3D origineRayon, Vecteur3D rayon, Sommet sommet0, Sommet sommet1, Sommet sommet2) {
        
        double EPSILON = 0.0000001;
        Vecteur3D arete1 = new Vecteur3D(sommet0, sommet1);
        Vecteur3D arete2 = new Vecteur3D(sommet0, sommet2);
        Vecteur3D h = rayon.produitVectoriel(arete2);   //Produit vectoriel
        double a = arete1.produitScalaire(h);   //Produit scalaire

        if(a > -EPSILON && a < EPSILON){
            return false;
        }
            
        double f = 1/a;
        Vecteur3D s = new Vecteur3D(sommet0, origineRayon);
        double u = f*(s.produitScalaire(h));

        if(u<0 || u>1){
            return false;
        }

        Vecteur3D q = s.produitVectoriel(arete1);
        double v = f*(rayon.produitScalaire(q));

        if(v<0 || u+v>1){
            return false;
        }

        double t = f*(arete2.produitScalaire(q));

        if( t>EPSILON && t<(1-EPSILON)){
            return true;
        }

        return false;
    }

    
    public static boolean verificationAutoIntersection(Maillage m){

        //On génère la liste des Aretes
        ArrayList<Arete> listeAretes = genereArete(m);

        for(int i = 0; i <= listeAretes.size()-1; i++){
            for(int j = 0; j<=m.getNbFaces()-1; j++){

                //Déclaration des paramètres
                Sommet origineRayon = m.getSommet(listeAretes.get(i).getS1());
                Vecteur3D rayon = new Vecteur3D(origineRayon, m.getSommet(listeAretes.get(i).getS2()));
                Sommet sommet0 = m.getSommet(m.getFace(j).getS1());
                Sommet sommet1 = m.getSommet(m.getFace(j).getS2());
                Sommet sommet2 = m.getSommet(m.getFace(j).getS3());

                if(mollerTrumbore(origineRayon, rayon, sommet0, sommet1, sommet2)){
                    return true;
                }
            }
        }
        return false;
    }
    
    public static void main(String[] args){
        if(args.length!=1){
            System.out.println("usage : java MainPartie1 fichier.off");
            System.exit(0);
        }
        String fichier=args[0];
        
        Maillage m = new Maillage(fichier);

        if(!verificationSurfaceFermee(m) && !verificationAutoIntersection(m)){
            System.out.println("Le maillage triangulaire n’est pas imprimable car la surface n’est pas fermee.");
        }
        else if(verificationSurfaceFermee(m) && verificationAutoIntersection(m)){
            System.out.println("Le maillage triangulaire n’est pas imprimable car il presente au moins une auto-intersection.");
        }
        else if(!verificationSurfaceFermee(m) && verificationAutoIntersection(m)){
            System.out.println("Le maillage triangulaire n’est pas imprimable car la surface n’est pas fermee et il presente au moins une auto-intersection.");
        }
        else{
            System.out.println("Le maillage triangulaire est imprimable.");
        }
	
    }
}
