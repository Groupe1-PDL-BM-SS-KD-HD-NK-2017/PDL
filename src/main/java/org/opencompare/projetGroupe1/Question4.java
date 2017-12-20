package org.opencompare.projetGroupe1;

import java.io.File;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.opencompare.api.java.AbstractFeature;
import org.opencompare.api.java.Cell;
import org.opencompare.api.java.Feature;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.PCMContainer;
import org.opencompare.api.java.Product;
import org.opencompare.api.java.io.CSVExporter;

import scala.Option;

public class Question4 {

	static int limite_choisi = 50;
	static final int NOMBRE_FREQUENCE_MIN = 5;
	/**
	 * Quelles sont les valeurs de cellule qui sont le plus souvent ensemble
	 * dans une colonne ou une ligne?
	 */






	public static void fonction2() {

		File[] fichiers = Code.getListFileNameFromDirectory("fichiers");

		List<String> headerCSV=new ArrayList();
		List<List<String>> contentCSV=new ArrayList<List<String>>();


		headerCSV.add("Nom PCM");
		headerCSV.add("Mot 1");
		headerCSV.add("Mot 2");
		headerCSV.add("Frequence");


		
		//File fichier = new File("fichiers/Comparison_between_U.S._states_and_countries_by_GDP_(nominal)_1.pcm");
		File fichier = new File("fichiers/Comparison_of_Chinese_romanization_systems_0.pcm");

			//File fichier = new File("fichiers\\Comparison_of_e-book_readers_3.pcm");
			if(Question8.fonction(fichier.getAbsolutePath()))
			{


				List<PCMContainer> pcmContainers = Code.getListPCMContainers(fichier);
				ArrayList<ArrayList<String>> cellulesOrganisedByColomn = new ArrayList();
				HashMap<String , Integer> mapOccurence = new HashMap();



				for( PCMContainer pcmContainer : pcmContainers)
				{
					PCM pcm = pcmContainer.getPcm();
					List<Feature> features = pcm.getConcreteFeatures();
					List<Product> products = pcm.getProducts();
					List<String> listeCellules = new ArrayList(); //on prendra cette liste et le transformer en HashMap pour compter les nombre d'occurence

					//listeCellules.re

					//System.out.println(pcm.getName());
					int indice = 0;
					for(Feature feature : features)
					{
						//feature.get
						ArrayList<String> ligneCourante = new ArrayList(); //la liste intermediaire qui vaconstruire la liste des listes


						for(Product product : products)
						{
							Cell cellule = product.findCell(feature);
							ligneCourante.add(cellule.getContent());
							listeCellules.add(cellule.getContent());
						}

						cellulesOrganisedByColomn.add(ligneCourante);
					}

					//we save the the number of occurence of different value of cellule content in the HashMap
					mapOccurence = Code.getOccurrence(listeCellules);

					//we take the first 50
					ArrayList<Integer> frequences=new ArrayList();
					ArrayList<String> mots=new ArrayList();


					Set<String> cles = mapOccurence.keySet();
					Iterator<String> it=cles.iterator();


					//RANKING THE FIRST 50 HAVING HIGHTER FREQUENCY
					//RANKING THE FIRST 50 HAVING HIGHTER FREQUENCY
					//RANKING THE FIRST 50 HAVING HIGHTER FREQUENCY
					//RANKING THE FIRST 50 HAVING HIGHTER FREQUENCY
					//RANKING THE FIRST 50 HAVING HIGHTER FREQUENCY

					while(it.hasNext())
					{
						String cle = it.next();
						Integer valeurFrequence = mapOccurence.get(cle);
						boolean estAjoute = false ;


						if(frequences.size()==0)
						{
							frequences.add(valeurFrequence);
							mots.add(cle);
						}
						else if(frequences.size()<limite_choisi)
						{
							//in this section, we order according to the frequency
								for(int i=0 ; i < frequences.size() ; i++)
								{
									if(valeurFrequence > frequences.get(i))
									{
										frequences.add(i,valeurFrequence);
										mots.add(i,cle);
										estAjoute = true;
										break;
									}
								}
								if(!estAjoute)
								{
									frequences.add(valeurFrequence);
									mots.add(cle);

								}


						}
						else if(frequences.size()>=limite_choisi)
						{
							//if we are reached 50

							//We check if the last element is greater than the current element to add ,
							//if that is the case, we move to the next element
							if(frequences.get(frequences.size()-1) < valeurFrequence )
							{
								frequences.remove(frequences.size()-1);
								mots.remove(frequences.size()-1);
								//we browse the list to find a position to add the current element

								int limite = (frequences.size() < limite_choisi)?frequences.size():limite_choisi;
								for(int i=0 ; i < limite ; i++)
								{
									if(valeurFrequence > frequences.get(i))
									{
										frequences.add(i,valeurFrequence);
										mots.add(i,cle);
										estAjoute = true;
										break;
									}
								}
								if(!estAjoute)
								{
									frequences.add(valeurFrequence);
									mots.add(cle);

								}

							}
						}

					}

					//END OF RANKING
					//END OF RANKING



					//We try to find the the words that follow each other two by two
					int[][] matrice = new int[limite_choisi][limite_choisi];
					for (int i=0; i<matrice.length; i++)
					{
						for (int j=0; j<matrice[0].length; j++)
						{
							matrice[i][j]=0;
						}
					}


					String texte ="";
					String texteSuivant;


					for(ArrayList<String> ligne :cellulesOrganisedByColomn)
					{

						for(int i=0;i<ligne.size()-2;i++)
						{
							//We check if the both are in top 50
							int indiceX=mots.indexOf(ligne.get(i));
							int indiceY=mots.indexOf(ligne.get(i+1));

							if(indiceX>=0 && indiceY>=0)
							{
								matrice[indiceX][indiceY]++;
							}


						}


					}



					int compteur=0;
					ArrayList<String> currentLine = new ArrayList<String>();
					currentLine.add(pcm.getName());
					currentLine.add("");
					currentLine.add("");
					currentLine.add("");


					contentCSV.add(currentLine);


					for(int i=0;i<limite_choisi;i++)
					{
						for(int j=0;j<limite_choisi;j++)
						{
							if(matrice[i][j]>=NOMBRE_FREQUENCE_MIN && i!=j)
							{
								//System.out.println("--"+i+","+j+"  "+matrice[i][j]+"--"+mots.get(i)+"/-/-/"+mots.get(j));

								currentLine = new ArrayList<String>();
								currentLine.add(mots.get(i).replace('\n', ' '));
								currentLine.add(mots.get(j).replace('\n', ' '));
								currentLine.add(mots.get(j).replace('\n', ' '));
								currentLine.add(matrice[i][j]+"");

								contentCSV.add(currentLine);
								compteur++;
							}
						}
					}

					if(compteur==0)
					{
						currentLine = new ArrayList<String>();
						currentLine.add(" ");
						currentLine.add("");
						currentLine.add("");
						contentCSV.add(currentLine);

						currentLine = new ArrayList<String>();
						currentLine.add("Pas de mot frequent");
						currentLine.add("");
						currentLine.add("");
						contentCSV.add(currentLine);

						currentLine = new ArrayList<String>();
						currentLine.add(" ");
						currentLine.add("");
						currentLine.add("");
						contentCSV.add(currentLine);





					}




				}


			}





		if(contentCSV.size()!=0)
		{
			System.out.println(contentCSV.size()+" 22222");
			Code.exportCSV(headerCSV, contentCSV, "lesMotsFrequentUnFichier");
		}


		//System.out.println("Terminé");

	} //fonction




