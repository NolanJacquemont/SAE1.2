/**
 * Classe des algorithmes de tri
 *
 * @author Enzo (tri), Christopher (adaptation pour type Etudiant)
 */
public class LibTri2 {
    
    /** affiche un TNP
     * @param pfTNP IN : la liste des éléments
     */
    public static void afficherTNP(TNP pfTNP)  {
        System.out.print("Liste des étudiants : \n") ;
        for (int i = 0; i < pfTNP.nbElt ; i = i + 1){
            System.out.print(pfTNP.lesElements[i].nom + " " + pfTNP.lesElements[i].prenom + " " 
            + pfTNP.lesElements[i].groupe[0] + pfTNP.lesElements[i].groupe[1]) ;
            if (i < (pfTNP.nbElt - 1)) { System.out.print("\n") ; }
        }
        System.out.print("\n") ;
    }
    
    /** tri dans l'ordre croissant
     * @param pfTNP IN/OUT : la liste des éléments
     */
    public static void triSimple(TNP pfTNP)    {
        for (int i = 0; i < pfTNP.nbElt -1 ; i = i + 1) {
            for (int j = i+1; j < pfTNP.nbElt ; j = j + 1) {
                if(LibRecherche.estPlusGrandQue(pfTNP.lesElements[i], pfTNP.lesElements[j])) {
                     Etudiant echange = pfTNP.lesElements[i] ;
                     pfTNP.lesElements[i] = pfTNP.lesElements[j] ;
                     pfTNP.lesElements[j] = echange;
                }
            }
        }
    }
}

