package solutions;

import solver.Solver;
import solver.constraints.ICF;
import solver.variables.BoolVar;
import solver.variables.IntVar;
import solver.variables.VF;


public class LePeripleDuChevalierChoco3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Première énigme");
		System.out.println("---------------");
		Enigme1();
		System.out.println("");
		System.out.println("Deuxième énigme");
		System.out.println("---------------");
	    Enigme2();
		System.out.println("");
		System.out.println("Troisième énigme");
		System.out.println("----------------");
	    Enigme3();
	}
	
		public static void Enigme1() {
			
		    //Déclaration du solveur
			Solver Enigme1Solv = new Solver("Enigme1");
			
		    //Déclaration des variables
	    	BoolVar Panneau1 = VF.bool("Panneau1",Enigme1Solv);
//	    	BoolVar Panneau2 = VF.bool("Panneau2",Enigme1Solv);
	    	BoolVar Panneau2 = VF.not(Panneau1);
	    	BoolVar SortieAuNord = VF.bool("Sortie au Nord",Enigme1Solv);
	    	BoolVar SortieAuSud = VF.bool("Sortie au Sud",Enigme1Solv);
	    	BoolVar MortAuNord = VF.not(SortieAuNord);
	    	BoolVar MortAuSud = VF.not(SortieAuSud);
	    	BoolVar P2S = VF.bool("SaN ou SaS",Enigme1Solv);
	    	BoolVar P2M = VF.bool("MaN ou MaS",Enigme1Solv);
			
	    	// Déclaration des contraintes
	    	Enigme1Solv.post(ICF.minimum(Panneau1, SortieAuNord, MortAuSud));
	    	Enigme1Solv.post(ICF.minimum(Panneau2, P2S, P2M));
	    	Enigme1Solv.post(ICF.maximum(P2S, SortieAuNord, SortieAuSud));
	    	Enigme1Solv.post(ICF.maximum(P2M, MortAuNord, MortAuSud));

	    	// Résolution et affichage
	    	if(Enigme1Solv.findSolution()) {
	    		do {
	    			System.out.print(Panneau1.getName() + " = " + Panneau1.getValue() + " ; ");
	    			System.out.print("Panneau2" + " = " + Panneau2.getValue() + " ; ");
	    			System.out.print(SortieAuNord.getName() + " = " + SortieAuNord.getValue() + " ; ");
	    			System.out.print("Mort Au Nord" + " = " + MortAuNord.getValue() + " ; ");
	    			System.out.print(SortieAuSud.getName() + " = " + SortieAuSud.getValue() + " ; ");
	    			System.out.println("Mort Au Sud" + " = " + MortAuSud.getValue());
	    		}while (Enigme1Solv.nextSolution());
	    		System.out.println(Enigme1Solv.getMeasures().getSolutionCount()+" solution(s) trouvée(s) en "
	    				+ Enigme1Solv.getMeasures().getTimeCount() + "secondes");		

	    		} else{
	    			System.out.println("Problème sans solution");
	    		}
	    		
	    	
		}


		public static void Enigme2() {
			// Mise en équations
			//T1= MaN ou SaS
			//T2 = SaN			
			//T1=T2			
		    //Déclaration du solveur
			Solver Enigme2Solv = new Solver("Enigme1");
			
		    //Déclaration des variables
	    	BoolVar Tete1 = VF.bool("Tete1",Enigme2Solv);
	    	BoolVar Tete2 = VF.bool("Tete2",Enigme2Solv);
	    	BoolVar SortieAuNord = VF.bool("Sortie au Nord",Enigme2Solv);
	    	BoolVar SortieAuSud = VF.bool("Sortie au Sud",Enigme2Solv);
	    	BoolVar MortAuNord = VF.not(SortieAuNord);
	    	BoolVar MortAuSud = VF.not(SortieAuSud);
			
	    	// Déclaration des contraintes
	    	Enigme2Solv.post(ICF.maximum(Tete1, MortAuNord, SortieAuSud));
	    	Enigme2Solv.post(ICF.arithm(Tete2,"=", SortieAuNord));
	    	Enigme2Solv.post(ICF.arithm(Tete1,"=", Tete2));

	    	// Résolution et affichage
	    	if(Enigme2Solv.findSolution()) {
	    		do {
	    			System.out.print(Tete1.getName() + " = " + Tete1.getValue() + " ; ");
	    			System.out.print(Tete2.getName() + " = " + Tete2.getValue() + " ; ");
	    			System.out.print(SortieAuNord.getName() + " = " + SortieAuNord.getValue() + " ; ");
	    			System.out.print("Mort Au Nord" + " = " + MortAuNord.getValue() + " ; ");
	    			System.out.print(SortieAuSud.getName() + " = " + SortieAuSud.getValue() + " ; ");
	    			System.out.println("Mort Au Sud" + " = " + MortAuSud.getValue());
	    		}while (Enigme2Solv.nextSolution());
	    		System.out.println(Enigme2Solv.getMeasures().getSolutionCount()+" solution(s) trouvée(s) en "
	    				+ Enigme2Solv.getMeasures().getTimeCount() + "secondes");		

	    		} else{
	    			System.out.println("Problème sans solution");
	    		}
	    		
	    	
		}

		public static void Enigme3() {
			// Mise en équations
			// E1 = MaN
			// E2 = SaE			
			// E3 = MaE			
			// SaN xor SaE xor SaS		
			// E1 xor E2 xor E3		
			
		    //Déclaration du solveur
			Solver Enigme3Solv = new Solver("Enigme3");
			
		    //Déclaration des variables
	    	BoolVar Eclair1 = VF.bool("Eclair1",Enigme3Solv);
	    	BoolVar Eclair2 = VF.bool("Eclair2",Enigme3Solv);
	    	BoolVar Eclair3 = VF.bool("Eclair3",Enigme3Solv);
	    	BoolVar SortieAuNord = VF.bool("Sortie au Nord",Enigme3Solv);
	    	BoolVar SortieAuSud = VF.bool("Sortie au Sud",Enigme3Solv);
	    	BoolVar SortieAEst = VF.bool("Sortie à l'Est",Enigme3Solv);
	    	BoolVar MortAuNord = VF.not(SortieAuNord);
	    	BoolVar MortAuSud = VF.not(SortieAuSud);
	    	BoolVar MortAEst = VF.not(SortieAEst);
	    	
	    	IntVar UN = VF.fixed(1, Enigme3Solv);
			
	    	// Déclaration des contraintes
	    	Enigme3Solv.post(ICF.arithm(Eclair1,"=", MortAuNord));
	    	Enigme3Solv.post(ICF.arithm(Eclair2,"=", SortieAEst));
	    	Enigme3Solv.post(ICF.arithm(Eclair3,"=", MortAEst));
	    	Enigme3Solv.post(ICF.sum(new BoolVar[]{SortieAuNord,SortieAuSud,SortieAEst}, UN));
	    	Enigme3Solv.post(ICF.sum(new BoolVar[]{Eclair1,Eclair2,Eclair3}, UN));
	    	
	    	// Résolution et affichage
	    	if(Enigme3Solv.findSolution()) {
	    		do {
	    			System.out.print(Eclair1.getName() + " = " + Eclair1.getValue() + " ; ");
	    			System.out.print(Eclair2.getName() + " = " + Eclair2.getValue() + " ; ");
	    			System.out.println(Eclair3.getName() + " = " + Eclair3.getValue() + " ; ");
	    			System.out.print(SortieAuNord.getName() + " = " + SortieAuNord.getValue() + " ; ");
	    			System.out.print("Mort Au Nord" + " = " + MortAuNord.getValue() + " ; ");
	    			System.out.print(SortieAuSud.getName() + " = " + SortieAuSud.getValue() + " ; ");
	    			System.out.print("Mort Au Sud" + " = " + MortAuSud.getValue() + " ; ");
	    			System.out.print(SortieAEst.getName() + " = " + SortieAEst.getValue() + " ; ");
	    			System.out.println("Mort A l'Est" + " = " + MortAEst.getValue());
	    		}while (Enigme3Solv.nextSolution());
	    		System.out.println(Enigme3Solv.getMeasures().getSolutionCount()+" solution(s) trouvée(s) en "
	    				+ Enigme3Solv.getMeasures().getTimeCount() + "secondes");		

	    		} else{
	    			System.out.println("Problème sans solution");
	    		}
	    		
	    	
		}

}
