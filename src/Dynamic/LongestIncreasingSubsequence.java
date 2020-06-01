package Dynamic;
import java.util.*;

public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int[] seq = Arrays.stream(s.nextLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
        System.out.println(calculateLIS(seq));
    }
    static int calculateLIS(int[] seq) {
        int[] len = new int[seq.length];
        for(int i = 0; i < seq.length; i++) {
            len[i] = 1;
            for(int j = 0; j < i; j++) {
                if(seq[j] < seq[i] && len[j] + 1 > len[i])
                    len[i] = 1 + len[j];
            }
        }
        return Arrays.stream(len).sorted().toArray()[seq.length - 1];
    }
}
