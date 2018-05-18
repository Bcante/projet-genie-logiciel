package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Main.Plan;
import metroproject.Metro;
import metroproject.PathComparatorLines;
import metroproject.PathFinder;
import metroproject.Station;

public class testChangementsLignes {
	static Plan p=new Plan();
	static Metro m;
	PathComparatorLines comparator=new PathComparatorLines();
	PathFinder pf=new PathFinder();
	
	@BeforeAll
	static void beforeAll() {
		m=p.createParisMetro();
	}
	
	@Test
	@DisplayName("Test du comparateur d'itinéraire pour le moins de changement possible")
	void testComparatorLinesChangeGreaterThan() {
		ArrayList<Station> path1=new ArrayList<Station>();
		ArrayList<Station> path2=new ArrayList<Station>();
		path1.add(m.getStation("Courcelles"));
		path1.add(m.getStation("Concorde"));
		path1.add(m.getStation("ArtsEtMetiers"));
		path1.add(m.getStation("AnatoleFrance"));
		
		path2.add(m.getStation("AlexandreDumas"));
		path2.add(m.getStation("Anvers"));
		path2.add(m.getStation("Esplanade"));
		path2.add(m.getStation("FranklinDRoosvlet"));
		path2.add(m.getStation("Avron"));
		path2.add(m.getStation("CharlesDeGaulle"));
		assertEquals(1,comparator.compare(path1, path2));
	}
}
