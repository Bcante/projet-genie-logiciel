package test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Main.Plan;
import metroproject.Metro;
import metroproject.PathComparator;
import metroproject.Station;

public class testRechercheChemin {
	static Plan p=new Plan();
	static Metro m;
	PathComparator comparator=new PathComparator();
	
	
	@BeforeAll
	static void beforeAll() {
		m=p.creerMetroParis();
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

}
