import src.*;
public class Symulacja
{
    public static void main(String args[])
    {
        if(args.length != 2)
        {
            System.out.println("Zła liczba parametrów programu!");
            System.exit(1);
        }

        Parametry parametry = new Parametry(args[1]);
        Plansza plansza = new Plansza(args[0], parametry);
    }
}