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
    private String inputFileName;
    private int sizeMagicSquare;
    private int[][] matrixMagicSquare;
    private MagicSquare testMagicSquare; 

    // Constructors
    public MagicSquare(String inputFileName) {
        // Assign the input String paramter to the Class' private
        // variable of the same name
        this.inputFileName = inputFileName;

        try {
            // Take the input string and convert it to type file
            File checkFileName = new File(inputFileName);

            // Open a scanner on the File
            Scanner scannerFile = new Scanner(checkFileName);

            // While the file has lines in existence...
            while (scannerFile.hasNextLine()) {
                // Declare a line to be the next existence 
                // of a line in the file
                String line = scannerFile.nextLine();

                // Open a scanner on the line
                Scanner scannerLine = new Scanner(line);

                // Output the contents of each line to the terminal
                System.out.println(line);

                // Close the line scanner
                scannerLine.close();
            }
            // Close the file scanner
            scannerFile.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public MagicSquare(String inputFileName, int sizeMagicSquare) {
        // Take the same input parameter and assign it to the private
        // String variable, inputFileName
        this.inputFileName = inputFileName;

        // Assign the input int paramter to the class' private variable
        this.sizeMagicSquare = sizeMagicSquare;

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

        // Start from i = 1 up to and including the input value
        // squared to avoid an index out of bounds error

        int oldRow;
        int oldCol;

        for (int i = 1; i <= inputSquared; i++) {
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
                oldRow = row;
                oldCol = col;
                row--;
            }
        }

        // Now write the magic square matrix/2d array to a file
        try {
            // Take the input string and convert it to type file
            File checkFileName = new File(inputFileName);

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
        int validTotal = sizeMagicSquare * ((sizeMagicSquare * sizeMagicSquare) / 2);
        int counter = 0;
        
        // Check for horizontal
        for (int i = 0; i < matrixMagicSquare.length; i++) {
            for (int j = 0; j < matrixMagicSquare[i].length; j++) {
                counter += matrixMagicSquare[i][j];
            }
            if (counter != validTotal) {
                return false;
            }
            counter = 0;
        }

        // Check verticle
        for (int i = 0; i < matrixMagicSquare.length; i++) {
            for (int j = 0; j < matrixMagicSquare[i].length; j++) {
                counter += matrixMagicSquare[j][i];
            }
            if (counter != validTotal) {
                return false;
            }
            counter = 0;
        }

        return false;
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
        String returnMessage = "";
        if (isMagicSquare() == true) {
            returnMessage += "The matrix" + "\n";
            for (int i = 0; i < matrixMagicSquare.length; i++) {
                for (int j = 0; i < matrixMagicSquare[i].length; j++) {
                    returnMessage += matrixMagicSquare[i][j] + " ";
                }
                returnMessage += "\n";
            }
            return returnMessage;
        }
        else {
                returnMessage += "The matrix" + "\n";
                for (int i = 0; i < matrixMagicSquare.length; i++) {
                    for (int j = 0; j < matrixMagicSquare[i].length; j++) {
                        returnMessage += matrixMagicSquare[i][j] + " ";
                    }
                    returnMessage += "\n";
                }
            return returnMessage;
        }
    }
}
