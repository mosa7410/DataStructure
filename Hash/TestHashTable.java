package DS07;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class TestHashTable {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("C://Caesar.txt")));
            LinearProbingHashTable l = new LinearProbingHashTable();
            QuadraticProbingHashTable q = new QuadraticProbingHashTable();
            DoubleHashingHashTable d = new DoubleHashingHashTable();
            String line = br.readLine();
            while(line != null) {
                StringTokenizer split_line = new StringTokenizer(line, " |,|;|.|:|--|?|!");
                while(split_line.hasMoreTokens()) {
                    String str = split_line.nextToken();
                    str = str.toLowerCase();
                    l.put(str, str);
                    d.put(str, str);
                    q.put(str, str);
                }
                line = br.readLine();
            }

            System.out.println("***** Collision Count *****");
            System.out.println("LineProb : " + l.collision());
            System.out.println("QuadProb : " + q.collision());
            System.out.println("DoubHash : " + d.collision());
            System.out.println();
            System.out.println("***** Word Count *****");
            System.out.println("LineProb : " + l.size());
            System.out.println("QuadProb : " + q.size());
            System.out.println("DoubHash : " + d.size());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
