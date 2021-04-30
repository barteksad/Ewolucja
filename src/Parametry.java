package src;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

public class Parametry 
{
    private static int liczba_parametrów = 15;
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

        while(scanner.hasNext())
        {
            String parametr = "";
            int wartość = -1;
            try
            {
                parametr = scanner.next();
                if(parametr.equals("pocz_prog"))
                {
                    if(!this.sprawdźUstawPoczProg(scanner))
                    {
                        System.out.println("Zły format pliku z parametrami!\n");
                        System.exit(1);
                    }
                    continue;
                }
                if(parametr.equals("spis_instr"))
                {
                    if(!this.sprawdźUstawSpisInstr(scanner))
                    {
                        System.out.println("Zły format pliku z parametrami!\n");
                        System.exit(1);
                    }
                    continue;
                }
                wartość = scanner.nextInt();
                System.out.println(parametr + " " + wartość);
            }catch(Exception e)
            {
                System.out.println("Zły format pliku z parametrami!\n" + e);
                System.exit(1);
            }

            if(!this.sprawdźUstawParametr(parametr, wartość))
            {
                System.out.println("Zły format pliku z parametrami!\n");
                System.exit(1);
            }

            wczytane_parametry.add(parametr);
        }
        if(wczytane_parametry.size() != Parametry.liczba_parametrów)
        {
            System.out.println("Zły format pliku z parametrami!\n");
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
        return true;
    }

    private boolean sprawdźUstawSpisInstr(Scanner scanner)
    {
        spis_instr = new ArrayList<Character>();
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
                    spis_instr.add(input.charAt(i));
            }
            else
                return false;
        }
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
                rozmiar_planszy_x = wartość;
                return true;
            case "rozmiar_planszy_y":
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
                pr_powielenia = wartość;
                return true;
            case "ułamek_energii_rodzica":
                ułamek_energii_rodzica = wartość;
                return true;
            case "pr_usunięcia_instr":
                pr_usunięcia_instr = wartość;
                return true;
            case "pr_dodania_instr":
                pr_dodania_instr = wartość;
                return true;
            case "pr_zmiany_instr":
                pr_zmiany_instr = wartość;
                return true;
            case "co_ile_wypisz":
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
        return pocz_prog;
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
