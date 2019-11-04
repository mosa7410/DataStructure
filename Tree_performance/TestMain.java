package DS09;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestMain {
    public static void main(String args[]) {

        long start, end;
        int num = 10000;

        List<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < num; i++) list.add(i+1);
        Collections.shuffle(list);

        System.out.println("Number : " + num);
        System.out.println("*****INSERT*****");

        start = System.currentTimeMillis();
        avlTree avl = new avlTree(list.get(0));
        for(int i = 1; i < num; i++) {
            avl.grow(list.get(i));
        }
        end = System.currentTimeMillis();
        System.out.println("AVL : " + (end - start) + "ms");

        start = System.currentTimeMillis();
        BinarySearchTree bst = new BinarySearchTree(list.get(0));
        for(int i = 1; i < num; i++) {
            bst.add(list.get(i));
        }
        end = System.currentTimeMillis();
        System.out.println("BST : " + (end - start) + "ms");

        start = System.currentTimeMillis();
        QuadracticProbingHashTable quad = new QuadracticProbingHashTable();
        for(int i = 0; i < num; i++) {
            quad.put(list.get(i),list.get(i));
        }
        end = System.currentTimeMillis();
        System.out.println("HASH : " + (end - start) + "ms");

        System.out.println("\n*****SEARCH*****");
        start = System.currentTimeMillis();
        for(int i = 0; i < num; i++) {
            avl.search(list.get(i));
        }
        end = System.currentTimeMillis();
        System.out.println("AVL : " + (end - start) + "ms");

        start = System.currentTimeMillis();
        for(int i = 0; i < num; i++) {
            bst.search(list.get(i));
        }
        end = System.currentTimeMillis();
        System.out.println("BST : " + (end - start) + "ms");

        start = System.currentTimeMillis();
        for(int i = 0; i < num; i++) {
            quad.get(list.get(i));
        }
        end = System.currentTimeMillis();
        System.out.println("HASH : " + (end - start) + "ms");

        List<Integer> list1 = new ArrayList<Integer>();
        for(int i = 0; i < num*10; i++) list1.add(i+1);
        Collections.shuffle(list1);

        System.out.println("\nNumber : " + num*10);
        System.out.println("*****INSERT*****");
        start = System.currentTimeMillis();
        avlTree avl1 = new avlTree(list1.get(0));
        for(int i = 1; i < num*10; i++) {
            avl1.grow(list1.get(i));
        }
        end = System.currentTimeMillis();
        System.out.println("AVL : " + (end - start) + "ms");

        start = System.currentTimeMillis();
        BinarySearchTree bst1 = new BinarySearchTree(list1.get(0));
        for(int i = 1; i < num*10; i++) {
            bst1.add(list1.get(i));
        }
        end = System.currentTimeMillis();
        System.out.println("BST : " + (end - start) + "ms");

        start = System.currentTimeMillis();
        QuadracticProbingHashTable quad1 = new QuadracticProbingHashTable();
        for(int i = 0; i < num*10; i++) {
            quad1.put(list1.get(i),list1.get(i));
        }
        end = System.currentTimeMillis();
        System.out.println("HASH : " + (end - start) + "ms");

        Collections.shuffle(list1);
        System.out.println("\n*****SEARCH*****");
        start = System.currentTimeMillis();
        for(int i = 0; i < num*10; i++) {
            avl1.search(list1.get(i));
        }
        end = System.currentTimeMillis();
        System.out.println("AVL : " + (end - start) + "ms");

        start = System.currentTimeMillis();
        for(int i = 0; i < num*10; i++) {
            bst1.search(list1.get(i));
        }
        end = System.currentTimeMillis();
        System.out.println("BST : " + (end - start) + "ms");

        start = System.currentTimeMillis();
        for(int i = 0; i < num*10; i++) {
            quad1.get(list1.get(i));
        }
        end = System.currentTimeMillis();
        System.out.println("HASH : " + (end - start) + "ms");

        List<Integer> list2 = new ArrayList<Integer>();
        for(int i = 0; i < num*100; i++) list2.add(i+1);
        Collections.shuffle(list2);

        System.out.println("\nNumber : " + num*100);
        System.out.println("*****INSERT*****");
        start = System.currentTimeMillis();
        avlTree avl2 = new avlTree(list2.get(0));
        for(int i = 1; i < num*100; i++) {
            avl2.grow(list2.get(i));
        }
        end = System.currentTimeMillis();
        System.out.println("AVL : " + (end - start) + "ms");

        start = System.currentTimeMillis();
        BinarySearchTree bst2 = new BinarySearchTree(list2.get(0));
        for(int i = 1; i < num*100; i++) {
            bst2.add(list2.get(i));
        }
        end = System.currentTimeMillis();
        System.out.println("BST : " + (end - start) + "ms");

        start = System.currentTimeMillis();
        QuadracticProbingHashTable quad2 = new QuadracticProbingHashTable();
        for(int i = 0; i < num*100; i++) {
            quad2.put(list2.get(i),list2.get(i));
        }
        end = System.currentTimeMillis();
        System.out.println("HASH : " + (end - start) + "ms");

        Collections.shuffle(list2);
        System.out.println("\n*****SEARCH*****");
        start = System.currentTimeMillis();
        for(int i = 0; i < num*100; i++) {
            avl2.search(list2.get(i));
        }
        end = System.currentTimeMillis();
        System.out.println("AVL : " + (end - start) + "ms");

        start = System.currentTimeMillis();
        for(int i = 0; i < num*100; i++) {
            bst2.search(list2.get(i));
        }
        end = System.currentTimeMillis();
        System.out.println("BST : " + (end - start) + "ms");

        start = System.currentTimeMillis();
        for(int i = 0; i < num*100; i++) {
            quad2.get(list2.get(i));
        }
        end = System.currentTimeMillis();
        System.out.println("HASH : " + (end - start) + "ms");
    }
}
