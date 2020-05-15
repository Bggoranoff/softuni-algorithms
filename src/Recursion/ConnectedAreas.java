package Recursion;
import java.util.*;

public class ConnectedAreas {
    private static List<Integer[]> foundAreas = new ArrayList<>();

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int rows = Integer.parseInt(s.nextLine()), cols = Integer.parseInt(s.nextLine());
        String[][] matrix = new String[rows][cols];
        for(int i = 0; i < rows; i++) {
            matrix[i] = s.nextLine().split("\\s");
        }
        findConnectedAreas(0, matrix);
    }
    static void findConnectedAreas(int row, String[][] matrix) {
        if(row == matrix.length) printFoundAreas();
        else {
            for(int i = 0; i < matrix[row].length; i++) {
                if(isFree(matrix, row, i)) {
                    Integer[] arr = new Integer[3];
                    arr[0] = row;
                    arr[1] = i;
                    arr[2] = 0;
                    mark(matrix, row, i, arr);
                    foundAreas.add(arr);
                }
            }
            findConnectedAreas(row + 1, matrix);
        }
    }
    static boolean isFree(String[][] matrix, int row, int col) {
        return !matrix[row][col].equals("*");
    }
    static void mark(String[][] matrix, int row, int col, Integer[] data) {
        if(isInBounds(matrix, row, col) && isFree(matrix, row, col)) {
            data[2] ++;
            matrix[row][col] = "*" ;
            mark(matrix, row, col - 1, data);
            mark(matrix, row, col + 1, data);
            mark(matrix, row + 1, col, data);
            mark(matrix, row - 1, col, data);
        }
    }
    static boolean isInBounds(String[][] matrix, int row, int col) {
        return (row >= 0 && row < matrix.length) && (col >= 0 && col < matrix[0].length);
    }
    static void printFoundAreas() {
        foundAreas.sort((a, b) -> b[2] - a[2]);
        for(int i = 0; i < foundAreas.size(); i++) {
                System.out.printf("Area #%d at (%d, %d), size: %d\n", i, foundAreas.get(i)[0], foundAreas.get(i)[1], foundAreas.get(i)[2]);
        }
    }
}
