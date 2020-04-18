package lab11.graphs;

import edu.princeton.cs.algs4.Stack;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private Maze maze;
    private boolean targetFound;

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        s = maze.xyTo1D(1, 1);
        edgeTo[s] = s;
    }

    @Override
    public void solve() {
        dfs(10);
    }

    private void dfs(int v) {
        Stack<Integer> fringe = new Stack<>();

        marked[v] = true;
        announce();
        fringe.push(v);

        boolean targetFound = false;
        while(!fringe.isEmpty()){
            if(targetFound) {
                break;
            }
            int i = fringe.pop();
            for(int w: maze.adj(i)){
                if(!marked[w]){
                    edgeTo[w] = i;
                    announce();
                    marked[w] = true;
                    fringe.push(w);
                }
                for(int u: maze.adj(w)) {
                    if (marked[u] && (edgeTo[w] != u)) {
                        targetFound = true;
                    }
                }
            }
        }
    }

}

