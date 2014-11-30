package solutions.CBar;


/*
 * LecteurFichier.java
 *
 * Created on 6 février 2007, 16:28
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */



import java.util.*;
import java.io.*;

public class LecteurFichier 
{

    protected String nomFic;
    public int[] instance;  //  créer une methode GetInstance
    protected int nbjobs;
    
    public LecteurFichier(String nFic) {
        nomFic = nFic;
        instance = null;
        nbjobs=0;
    }

    public int GetNbJobs() 
    {
    	return nbjobs;
    }

    public int[] convert() {
        try {
            BufferedReader in = new BufferedReader(new FileReader(nomFic)); // ouvre le fichier
            String line;
            int i = 0;
            line = in.readLine() ;
            if (line != null)
            {
            	nbjobs=Integer.parseInt(line);
            	instance = new int[nbjobs];
            	
            	while ((line = in.readLine()) != null) { // lit la première ligne du fichier : = null si on est à la fin du fichier
                    StringTokenizer st = new StringTokenizer(line, " ");
                    // Rq st peut être vue comme une liste de chaines de caractères séparées par le token " "
                    int taille = st.countTokens();  // nb de listes
                    int j = 0;
                    while(taille > 0){
                        int val = Integer.parseInt(st.nextToken());  //renvoie la string sur laquelle on pointe et passe à la suivante
                        instance[j] = val;
                        j++;
                        taille--;
                    }
                    i++;
                }
            }
            else System.out.println("vide !!!");
            
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur fichier");
        }

        System.out.println("------------------------------------------------");
        for(int i=0; i<1; i++){
            for(int j=0; j<nbjobs; j++) System.out.print(instance[j]+" ");
            System.out.println();
        }

        System.out.println();
        System.out.println("------------------------------------------------");


        return instance;
    }
}


