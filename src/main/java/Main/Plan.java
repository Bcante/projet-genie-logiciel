package Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;

import metroproject.Ligne;
import metroproject.Metro;
import metroproject.Rail;
import metroproject.Station;

public class Plan {

	public Metro creerMetroParis() {
		Metro metroParis = new Metro("Paris");

		HashMap<String, Station> listeStation = new HashMap<String, Station>();
		HashMap<String, Ligne> listeLigne = new HashMap<String, Ligne>();
		
		ArrayList<Rail> listeRail = new ArrayList<Rail>();
		
		LinkedHashSet<Station> listL1 = new LinkedHashSet<Station>();
		LinkedHashSet<Station> listL2 = new LinkedHashSet<Station>();
		LinkedHashSet<Station> listL3 = new LinkedHashSet<Station>();
		LinkedHashSet<Station> listL4 = new LinkedHashSet<Station>();
		LinkedHashSet<Station> listL5 = new LinkedHashSet<Station>();

		// Ligne 1 Argentine -> ChampsElysee
		listeStation.put("Argentine", new Station("Argentine"));
		listeStation.put("Bastille", new Station("Bastille"));
		listeStation.put("Berault", new Station("Berault"));
		listeStation.put("ChampsElysee", new Station("ChampsElysee"));

		// Ligne 2 AlexandreDumas -> Pigalle
		listeStation.put("AlexandreDumas", new Station("AlexandreDumas"));
		listeStation.put("Anvers", new Station("Anvers"));
		listeStation.put("Avron", new Station("Avron"));
		listeStation.put("Barbes", new Station("Barbes"));
		listeStation.put("Pigalle", new Station("Pigalle"));

		// Ligne 3
		listeStation.put("AnatoleFrance", new Station("AnatoleFrance"));
		listeStation.put("ArtsEtMetiers", new Station("ArtsEtMetiers"));
		listeStation.put("Bourse", new Station("Bourse"));
		listeStation.put("Europe", new Station("Europe"));

		// Ligne 4
		listeStation.put("ChateauDeVincennes", new Station("ChateauDeVincennes"));
		listeStation.put("Chatelet", new Station("Chatelet"));
		listeStation.put("Couronnes", new Station("Couronnes"));

		// Ligne 5
		listeStation.put("BelleVille", new Station("BelleVille"));
		listeStation.put("Blanche", new Station("Blanche"));
		listeStation.put("Gallieni", new Station("Gallieni"));
		listeStation.put("Gambetta", new Station("Gambetta"));

		// LigneCommune
		listeStation.put("CharlesDeGaule", new Station("CharlesDeGaule"));// 1 et 2 et 4
		listeStation.put("Concorde", new Station("Concorde"));// 1,3,5
		listeStation.put("Esplanade", new Station("Esplanade"));// 2,4
		listeStation.put("FranklinDRoosvlet", new Station("FranklinDRoosvlet"));// 2,3
		listeStation.put("ColonnelFabien", new Station("ColonnelFabien"));// 2,3
		listeStation.put("Courcelles", new Station("Courcelles"));// 4,5
		listeStation.put("Jaures", new Station("Jaures"));// 2,5

		// Rails L1
		listeRail.add(new Rail(listeStation.get("Argentine"), listeStation.get("Bastille"), 2, false));
		listeRail.add(new Rail(listeStation.get("Bastille"), listeStation.get("CharlesDeGaule"), 4,false));
		listeRail.add(new Rail(listeStation.get("CharlesDeGaule"), listeStation.get("Berault"), 4,false));
		listeRail.add(new Rail(listeStation.get("Berault"), listeStation.get("Concorde"), 2, false));
		listeRail.add(new Rail(listeStation.get("Concorde"), listeStation.get("ChampsElysee"), 1, false));

		listL1.add(listeStation.get("Argentine"));
		listL1.add(listeStation.get("Bastille"));
		listL1.add(listeStation.get("CharlesDeGaulle"));
		listL1.add(listeStation.get("Berault"));
		listL1.add(listeStation.get("Concorde"));
		listL1.add(listeStation.get("ChampsElysee"));
		
		// Rails L2
		listeRail.add(new Rail(listeStation.get("AlexandreDumas"), listeStation.get("Anvers"), 3,false));
		listeRail.add(new Rail(listeStation.get("Anvers"), listeStation.get("Esplanade"), 2,false));
		listeRail.add(new Rail(listeStation.get("Esplanade"), listeStation.get("FranklinDRoosvlet"), 2,false));
		listeRail.add(new Rail(listeStation.get("FranklinDRoosvlet"), listeStation.get("Avron"), 2,false));
		listeRail.add(new Rail(listeStation.get("Avron"), listeStation.get("CharlesDeGaulle"), 3,false));
		listeRail.add(new Rail(listeStation.get("CharlesDeGaulle"), listeStation.get("ColonnelFabien"), 2,false));
		listeRail.add(new Rail(listeStation.get("ColonnelFabien"), listeStation.get("Barbes"), 1,false));
		listeRail.add(new Rail(listeStation.get("Barbes"), listeStation.get("Pigalle"), 3,false));
		
		listL2.add(listeStation.get("ALexandreDumas"));
		listL2.add(listeStation.get("Anvers"));
		listL2.add(listeStation.get("Esplanade"));
		listL2.add(listeStation.get("FranklinDRoosvlet"));
		listL2.add(listeStation.get("Avron"));
		listL2.add(listeStation.get("CharlesDeGaulle"));
		listL2.add(listeStation.get("ColonnelFabien"));
		listL2.add(listeStation.get("Barbes"));
		listL2.add(listeStation.get("Pigalle"));
		
		// Rails L3
		listeRail.add(new Rail(listeStation.get("AnatoleFrance"), listeStation.get("ArtsEtMetiers"), 2,false));
		listeRail.add(new Rail(listeStation.get("ArtsEtMetiers"), listeStation.get("Concorde"), 3,false));
		listeRail.add(new Rail(listeStation.get("Concorde"), listeStation.get("Bourse"), 2,false));
		listeRail.add(new Rail(listeStation.get("Bourse"), listeStation.get("FranklinDRoosvlet"), 3,false));
		listeRail.add(new Rail(listeStation.get("FranklinDRoosvlet"), listeStation.get("ColonnelFabien"), 2,false));
		listeRail.add(new Rail(listeStation.get("ColonnelFabien"), listeStation.get("Barbes"), 1,false));
		
		listL3.add(listeStation.get("AnatoleFrance"));
		listL3.add(listeStation.get("ArtsEtMetiers"));
		listL3.add(listeStation.get("Concorde"));
		listL3.add(listeStation.get("Bourse"));
		listL3.add(listeStation.get("FranklinDRoosvlet"));
		listL3.add(listeStation.get("ColonnelFabien"));
		listL3.add(listeStation.get("Europe"));
		
		// Rails L4
		listeRail.add(new Rail(listeStation.get("ChateauDeVincennes"), listeStation.get("CharlesDeGaule"), 2,false));
		listeRail.add(new Rail(listeStation.get("CharlesDeGaulle"), listeStation.get("Chatelet"), 3,false));
		listeRail.add(new Rail(listeStation.get("Chatelet"), listeStation.get("Esplanade"), 2,false));
		listeRail.add(new Rail(listeStation.get("Esplanade"), listeStation.get("Courcelles"), 3,false));
		listeRail.add(new Rail(listeStation.get("Courcelles"), listeStation.get("Couronnes"), 2,false));
		
		listL4.add(listeStation.get("ChateauDeVincennes"));
		listL4.add(listeStation.get("CharlesDeGaule"));
		listL4.add(listeStation.get("Chatelet"));
		listL4.add(listeStation.get("Esplanade"));
		listL4.add(listeStation.get("Courcelles"));
		listL4.add(listeStation.get("Couronnes"));
		
		// Rails L5
		
		listeRail.add(new Rail(listeStation.get("Belleville"), listeStation.get("Blanche"), 2,false));
		listeRail.add(new Rail(listeStation.get("Blanche"), listeStation.get("Concorde"), 3,false));
		listeRail.add(new Rail(listeStation.get("Concorde"), listeStation.get("Courcelles"), 2,false));
		listeRail.add(new Rail(listeStation.get("Courcelles"), listeStation.get("Gallieni"), 3,false));
		listeRail.add(new Rail(listeStation.get("Gallieni"), listeStation.get("Jaures"), 1,false));
		listeRail.add(new Rail(listeStation.get("Jaures"), listeStation.get("Gambetta"), 1,false));
		
		listL5.add(listeStation.get("Belleville"));
		listL5.add(listeStation.get("Blanche"));
		listL5.add(listeStation.get("Concorde"));
		listL5.add(listeStation.get("Courcelles"));
		listL5.add(listeStation.get("Gallieni"));
		listL5.add(listeStation.get("Jaures"));
		listL5.add(listeStation.get("Gambetta"));

		listeLigne.put("1", new Ligne("1"));
		listeLigne.put("2", new Ligne("2"));
		listeLigne.put("3", new Ligne("3"));
		listeLigne.put("4", new Ligne("4"));
		listeLigne.put("5", new Ligne("5"));
		
		// Affectation des stations aux lignes
		listeLigne.get("1").setStationsLigneAllez(listL1);
		listeLigne.get("2").setStationsLigneAllez(listL2);
		listeLigne.get("3").setStationsLigneAllez(listL3);
		listeLigne.get("4").setStationsLigneAllez(listL4);
		listeLigne.get("5").setStationsLigneAllez(listL5);
		
		metroParis.setStations(listeStation);
		metroParis.setRails(listeRail);
		metroParis.setLignes(listeLigne);
		
		return metroParis;
	}
}
