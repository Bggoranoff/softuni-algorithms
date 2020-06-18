package ExamPrep;
import java.util.*;

public class SchoolTeams {
    static ArrayList<String> girlsCombinations = new ArrayList<>(), boysCombinations = new ArrayList<>();
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String[] girls = s.nextLine().split(", "), boys = s.nextLine().split(", ");
        getCombinations(girls, 0, 0, 3, new String[3]);
        getCombinations(boys, 0, 0, 2, new String[2]);
        for (String girlsCombination : girlsCombinations) {
            for (String boysCombination : boysCombinations) {
                printTeam(girlsCombination, boysCombination);
            }
        }
    }
    static void printTeam(String girls, String boys) {
        System.out.println(girls + ", " + boys);
    }
    static void getCombinations(String[] arr, int index, int start, int k, String[] combs) {
        if(index >= k) addCombination(combs, k);
        else {
            for(int i = start; i < arr.length; i++) {
                combs[index] = arr[i];
                getCombinations(arr, index + 1, i + 1, k, combs);
            }
        }
    }
    static void addCombination(String[] comb, int k) {
        if(k == 3) girlsCombinations.add(String.join(", ", comb));
        else boysCombinations.add(String.join(", ", comb));
    }
}
