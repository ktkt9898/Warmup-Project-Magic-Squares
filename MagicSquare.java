import java.util.Scanner;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.FileNotFoundException;

/**
 * @author Kyle Truschel
 * This Class contains necessary constructors and method signatures
 * to create a Magic Square object and validate an already existing
 * Magic Square (from file name)
 */

public class MagicSquare implements MagicSquareInterface {
    // Instance variables
    private int[][] matrixMagicSquare;
    private int sizeMagicSquare;

    // Constructors
    public MagicSquare(String fileName) throws FileNotFoundException {
        String inputFileName = fileName;

        File retrieveFile = new File(inputFileName);

        Scanner fileScanner = new Scanner(retrieveFile);

        int startSize = fileScanner.nextInt();

        matrixMagicSquare = new int[startSize][startSize];

        for (int i = 0; i < matrixMagicSquare.length; i++) {
            for (int j = 0; j < matrixMagicSquare[i].length; j++) {
                fileScanner.nextInt((matrixMagicSquare[i][j]));
            }
        }

        fileScanner.close();
    }

    public MagicSquare(String fileName, int inputSizeMagicSquare) {
        // Take the same input parameter and assign it to the private
        // String variable, inputFileName
        String createFileName = fileName;

        // Assign the input int paramter to the class' private variable
        int sizeMagicSquare = inputSizeMagicSquare;

        // Magic Square Algorithm
        // Assign the constructor int dimension to a new 2d array
        // object with the input paramter dimensions
        matrixMagicSquare = new int[sizeMagicSquare][sizeMagicSquare];

        // The two integer variables old row and old column
        int row;
        int col;

        // Set row to be input size - 1
        row = (sizeMagicSquare - 1);

        // Set column to be input size / 2
        col = (sizeMagicSquare / 2);

        // Variable to create the input parameter squared^2
        int inputSquared = sizeMagicSquare * sizeMagicSquare;

        // Declare an oldRow and oldCol variable to be used later
        int oldRow;
        int oldCol;

        // Start from i = 1 up to and including the input value
        // squared to avoid an index out of bounds error
        for (int i = 1; i < inputSquared; i++) {
            matrixMagicSquare[row][col] = i; 

            // Assign oldRow to row and oldCol to col
            oldRow = row;
            oldCol = col;

            // Increment row and column
            row++;
            col++;

            // Conditional statements
            // If row is the same as the input parameter, assingn row to be 0
            if (row == sizeMagicSquare) {
                row = 0;
            }
            // If col is the same as the input parameter, assing column to be 0
            if (col == sizeMagicSquare) {
                col = 0;
            }

            // Check the value stored at the location [row][col]
            // and if the element exists or does not equal 0 (for primitives)
            // perform the conditional below
            if (matrixMagicSquare[row][col] != 0) {
                row = oldRow;
                col = oldCol;
                row--;
            }
        }

        // Now write the magic square matrix/2d array to a file
        try {
            // Take the input string and convert it to type file
            File checkFileName = new File(createFileName);

            FileOutputStream outputStream = new FileOutputStream(checkFileName);

            PrintWriter writeToNewFile = new PrintWriter(outputStream);

            // PrintWriter prints the first line to be the size of the matrix
            writeToNewFile.println(sizeMagicSquare);

            // "i" represents the row, "j" represents the column
            for (int i = 0; i < matrixMagicSquare.length; i++) {
                for (int j = 0; j < matrixMagicSquare[i].length; j++) {
                    writeToNewFile.write(matrixMagicSquare[i][j] + " ");
                }
                // Make a new line after the first line has finished
                writeToNewFile.println();
            }

            writeToNewFile.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Invalid file. Please re-check spelling.");
        }
    }

    // Setters and getters
    // True or false method to see if a magic square object exists
    @Override
    public boolean isMagicSquare() {
        // Formula for the size of the magic square
        int validTotal = (sizeMagicSquare * ((sizeMagicSquare * sizeMagicSquare) + 1)) / 2;
        int counter = 0;
        
        // Check for horizontal lines
        // Start with row first, as the i variable
        for (int i = 0; i < matrixMagicSquare.length; i++) {
            // The j variable represents the columns
            for (int j = 0; j < matrixMagicSquare[i].length; j++) {
                counter += matrixMagicSquare[i][j];
            }
        }

        if (counter != validTotal) {
            return false;
        }
        counter = 0;

        // Check vertical lines
        // Start with row first, as the i variable
        for (int i = 0; i < matrixMagicSquare.length; i++) {
            // The j variable represents the columns
            for (int j = 0; j < matrixMagicSquare[i].length; j++) {
                counter += matrixMagicSquare[j][i];
            }
        }

        if (counter != validTotal) {
            return false;
        }
        counter = 0;

        // If both conditionals are passed, return true;
        return true;
    }

    // Force encapsulation and return the values of an existing magic square matrix
    @Override
    public int[][] getMatrix() {
        int[][] copyMatrixMagicSquare = new int[0][0];

        for (int i = 0; i < matrixMagicSquare.length; i++) {
            for (int j = 0; j < matrixMagicSquare[i].length; j++) {
                copyMatrixMagicSquare[i][j] = matrixMagicSquare[i][j];
            }
        }

        // Return a brand new 2d array, that is not the same as the
        // Class' array (to avoid breaking encapsulation)
        return copyMatrixMagicSquare;
    }

    // Functioning toString, that will print to terminal the validity
    // of the magic square
    @Override
    public String toString() {
        // Primer variable, to later append string values
        String returnMessage = "";

        // First call the isMagicSquare method, which will handle the validity
        // of a magic square
        if (isMagicSquare() == true) {
            returnMessage += "The matrix" + "\n";

            // Outer loop represents the rows to be printed
            for (int i = 0; i < matrixMagicSquare.length; i++) {
                // Inner loop represents the columns to be printed
                for (int j = 0; i < matrixMagicSquare[i].length; j++) {
                    returnMessage += matrixMagicSquare[i][j] + " ";
                }
                returnMessage += "\n";
            }
            returnMessage += "is a magic square.";
            return returnMessage;
        }
        else {
            returnMessage += "The matrix" + "\n";

            // Outer loop represents the rows to be printed
            for (int i = 0; i < matrixMagicSquare.length; i++) {
                // Inner loop represents the columns to be printed
                for (int j = 0; j < matrixMagicSquare[i].length; j++) {
                    returnMessage += matrixMagicSquare[i][j] + " ";
                }
                returnMessage += "\n";
            }
            returnMessage += "is not a magic square.";
            return returnMessage;
        }
    }
}
