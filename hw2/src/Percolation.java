import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    // TODO: Add any necessary instance variables.
    private int[][] grids;
    private int size, count_open = 0, count_botfull = 0;
    private WeightedQuickUnionUF ids;

    public Percolation(int N) {
        // TODO: Fill in this constructor.
        if (N <= 0) {
            throw new IllegalArgumentException("size of Percolation should be postive integers");
        }
        size = N;
        grids = new int[size][size];
        ids = new WeightedQuickUnionUF(size*size); // 0 for virtual top site, size + 1 for bottom site
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grids[i][j] = 0;// 0 for closed
                if (i == 0) {
                    ids.union(xyto1D(0,0),xyto1D(i,j));
                } else if (i == size -1) {
                    //ids.union(xyto1D(size - 1,0),xyto1D(i,j));
                }
            }
        }
    }

    public void open(int row, int col) {
        // TODO: Fill in this method.
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IndexOutOfBoundsException("input indices are out of the bounds");
        }
        grids[row][col] = 1; //1 for open
        count_open++;
    }

    public boolean isOpen(int row, int col) {
        // TODO: Fill in this method.
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IndexOutOfBoundsException("input indices are out of the bounds");
        }
        return grids[row][col] == 1;
    }

    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IndexOutOfBoundsException("input indices are out of the bounds");
        }
        //
        if (row > 0 && row < size - 1) {
            if (this.isOpen(row, col)) {
                // connect adjacent open cells
                if (this.isOpen(row - 1, col)) {
                    ids.union(xyto1D(row, col), xyto1D(row - 1, col));//connect top open cell
                }
                if (this.isOpen(row + 1, col)) {
                    ids.union(xyto1D(row, col), xyto1D(row + 1, col));//connect top open cell
                }
                if (col != 0) {
                    if (this.isOpen(row, col - 1)) {
                        ids.union(xyto1D(row, col), xyto1D(row, col - 1));//connect left open cell
                    }
                }
                if (col != size - 1) {
                    if (this.isOpen(row, col + 1)) {
                        ids.union(xyto1D(row, col), xyto1D(row, col + 1));//connect right open cell
                    }
                }
                // check whether the cell is full or not
                if (ids.connected(0, xyto1D(row, col))) {
                        return true;
                }
            }
        } else if (row == 0) { // for first row
            if (this.isOpen(row, col)) {
                if (size > 1) {
                    if (this.isOpen(row + 1, col)) {
                        ids.union(xyto1D(row, col), xyto1D(row + 1, col));// connect open cells under it
                    }
                }
                return true;
            }
        } else { // for last row
            if (this.isOpen(row, col)) {
                if (this.isOpen(row - 1, col)) {
                    ids.union(xyto1D(row, col), xyto1D(row - 1, col));//connect top open cell
                }
                if (col != 0) {
                    if (this.isOpen(row, col - 1)) {
                        ids.union(xyto1D(row, col), xyto1D(row, col - 1));//connect left open cell
                    }
                }
                if (col != size - 1) {
                    if (this.isOpen(row, col + 1)) {
                        ids.union(xyto1D(row, col), xyto1D(row, col + 1));//connect right open cell
                    }
                }
                if (ids.connected(xyto1D(row, col), 0)) {
                    count_botfull++;
                    return true;
                }
            }
        }
        return false;
    }

    public int numberOfOpenSites() {
        // TODO: Fill in this method.
        return count_open;
    }

    public boolean percolates() {
        // TODO: Fill in this method.
        if (size == 1 && this.isOpen(0, 0) ) {
            return true;
        } else if (count_botfull > 0) {
            return true;
        }
        return false;
    }

    public int xyto1D(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IndexOutOfBoundsException("input indices are out of the bounds");
        }
        return row * size + col;
    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.

}
