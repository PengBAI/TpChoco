package MesSolutions;

import solver.Solver;
import solver.constraints.ICF;
import solver.constraints.LCF;
import solver.variables.BoolVar;
import solver.variables.IntVar;
import solver.variables.VF;

public class Zebre {

	public static void main(String[] args) {
		// Déclaration du solver
		Solver UnTp1Solv = new Solver("Zebre");

		// Déclaration des variables
		

		// Déclaration des contraintes




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
