package solutions.sudoku;

import solver.Solver;
import solver.constraints.ICF;
import solver.variables.IntVar;
import solver.variables.VF;


public class SodokuChoco3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	    int[][] FondDeGrille;  

		LecteurFichierSodoku monlecteurdefichier = new LecteurFichierSodoku("Sodoku4.txt");
	    monlecteurdefichier.convert();
	    FondDeGrille = monlecteurdefichier.GetGrille();
	    
	    // Déclaration du solveur
	    Solver SodokuSolv = new Solver("Sodoku");
	    
	    // Déclaration des variables
	    IntVar[][] Cases = new IntVar[9][9];
	    for (int i=0;i<9;i++){
	    	for (int j=0;j<9;j++){
	    		if (FondDeGrille[i][j]==0)
					Cases[i][j] = VF.enumerated("Case_" + i + "_" + j, 1, 9,SodokuSolv);
				else Cases[i][j] = VF.fixed("Case_" + i + "_" + j, FondDeGrille[i][j],SodokuSolv);
	    	}
	    }

	    IntVar[][] TranspCases = new IntVar[9][9];
	    for (int i=0;i<9;i++){
	    	for (int j=0;j<9;j++){
	    		TranspCases[i][j] = Cases[j][i];
	    	}
	    }
	    
	    IntVar[] GroupeDeCases = new IntVar[9];
	    
	    // Déclaration des contraintes
	    for (int i=0;i<9;i++){
	    	SodokuSolv.post(ICF.alldifferent(Cases[i]));
	    	SodokuSolv.post(ICF.alldifferent(TranspCases[i]));
	    }

	    for (int l=0;l<9;l+=3){
	    	for (int c=0;c<9;c+=3){
	    		for (int i=0;i<3;i++){
	    			for (int j=0; j<3;j++){
	    				GroupeDeCases[i*3+j]=Cases[l+i][c+j];
	    			}
	    		}
	    		SodokuSolv.post(ICF.alldifferent(GroupeDeCases));
	    	}
	    }
	    
	    // Résolution et affichage des résultats
		if(SodokuSolv.findSolution())  {
				for (int i = 0; i < 9; i++) 
				{
					for (int j = 0; j < 9; j++) 
					{
						if (FondDeGrille[i][j]==0)
							System.out.print(". ");
						else System.out.print(FondDeGrille[i][j]+" ");
						if ((j==2)|(j==5))
							System.out.print("| ");
					}
					System.out.print("     |     ");
					for (int j = 0; j < 9; j++) 
					{
						System.out.print(Cases[i][j].getValue()+" ");
						if ((j==2)|(j==5))
							System.out.print("| ");
					}
					System.out.println();
					if ((i==2)|(i==5))
						System.out.println("------+-------+------"+"      |     "+"------+-------+------");
				}
			System.out.println(SodokuSolv.getMeasures().getSolutionCount()+" solution(s) trouvée(s) en "
					+ SodokuSolv.getMeasures().getTimeCount() + "secondes");		

			}



	}

}
