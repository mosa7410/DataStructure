package DS09;

public class QuadracticProbingHashTable {
    private Entry[] entry;
    private int size, used;
    private float loadFactor;
    private final Entry NIL = new Entry(null, null);

    private class Entry {
        Object key, value;
        Entry(Object k, Object v) {
            key = k;
            value = v;
        }
    }

    public QuadracticProbingHashTable(int capacity, float loadFactor) {
        entry = new Entry[capacity];
        this.loadFactor = loadFactor;
    }

    public QuadracticProbingHashTable(int capacity) {
        this(capacity, 0.75F);
    }

    public QuadracticProbingHashTable() {
        this(101);
    }

    private int hash(Object key) {
        if(key == null) throw new IllegalArgumentException();
        return (key.hashCode() & 0x7fffffff) % entry.length;
    }

    private int nextProbe(int h, int i) {
        return (h+i*i)% entry.length;
    }

    private void rehash() {
        Entry[] oldEntry = entry;
        entry = new Entry[2*oldEntry.length+1];
        for(int k = 0; k < oldEntry.length; k++) {
            Entry entry = oldEntry[k];
            if(entry == null || entry == NIL) continue;
            int h = hash(entry.key);
            for(int i = 0; i < this.entry.length; i++) {
                int j = nextProbe(h, i);
                if(this.entry[j] == null) {
                    this.entry[j] = entry;
                    break;
                }
            }
        }
        used = size;
    }

    public Object get(Object key) {
        int h = hash(key);
        for (int i = 0; i< entry.length; i++) {
            int j = nextProbe(h, i);
            Entry entry = this.entry[j];
            if(entry == null) break;
            if(entry == NIL) continue;
            if(entry.key.equals(key)) return entry.value;
        }
        return null; //failure : key not Found
    }

    public Object put(Object key, Object value) {
        if(used > loadFactor* entry.length) rehash();
        int h = hash(key);
        for(int i = 0; i < entry.length; i++) {
            int j = nextProbe(h, i);
            Entry entry = this.entry[j];
            if(entry == null) {
                this.entry[j] = new Entry(key, value);
                ++size;
                ++used;
                return null; // insertion success
            }
            if(entry == NIL) continue;
            if(entry.key.equals(key)) {
                Object oldValue = entry.value;
                this.entry[j].value = value;
                return oldValue; // update success
            }
        }
        return null; // failure : table overflow
    }

    public int size() { return size; }
}
