package org.opencompare.projetGroupe1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.opencompare.api.java.Feature;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.PCMContainer;
import org.opencompare.api.java.impl.io.KMFJSONLoader;
import org.opencompare.api.java.io.PCMLoader;

public class principale {

	//NE MODIFIEZ PAS CETTE CLASSE SVP
	//VOUS POUVEZ COMPILER POUR VOIR L4AVANCEMENT DE CHACUN

	public static void main(String[] args) throws IOException {


		Question1.fonction();
		Question2.TailleMatrice();
		Question3 q3 = new Question3();
		q3.fonction();
		Question4.fonction();
		Question4.fonction2();
		Question5.fonction();
		Question6.fonction();
		Question7.similarites();
		//Question8.fonction("fichiers/Comparison_between_Esperanto_and_Novial_3.pcm");
		//System.out.println("Bon");

	}

}
