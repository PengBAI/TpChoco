package solutions;

import solver.Solver;
import solver.constraints.ICF;
import solver.search.strategy.ISF;
import solver.variables.IntVar;
import solver.variables.VF;


public class CarreMagiqueChoco3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// D�claration des donn�es
		int n = 3; // Ordre du carr� magique  		
//		int SommeMagique = n * (n * n + 1) / 2; // Somme Magique
		
		// D�claration du solveur
		Solver CarreMagiqueSolv = new Solver("Carr� Magique");

		// D�claration des variables
		IntVar SommeMagique = VF.fixed(n * (n * n + 1) / 2, CarreMagiqueSolv);
		IntVar[][] Cases = VF.enumeratedMatrix("Case", n, n, 1, n * n,CarreMagiqueSolv);
		IntVar[][] TranspCases = new IntVar[n][n];
		IntVar[] CasesEnLigne = new IntVar[n*n];
		IntVar[] Diag1 = new IntVar[n];
		IntVar[] Diag2 = new IntVar[n];
		
		for (int i=0;i<n;i++){
			Diag1[i]=Cases[i][i];
			Diag2[i]=Cases[i][n-i-1];
			for (int j=0; j<n ; j++){
				TranspCases[j][i] = Cases[i][j];
				CasesEnLigne[i*n+j] = Cases[i][j];
			}
			
		}
		
		// D�claration des contraintes
		CarreMagiqueSolv.post(ICF.alldifferent(CasesEnLigne));
		CarreMagiqueSolv.post(ICF.sum(Diag1, "=", SommeMagique));
		CarreMagiqueSolv.post(ICF.sum(Diag2, "=", SommeMagique));
		for (int i=0;i<n;i++){
			CarreMagiqueSolv.post(ICF.sum(Cases[i], "=", SommeMagique));
			CarreMagiqueSolv.post(ICF.sum(TranspCases[i], "=", SommeMagique));
			}
		
		CarreMagiqueSolv.post(ICF.sort(Diag1, Diag1));
		CarreMagiqueSolv.post(ICF.arithm(Diag1[0],"<", Diag2[n-1]));

		
		CarreMagiqueSolv.set(ISF.impact(CasesEnLigne,n+1));//n+1
//		CarreMagiqueSolv.set(ISF.custom(ISF.minDomainSize_var_selector(), 
//				ISF.max_value_selector(), CasesEnLigne));
		
		
		System.out.println("D�but r�solution");
		
		// R�solution et affichage
		if(CarreMagiqueSolv.findSolution()) { 
			System.out.println("Fin r�solution");
			do{
			for (int i = 0; i < n; i++) 
			{
				for (int j = 0; j < n; j++) 
					System.out.print(Cases[i][j].getValue()+" ");
				System.out.println();
			}
			System.out.println();
			}while(CarreMagiqueSolv.nextSolution());
			
			System.out.println(CarreMagiqueSolv.getMeasures().getSolutionCount()+" solution(s) trouv�e(s) en "
				+ CarreMagiqueSolv.getMeasures().getTimeCount() + "secondes");		
		
			} else
				System.out.println("pas de solution");
		
	}

}
