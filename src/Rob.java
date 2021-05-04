package src;

import java.util.ArrayList;
import java.util.Random;

public class Rob 
{
    private static Random gen = new Random();

    private int kierunek; // 0 góra, 1 prawo, 2 dół, 3 lewo
    private ArrayList<Character> program;
    private int energia;
    private Pole pole;
    private int wiek = 0;
    
    public Rob(Parametry parametry, Plansza plansza) {
        this.program = parametry.pocz_prog();
        this.energia = parametry.pocz_energia();
        int x = Rob.gen.nextInt(parametry.rozmiar_planszy_x());
        int y = Rob.gen.nextInt(parametry.rozmiar_planszy_y());
        this.pole = plansza.dajPole(x, y);
        this.kierunek = Rob.gen.nextInt(4);
    }

    public Rob(Parametry parametry,ArrayList<Character> program, Plansza plansza, int energia, int kierunek) {
        this.program = program;
        this.energia = energia;
        int x = Rob.gen.nextInt(parametry.rozmiar_planszy_x());
        int y = Rob.gen.nextInt(parametry.rozmiar_planszy_y());
        this.pole = plansza.dajPole(x, y);
        this.kierunek = kierunek;
    }

    private ArrayList<Character> mutujProgram(Parametry parametry)
    {
        if(program.size() == 0)
            return null;
        ArrayList<Character> nowy = parametry.pocz_prog();

        double p = Rob.gen.nextDouble();
        if(p >= parametry.pr_usunięcia_instr())
            nowy.remove(nowy.size());

        p = Rob.gen.nextDouble();
        if(p >= parametry.pr_dodania_instr())
        {
            int idx = Rob.gen.nextInt(parametry.spis_instr().size());
            nowy.add(parametry.spis_instr().get(idx));
        }

        if(nowy.size() > 0)
        {
            p = Rob.gen.nextDouble();
            if(p >= parametry.pr_zmiany_instr())
            {
                int idx = Rob.gen.nextInt(nowy.size());
                int instr_idx = Rob.gen.nextInt(parametry.spis_instr().size());
                nowy.set(idx, parametry.spis_instr().get(instr_idx));
            }
        }
        return nowy;
    }

    private Rob powiel(Parametry parametry, Plansza plansza)
    {
        Rob nowy = null;
        if(energia >= parametry.ułamek_energii_rodzica())
        {
            double p = Rob.gen.nextDouble();
            if(p >= parametry.pr_powielenia())
            {
                ArrayList<Character> nowy_program = mutujProgram(parametry);
                nowy = new Rob(parametry, nowy_program, plansza, parametry.ułamek_energii_rodzica(), this.kierunek);
                this.energia -= parametry.ułamek_energii_rodzica();
            }
        }
        return nowy;
    }

    private void idź(Plansza plansza)
    {
        Pole nowe_pole = plansza.dajSąsiada(pole, kierunek);
        energia += nowe_pole.dajPożywienie();
        pole = nowe_pole;
    }

    private void wąchaj(Plansza plansza)
    {
        for(int k = 0; k < 4; k++)
        {
            Pole sąsiad = plansza.dajSąsiada(pole, k);
            if(sąsiad.czyMaPożywienie())
            {
                kierunek = k;
                break;
            }
        }
    }

    private void jedz(Plansza plansza)
    {
        for(int k = 0; k < 7; k++)
        {
            Pole sąsiad = plansza.dajSąsiada(pole, k);
            if(sąsiad.czyMaPożywienie())
            {
                energia += sąsiad.dajPożywienie();
                pole = sąsiad;
                break;
            }
        }
    }

    private void wykonajLosowyRuch(Parametry parametry, Plansza plansza)
    {
        if(program == null || program.size() == 0)
            return;
        char instrukcja = program.remove(program.size()-1);
        
        switch(instrukcja)
        {
            case 'l':
                kierunek += 3;
                kierunek %= 4;
                break;
            case 'p':
                kierunek += 1;
                kierunek %= 4;
            case 'i':
                idź(plansza);
            case 'w':
                wąchaj(plansza);
            case 'j':
                jedz(plansza);
        }
        energia--;
    }

    public Rob symulujTure(Parametry parametry, Plansza plansza)
    {
        wiek++;
        Rob nowy = null;

        energia -= parametry.koszt_tury();
        if(energia <= 0)
            return nowy;
        
        nowy = this.powiel(parametry, plansza);
        if(energia <= 0)
            return nowy;

        wykonajLosowyRuch(parametry, plansza);
        energia -= parametry.koszt_tury();
        return nowy;
    }  
    
    public int energia()
    {
        return energia;
    }

    public int wiek()
    {
        return wiek;
    }

    public int długość_programu()
    {
        return program.size();
    }

    @Override 
    public String toString()
    {
        return "energia: " + energia + " (x,y): " + pole + " hash#: " + this.hashCode();
    }
}
