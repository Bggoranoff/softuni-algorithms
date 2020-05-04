package Recursion;
import java.util.*;

public class EightQueensPuzzle {
    public static void main(String[] args) {
        int[][] matrix = new int[8][8];
        fill(matrix);
        solveQueens(matrix, 0);
    }
    static void fill(int[][] matrix) {
        for(int[] row : matrix) {
            Arrays.fill(row, 0);
        }
    }
    static void solveQueens(int[][] matrix, int row) {
        if(row == 8) print(matrix);
        else {
            for(int i = 0; i < 8; i++) {
                if(canPlaceQueen(matrix, row, i)) {
                    markAllAttackedPositions(matrix, row, i);
                    matrix[row][i] = 1;
                    solveQueens(matrix, row + 1);
                    unmarkAllAttackedPositions(matrix, row, i);
                }
            }
        }
    }
    static void print(int[][] matrix) {
        for (int[] row: matrix) {
            for(int el: row) {
                System.out.printf("%s ", el%2 == 0 ? '-' : 'Q');
            }
            System.out.println();
        }
        System.out.println();
    }
    static boolean canPlaceQueen(int[][] matrix, int row, int col) {
        return matrix[row][col] == 0;
    }
    static void markAllAttackedPositions(int[][] matrix, int row, int col) {
        markRow(matrix, row, 2);
        markColumn(matrix, col, 2);
        markDiagonals(matrix, row, col, 2);
    }
    static void markRow(int[][] matrix, int row, int num) {
        for(int i = 0; i < 8; i++) matrix[row][i] += num;
    }
    static void markColumn(int[][] matrix, int col, int num) {
        for(int i = 0; i < 8; i++) matrix[i][col] += num;
    }
    static void markDiagonals(int[][] matrix, int row, int col, int num) {
        for(int i = -7; i < 8; i++) {
            if(row + i >= 0 && row + i < matrix.length) {
                if(col + i >= 0 && col + i < matrix.length) matrix[row + i][col + i] += num;
                if(col - i >= 0 && col - i < matrix.length) matrix[row + i][col - i] += num;
            }
        }
    }
    static void unmarkAllAttackedPositions(int[][] matrix, int row, int col) {
        markRow(matrix, row, -2);
        markColumn(matrix, col, -2);
        markDiagonals(matrix, row, col, -2);
        matrix[row][col] = 0;
    }

}
