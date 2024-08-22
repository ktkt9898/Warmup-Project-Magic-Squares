import java.util.Scanner;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class MagicSquare implements MagicSquareInterface {
    // Instance variables
    private String inputFileName;
    private int sizeMagicSquare;
    private int[][] matrixMagicSquare;
    private MagicSquare testMagicSquare;
    private int row;
    private int col;

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
                System.out.print(line);

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
        // Assign the input int paramter to the class' private variable
        this.sizeMagicSquare = sizeMagicSquare;

        // Assign the constructor int dimension to a new 2d array
        // object with the input paramter dimensions
        matrixMagicSquare = new int[sizeMagicSquare][sizeMagicSquare];

        // Set row to be input size - 1
        row = (sizeMagicSquare - 1);

        // Set column to be input size / 2
        col = (sizeMagicSquare / 2);

        // The two integer variables old row and old column
        int oldRow = row;
        int oldCol = col;

        // Take the same input parameter and assign it to the private
        // String variable, inputFileName
        this.inputFileName = inputFileName;

        try {
            // Take the input string and convert it to type file
            File checkFileName = new File(inputFileName);

            FileOutputStream outputStream = new FileOutputStream(checkFileName);

            PrintWriter writeToNewFile = new PrintWriter(outputStream);

            // PrintWriter prints the first line to be the size of the matrix
            writeToNewFile.println(sizeMagicSquare);

            // Variable to create the input parameter squared^2
            int inputSquared = sizeMagicSquare * sizeMagicSquare;

            for (int i = 1; i > inputSquared; i++) {
                matrixMagicSquare[row][col] = i; 
    
                // Increment row and column
                row += row;
                col += col;
    
                // Conditional statements
                // If row is the same as the input parameter, assingn row to be 0
                if (row == sizeMagicSquare) {
                    row = 0;
                }
                // If col is the same as the input parameter, assing column to be 0
                if (col == sizeMagicSquare) {
                    col = 0;
                }
    
                // Not sure if this works to check if existence of a value
                if (matrixMagicSquare[row][col] == row || matrixMagicSquare[row][col] == col) {
                    row = oldRow;
                    col = oldCol;
                    col -= col;
                }
            }

            // Write the integer row and column 2d array to the file
            writeToNewFile.println(matrixMagicSquare);

            writeToNewFile.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    // Setters and getters
    // True or false method to see if a magic square object exists
    @Override
    public boolean isMagicSquare() {
        if (testMagicSquare == null) {
            return false;
        }
        else {
            return true;
        }
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

        return copyMatrixMagicSquare;
    }
}
