import java.util.*;

class Conformity{
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int max = 0;
        int maxCount =0;
        int[] courses = new int[5];
        for (int i = 0; i < num; i++) {
            String string = "";
            for (int j =0; j< 5; j++) {
                courses[j] = sc.nextInt();
            }
            Arrays.sort(courses);
            for(int k =0; k< 5 ;k++) {
                string += courses[k];
            }
            if (map.containsKey(string)) {
                int value = map.get(string);
                map.replace(string, value +1);
            } else {
                map.put(string, 1);
            }
        }
        Integer[] count = map.values().toArray(new Integer[0]);
        for (int i =0; i < count.length; i++) {
            max = Math.max(max, count[i]);
        }
        for (int i =0; i< count.length; i++) {
            if (count[i] == max) {
                maxCount += count[i];
            }
        }
        
        System.out.println(maxCount);
        sc.close();
    }
}