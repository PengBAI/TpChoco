package solutions;

import solver.Solver;
import solver.constraints.ICF;
import solver.variables.IntVar;
import solver.variables.VF;

//une solution unique
//forty = 29786
//+ ten = + 850
//+ ten = + 850
//-----   -----
//sixty = 31486
// Beaucoup plus rapide en déclarant forty, ten et sixty en bounded plutôt qu'en enumerated.
public class CryptarithmeChoco3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Solver CryptaSolv = new Solver("Cryptarithme");
		
		IntVar f = VF.bounded("f",1,9,CryptaSolv);
		IntVar o = VF.bounded("o",0,9,CryptaSolv);
		IntVar r = VF.bounded("r",0,9,CryptaSolv);
		IntVar t = VF.bounded("t",1,9,CryptaSolv);
		IntVar y = VF.bounded("y",0,9,CryptaSolv);
		IntVar e = VF.bounded("e",0,9,CryptaSolv);
		IntVar n = VF.bounded("n",0,9,CryptaSolv);
		IntVar s = VF.bounded("s",1,9,CryptaSolv);
		IntVar i = VF.bounded("i",0,9,CryptaSolv);
		IntVar x = VF.bounded("x",0,9,CryptaSolv);

		IntVar forty = VF.bounded("forty",10000,99999,CryptaSolv);
		IntVar ten = VF.bounded("ten",100,999,CryptaSolv);
		IntVar sixty = VF.bounded("sixty",10000,99999,CryptaSolv);
//		IntVar[] Tabforty =  new IntVar[] {f,o,r,t,y};

		
		CryptaSolv.post(ICF.scalar(new IntVar[] {f,o,r,t,y}, new int[]{10000,1000,100,10,1}, forty));
		CryptaSolv.post(ICF.scalar(new IntVar[] {t,e,n}, new int[]{100,10,1}, ten));
		CryptaSolv.post(ICF.scalar(new IntVar[] {s,i,x,t,y}, new int[]{10000,1000,100,10,1}, sixty));

		CryptaSolv.post(ICF.scalar(new IntVar[] {forty,ten,ten}, new int[]{1,1,1}, sixty));

		CryptaSolv.post(ICF.alldifferent(new IntVar[] {f,o,r,t,y,e,n,s,i,x}));
		
		
		if(CryptaSolv.findSolution()) {
			do {
				System.out.println(forty.getName() + " = " + forty.getValue());
				System.out.println(ten.getName() + " = " + ten.getValue());
				System.out.println(ten.getName() + " = " + ten.getValue());
				System.out.println(sixty.getName() + " = " + sixty.getValue());
			}while (CryptaSolv.nextSolution());
			System.out.println(CryptaSolv.getMeasures().getSolutionCount()+" solution(s) trouvée(s) en "
					+ CryptaSolv.getMeasures().getTimeCount() + "secondes");		

			}
	}

}
