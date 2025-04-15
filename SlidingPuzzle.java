package be.uliege.montefiore.oop;
import java.io.FileNotFoundException;
import be.uliege.montefiore.oop.GUIException;

public class SlidingPuzzle
{
    public static void  main(String[] args) throws InvalidFileFormatException, FileNotFoundException
    {
        if(args.length != 1)
            throw new InvalidFileFormatException("Usage: java SlidingPuzzle <filename>");

        try
        {
            ReadFile reader_file = new ReadFile();
            Board Grille = reader_file.loadFile(args[0]); 

            GraphInterface gI = new GraphInterface(Grille.getCol(), Grille.getLig(), 1200, 750); 

            boolean endGame = false;
            gI.display(Grille); 
            while(endGame == false)
            {
                endGame = gI.nextMove(Grille); 
                gI.display(Grille);
            }
            while(endGame == true)
            {
                gI.waitForWindowClosing(Grille); 
            }
            return; 
        }catch (WrongValuesException e)
        {
            System.out.println(e.getMessage());
            return;
        }
        catch (GUIException e)
        {
            System.out.println(e.getMessage());
            return;
        }
        catch (GameFinished e)
        {
            return;
        }
    }
}