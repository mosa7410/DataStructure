package DS09;

public class avlTree {
    private int key, height;
    private avlTree left, right;
    private int BalanceFactor;
    public static final avlTree NIL = new avlTree();

    public avlTree(int key) {
        this.key = key;
        left = right = NIL;
    }

    private avlTree() { // constructs the empty tree
        left = right = this;
    }

    private avlTree(int key, avlTree left, avlTree right) {
        this.key = key;
        this.left = left;
        this.right = right;
        height = 1 + Math.max(left.height, right.height);
    }

    public String toString() {
        if (this == NIL) return "";
        else {
            BalanceFactor  = left.height - right.height;
            return left + " (" + key + ", " + BalanceFactor + ") " + right;
        }
    }

    public int size() {
        if(this == NIL) return 0;
        return 1 + left.size() + right.size();
    }

    public boolean add(int key) {
        int oldSize = size();
        grow(key);
        return size() > oldSize;
    }

    public avlTree grow(int key) {
        if(this == NIL) return new avlTree(key);
        if(key == this.key) return this;
        if(key < this.key) left = left.grow(key);
        else right = right.grow(key);
        rebalance();
        height = 1 + Math.max(left.height, right.height);
        return this;
    }

    private void rebalance() {
        if(left.height > right.height + 1) {
            if(left.right.height > left.left.height)
                left.rotateLeft();
            rotateRight();
        }
        else if(right.height > left.height + 1) {
            if(right.left.height > right.right.height)
                right.rotateRight();
            rotateLeft();
        }
    }

    private void rotateLeft() {
        left = new avlTree(key, left, right.left);
        key = right.key;
        right = right.right;
    }

    private void rotateRight() {
        right = new avlTree(key, left.right, right);
        key = left.key;
        left = left.left;
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
}