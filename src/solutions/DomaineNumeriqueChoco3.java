package solutions;

import solver.constraints.ICF;
import solver.constraints.LCF;
import solver.Solver;
import solver.variables.BoolVar;
import solver.variables.IntVar;
import solver.variables.VF;



public class DomaineNumeriqueChoco3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	Solver solveur = new Solver("Domaine Numérique");
	
	IntVar Y = VF.enumerated("Y", 0, 2,solveur);
	IntVar X = VF.enumerated("X", -2, 2,solveur);
	IntVar XP2 = VF.enumerated("XP2", 0, 4, solveur);
	IntVar YP2 = VF.enumerated("YP2", 0, 4, solveur);
			
	solveur.post(ICF.times(X, X, XP2));  // note  : ne pas utiliser square avec bounded Integer.
	solveur.post(ICF.times(Y, Y, YP2));
	solveur.post(ICF.arithm(XP2,"+",YP2,"<=",4));
    solveur.post(ICF.arithm(Y,">",0));
    
    BoolVar[] cnts = {ICF.arithm(Y,">=", X,"+", 1).reif(), 
			ICF.arithm(Y,"+", X,">=",1).reif()};
	solveur.post(LCF.or(cnts));
	
	if(solveur.findSolution()) {
	do {
		System.out.print(X.getName() + " = " + X.getValue());
		System.out.println("   "+Y.getName() + " = " + Y.getValue());
	}while (solveur.nextSolution());
	System.out.println(solveur.getMeasures().getSolutionCount()+" solution(s) trouvée(s) en "
			+ solveur.getMeasures().getTimeCount() + "secondes");		

	}

}
}