import java.util.*;
import java.io.*;


class HumanCannonBall {

    public static double[] time;
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        Coord first = new Coord(fio.nextDouble(), fio.nextDouble());
        Coord end = new Coord(fio.nextDouble(), fio.nextDouble());
        int numC = fio.nextInt();
        double[][] adj = new double[numC+2][numC+2];
        Coord[] cannons = new Coord[numC + 2];
        for(int i = 0; i <= numC + 1; i++) {
            if (i == cannons.length - 1) {
                cannons[i] = end;
            } else if (i == 0) {
                cannons[0] = first;
            } else {
                cannons[i] = new Coord(fio.nextDouble(), fio.nextDouble());
            }
        }
        for (int i = 0; i <= numC + 1; i++) {
            for (int j = i; j <= numC + 1; j++) {
                if (i == 0){
                    adj[i][j] = adj[j][i] = cannons[i].runtimeTo(cannons[j]);
                } else {
                    adj[i][j] = adj[j][i] = cannons[i].runFlyto(cannons[j]);
                }
            }
        }

        time = new double[adj.length];
        for (int i = 1; i < time.length; i++) {
            time[i] = Double.MAX_VALUE;
        }
        
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(0,0));
        for (int i = 1; i < time.length; i++) {
            pq.add(new Pair(time[i], i));
        }
        while (!pq.isEmpty()) {
            Pair temp = pq.poll();
            int vert = temp.id;
            double t = temp.t;
            if(t == time[vert]) {
                for (int i = 0; i < adj.length; i++) {
                    double weight = adj[vert][i];
                    if(i != vert && time[i] > time[vert] + weight) {
                        time[i] = time[vert] + weight;
                        pq.add(new Pair(time[i], i));
                    }
                }
            }
        }

        // for (int i = 0; i< time.length; i++) {
        //     fio.println(time[i]);
        // }
        fio.println(String.format("%.6f", time[time.length - 1]));
        // for (int i = 0; i< adj.length; i++) {
        //     for(int j = 0; j < adj.length; j++) {
        //         fio.print(adj[i][j] + " ");
        //     }
        //     fio.println();
        // }
        fio.close();
    }
}

class Pair implements Comparable<Pair>{
    public double t;
    public int id;
    public Pair(double t, int id) {
        this.t = t;
        this.id = id;
    }

    public int compareTo(Pair p) {
        if (this.t != p.t) {
            return (int) (this.t - p.t);
        } 
        return this.id - p.id;
    }
}
class Coord{
    public double x;
    public double y;
    public Coord(double x, double y) {
        this.x = x;
        this.y= y;
    }

    public double distanceTo(Coord p) {
        return Math.sqrt(Math.pow(this.x-p.x,2) + Math.pow(this.y - p.y, 2));
    }

    public double runtimeTo(Coord p) {
        return this.distanceTo(p) / 5;
    } 

    public double runFlyto(Coord p) {
        if (this.distanceTo(p) == 0) {
            return 0;
        }
        if (this.distanceTo(p) < 50) {
            return Math.min((2 + (50 - this.distanceTo(p)) / 5), this.distanceTo(p) / 5) ;
        }
        return 2 + ((this.distanceTo(p) - 50) / 5);
    }

    public String toString() {
        return "(" + this.x +"," + this.y +")";
    }
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
