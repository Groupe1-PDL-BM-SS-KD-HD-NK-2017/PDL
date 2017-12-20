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
				//Chargement du dossier contenant les fichiers
				
				List<PCMContainer> pcmConteneurs = Code.getListPCMFromDirectory("fichiers");
				
				//on crée les entêtes du fichiers CSV
				
				List<String> entete = new ArrayList();		
				entete.add("Noms PCM1");
				entete.add("Noms PCM2");
				entete.add("Pourcentage similaires");
		        entete.add("Pourcentage differences");
		        entete.add("Reponse");
		        List<List<String>> contenuGlobal = new ArrayList();
		        // on parcourt les PCM sauf le dernier pour pour fixer un premier
		        
		        ArrayList<ArrayList<String>> listeFeatures = new ArrayList<ArrayList<String>>();
		        ArrayList<String> listeNomPCM = new ArrayList<String>();
		        
		        for(PCMContainer pcmC : pcmConteneurs)
		        {
		        	PCM pcm = pcmC.getPcm();
		        	List<Feature> features= pcm.getConcreteFeatures();
		        	 ArrayList<String> current = new ArrayList<String>();
		             
					for(int k=0; k<=features.size()-1; k++) {
						
					   current.add( features.get(k).getName());		   
					}
					
					listeFeatures.add(current);
					listeNomPCM.add(pcm.getName()); 	
		        }     
		      
				for (int i=0; i<listeFeatures.size()-1; i++) {
					HashSet<String> contenus = new HashSet<String>();
					contenus.addAll(listeFeatures.get(i));
					
					for(int j=i+1; j<=listeFeatures.size()-1; j++) {
					     HashSet<String> content = new HashSet<String>();
					     content.addAll(listeFeatures.get(j));
					    	 // on crée les listes qui vont contenir les features des PCMS qu'on recupére en premier
							 // pour le comparer avec le second à chaque fois
							 
					     		//contenu
							    HashSet<String> similarites = new HashSet<String>(contenus);
								HashSet<String> differences = new HashSet<String>(contenus);
								similarites.retainAll(content);
								content.removeAll(similarites);
								 
								differences.removeAll(similarites);
								differences.addAll(content);
								
								// ici on calcule les pourcentages  
								
								float total=2*similarites.size()+differences.size();
								float pourcentageDiff=( (float)differences.size()/total)*100;
								float pourcentageSim= 100-pourcentageDiff;
								List<String> contenant= new ArrayList();
								
								//on ajoute les données dans le fichiers CSV
								
								contenant.add(listeNomPCM.get(i));
								contenant.add(listeNomPCM.get(j));
								contenant.add(pourcentageSim+"");
								contenant.add(pourcentageDiff+"");
								
								// à chaque ajout on compare si c'est >75 (le seuil) pour mettre la reponse
								
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
		
