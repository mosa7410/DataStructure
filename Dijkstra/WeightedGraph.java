package DS04;

import java.util.Stack;

public class WeightedGraph {
    private int s;
    private int sim;
    private int min;
    private String[] vertices;
    int[][] a;
    private int[] distance;
    private int[] prev;
    private boolean[] visit;

    public WeightedGraph(int size, String[] args) {
        s = size;
        sim = size + 1;
        min = 0;
        vertices = new String[size];
        System.arraycopy(args, 0, vertices, 0, size);
        a = new int[size][size];
        distance = new int[size];
        prev = new int[size];
        visit = new boolean[size];
        for(int i = 0; i < s; i++) {
            distance[i] = Integer.MAX_VALUE;
            prev[i] = Integer.MAX_VALUE;
            for(int j = 0; j < s; j++)
                a[i][j] = Integer.MAX_VALUE;
        }
        distance[0] = 0;
    }

    public void add(String v, String w, int d) {
        int i = index(v), j = index(w);
        a[i][j] = a[j][i] = d;
    }

    private int index(String v) {
        for(int i = 0; i < s; i++)
            if(vertices[i].equals(v))
                return i;
        return a.length;
    }

    public void dijkstra(int v) {

        visit[v] = true;
        while (min != Integer.MAX_VALUE && sim != v) {
            min = Integer.MAX_VALUE;
            for (int i = 0; i < s; i++) {
                if (a[v][i] != Integer.MAX_VALUE && !visit[i]) {
                    if (distance[i] > distance[v] + a[v][i]) {
                        distance[i] = distance[v] + a[v][i];
                        prev[i] = v;
                    }

                    if((min != Integer.MAX_VALUE) && (distance[min] == distance[i]))
                        sim = i;

                    if ((min == Integer.MAX_VALUE) || (distance[min] > distance[i]))
                        min = i;
                }
            }
            if (min != Integer.MAX_VALUE)
                dijkstra(min);
        }
    }

    public void printpath() {

        for(int i = 1; i < s; i++) {
            Stack stack = new Stack();
            System.out.println(vertices[0]);
            int k = i;
            while( k != 0) {
                stack.push(k);
                k = prev[k];
            }
            while(!stack.isEmpty()) {
                System.out.print("->" + vertices[(int)stack.peek()] + "(" + distance[(int)stack.peek()] + ")" +"\t");
                stack.pop();
            }
            System.out.println();
        }
    }
}
