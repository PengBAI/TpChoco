package solutions;

import solver.Solver;
import solver.constraints.ICF;
import solver.variables.IntVar;
import solver.variables.VF;


public class ProblemeDesReinesChoco3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n=4;
		
		// Déclaration du solveur
		Solver ReineSolv = new Solver("Problème des n reines");
		
		// Déclaration des variables
		IntVar[] X= VF.enumeratedArray("Ligne", n, 0, n-1, ReineSolv);
		IntVar[] Y= new IntVar[n];
		IntVar[] Z= new IntVar[n];

		for(int i=0;i<n;i++)
		{
		   Y[i]= VF.offset(X[i],i);	
		   Z[i]= VF.offset(X[i],-i);	
		}
		
		// Déclaration des contraintes
		ReineSolv.post(ICF.alldifferent(X));
		ReineSolv.post(ICF.alldifferent(Y));
		ReineSolv.post(ICF.alldifferent(Z));
		
		if(ReineSolv.findSolution()) {
			do {
				for (int i = 0; i < n; i++) 
				{
					System.out.println("Reine en colonne "+(i+1)+" Ligne = "+ (X[i].getValue()+1));
				}
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) 
						if (X[j].getValue()==i) 
						{
							System.out.print(" *");
						} else {
							System.out.print(" .");
						}
					System.out.println(" ");
				}
		
				System.out.println();
			}while (ReineSolv.nextSolution());
			System.out.println(ReineSolv.getMeasures().getSolutionCount()+" solution(s) trouvée(s) en "
					+ ReineSolv.getMeasures().getTimeCount() + "secondes");		

			} else {
			    System.out.println("Pas de solution");	
			}
		
	}

}
