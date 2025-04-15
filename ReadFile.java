package be.uliege.montefiore.oop;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class ReadFile
{
    public Board loadFile(String filename) throws FileNotFoundException, InvalidFileFormatException, WrongValuesException
    {
        Scanner scan = new Scanner(new File(filename));
        int ligne = 1;

        try
        {
            if(!scan.hasNextInt()) 
                throw new InvalidFileFormatException("Ligne "+ ligne +" : erreur colonne");
            int nCol = scan.nextInt();

            if(!scan.hasNextInt()) 
                throw new InvalidFileFormatException("Ligne "+ ligne +" : erreur ligne");
            int nLig = scan.nextInt();

            if(!scan.hasNextInt()) 
                throw new InvalidFileFormatException("Ligne "+ ligne +" : erreur nombre de Piece");
            int nPiece = scan.nextInt();

            if(nCol <= 0 || nLig <= 0 || nPiece <= 0)
                throw new WrongValuesException("Ligne "+ ligne +" : les dimensions sont inférieures à 0");

            Board Grille = new Board(nCol,nLig);

            for(int i = 0; i < nPiece; i++)
            {
                ligne++; 

                if(!scan.hasNextInt()) 
                    throw new InvalidFileFormatException("Ligne "+ ligne +" : erreur largeur de la piece");
                int width = scan.nextInt();

                if(!scan.hasNextInt()) 
                    throw new InvalidFileFormatException("Ligne "+ ligne +" : erreur hauteur de la piece");
                int height = scan.nextInt();

                if(!scan.hasNextInt()) 
                    throw new InvalidFileFormatException("Ligne "+ ligne +" : erreur position X");
                int xpos = scan.nextInt() - 1;

                if(!scan.hasNextInt()) 
                    throw new InvalidFileFormatException("Ligne "+ ligne +" : erreur position Y");
                int ypos = scan.nextInt() - 1;

                Piece Puzzle = new Piece(width, height, xpos, ypos, Color.BLUE,false);
                try{
                    Grille.addPiece(Puzzle); 
                }catch(Exception e){
                        throw new InvalidFileFormatException("Erreur lors de l’ajout d’une pièce : " + e.getMessage());
                }
                
            }

            ligne++;
            if(!scan.hasNextInt()) 
                throw new InvalidFileFormatException("Ligne "+ ligne +" : erreur nombre d'objectif");
            int nbGoal = scan.nextInt();

            if(nbGoal <= 0 || nbGoal > nPiece)
                throw new WrongValuesException("Nombre d'objectif éronnée"); 

            for(int i = 0; i < nbGoal; i++)
            {
                ligne++;
                if(!scan.hasNextInt()) 
                    throw new InvalidFileFormatException("Ligne "+ ligne +" : erreur nombre de la piece");
                int nb = scan.nextInt() - 1;

                if(!scan.hasNextInt()) 
                    throw new InvalidFileFormatException("Ligne "+ ligne +" : erreur position X");
                int xposGoal = scan.nextInt() - 1;

                if(!scan.hasNextInt()) 
                    throw new InvalidFileFormatException("Ligne "+ ligne +" : erreur position Y");
                int yposGoal = scan.nextInt() - 1;

                if(nb > Grille.getPiece().size() || nb < 0)
                    throw new WrongValuesException("Pas d'objectif sir la pièce"); 

                Grille.getPiece().get(nb).setColor(Color.RED);
                Grille.getPiece().get(nb).setGoalPosition(xposGoal,yposGoal);
                Grille.getPiece().get(nb).setGoal(true);  
            }
            //verifier si il reste de l espace dans grille 

            return Grille; 
        }finally
        {
            scan.close(); 
        }
    }

}