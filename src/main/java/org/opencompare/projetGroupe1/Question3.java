package org.opencompare.projetGroupe1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.opencompare.api.java.Cell;
import org.opencompare.api.java.Feature;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.PCMContainer;
import org.opencompare.api.java.Product;
import org.opencompare.api.java.impl.io.KMFJSONLoader;
import org.opencompare.api.java.io.CSVExporter;
import org.opencompare.api.java.io.PCMLoader;

public class Question3 {
	
List<List<Cell>> celules=new ArrayList<>();
List<String>temporaireCele=new ArrayList<String>();
List<PCMContainer> pcmContainers=new ArrayList<PCMContainer>();
List<String>  titre=new ArrayList();

List<List<String>> contenuFinal=new ArrayList();

 public void fonction() throws IOException{
      System.out.println("------------------------------------------------------------------------------------------");
	  System.out.println("----------------------------------QUESTION 3----------------------------------------------");
	  System.out.println("Quelles sont les valeurs de cellules les plus fréquentes?");
	  System.out.println("Nous repondons à la question posée"+'\n');
		
	     //Recuperer la liste des fichiers du repertoire

		pcmContainers=Code.getListPCMFromDirectory("pcms");

		titre.add("NOM-CELLULE");
		titre.add("NOMBRE-OCCURENCE");		
		for (PCMContainer pcmContainer : pcmContainers) {
           // Get the PCM
          PCM pcm = pcmContainer.getPcm();
          // Find the cell corresponding to the current feature and product

          for (Feature feature : pcm.getConcreteFeatures()) {

          // Browse the cells of the PCM
          for (Product product : pcm.getProducts()) {
        // Find the cell corresponding to the current feature and product
        	  
         celules.add(product.getCells());
             }}}

         for(List<Cell> celuls : celules)
           {
			 for(Cell celule : celuls){ 
			String contenu=celule.getContent();
			contenu=contenu.replace(";"," ");
			contenu=contenu.replace('\n', ' ');
             temporaireCele.add(contenu) ;
		    	 
			 }}
         
HashMap<String,Integer> lesOccurrences=Code.getOccurrence(temporaireCele);
  //export the csv file

	List<List<String>> contenuFinal= Code.getListOfListFromHashMap(lesOccurrences);
	Code.exportCSV(titre, contenuFinal, "occurenece-cellule");
 
 }
	
 public static void main(String[] args) throws IOException {
		Question3 q=new Question3();
		q.fonction();
		
	}
	
}
