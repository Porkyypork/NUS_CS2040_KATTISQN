import java.util.*;
import java.io.*;

public class Teque {
    private static HashMap<Integer, Integer> front = new HashMap<>();
    private static HashMap<Integer, Integer> back = new HashMap<>();
    private static int minFront = 0;
    private static int maxFront = 0;
    private static int minBack = 0;
    private static int maxBack = 0;

    public static void main(String[] args) {

        FastIO fio = new FastIO();
        int count = fio.nextInt();
        for (int i = 0; i < count; i++) {
            String command = fio.next();
            int num = fio.nextInt();
            if (command.charAt(0) == 'g') {
                fio.println(get(num));
            } else {
                switch (command.charAt(5)) {
                    case 'b':
                        pushBack(num);
                        break;
                    case 'f':
                        pushFront(num);
                        break;
                    case 'm':
                        pushMiddle(num);
                        break;
                }
            }
        }
        fio.close();
    }

    public static void pushBack(int num) {
        back.put(maxBack, num);
        maxBack++;
        balance();
    }

    public static void pushFront(int num) {
        minFront--;
        front.put(minFront, num);
        balance();
    }

    public static void pushMiddle(int num) {
        if (front.size() == back.size()) {
            minBack--;
            back.put(minBack, num);
        } else {
            front.put(maxFront, back.get(minBack));
            maxFront++;
            back.put(minBack, num);
        }
    }

    public static int get(int index) {
        if (index < (maxFront - minFront)) {
            return front.get(index + minFront);
        } else {
            index -= (maxFront - minFront);
            return back.get(index + minBack);
        }
    }

    public static void balance() {
        if (front.size() < back.size() - 1) {
            front.put(maxFront, back.get(minBack));
            maxFront++;
            back.remove(minBack);
            minBack++;
        } else if (front.size() > back.size()) {
            minBack--;
            maxFront--;
            back.put(minBack, front.get(maxFront));
            front.remove(maxFront);
        }
    }
}

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
