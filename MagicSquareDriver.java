import java.util.Scanner;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

/**
 * @author Kyle Truschel
 * 
 * This class functions as the driver class that has a dependency on
 * the MagicSquare class in order to output to the terminal a valid
 * magic square matrix
 */

public class MagicSquareDriver {
    public static void main(String[] args) {
        System.out.println("Usage: java MagicSquareDriver");
        
        // MagicSquare newMagicSquare = new MagicSquare("valid6x6");

        MagicSquare testValue = new MagicSquare("test.txt", 5);
        testValue.isMagicSquare();
        testValue.toString();
    }
}