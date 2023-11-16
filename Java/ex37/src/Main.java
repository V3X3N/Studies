import java.io.*;

public class Main
{
    public static void main(String[] args)
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        try
        {
            System.out.print("imie: ");
            String imie=br.readLine();
            System.out.print("nazwisko: ");
            String nazwisko=br.readLine();

            PrintWriter plik1=new PrintWriter(new BufferedWriter(new FileWriter("plik.txt",true)));
            plik1.println(imie+" "+nazwisko);
            plik1.close();
        }
        catch(Exception e){System.out.println(e);}



        System.out.println("\n-- z pliku --");

        try
        {
            BufferedReader plik2=new BufferedReader(new FileReader("plik.txt"));
            String str;

            while(plik2.ready())
            {
                str=plik2.readLine();
                System.out.println(str);
            }

            plik2.close();
        }
        catch(Exception e){System.out.println(e);}
    }
}