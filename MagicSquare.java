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

    // Constructors
    public MagicSquare(String fileName) throws FileNotFoundException {
        readMatrix(fileName);
    }

    public MagicSquare(String fileName, int inputSizeMagicSquare) throws IOException {
        int sizeMagicSquare = inputSizeMagicSquare;
        matrixMagicSquare = new int[sizeMagicSquare][sizeMagicSquare];
        writeMatrix(matrixMagicSquare, fileName);
    }

    // Setters and getters
    // True or false method to see if a magic square object exists
    private int[][] readMatrix(String fileName) throws FileNotFoundException {
        String inputFileName = fileName;

        File retrieveFile = new File(inputFileName);

        Scanner fileScanner = new Scanner(retrieveFile);

        String line = fileScanner.nextLine();

        int startSize = Integer.parseInt(line);

        matrixMagicSquare = new int[startSize][startSize];
        
        for (int i = 0; i < startSize; i++) {
            for (int j = 0; j < startSize; j++) {
                line = fileScanner.next();
                int number = Integer.parseInt(line);
                matrixMagicSquare[i][j] = number;
            }
        }

        fileScanner.close();

        return matrixMagicSquare;
    }

    private void writeMatrix(int[][] matrix, String fileName) throws IOException {
        // Take the same input parameter and assign it to the private
        // String variable, inputFileName
        matrixMagicSquare = matrix;
        String createFileName = fileName;

        // Identify size from matrix length
        int sizeMagicSquare = matrixMagicSquare.length;

        // Magic Square Algorithm
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

    @Override
    public boolean isMagicSquare() {
        // Formula for the size of the magic square
        int size = matrixMagicSquare.length;
        int validTotal = (size * ((size * size) + 1)) / 2;
        int forwardDiagonalCount = 0;
        int reverseDiagonalCount = 0;

        
        // Check for horizontal lines
        // Start with row first, as the i variable
        for (int i = 0; i < size; i++) {
            int horizontalCount = 0;
            // The j variable represents the columns
            for (int j = 0; j < size; j++) {
                horizontalCount += matrixMagicSquare[i][j];
            }
            if (horizontalCount != validTotal) {
                return false;
            }
        }

        // Check vertical lines
        // Start with row first, as the i variable
        for (int i = 0; i < size; i++) {
            int verticalCount = 0;
            // The j variable represents the columns
            for (int j = 0; j < size; j++) {
                verticalCount += matrixMagicSquare[j][i];
            }
            if (verticalCount != validTotal) {
                return false;
            }
        }

        // Forward Diagonal check
        for (int i = 0; i < matrixMagicSquare.length; i++) {
            forwardDiagonalCount += matrixMagicSquare[i][i];
        }
        if (forwardDiagonalCount != validTotal) {
            return false;
        }

        // Reverse Diagonal check
        for (int i = 0; i < matrixMagicSquare.length; i++) {
            reverseDiagonalCount += matrixMagicSquare[i][matrixMagicSquare.length - 1 - i];
        }
        if (reverseDiagonalCount != validTotal) {
            return false;
        }

        // Check if 1, 2, 3, n... exists
        for (int i = 1; i <= size * size; i++) {
            boolean found = false;
            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    if (matrixMagicSquare[row][col] == i) {
                        found = true;
                    }
                }
            }
            if (!found) {
                return false;
            }
        }

        return true;
    }

    // Force encapsulation and return the values of an existing magic square matrix
    @Override
    public int[][] getMatrix() {
        int[][] copyMatrixMagicSquare = new int[matrixMagicSquare.length][matrixMagicSquare.length];

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
        returnMessage += "The matrix" + "\n";
        int[][] copyMatrixMagicSquare = getMatrix();

        if (isMagicSquare()) {
            // Outer loop represents the rows to be printed
            for (int i = 0; i < copyMatrixMagicSquare.length; i++) {
                // Inner loop represents the columns to be printed
                for (int j = 0; j < copyMatrixMagicSquare[i].length; j++) {
                    returnMessage += copyMatrixMagicSquare[i][j] + " ";
                }
                returnMessage += "\n";
            }
            returnMessage += "is a magic square.";
            return returnMessage;
        }
        else {
            // Outer loop represents the rows to be printed
            for (int i = 0; i < copyMatrixMagicSquare.length; i++) {
                // Inner loop represents the columns to be printed
                for (int j = 0; j < copyMatrixMagicSquare[i].length; j++) {
                    returnMessage += copyMatrixMagicSquare[i][j] + " ";
                }
                returnMessage += "\n";
            }
            returnMessage += "is not a magic square.";
            return returnMessage;
        }
    }
}
