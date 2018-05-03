package test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Main.Plan;
import metroproject.Metro;
import metroproject.PathComparator;
import metroproject.PathFinder;
import metroproject.Station;

public class testRechercheChemin {
	static Plan p=new Plan();
	static Metro m;
	PathComparator comparator=new PathComparator();
	PathFinder pf=new PathFinder();
	
	@BeforeAll
	static void beforeAll() {
		m=p.createParisMetro();
	}
	
	@Test
	void pathComparatorTestSameLength() {
		ArrayList<Station> path1=new ArrayList<Station>();
		ArrayList<Station> path2=new ArrayList<Station>();
		path1.add(m.getStation("Argentine"));
		path1.add(m.getStation("Bastille"));
		path1.add(m.getStation("CharlesDeGaulle"));
		path1.add(m.getStation("Berault"));
		path1.add(m.getStation("Concorde"));
		
		path2.add(m.getStation("AlexandreDumas"));
		path2.add(m.getStation("Anvers"));
		path2.add(m.getStation("Esplanade"));
		path2.add(m.getStation("FranklinDRoosvlet"));
		path2.add(m.getStation("Avron"));
		path2.add(m.getStation("CharlesDeGaulle"));
		assertEquals(0,comparator.compare(path1, path2));
	}
	
	@Test
	void pathComparatorTestGreaterThan() {
		ArrayList<Station> path1=new ArrayList<Station>();
		ArrayList<Station> path2=new ArrayList<Station>();
		path1.add(m.getStation("Argentine"));
		path1.add(m.getStation("Bastille"));
		path1.add(m.getStation("CharlesDeGaulle"));
		path1.add(m.getStation("Berault"));
		path1.add(m.getStation("Concorde"));
		
		path2.add(m.getStation("AlexandreDumas"));
		path2.add(m.getStation("Anvers"));
		path2.add(m.getStation("Esplanade"));
		path2.add(m.getStation("FranklinDRoosvlet"));
		path2.add(m.getStation("Avron"));
		assertEquals(1,comparator.compare(path1, path2));
	}
	
	@Test
	void pathComparatorTestLessThan() {
		ArrayList<Station> path1=new ArrayList<Station>();
		ArrayList<Station> path2=new ArrayList<Station>();
		path1.add(m.getStation("Argentine"));
		path1.add(m.getStation("Bastille"));
		path1.add(m.getStation("CharlesDeGaulle"));
		path1.add(m.getStation("Berault"));
		
		path2.add(m.getStation("AlexandreDumas"));
		path2.add(m.getStation("Anvers"));
		path2.add(m.getStation("Esplanade"));
		path2.add(m.getStation("FranklinDRoosvlet"));
		path2.add(m.getStation("Avron"));
		path2.add(m.getStation("CharlesDeGaulle"));
		assertEquals(-1,comparator.compare(path1, path2));
	}
	
	@Test
	@DisplayName("Un chemin est bien retourné")
	void returnsPath() {
		ArrayList<Station> res=null;
		res=pf.shortestPath(m.getStation("AlexandreDumas"), m.getStation("Argentine"));
		assertNotNull(res);
	}
	
	@Test
	@DisplayName("Vérification que le chemin trouvé est bien le plus rapide.")
	void isFastest() {
		//Chemin le + rapide d'Esplanade à Barbès: E-> FranklinDRoosevelt-> Colonnel Fabien -> Barbes
		ArrayList<Station> res=null;
		ArrayList<Station> expected=new ArrayList<Station>();
		expected.add(m.getStation("Esplanade"));
		expected.add(m.getStation("FranklinDRoosvlet"));
		expected.add(m.getStation("ColonnelFabien"));
		expected.add(m.getStation("Barbes"));
		res=pf.shortestPath(m.getStation("Esplanade"), m.getStation("Barbes"));
		assertEquals(res,expected);
	}
	

}
