package DS07;

public class DoubleHashingHashTable {
    private Entry2[] entries;
    private int size, used;
    private float loadFactor;
    private final Entry2 NIL = new Entry2(null, null);
    private int collision = 0;

    private class Entry2 {
        Object key, value;
        Entry2(Object k, Object v) {
            key = k;
            value = v;
        }
    }

    public DoubleHashingHashTable(int capacity, float loadFactor) {
        entries = new Entry2[capacity];
        this.loadFactor = loadFactor;
    }

    public DoubleHashingHashTable(int capacity) {
        this(capacity, 0.75F);
    }

    public DoubleHashingHashTable() {
        this(101);
    }

    private int hash(Object key) {
        if(key == null) throw new IllegalArgumentException();
        return (key.hashCode() & 0x7fffffff) % entries.length;
    }

    private int hash2(Object key) {
        if(key == null) throw new IllegalArgumentException();
        return 1+(key.hashCode() & 0x7fffffff) % (entries.length-1);
    }

    private int nextProbe(int h, int d, int i) {
        return (h+d*i)%entries.length;
    }

    private void rehash() {
        Entry2[] oldEntries = entries;
        entries = new Entry2[2*oldEntries.length+1];
        for(int k = 0; k < oldEntries.length; k++) {
            Entry2 entry = oldEntries[k];
            if(entry == null || entry == NIL) continue;
            int h = hash(entry.key);
            int d = hash2(entry.key);
            for(int i = 0; i < entries.length; i++) {
                int j = nextProbe(h, d, i);
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
        int d = hash2(key);
        for (int i = 0; i<entries.length; i++) {
            int j = nextProbe(h, d, i);
            Entry2 entry = entries[j];
            if(entry == null) break;
            if(entry == NIL) continue;
            if(entry.key.equals(key)) return entry.value;
        }
        return null; //failure : key not Found
    }

    public Object put(Object key, Object value) {
        if(used > loadFactor*entries.length) rehash();
        int h = hash(key);
        int d = hash2(key);
        for(int i = 0; i < entries.length; i++) {
            int j = nextProbe(h, d, i);
            Entry2 entry = entries[j];
            if(entry == null) {
                entries[j] = new Entry2(key, value);
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
        int d = hash2(key);
        for (int i = 0; i < entries.length; i++) {
            int j = nextProbe(h, d, i);
            Entry2 entry = entries[j];
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
