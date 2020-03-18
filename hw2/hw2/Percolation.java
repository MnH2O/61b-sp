package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import org.junit.Test;
import static org.junit.Assert.*;

public class Percolation {
    private boolean[][] loc;
    private WeightedQuickUnionUF unionF;
    private WeightedQuickUnionUF unionF_virtual;
    private int openSites;
    // create the N-by-N grid with all sites initially blocked
    public Percolation(int N) {
        if(N <= 0) {
            throw new IllegalArgumentException("N cannot be less than or equal to 0!");
        }
        openSites = 0;
        loc = new boolean[N][N];
        unionF = new WeightedQuickUnionUF(N * N + 1); // only the virtual top site
        unionF_virtual = new WeightedQuickUnionUF(N * N + 2);
        // plus 2 means that there is a virtual top site and a virtual bottom site
        for(int i = 0; i < N; i += 1) {
            for(int j = 0 ; j < N; j+= 1) {
                loc[i][j] = false;
            }
        }
    }

    // open the site (row, col) if it is not open
    public void open(int row, int col) {
        if(row < 0 | row >= loc.length | col < 0 | col >= loc.length) {
            throw new IndexOutOfBoundsException("Index out of bounds!");
        }
        if(!loc[row][col]) {
            loc[row][col] = true;
        }

        openSites += 1;
        // union this item with the virtual sites if these items are at top or bottom
        if(row == 0 | row == loc.length - 1){
            connectToVirtualSite(row, col);
        }
        // union this item with surrounding items (if they are open)
        connect(row, col);
    }

    private boolean checkLeft(int row, int col) {
        if (col == 0)
            return false;
        else {
            return isOpen(row, col - 1);
        }
    }

    private boolean checkRight(int row, int col) {
        if (col == loc.length - 1)
            return false;
        else {
            return isOpen(row, col + 1);
        }
    }

    private boolean checkUpper(int row, int col) {
        if (row == 0)
            return false;
        else {
            return isOpen(row - 1, col);
        }
    }

    private boolean checkLower(int row, int col) {
        if (row == loc.length - 1)
            return false;
        else {
            return isOpen(row + 1, col);
        }
    }

    private void connect(int row, int col){
        int self = xyTo1D(row, col);
        int left = xyTo1D(row, col - 1);
        int right = xyTo1D(row, col + 1);
        int upper = xyTo1D(row - 1, col);
        int lower = xyTo1D(row + 1, col);

        if (checkLeft(row, col)) {
            unionF.union(self, left);
            unionF_virtual.union(self, left);
        }
        if (checkRight(row, col)) {
            unionF.union(self, right);
            unionF_virtual.union(self, right);
        }
        if (checkUpper(row, col)) {
            unionF.union(self, upper);
            unionF_virtual.union(self, upper);
        }
        if (checkLower(row, col)) {
            unionF.union(self, lower);
            unionF_virtual.union(self, lower);
        }
    }

    private void connectToVirtualSite(int row, int col) {
        int self = xyTo1D(row, col);
        int N = loc.length;
        if(row == 0) {
            unionF.union(self, N * N);
            unionF_virtual.union(self, N * N);
        }
        if(row == N - 1) {
            unionF_virtual.union(self, N * N + 1);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if(row < 0 | row >= loc.length | col < 0 | col >= loc.length) {
            throw new IndexOutOfBoundsException("Index out of bounds!");
        }
        return loc[row][col];
    }

    // helper method
    private int xyTo1D(int r, int c) {
        return (r * loc.length) + c;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if(row < 0 | row >= loc.length | col < 0 | col >= loc.length) {
            throw new IndexOutOfBoundsException("Index out of bounds!");
        }
        int self = xyTo1D(row, col);
        int N = loc.length;
        if(unionF.connected(N * N, self)) {
            return true;
        }
        return false;
    }

    // number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // check if the system percolate
    public boolean percolates() {
        int N = loc.length;
        return unionF_virtual.connected(N * N, N * N + 1);
    }


    public static void main(String[] args) {
        Percolation test = new Percolation(4);
        test.open(0,0);
        test.open(1,0);
        test.open(2,0);
        test.open(3,0);
        test.open(3,2);
        test.open(2,2);
        System.out.println(test.isFull(3,2));
        System.out.println(test.isFull(2,2));
        System.out.println(test.isFull(3,0));
        System.out.println(test.percolates());
    }
}

