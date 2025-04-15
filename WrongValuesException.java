package be.uliege.montefiore.oop;

public class WrongValuesException extends Exception
{
    public WrongValuesException()
    {
        super();
    }
    
    public WrongValuesException(String s)
    {
        super(s);
    }
}