package MesSolutions;

import solver.Solver;
import solver.constraints.ICF;
import solver.constraints.LCF;
import solver.variables.BoolVar;
import solver.variables.IntVar;
import solver.variables.VF;


/** 
 * Problème: 1. Contraintes numériques simples
 * Soit le domaine D déﬁni par:
 * 1) x^2 + y^2 ≤ 4
 * 2) y ≥ x + 1 ou y ≥ −x + 1
 * 3) y > 0
 * Trouver un point à coordonnées entières appartenant à D.
 * Trouver tous les points à coordonnées entières appartenant à D. 
 * */
public class ContraintsNS {

	public static void main(String[] args) {

		// Déclaration du solver
		Solver UnTp1Solv = new Solver("tp1_1");

		// Déclaration des variables
		IntVar x = VF.enumerated("x", -50, 50, UnTp1Solv);
		IntVar y = VF.enumerated("y", -50, 50, UnTp1Solv);
		IntVar x2 = VF.enumerated("x2", 0, 2500, UnTp1Solv);
		IntVar y2 = VF.enumerated("y2", 0, 2500, UnTp1Solv);

		// Déclaration des contraintes
		UnTp1Solv.post(ICF.square(x2, x));
		UnTp1Solv.post(ICF.square(y2, y));
		UnTp1Solv.post(ICF.arithm(x2, "+", y2, "<=", 4));

		UnTp1Solv.post(ICF.arithm(y, ">", 0));

		BoolVar[] cnts = { ICF.arithm(y, ">=", x, "+", 1).reif(),
				ICF.arithm(y, "+", x, ">=", 1).reif() };
		UnTp1Solv.post(LCF.or(cnts));



		// Résolution et affichage des résultats

		if (UnTp1Solv.findSolution()) {
			do {
				System.out.print(x.getName() + " = " + x.getValue());
				System.out.println("   " + y.getName() + " = " + y.getValue());
			} while (UnTp1Solv.nextSolution());
			System.out.println(UnTp1Solv.getMeasures().getSolutionCount()
					+ " solution(s) trouvée(s) en "
					+ UnTp1Solv.getMeasures().getTimeCount() + "secondes");

		}else{
			System.out.println("Aucune solution trouvée !");
		}
	}

}
