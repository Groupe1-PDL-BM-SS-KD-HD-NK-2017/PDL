		package org.opencompare.projetGroupe1;
		
		import java.io.File;
		import java.io.IOException;
		import java.util.ArrayList;
		import java.util.List;
		
		public class Question2 {
		
			/**
			 * Quelles sont les tailles des matrices?
			 */
			public static void main(String[] args) {
				TailleMatrice();
			}
			
			public static void TailleMatrice() {
				//Chargement du dossier contenant les fichiers
				
				File fichiers[]=Code.getListFileNameFromDirectory("fichiers");
				
				//on crée les entêtes du fichiers CSV
				List<String> entete = new ArrayList();
				entete.add("Noms");
				entete.add("Nombre Features");
				entete.add("Nombre Products");
				entete.add("Taille PCM");
				List<List<String>> Contenus= new ArrayList();
				// ici on parcourt tous les fichiers et on récupére à chaque fois la taille des featues et des products
				for(File fichier:fichiers) 
				{	
					
					int sizeFeatures= Code.getListPCMContainers( fichier ).get(0).getPcm().getFeatures().size();
					int sizeProducts=Code.getListPCMContainers(fichier).get(0).getPcm().getProducts().size();
					
					// on calcule le résultat
					
					int resultat =sizeFeatures*(sizeProducts + 1);
					List<String> contenu= new ArrayList();
					
					System.out.print(" Fichier :  "  + fichier.getName() +  "   ");
					System.out.println(" taille features  " + sizeFeatures +  " taille products  "  + sizeProducts +  " taille matrice  " + resultat );
					//on ajoute les données dans le fichiers CSV
					contenu.add(fichier.getName());
					contenu.add(sizeFeatures+"");
					contenu.add(sizeProducts+"");
					contenu.add(resultat+"");
					Contenus.add(contenu);
				}
					Code.exportCSV(entete, Contenus, "fichiers");
				
				
			   }
				
			
			 
		}
