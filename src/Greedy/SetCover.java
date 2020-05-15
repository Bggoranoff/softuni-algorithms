package Greedy;
import java.util.*;

public class SetCover {
    public static void main(String[] args) {

    }
    static ArrayList<int[]> chooseSets(List<int[]> sets, ArrayList<Integer> universe) {
        ArrayList<int[]> result = new ArrayList<>();
        while(universe.size() > 0) {
            sets.sort(Comparator.comparingInt(a -> Arrays.stream(a).filter(universe::contains).toArray().length));
            int[] currentSet = sets.get(0);
            result.add(currentSet);
            sets.remove(currentSet);
            for (int number : currentSet) {
                universe.remove(number);
            }
        }
        return result;
    }
}
