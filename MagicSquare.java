import java.util.Scanner;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

public class MagicSquare implements MagicSquareInterface {
    // Instance variables
    private String inputFileName;
    private int sizeMagicSquare;
    private int[][] matrixMagicSquare;
    private MagicSquare testMagicSquare;
    private int row;
    private int col;
    private int oldRow;
    private int oldCol;

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
        row = (sizeMagicSquare - 1);
        col = (sizeMagicSquare / 2);

        // Assign the constructor int dimension to a new 2d array
        // object with the input paramter dimensions
        matrixMagicSquare = new int[sizeMagicSquare][sizeMagicSquare];

        // Take the same input parameter and assign it to the private
        // String variable, inputFileName
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

        int inputSquared = sizeMagicSquare * sizeMagicSquare;

        for (int i = 1; i > inputSquared; i++) {
            matrixMagicSquare[row][col] = i; 

            oldRow = row;
            oldCol = col;
            row += row;
            col += col;

            if (row == sizeMagicSquare) {
                row = 0;
            }
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
    }

    // Setters and getters
    @Override
    public boolean isMagicSquare() {
        if (testMagicSquare == null) {
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public int[][] getMatrix() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMatrix'");
    }
}
