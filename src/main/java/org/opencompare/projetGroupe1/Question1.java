package org.opencompare.projetGroupe1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.HashMap;

import org.opencompare.MyPCMPrinter;
import org.opencompare.api.java.Feature;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.PCMContainer;
import org.opencompare.api.java.Product;
import org.opencompare.api.java.impl.io.KMFJSONLoader;
import org.opencompare.api.java.io.PCMLoader;

public class Question1 {

	/**
	 * Quels sont les noms de “features”/”produits” qui reviennent souvent?
	 */

	public static void main(String[] args) {
		fonction();
	}

	public static void fonction() {
		System.out
				.println("------------------------------------------------------------------------------------------");
		System.out
				.println("----------------------------------QUESTION 1----------------------------------------------");
		System.out.println("Quels sont les noms de “features”/”produits” qui reviennent souvent?");
		System.out.println("Nous repondons à la question posée");
		List<String> entete= new ArrayList();
		entete.add("Nom de Feature");
		entete.add("Ocurrence");
		
		List<String> entete1= new ArrayList();
		entete1.add("Nom de Product");
		entete1.add("Ocurrence");
		File[] fichiers = Code.getListFileNameFromDirectory("fichiers");
		
		try {
			System.out.println("la liste des noms de feature");	
		Code.exportCSVFromList( entete, FeaturesNameList(fichiers), "OccurenceFeature");
			System.out.println("la liste des noms de product");	
		Code.exportCSVFromList( entete1, ProductsNameList(fichiers), "OccurenceProduct");
		System.out.println("les PCM ayant au moins un nom de feature vide");	
		FeaturesNonVide(fichiers);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/*
	 * String cheminRepetoire;
	 *
	 * public Repertoire(String cheminRepetoire) { super(); this.cheminRepetoire
	 * = cheminRepetoire; }
	 */
	/**
	 * @param cheminRepetoire
	 * @return pour charger less fichiers d'un repetoire
	 */
	public static File[] LoadFile(String cheminRepetoire) {

		// this.cheminRepetoire=cheminRepetoire;
		File rep = new File(cheminRepetoire);
		File[] fichiers = rep.listFiles();

		return fichiers;
	}
/* 
 * Cette fonction retourne la liste des nom de features contenu dans la liste des fichiers  
 * passée en parametres
 */
	public static List<String> FeaturesNameList(File[] fichiers) throws IOException {

		List<String> FeatureList = new ArrayList();
		
		for (File f : fichiers) {
			List<PCMContainer> pcmContainers = Code.getListPCMContainers(f);

			for (PCMContainer pcmContainer : pcmContainers) {

				// Get the PCM
				PCM pcm = pcmContainer.getPcm();

				// Browse the cells of the PCM

				for (Feature feature : pcm.getConcreteFeatures()) {
					
					FeatureList.add(feature.getName().replace('\n', ' ').replace(';', ' '));
									 
				}
				
			}
		}
		return FeatureList;
	}
	
	/*
	 * Cette fonction retourne la liste des nom de products contenu dans la liste des fichiers  
 * passée en parametres
	 */
	public static List<String> ProductsNameList(File[] fichiers) throws IOException {
		MyPCMPrinter print = new MyPCMPrinter();
		List<String> ProductList = new ArrayList();
		
		for (File f : fichiers) {
			List<PCMContainer> pcmContainers = Code.getListPCMContainers(f);

			for (PCMContainer pcmContainer : pcmContainers) {

				// Get the PCM
				PCM pcm = pcmContainer.getPcm();

				// Browse the cells of the PCM
				
				// get the list of products name
				for (Product product : pcm.getProducts()) {
					if( product.getKeyContent() != null){
						
					ProductList.add(product.getKeyContent().replace('\n', ' ').replace(';',' '));
					System.out.println(product.getKeyContent());
					}
				}
				
			}
		}
	   return ProductList;
	}
	
	/*
	 * Fonction pour verifier que les noms de features que notre fonction 
	 * retourne vide  sont reelement vide
	 */
	public static void FeaturesNonVide(File[] fichiers) throws IOException {

		
		int i=0; int a=0;
		for (File f : fichiers) {
			List<PCMContainer> pcmContainers = Code.getListPCMContainers(f);

			for (PCMContainer pcmContainer : pcmContainers) {

				// Get the PCM
				PCM pcm = pcmContainer.getPcm();

				// Browse the cells of the PCM
				
					
				for (Feature feature : pcm.getConcreteFeatures()) {
					if(feature.getName().trim()==""){
				
					System.out.println(pcm ); 
						System.out.println("nom de feature"+ feature.getName());
						
					}
					
					
				}
				
			}
		}
		
	}

	
}


