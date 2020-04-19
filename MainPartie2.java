import java.util.ArrayList;
import MG3D.geometrie.*;

class MainPartie2{

    /**
     * Algorithme de Dijkstra. Permet de trouver le chemin le plus court entre
     * deux sommets d'un maillage triangulaire. Le maillage est passé en paramètre ainsi que
     * les deux indices des sommets de départ et d'arrivée du chemin à trouver.
     * Un chemin sera construit. Il sera stocké dans un tableau dynamique d'entiers.
     * Le premier indice sera l'indice du sommet de départ. Le dernier sera celui
     * du sommet d'arrivée. Entre les deux, des indices de sommets permettant de suivre les
     * arêtes pour aller du départ à l'arrivée.
     * @param m un objet décrivant un maillage triangulaire.
     * @param depart l'indice du sommet de départ
     * @param arrivee l'indice du sommet d'arrivée
     * @return un tableau dynamique d'entiers représentant le chemin complet.
     */
    public static ArrayList<Integer> dijkstra(Maillage m, int depart, int arrivee){
	// Initialisation de parrcouru et precedent. Ces deux informations doivent
	// être rattachées à chaque sommet. Un tableau parcouru et un tableau precedent
	// sont créé pour stocker ces informations.
	// Les deux tableaux auront donc la taille correspondant au nombre de sommet du maillage.
	// La première case du tableau parcouru stockera l'information parcouru pour le premier
	// sommet du maillage. La seconde case du talbeau parcouru aura l'info pour le second
	// sommet du maillage, etc.
	// Idem pour precedent.
	// Concernant l'initialisation de parcouru à l'infini, comme l'infini n'existe pas
	// en informatique, les cases sont initialisées à -1.
	// precedent est bien initalisé à 0 comme prévu.
	
	double[] parcouru = new double[m.getNbSommets()];
	for(int i=0;i<m.getNbSommets();i++)
	    parcouru[i]=-1;
	
	// TODO - Créer et initaliser le tableau qui s'appelle precedent

	// Tableau des sommets non visités. C'est un tableau dynamique car les sommets seront
	// traités un à un et supprimé de cette liste.
	ArrayList<Integer> pasEncoreVu = new ArrayList<Integer>();
	// TODO - Ajouter tous les indices des sommets encore non vus. (donc tous).

	// Initialisation de la distance parcouru depuis le départ pour le sommet de départ.
	// Donc 0 car la distance entre le départ et le départ est nulle.
	parcouru[depart]=0;

	while(pasEncoreVu.size()>0){
	    // TODO - Dans la liste des sommets encore non visités, chercher le numéro
	    // de sommet ayant la plus petite distance parcourue.
	    // L'indice de ce sommet sera stocké dans une variable qui s'appelle s1
	    int s1;	    

	    // Suppression du sommet s1 de la liste des sommets non traités
	    pasEncoreVu.remove(new Integer(s1));

	    // Pour tous les sommets voisins de s1
	    for(int i=0;i<m.getSommet(s1).getNbVoisins();i++){
		int s2=m.getSommet(s1).getVoisin(i);
		// TODO - Mise à jour de parcouru et precedent si nécessaire.
	    }		
	}

	// On a visité tous les sommets. On peut donc remonter le chemin
	// partant de l'arrivée jusqu'au départ.
	
	// Création du chemin vide pour le moment.
	ArrayList<Integer> chemin = new ArrayList<Integer>();
	// On part de l'arrivée pour remonter le chemin.
	int s=arrivee;

	// Tant qu'on n'est pas revenu à notre point de départ, on ajoute le sommet courant
	// et on passe au précédent.
	while(s!=depart){
	    // TODO - Ajouter s en début du tableau chemin

	    // On remonte le chemin
	    s=precedent[s];
	}

	// TODO - Ajouter le sommet de départ en début du tableau chemin
	

	return chemin;
    }

    public static void main(String[] args){
	if(args.length!=3){
	    System.out.println("usage : java MainPartie2 fichier.off indice1 indice2");
	    System.exit(0);
	}
	String fichier=args[0];
	int indice1 = Integer.parseInt(args[1]);
	int indice2 = Integer.parseInt(args[2]);

	// TODO - Calculer la distance et l'afficher
    }
}
