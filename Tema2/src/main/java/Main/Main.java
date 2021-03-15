package Main;

import generate.numbers.GenerateNumbers;
import generate.numbers.JacobiSymbol;


public class Main {
    public static void main(String[] args) {

        GenerateNumbers generateNumbers = new GenerateNumbers();

        generateNumbers.generate(100);
        System.out.println(generateNumbers);
        generateNumbers.testGenerator();
        System.out.println();
        JacobiSymbol jacobiSymbol = new JacobiSymbol();

        jacobiSymbol.getMJacobiSymbols(10000);

        System.out.println(jacobiSymbol);
    }
}
