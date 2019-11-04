package DS10;

import java.io.*;
import java.util.Iterator;

public class TestHuffmanCode {
    public static void main(String[] args) {
        HuffmanCode huf = new HuffmanCode();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("C://Caesar.txt")));

            String line = br.readLine();
            while(line != null) {
                line = line.toLowerCase();
                line = line.replaceAll(" |!|,|:|;|--|'|[?]|[.]", "");
                for(int i = 0; i < line.length(); i++) {
                    if(huf.map.containsKey(line.charAt(i)))
                        ++huf.map.get(line.charAt(i)).freq;
                    else {
                        HuffmanCode.htree tree = huf.new htree();
                        huf.map.put(line.charAt(i), tree);
                        huf.map.get(line.charAt(i)).alphabet = line.charAt(i);
                        ++huf.map.get(line.charAt(i)).freq;
                    }
                }
                line = br.readLine();
            }
            huf.priorityQueue();
            huf.huffmanTree();
            huf.encoding(huf.queue.get(0));

            Iterator<Character> iter = huf.map.keySet().iterator();
            while(iter.hasNext()) {
                char key = iter.next();
                System.out.println(huf.map.get(key).alphabet + " : " + huf.map.get(key).code);
            }

            System.out.println();
            BufferedReader anne = new BufferedReader(new FileReader(new File("C://Caesar.txt")));
            String Mark = anne.readLine();
            int count = 0;
            while(count != 4) {
                Mark = Mark.toLowerCase();
                System.out.println(Mark);
                Mark = Mark.replaceAll(" |!|,|:|;|--|'|[?]|[.]", "");
                for(int i = 0; i < Mark.length(); i++) {
                    System.out.print(huf.map.get(Mark.charAt(i)).code);
                }
                Mark = anne.readLine();
                System.out.println();
                ++count;
            }

            System.out.println("\nInsert bit : ");
            BufferedReader write = new BufferedReader(new InputStreamReader(System.in));
            String key = write.readLine();
            System.out.print(key + " Decoding : ");
            huf.decoding(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
