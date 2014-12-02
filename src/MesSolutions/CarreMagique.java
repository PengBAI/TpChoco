package MesSolutions;

import solver.Solver;
import solver.constraints.ICF;
import solver.variables.IntVar;
import solver.variables.VF;

/**
 * Un carré magique d’ordre n est un carré n × n dans lequel on a réparti les nombres de 1 à n 2
 * de telle sorte que la somme sur chaque ligne soit égale à celle sur chaque colonne et à celle 
 * sur chacune des deux diagonales. Cette somme s’appelle la somme magique, pour un carré d’ordre
 * n elle vaut : SM = n(n^2+1)/2.
 * Par exemple :
 * 2 7 6
 * 9 5 1
 * 4 3 8         n=3
 * ou
 *  4 16  1 13
 * 10  7 14  3
 * 15  2 11  6
 *  5  9  8 12   n=4
 * Programmer un CSP permettant de construire un carré magique d’ordre n donné. Jusqu’à quelles valeurs
 * de n ce CSP est il utilisable ? Etudier des améliorations possibles.
 * */
public class CarreMagique {

	public static void main(String[] args) {
		int N = 4;
		
		// Déclaration du solver
		Solver carreMagiqueSol = new Solver("Carré Magique");
		
		// Déclaration des variables
		IntVar somme = VF.fixed(N * (N * N + 1)/2, carreMagiqueSol);
		IntVar[][] cases = VF.enumeratedMatrix("Case", N, N, 1, N * N, carreMagiqueSol);
		IntVar[] caseEnLigne = new IntVar[N * N];
		IntVar[][] transCases = new IntVar[N][N];
		IntVar[] dia1 = new IntVar[N];
		IntVar[] dia2 = new IntVar[N];
		
		for(int i = 0; i < N; i++){
			dia1[i] = cases[i][i];
			dia2[i] = cases[i][N-i-1];
			for(int j = 0; j < N; j++){
				transCases[i][j] = cases[j][i];
				caseEnLigne[i * N + j] = cases[i][j];
			}
		}
		// Déclaration des contraintes
		carreMagiqueSol.post(ICF.alldifferent(caseEnLigne));
		carreMagiqueSol.post(ICF.sum(dia1, "=", somme));
		carreMagiqueSol.post(ICF.sum(dia2, "=", somme));
		for (int i = 0; i < N; i++){
			carreMagiqueSol.post(ICF.sum(cases[i], "=", somme));
			carreMagiqueSol.post(ICF.sum(transCases[i], "=", somme));
			}
		
		// Résolution et affichage
		if(carreMagiqueSol.findSolution()) { 
			System.out.println("Fin résolution");
			do{
			for (int i = 0; i < N; i++) 
			{
				for (int j = 0; j < N; j++) 
					System.out.print(cases[i][j].getValue()+" ");
				System.out.println();
			}
			System.out.println();
			}while(carreMagiqueSol.nextSolution());
			
			System.out.println(carreMagiqueSol.getMeasures().getSolutionCount()+" solution(s) trouvée(s) en "
				+ carreMagiqueSol.getMeasures().getTimeCount() + "secondes");		
		
			} else
				System.out.println("pas de solution");
		
	}
}
