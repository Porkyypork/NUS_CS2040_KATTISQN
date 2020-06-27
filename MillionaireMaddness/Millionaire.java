import java.util.*;
import java.io.*;

public class Millionaire {

    public static int[][] graph;
    public static int[][] visited;
    public static boolean reached = false;
    public static PriorityQueue<Trip> queue;
    public static int maxLadder = 0;

    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int row = fio.nextInt();
        int col = fio.nextInt();
        graph = new int[row][col];
        visited = new int[row][col];
        queue = new PriorityQueue<>();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                graph[i][j] = fio.nextInt();
            }
        }
        queue.offer(new Trip(0, 0, 0));

        while (!queue.isEmpty()) {
            Trip vert = queue.poll();
            visited[vert.i][vert.j] = 1;
            int curr = vert.weight;
            maxLadder = Math.max(maxLadder, curr);
            if (vert.i == row - 1 && vert.j == col - 1) {
                break;
            }
            add(vert.i - 1, vert.j, graph[vert.i][vert.j]);
            add(vert.i + 1, vert.j, graph[vert.i][vert.j]);
            add(vert.i, vert.j - 1, graph[vert.i][vert.j]);
            add(vert.i, vert.j + 1, graph[vert.i][vert.j]);
        }

        fio.println(maxLadder);
        fio.close();
    }

    public static void add(int i, int j, int prev) {
        int rows = graph.length;
        int col = graph[0].length;
        if (i < 0 || j < 0 || i >= rows || j >= col || visited[i][j] == 1) {
            return;
        }
        int curr = graph[i][j];
        queue.offer(new Trip(i, j, curr - prev));
    }
}

class Trip implements Comparable<Trip> {
    public int i;
    public int j;
    public int weight;

    public Trip(int first, int second, int weight) {
        this.i = first;
        this.j = second;
        this.weight = weight;
    }

    public String toString() {
        return "(" + this.weight + " " + this.i + " " + this.j + ")";
    }

    public int compareTo(Trip t) {
        return this.weight - t.weight;
    }
}

/**
 * Fast I/O
 * 
 * @source https://www.geeksforgeeks.org/fast-io-in-java-in-competitive-programming/
 */
class FastIO extends PrintWriter {
    BufferedReader br;
    StringTokenizer st;

    public FastIO() {
        super(new BufferedOutputStream(System.out));
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    int nextInt() {
        return Integer.parseInt(next());
    }

    long nextLong() {
        return Long.parseLong(next());
    }

    double nextDouble() {
        return Double.parseDouble(next());
    }

    String nextLine() {
        String str = "";
        try {
            str = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}
