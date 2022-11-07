import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main
{

    public static void main(String[] args)
    {

        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        ArrayList<File> arrayFile = new ArrayList<>();
        ExecutorService service = Executors.newCachedThreadPool();
        ConcurrentHashMap<Character,Integer> concurrentHashMap = new ConcurrentHashMap<>();
        boolean t = true;
        int i;

        try
        {
            while (t)
            {
                System.out.println("\n\nInserisci il path di un file di testo (terminare inserimento con exit): ");
                String path = bufferedReader.readLine();

                if (path.equals("exit")) t = false;

                else
                {
                    File file = new File(path);

                    if (!file.exists()) System.err.printf("Il path %s non esiste\n", file);
                    else arrayFile.add(file);
                }
            }

            System.out.print("\nInizio conteggio\n");

            for (i=65; i<=90; i++) concurrentHashMap.put((char) i, 0); //[A-Z]
            for (i=97; i<=122; i++) concurrentHashMap.put((char) i, 0); //[a-z]

            for (i = 0; i < arrayFile.size(); i++)
            {
                System.out.print("\nFile: " + arrayFile.get(i));
                service.execute(new Task(arrayFile.get(i),concurrentHashMap));
            }

            service.shutdown();
            service.awaitTermination(5000, TimeUnit.MILLISECONDS);

            System.out.print("\n\nFine conteggio");

            File output = new File("output.txt");
            FileWriter fileWriter = new FileWriter(output);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            concurrentHashMap.forEach( (k, v) -> {
                try {
                    bufferedWriter.write(k + "," + v + "\n");
                    bufferedWriter.flush();
                } catch (IOException e) {throw new RuntimeException(e);}
            });

            bufferedWriter.close();

        }catch (IOException ioException) {System.out.println("IOException!");} catch (InterruptedException e) {throw new RuntimeException(e);}

    }

}