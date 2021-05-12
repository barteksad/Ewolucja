package zad1;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.TreeSet;

public class Parametry 
{
    private static int liczba_parametrów = 16;
    private static TreeSet<Character> dozwolone_ruchy = new TreeSet<Character>(Arrays.asList('l', 'p', 'i', 'w', 'j'));

    private int ile_tur;
    private int rozmiar_planszy_x;
    private int rozmiar_planszy_y;
    private int pocz_ile_robów;
    private ArrayList<Character> pocz_prog;
    private ArrayList<Character> spis_instr;
    private int pocz_energia;
    private int ile_daje_jedzenie;
    private int ile_rośnie_jedzenie;
    private int koszt_tury;
    private int pr_powielenia;
    private int ułamek_energii_rodzica;
    private int pr_usunięcia_instr;
    private int pr_dodania_instr;
    private int pr_zmiany_instr;
    private int co_ile_wypisz;

    public Parametry(String ścierzka)
    {
        Scanner scanner = null;
        try
        {
            scanner = new Scanner(new File(ścierzka));
        }catch(Exception e)
        {
            System.out.println("Zła ścierzka do pliku z parametrami!\n" + e);
            System.exit(1);
        }

        TreeSet<String> wczytane_parametry = new TreeSet<String>();

        try
        {
            while(scanner.hasNextLine())
            {
                String linia = scanner.nextLine();
                Scanner skan_lini = new Scanner(linia);
                String parametr = skan_lini.next();
                int ile_wczytano = parametr.length();

                if(wczytane_parametry.contains(parametr))
                    throw new InputMismatchException();
                wczytane_parametry.add(parametr);

                if(parametr.equals("pocz_prog"))
                {
                    if(!this.sprawdźUstawPoczProg(skan_lini))
                        throw new InputMismatchException();
                    ile_wczytano += pocz_prog.size() * 2 - 1;             
                }
                else if(parametr.equals("spis_instr"))
                {
                    if(!this.sprawdźUstawSpisInstr(skan_lini))
                        throw new InputMismatchException();   
                    ile_wczytano += spis_instr.size() * 2 - 1;                       
                }
                else 
                {
                    int wartość = skan_lini.nextInt();
                    ile_wczytano += ("" + wartość).length();
                    
                    if(wartość < 0 )
                        throw new InputMismatchException();             
                    if(!this.sprawdźUstawParametr(parametr, wartość))
                        throw new InputMismatchException();             
                }
                if(ile_wczytano + 1 != linia.length())
                    throw new InputMismatchException();    
            }
            if(wczytane_parametry.size() != Parametry.liczba_parametrów)
                throw new InputMismatchException();    
            if(!spis_instr.containsAll(pocz_prog))
                throw new InputMismatchException();    
        }catch(Exception e)
        {
            System.out.println("Zły format pliku z parametrami!\n" + e);
            System.exit(1);
        }
    }

    private boolean sprawdźUstawPoczProg(Scanner scanner)
    {
        pocz_prog = new ArrayList<Character>();
        String input = scanner.nextLine();
        boolean spacja = true;

        for(int i = 0;i < input.length(); i++)
        {
            if(!(input.charAt(i) == ' ' ^ spacja))
            {
                spacja = !spacja;
                if(!spacja)
                    continue;
                if(!Parametry.dozwolone_ruchy.contains(input.charAt(i)))
                    return false;
                pocz_prog.add(input.charAt(i));
            }
            else
                return false;
        }
        if(spacja == false)
            return false;
        return true;
    }

    private boolean sprawdźUstawSpisInstr(Scanner scanner)
    {
        spis_instr = new ArrayList<Character>();
        String input = scanner.nextLine();
        boolean spacja = true;

        for(int i = 0;i < input.length(); i++)
        {
            char nowy = input.charAt(i);
            if(!(nowy == ' ' ^ spacja))
            {
                spacja = !spacja;
                if(!spacja)
                    continue;
                if(!Parametry.dozwolone_ruchy.contains(nowy))
                    return false;
                if(spis_instr.contains(nowy))
                    return false;
                spis_instr.add(nowy);
            }
            else
                return false;
        }
        if(spacja == false)
            return false;
        return true;    
    }

    private boolean sprawdźUstawParametr(String parametr, int wartość)
    {
        switch(parametr)
        {
            case "ile_tur":
                ile_tur = wartość;
                return true;
            case "rozmiar_planszy_x":
                if(wartość == 0)
                    return false;
                rozmiar_planszy_x = wartość;
                return true;
            case "rozmiar_planszy_y":
                if(wartość == 0)
                    return false;
                rozmiar_planszy_y = wartość;
                return true;
            case "pocz_ile_robów":
                pocz_ile_robów = wartość;
                return true;
            case "ile_daje_jedzenie":
                ile_daje_jedzenie = wartość;
                return true;
            case "ile_rośnie_jedzenie":
                ile_rośnie_jedzenie = wartość;
                return true;
            case "koszt_tury":
                koszt_tury = wartość;
                return true;
            case "pocz_energia":
                pocz_energia = wartość;
                return true;
            case "pr_powielenia":
                if(wartość > 100)
                    return false;
                pr_powielenia = wartość;
                return true;
            case "ułamek_energii_rodzica":
                ułamek_energii_rodzica = wartość;
                return true;
            case "pr_usunięcia_instr":
                if(wartość > 100)
                    return false;
                pr_usunięcia_instr = wartość;
                return true;
            case "pr_dodania_instr":
                if(wartość > 100)
                    return false;
                pr_dodania_instr = wartość;
                return true;
            case "pr_zmiany_instr":
                if(wartość > 100)
                    return false;
                pr_zmiany_instr = wartość;
                return true;
            case "co_ile_wypisz":
                if(wartość == 0)
                    return false;
                co_ile_wypisz = wartość;
                return true;
            default:
                return false;
        }
    }

    public int ile_tur()
    {
        return ile_tur;
    }
    public int rozmiar_planszy_x()
    {
        return rozmiar_planszy_x;
    }
    public int rozmiar_planszy_y()
    {
        return rozmiar_planszy_y;
    }
    public int pocz_ile_robów()
    {
        return pocz_ile_robów;
    }
    public ArrayList<Character> pocz_prog()
    {
        ArrayList<Character> kopia = new ArrayList<Character>();
        for(char c : pocz_prog)
            kopia.add(c);
        return kopia;
    }
    public ArrayList<Character> spis_instr()
    {
        return spis_instr;
    }
    public int pocz_energia()
    {
        return pocz_energia;
    }
    public int ile_daje_jedzenie()
    {
        return ile_daje_jedzenie;
    }
    public int ile_rośnie_jedzenie()
    {
        return ile_rośnie_jedzenie;
    }
    public int koszt_tury()
    {
        return koszt_tury;
    }
    public int pr_powielenia()
    {
        return pr_powielenia;
    }
    public int ułamek_energii_rodzica()
    {
        return ułamek_energii_rodzica;
    }
    public int pr_usunięcia_instr()
    {
        return pr_usunięcia_instr;
    }
    public int pr_dodania_instr()
    {
        return pr_dodania_instr;
    }
    public int pr_zmiany_instr()
    {
        return pr_zmiany_instr;
    }
    public int co_ile_wypisz()
    {
        return co_ile_wypisz;
    }
}
