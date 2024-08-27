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

        // First terminal string value is the mode, check or create
        String mode = args[0];

        // Second terminal string value is the file name for check or create
        String fileName = args[1];



        switch (mode) {
            case "-check":
                MagicSquare checkMagicSquare = null;
                System.out.println("Check mode!");

                try {
                    checkMagicSquare = new MagicSquare(fileName);
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                System.out.println(checkMagicSquare.toString());
                
                break;
           
            case "-create":
                // Third terminal string value is only for create, a size integer
                int inputSize = Integer.parseInt(args[2]);
                MagicSquare newMagicSquare = null;
                System.out.println("Create mode!");

                try {
                    newMagicSquare = new MagicSquare(fileName, inputSize);
                    System.out.println(newMagicSquare.toString());
                } catch (Exception e) {
                    // TODO: handle exception
                }
                break;
        
            default:
                break;
        }       
    }
}