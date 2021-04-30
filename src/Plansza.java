package src;

import java.io.File;
import java.io.FileNotFoundException;
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
}
