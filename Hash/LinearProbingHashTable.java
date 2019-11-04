package DS07;

public class LinearProbingHashTable {
    public Entry[] entries;
    private int size, used;
    private float loadFactor;
    private final Entry NIL = new Entry(null, null);
    private int collision = 0;

    public class Entry {
        Object key, value;
        Entry(Object k, Object v) {
            key = k;
            value = v;
        }
    }

    private int hash(Object key) {
        if(key == null) throw new IllegalArgumentException();
        return (key.hashCode() & 0x7fffffff) % entries.length;
    }

    private int nextProbe(int h, int i) {
        return (h+i)%entries.length;
    }

    private void rehash() {
        Entry[] oldEntries = entries;
        entries = new Entry[2*oldEntries.length + 1];
        for(int k = 0; k < oldEntries.length; k++) {
            Entry entry = oldEntries[k];
            if(entry == null || entry == NIL) continue;
            int h = hash(entry.key);
            for(int i = 0; i < entries.length; i++) {
                int j = nextProbe(h, i);
                if(entries[j] == null) {
                    entries[j] = entry;
                    break;
                }
            }
        }
        used = size;
    }

    public LinearProbingHashTable(int capacity, float loadFactor) {
        entries = new Entry[capacity];
        this.loadFactor = loadFactor;
    }

    public LinearProbingHashTable(int capacity) {
        this(capacity, 0.75F);
    }

    public LinearProbingHashTable() {
        this(101);
    }

    public Object get(Object key) {
        int h = hash(key);
        for (int i = 0; i < entries.length; i++) {
            int j = nextProbe(h, i);
            Entry entry = entries[j];
            if(entry == null) break;
            if(entry == NIL) continue;
            if(entry.key.equals(key)) return entry.value;
        }
        return null; //failure : key not Found
    }

    public Object put(Object key, Object value) {
        if(used > loadFactor*entries.length) rehash();
        int h = hash(key);
        for(int i = 0; i < entries.length; i++) {
            int j = nextProbe(h, i);
            Entry entry = entries[j];
            if(entry == null) {
                entries[j] = new Entry(key, value);
                ++size;
                ++used;
                return null; // insertion success
            }
            if(entry == NIL) continue;
            if(entry.key.equals(key)) {
                Object oldValue = entry.value;
                entries[j].value = value;
                return oldValue; // update success
            }
            ++collision;
        }
        return null; // failure : table overflow
    }

    public Object remove(Object key) {
        int h = hash(key);
        for (int i = 0; i < entries.length; i++) {
            int j = nextProbe(h, i);
            Entry entry = entries[j];
            if(entry == null) break;
            if(entry == NIL) continue;
            if(entry.key.equals(key)) {
                Object oldValue = entry.value;
                entries[j] = NIL;
                --size;
                return oldValue; // success
            }
        }
        return null; // failure : key not found
    }

    public int size() {
        return size;
    }
    public int collision() { return collision; }
}
