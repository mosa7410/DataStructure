package DS04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TestDijkstra {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("C://input.txt")));
            int size = Integer.parseInt(br.readLine());
            String[] data = new String[size];
            for(int i = 0; i < size; i++)
                data[i] = br.readLine();
            WeightedGraph g = new WeightedGraph(size, data);
            String st = br.readLine();
            String[] push = new String[3];
            while(st != null) {
                push = st.split(" ");
                g.add(push[0], push[1], Integer.parseInt(push[2]));
                st = br.readLine();
            }
            g.dijkstra(0);
            g.printpath();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
