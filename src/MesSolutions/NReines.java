package MesSolutions;

import solver.Solver;
import solver.constraints.ICF;
import solver.variables.IntVar;
import solver.variables.VF;



/** En vous appuyant sur les transparents du cours, programmer un CSP
 * résolvant ce problème dans le cas général (n quelconque).
 * Modéliser:
 * C_lig = {L_i != L_j | i et j ∈ {1, ..n}, et i != j}
 * C_dm = {L_i + i != L_j + j | i et j ∈ {1, ..n}, et i != j}
 * C_dd = {L_i − i != L_j − j | i et j ∈ {1, ..n}, et i != j}
 * */
public class NReines {

	public static void main(String[] args) {
		
		int N = 4;
		// Déclaration du solver
		Solver NReinesSol = new Solver("NReines");
		
		// Déclaration des variables
		// Li: Numéro de la ligne de la reine à la ième colonne
		IntVar[] Li = VF.enumeratedArray("L", N, 0, N - 1, NReinesSol);
		
		// Déclaration des contraintes
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				if(i != j){
					NReinesSol.post(ICF.alldifferent(new IntVar[] {Li[i], Li[j]}) );
					NReinesSol.post(ICF.alldifferent(new IntVar[] {VF.offset(Li[i], i), VF.offset(Li[j], j)}));
					NReinesSol.post(ICF.alldifferent(new IntVar[] {VF.offset(Li[i], -i), VF.offset(Li[j], -j)}));
				}
			}
		}
		
		// Stratégie
		
		// Résolution et affichage des résultats
		int cpt = 1;
		if (NReinesSol.findSolution()) {
			
			do {
				System.out.println("Solution " + cpt++ + " :");
				for(int i = 0; i < N; i++){
					System.out.println(Li[i]);
				}
			} while (NReinesSol.nextSolution());
			System.out.println(NReinesSol.getMeasures().getSolutionCount()
					+ " solution(s) trouvée(s) en "
					+ NReinesSol.getMeasures().getTimeCount() + "secondes");

		}else{
			System.out.println("Aucune solution trouvée !");
		}
	}

}
