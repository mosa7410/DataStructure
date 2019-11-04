package DS05;

public class Sets {
    private int parent[];
    private int size;

    public Sets(int n) {
        this.size = n;
        this.parent = new int[size];
    }

    public void initialize() {
        this.parent = new int[size];
    }

    public void addSet(int set[]) {
        parent[set[0]] = -set.length;
        int j = 1;
        while(j < set.length) {
            int i = 0;
            while(i != set[j])
                i++;
            parent[i] = set[0];
            j++;
        }
    }

    public void printSets() {
        for(int i = 0; i < size; i++)
            collapsingFind(i);

        for(int j = 0; j < size; j++) {
            StringBuffer buf = new StringBuffer();
            if (parent[j] < 0) {
                buf.append("[Root : " + j + "]");
                buf.append(" {");
                for (int k = 0; k < size; k++) {
                    if (j == parent[k])
                        buf.append(" " + k + " ");
                }
                buf.append("}");
                System.out.println(buf);
            }
        }
    }

    public void weightedUnion(int i, int j) {
        for(int k = 0; k < size; k++)
            collapsingFind(k);

        int rooti = collapsingFind(i);
        int rootj = collapsingFind(j);

        System.out.println("\nweightedUnion : "+ i +", " + j);
        if(parent[rooti] < parent[rootj]) {
            parent[rooti] = parent[rooti] + parent[rootj];
            parent[rootj] = rooti;
        }
        else {
            parent[rootj] = parent[rootj] + parent[rooti];
            parent[rooti] = rootj;
        }
    }

    public int collapsingFind(int i) {
        int a = parent[i];
        if(a < 0)
            return i;
        else if(parent[a] < 0)
            return a;
        else {
            parent[i] = parent[a];
            return collapsingFind(a);
        }
    }
}