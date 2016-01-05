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
        for (int i = 0; i < this.tags.length; i++) {
            if (tag == this.tags[i]) {
                hit = true;
                updateMatrix(i);
                break;
            }
        }
        if (!hit) {
            for (int i = 0; i < this.matrix.length; i++) {
                if (allZero(this.matrix[i])) {
                    updateMatrix(i);
                    this.tags[i] = tag;
                    break;
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
        for (int i = 0; i < this.matrix.length; i++) {
            this.matrix[index][i] = 1;
            this.matrix[i][index] = 0;
        }
    }
}
