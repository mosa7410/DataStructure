package DS10;

import java.util.*;
import java.util.Iterator;

public class HuffmanCode {
    Map<Character, htree> map;
    List<htree> queue;
    public HuffmanCode() {
        map = new HashMap<Character, htree>();
        queue = new ArrayList();
    }

    public void encoding(htree tree) {
        while (tree.rchild != null && tree.lchild != null) {
            tree.lchild.code = tree.code + "0";
            tree.rchild.code = tree.code + "1";
            encoding(tree.lchild);
            encoding(tree.rchild);
            return;
        }
    }

    public void decoding(String code) {
        htree tree = queue.get(0);
        for(int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);
            if(tree.rchild == null && tree.lchild == null) {
                System.out.print(tree.alphabet);
                code = code.substring(i);
                decoding(code);
                return;
            }
            if(c == '1') tree = tree.rchild;
            else if(c == '0') tree = tree.lchild;
        }

        if(tree.rchild == null && tree.lchild == null) {
            System.out.print(tree.alphabet);
            return;
        }
    }

    public void priorityQueue() {
        Iterator<Character> iter = map.keySet().iterator();
        while(iter.hasNext()) {
            char key = iter.next();
            queue.add(map.get(key));
        }
        MinHeap(queue, 0, queue.size());
    }

    private void MinHeap(List a, int i, int n)
    {
        if (i >= n / 2) return;
        MinHeap(a, 2 * i + 1, n);
        MinHeap(a, 2 * i + 2, n);
        heapify(a, i, n);
    }

    private void heapify(List<htree> a, int i, int n) {
        htree parent = a.get(i);
        while(i < n/2) {
            int leaf = 2*i + 1;
            if(leaf + 1 < n && a.get(leaf+1).freq < a.get(leaf).freq) ++leaf;
            if(a.get(leaf).freq >= parent.freq) break;
            Collections.swap(a, i, leaf);
            i = leaf;
        }
    }

    public void huffmanTree() {
        htree temp = new htree();
        Collections.swap(queue, 0, queue.size() - 1);
        Collections.swap(queue, 1, queue.size() - 2);
        temp.lchild = queue.remove(queue.size() - 1);
        temp.rchild = queue.remove(queue.size() - 1);
        temp.freq = temp.rchild.freq + temp.lchild.freq;
        queue.add(temp);
        MinHeap(queue, 0, queue.size());
        while(queue.size() != 1) {
            huffmanTree();
            return;
        }
    }

    public class htree {
        char alphabet;
        int freq;
        String code;
        htree lchild;
        htree rchild;
        htree() {
            alphabet = 0;
            freq = 0;
            lchild = null;
            rchild = null;
            code = "";
        }
    }
}
