package metroproject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class PathFinder {

    public PathFinder() {

    }
    public ArrayList<Station> shortestPath(Station s1, Station s2){
    	PathComparator comparator=new PathComparator();
    	return findPath(s1, s2, comparator);
    }
    
    public ArrayList<Station> customPath(Station s1, Station s2, Station intermediaire){
    	ArrayList<Station> firstHalf=shortestPath(s1,intermediaire);
    	ArrayList<Station> secondHalf=shortestPath(intermediaire,s2);
    	secondHalf.remove(0); //remove pour qu'il n'y ai pas de doublon de la station intermédiaire
    	firstHalf.addAll(secondHalf);
    	return firstHalf;
    }
    
    public ArrayList<Station> leastLineChange(Station s1, Station s2){
    	PathComparatorLines comparator=new PathComparatorLines();
    	return findPath(s1, s2, comparator);
    }

    private ArrayList<Station> findPath(Station s1, Station s2,Comparator<ArrayList<Station>> comparator){
    	 /*initialisations*/
        ArrayList<Station> vu = new ArrayList<Station>(); //les stations déjà développées
        ArrayList<ArrayList<Station>> queue=new ArrayList<ArrayList<Station>>(); //notre queue de chemins
        //PathComparator comparator=new PathComparator();
        boolean done=false;
        ArrayList<Station> ini=new ArrayList<Station>();
        ini.add(s1);
        queue.add(ini);
        vu.add(s1);
        ArrayList<Station> chemin = new ArrayList<Station>();
        ArrayList<Station> sauvegarde=new ArrayList<Station>();
        while(!done && queue.size()!=0) {
            /*dérouler les fils*/
            chemin=queue.get(0); //on prend le premier chemin de la queue
            queue.remove(0); // et on le retire de la queue
            //former les sucesseurs à ce chemin
            
            sauvegarde=chemin.get(chemin.size()-1).getConnections(true); //save des connections du dernier chemi
            while (sauvegarde.size()!=0) { //développer les successeurs
                if (vu.indexOf(sauvegarde.get(0))==-1) { //la station n'a pas été développée
                    ArrayList<Station> newChemin=new ArrayList<>(chemin);
                    newChemin.add(sauvegarde.get(0));
                    queue.add(newChemin); //nouveau chemin ajouté
                }
                sauvegarde.remove(0);
            }
            vu.add(chemin.get(chemin.size()-1)); // on ajoute à vu le noeud développé
            //on classe la queue
            queue.sort(comparator);
            //supprimer les chemins nuls de la liste
            queue=delLongPaths(queue);
            //si le premier chemin atteint la destination, on s'arrête là
            if (queue.get(0).contains(s2)) {
                done=true;
            }
        }
        if (!done) {
            return null;
        }
        else {
            return queue.get(0);
        }
    }

    private ArrayList<ArrayList<Station>> delLongPaths(ArrayList<ArrayList<Station>> queue){
        Station finalS=null;
        Iterator<ArrayList<Station>> iteration=queue.iterator();
        ArrayList<Station> chemin;
        PathComparator comparator=new PathComparator();
        while(iteration.hasNext()) {
            chemin=iteration.next();
            finalS=chemin.get(chemin.size()-1); //station finale du chemin

            for(int i=0;i<queue.size();i++) {
                if ((queue.get(i).contains(finalS)) && (comparator.compare(chemin, queue.get(i))==-1)) {
                    iteration.remove();
                }
            }
        }
        return queue;
    }
    

}
