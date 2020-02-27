/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestRun;

import static TestRun.TestRunner.totalTest;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Home
 */
public class MainTestRunner2 implements Runnable {

    ArrayList<File> testFiles;
    ArrayList<File> OriginalTest;
    int counter;
    static int totalTest = 0;
    String directoryToTest;
    String projectPath;

    ArrayList<String> testFilesPathes;
    String mutantFile;

    public MainTestRunner2(String projectPath, String MutantPath, int counter, ArrayList<File> testFiles, ArrayList<String> testFilesPathes, int threadNumbers) {

        mutantFile = MutantPath + "\\Mutant" + counter + ".txt";
//        firstMutantFile = MutantPath + "\\Mutant0.txt";
//        secondMutantFile = MutantPath + "\\Mutant1.txt";
//        thirdMutantFile = MutantPath + "\\Mutant2.txt";
//        fourthMutantFile = MutantPath + "\\Mutant3.txt";
        directoryToTest = MutantPath + counter;
        this.projectPath = projectPath;
        this.testFiles = testFiles;
        this.testFilesPathes = testFilesPathes;
        totalTest++;
        this.counter = counter;
    }

    public void run() {
        //System.out.println(counter+"  mm");
        runTestCasesAgainstMutants(directoryToTest, counter, testFiles);
    }
    
     public void runTestCasesAgainstMutants(String mutantPathtoTest, int number, ArrayList<File> testFiles) {
        //System.out.println("Test No. "+number+" is Running..");

        try {

            File file = new File(mutantFile);
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                //System.out.println("  Test No. "+(totalTest+1)+" Mutant No.: "+counter);
                String originalPath = sc.nextLine();
                String mutantPath = sc.nextLine();
                String directoryToPath = sc.nextLine();
                String fileName = sc.nextLine();
                String mutantNumber = sc.nextLine();
                String lineNumber = sc.nextLine();
                String operator = sc.nextLine();
                String originalLine = sc.nextLine();
                String mutantLine = sc.nextLine();
                //beforeTest(mutantPath, directoryToPath, fileName, testFiles, ".Mutant" + number);
                //runTest(mutantPathtoTest, fileName, lineNumber, operator, originalLine, mutantLine);
               // afterTest(originalPath, directoryToPath, fileName, testFiles, mutantPathtoTest, ".Mutant" + number);
                // totalTest++;
            }
            sc.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }
}
