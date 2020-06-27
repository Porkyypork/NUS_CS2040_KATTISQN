import java.util.*;
import java.io.*;

class Train {
	public static void main(String[] args) {
		FastIO fio = new FastIO();
		int capacity = fio.nextInt();
		int stations = fio.nextInt();
		boolean[] possible = new boolean[stations];
		Arrays.fill(possible, true);
		int total = 0;
		for (int i = 0; i < stations; i++) {
			int left = fio.nextInt();
			int enter = fio.nextInt();
			int wait = fio.nextInt();
			if ((i == 0 && left != 0) || (i == stations - 1 && wait != 0)) {
				possible[i] = false;
			}
			total -= left;
			total += enter;
			if (total > capacity || total < 0 || (total != capacity && wait != 0)) {
				possible[i] = false;
			}
			if (i == stations - 1 && total > 0) {
				possible[i] = false;
			}
		}
		for (int i = 0; i <= possible.length; i++) {
			if (i == possible.length) {
				fio.println("possible");
			} else if (!possible[i]) {
				fio.println("impossible");
				break;
			}
		}
		fio.close();
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
