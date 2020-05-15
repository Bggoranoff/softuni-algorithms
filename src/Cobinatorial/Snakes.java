package Cobinatorial;
import java.util.*;

public class Snakes {
    static HashSet<String> commandList = new HashSet<>();
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int length = Integer.parseInt(s.nextLine());
        int[][] matrix = new int[length][length];
        for(int i = 0; i < length; i++) {
            for(int j = 0; j < length; j++) {
                matrix[i][j] = 0;
            }
        }
        ArrayList<String> commands = new ArrayList<>();
        generateSnakes(matrix, commands, length, 0, 0, "S");
        print();
    }
    public static void generateSnakes(int[][] matrix, ArrayList<String> commands, int n, int row, int col, String command) {
        if(commands.size() >= n) addSequence(commands);
        else {
            if(isValid(matrix, row, col)) {
                mark(matrix, row, col);
                commands.add(command);
                generateSnakes(matrix, commands, n, row, col + 1, "R");
                generateSnakes(matrix, commands, n, row + 1, col, "D");
                generateSnakes(matrix, commands, n, row, col - 1, "L");
                generateSnakes(matrix, commands, n, row - 1, col, "U");
                commands.remove(commands.size() - 1);
                unmark(matrix, row, col);
            }
        }
    }
    static void addSequence(ArrayList<String> list) {
        HashMap<String, String> map = new HashMap<>();
        for(String el : list) {
            if(!map.containsKey(el)) {
                map.put(el, String.valueOf(map.size()));
            }
        }
        ArrayList<String> clonedList = new ArrayList<>();
        for(String el : list) {
            clonedList.add(map.get(el));
        }
        ArrayList<String> reversedList = new ArrayList<>();
        ArrayList<String> reversedCommand = (ArrayList<String>) list.clone();
        reversedCommand.remove(0);
        reversedCommand.add("S");
        map = new HashMap<>();
        for(int i = reversedCommand.size() - 1; i >= 0; i--) {
            if(!map.containsKey(reversedCommand.get(i))) {
                map.put(reversedCommand.get(i), String.valueOf(map.size()));
            }
        }
        for(int i = reversedCommand.size() - 1; i >= 0; i--) {
            reversedList.add(map.get(reversedCommand.get(i)));
        }
        if(!commandList.contains(String.join("", reversedList)))
            commandList.add(String.join("", clonedList));
    }
    static void print() {
        HashMap<String, String> dict = new HashMap<>() {
            {
                put("0", "S");
                put("1", "R");
                put("2", "D");
                put("3", "L");
                put("4", "U");
            }
        };
        for(String c : commandList) {
            String[] arr = c.split("");
            System.out.printf("%s", dict.get(arr[0]));
            for(int i = 1; i < arr.length; i++) {
                if(!isDuplicate(arr, i, i - 1)) {
                    System.out.printf("%s", dict.get(arr[i]));
                } else {
                    System.out.printf("%s", dict.get(String.valueOf(Integer.parseInt(arr[i]) + 1)));
                }
            }
            System.out.println();
        }
    }
    static boolean isValid(int[][] matrix, int row, int col) {
        return (row >= 0 && row < matrix.length) && (col >= 0 && col < matrix.length) && matrix[row][col] != 1;
    }
    static boolean isDuplicate(String[] arr, int i, int j) {
        HashMap<String, String> dict = new HashMap<>(){
            {
                put("1", "3");
                put("3", "1");
                put("2", "4");
                put("4", "2");
            }
        };
        return dict.get(arr[i]).equals(arr[j]);
    }
    static void mark(int[][] matrix, int row, int col) {
        matrix[row][col] = 1;
    }
    static void unmark(int[][] matrix, int row, int col) {
        matrix[row][col] = 0;
    }
}
