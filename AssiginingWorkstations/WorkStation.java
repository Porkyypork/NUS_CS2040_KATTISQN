package AssiginingWorkstations;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class WorkStation {
    public static void main (String[] args) {
        
        PriorityQueue<Researcher> pq = new PriorityQueue<>(11, new MyComparator());
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int min = sc.nextInt();
        int count = 0;
        PriorityQueue<Integer> stations = new PriorityQueue<>();
        for (int i = 0; i < num ; i++) {
            int arr = sc.nextInt();
            int stays = sc.nextInt();
            Researcher rs = new Researcher(arr, stays);
            pq.add(rs);
        }
        while(pq.size() > 0) {
            Researcher rs = pq.poll();

            while(stations.size() > 0 && stations.peek() + min< rs.getArr()) {
                stations.poll();
            }
            if(stations.size() == 0) {
                stations.add(rs.getArr() + rs.getStays());
            }else if (rs.getArr() < stations.peek()) {
                stations.add(rs.getArr() +rs.getStays());
            } else if(rs.getArr() <= stations.peek() + min) { 
                stations.poll();
                stations.add(rs.getArr() + rs.getStays());
                count++;
            }
        }
        System.out.println(count);    
        sc.close();    
    }
}

class MyComparator implements Comparator<Researcher> {
    @Override
    public int compare(Researcher r1, Researcher r2) {
        return r1.getArr() - r2.getArr();
    }
}

