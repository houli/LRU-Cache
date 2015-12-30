import java.util.Arrays;

class LRUCacheSet implements CacheSet {
    private byte[][] matrix;
    private int[] tags;

    public LRUCacheSet(int associativity) {
        this.matrix = new byte[associativity][associativity];
        this.tags = new int[associativity];

        // Fill with "empty" marker
        Arrays.fill(this.tags, -1);
    }

    public boolean access(int tag) {
        boolean hit = false;
        for (int t : this.tags) {
            if (t == tag) {
                hit = true;
            }
        }
        if (!hit) {
            for (int i = 0; i < this.matrix[0].length; i++) {
                if (allZero(this.matrix[i])) {
                    updateMatrix(i);
                    tags[i] = tag;
                }
            }
        }
        return hit;
    }

    private boolean allZero(byte[] row) {
        for (byte b : row) {
            if (b != 0) {
                return false;
            }
        }
        return true;
    }

    private void updateMatrix(int index) {
        for (int i = 0; i < matrix[0].length; i++) {
            matrix[i][index] = 1;
            matrix[index][i] = 0;
        }
    }
}
