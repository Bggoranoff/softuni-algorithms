package Dynamic;
import java.util.*;

public class MinimumEditDistance {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int replace = Integer.parseInt(s.nextLine()), insert = Integer.parseInt(s.nextLine()),
                delete = Integer.parseInt(s.nextLine());
        String first = s.nextLine(), second = s.nextLine();
        int[][] dp = new int[first.length() + 1][second.length() + 1];
        for(int i = 1; i <= second.length(); i++)
            dp[0][i] = dp[0][i - 1] + insert;
        for(int i = 1; i <= first.length(); i++)
            dp[i][0] = dp[i - 1][0] + delete;
        for(int i = 1; i <= first.length(); i++) {
            for(int j = 1; j <= second.length(); j++) {
                if(first.charAt(i - 1) == second.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else {
                    int costDelete = dp[i - 1][j] + delete, costInsert = dp[i][j - 1] + insert,
                            costReplace = dp[i - 1][j - 1] + replace;
                    dp[i][j] = Math.min(costInsert, Math.min(costDelete, costReplace));
                }
            }
        }
        System.out.println(dp[first.length()][second.length()]);
    }
}
