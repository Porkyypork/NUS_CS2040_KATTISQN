import java.util.*;
import java.io.*;


public class LostMap {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        ArrayList<IntegerTrip> edgeList = new ArrayList<>();
        ArrayList<IntegerPair> completed = new ArrayList<>();
        int num = fio.nextInt();
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                int weight = fio.nextInt();
                if(j > i){
                    edgeList.add(new IntegerTrip(weight, i , j));
                }
            }
        }

        Collections.sort(edgeList);

        //print edgeList
        // for (int i = 0; i< edgeList.size(); i++) {
        //     fio.println(edgeList.get(i));
        // }

        UnionFind set = new UnionFind(num);
        for (int i = 0; i < edgeList.size(); i++) {
            if(completed.size() == num - 1) {
                break;
            }
            IntegerTrip t = edgeList.get(i);
            int u = t.u;
            int v = t.v;
            if (!set.isSameSet(u, v)) {
                completed.add(new IntegerPair(u,v));
                set.unionSet(u, v);
            }
        }
        Collections.sort(completed);
        for (int i = 0; i < completed.size(); i++) {
            fio.println(completed.get(i));
        } 
        fio.close();
    }
}

class IntegerTrip implements Comparable<IntegerTrip>{
    public int u;
    public int v; 
    public int w;
    public IntegerTrip(int w, int u, int v) {
        this.u = u;
        this.v = v;
        this.w = w;
    }

    public int compareTo(IntegerTrip t) {
        if(this.w != t.w) {
            return this.w - t.w;
        } else if (this.u != t.u) { 
            return this.u - t.u; 
        }
        return this.v - t.v;
    }

    public String toString() {
        return this.w + " " + this.u + " " + this.v;
    }
}

class IntegerPair implements Comparable<IntegerPair>{
    public int u;
    public int v;
    public IntegerPair(int u, int v) {
        this.u = u;
        this.v = v;
    }

    public int compareTo(IntegerPair p) {
        if (this.u != p.u) {
            return this.u - p.u;
        }
        return this.v-p.v;
    }

    public String toString() {
        return (this.u+1) + " " + (this.v + 1);
    }
}

class UnionFind {
    public int[] p;
    public int[] rank;
    public int[] setSize;
    public int numSets;
  
    public UnionFind(int N) {
      p = new int[N];
      rank = new int[N];
      setSize = new int[N];
      numSets = N;
      for (int i = 0; i < N; i++) {
        p[i] = i;
        rank[i] = 0;
        setSize[i] = 1;
      }
    }
  
    public int findSet(int i) { 
      if (p[i] == i) return i;
      else {
        p[i] = findSet(p[i]);
        return p[i]; 
      } 
    }
  
    public Boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }
  
    public void unionSet(int i, int j) { 
      if (!isSameSet(i, j)) { 
        numSets--; 
        int x = findSet(i), y = findSet(j);
        // rank is used to keep the tree short
        if (rank[x] > rank[y]) { 
            p[y] = x; 
            setSize[x] = setSize[x] + setSize[y]; 
        }
        else { 
            p[x] = y; 
            setSize[y] = setSize[x] + setSize[y];
          if (rank[x] == rank[y]) 
            rank[y] = rank[y]+1; 
        } 
      } 
    }
  
    public int numDisjointSets() { return numSets; }
  
    public int sizeOfSet(int i) { return setSize[findSet(i)]; }
  }

/**
 * Fast I/O
 * @source https://www.geeksforgeeks.org/fast-io-in-java-in-competitive-programming/
 */
class FastIO extends PrintWriter
{
    BufferedReader br;
    StringTokenizer st;

    public FastIO()
    {
        super(new BufferedOutputStream(System.out));
        br = new BufferedReader(new
                InputStreamReader(System.in));
    }

    String next()
    {
        while (st == null || !st.hasMoreElements())
        {
            try
            {
                st = new StringTokenizer(br.readLine());
            }
            catch (IOException  e)
            {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    int nextInt()
    {
        return Integer.parseInt(next());
    }

    long nextLong()
    {
        return Long.parseLong(next());
    }

    double nextDouble()
    {
        return Double.parseDouble(next());
    }

    String nextLine()
    {
        String str = "";
        try
        {
            str = br.readLine();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return str;
    }
}
