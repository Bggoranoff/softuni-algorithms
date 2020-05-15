package Greedy;
import java.util.*;

public class EgyptianFractions {
    private static class Fraction {
        int denominator, numerator;
        Fraction(int numerator, int denominator) {
            this.numerator = numerator;
            this.denominator = denominator;
        }
        public void shorten() {
            if(this.numerator != 0) {
                for (int i = 2; i <= (Math.min(denominator, numerator)) / 2; i++) {
                    if (denominator % i == 0 && numerator % i == 0) {
                        this.setDenominator(this.denominator / i);
                        this.setNumerator(this.numerator / i);
                    }
                }
            }
        }
        public void remove(Fraction f) {
            this.setNumerator(this.numerator * f.getDenominator() - f.getNumerator() * this.denominator);
            this.setDenominator(this.denominator * f.getDenominator());
            this.shorten();
        }
        public boolean isGreaterThan(Fraction f) {
            return (double) this.numerator / (double) this.denominator >= (double) f.getNumerator() / (double) f.getDenominator();
        }
        public boolean equals(Fraction f) {
            if(this.numerator == 0 || f.getNumerator() == 0) return this.numerator == f.getNumerator();
            f.shorten();
            this.shorten();
            return this.numerator == f.getNumerator() && this.denominator == f.getDenominator();
        }
        public void setDenominator(int denominator) {
            this.denominator = denominator;
        }

        public void setNumerator(int numerator) {
            this.numerator = numerator;
        }

        public int getDenominator() {
            return denominator;
        }

        public int getNumerator() {
            return numerator;
        }

        @Override
        public String toString() {
            return numerator + "/" + denominator;
        }
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String[] input = s.nextLine().split("/");
        Fraction dest = new Fraction(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
        if(dest.getNumerator() > dest.getDenominator()) System.out.println("Error (fraction is equal or greater than one)");
        else {
            System.out.printf("%s = ", dest.toString());
            dest.shorten();
            ArrayList<Fraction> fractions = new ArrayList<>();
            int i = 2;
            while (!dest.equals(new Fraction(0, 1))) {
                Fraction test = new Fraction(1, i);
                if (dest.isGreaterThan(test)) {
                    dest.remove(test);
                    fractions.add(test);
                }
                i++;
            }
            for (int j = 0; j < fractions.size(); j++) {
                if (j == fractions.size() - 1) System.out.printf("%s\n", fractions.get(j).toString());
                else System.out.printf("%s + ", fractions.get(j).toString());
            }
        }
    }
}
