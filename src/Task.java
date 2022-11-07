import java.io.*;
import java.util.concurrent.ConcurrentHashMap;

public class Task implements Runnable
{

    File file;
    ConcurrentHashMap<Character,Integer> concurrentHashMap;

    public Task(File file, ConcurrentHashMap<Character,Integer> concurrentHashMap)
    {
        this.file = file;
        this.concurrentHashMap = concurrentHashMap;
    }

    @Override
    public void run()
    {

        synchronized (concurrentHashMap)
        {

            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line;

                while ((line = bufferedReader.readLine()) != null)
                    for (int i = 0; i < line.length(); i++)
                        if(concurrentHashMap.containsKey(line.charAt(i)))
                            concurrentHashMap.replace(line.charAt(i), concurrentHashMap.get(line.charAt(i)) + 1);

            } catch (IOException e) {throw new RuntimeException(e);}
        }
    }
}