	public static void fonction() {

		File[] fichiers = Code.getListFileNameFromDirectory("fichiers");

		List<String> headerCSV=new ArrayList();
		List<List<String>> contentCSV=new ArrayList<List<String>>();


		headerCSV.add("Nom PCM");
		headerCSV.add("Mot 1");
		headerCSV.add("Mot 2");
		headerCSV.add("Frequence");


		for(File fichier : fichiers)
		{



			//File fichier = new File("fichiers\\Comparison_of_e-book_readers_3.pcm");
			if(Question8.fonction(fichier.getAbsolutePath()))
			{


				List<PCMContainer> pcmContainers = Code.getListPCMContainers(fichier);
				ArrayList<ArrayList<String>> cellulesOrganisedByColomn = new ArrayList();
				HashMap<String , Integer> mapOccurence = new HashMap();



				for( PCMContainer pcmContainer : pcmContainers)
				{
					PCM pcm = pcmContainer.getPcm();
					List<Feature> features = pcm.getConcreteFeatures();
					List<Product> products = pcm.getProducts();
					List<String> listeCellules = new ArrayList(); //on prendra cette liste et le transformer en HashMap pour compter les nombre d'occurence

					//listeCellules.re

					//System.out.println(pcm.getName());
					int indice = 0;
					for(Feature feature : features)
					{
						//feature.get
						ArrayList<String> ligneCourante = new ArrayList(); //la liste intermediaire qui vaconstruire la liste des listes


						for(Product product : products)
						{
							Cell cellule = product.findCell(feature);
							ligneCourante.add(cellule.getContent());
							listeCellules.add(cellule.getContent());
						}

						cellulesOrganisedByColomn.add(ligneCourante);
					}

					//we save the the number of occurence of different value of cellule content in the HashMap
					mapOccurence = Code.getOccurrence(listeCellules);

					//we take the first 50
					ArrayList<Integer> frequences=new ArrayList();
					ArrayList<String> mots=new ArrayList();


					Set<String> cles = mapOccurence.keySet();
					Iterator<String> it=cles.iterator();


					//RANKING THE FIRST 50 HAVING HIGHTER FREQUENCY
					//RANKING THE FIRST 50 HAVING HIGHTER FREQUENCY
					//RANKING THE FIRST 50 HAVING HIGHTER FREQUENCY
					//RANKING THE FIRST 50 HAVING HIGHTER FREQUENCY
					//RANKING THE FIRST 50 HAVING HIGHTER FREQUENCY

					while(it.hasNext())
					{
						String cle = it.next();
						Integer valeurFrequence = mapOccurence.get(cle);
						boolean estAjoute = false ;


						if(frequences.size()==0)
						{
							frequences.add(valeurFrequence);
							mots.add(cle);
						}
						else if(frequences.size()<limite_choisi)
						{
							//in this section, we order according to the frequency
								for(int i=0 ; i < frequences.size() ; i++)
								{
									if(valeurFrequence > frequences.get(i))
									{
										frequences.add(i,valeurFrequence);
										mots.add(i,cle);
										estAjoute = true;
										break;
									}
								}
								if(!estAjoute)
								{
									frequences.add(valeurFrequence);
									mots.add(cle);

								}


						}
						else if(frequences.size()>=limite_choisi)
						{
							//if we are reached 50

							//We check if the last element is greater than the current element to add ,
							//if that is the case, we move to the next element
							if(frequences.get(frequences.size()-1) < valeurFrequence )
							{
								frequences.remove(frequences.size()-1);
								mots.remove(frequences.size()-1);
								//we browse the list to find a position to add the current element

								int limite = (frequences.size() < limite_choisi)?frequences.size():limite_choisi;
								for(int i=0 ; i < limite ; i++)
								{
									if(valeurFrequence > frequences.get(i))
									{
										frequences.add(i,valeurFrequence);
										mots.add(i,cle);
										estAjoute = true;
										break;
									}
								}
								if(!estAjoute)
								{
									frequences.add(valeurFrequence);
									mots.add(cle);

								}

							}
						}

					}

					//END OF RANKING
					//END OF RANKING



					//We try to find the the words that follow each other two by two
					int[][] matrice = new int[limite_choisi][limite_choisi];
					for (int i=0; i<matrice.length; i++)
					{
						for (int j=0; j<matrice[0].length; j++)
						{
							matrice[i][j]=0;
						}
					}


					String texte ="";
					String texteSuivant;


					for(ArrayList<String> ligne :cellulesOrganisedByColomn)
					{

						for(int i=0;i<ligne.size()-2;i++)
						{
							//We check if the both are in top 50
							int indiceX=mots.indexOf(ligne.get(i));
							int indiceY=mots.indexOf(ligne.get(i+1));

							if(indiceX>=0 && indiceY>=0)
							{
								matrice[indiceX][indiceY]++;
							}


						}


					}



					int compteur=0;
					ArrayList<String> currentLine = new ArrayList<String>();
					currentLine.add(pcm.getName());
					currentLine.add("");
					currentLine.add("");
					currentLine.add("");


					contentCSV.add(currentLine);


					for(int i=0;i<limite_choisi;i++)
					{
						for(int j=0;j<limite_choisi;j++)
						{
							if(matrice[i][j]>=NOMBRE_FREQUENCE_MIN && i!=j)
							{
								//System.out.println("--"+i+","+j+"  "+matrice[i][j]+"--"+mots.get(i)+"/-/-/"+mots.get(j));

								currentLine = new ArrayList<String>();
								currentLine.add(mots.get(i).replace('\n', ' '));
								currentLine.add(mots.get(j).replace('\n', ' '));
								currentLine.add(matrice[i][j]+"");

								contentCSV.add(currentLine);
								compteur++;
							}
						}
					}

					if(compteur==0)
					{
						currentLine = new ArrayList<String>();
						currentLine.add(" ");
						currentLine.add("");
						currentLine.add("");
						contentCSV.add(currentLine);

						currentLine = new ArrayList<String>();
						currentLine.add("Pas de mot frequent");
						currentLine.add("");
						currentLine.add("");
						contentCSV.add(currentLine);

						currentLine = new ArrayList<String>();
						currentLine.add(" ");
						currentLine.add("");
						currentLine.add("");
						contentCSV.add(currentLine);

					}




				}


			}



		}

		if(contentCSV.size()!=0)
		{
			Code.exportCSV(headerCSV, contentCSV, "lesMotsFrequent");
		}


		//System.out.println("Terminé");

	} //fonction



} //Class Question4
