package Greedy;
import java.util.*;

public class KnightsTour {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = Integer.parseInt(s.nextLine());
        int[][] matrix = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                matrix[i][j] = calculateFree(n, i, j);
            }
        }
        int[][] steps = new int[n][n];
        moveKnight(matrix, steps, 0, 0, 1);
        print(steps);
    }
    static int calculateFree(int n, int row, int col) {
        int i = 0;
        i += isValid(n, row - 2, col - 1) ? 1 : 0;
        i += isValid(n, row - 1, col - 2) ? 1 : 0;
        i += isValid(n, row + 1, col - 2) ? 1 : 0;
        i += isValid(n, row + 2, col - 1) ? 1 : 0;
        i += isValid(n, row + 2, col + 1) ? 1 : 0;
        i += isValid(n, row + 1, col + 2) ? 1 : 0;
        i += isValid(n, row - 1, col + 2) ? 1 : 0;
        i += isValid(n, row - 2, col + 1) ? 1 : 0;
        return i;
    }
    static void print(int[][] matrix) {
        for(int[] row : matrix) {
            for(int el : row) {
                System.out.printf("%s ", generateOutput(String.valueOf(el)));
            }
            System.out.println();
        }
    }
    static String generateOutput(String num) {
        StringBuilder output = new StringBuilder();
        for(int i = 0; i < 3 - num.length(); i++) {
            output.append(" ");
        }
        output.append(num);
        return output.toString();
    }
    static boolean isValid(int n, int row, int col) {
        return row < n && row >= 0 && col < n && col >= 0;
    }
    static boolean isFree(int[][] matrix, int row, int col) {
        return matrix[row][col] == 0;
    }
    static void moveKnight(int[][] matrix, int[][] steps, int row, int col, int step) {
        if(step <= matrix.length * matrix.length) {
            mark(matrix, steps, row, col, step);
            steps[row][col] = step;
            int[] nextCoords = findMin(steps, matrix, row, col);
            moveKnight(matrix, steps, nextCoords[0], nextCoords[1], step + 1);
        }
    }
    static void mark(int[][] matrix, int[][] steps, int row, int col, int step) {
        if(isValid(matrix.length, row - 2, col - 1) && isFree(steps, row - 2, col - 1)) matrix[row - 2][col - 1] -= 1;
        if(isValid(matrix.length, row - 1, col - 2) && isFree(steps, row - 1, col - 2)) matrix[row - 1][col - 2] -= 1;
        if(isValid(matrix.length, row + 1, col - 2) && isFree(steps, row + 1, col - 2)) matrix[row + 1][col - 2] -= 1;
        if(isValid(matrix.length, row + 2, col - 1) && isFree(steps, row + 2, col - 1)) matrix[row + 2][col - 1] -= 1;
        if(isValid(matrix.length, row + 2, col + 1) && isFree(steps, row + 2, col + 1)) matrix[row + 2][col + 1] -= 1;
        if(isValid(matrix.length, row + 1, col + 2) && isFree(steps, row + 1, col + 2)) matrix[row + 1][col + 2] -= 1;
        if(isValid(matrix.length, row - 1, col + 2) && isFree(steps, row - 1, col + 2)) matrix[row - 1][col + 2] -= 1;
        if(isValid(matrix.length, row - 2, col + 1) && isFree(steps, row - 2, col + 1)) matrix[row - 2][col + 1] -= 1;
    }
    static int[] findMin(int[][] steps, int[][] matrix, int row, int col) {
        int[] mincoords = new int[2];
        int minOptions = Integer.MAX_VALUE;
        if(isValid(matrix.length, row - 2, col - 1) && isFree(steps, row - 2, col - 1) && matrix[row - 2][col - 1] < minOptions) {
            mincoords[0] = row - 2;
            mincoords[1] = col - 1;
            minOptions = matrix[row - 2][col - 1];
        }
        if(isValid(matrix.length, row - 1, col - 2) && isFree(steps, row - 1, col - 2) && matrix[row - 1][col - 2] < minOptions) {
            mincoords[0] = row - 1;
            mincoords[1] = col - 2;
            minOptions = matrix[row - 1][col - 2];
        }
        if(isValid(matrix.length, row + 1, col - 2) && isFree(steps, row + 1, col - 2) && matrix[row + 1][col - 2] < minOptions) {
            mincoords[0] = row + 1;
            mincoords[1] = col - 2;
            minOptions = matrix[row + 1][col - 2];
        }
        if(isValid(matrix.length, row + 2, col - 1) && isFree(steps, row + 2, col - 1) && matrix[row + 2][col - 1] < minOptions) {
            mincoords[0] = row + 2;
            mincoords[1] = col - 1;
            minOptions = matrix[row + 2][col - 1];
        }
        if(isValid(matrix.length, row + 2, col + 1) && isFree(steps, row + 2, col + 1) && matrix[row + 2][col + 1] < minOptions) {
            mincoords[0] = row + 2;
            mincoords[1] = col + 1;
            minOptions = matrix[row + 2][col + 1];
        }
        if(isValid(matrix.length, row + 1, col + 2) && isFree(steps, row + 1, col + 2) && matrix[row + 1][col + 2] < minOptions) {
            mincoords[0] = row + 1;
            mincoords[1] = col + 2;
            minOptions = matrix[row + 1][col + 2];
        }
        if(isValid(matrix.length, row - 1, col + 2) && isFree(steps, row - 1, col + 2) && matrix[row - 1][col + 2] < minOptions) {
            mincoords[0] = row - 1;
            mincoords[1] = col + 2;
            minOptions = matrix[row - 1][col + 2];
        }
        if(isValid(matrix.length, row - 2, col + 1) && isFree(steps, row - 2, col + 1) && matrix[row - 2][col + 1] < minOptions) {
            mincoords[0] = row - 2;
            mincoords[1] = col + 1;
//            minOptions = matrix[row - 2][col + 1];
        }
        return mincoords;
    }
}
