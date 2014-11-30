package solutions.CBar;
import solver.ResolutionPolicy;
import solver.Solver;
import solver.constraints.ICF;
import solver.constraints.LCF;
import solver.search.strategy.ISF;
import solver.search.strategy.strategy.AbstractStrategy;
import solver.variables.IntVar;
import solver.variables.VF;
import solver.variables.BoolVar;

public class UnCbarChoco3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int n=3;
		int Cmax=0, UBCbar=0;
		int[] p ;
		
	    LecteurFichier monlecteurdefichier = new LecteurFichier("src/solutions/CBar/dataUnCbar.txt");
	    p = monlecteurdefichier.convert();
	    n = monlecteurdefichier.GetNbJobs();
	    
		
		
		for (int i=0;i<n;i++)
		{
			System.out.println("p["+i+"]="+p[i]);
		}
		for (int i=0;i<n;i++)
		{
			Cmax=Cmax+p[i];
			UBCbar=UBCbar+(i+1)*p[i];
		}
		System.out.println("Cmax="+Cmax+" UBCbar="+UBCbar);
		
		// Déclaration du solver
		Solver UnCbarSolv = new Solver("Pbm Un Cbar");
		
		// Déclaration des variables
		IntVar[] C = VF.enumeratedArray("C", n, 1, Cmax,UnCbarSolv);
//		IntVar OBJ = VF.bounded("objective", 0, 999, solver);
		IntVar Cbar = VF.enumerated("Cbar",1,UBCbar,UnCbarSolv);
		
		// Déclaration des contraintes
		for (int i=0;i<n;i++)
			UnCbarSolv.post(ICF.arithm(C[i],">=",p[i]));
		
		for (int i=0;i<n;i++)
			for (int j=0;j<n;j++)
				if (i!=j){
					BoolVar[] cnts = {ICF.arithm(C[j],"-",C[i],">=",p[j]).reif(), 
							ICF.arithm(C[i],"-",C[j],">=",p[i]).reif()};
					UnCbarSolv.post(LCF.or(cnts) ) ;
				}

		
		UnCbarSolv.post(ICF.sum(C, "=", Cbar));
		
		// Stratégie
//		UnCbarSolv.set(ISF.impact(C,4));
		AbstractStrategy[] as = {ISF.lexico_LB(C)};
		UnCbarSolv.set(as); 
		
		// Résolution et affichage des résultats
		UnCbarSolv.findOptimalSolution(ResolutionPolicy.MINIMIZE, Cbar);
		
		System.out.println(Cbar.getName() + " = " + Cbar.getValue());
		System.out.println();
		
		for(int i=0;i<n;i++)
		{
			System.out.println(C[i].getName() + " = " + C[i].getValue());
		}	
		
		System.out.println(UnCbarSolv.getMeasures().getSolutionCount()+" solution(s) trouvée(s) en "
				+ UnCbarSolv.getMeasures().getTimeCount() + " secondes");	
	}

}
