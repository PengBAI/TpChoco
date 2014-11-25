package MesSolutions;

import solver.Solver;
import solver.constraints.ICF;
import solver.variables.IntVar;
import solver.variables.VF;


/** Problème: 2. Cryptarithme
 * e.g. : forty + ten + ten = sixty 
 * Affecter une valeur entre 0 et 9 à chaque lettre de telle 
 * sorte que l’addition soit vériﬁée et qu’une même
 * valeur ne soit pas affectée à deux lettres différentes.
 * */
public class Cryptarithme {

	public static void main(String[] args) {

		// Déclaration du solver
		Solver solveur = new Solver("Cryptarithme");

		// Déclaration des variables
		IntVar f = VF.enumerated("f", 0, 9, solveur);
		IntVar o = VF.enumerated("o", 0, 9, solveur);
		IntVar r = VF.enumerated("r", 0, 9, solveur);
		IntVar t = VF.enumerated("t", 0, 9, solveur);
		IntVar y = VF.enumerated("y", 0, 9, solveur);
		IntVar e = VF.enumerated("e", 0, 9, solveur);
		IntVar n = VF.enumerated("n", 0, 9, solveur);
		IntVar s = VF.enumerated("s", 0, 9, solveur);
		IntVar i = VF.enumerated("i", 0, 9, solveur);
		IntVar x = VF.enumerated("x", 0, 9, solveur);

		IntVar forty = VF.bounded("forty", 10000, 99999, solveur);
		IntVar ten = VF.bounded("ten", 100, 999, solveur);
		IntVar sixty = VF.bounded("forty", 10000, 99999, solveur);

		// Déclaration des contraintes
		solveur.post(ICF.scalar(new IntVar[] { f, o, r, t, y }, new int[] {
				10000, 1000, 100, 10, 1 }, forty));
		
		solveur.post(ICF.scalar(new IntVar[] { t, e, n }, new int[] { 100, 10,
				1 }, ten));
		
		solveur.post(ICF.scalar(new IntVar[] { s, i, x, t, y }, new int[] {
				10000, 1000, 100, 10, 1 }, sixty));

		solveur.post(ICF.scalar(new IntVar[] { forty, ten, ten }, new int[] {
				1, 1, 1 }, sixty));

		solveur.post(ICF.alldifferent(new IntVar[] { f, o, r, t, y, e, n, s, i,
				x }));
		// Stratégie

		// Résolution et affichage des résultats
		if (solveur.findSolution()) {
			do {
				System.out.println(forty.getName() + " = " + forty.getValue());
				System.out.println(ten.getName() + " = " + ten.getValue());
				System.out.println(ten.getName() + " = " + ten.getValue());
				System.out.println(sixty.getName() + " = " + sixty.getValue());
			} while (solveur.nextSolution());
			System.out.println(solveur.getMeasures().getSolutionCount()
					+ " solution(s) trouvée(s) en "
					+ solveur.getMeasures().getTimeCount() + "secondes");

		}else{
			System.out.println("Aucune solution trouvée !");
		}
	}

}
