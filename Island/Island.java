import java.util.*;
import java.io.*;
class Island {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int rows = fio.nextInt();
        int col = fio.nextInt();
        int[][] visitedL = new int[rows][col];
        char[][] graph = new char[rows][col];
        for (int i = 0; i < rows; i++) {
            String temp = fio.next();
            for (int j = 0; j < col; j++) {
                graph[i][j] = temp.charAt(j);
            }
        }
        for(int i = 0; i < graph.length; i ++) {
            for (int j = 0; j < graph[0].length; j++) {
                if(graph[i][j] == 'L' && visitedL[i][j] == 0) {
                    visitedL[i][j] = 1;
                    convert(graph, visitedL, i +1, j);
                    convert(graph, visitedL, i -1, j);
                    convert(graph, visitedL, i, j+1);
                    convert(graph, visitedL, i,  j-1);
                }
            }
        }
            
        int[][] visited = new int[rows][col];
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < col; j++) {
                if(graph[i][j] == 'L' && visited[i][j] == 0) {
                    visited[i][j] = 1;
                    count++;
                    //System.out.println(i + " " + j);
                    if (i == rows - 1 && j == col - 1) {
                        break;
                    } else if(i == rows - 1) {
                        search(graph,visited, i , j + 1);
                    } else if(j == col -1) {
                        search(graph,visited, i + 1 , j);
                    } else if(i == 0 && j == 0) {
                        search(graph,visited, i , j + 1); 
                        search(graph,visited, i + 1 , j);
                    } else if(i == 0) {
                        search(graph,visited, i , j + 1);
                        search(graph,visited, i + 1 , j);
                    } else {
                        search(graph,visited, i , j + 1);
                        search(graph,visited, i + 1 , j);
                    }
                }
            }
        }
        // for (int i = 0; i < rows; i++) {
        //     for (int j = 0; j < col; j++) {
        //         System.out.print(graph[i][j] +" ");
        //     }   
        //         System.out.println();
        // }
        fio.println(count);
        fio.close();
    }
    public static void convert(char[][] graph, int[][] visitedL, int i, int j) {
        int rows = graph.length;
        int col = graph[0].length;
        if(i >= rows || j >= col || i < 0 || j< 0 || graph[i][j] == 'W' || visitedL[i][j] == 1) {
            return;
        } 
        graph[i][j] = 'L';
        visitedL[i][j] = 1;
        convert(graph, visitedL,  i + 1,j);
        convert(graph, visitedL, i, j + 1);
        convert(graph, visitedL, i - 1,j);
        convert(graph, visitedL, i, j - 1);          
    }

    public static void search(char[][] graph, int[][] visited, int i, int j) {
        int rows = graph.length;
        int col = graph[0].length;
        if(i >= rows || j >= col || i < 0 || j< 0 || graph[i][j] != 'L' || visited[i][j] == 1) {
            return;
        } 
        visited[i][j] = 1;
        search(graph, visited, i + 1,j);
        search(graph, visited, i, j + 1);
        search(graph, visited, i - 1,j);
        search(graph, visited, i, j - 1);          
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
