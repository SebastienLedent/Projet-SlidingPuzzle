package be.uliege.montefiore.oop;
import java.util.Vector;

public class Board
{
    private int nCol, nLig;
    private Vector<Piece> pieces; 
    private boolean[][] BoardInGame; 

    public Board(int nCol, int nLig)
    {
        this.nCol = nCol;
        this.nLig = nLig; 
        this.pieces = new Vector<>();
        this.BoardInGame = new boolean[nCol][nLig]; 
    }

    public void addPiece(Piece piece) throws Exception
     { 
        if(!isGoodPosition(piece))
        {
            throw new Exception("Piece is out of the board");
        }
        if(!isGood(piece))
        {
            throw new Exception("Piece overlap an other");
        }
        pieces.add(piece);
        updateGoodPositionGrid();
     }

    public Piece getPieceCase(int x, int y)
    {
        for(Piece p : pieces)
        {
            if(p.contain(x,y))
            {
                return p;
            }
        
        }
        return null; 
    }

    public boolean isGoodPosition(Piece piece)
    {
        int x = piece.getX();
        int y = piece.getY(); 
        int width = piece.getPieceWidth();
        int height = piece.getPieceHeight();

        if( x >= 0 && x + width <= nCol)
        {
            if(y >= 0 && y + height <= nLig)
            {
                return true;
            }
        }
        
        return false;
    }

    public boolean isGood(Piece piece)
    {
        for(Piece p : pieces)
        {
            if(p == piece)
            {
                continue;
            }

            if(chevauchement(piece,p))
            {
                return false;
            }
        }
        return true;

    }

    private boolean chevauchement(Piece p_a, Piece p_b)
    {
        if(p_a.getX() < p_b.getX() + p_b.getPieceWidth() && p_a.getX() + p_a.getPieceWidth() > p_b.getX())
        {
            if(p_a.getY() < p_b.getY() + p_b.getPieceHeight() && p_a.getY() + p_a.getPieceHeight() > p_b.getY())
            {
                return true;
            }
        }
        return false; 

    }

    public void updateGoodPositionGrid()
    {
        BoardInGame = new boolean[nCol][nLig];

        for(Piece p: pieces)
        {
            for(int i = 0; i < p.getPieceWidth(); i++)
            {
                for(int j = 0; j < p.getPieceHeight(); j++)
                {
                    BoardInGame[p.getX() + i][p.getY() + j] = true;
                }
            
            }
        }

    }

    public boolean move(Piece piece, int dx, int dy)
    {
        int x = piece.getX();
        int y = piece.getY(); 

        piece.movePiece(dx,dy);

        if(!isGoodPosition(piece) || !isGood(piece))
        {
            piece.movePiece(-dx,-dy);
            return false;
        }

        updateGoodPositionGrid(); 
        return true; 
    }

    public boolean Resolu()
    {
        for(Piece p : pieces)
        {
            if(p.isGoalPiece() && !p.isGoalPieceAt())
                return false;
        }
        return true; 
    }

    public Vector<Piece> getPiece()
    {
        return pieces;
    }

    public int getCol()
    {
        return nCol; 
    }

    public int getLig()
    {
        return nLig; 
    }

}