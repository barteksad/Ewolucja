package zad1;

public final class Puste extends Pole 
{
    public Puste(int x, int y)
    {
        super(x, y);
    }

    @Override
    public final int dajPożywienie()
    {
        return 0;
    }

    @Override
    public final void symulujTure()
    {
        return;
    }

    @Override
    public final boolean czyMaPożywienie()
    {
        return false;
    }
}
