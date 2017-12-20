package org.opencompare.projetGroupe1;

import org.opencompare.api.java.*;
import org.opencompare.api.java.io.CSVExporter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static org.opencompare.projetGroupe1.Code.exportCSV;
import static org.opencompare.projetGroupe1.Code.getListPCMFromDirectory;

public class Question6 {
    /**
     * this function makes it possible to calculate the degree of homogeneity of all thecolumns of all the PCMs contained in a file,
     * to say yes or not if the columns arehomogeneous and generates a file .CSV to visualize the
     * result(name of PCM, name of column "feature", the degree of homogeneity and non-homogeneity).
     */
    public static void main(String[] args) {
        fonction();
    }

    public static void fonction() {
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("----------------------------------QUESTION 6----------------------------------------------");
        System.out.println("Est-ce que les valeurs d?une colonne sont ?homogènes? (i.e., du meme ?type?)?");
        System.out.println("Nous repondons à la question posée");
        System.out.println();
        System.out.println();
        String lerepertoire = "fichiers/";
        List<PCMContainer> lesconteneurs;

        lesconteneurs = Code.getListPCMFromDirectory(lerepertoire);

        System.out.println(lesconteneurs.size());

        List<List<String>> listFinal = new ArrayList<>();
        int nombrepcm = 0;
        int x=0,y=0,z=0;
        for (PCMContainer unpcmContainer : lesconteneurs) {
            PCM lepcm = unpcmContainer.getPcm();
            System.out.println(lepcm.getName());
            nombrepcm++;

            for (Feature lefeature : lepcm.getConcreteFeatures()) {
                System.out.println("            le nom du feature est :                " + lefeature.getName());
                boolean homogene1 = true, homogene2 = true, homogene = true;
                int homo = 0, nonhomo = 0;
                List<String> feat_type = new ArrayList<>();
                List<Cell> listcell = lefeature.getCells();
                int nbcell = listcell.size();
                float homopercent;
                float nonhomopercent;
                System.out.println(" taille de la liste de cell du feature : " + nbcell);

                if (nbcell != 0) {
                    System.out.println(listcell.get(0));
                    System.out.println(listcell.get(0).getInterpretation());
                    if ((listcell.get(0).getInterpretation()) != null) {
                        String letexte = listcell.get(0).getInterpretation().toString();
                        z++;
                        String letype = letexte.substring(36, letexte.indexOf("@"));
                        for (int j = 1; j < nbcell; j++) {

                            int arobase = listcell.get(j).getInterpretation().toString().indexOf("@");
                            String autretype = listcell.get(j).getInterpretation().toString().substring(36, arobase);
                            System.out.println("    le type du comparé      " + autretype);

                            if (letype.equals(autretype)) {
                                homo++;
                                //System.out.println("incrementation homo : >>>>>>>>>>>>>> " + homo);

                            } else {
                                homogene2 = false;
                                nonhomo++;
                                //System.out.println("incrementation non homo : >>>>>>>>>>>>>> " + nonhomo);
                            }
                        }

                        if (!homogene2) {
                            homogene = false;
                        }
                        System.out.println("l'homogeneité--------------------: " + homogene);
                        System.out.println();
                        System.out.println("incrementation homogene : <<<<<<<<<<<<<<<<<<<< >>>>>>>>>>>>>> " + homo);
                        System.out.println("incrementation non homogene : <<<<<<<<<<<<<<<<<<<< >>>>>>>>>>>>>> " + nonhomo);

                        homopercent = (float) (homo + 1) / nbcell;
                        nonhomopercent = (float) nonhomo / nbcell;
                        if (((homo + 1) / nbcell) == 1){
                            x++;
                        }else if(((homo + 1) / nbcell) > 0.9) {
                            y++;
                        }else if (nonhomo / nbcell <=1.0) {
                            y++;
                        }

                        System.out.println("les pourcentages : -------->>>>>>>>>> homogeneité : " + homopercent * 100 + "%" + "-------->>>>>>>>non homo homogeneité : " + nonhomopercent * 100 + "%");
                        feat_type.add(lepcm.getName().replace('\n', ' ').replace(';', ' '));
                        feat_type.add(lefeature.getName().replace('\n', ' ').replace(';', ' '));
                        feat_type.add(String.valueOf(homogene));
                        feat_type.add(homopercent * 100 + "%");
                        feat_type.add(nonhomopercent * 100 + "%");
                        listFinal.add(feat_type);
                        System.out.println();
                        System.out.println();
                    }

                }
            }
            // System.out.println("le nombre de pcm : " + nombrepcm);
        }
        System.out.println("<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>> le nombre total de colonnes : " + z);
        System.out.println();
        System.out.println("<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>> le nombre de colonne homegene à 100% : " + x);
        System.out.println("<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>> le nombre de colonne non homogene à 100% : " + y);
        System.out.println();
        System.out.println();
        List<String> head = new ArrayList<>();
        head.add("Nom du PCM");
        head.add("Nom du Feature");
        head.add("Homogeneité des cellules");
        head.add("Pourcentage d'Homogeneité des cellules");
        head.add("Pourcentage de Non Homogeneité des cellules");
        Code.exportCSV(head, listFinal, "Question6");
    }
}
