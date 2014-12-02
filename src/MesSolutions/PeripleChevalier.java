package MesSolutions;

import solver.Solver;
import solver.constraints.ICF;
import solver.variables.VF;
import solver.variables.BoolVar;



/** 
 * Problème: 3. Le périple du chevalier
 * 3.1.1 Le premier choix
 * Le Chevalier Haje s’est égaré au fond de la forêt enchantée. 
 * Après des jours d’errance, il arrive devant un
 * chêne séculaire sur l’écorce duquel il peut lire "un de ces 
 * deux panneaux dit vrai l’autre ment". Baissant les
 * yeux, il voit eﬀectivement deux panneaux l’un pointant vers le Nord, 
 * l’autre vers le Sud. Le panneau pointant
 * vers le Nord lui dit "J’indique la direction de la sortie et 
 * l’autre panneau celle de la mort." Le second panneau
 * pour sa part s’exclame "Une des deux directions mène à la sortie et l’autre à la mort".
 * Quelle direction doit prendre Sir Haje ? (En admettant qu’il ne soit pas suicidaire).
 * 
 * Indications:
 * Le problème peut se modéliser comme ceci : soient P1, P2, SaN, MaN, SaS, MaS des 
 * variables booléennes (SaN = Sortie au Nord, MaS = Mort au Sud, ...), il faut trouver 
 * une solution au système de contraintes :
 * P1= non P2, SaN = non MaN, SaS = non MaS, P1 = SaN et MaS, P2 = (SaN ou SaS)et(MaN ou MaS)
 */
public class PeripleChevalier {

	public static void main(String[] args) {
		// Déclaration du solver
		Solver solveur = new Solver("premier choix");

		// Déclaration des variables
		BoolVar P1 = VF.bool("P1", solveur);
		BoolVar P2 = VF.bool("P2", solveur);
		BoolVar SaN = VF.bool("SaN", solveur);
		BoolVar MaN = VF.bool("MaN", solveur);
		BoolVar SaS = VF.bool("SaS", solveur);
		BoolVar MaS = VF.bool("Mas", solveur);
		
		// Déclaration des contraintes
		solveur.post(ICF.arithm(P1, "=", VF.not(P2)));
		solveur.post(ICF.arithm(SaN, "=", VF.not(MaN)));
		solveur.post(ICF.arithm(SaS, "=", VF.not(MaS)));
		
		solveur.post(ICF.minimum(P1, SaN, MaS));
		
		//BoolVar[] sa ={SaN, SaS};
		//BoolVar[] ma ={MaN, MaS};
		
		//BoolVar[] setm = {(BoolVar) LCF.or(sa),(BoolVar) LCF.or(ma)};
		//solveur.post(ICF.arithm(P1, "=", (BoolVar)LCF.and(setm)));
		
		
		// Stratégie

		// Résolution et affichage des résultats
		if (solveur.findSolution()) {
			do {
				System.out.println(P1.getName() + " = " + P1.getValue());
				System.out.println(P2.getName() + " = " + P2.getValue());
				System.out.println(SaN.getName() + " = " + SaN.getValue());
				System.out.println(MaN.getName() + " = " + MaN.getValue());
				System.out.println(SaS.getName() + " = " + SaS.getValue());
				System.out.println(MaS.getName() + " = " + MaS.getValue());
			} while (solveur.nextSolution());
			System.out.println(solveur.getMeasures().getSolutionCount()
					+ " solution(s) trouvée(s) en "
					+ solveur.getMeasures().getTimeCount() + "secondes");

		}else{
			System.out.println("Aucune solution trouvée !");
		}
	}

}
