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

    public static void printUsage ( ) {
        System.out.println("Usage: java MagicSquareDriver <-check | -create> <filename> < |size>");
        System.out.println("\tcheck  : Check if filename is a magic square");
        System.out.println("\tcreate : Create a magic square of dimension size");
    }

    public static void main(String[] args) {

        if ( args.length != 2 && args.length != 3 ) {
            printUsage();
            return;
        }

        String mode = args[0];
        String fileName = args[1];

        // TODO: Parse command line argumets
        //       1. Are we checking or creating? 
        //       2. Which file
        //       3. (maybe) How big

        switch (mode) {

            case "-check":
                
                MagicSquare newMagicSquare = null;

                try {
                    newMagicSquare = new MagicSquare(fileName);
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                System.out.println(newMagicSquare.toString());
                
                break;
           
            case "-create":
                System.out.println("Create mode!");
                break;
        
            default:
                break;
        }       
        
        // 

        MagicSquare testValue = new MagicSquare("test.txt", 3);

        System.out.println(testValue.toString());
    }
}