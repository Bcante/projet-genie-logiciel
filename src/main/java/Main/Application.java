package Main;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Application {

	public static void main(String[] args) {
		String geoloc;
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
		String[] res;
		try {
			CommandLine line = parser.parse(options, args);
			geoloc=line.getOptionValue("g");
			dest=line.getOptionValue("d");
			res = geoloc.split(",");
			if (res.length!=2) {
				throw new ParseException(geoloc);
			}
			if (line.hasOption("lp")) {
				//chemin avec le moins de changements
			}
			else if (line.hasOption("cp")) {
				//itinéraire perso
			}
			else {
				//chemin le plus rapide (par défaut)
			}
		}catch(ParseException e){
			System.out.println("Problème de parsing. Les argument -g (géolocalisation) et -d (Station de destination) sont-ils bien renseignés?");
		}
		
		
	
	}
	
}
