package org.opencompare.projetGroupe1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.opencompare.api.java.AbstractFeature;
import org.opencompare.api.java.Cell;
import org.opencompare.api.java.Feature;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.PCMContainer;
import org.opencompare.api.java.Product;
import org.opencompare.api.java.impl.io.KMFJSONLoader;
import org.opencompare.api.java.io.CSVExporter;
import org.opencompare.api.java.io.PCMLoader;

public class Code {

	public static void main(String[] args) throws IOException {



		/*List<PCMContainer> pcmContainers=Code.getListPCMContainers(new File("fichiers\\Comparison_of_IOC,_FIFA,_and_ISO_3166_country_codes_1.pcm"));
		System.out.println(pcmContainers.size());

		PCM pcm = pcmContainers.get(0).getPcm();
		System.out.println(pcm.getName());
		System.out.println("Nombre Products : "+pcm.getProducts().size());
		System.out.println("Nombre Features : "+pcm.getConcreteFeatures().size());

		Feature f = pcm.getConcreteFeatures().get(0);
		System.out.println("Nom feature 1 : "+f.getName());
*/




		/*List<PCMContainer> pcmContainers = Code.getListPCMFromDirectory("fichiers");

		int ligne=1;
		int colonne = 1;
		for(PCMContainer pcmContainer : pcmContainers)
		{
			PCM pcm=pcmContainer.getPcm();
			List<Product> products = pcm.getProducts();
			List<Feature> features = pcm.getConcreteFeatures();

			for (Product p : products)
			{
				System.out.println("("+ligne+") ");
				colonne=1;
				for(int i=0;i<features.size();i++)
				{
					Feature f = features.get(i);
					System.out.print(" "+colonne+"  ");
					Cell c = p.findCell(f);
					colonne++;
				}

				ligne++;
			}

			//System.out.println( products.size()+" - "+features.size());



		}*/
		/*List<Feature> features = pcm.getConcreteFeatures();

		List<String> featuresString = new ArrayList();
		List<String> CellsString = new ArrayList();


		System.out.println(pcm.getName());
		System.out.println("Products "+products.size());
		System.out.println("Features "+features.size());

		for(Feature f:features)
		{
			featuresString.add(f.getName());

		}


		Product premierProduct=products.get(1);
		List<Cell> cellules = premierProduct.getCells();
		System.out.println(cellules.size() +"/");

		System.out.println("----------------Parcours--------------------");
		System.out.println("----------------Features--------------------");
		System.out.println(featuresString);
		System.out.println();

		for(Cell c:cellules)
		{
			CellsString.add(c.getContent());

		}

		System.out.println("----------------Cellules--------------------");
		System.out.println(CellsString);



		System.out.println("----------------getCell--------------------");

		List<String> cells=new ArrayList();

		for(Feature f : pcm.getConcreteFeatures())
		{
			Cell cellule = premierProduct.findCell(f);
			System.out.println(f.getName()+" /  "+cellule.getContent());
		}
		*/










	}






	/**
	 * Cette fonction charge le repertoire passé en paramètre et renvoi un PCMContainer qui se trouve dans tous les fichiers du repertoire
	 * @param directory : le chemin du repertoire
	 * @return : la liste de PCMContainer contenant dans les fichiers .pcm du repertoire chargé
	 */
	public static List<PCMContainer> getListPCMFromDirectory(String directory)
	{
		File repertoire = new File(directory);  			//Pour charger un repertoire
        File[] fichiers = repertoire.listFiles();			//Recuperer la liste des fichiers du repertoire

        PCMLoader loader = new KMFJSONLoader();
        //List<PCMContainer> pcmContainers=new ArrayList(),temp;


        List<PCMContainer> pcmContainers=new ArrayList();
        List<PCMContainer> temp;


        for(File fichier:fichiers)
        {

        	fichier.getAbsolutePath();
        	try {
				temp = loader.load(fichier);//chargement d'un fichier pcm par le chargeur
				pcmContainers.addAll(temp);

			} catch (IOException e) {
				e.printStackTrace();
			}

        }



		return pcmContainers;
	}




