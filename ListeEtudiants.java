import java.io.*;
import java.util.Scanner;
/**
 * Classe principale : chargement des étudiants à partir d'un CSV, tri des étudiants, test des fonctions de recherche
 * 
 * @author Enzo (jeu de données), Christopher (adaptation pour type Etudiant)
 */
public class ListeEtudiants {
    /** Donne le nombre d’étudiants de la liste pfListe
     *  @param pfListe IN : tableau contenant la liste d'étudiants nom, prenom
     *  @return le nombre d’étudiants de la liste
     **/
    public static int nbEtudiant(String pfListe[][]){
        return pfListe.length;
    }
    
    /** Charge dans un tableau la liste des étudiants
     *  @param pfFileName IN : le nom du fichier à lire
     *  @param pfDelimiter IN : le délimiteur de champs dans le fichier csv
     *  @return le tableau
     **/
    public static String[][] getListe(String pfFileName, String pfDelimiter) //change return value
        throws FileNotFoundException{
        
        // Ouvre un fichier et compte le nombre  de lignes du fichier.
        //   Ce nombre de lignes correspond au nombre d'étudiants
        BufferedReader read = new BufferedReader(new FileReader(pfFileName));
        int nbElt = 0;

        // le try catch est la pour recuperer des erreurs eventuelles de lecture
        // dans le fichier. Si une erreur se produit, ce sont les instructions
        // du catch qui seront executees (sera vu en semaine 46).
        try {
            while (read.readLine() != null) {
                nbElt++;
            }
            read.close(); 
        } catch (IOException e) {
            e.printStackTrace();
        } 

        // création d'un tableau à deux entrees (une pour le nom, une pour le
        // prenom) pour le nbElt etudiants
        String res [][] = new String [nbElt][4];
        // Etudiant res [] = new Etudiant [nbElt]; to change
        
        
        // lecture du fichier pour récupérer les noms et prénoms
        String line = "";
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(pfFileName));
            int cpt = 0; // numero de l'etudiant en lecture

            // loops through every line until null found
            while ((line = reader.readLine()) != null) {
                
                // separate every token by comma
                String[] token = line.split(pfDelimiter);    

                res [cpt][0] = token[0];
                res [cpt][1] = token[1];
                res [cpt][2] = token[2];
                res [cpt][3] = token[3];
                
                cpt ++;
            }
            reader.close(); 
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
    
    /**
     * Crée un tableau contenant tous les étudiants à partir d'un tableau à double entrée
     * 
     * @param pfListeEtudiants IN : tableau à double entrée des étudiants
     */
    public static TNP creerTNPEtud(String[][] pfListeEtudiants) {
        TNP TNPEtudiants = new TNP(pfListeEtudiants.length);
        for(int i = 0; i < pfListeEtudiants.length; i++) {
            Etudiant etudiant = new Etudiant(pfListeEtudiants[i][0], pfListeEtudiants[i][1], pfListeEtudiants[i][3]);
            TNPEtudiants.lesElements[i] = etudiant;
            TNPEtudiants.nbElt++;
        }
        return TNPEtudiants;
    }
    
    public static void main(String[] args) {
        try {
            String[][] promo = getListe("listenomssansaccent.csv", ",");
            TNP listeEtudiants = creerTNPEtud(promo);
            LibTri2.triSimple(listeEtudiants);
            LibTri2.afficherTNP(listeEtudiants);
            System.out.println("Il y a : " + listeEtudiants.nbElt + " personnes.");
            
            System.out.println("\n======= Algorithme de recherche sans rupture =======\n");
            testAlgoSansRupture(listeEtudiants);
            
            System.out.println("\n======= Algorithme de recherche avec rupture =======\n");
            testAlgoAvecRupture(listeEtudiants);
            
            System.out.println("\n======= Algorithme de recherche dichotomique =======\n");
            testAlgoDichotomie(listeEtudiants);
        } catch (Exception e) {  
            System.out.println("Erreur : " + e.getMessage());
        }
    }
    
