
import java.io.*;
import java.util.*;

public class FindCommonWords {

    private static int incrCount = 0;

    public static void main(String[] args)
            throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String path = reader.readLine();
            HashMap<String, Integer> result = getCommonWords(path);
            if(result.size() != 0) {
                System.out.println("Common Words are :: \n");
                Set set = result.keySet();
                Iterator<String> iter = set.iterator();
                while (iter.hasNext()) {
                    System.out.print(iter.next() + " ");
                }
            } else {
                System.out.println("\nNo Common Words.");
            }
        } catch (IOException e) {
            System.out.println("Error in reading path.");
        }
    }

    public static HashMap<String, Integer> getCommonWords(String path) {
        File[] file = new File(path).listFiles();
        HashMap<String, Integer> result = new HashMap<>();
        Scanner scanFile = null;
        String theWord = new String();
        int count = 1;
        for(int i = 0; i < file.length; ++i) {
            try {
                scanFile = new Scanner(new FileReader(file[i]));
                while (scanFile.hasNext()){
                    theWord = scanFile.next().toLowerCase();
                    theWord = removeSpecialCharacters(theWord);
                    if(i == 0) {
                        result.put(theWord, 1);
                    } else {
                        if(result.containsKey(theWord)){
                            if(result.get(theWord) == count)
                                result.put(theWord, result.get(theWord)+1);
                        }
                    }
                }
                if(i != 0) {
                    incrCount = incrCount+1;
                    count = count + 1;
                    removeUnsedWordFromMap(result, incrCount);
                }
            } catch (Exception e) {
                System.out.println("Exception in reading file."+file[0].getAbsolutePath());
            }
        }
        return result;
    }

    public static String removeSpecialCharacters(String word){
        return word.replaceAll("[^a-zA-Z0-9]", "");
    }

    public static void removeUnsedWordFromMap(HashMap<String, Integer> result, Integer incrCount) {

        Iterator<Map.Entry<String, Integer>> entryIt = result.entrySet().iterator();
        while (entryIt.hasNext()) {
            Map.Entry<String, Integer> entry = entryIt.next();
            if (entry.getValue() == incrCount) {
                entryIt.remove();
            }
        }
    }
}
