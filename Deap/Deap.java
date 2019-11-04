package DS11;

public class Deap {
    int[] deap;
    int n = 0; // deap에 저장되는 원소의 개수, 루트는 비워져있음.

    public Deap(int maxSize) {
        deap = new int[maxSize];
    }

    // 인덱스 i가 max-heap에 위치해 있으면 true를 리턴하고, 그렇지 않으면 false를 리턴한다
    private boolean inMaxHeap(int i) {
        while(i != 1 && i != 2){
            i = (i-1)/2;
        }
        if(i == 1) return false;
        else  return true;
    }

    // 인덱스 pos가 min-heap에 위치해 있을때 max partner의 인덱스를 리턴한다
    private int maxPartner(int pos) {
        double a = Math.log(pos)+1;
        pos = (int) (pos + Math.pow(2, Math.floor(a)-1));
        if(pos >= n)
            return (pos-1)/2;
        else return pos;
    }

    // 인덱스 pos가 max-heap에 위치해 있을때 min partner의 인덱스를 리턴한다
    private int minPartner(int pos) {
        double a = Math.log(pos)+1;
        return (int) (pos - Math.pow(2, Math.floor(a)-1));
    }

    // min-heap에 있는 인덱스 at 위치에 key를 삽입
    private void minInsert(int at, int key) {
        deap[at] = key;
        while(true) {
            int parent = (at-1)/2;
            if(deap[parent] > deap[at] && parent != 0) {
                int i = deap[parent];
                deap[parent] = deap[at];
                deap[at] = i;
                at = parent;
            }
            else return;
        }
    }

    // max-heap에 있는 인덱스 at 위치에 key를 삽입
    private void maxInsert(int at, int key) {
        deap[at] = key;
        while(true) {
            int parent = (at-1)/2;
            if(deap[parent] < deap[at] && parent != 0) {
                int i = deap[parent];
                deap[parent] = deap[at];
                deap[at] = i;
                at = parent;
            }
            else return;
        }
    }

    // max 값을 삭제하여 리턴한다
    public int deleteMax() {
        System.out.println("---------- Delete Max ----------");
        if(n == 0) {
            System.out.println("Element does not exist");
            return 0;
        }
        int temp = deap[n];
        n--;
        int i = 2;
        int loc = 0;
        int max = deap[i];
        while(i<n) {
            if(deap[2*i+1] >= deap[2*i+2]) {
                deap[i] = deap[2*i+1];
                loc = i;
                i = 2*i+1;
            }
            else {
                deap[i] = deap[2*i+2];
                loc = i;
                i = 2*i+2;
            }
        }
        if(temp < deap[minPartner(loc)]) {
            int a = deap[minPartner(loc)];
            minInsert(minPartner(loc), temp);
            maxInsert(loc, a);
            //deap[i] = deap[minPartner(i)];
            //deap[minPartner(i)] = temp;
            //maxInsert(i, temp);
        }
        else maxInsert(loc, temp);
        return max;
    }

    // min 값을 삭제하여 리턴한다
    public int deleteMin() {
        System.out.println("---------- Delete Min ----------");
        if(n == 0) {
            System.out.println("Element does not exist");
            return 0;
        }
        int temp = deap[n];
        n--;
        int i = 1;
        int loc = 0;
        int min = deap[i];
        while(i<n) {
            if(deap[2*i+1] > deap[2*i+2]) {
                deap[i] = deap[2*i+2];
                loc = i;
                i = 2*i+2;
            }
            else {
                deap[i] = deap[2*i+1];
                loc = i;
                i = 2*i+1;
            }
        }
        if(temp > deap[maxPartner(loc)]) {
            int a = deap[maxPartner(loc)];
            maxInsert(maxPartner(loc), temp);
            minInsert(loc, a);
            //deap[i] = deap[maxPartner(i)];
            //deap[maxPartner(i)] = temp;
            //minInsert(i, temp);
        }
        else minInsert(loc, temp);
        return min;
    }

    // x를 삽입한다.
    public void insert(int x) {
        if (n == deap.length - 1) {
            System.out.println("The heap is full");
            System.exit(1);
        }
        n++;

        if (n == 1) {
            deap[1] = x;
            return;
        }
        if (inMaxHeap(n)) {
            int i = minPartner(n);
            if (x < deap[i]) {
                deap[n] = deap[i];
                minInsert(i, x);
            } else {
                maxInsert(n, x);
            }
        } else {
            int i = maxPartner(n);
            if (x > deap[i]) {
                deap[n] = deap[i];
                maxInsert(i, x);
            } else {
                minInsert(n, x);
            }
        }
    }

    // Deap를 트리 형식으로 프린트한다.
    public void print() {
        int levelNum = 2;
        int thisLevel = 0;
        int gap = 8;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < gap - 1; j++) {
                System.out.print(" ");
            }
            if (thisLevel != 0) {
                for (int j = 0; j < gap - 1; j++) {
                    System.out.print(" ");
                }
            }
            if (Integer.toString(deap[i]).length() == 1) {
                System.out.print(" ");
            }
            System.out.print(deap[i]);
            thisLevel++;
            if (thisLevel == levelNum) {
                System.out.println();
                thisLevel = 0;
                levelNum *= 2;
                gap /= 2;
            }
        }
        System.out.println();
        if (thisLevel != 0) {
            System.out.println();
        }
    }

    // 메인 함수 작성
    public static void main(String[] argv) {
        Deap deap = new Deap(1024);
        deap.insert(235);
        deap.insert(33);
        deap.insert(88);
        deap.insert(63);
        deap.insert(242);
        deap.insert(423);
        deap.insert(253);
        deap.insert(332);
        deap.insert(444);
        deap.insert(48);
        deap.insert(29);
        deap.insert(87);
        deap.insert(999);
        System.out.println("--------- Initial Deep ---------");
        deap.print();
        System.out.println("Min : " + deap.deleteMin());
        deap.print();
        System.out.println("Min : " + deap.deleteMin());
        deap.print();
        System.out.println("Min : " + deap.deleteMin());
        deap.print();
        System.out.println("Max : " + deap.deleteMax());
        deap.print();
        System.out.println("Max : " + deap.deleteMax());
        deap.print();
        System.out.println("Max : " + deap.deleteMax());
        deap.print();
    }
}
