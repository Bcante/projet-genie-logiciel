package Main;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import metroproject.Station;
import metroproject.Utilisateur;

public class Application {

	public static void main(String[] args) {
	/*	String geoloc;
		String dest;
		Options options=new Options();
		Option geolocalisation=new Option("g","geolocalisation",true,"Géolocalisation de l'utilisateur");
		geolocalisation.setRequired(true);
		options.addOption(geolocalisation);
		Option destination=new Option("d","destination",true,"Station destination souhaitée par l'utilisateur");
		destination.setRequired(true);
		options.addOption(destination);
		//si pas d'option de chemin précisée, on fait le plus rapide par défaut
		Option fastestPath=new Option("fp","Chemin le plus rapide",false,"Permet d'obtenir le chemin le plus rapide jusqu'à la station désirée");
		Option customPath=new Option("cp","Itinéraire personnalisé",true,"Permet d'obtenir un itinéraire passant par une station spécifique");
		Option leastTransferPath=new Option("lp","Itinéraire avec le moins de changements",true,"Permet d'obtenir un itinéraire passant par le moins de lignes possible");
		options.addOption(fastestPath);
		options.addOption(customPath);
		options.addOption(leastTransferPath);
		CommandLineParser parser = new DefaultParser();
		
		//initialisations des valeurs
		String[] res=new String[2];
		String depart;
		ArrayList<Station> path=new ArrayList<Station>();
		IHM utilisation=new IHM();
		String outUser="";
		try {
			CommandLine line = parser.parse(options, args);
			geoloc=line.getOptionValue("g");
			dest=line.getOptionValue("d");
			res = geoloc.split(",");
			if (res.length!=2) {
				throw new ParseException(geoloc);
			}
			Utilisateur u=new Utilisateur(Integer.parseInt(res[0]), Integer.parseInt(res[1]),"xxx");
			depart=utilisation.findClosestStation(u);
			if (line.hasOption("lp")) {
				//itinéraire avec le moins de changements 
			}
			else if (line.hasOption("cp")) {
				//itinéraire perso
			}
			else {
				//chemin le plus rapide (par défaut)
				outUser=utilisation.shortestPath(utilisation.getM().getStation(depart), utilisation.getM().getStation(depart));
			}
		}catch(ParseException e){
			System.out.println("Problème de parsing. Les argument -g (géolocalisation) et -d (Station de destination) sont-ils bien renseignés?");
		}
		System.out.println(outUser);*/

		int choix = 0;
		Scanner scanner = new Scanner(System.in);
		Utilisateur user = new Utilisateur(0,0,"Bobby McUserinton")
		do {
			System.out.println("Menu principal");
			System.out.println("0- Quitter");
			System.out.println("1- Trouver un chemin");
			System.out.println("2- Maj position");
			System.out.println("3- Connaitre les perturbations");
			choix = Integer.parseInt(scanner.nextLine());

			switch (choix) {
				case 1:
					findPath();
					break;
				case 2:
					majLoc(user);
					break;
				case 3:
					getInfoPerturbations();
					break;
				default:
					choix = -1;
			}
		} while (choix != 0);

	}

	private static void majLoc(Utilisateur u) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Indiquez la nouvelle coordonnée X: ");
		int newX = Integer.parseInt(scanner.nextLine());
		System.out.println("Indiquez la nouvelle coordonnée Y: ");
		int newY = Integer.parseInt(scanner.nextLine());
		u.setX(newX);
		u.setY(newY);
	}

	private static void getInfoPerturbations() {
	}

	private static void findPath() {
	}


}
