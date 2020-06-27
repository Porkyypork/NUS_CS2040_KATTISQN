import java.util.*;
import java.io.*;

class WeakVertices {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        while (true) {
            int num = fio.nextInt();
            if (num == -1) {
                break;
            }
            int[][] graph = new int[num][num];
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    graph[i][j] = fio.nextInt();
                }
            }
            ArrayList<IntegerPair> list = new ArrayList<>();
            for (int i = 0; i < num; i++) {
                for (int j = i; j < num; j++) {
                    if (graph[i][j] == 1) {
                        list.add(new IntegerPair(i, j));
                    }
                }
            }
            int[] tri = new int[num];
            for (int i = 0; i < list.size() - 1; i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    if (list.get(i).first == list.get(j).first) {
                        IntegerPair temp = new IntegerPair(list.get(i).second, list.get(j).second);
                        for (int k = 0; k < list.size(); k++) {
                            if (list.get(k).equals(temp)) {
                                tri[list.get(i).first] = tri[list.get(i).second] = tri[list.get(j).second] = 1;
                            }
                        }
                    }
                }
            }
            for (int i = 0; i < tri.length; i++) {
                if (tri[i] == 0) {
                    fio.print(i + " ");
                }
            }
            fio.println();
        }
        fio.close();
    }
}

class IntegerPair {
    int first;
    int second;

    public IntegerPair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public boolean equals(IntegerPair pair) {
        return (this.first == pair.first) && (this.second == pair.second);
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", this.first, this.second);
    }
}

class FastIO extends PrintWriter

{
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
