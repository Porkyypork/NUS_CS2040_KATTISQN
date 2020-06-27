package KattisQuest;

import java.util.*;

class Kattis {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        ArrayList<Long> query = new ArrayList<>();
        TreeSet<Quest> tree = new TreeSet<>(new MyComp());
        int id = 1;
        for (int i = 0; i < num; i++) {
            String input = sc.next();
            if (input.charAt(0) == 'a') {
                int energyreq = sc.nextInt();
                int gold = sc.nextInt();
                tree.add(new Quest(id, energyreq, gold));
                id++;
            } else {
                int energyq = sc.nextInt();
                long gold = 0L;
                while (true) {
                    Quest q = tree.ceiling(new Quest(Integer.MAX_VALUE, energyq, Integer.MAX_VALUE));
                    if (q == null || energyq - q.energy < 0) {
                        break;
                    } else {
                        tree.remove(q);
                        energyq -= q.energy;
                        gold += q.gold;
                    }
                }
                query.add(gold);
            }
        }
        for (long e : query) {
            System.out.println(e);
        }
        sc.close();
    }
}

class MyComp implements Comparator<Quest> {
    @Override
    public int compare(Quest q1, Quest q2) {
        if (q1.energy == q2.energy && q2.gold == q1.gold) {
            return q2.id - q1.id;
        } else if (q1.energy == q2.energy) {
            return q2.gold - q1.gold;
        }
        return q2.energy - q1.energy;
    }
}