package MesSolutions;

import solver.Solver;
import solver.constraints.ICF;
import solver.constraints.LCF;
import solver.variables.IntVar;
import solver.variables.VF;

/** Cinq maisons consécutives, de couleurs différentes, sont habitées 
 * par des hommes de diﬀérentes nationalités. Chacun possède un animal 
 * différent, a une boisson préférée différente et fume des cigarettes 
 * différentes. De plus, on sait que :
 * 1. Le norvégien habite la première maison,
 * 2. La maison à coté de celle du norvégien est bleue,
 * 3. L’habitant de la troisième maison boit du lait,
 * 4. L’anglais habite la maison rouge,
 * 5. L’habitant de la maison verte boit du café,
 * 6. L’habitant de la maison jaune fume des kools,
 * 7. La maison blanche se trouve juste après la verte,
 * 8. L’espagnol a un chien,
 * 9. L’ukrainien boit du thé,
 * 10. Le japonais fume des cravens,
 * 11. Le fumeur de old golds a un escargot,
 * 12. Le fumeur de gitanes boit du vin,
 * 13. Le voisin du fumeur de Chesterﬁelds a un renard,
 * 14. Le voisin du fumeur de kools a un cheval.
 * Qui boit de l’eau ? A qui appartient le zèbre ? 
 * */
public class Zebre {

	public static void main(String[] args) {
		// Déclaration du solver
		Solver ZebreSol = new Solver("Zebre");

		// Déclaration des variables
		IntVar maison1 = VF.enumerated("Maison1", 1, 5, ZebreSol);
		IntVar maison2 = VF.enumerated("Maison2", 1, 5, ZebreSol);
		IntVar maison3 = VF.enumerated("Maison3", 1, 5, ZebreSol);
		IntVar maison4 = VF.enumerated("Maison4", 1, 5, ZebreSol);
		IntVar maison5 = VF.enumerated("Maison5", 1, 5, ZebreSol);
		
		IntVar bleu = VF.enumerated("Bleu", 1, 5, ZebreSol);
		IntVar rouge = VF.enumerated("Rouge", 1, 5, ZebreSol);
		IntVar verte = VF.enumerated("Verte", 1, 5, ZebreSol);
		IntVar blanche = VF.enumerated("Blanche", 1, 5, ZebreSol);
		IntVar jaune = VF.enumerated("Jaune", 1, 5, ZebreSol);
		
		IntVar norvegien = VF.enumerated("Norvégien", 1, 5, ZebreSol);
		IntVar espagnol = VF.enumerated("Espagnol", 1, 5, ZebreSol);
		IntVar ukrainien = VF.enumerated("Ukrainien", 1, 5, ZebreSol);
		IntVar japonais = VF.enumerated("Japonais", 1, 5, ZebreSol);
		IntVar anglais = VF.enumerated("Anglais", 1, 5, ZebreSol);
		
		IntVar cravens = VF.enumerated("Cravens", 1, 5, ZebreSol);
		IntVar oldGolds = VF.enumerated("OldGolds", 1, 5, ZebreSol);
		IntVar gitanes = VF.enumerated("Gitanes", 1, 5, ZebreSol);
		IntVar chesterﬁelds = VF.enumerated("Chesterﬁelds", 1, 5, ZebreSol);
		IntVar kools = VF.enumerated("Kools", 1, 5, ZebreSol);
		
		IntVar lait = VF.enumerated("Lait", 1, 5, ZebreSol);
		IntVar cafe = VF.enumerated("Café", 1, 5, ZebreSol);
		IntVar the = VF.enumerated("Thé", 1, 5, ZebreSol);
		IntVar vin = VF.enumerated("Vin", 1, 5, ZebreSol);
		IntVar eau = VF.enumerated("Eau", 1, 5, ZebreSol);
		
		IntVar chien = VF.enumerated("Chien", 1, 5, ZebreSol);
		IntVar escargot = VF.enumerated("Escargot", 1, 5, ZebreSol);
		IntVar renard = VF.enumerated("Renard", 1, 5, ZebreSol);
		IntVar zebre = VF.enumerated("Zèbre", 1, 5, ZebreSol);
		IntVar cheval = VF.enumerated("Cheval", 1, 5, ZebreSol);
		
		// Déclaration des contraintes
		ZebreSol.post(ICF.alldifferent(new IntVar[] {maison1, maison2, maison3, maison4, maison5}));
		ZebreSol.post(ICF.alldifferent(new IntVar[] {bleu, verte, blanche, jaune, rouge}));
		ZebreSol.post(ICF.alldifferent(new IntVar[] {norvegien, japonais, espagnol, ukrainien, anglais}));
		ZebreSol.post(ICF.alldifferent(new IntVar[] {cravens, oldGolds, gitanes, chesterﬁelds, kools}));
		ZebreSol.post(ICF.alldifferent(new IntVar[] {lait, cafe, the, vin, eau}));
		ZebreSol.post(ICF.alldifferent(new IntVar[] {chien, escargot, renard, zebre, cheval}));
		
		ZebreSol.post(ICF.arithm(norvegien, "=", 1));
		ZebreSol.post(ICF.arithm(bleu, "=", 2));
		ZebreSol.post(ICF.arithm(lait, "=", 3));
		ZebreSol.post(ICF.arithm(anglais, "=", rouge));
		ZebreSol.post(ICF.arithm(verte, "=", cafe));
		ZebreSol.post(ICF.arithm(jaune, "=", kools));
		ZebreSol.post(ICF.arithm(blanche, "-", verte, "=", 1));
		ZebreSol.post(ICF.arithm(espagnol, "=", chien));
		ZebreSol.post(ICF.arithm(ukrainien, "=", the));
		ZebreSol.post(ICF.arithm(japonais, "=", cravens));
		ZebreSol.post(ICF.arithm(oldGolds, "=", escargot));
		ZebreSol.post(ICF.arithm(gitanes, "=", vin));
		ZebreSol.post(LCF.or(ICF.arithm(chesterﬁelds, "-", renard, "=", 1),
				ICF.arithm(renard, "-", chesterﬁelds, "=", 1)));
		ZebreSol.post(LCF.or(ICF.arithm(kools, "-", cheval, "=", 1),
				ICF.arithm(cheval, "-", kools, "=", 1)));
		
		// Résolution et affichage des résultats

		if (ZebreSol.findSolution()) {
			do {
				System.out.print(zebre.getName() + " = " + zebre.getValue());
				System.out.println("   " + eau.getName() + " = " + eau.getValue());
			} while (ZebreSol.nextSolution());
			System.out.println(ZebreSol.getMeasures().getSolutionCount()
					+ " solution(s) trouvée(s) en "
					+ ZebreSol.getMeasures().getTimeCount() + "secondes");

		}else{
			System.out.println("Aucune solution trouvée !");
		}

	}

}
