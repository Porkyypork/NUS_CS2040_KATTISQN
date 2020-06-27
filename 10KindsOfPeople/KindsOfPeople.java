import java.util.*;
import java.io.*;


public class KindsOfPeople {

    public static int[][] map;
    public static int[][] visited;
    public static int[][] island;
    public static int sector = 1;
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int row = fio.nextInt();
        int col = fio.nextInt();
        map = new int[row][col];
        visited = new int[row][col];
        for (int i = 0; i < row; i++) {
            String lines = fio.next();    
            for (int j = 0; j < col; j++) {
                map[i][j] = Character.getNumericValue(lines.charAt(j));
            }
        }
        island  = new int[row][col];
        for(int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int mode = map[i][j];
                boolean flag = BFS(i,j,sector,mode);
                if(flag) {
                    sector++;
                } 
            }
        }
        int query = fio.nextInt();
        for (int i = 0; i < query; i++) {
            Pair input = new Pair(fio.nextInt() - 1, fio.nextInt() - 1);
            Pair output = new Pair(fio.nextInt() - 1, fio.nextInt() - 1);
            if (island[input.x][input.y] == island[output.x][output.y]) {
                if(map[input.x][input.y] == 0) {
                    fio.println("binary");
                }else {
                    fio.println("decimal");
                }
            } else{
                fio.println("neither");
            }
        }

        //for debug
        // for(int i = 0;i< island.length; i++) {
        //     for (int j = 0 ;j < island[0].length; j++){
        //         System.out.print(island[i][j]);
        //     }
        //     System.out.println();
        // }
        
        fio.close();
    }

    public static boolean BFS(int startx, int starty, int sector, int mode) {            
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(startx,starty));
        if (island[startx][starty] != 0) {
            return false;
        }
        visited[startx][starty] = 1;
        island[startx][starty] = sector;
        while (!queue.isEmpty()) {
            Pair start = queue.poll();
            if (neighbours(start.x + 1, start.y, mode)) { // search down
                visited[start.x + 1][start.y] = 1;
                island[start.x + 1][start.y] = sector;
                queue.add(new Pair(start.x + 1, start.y));
            }
            if (neighbours(start.x - 1, start.y, mode)) { // search up
                visited[start.x - 1][start.y] = 1;
                island[start.x -1][start.y] = sector;
                queue.add(new Pair(start.x - 1, start.y));
            }
            if (neighbours(start.x, start.y + 1, mode)) { // search right
                visited[start.x][start.y + 1] = 1;
                island[start.x][start.y + 1] = sector;
                queue.add(new Pair(start.x, start.y + 1));
            }
            if (neighbours(start.x, start.y - 1, mode)) { // search left
                visited[start.x][start.y - 1] = 1;
                island[start.x][start.y -1 ] = sector;
                queue.add(new Pair(start.x, start.y - 1));
            }
        }
        return true;
    }

    public static boolean neighbours(int x, int y, int mode) {
        int rows = map.length;
        int col = map[0].length;
        if(x > rows - 1 || y > col -1 || x < 0 || y < 0 || visited[x][y] != 0 || map[x][y] != mode) {
            return false;
        } 
        return true;
    }
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

class Pair {
    public int x;
    public int y;
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Pair p) {
        return (this.x == p.x && this.y == p.y);
    }
}
