import java.util.*;
import java.io.*;

class T9Spelling {
	public static void main(String[] args) {
		FastIO fio = new FastIO();
		int num = fio.nextInt();
		String[] cases = new String[num];
		for (int i = 0; i < num; i++) {
			String words = fio.nextLine();
			cases[i] = Convert(words);
		}
		for (int i = 0; i < cases.length; i++) {
			fio.println("Case #" + (i + 1) + ": " + cases[i]);
		}
		fio.close();
	}

	public static String Convert(String msg) {
		String[] alpha = { "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz", " " };
		String num_msg = "";
		for (int i = 0; i < msg.length(); i++) {// loop through msg
			for (int j = 0; j < alpha.length; j++) {// loop through array
				for (int k = 0; k < alpha[j].length(); k++) {// loop throught elements in array
					if (msg.charAt(i) == alpha[j].charAt(k)) {// searching for the same element
						if (num_msg.length() == 0) {
							for (int r = 0; r < k + 1; r++) {
								num_msg += (j + 2) % 10;
							}
						} else {
							if (num_msg.charAt(num_msg.length() - 1) == Character.forDigit((j + 2) % 10, 10)) {
								num_msg += " ";
							}
							for (int r = 0; r < k + 1; r++) {
								num_msg += (j + 2) % 10;

							}
						}
					}
				}
			}
		}
		return num_msg;
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
