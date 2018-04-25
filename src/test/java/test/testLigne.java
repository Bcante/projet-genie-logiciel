package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import metroproject.Ligne;
import metroproject.Station;

class testLigne {
	Ligne ligneTest;
	LinkedHashSet<Station> listeStation;
	Station s1,s2,s3,s4;
	
	@BeforeEach
	void beforeEach() {
		ligneTest = new Ligne("test");
		listeStation = new LinkedHashSet<Station>();
		
		s1 = new Station("s1");
		s2 = new Station("s2");
		s3 = new Station("s3");
		s4 = new Station("s4");
			
		listeStation.add(s1);
		listeStation.add(s2);
		listeStation.add(s3);
		listeStation.add(s4);
		
		ligneTest.setStationsLigneAller(listeStation);
	}

	@Test
	@DisplayName("ListeRetour est inverse a listeAllez")
	void testReverse() {
		int i = ligneTest.getStationsLigneRetour().size()-1;
		Iterator<Station> iterator = ligneTest.getStationsLigneAller().iterator();
		ArrayList<Station> list = new ArrayList<Station>(ligneTest.getStationsLigneRetour());
		while(iterator.hasNext()) {
			assertEquals(iterator.next(),list.get(i));
			i--;
        }
	}
	
	@Test
	@DisplayName("Le départ allez est s1")
	void testGetDepartAllez() {
		assertEquals(s1,ligneTest.getDepartAller());
	}

	@Test
	@DisplayName("L'arrivée allez est s4")
	void testGetTerminusAllez() {
		assertEquals(s4,ligneTest.getTerminusAller());
	}
	
	@Test
	@DisplayName("Le départ retour est s4")
	void testGetDepartRetour() {
		assertEquals(s4,ligneTest.getDepartRetour());
	}

	@Test
	@DisplayName("L'arrivée retour est s1")
	void testGetTerminusRetour() {
		assertEquals(s1,ligneTest.getTerminusRetour());
	}

	
	
}
