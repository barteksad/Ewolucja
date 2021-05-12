package zad1;
public class Symulacja
{
    public static void main(String args[])
    {
        if(args.length != 2)
        {
            System.out.println("Zła liczba parametrów programu!");
            System.exit(1);
        }

        Ewolucja ewolucja = new Ewolucja(args[0], args[1]);
        ewolucja.symuluj();
    }
}