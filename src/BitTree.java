class BitTree {

    // Array representation of binary tree
    private byte[] array;

    public BitTree(int setSize) {
        this.array = new byte[setSize - 1];
    }

    public int getNextIndex() {
        return getNextIndex(0, 0);
    }

    private int getNextIndex(int index, int accumulator) {
        accumulator *= 2;
        accumulator += array[index];

        // If array[index] is 0 we will go left, 1 we will go right
        int next = (index * 2) + 1 + array[index];
        if (next >= this.array.length) {
            update(accumulator);
            return accumulator;
        } else {
            return getNextIndex(next, accumulator);
        }
    }

    public void update(int index) {
        updateRec(((this.array.length + 1) / 2) - 1 + (index / 2));
    }

    private void updateRec(int index) {
        if (index < this.array.length) {
            this.array[index] = (this.array[index] == 0) ? (byte) 1 : 0;
        }
        if (index == 0) {
            return;
        } else {
            updateRec((index - 1) / 2);
        }
    }
}
