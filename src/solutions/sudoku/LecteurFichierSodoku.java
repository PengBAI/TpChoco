package solutions.sudoku;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class LecteurFichierSodoku {
    protected String nomFic;
    public int[][] Grille;  
    
    public LecteurFichierSodoku (String nFic) {
        nomFic = nFic;
        Grille = new int [9][9];
    }

    public int[][] GetGrille() 
    {
    	return Grille;
    }


    public void convert() {
        try {
            BufferedReader in = new BufferedReader(new FileReader(nomFic)); // ouvre le fichier
            String line;
           	for (int i=0; i<9; i++)
            	{
            		line = in.readLine();
                    if (line != null)
                    {
                    	StringTokenizer st = new StringTokenizer(line, " ");
                    	for (int j=0; j<9; j++)
                    		Grille[i][j] = Integer.parseInt(st.nextToken());  //renvoie la string sur laquelle on pointe et passe à la suivante
                    }
                    else System.out.println("Erreur ligne vide");
            	}
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur lecture fichier dans LecteurFichier2");
        }

 /*
 
            for(int i=0; i<9; i++) 
            {
                for(int j=0; j<9; j++) 
                	System.out.print(Grille[i][j]+" ");
                System.out.println();
            }
        System.out.println();
        System.out.println("------------------------------------------------");
 */
    }

}