    /**
     * Test de l'algorithme de recherche sans rupture
     * 
     * @param pfTNP IN : liste des étudiants
     */
    public static void testAlgoSansRupture(TNP pfTNP) {
        Etudiant etuTest1 = new Etudiant("Ferrance", "Sandy", "3A");
        Etudiant etuTest2 = new Etudiant("Afritt", "Barack", "1A");
        Etudiant etuTest3 = new Etudiant("zzzzzz", "Barack", "1A");
        Etudiant etuTest4 = new Etudiant("Afritt", "aaaaaa", "1A");
        Etudiant etuTest5 = new Etudiant("Afritt", "Barack", "2A");
        Etudiant etuTest6 = new Etudiant("Za", "Pit", "5B");
        
        Resultat result1 = LibRecherche.testSansRupture(pfTNP, etuTest1);
        Resultat result2 = LibRecherche.testSansRupture(pfTNP, etuTest2);
        Resultat result3 = LibRecherche.testSansRupture(pfTNP, etuTest3);
        Resultat result4 = LibRecherche.testSansRupture(pfTNP, etuTest4);
        Resultat result5 = LibRecherche.testSansRupture(pfTNP, etuTest5);
        Resultat result6 = LibRecherche.testSansRupture(pfTNP, etuTest6);
        
        System.out.println(etuTest1.nom + " " + etuTest1.prenom + " " + String.valueOf(etuTest1.groupe) 
                           + " est présent ? " + result1.present + " (" + result1.nb_comp + " comparaisons)");
         
        System.out.println(etuTest2.nom + " " + etuTest2.prenom + " " + String.valueOf(etuTest2.groupe) 
                           + " est présent ? " + result2.present + " (" + result2.nb_comp + " comparaisons)");
        
        System.out.println(etuTest3.nom + " " + etuTest3.prenom + " " + String.valueOf(etuTest3.groupe) 
                           + " est présent ? " + result3.present + " (" + result3.nb_comp + " comparaisons)");
        
        System.out.println(etuTest4.nom + " " + etuTest4.prenom + " " + String.valueOf(etuTest4.groupe) 
                           + " est présent ? " + result4.present + " (" + result4.nb_comp + " comparaisons)");
        
        System.out.println(etuTest5.nom + " " + etuTest5.prenom + " " + String.valueOf(etuTest5.groupe) 
                           + " est présent ? " + result5.present + " (" + result5.nb_comp + " comparaisons)");
        
        System.out.println(etuTest6.nom + " " + etuTest6.prenom + " " + String.valueOf(etuTest6.groupe) 
                           + " est présent ? " + result6.present + " (" + result6.nb_comp + " comparaisons)");
        
        int nb_exec_total = 0;
        for(int i = 0; i < pfTNP.nbElt; i++) {
            Resultat result = LibRecherche.testSansRupture(pfTNP, pfTNP.lesElements[i]);
            nb_exec_total += result.nb_comp;
        }
        System.out.println("Pour trouver un étudiant, l'algorithme de recherche dichotomique a besoin de " 
                          + nb_exec_total + " comparaisons, soit une moyenne de " + (nb_exec_total/pfTNP.nbElt) + " par étudiant");
    }
    
    /**
     * Test de l'algorithme de recherche avec rupture
     * 
     * @param pfTNP IN : liste des étudiants
     */
    public static void testAlgoAvecRupture(TNP pfTNP) {
        Etudiant etuTest1 = new Etudiant("Ferrance", "Sandy", "3A");
        Etudiant etuTest2 = new Etudiant("Afritt", "Barack", "1A");
        Etudiant etuTest3 = new Etudiant("zzzzzz", "Barack", "1A");
        Etudiant etuTest4 = new Etudiant("Afritt", "aaaaaa", "1A");
        Etudiant etuTest5 = new Etudiant("Afritt", "Barack", "2A");
        Etudiant etuTest6 = new Etudiant("Za", "Pit", "5B");
        
        Resultat result1 = LibRecherche.testAvecRupture(pfTNP, etuTest1);
        Resultat result2 = LibRecherche.testAvecRupture(pfTNP, etuTest2);
        Resultat result3 = LibRecherche.testAvecRupture(pfTNP, etuTest3);
        Resultat result4 = LibRecherche.testAvecRupture(pfTNP, etuTest4);
        Resultat result5 = LibRecherche.testAvecRupture(pfTNP, etuTest5);
        Resultat result6 = LibRecherche.testAvecRupture(pfTNP, etuTest6);
        
        System.out.println(etuTest1.nom + " " + etuTest1.prenom + " " + String.valueOf(etuTest1.groupe) 
                           + " est présent ? " + result1.present + " (" + result1.nb_comp + " comparaisons)");
         
        System.out.println(etuTest2.nom + " " + etuTest2.prenom + " " + String.valueOf(etuTest2.groupe) 
                           + " est présent ? " + result2.present + " (" + result2.nb_comp + " comparaisons)");
        
        System.out.println(etuTest3.nom + " " + etuTest3.prenom + " " + String.valueOf(etuTest3.groupe) 
                           + " est présent ? " + result3.present + " (" + result3.nb_comp + " comparaisons)");
        
        System.out.println(etuTest4.nom + " " + etuTest4.prenom + " " + String.valueOf(etuTest4.groupe) 
                           + " est présent ? " + result4.present + " (" + result4.nb_comp + " comparaisons)");
        
        System.out.println(etuTest5.nom + " " + etuTest5.prenom + " " + String.valueOf(etuTest5.groupe) 
                           + " est présent ? " + result5.present + " (" + result5.nb_comp + " comparaisons)");
        
        System.out.println(etuTest6.nom + " " + etuTest6.prenom + " " + String.valueOf(etuTest6.groupe) 
                           + " est présent ? " + result6.present + " (" + result6.nb_comp + " comparaisons)");
        
        int nb_exec_total = 0;
        for(int i = 0; i < pfTNP.nbElt; i++) {
            Resultat result = LibRecherche.testAvecRupture(pfTNP, pfTNP.lesElements[i]);
            nb_exec_total += result.nb_comp;
        }
        System.out.println("Pour trouver un étudiant, l'algorithme de recherche dichotomique a besoin de " 
                          + nb_exec_total + " comparaisons, soit une moyenne de " + (nb_exec_total/pfTNP.nbElt) + " par étudiant");
    }
    
