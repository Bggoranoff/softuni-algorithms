package Dynamic;
import java.util.*;

public class MoveDownRightSum {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int rows = Integer.parseInt(s.nextLine());
        int cols = Integer.parseInt(s.nextLine());
        int[][] numbers = new int[rows][cols];
        for(int i = 0; i < rows; i++) {
            int[] line = Arrays.stream(s.nextLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            numbers[i] = line;
        }
        int[][] sums = new int[rows][cols];
        sums[0][0] = numbers[0][0];
        // Fill row
        for(int i = 1; i < rows; i++) {
            sums[i][0] = sums[i - 1][0] + numbers[i][0];
        }
        for(int i = 1; i < cols; i++) {
            sums[0][i] = sums[0][i - 1] + numbers[0][i];
        }
        for(int row = 1; row < rows; row++) {
            for(int col = 1; col < cols; col++) {
                sums[row][col] = Math.max(sums[row - 1][col], sums[row][col - 1]) + numbers[row][col];
            }
        }
        System.out.println(sums[rows - 1][cols - 1]);
//        Route
        ArrayList<String> result = new ArrayList<String>();
        result.add(String.join("", new String[]{"[", String.valueOf(rows - 1), ", ", String.valueOf(cols - 1), "]"}));
        int currentRow = rows - 1, currentCol = cols - 1;
        while (!(currentRow == 0 && currentCol == 0)) {
            int top = currentRow - 1 >= 0 ? sums[currentRow - 1][currentCol] : -1;
            int left = currentCol - 1 >= 0 ? sums[currentRow][currentCol - 1] : -1;

            if(top > left) {
                result.add(String.join("", new String[]{"[", String.valueOf(currentRow - 1), ", ", String.valueOf(currentCol), "]"}));
                currentRow -= 1;
            } else {
                result.add(String.join("", new String[]{"[", String.valueOf(currentRow), ", ", String.valueOf(currentCol - 1), "]"}));
                currentCol -= 1;
            }
        }
        Collections.reverse(result);
        System.out.println(String.join(" ", result));
    }

}
