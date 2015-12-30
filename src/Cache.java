public class Cache {
    public enum ReplacementPolicy {
        LRU,
        PSEUDO_LRU
    }

    private int accesses;
    private int hits;

    private int numOffsetBits;
    private int numSetBits;

    private CacheSet[] cacheSets;

    public Cache(int l, int k, int n, ReplacementPolicy policy) {
        this.cacheSets = new CacheSet[n];
        for (int i = 0; i < cacheSets.length; i++) {
            switch (policy) {
            case LRU:
                cacheSets[i] = new LRUCacheSet(k);
                break;
            case PSEUDO_LRU:
                cacheSets[i] = new PseudoLRUCacheSet(k);
                break;
            default:
                break;
            }
        }

        // Surprisingly, Java doesn't have an arbitrary base log function so doing some ~maths~ here
        this.numOffsetBits = (int) Math.ceil(Math.log(l) / Math.log(2));
        this.numSetBits = (int) Math.ceil(Math.log(n) / Math.log(2));
    }

    public boolean access(int address) {
        this.accesses++;
        int setNumber = (address >> numOffsetBits) & mask(numSetBits);
        int tag = address >> (numSetBits + numOffsetBits);

        boolean hit = cacheSets[setNumber].access(tag);
        if (hit) {
            this.hits++;
        }
        return hit;
    }

    public void printResults() {
        System.out.println("Hits: " + this.hits);
        System.out.println("Accesses: " + this.accesses);
        System.out.println("Hit rate: " + (double) this.hits / this.accesses);
    }

    private int mask(int numBits) {
        return (1 << numBits) - 1;
    }
}
