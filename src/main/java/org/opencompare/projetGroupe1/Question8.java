package org.opencompare.projetGroupe1;

import java.io.File;

import java.util.List;

import org.opencompare.api.java.Cell;
import org.opencompare.api.java.Feature;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.PCMContainer;
import org.opencompare.api.java.Product;

public class Question8 {

	/**
	 * Dans quels cas la procédure d’extraction est défectueuse?
	 */


	public static boolean fonction(String path)
	{
		System.out.println("------------------------------------------------------------------------------------------");
		System.out.println("----------------------------------QUESTION 8----------------------------------------------");
		System.out.println("Dans quel cas la procedure est defectueuse”?");

		File fichier = new File(path);
		List<PCMContainer> pcmContainers = Code.getListPCMContainers(new File(path));

		for ( PCMContainer pcmContainer : pcmContainers)
		{

			PCM pcm = pcmContainer.getPcm();
			List<Feature> features = pcm.getConcreteFeatures();
			List<Product> products = pcm.getProducts();

			for( Product product : products)
			{
				for( Feature feature : features)
				{
					Cell cellule = product.findCell(feature);
					if(cellule == null)
					{
						return false;
					}
				}
			}

		}

		return true;
	}



}
