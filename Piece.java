package be.uliege.montefiore.oop;
public class Piece
{
    private int width; 
    private int height; 
    private int x, y; 
    private boolean goal;
    private int xGoal, yGoal; 
    private Color colour; 

    public Piece(int width, int height, int x, int y, Color colour, boolean goal)
    {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.colour = colour; 
        this.goal = goal; 
    }

    public int getPieceWidth() { return width; }
    public int getPieceHeight() { return height; }
    public int getX() { return x; }
    public int getY() { return y; }
    public boolean getGoal() { return goal;}
    public Color getColor(){return colour;}

    public void setGoalPosition(int xGoal, int yGoal)
    {
        this.xGoal = xGoal;
        this.yGoal = yGoal; 
    }

    public void setGoal(boolean GoalPiece)
    {
        this.goal = GoalPiece; 
    }

    public boolean isGoalPiece()
    {
        return goal; 
    }

    public boolean isGoalPieceAt()
    {
        if(this.x == xGoal && this.y == yGoal)
        {
            return true;
        }
        else return false; 
    }

    public boolean contain(int px, int py)
    {
        boolean inX = false, inY = false;
        if(px >= x && px < x + width) 
            inX = true; 
        if(py >= y && py < y + height)
            inY = true;
        if(inX && inY)
            return true;
        else return false; 
    }

    public void movePiece(int dx, int dy)
    {
        this.x += dx;
        this.y += dy; 
    }

    public void setColor(Color colour)
    {
        this.colour = colour; 
    }
}