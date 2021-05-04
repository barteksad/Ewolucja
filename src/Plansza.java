package src;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Plansza 
{
    private int rozmiar_planszy_x;
    private int rozmiar_planszy_y;
    private ArrayList<Pole> plansza;

    public Plansza(String ścierzka, Parametry parametry)
    {
        Scanner sc = null; 
        try
        {
            sc = new Scanner(new File(ścierzka));
        }catch(Exception e)
        {
            System.out.println("Zła ścierzka do pliku z planszą!\n" + e);
            System.exit(1);
        }

        rozmiar_planszy_x = parametry.rozmiar_planszy_x();
        rozmiar_planszy_y = parametry.rozmiar_planszy_y();
        plansza = new ArrayList<Pole>(rozmiar_planszy_x * rozmiar_planszy_y);

        for(int i = 0; i < rozmiar_planszy_y; i++)
        {
            boolean sukces = true;
            if(!sc.hasNext())
                sukces = false;
            else
            {

                String input = sc.nextLine();
                
                for(int j = 0; j < rozmiar_planszy_x; j++)
                {
                    char c = input.charAt(j);
                    if(c != ' ' && c != 'x')
                    {
                        sukces = false;
                        break;
                    }            
                    if(c == ' ')
                        plansza.add(new Puste(j, i));
                    else
                        plansza.add(new Żywieniowe(j, i, parametry.ile_daje_jedzenie(), parametry.ile_rośnie_jedzenie()));   
                }
            }

            if(!sukces)
            {
                System.out.println("Zły format pliku z planszą!\n");
                System.exit(1);
            }
    }
    }    

    public Pole dajPole(int x, int y)
    {
        return plansza.get(y * rozmiar_planszy_x + x);
    }

    public Pole dajSąsiada(Pole pole, int kierunek)
    {
        int x = pole.x();
        int y = pole.y();

        switch(kierunek)
        {
            case 0:
                y += rozmiar_planszy_y - 1;
                break;
            case 1:
                x += rozmiar_planszy_x + 1;
                break;
            case 2:
                y += rozmiar_planszy_y + 1;
                break;
            case 3:
                x += rozmiar_planszy_x - 1;
                break;
            case 4: // skos prawa góra
                y += rozmiar_planszy_y - 1;
                x += rozmiar_planszy_x + 1;
                break;
            case 5: // skos prawy dół
                y += rozmiar_planszy_y + 1;
                x += rozmiar_planszy_x + 1;
            case 6: // skos lewy dół
                y += rozmiar_planszy_y + 1;
                x += rozmiar_planszy_x - 1;
                break;
            case 7: // skos lewa góra
                y += rozmiar_planszy_y - 1;
                x += rozmiar_planszy_x - 1;
                break;
            
        }

        x %= rozmiar_planszy_x;
        y %= rozmiar_planszy_y;

        return dajPole(x, y);
    }

    public int ileZŻywnością()
    {
        int ile = 0;
        for(Pole p: plansza)
            if(p.czyMaPożywienie())
                ile++;
        return ile;
    }
}
