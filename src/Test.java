import java.io.File;
import java.io.FileNotFoundException;

class Test {

    public static void main(String[] args) throws FileNotFoundException{

        Legesystem legesystemet = new Legesystem();
        try{
            legesystemet.lesFil(new File("./Inndata1.txt"));
        }
        catch (FileNotFoundException | UlovligUtskrift e){
            System.out.println(e.getMessage());
        }

        legesystemet.menyvalg();
    }
}