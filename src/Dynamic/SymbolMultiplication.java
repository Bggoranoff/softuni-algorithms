package Dynamic;
import java.util.*;

public class SymbolMultiplication {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String[] alphabet = s.nextLine().split("\\s");
        String[][] table = new String[alphabet.length][alphabet.length];
        for(int i = 0; i < alphabet.length; i++) {
            String[] arr = s.nextLine().split("");
            table[i] = arr;
        }
        String[] characters = s.nextLine().split("\\s");
        //TODO: Complete solution
    }
}
