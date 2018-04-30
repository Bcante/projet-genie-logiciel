package metroproject;

import java.util.ArrayList;
import java.util.Comparator;

/*Sert à comparer des chemins sous forme de liste. Le critère: le chemin doit prendre le moins de temps 
 * (durée) possible. */
public class PathComparator implements Comparator<ArrayList<Station>>{
	@Override
	public int compare(ArrayList<Station> path1, ArrayList<Station> path2) {
		if (lengthOfPath(path1)<lengthOfPath(path2)) {
			return -1;
		}
		else if(lengthOfPath(path1)>lengthOfPath(path2)) {
			return 1;
		}
		else {
			return 0;
		}
		
	}
	
	private int lengthOfPath(ArrayList<Station> path) {
		int length=0;
		for(int i=0; i<path.size()-1; i++){ 
			length+=path.get(i).getConnectionTo(path.get(i+1)).getDuree();
		}
		return length;
	}



}
