import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Main {
    Map<Integer, int[] > sources;
    static String[] files;
    HashMap<String, Dic> table= new  HashMap<String, Dic>();
    class Dic {
        public Map<Integer, Integer > DOC;
        Dic() {
            DOC = new HashMap<Integer, Integer>();
        }

    }

    Main() {
        sources = new HashMap<Integer, int[] >();
    }
    public double[] sort(int size)
    {
        double[] sorted=new double[size];
        int x=0;
            for(int i=0;i<files.length;i++)
            {
                for(int z=i+1;z<files.length;z++)
                {
                    sorted[x]=CosineSimilarity(i,z);
                    x++;

                }
            }

        Arrays.sort(sorted);

        return sorted;
    }
    public double CosineSimilarity(int doc1,int doc2){
        int DPV=0;
        double Magnitude1=0;
        double Magnitude2=0;
        double Magnitude=0;
        double CosineSimilarity=0;
        Iterator it = table.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            table.get(pair.getKey()).DOC.get(doc1);
            DPV = DPV + (table.get(pair.getKey()).DOC.get(doc1) * table.get(pair.getKey()).DOC.get(doc2));
            Magnitude1 = Magnitude1 + table.get(pair.getKey()).DOC.get(doc1) *table.get(pair.getKey()).DOC.get(doc1);
            Magnitude2 = Magnitude2 + table.get(pair.getKey()).DOC.get(doc2) * table.get(pair.getKey()).DOC.get(doc2);
        }
        Magnitude=Math.sqrt(Magnitude1)*Math.sqrt(Magnitude2);
        CosineSimilarity=DPV/Magnitude;
        return CosineSimilarity;


    }
    public void search() {
        int i = 0;
        int x=0;
        for (String fileName : files) {
            try (BufferedReader file = new BufferedReader(new FileReader(fileName))) {
                String ln;
                while ((ln = file.readLine()) != null)
                {
                    String[] words = ln.split("\\W+");
                    for (String word : words) {
                        word = word.toLowerCase();
                        // check to see if the word is not in the dictionary
                        if (!table.containsKey(word)) {
                            table.put(word, new Dic());
                        }
                        if (!table.get(word).DOC.containsKey(i)) {
                            table.get(word).DOC.put(i,0);

                        }
                        table.get(word).DOC.replace(i, table.get(word).DOC.get(i)+1);
                    }
                }
                i++;
                x=i;

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
       // int[] t=new int[table.size()];
        Iterator it = table.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            for(int g=0;g<x;g++)
            if (!table.get(pair.getKey()).DOC.containsKey(g)) {
                table.get(pair.getKey()).DOC.put(g,0);

            }
        }

    }
    /*
                        "C:\\Users\\pc\\Desktop\\AssignmentBonus\\docs\\100.txt",
                        "C:\\Users\\pc\\Desktop\\AssignmentBonus\\docs\\101.txt",
                        "C:\\Users\\pc\\Desktop\\AssignmentBonus\\docs\\102.txt",
                        "C:\\Users\\pc\\Desktop\\AssignmentBonus\\docs\\103.txt"
                        "C:\\Users\\pc\\Desktop\\AssignmentBonus\\docs\\1.txt",
                        "C:\\Users\\pc\\Desktop\\AssignmentBonus\\docs\\2.txt"
                        "C:\\Users\\pc\\Desktop\\AssignmentBonus\\docs\\01.txt",
                        "C:\\Users\\pc\\Desktop\\AssignmentBonus\\docs\\02.txt",
                        "C:\\Users\\pc\\Desktop\\AssignmentBonus\\docs\\03.txt"
                                                                                */
    public static void main(String args[]) {

       Main m=new Main();
        files=  new String[]
                {
                        "C:\\Users\\pc\\Desktop\\AssignmentBonus\\docs\\100.txt",
                        "C:\\Users\\pc\\Desktop\\AssignmentBonus\\docs\\101.txt",
                        "C:\\Users\\pc\\Desktop\\AssignmentBonus\\docs\\102.txt",
                        "C:\\Users\\pc\\Desktop\\AssignmentBonus\\docs\\103.txt"
                };

        System.out.println("-----------------Cosine Similarity--------------------- ");
        m.search();
        int size=0;
        for(int i=0;i<files.length;i++)
        {
            for(int z=i+1;z<files.length;z++)
            {
                size++;
            }
        }
        double[] sorted=new double[size];
        sorted=m.sort(size);
        for(int x=sorted.length-1;x>=0;x--)
        {
            for(int i=0;i<files.length;i++)
            {
                for(int z=i+1;z<files.length;z++)
                {
                    if((sorted[x]==m.CosineSimilarity(i,z))) {
                        System.out.printf("D%d and D%d cosine similarity = ", i + 1, z + 1);
                        System.out.println(m.CosineSimilarity(i, z));
                    }

                }
            }
        }



    }
}