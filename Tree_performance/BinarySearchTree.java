package DS09;

public class BinarySearchTree {
    private int key;
    private BinarySearchTree left, right;
    public static final BinarySearchTree NIL = new BinarySearchTree();

    public BinarySearchTree(int key) {
        this.key = key;
        left = right = NIL;
    }

    private BinarySearchTree() { // constructs the empty tree
        left = right = this;
    }

    public boolean add(int key) {
        if(this == NIL) {
            new BinarySearchTree(key);
            return true;
        }
        else if(key < this.key) {
            if(this.left != NIL)
                this.left.add(key);
            else this.left = new BinarySearchTree(key);
            return true;
        }
        else if(key > this.key) {
            if(this.right != NIL)
                this.right.add(key);
            else this.right = new BinarySearchTree(key);
            return true;
        }
        else if(key == this.key) return true;
        else return false;
    }

    public boolean search(int key) {
        if(this == NIL)
            return false;
        else if(key == this.key)
            return true;
        else if(key < this.key) left.search(key);
        else right.search(key);
        return false;
    }

    public String toString() {
        if (this == NIL) return "";
        else return left + " " + key + " " + right;
    }
}
