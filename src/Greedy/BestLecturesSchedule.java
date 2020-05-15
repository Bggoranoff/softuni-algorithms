package Greedy;
import java.util.*;

public class BestLecturesSchedule {
    static private class Hour {
        int[] hours;
        String name;
        Hour(int[] hours, String name) {
            this.hours = hours;
            this.name = name;
        }

        public int[] getHours() {
            return hours;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            StringBuilder str = new StringBuilder();
            str.append(this.hours[0]);
            str.append("-");
            str.append(this.hours[1]);
            str.append(" -> ");
            str.append(this.name);
            return str.toString();
        }
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int lectures = Integer.parseInt(s.nextLine().split("\\s")[1]);
        ArrayList<Hour> hours = new ArrayList<>();
        int maxBegin = 0, minBegin = Integer.MAX_VALUE;
        for(int i = 0; i < lectures; i++) {
            String[] input = s.nextLine().split(": ");
            String name = input[0];
            int[] arr = Arrays.stream(input[1].split(" - ")).mapToInt(Integer::parseInt).toArray();
            hours.add(new Hour(arr, name));
            if(arr[0] > maxBegin) maxBegin = arr[0];
            if(arr[0] < minBegin) minBegin = arr[0];
        }
        ArrayList<Hour> possibleLectures = new ArrayList<>();
        hours.sort(Comparator.comparingInt(a -> a.getHours()[1]));
        int i = 0, hour = minBegin;
        while(hour <= maxBegin) {
            if(hour <= hours.get(i).getHours()[0]) {
                possibleLectures.add(hours.get(i));
                hour = hours.get(i).getHours()[1];
            }
            i++;
        }
        System.out.printf("Lectures (%d):\n", possibleLectures.size());
        for(Hour el : possibleLectures) {
            System.out.println(el.toString());
        }
    }
}