	/**
	 * Avoir les PCMContainers qui se trouvent dans le fichier passé en paramètre
	 * @param fichier : le fichier contenant les PCM
	 * @return une liste de PCMContainer
	 * @throws IOException
	 */
	//public static List<PCMContainer> getListPCMContainers(File fichier) throws IOException {
	public static List<PCMContainer> getListPCMContainers(File fichier){

		PCMLoader loader = new KMFJSONLoader(); // l'objet chargeur qui se chargera de charger les fichiers PCM
		try {
			return loader.load(fichier);  //on retourne la liste des PCMContainers qui est chargé par le chargeur (loader)
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}




	/**
	 * Cette fonction exporte le données passées en paramètre dans un fichier CSV
	 * @param head : une liste qui contient les entêtes
	 * @param content : Une liste de liste qui contient les données qui seront dans le fichier CSV
	 * @param finalFileName : Le nom du fichier CSV final
	 */
	public static void exportCSV(List<String> head, List<List<String>> content , String finalFileName)
	{
		StringBuffer texte=new StringBuffer("");
		char separator=';';

		for(String titre:head)
		{
			texte.append(titre.toString()+separator);
		}
		texte.deleteCharAt(texte.length()-1);
		texte.append('\n');

		//Parcours des données
		for(List<String> ligne : content )
		{

			for(String element : ligne)
			{
				texte.append(element+separator);
			}
			texte.deleteCharAt(texte.length()-1);
			texte.append('\n');

		}

		System.out.println(texte.toString());

		Writer writer;
		try {
			writer = new FileWriter(new File(""+finalFileName+".csv"));
			writer.write(texte.toString()) ;  // écriture dans le fichier
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}




	public static void exportPcmToCsv(PCMContainer conteneur)
	{
		CSVExporter csvCreator=new CSVExporter();
		String texteCsv=csvCreator.export(conteneur);
		texteCsv.replace(',', ';');


		Writer writer;
		try {
			writer = new FileWriter(new File("ok.csv"));
			writer.write(texteCsv) ;  // écriture dans le fichier
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}


	}



	/**
	 * retourne le noms des fichiers qui sont dans le repertoire passé en paramètre
	 * @param pathDirectory : nom du repertoire
	 * @return liste des noms
	 */
	public static File[] getListFileNameFromDirectory(String pathDirectory) {
		ArrayList<String> listeNom = new ArrayList();
		File repertoire = new File(pathDirectory); // Pour charger un repertoire
		return repertoire.listFiles(); // Recuperer la liste des fichiers du repertoire
	}




	//List<String>   -> HashMap<String,Integer>

	public static HashMap<String,Integer> getOccurrence(List<String> listeValeur)
	{
		HashMap<String,Integer> lesOccurrences=new HashMap();


		for(String element :  listeValeur)
		{
			element = element.trim();
			if(lesOccurrences.containsKey(element))
			{
				Integer occurence = lesOccurrences.get(element);
				occurence++;
				lesOccurrences.put(element,occurence);
			}
			else
			{
				lesOccurrences.put(element,1);
			}

		}

		return lesOccurrences;
	}




	//HashMap<String,Integer>  ->  List<List<String>>
	public static List<List<String>> getListOfListFromHashMap(HashMap<String,Integer> lesOccurrences)
	{
		 List<List<String>> laFameuseListe= new ArrayList();

		 Set<String> cles=lesOccurrences.keySet();
		 Iterator<String> it=cles.iterator();

		 while(it.hasNext())
		 {
			 ArrayList<String> ligneCourante = new ArrayList();

			 String cleCourante = it.next();
			 Integer valeurCourante = lesOccurrences.get(cleCourante);

			 ligneCourante.add(cleCourante);
			 ligneCourante.add(valeurCourante+"");
			 laFameuseListe.add(ligneCourante);
		 }

		 return laFameuseListe;
	}



	public static void exportCSVFromList(List<String> entete,List<String> listeDepart, String nomFinal)
	{
		HashMap<String,Integer> resultat = getOccurrence(listeDepart);
		List<List<String>> resultat2 = getListOfListFromHashMap(resultat);
		exportCSV(entete, resultat2, nomFinal);
	}


















}
