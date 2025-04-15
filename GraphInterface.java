package be.uliege.montefiore.oop;
import be.uliege.montefiore.oop.*;
import java.util.Vector;


public class GraphInterface
{
    private int width, height, nCol, nLig; 
    private SlidingPuzzleGUI sp;
    private int sizeh, sizew;
    public GraphInterface(int nCol, int nLig, int width, int height) throws WrongValuesException, GUIException
    {
         if(width> 1600 || height > 900) {
            throw new WrongValuesException("Wrong values for window size");
        }

        sp = new SlidingPuzzleGUI(width, height);
        this.width = width; 
        this.height = height; 
        this.nCol = nCol; 
        this.nLig = nLig; 

        this.sizeh = height/nLig;
        this.sizew = width/nCol;
    }

    public void display(Board Grille) throws GUIException
    {
        sp.startFrame(); 
        Vector<Piece> pieces = Grille.getPiece(); 
        for(Piece p : pieces)
        {
            int x = sizew*(p.getX()-1);
            int y = sizeh*(p.getY()-1);
            int w = sizew*p.getPieceWidth();
            int h = sizeh*p.getPieceHeight();  
            switch(p.getColor())
            {
                case RED: 
                    sp.newRectangle(x,y,w,h,255,0,0);
                    break;
                case GREEN: 
                    sp.newRectangle(x,y,w,h,0,255,0);
                    break; 
                case BLUE: 
                    sp.newRectangle(x,y,w,h,0,0,255); 
                    break; 

            }
        }
        sp.endFrame();
    }

    public boolean nextMove(Board Grille) throws GameFinished
    {
        try
        {
            int[] Move = sp.nextMove(); 
            if(Move == null)
                throw new GameFinished("Window has been closed"); 
            
            int x0 = Move[0]/sizew + 1; 
            int y0 = Move[1]/sizeh + 1;
            int x = Move[2]/sizew + 1;
            int y = Move[3]/sizeh + 1;

            Piece goodPiece = Grille.getPieceCase(x0,y0);
            if(goodPiece != null)
            {
                if(Grille.move(goodPiece, x-x0, y-y0))
                {
                    display(Grille);
                    return true;
                }
            }

            return false; 

        }catch(GUIException e){
            return false; 
        }
    }

    public void waitForWindowClosing(Board Grille) throws GameFinished
    {
        try
        {
            sp.startFrame(); 
            Vector<Piece> pieces = Grille.getPiece(); 
            for(Piece p : pieces)
            {
                int x = sizew*(p.getX()-1);
                int y = sizeh*(p.getY()-1);
                int w = sizew*p.getPieceWidth();
                int h = sizeh*p.getPieceHeight();  
                sp.newRectangle(x,y,w,h,0,255,0);
            }
            sp.endFrame(); 

        }catch(NullPointerException e)
        {
            throw new GameFinished("Window has been closed");
        }
        catch(GUIException e){}
    }



}
