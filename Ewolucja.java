package zad1;

import java.util.LinkedList;

public class Ewolucja
{
    private Parametry parametry;
    private Plansza plansza;
    private LinkedList<Rob> roby;
    private int tura;

    public Ewolucja(String plansza_ścierzka, String parametry_ścierzka)
    {
        parametry = new Parametry(parametry_ścierzka);
        plansza = new Plansza(plansza_ścierzka, parametry);
        roby = new LinkedList<Rob>();
        tura = 1;

        for(int i = 0; i < parametry.pocz_ile_robów(); i++)
            roby.add(new Rob(parametry, plansza));
        
        wypiszStatystyki();
    }

    public void symuluj()
    {
        System.out.print(this);

        while(tura <= parametry.ile_tur() && roby.size() > 0)
        {
            for(int i = roby.size() - 1; i >= 0; i--)
            {
                Rob nowy = roby.get(i).symulujTure(parametry, plansza);
                if(nowy != null)
                    roby.addLast(nowy);
                if(roby.get(i).energia() <= 0)
                    roby.remove(i);
            }
            if(roby.size() == 0)
                break;
            wypiszStatystyki();

            if(tura % parametry.co_ile_wypisz() == 0)
                System.out.print(this);
            tura++;
        }

        System.out.print(this);
    }

    private String statystykaProgram()
    {
        StringBuilder acc = new StringBuilder();

        acc.append(tura + ", ");
        acc.append("rob: " + roby.size() + ", ");
        acc.append("żyw: " + plansza.ileZŻywnością() + ", ");

        int min_prog = roby.get(0).długość_programu();
        int max_prog = roby.get(0).długość_programu();
        int suma = 0;
        for(Rob r: roby)
        {
            min_prog = Math.min(min_prog, r.długość_programu());
            max_prog = Math.max(max_prog, r.długość_programu());
            suma += r.długość_programu();
        }
        int średnia = roby.size() > 0 ? suma/roby.size() : 0;
        acc.append("prog: " + min_prog + "/" + średnia + "/" + max_prog + ", ");

        return acc.toString();
    }

    private String statystykaEnergia()
    {
        StringBuilder acc = new StringBuilder();

        int min_energia = roby.get(0).energia();
        int max_energia = roby.get(0).energia();
        int suma = 0;
        for(Rob r: roby)
        {
            min_energia = Math.min(min_energia, r.energia());
            max_energia = Math.max(max_energia, r.energia());
            suma += r.energia();
        }
        int średnia = roby.size() > 0 ? suma/roby.size() : 0;
        acc.append("energ: " + min_energia + "/" + średnia + "/" + max_energia + ", ");

        return acc.toString();
    } 

    private String statystykaWiek()
    {
        StringBuilder acc = new StringBuilder();

        
        int min_wiek = roby.get(0).wiek();
        int max_wiek = roby.get(0).wiek();
        int suma = 0;
        for(Rob r: roby)
        {
            min_wiek = Math.min(min_wiek, r.wiek());
            max_wiek = Math.max(max_wiek, r.wiek());
            suma += r.wiek();
        }
        int średnia = roby.size() > 0 ? suma/roby.size() : 0;
        acc.append("wiek: " + min_wiek + "/" + średnia + "/" + max_wiek); 

        return acc.toString();
    }

    public void wypiszStatystyki()
    {
        StringBuilder acc = new StringBuilder();

        acc.append(statystykaProgram());
        acc.append(statystykaEnergia());
        acc.append(statystykaWiek());
        
        System.out.println(acc);
    }

    @Override
    public String toString()
    {
        StringBuilder acc = new StringBuilder();
        acc.append("- - - - - - - - - -- - - - - - - - - -- - - - - - - - - -\n");
        acc.append("ewolucja tura: " + tura + ", liczba robów: " + roby.size() + "\n");
        for(Rob r: roby)
            acc.append(r + "\n");
        acc.append("- - - - - - - - - -- - - - - - - - - -- - - - - - - - - -\n");

        return acc.toString();
    }
}