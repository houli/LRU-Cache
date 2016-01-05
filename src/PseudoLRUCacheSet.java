import java.util.Arrays;

class PseudoLRUCacheSet implements CacheSet {
    private int[] tags;

    private BitTree tree;

    public PseudoLRUCacheSet(int associativity) {
        this.tags = new int[associativity];
        // Fill with "empty" marker
        Arrays.fill(this.tags, -1);

        if (associativity != 1) {
            this.tree = new BitTree(associativity);
        }
    }

    public boolean access(int tag) {
        boolean hit = false;
        for (int i = 0; i < this.tags.length; i++) {
            if (this.tags[i] == tag) {
                hit = true;
                if (this.tags.length != 1) {
                    this.tree.update(i);
                }
                break;
            }
        }

        if (!hit) {
            if (this.tags.length == 1) {
                // Direct-mapped cache
                this.tags[0] = tag;
            } else {
                int index = this.tree.getNextIndex();
                this.tags[index] = tag;
            }
        }
        return hit;
    }
}
