# ****************
* Project 1: Warmup
* CS 221
* August 23rd, 2024 // CHANGE TO CURRENT DATE
* Kyle Truschel
# ****************

# OVERVIEW:
This program constructs a magic square matrix, where each values
in the line sum up to the same value as the other lines

# INCLUDED FILES:
README.md - this file
MagicSquare.java - source file
MagicSquareDriver.java - source file
MagicSquareInterface.java - interface file for methods in MagicSquare.java
MagicSquareTester.java - tester file to ensure program runs properly

# COMPILING AND RUNNING
From the directory containing all included files, compile the driver class with the command:
# $ javac MagicSquare.java

Run the compiled class file with the command:
# java MagicSquare

After compiling, the program will be ran as...

# TODO ****************

# PROGRAM DESIGN AND IMPORTANT CONCEPTS:
The MagicSquare class involves two constructors and two method signatures. 
The first constructor is primarily used to recieve an already existing magic square file. This involves receiving the file name as a String variable and passing it as a paramter into the first overloaded constructor. It then takes this string variable and converts it to a File type object. Then, using standard procedure, two Scanners will be opened onto the File as well as its lines to parse through its contents using a while loop, where as long as lines exist, the scanner will continue printing out the line.
The second constructor is used to create a new magic square matrix as a 2d array of integers, and then output its contents to a new file. Similarly, it receives a file name as a String variable and then passes this String variable to be used as a new File object. In addition, the constructor also requires an int parameter, which is used to determine the size of the matrix.

# DISCUSSION:
Since this was the first project after a bad CS 121 expierence and not much opportunity to learn more over the break, I had a bit of trouble. I was able to successfully create the first constructor, which simply prints out an already existing file, by following my projects from CS 121. The second constructor gave me trouble since I had no idea how to take the given algorithm and write its contents to a new file.

# SOURCES:
Kount Learning Center
https://www.geeksforgeeks.org/check-if-a-value-is-present-in-an-array-in-java/

