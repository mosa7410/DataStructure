package DS07;

public class QuadraticProbingHashTable {
    private Entry1[] entries;
    private int size, used;
    private float loadFactor;
    private final Entry1 NIL = new Entry1(null, null);
    private int collision = 0;

    private class Entry1 {
        Object key, value;
        Entry1(Object k, Object v) {
            key = k;
            value = v;
        }
    }

    public QuadraticProbingHashTable(int capacity, float loadFactor) {
        entries = new Entry1[capacity];
        this.loadFactor = loadFactor;
    }

    public QuadraticProbingHashTable(int capacity) {
        this(capacity, 0.75F);
    }

    public QuadraticProbingHashTable() {
        this(101);
    }

    private int hash(Object key) {
        if(key == null) throw new IllegalArgumentException();
        return (key.hashCode() & 0x7fffffff) % entries.length;
    }

    private int nextProbe(int h, int i) {
        return (h+i*i)%entries.length;
    }

    private void rehash() {
        Entry1[] oldEntries = entries;
        entries = new Entry1[2*oldEntries.length+1];
        for(int k = 0; k < oldEntries.length; k++) {
            Entry1 entry = oldEntries[k];
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

    public Object get(Object key) {
        int h = hash(key);
        for (int i = 0; i<entries.length; i++) {
            int j = nextProbe(h, i);
            Entry1 entry = entries[j];
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
            Entry1 entry = entries[j];
            if(entry == null) {
                entries[j] = new Entry1(key, value);
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
            Entry1 entry = entries[j];
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

    public int size() { return size; }
    public int collision() { return collision; }
}
