import java.util.*;
import java.io.*;

class Ladice {
    public static void main(String[] args) {
      FastIO fio = new FastIO();
      int n = fio.nextInt();
      int drawers = fio.nextInt();
      UnionFind sets = new UnionFind(drawers + 1);
      for(int i = 0 ; i < n; i++) {
          int first = fio.nextInt();
          int second = fio.nextInt();     
        sets.unionSet(first, second);
        if(sets.space(first) < 0) {
          fio.println(String.format("SMECE"));
        } else{
          fio.println(String.format("LADICA"));
        }
      }
      fio.close();
    }
}

class UnionFind {                                              
  public int[] p;
  public int[] rank;
  public int numSets;
  public int[] space;

  public UnionFind(int N) {
    p = new int[N];
    rank = new int[N];
    numSets = N;
    space = new int[N];
    for (int i = 0; i < N; i++) {
      p[i] = i;
      rank[i] = 0;
      space[i] = 1;
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
    int x = findSet(i), y = findSet(j);
    if (!isSameSet(i, j)) { 
      numSets--; 
      // rank is used to keep the tree short
        if (rank[x] > rank[y]) {
            p[y] = x;
            if (space[x] < 0 || space[y] < 0){
              space[x] += space[y];
            }else{
            space[x] = space[y] + space[x] - 1;
            }
        } else { 
            p[x] = y;
            if (space[y] < 0 || space[x] < 0){
              space[y] += space[x];
            }else{
              space[y] = space[y] + space[x] - 1;
            }
            if (rank[x] == rank[y]) { 
                rank[y] = rank[y]+1; 
            }
      } 
    } else {
      if(space[x] < 0) {
        space[x] = -1;
      }else{
        space[x] -= 1;
    
      }
    } 
  }

public int space(int i) {
  int x = findSet(i);
  return space[x];
}

  public int numDisjointSets() { return numSets; }
}


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
