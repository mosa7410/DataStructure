package DS03;

import java.util.*;
/**
 * Created by 안정은 on 2017-09-11.
 */
public class Graph {
    int size;
    String[] vertices;
    boolean[][] a;
    boolean[] visit;
    boolean[] visit1;
    List data;
    List data1;

    public Graph(String[] args) {
        size = args.length;
        vertices = new String[size];
        System.arraycopy(args, 0, vertices, 0, size);
        a = new boolean[size][size];
        visit = new boolean[size];
        visit1 = new boolean[size];
        data = new ArrayList();
        data1 = new ArrayList();
    }

    public void add(String v, String w) {
        int i = index(v), j = index(w);
        a[i][j] = a[j][i] = true;
    }

    private int index(String v) {
        for(int i = 0; i < size; i++)
            if(vertices[i].equals(v))
                return i;
        return a.length;
    }

    public String toString() {
        if(size == 0) return "{}";
        StringBuffer buf = new StringBuffer("{" + vertex(0));
        for(int i = 1; i < size; i++)
            buf.append(" , " + vertex(i));
        return buf + "}";
    }

    private String vertex(int i) {
        StringBuffer buf = new StringBuffer(vertices[i] + " : ");
        for(int j = 0; j < size; j++)
            if(a[i][j]) buf.append(vertices[j]);
        return buf + "";
    }

    public void recu_dfs(int v) {
        visit[v] = true;
        data.add(vertices[v]);
        System.out.println("정점 " + vertices[v] + "를 리스트에 삽입한다.");
        for(int i = 0; i < size; i++) {
            if (a[v][i] && !visit[i])
                recu_dfs(i);
        }
    }

    public void nonrecu_dfs(int v) {
        Stack s = new Stack();
        visit1[v] = true;
        s.push(vertices[v]);
        data1.add(vertices[v]);
        System.out.println("정점 " + vertices[v] + "를 리스트에 삽입한다.");

        while(!s.isEmpty()) {
            for(int i = 0; i < size; i++) {
                if(a[v][i] && !visit1[i]) {
                    s.push(vertices[i]);
                    visit1[i] = true;
                    data1.add(vertices[i]);
                    System.out.println("정점 " + vertices[i] + "를 리스트에 삽입한다.");
                    v = i;
                }
            }
            int j = 0;
            while(v+1 != j) {
                if (s.peek().equals(vertices[j])) {
                    v = j;
                    s.pop();
                }
                j++;
            }
        }
    }

    public void printList() {
        System.out.print("Recu_dfs :");
        for(int i = 0; i < size; i++)
            System.out.print(" " + data.get(i).toString());
        System.out.println();
        System.out.print("NonRecu_dfs :");
        for(int j = 0; j < size; j++)
            System.out.print(" " + data1.get(j).toString());

    }
}
