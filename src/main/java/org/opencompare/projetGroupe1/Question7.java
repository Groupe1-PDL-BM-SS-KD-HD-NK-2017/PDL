package org.opencompare.projetGroupe1;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.opencompare.api.java.AbstractFeature;
import org.opencompare.api.java.Feature;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.PCMContainer;
import org.opencompare.api.java.Product;

public class Question7 {
	private final static double seuil =  75.0; 
	/**
	 * Est-ce qu’on peut identifier des matrices “similaires”?
	 */
	public static void main(String[] args) {
		long debut=System.currentTimeMillis();
		similarites();
		long fin = System.currentTimeMillis();
		System.out.println(fin-debut);		
	}

	public static void similarites() {
		
		System.out
				.println("------------------------------------------------------------------------------------------");
		System.out
				.println("----------------------------------QUESTION 7----------------------------------------------");
		System.out.println("Est-ce qu’on peut idenifier des matrices “similaires”?");
		System.out.println("Nous repondons à la question posée");

		List<PCMContainer> pcmConteneurs = Code.getListPCMFromDirectory("fichiers");
		List<String> entete = new ArrayList();
		
		
		entete.add("Noms PCM1");
		entete.add("Noms PCM2");
		entete.add("Pourcentage similaires");
        entete.add("Pourcentage differences");
        entete.add("Reponse");
        List<List<String>> contenuGlobal = new ArrayList();
        
		for (int i=0; i<pcmConteneurs.size()-1; i++) {
			PCMContainer pcmConteneur = pcmConteneurs.get(i);
			PCM pcm=pcmConteneur.getPcm();
			HashSet<String> contenus = new HashSet<String>();
			List<Feature> features= pcm.getConcreteFeatures();
			
			for(int k=0; k<=features.size()-1; k++) {
			   contenus.add( features.get(k).getName());
			   
			}
			
			
			
			   for(int j=i+1; j<=pcmConteneurs.size()-1; j++) {
				 PCMContainer pcmConteneur2 = pcmConteneurs.get(j);
			     PCM pcm2 = pcmConteneur2.getPcm();
			     HashSet<String> content = new HashSet<String>();
			     List<Feature> features1= pcm2.getConcreteFeatures();
			     
					 for (int k=0; k<=features1.size()-1; k++) {
					    content.add( features1.get(k).getName());
					 }
					    HashSet<String> similarites = new HashSet<String>(contenus);
						HashSet<String> differences = new HashSet<String>(contenus);
						similarites.retainAll(content);
						content.removeAll(similarites);
						 
						differences.removeAll(similarites);
						differences.addAll(content);
						 
						float total=2*similarites.size()+differences.size();
						float pourcentageDiff=( (float)differences.size()/total)*100;
						float pourcentageSim= 100-pourcentageDiff;
						List<String> contenant= new ArrayList();					
						contenant.add(pcm.getName());
						contenant.add(pcm2.getName());
						contenant.add(pourcentageSim+"");
						contenant.add(pourcentageDiff+"");
						if(pourcentageSim>seuil) {
					       contenant.add("oui");		
						}else {
							contenant.add("non");
						}
						contenuGlobal.add(contenant);
						 
						System.out.println(similarites.toString());
						System.out.println(differences.toString());
						System.out.println(pourcentageSim);
					    System.out.println(pourcentageDiff);
					    
			}	
		}
				
		 Code.exportCSV(entete, contenuGlobal, "MatricesSimilaires"); 
   }	 		

}

