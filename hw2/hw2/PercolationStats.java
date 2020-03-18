package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;


public class PercolationStats {
    private double[] percolationThreshold;
    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException("N and T must be greater than 0");
        }
        percolationThreshold = new double[T];
        for(int i = 0; i < T; i += 1) {
            int count = 0;
            Percolation sample = pf.make(N);
            while(!sample.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                if(!sample.isOpen(row, col)) {
                    sample.open(row, col);
                    count += 1;
                }
            }
            percolationThreshold[i] = (double) count / (N * N);
            //System.out.println(count);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(percolationThreshold);
    }

    public double stddev() {
        return StdStats.stddev(percolationThreshold);
    }

    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(percolationThreshold.length);
    }

    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(percolationThreshold.length);
    }


}
