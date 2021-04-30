package src;

public final class Żywieniowe extends Pole 
{
    private int ile_daje_jedzenie;
    private int ile_rośnie_jedzenie;
    private int ile_już_rośnie;


    public Żywieniowe(int x, int y, int ile_daje_jedzenie, int ile_rośnie_jedzenie)
    {
        super(x, y);
        this.ile_daje_jedzenie = ile_daje_jedzenie;
        this.ile_rośnie_jedzenie = ile_rośnie_jedzenie;
        ile_już_rośnie = ile_rośnie_jedzenie;
    }

    @Override
    public final int dajPożywienie()
    {
        if(ile_już_rośnie == ile_rośnie_jedzenie)
        {
            ile_już_rośnie = 0;
            return ile_daje_jedzenie;
        }
        else
            return 0;
    }

    @Override 
    public final void symulujTure()
    {
        ile_już_rośnie += 1;
        ile_już_rośnie %= ile_rośnie_jedzenie;
    }
}
