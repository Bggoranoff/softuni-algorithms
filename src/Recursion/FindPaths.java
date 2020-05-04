package Recursion;
import java.util.*;

public class FindPaths {
    public static void main(String[] args) {
        String[][] matrix = handleInput();
        ArrayDeque<Character> operations = new ArrayDeque<Character>();
        solveLabyrinth(matrix, 0, 0, operations, 'S');
    }
    static String[][] handleInput() {
        Scanner s = new Scanner(System.in);
        int rows = Integer.parseInt(s.nextLine());
        int cols = Integer.parseInt(s.nextLine());
        String[][] matrix = new String[rows][cols];
        for(int i = 0; i < rows; i++) {
            matrix[i] = s.nextLine().split("\\s");
        }
        return matrix;
    }
    static void solveLabyrinth(String[][] matrix, int row, int col, ArrayDeque<Character> operations, Character direction) {
        if(isNotValid(matrix, row, col)) return;
        operations.push(direction);
        if(matrix[row][col].equals("e")) print(operations);
        else if(!isVisited(matrix, row, col) && isPassable(matrix, row, col)) {
            mark(matrix, row, col);
            solveLabyrinth(matrix, row + 1, col, operations, 'D');
            solveLabyrinth(matrix, row, col + 1, operations, 'R');
            solveLabyrinth(matrix, row - 1, col, operations, 'U');
            solveLabyrinth(matrix, row, col - 1, operations, 'L');
            unmark(matrix, row, col);
        }
        operations.pop();
    }
    static void mark(String[][] matrix, int row, int col) {
        matrix[row][col] = "X";
    }
    static void unmark(String[][] matrix, int row, int col) {
        matrix[row][col] = "-";
    }
    static boolean isVisited(String[][] matrix, int row, int col) {
        return matrix[row][col].equals("X");
    }
    static boolean isPassable(String[][] matrix, int row, int col) {
        return !matrix[row][col].equals("*");
    }
    static boolean isNotValid(String[][] matrix, int row, int col) {
        return row >= matrix.length || row < 0 || col >= matrix[0].length || col < 0;
    }
    static void print(ArrayDeque<Character> operations) {
        ArrayDeque<Character> copyOperations = new ArrayDeque<Character>(operations);
        System.out.println();
        Character[] arr = new Character[operations.size()];
        int i = 0;
        while(!copyOperations.isEmpty()) {
            arr[i] = copyOperations.pop();
            i++;
        }
        for (i = arr.length - 1; i >= 0; i--) {
            if (arr[i] != 'S') System.out.printf("%c", arr[i]);
        }
    }
}