    /**
     * Test de l'algorithme de recherche par dichotomie
     * 
     * @param pfTNP IN : liste des étudiants
     */
    public static void testAlgoDichotomie(TNP pfTNP) {
        Etudiant etuTest1 = new Etudiant("Ferrance", "Sandy", "3A");
        Etudiant etuTest2 = new Etudiant("Afritt", "Barack", "1A");
        Etudiant etuTest3 = new Etudiant("zzzzzz", "Barack", "1A");
        Etudiant etuTest4 = new Etudiant("Afritt", "aaaaaa", "1A");
        Etudiant etuTest5 = new Etudiant("Afritt", "Barack", "2A");
        Etudiant etuTest6 = new Etudiant("Za", "Pit", "5B");
        
        Resultat result1 = LibRecherche.testDichotomique(pfTNP, etuTest1);
        Resultat result2 = LibRecherche.testDichotomique(pfTNP, etuTest2);
        Resultat result3 = LibRecherche.testDichotomique(pfTNP, etuTest3);
        Resultat result4 = LibRecherche.testDichotomique(pfTNP, etuTest4);
        Resultat result5 = LibRecherche.testDichotomique(pfTNP, etuTest5);
        Resultat result6 = LibRecherche.testDichotomique(pfTNP, etuTest6);
        
        System.out.println(etuTest1.nom + " " + etuTest1.prenom + " " + String.valueOf(etuTest1.groupe) 
                           + " est présent ? " + result1.present + " (" + result1.nb_comp + " comparaisons)");
         
        System.out.println(etuTest2.nom + " " + etuTest2.prenom + " " + String.valueOf(etuTest2.groupe) 
                           + " est présent ? " + result2.present + " (" + result2.nb_comp + " comparaisons)");
        
        System.out.println(etuTest3.nom + " " + etuTest3.prenom + " " + String.valueOf(etuTest3.groupe) 
                           + " est présent ? " + result3.present + " (" + result3.nb_comp + " comparaisons)");
        
        System.out.println(etuTest4.nom + " " + etuTest4.prenom + " " + String.valueOf(etuTest4.groupe) 
                           + " est présent ? " + result4.present + " (" + result4.nb_comp + " comparaisons)");
        
        System.out.println(etuTest5.nom + " " + etuTest5.prenom + " " + String.valueOf(etuTest5.groupe) 
                           + " est présent ? " + result5.present + " (" + result5.nb_comp + " comparaisons)");
        
        System.out.println(etuTest6.nom + " " + etuTest6.prenom + " " + String.valueOf(etuTest6.groupe) 
                           + " est présent ? " + result6.present + " (" + result6.nb_comp + " comparaisons)");
                          
        int nb_exec_total = 0;
        for(int i = 0; i < pfTNP.nbElt; i++) {
            Resultat result = LibRecherche.testDichotomique(pfTNP, pfTNP.lesElements[i]);
            nb_exec_total += result.nb_comp;
        }
        System.out.println("Pour trouver un étudiant, l'algorithme de recherche dichotomique a besoin de " 
                          + nb_exec_total + " comparaisons, soit une moyenne de " + (nb_exec_total/pfTNP.nbElt) + " par étudiant");
    }
}

