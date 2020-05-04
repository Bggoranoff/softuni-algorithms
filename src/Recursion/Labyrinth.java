package Recursion;
import java.util.*;

public class Labyrinth {
    public static void main(String[] args) {
        String[][] matrix = handleInput();
        solveLabyrinth(matrix, 0, 0);
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
    static void solveLabyrinth(String[][] matrix, int row, int col) {
        if(isNotValid(matrix, row, col)) return;
        if(matrix[row][col].equals("e")) print(matrix);
        else if(!isVisited(matrix, row, col) && isPassable(matrix, row, col)) {
            mark(matrix, row, col);
            solveLabyrinth(matrix, row + 1, col);
            solveLabyrinth(matrix, row, col + 1);
            solveLabyrinth(matrix, row - 1, col);
            solveLabyrinth(matrix, row, col - 1);
            unmark(matrix, row, col);
        }
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
    static void print(String[][] matrix) {
        System.out.println();
        for(String[] line: matrix) {
            for(String el: line) {
                System.out.printf("%s ", el);
            }
            System.out.println();
        }
    }

}
