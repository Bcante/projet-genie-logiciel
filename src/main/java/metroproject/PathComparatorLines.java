package metroproject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;

public class PathComparatorLines implements Comparator<ArrayList<Station>> {

	@Override
	public int compare(ArrayList<Station> path1, ArrayList<Station> path2) {
		if (nbChanges(path1) < nbChanges(path2)) {
			return -1;
		} else if (nbChanges(path1) > nbChanges(path2)) {
			return 1;
		} else {
			return 0;
		}
	}

	private int nbChanges(ArrayList<Station> path) {
		int nbOfChanges = 0;
		HashSet<Integer> set1 = path.get(0).getLines();
		HashSet<Integer> set2 = path.get(1).getLines();
		set1.retainAll(set2);
		int currentLine = getOnlyElement(set1);
		if (currentLine == 0) {
			currentLine = getOnlyElement(path.get(0).getLines());
		}
	
		for (int i = 0; i < path.size(); i++) {
			HashSet<Integer> lines = path.get(i).getLines();
			if (!lines.contains(currentLine)) {
				nbOfChanges++;
				// get la prochaine line
				if (i != path.size() - 1) { // pas besoin de faire tout ça si on est à la dernière station
					set2 = path.get(i + 1).getLines();
					lines.retainAll(set2);
					currentLine = getOnlyElement(set1);
					if (currentLine == 0) {
						currentLine = getOnlyElement(path.get(0).getLines());
					}
				}
			}
		}
		return nbOfChanges;
	}

	private int getOnlyElement(HashSet<Integer> lines) {
		Iterator<Integer> it = lines.iterator();
		if (!it.hasNext()) {
			return 0;
		} else {
			int retour = it.next();
			return retour;
		}
	}

}
