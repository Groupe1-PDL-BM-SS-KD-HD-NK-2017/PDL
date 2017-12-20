package org.opencompare.projetGroupe1;

import org.opencompare.api.java.Cell;
import org.opencompare.api.java.Feature;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.PCMContainer;

import java.util.ArrayList;
import java.util.List;

public class Question5 {

    /**
     * Y a-t-il une correlation entre le “type” prédominant d’un “feature” et les valeurs des cellules?
     */
    public static void main(String[] args) {
        fonction();
    }

    public static void fonction() {
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("----------------------------------QUESTION 5----------------------------------------------");
        System.out.println("Y a-t-il une correlation entre le “type” prédominant d’un “feature” et les valeurs des cellules?");
        System.out.println("Nous repondons à la question posée");

        String lerepertoire = "fichiers/";
        List<PCMContainer> lesconteneurs;
        lesconteneurs = Code.getListPCMFromDirectory(lerepertoire);
        System.out.println("*********     ***********      ***********");
        System.out.println("Le nombre de fichiers ou PCM dans le repertoire est : " + lesconteneurs.size());
        System.out.println("*********     ***********      ***********");
        System.out.println();
        System.out.println();

        int nombrepcm = 0; //le nombre de PCM dans le fichier
        int nombrefeature =0; // Le nombre de features dans le PCM
        int nbrbooltotal = 0, nbrdecelltotal = 0; //le nombre total de cell a type boolean
        int cumulno = 0, cumulyes = 0; //le nombre des cellules ayant pour contenu yes ou no relative au type booleen
        int autre = 0; //le nombre des cellules ayant pour contenu true ou false relative au type booleen
        int nbrStrg =0, nbrReal = 0, nbrInteg =0, nbrNotAvai =0, nbrMulti =0, nbrCond = 0, autretype = 0;
        ArrayList<String> typeautres = new ArrayList<>();
        ArrayList<String> typeString = new ArrayList<>();
        ArrayList<String> typeNotAvai = new ArrayList<>();
        for (PCMContainer unpcmContainer : lesconteneurs) {
            PCM lepcm = unpcmContainer.getPcm();
            System.out.println("----Le PCM : " + lepcm.getName() + " ----contient : " + lepcm.getConcreteFeatures().size()+"  features");
            System.out.println();
            nombrepcm++;
            for (Feature lefeature : lepcm.getConcreteFeatures()) {
                nombrefeature++;
                int nbrboolyes = 0, nbrboolno = 0;
                System.out.println("++++++++ le feature : " + lefeature.getName()+ "   ++++++++ à : " + lefeature.getCells().size() + "  cellules");

                if (lefeature.getCells().size() != 0) {
                        for (int j = 0; j < lefeature.getCells().size(); j++) {
                            nbrdecelltotal++;
                            if(lefeature.getCells().get(j).getInterpretation()!=null) {

                                String letype = lefeature.getCells().get(j).getInterpretation().toString().substring(36, lefeature.getCells().get(j).getInterpretation().toString().indexOf("@"));
                                if (letype.equals("BooleanValueImpl")) {
                                    nbrbooltotal++;
                                    if (lefeature.getCells().get(j).getContent().equals("Yes")||lefeature.getCells().get(j).getContent().equals("yes")
                                            ||lefeature.getCells().get(j).getContent().equals("YES")||lefeature.getCells().get(j).getContent().equals("True")
                                            ||lefeature.getCells().get(j).getContent().equals("true")||lefeature.getCells().get(j).getContent().equals("?")) {
                                        nbrboolyes++;
                                    } else if(lefeature.getCells().get(j).getContent().equals("No")||lefeature.getCells().get(j).getContent().equals("NO")
                                            ||lefeature.getCells().get(j).getContent().equals("no") ||lefeature.getCells().get(j).getContent().equals("False")
                                            ||lefeature.getCells().get(j).getContent().equals("false")){
                                        nbrboolno++;
                                    } else{
                                        autre++;
                                        typeautres.add(lefeature.getCells().get(j).getContent());
                                    }
                                }else if(letype.equals("StringValueImpl")){
                                    nbrStrg++;
                                } else if(letype.equals("RealValueImpl")){
                                    nbrReal++;
                                }else if(letype.equals("IntegerValueImpl")){
                                    nbrInteg++;
                                }else if(letype.equals("NotAvailableImpl")){
                                    if(lefeature.getCells().get(j).getContent().equals("")||lefeature.getCells().get(j).getContent().equals("?")
                                            ||lefeature.getCells().get(j).getContent().equals("N/A") ||lefeature.getCells().get(j).getContent().equals("n/a")
                                            ||lefeature.getCells().get(j).getContent().equals("Unknown")||lefeature.getCells().get(j).getContent().equals("unknown")
                                            ||lefeature.getCells().get(j).getContent().equals("-")||lefeature.getCells().get(j).getContent().equals("--")
                                            ||lefeature.getCells().get(j).getContent().equals("?")||lefeature.getCells().get(j).getContent().equals("??")){
                                        nbrNotAvai++;
                                    } else{
                                        typeNotAvai.add(lefeature.getCells().get(j).getContent());
                                    }

                                }else if(letype.equals("MultipleImpl")){
                                    nbrMulti++;
                                }else if(letype.equals("ConditionalImpl")) {
                                    nbrCond++;
                                } else {
                                        autretype++;
                                }
                            }
                        }
                        cumulno = cumulno + nbrboolno;
                        cumulyes = cumulyes +nbrboolyes;
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("+++++++++++le nombre de PCM dans le fichier est +++++++++++ : " + nombrepcm);
        System.out.println("+++++++++++le nombre de features dans le fichier est +++++++++++ :  " + nombrefeature);
        System.out.println();
        System.out.println("************** le nombre de cellules dans le fichier :  " +nbrdecelltotal);
        System.out.println();
        System.out.println("************** nombre de cellules à type String dans tous le fichier est :  " +nbrStrg);
        System.out.println("************** nombre de cellules à type Real dans tous le fichier est :  " +nbrReal);
        System.out.println("************** nombre de cellules à type Integer dans tous le fichier est :  " +nbrInteg);
        System.out.println("************** nombre de cellules à type Multiple dans tous le fichier est :  " +nbrMulti);
        System.out.println("************** nombre de cellules à type Conditional dans tous le fichier est :  " +nbrCond);
        System.out.println("************** nombre de cellules à type Autres dans tous le fichier est :  " +autretype);
        System.out.println();
        System.out.println();
        System.out.println("************** nombre de cellules à type NotAvailable dans tous le fichier est :  " +nbrNotAvai);
        System.out.println();
        System.out.println("************** nombre de cellules à type boolean dans tous le fichier est :  " +nbrbooltotal);
        System.out.println();
        System.out.println("------------- le nombre de cellules booléen à contenu YES, Yes, yes, TRUE, True, true est : " + cumulyes);
        System.out.println("------------- le nombre de cellules booléen à contenu NO, No, no, False, false " + cumulno);
        System.out.println("------------- le nombre de cellules booléen à contenu différents : " + autre);

    }
}
