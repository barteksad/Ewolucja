package src;

public abstract class Pole 
{
    private int x;
    private int y;
    
    public Pole(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int x()
    {
        return x;
    }

    public int y()
    {
        return y;
    }
    
    abstract int dajPożywienie();

    abstract void symulujTure();
}
