import java.util.Scanner;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

public class MagicSquareDriver {
    public static void main(String[] args) {
        System.out.println("Usage: java MagicSquareDriver");
        
        // MagicSquare newMagicSquare = new MagicSquare("valid6x6");

        MagicSquare testValue = new MagicSquare("test.txt", 5);
        testValue.isMagicSquare();
    }
}