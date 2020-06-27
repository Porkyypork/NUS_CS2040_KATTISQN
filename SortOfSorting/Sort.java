import java.io.*;
import java.util.*;

class Sort {
	public static void main(String[] args) {
		FastIO fio = new FastIO();
		int num;
		do {
			num = fio.nextInt();
			if (num > 0) {
				String[] strings = new String[num];
				for (int i = 0; i < num; i++) {
					strings[i] = fio.next();
				}
				Arrays.sort(strings, new NameComparator());
				for (String string : strings) {
					fio.println(string);
				}
			}
			fio.println();

		} while (num > 0);
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

class NameComparator implements Comparator<String> {
	public int compare(String name1, String name2) {
		return name1.substring(0, 2).compareTo(name2.substring(0, 2));
	}
}
