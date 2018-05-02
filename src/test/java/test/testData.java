package test;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Main.Plan;
import metroproject.Metro;

public class testData {
	static Plan p=new Plan();
	static Metro m;
	
	@BeforeAll
	static void beforeAll() {
		m=p.createParisMetro();
	}
	
	@Test
	@DisplayName("Lignes présentes dans le métro")
	void lignesPresent() {
		assertNotSame(m.getLignes().size(), 0);
	}
	
	
	@Test
	@DisplayName("Stations présentes dans le métro")
	void stationsPresent() {
		assertNotSame(m.getStations().size(), 0);
	}
	
	@Test
	@DisplayName("Rails présents dans le métro")
	void railsPresent() {
		assertNotSame(m.getRails().size(), 0);
	}
	@Test
	@DisplayName("Lien de deux stations")
	void linkedStations() {
		assertEquals(true,m.areLinked(m.getStation("Argentine"), m.getStation("Bastille")));
	}
	@Test
	@DisplayName("Lien direct de deux stations avec beaucoup de lignes qui passent")
	void linkedStations2() {
		assertEquals(true,m.areLinked(m.getStation("Anvers"), m.getStation("Esplanade")));
	}
	@Test
	@DisplayName("Connection de stations d'une même ligne")
	void connectedStationsFromSameLigne() {
		boolean areLinked=m.areConnected(m.getStation("Argentine"), m.getStation("CharlesDeGaulle"));
		assertEquals(true, areLinked);
	}
	
	@Test
	@DisplayName("Connection de deux stations de lignes différentes")
	void connectedStationsFromDiffLignes() {
		boolean areLinked=m.areConnected(m.getStation("AlexandreDumas"), m.getStation("Gambetta"));
		assertEquals(true, areLinked);
	}
	
	@Test
	@DisplayName("Connection de deux stations de lignes différentes")
	void connectedStationsDifLignes2() {
		boolean areLinked=m.areConnected(m.getStation("AnatoleFrance"), m.getStation("Berault"));
		assertEquals(true, areLinked);
	}
	
	@Test
	@DisplayName("Connection de deux stations de lignes différentes, choisies au hasard")
	void connectedStations3DifLignes() {
		boolean areLinked=m.areConnected(m.getStation("Gambetta"), m.getStation("ColonnelFabien"));
		assertEquals(true, areLinked);
	}

}
