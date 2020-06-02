package Dynamic;
import java.util.*;

public class LongestCommonSubsequence {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String first = s.nextLine(), second = s.nextLine();
        int[][] lcs = new int[first.length() + 1][second.length() + 1];
        for(int row = 1; row <= first.length(); row++) {
            for(int col = 1; col <= second.length(); col++) {
                int up = lcs[row - 1][col];
                int left = lcs[row][col - 1];
                lcs[row][col] = first.charAt(row - 1) == second.charAt(col - 1)
                        ? Math.max(lcs[row - 1][col - 1] + 1, Math.max(up, left))
                        : Math.max(up, left);
            }
        }
        System.out.println(lcs[first.length()][second.length()]);
        int currentRow = first.length(), currentCol = second.length();
        ArrayList<Character> result = new ArrayList<>();
        while(currentRow > 0 || currentCol > 0) {
            if(first.charAt(currentRow - 1) == second.charAt(currentCol - 1) && lcs[currentRow][currentCol] - 1 == lcs[currentRow - 1][currentCol -1]) {
                result.add(first.charAt(currentCol - 1));
                currentRow--;
                currentCol--;
                continue;
            }
            if(lcs[currentRow - 1][currentCol] == lcs[currentRow][currentCol - 1]) currentRow--;
            else currentCol--;
        }
        Collections.reverse(result);
        result.forEach(System.out::print);
    }
}
