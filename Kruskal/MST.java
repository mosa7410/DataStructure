package DS06;

import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MST {
    private int parent[];
    private int size;
    public Edge[] e;
    private int minCost = 0;

    class Edge {
        int v; // start vertex index
        int w; // end vertex index
        int weight; // 가중치
        boolean selected; // 간선으로 선택되었는지 여부
        Edge() {
            v = 0;
            w = 0;
            weight = Integer.MAX_VALUE;
            selected = false;
        }
    }

    public MST(int n) {
        this.size = n;
        this.parent = new int[size];
        Arrays.fill(parent, -1);
        try {
            BufferedReader br = new BufferedReader(new FileReader("c://input.txt"));
            String line = br.readLine();
            String[] split_line = line.split(" ");
            this.e = new Edge[Integer.parseInt(split_line[1])];
            int a = Integer.parseInt(split_line[1]);
            int i = 0;
            while( i != a ) {
                line = br.readLine();
                split_line = line.split(" ");
                e[i] = new Edge();
                e[i].v = Integer.parseInt(split_line[0]);
                e[i].w = Integer.parseInt(split_line[1]);
                e[i].weight = Integer.parseInt(split_line[2]);
                e[i].selected = true;
                i++;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void weighteduion(int i, int j) {
        for(int k = 0; k < size; k++)
            collapsingfind(k);

        int rooti = collapsingfind(i);
        int rootj = collapsingfind(j);

        System.out.print("( "+ i +" , " + j + " )");
        if(parent[rooti] < parent[rootj]) {
            parent[rooti] = parent[rooti] + parent[rootj];
            parent[rootj] = rooti;
        }
        else {
            parent[rootj] = parent[rootj] + parent[rooti];
            parent[rooti] = rootj;
        }
    }

    public int collapsingfind(int i) {
        int a = parent[i];
        if (a < 0)
            return i;
        else if (parent[a] < 0)
            return a;
        else {
            parent[i] = parent[a];
            return collapsingfind(a);
        }
    }

    public void kruskal() {
        Edge a = new Edge();
        for (int i = 0; i < e.length; i++) {
            for (int j = i + 1; j < e.length; j++) {
                if (e[i].weight > e[j].weight) {
                    a = e[i];
                    e[i] = e[j];
                    e[j] = a;
                }
            }
        }

        System.out.println("최소 신장 트리에 포함된 간선");
        int count = 0;
            for (int k = 0; k < size; k++) {
                int rootk = collapsingfind(e[k].v);
                int rootl = collapsingfind(e[k].w);
                if (count < size-1) {
                    if(rootk != rootl) {
                        weighteduion(e[k].v, e[k].w);
                        minCost += e[k].weight;
                        ++count;
                    }
                }
                else break;
            }

        if(count == size-1)
            System.out.print("\nMin Cost : " + minCost);
        else System.out.print("\n최소 신장 트리가 아닙니다.");
    }
}
