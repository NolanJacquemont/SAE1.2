/**
 * Type Etudiant
 *
 * @author Christopher
 */
public class Etudiant
{
    String nom;
    String prenom;
    char[] groupe; // Le premier caractère correspond au numéro du groupe TD, et le deuxième au numéro de groupe TP
    
    /** Constructeur du type Etudiant
     * 
     * @param pfNom IN : Nom de l'étudiant
     * @param pfPrenom IN : Prénom de l'étudiant
     * @param pfGroupe IN: Groupe de l'étudiant
     * 
     */
    Etudiant(String pfNom, String pfPrenom, String pfGroupe) {
        this.nom = pfNom;
        this.prenom = pfPrenom;
        this.groupe = pfGroupe.toCharArray();
    }
}
