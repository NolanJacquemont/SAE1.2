/**
 * Classe des algorithmes de recherche
 * 
 * @author Nolan
 */
public class LibRecherche
{
    /**
     * Compare si 2 étudiants sont les mêmes
     * 
     * @param pfEtu1 IN : premier étudiant à comparer
     * @param pfEtu2 IN : deuxième étudiant à comparer
     */
    public static boolean egal(Etudiant pfEtu1, Etudiant pfEtu2) {
        String groupe1 = String.valueOf(pfEtu1.groupe);
        String groupe2 = String.valueOf(pfEtu2.groupe);
        return pfEtu1.nom.equals(pfEtu2.nom) && pfEtu1.prenom.equals(pfEtu2.prenom) && groupe1.equals(groupe2);
    }
    
    /**
     * Compare si un étudiant est classé avant ou après un autre
     * 
     * @param pfEtu1 IN : premier étudiant à comparer
     * @param pfEtu2 IN : deuxième étudiant à comparer
     */
    public static boolean estPlusGrandQue(Etudiant pfEtu1, Etudiant pfEtu2) {
        String groupe1 = String.valueOf(pfEtu1.groupe);
        String groupe2 = String.valueOf(pfEtu2.groupe);
        if(groupe1.compareTo(groupe2) > 0) {
            // Le groupe du premier étudiant est après celui du deuxième
            return true;
        }else {
            if(groupe1.compareTo(groupe2) == 0) {
                // Les deux étudiants sont dans le même groupe
                if(pfEtu1.nom.compareTo(pfEtu2.nom) == 0) {
                    // Les deux étudiants ont le même nom
                    // On retourne true si le premier prénom est après le deuxième, false sinon
                    return pfEtu1.prenom.compareTo(pfEtu2.prenom) > 0; 
                }else {
                    // On retourne true si le premier nom est après le deuxième, false sinon
                    return pfEtu1.nom.compareTo(pfEtu2.nom) > 0;
                }
            }else {
                // Le premier étudiant est avant le deuxième
                return false;
            }
        }
    }
    
    /**
     * Algorithme de recherche sans rupture
     * 
     * @param pfTNP IN : liste des étudiants
     * @param pfEtudiant IN : étudiant à chercher
     */
    public static Resultat testSansRupture(TNP pfTNP, Etudiant pfEtudiant) {
        boolean present = false;
        int compteur = 0;
        for(int i = 0; i < pfTNP.nbElt; i++) {
            compteur++; // Comparaison du for
            compteur++; // test
            if(egal(pfTNP.lesElements[i],pfEtudiant)) {
                present = true;
            }
        }
        return new Resultat(present, compteur);
    }
    
    /**
     * Algorithme de recherche avec rupture
     * 
     * @param pfTNP IN : liste des étudiants
     * @param pfEtudiant IN : étudiant à chercher
     */
    public static Resultat testAvecRupture(TNP pfTNP, Etudiant pfEtudiant) {
        int compteur = 0;
        for(int i = 0; i < pfTNP.nbElt; i++) {
            compteur++; // Compairson du for
            compteur++; // test
            if(egal(pfTNP.lesElements[i],pfEtudiant)) {
                return new Resultat(true, compteur);
            }
        }
        return new Resultat(false, compteur);
    }
    
    /**
     * Algorithme de recherche avec rupture
     * 
     * @param pfTNP IN : liste des étudiants
     * @param pfEtudiant IN : étudiant à chercher
     */
    public static Resultat testDichotomique(TNP pfTNP, Etudiant pfEtudiant) {
        int debut = 0;
        int fin = pfTNP.nbElt - 1;
        int compteur = 0;
        while(debut <= fin) {
            compteur++; // Comparaison du while
            int centre = (debut + fin) / 2;
            Etudiant etu = pfTNP.lesElements[centre];
            compteur++; // 1er test
            if(egal(pfEtudiant, etu)) {
                return new Resultat(true, compteur);
            }else if(estPlusGrandQue(pfEtudiant, etu)) {
                compteur++; // 2e test
                debut = centre + 1;
            }else {
                compteur++; // 2e test
                fin = centre - 1;
            }
        }
        return new Resultat(false, compteur);
    }
}