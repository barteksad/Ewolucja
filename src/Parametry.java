package src;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parametry 
{
    public Parametry(String ścierzka) throws FileNotFoundException
    {
        Scanner sc = new Scanner(new File(ścierzka));
    }
}
