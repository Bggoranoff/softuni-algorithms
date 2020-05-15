package Greedy;
import java.util.*;

public class ProcessorScheduling {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int length = Integer.parseInt(s.nextLine().split("\\s")[1]);
        ArrayList<int[]> tasks = new ArrayList<>();
        for(int i = 0; i < length; i++) {
            String[] input = s.nextLine().split("\\s-\\s");
            tasks.add(new int[] {Integer.parseInt(input[0]), Integer.parseInt(input[1]), i + 1});
        }
        tasks.sort((a, b) -> Integer.compare(b[0], a[0]));
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < length; i++) {
            if(tasks.get(i)[1] > max) max = tasks.get(i)[1];
        }
        ArrayList<Integer> optimalSchedule = new ArrayList<>();
        int i = 0, optimalValue = 0, step = 0;
        while(step < max) {
            if(step < tasks.get(i)[1]) {
                optimalSchedule.add(tasks.get(i)[2]);
                optimalValue += tasks.get(i)[0];
                step++;
            }
            i++;
        }
        for(int j = 0; j < optimalSchedule.size(); j++) {
            if(j == optimalSchedule.size() - 1) System.out.printf("%d", optimalSchedule.get(j));
            else System.out.printf("%d -> ", optimalSchedule.get(j));
        }
        System.out.printf("\nTotal value: %d", optimalValue);
    }
}
