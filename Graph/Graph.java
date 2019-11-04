package DS02;

/**
 * Created by 안정은 on 2017-09-11.
 */
public class Graph {
    private int size; //정점들의 개수
    private String[] vertices; // 정점들을 저장하는 배열
    private Node[] a; // 정점당 리스트를 가지게 하는 배열
    public Graph(String[] args) {
        size = args.length;
        a = new Node[size];
        vertices = new String[size];
        for(int i = 0; i < size; i++)
            vertices[i] = args[i];
    }

    public void add(String v, String w) {

        //Node p = a[index(v)];
        //Node q = a[index(w)];

        /*if(a[index(v)] == null)
            a[index(v)] = new Node(index(w), null);
        else {
            Node p = a[index(v)];
            p.next = new Node(index(w), p.next);
        }

        if(a[index(w)] == null)
            a[index(w)] = new Node(index(v), null);
        else {
            Node q = a[index(w)];
            q.next = new Node(index(v), q.next);
        }*/

        Node p = new Node(index(w), null);
        p.data = index(w);
        p.next = a[index(v)];
        a[index(v)] = p;

        Node q = new Node(index(v), null);
        q.data = index(v);
        q.next = a[index(w)];
        a[index(w)] = q;
    }

    private int index(String v) {
        for(int i = 0; i < size; i++) {
            if (vertices[i].equals(v)) return i;
        }
        return a.length;
    }

    public String toString() {

        if(size == 0) return "{}";
        StringBuffer str = new StringBuffer("{" + vertex(0));
        for(int i = 1; i < size; i++) {
            str.append(", " + vertex(i));
        }
        return str +"}";
    }

    private String vertex(int i) {
        StringBuffer str = new StringBuffer(vertices[i] + " : ");
        for(Node p = a[i]; p != null; p = p.next)
            str.append(vertices[p.data] + " ");
        return str + "";
    }

    public void calc_degree()
    {
        int[] count = new int[a.length];
        for(int i = 0; i < size; i++) {
            count[i] = 0;
        }
        for(int i = 0; i < size; i++) {
            for(Node p = a[i]; p != null; p = p.next)
                count[i]++;
        }
        System.out.println("정점  인접한 정점 수");
        for(int i = 0; i < size; i++) {
            System.out.println(" " + vertices[i] + "        " + count[i]);
        }
    }

    private class Node {
        private int data;
        private Node next;

        Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
