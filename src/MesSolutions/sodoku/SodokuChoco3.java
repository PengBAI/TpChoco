package MesSolutions.sodoku;

import solver.Solver;
import solver.constraints.ICF;
import solver.variables.IntVar;
import solver.variables.VF;


public class SodokuChoco3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	    int[][] FondDeGrille;  
	    
		LecteurFichierSodoku monlecteurdefichier = new LecteurFichierSodoku("src/MesSolutions/sodoku/Sodoku2.txt");
	    monlecteurdefichier.convert();
	    FondDeGrille = monlecteurdefichier.GetGrille();
	    
	    // Déclaration du solveur
	    Solver SodokuSolv = new Solver("Sodoku");
	    
	    // Déclaration des variables
	    IntVar[][] Cases = new IntVar[9][9];
	    for (int i=0;i<9;i++){
	    	for (int j=0;j<9;j++){
	    		if (FondDeGrille[i][j]==0)
					Cases[i][j] = VF.enumerated("Case_" + i + "_" + j, 1, 9, SodokuSolv);
				else Cases[i][j] = VF.fixed("Case_" + i + "_" + j, FondDeGrille[i][j], SodokuSolv);
	    	}
	    }

	    // Déclaration des contraintes
        for (int i = 0; i < 9; i++) {
        	// pour chaque lignes
        	SodokuSolv.post(ICF.alldifferent(Cases[i], "AC"));
            // pareille pour collonnes
        	SodokuSolv.post(ICF.alldifferent(new IntVar[] {Cases[0][i], Cases[1][i], Cases[2][i],
        			Cases[3][i], Cases[4][i], Cases[5][i], Cases[6][i], Cases[7][i], Cases[8][i]}, "AC"));     	
        }
 	    
        // pour les secteurs
        for(int i = 0; i < 3; i++){
        	for(int j = 0; j < 3; j++){
        		SodokuSolv.post(ICF.alldifferent(new IntVar[] {Cases[i*3][j*3], Cases[i*3][j*3+1], 
        				Cases[i*3][j*3+2], Cases[i*3+1][j*3], Cases[i*3+1][j*3+1], Cases[i*3+1][j*3+2], 
        				Cases[i*3+2][j*3], Cases[i*3+2][j*3+1], Cases[i*3+2][j*3+2]}, "AC"));
        	}
        }
	    
	    // Résolution et affichage des résultats
		if(SodokuSolv.findSolution()) {
			do{
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
			}while(SodokuSolv.nextSolution());
				System.out.println(SodokuSolv.getMeasures().getSolutionCount()+" solution(s) trouvée(s) en "
						+ SodokuSolv.getMeasures().getTimeCount() + "secondes");
			}



	}

}
