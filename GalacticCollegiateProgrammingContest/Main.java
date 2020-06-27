package GalacticCollegiateProgrammingContest;

import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] agrs) {
        FastIO fio = new FastIO();
        int teams = fio.nextInt();
        int problems = fio.nextInt();
        boolean[] visited = new boolean[teams + 1];
        Team[] allTeams = new Team[teams + 1];
        allTeams[1] = new Team(1, 0, 0);
        BST tree = new BST();
        tree.insert(allTeams[1]);
        visited[1] = true;
        for (int i = 0; i < problems; i++) {
            int id = fio.nextInt();
            int pen = fio.nextInt();
            if (visited[id] == false) {
                visited[id] = true;
                allTeams[id] = new Team(id, pen, 1);
                tree.insert(allTeams[id]);
            } else {
                Team temp = allTeams[id];
                tree.delete(temp);
                allTeams[id] = temp.update(pen);
                tree.insert(allTeams[id]);
            }
            fio.println(tree.upSize(tree.root) - tree.upRank(allTeams[1]) + 1);
        }
        fio.close();
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