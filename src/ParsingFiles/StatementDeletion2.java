/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ParsingFiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Home
 */
public class StatementDeletion2 {
    
    ArrayList<String> ifStatementOperator = new ArrayList<String>(); // this list contains the output of mutants
    ArrayList<Boolean> trueOrFalse = new ArrayList<Boolean>();
    ArrayList<File> originalFiles = new ArrayList<File>(); // this list contains the original files for each mutants
    ArrayList<MutantInformation> mutantInformation = new ArrayList<MutantInformation>(); // this firstPossibilityay contains the information of each mutant

    public static void main(String[] args) {

        File f = new File("E:\\projects\\test\\src\\app\\about\\about.page.ts");
        StatementDeletion2 b = new StatementDeletion2();
        b.generateMutant(f);

    }

    public void generateMutant(File file) {

        int lineNumber = 0; // to save the line number that we will edit in
        String beforeClass = ""; // string to save all text before the class word
        boolean startScanning = false;
//int cont = -1;
        //String insideFunction = "";
        try {
            Scanner sc = new Scanner(file);
            // scan the file
            while (sc.hasNext()) {
                lineNumber++;

                String line = sc.nextLine();

                // pasting the rest of the code in all mutants
//                for (int i = 0; i < ifStatementOperator.size(); i++) {
//                    String o = ifStatementOperator.get(i) + line + "\n";
//                    ifStatementOperator.set(i, o);
//                }
                // ignore all ifStatementOperator before the class word
                if (line.contains("class") && !startScanning) {
                    startScanning = true;
                    output += beforeClass + "\n" + line;
                    continue;
                } else if (!line.contains("class") && !startScanning) {
                    beforeClass += line + "\n";
                }
                // start scanning the ifStatementOperator between the block of the class only
                if (startScanning) {
                    //System.out.println(line);
                    if (line.contains("{") && !startMethod) {
                        startMethod = true;
                        //System.out.println("00000000000000000000");
                        cont++;
                        ifStatementOperator.add(output);
                        originalFiles.add(file);
                        output += line + "\n";
                    } else {
                        for (int i = 0; i < ifStatementOperator.size(); i++) {
                            if (i != cont) {
                                ifStatementOperator.set(i, ifStatementOperator.get(i) + "\n" + line);
                            }
                            if (i == cont && !startMethod) {
                                ifStatementOperator.set(i, ifStatementOperator.get(i) + "\n" + line);
                            }

                        }
                        output += line + "\n";
                    }
                    if (startMethod) {

                        setOperator(line, lineNumber);
                    }

                }

                ////
            }
            sc.close();

            // Generating the real files for each mutant by sending the mutants, original files and mutant information for each mutants
            //System.out.println(output);
            //System.out.println(trueOrFalse.size());
            for (int i = 0; i < ifStatementOperator.size(); i++) {
                System.out.println("-------------------");
                System.out.println(ifStatementOperator.get(i));
                System.out.println("-------------------");

            }
            if (!ifStatementOperator.isEmpty()) {
                GenerateMutantFiles gm = new GenerateMutantFiles();
               // gm.generateFiles(ifStatementOperator, originalFiles, mutantInformation);
            }

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }

    /*
     * This method takes each line that contains an operator and generate all possible mutants
     * as a string and saving all mutant information
     */
    static boolean startMethod = false;
    boolean found = false;
    boolean finished = false;
    int counter = 0;

    String output = "";
    int cont = -1;

    public void setOperator(String line, int lineNumber) {
        String rest = "";
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '{') {
                counter++;
                found = true;
            }
            if (line.charAt(i) == '}') {
                counter--;
                if (counter == 0) {
                    startMethod = false;
                }
                //System.out.println("HERE");
            }
            if (found) {
                rest += line.charAt(i);
            }

        }
        if (found && counter == 0) {
            found = false;
            // " { }");
        }
        if (!rest.isEmpty()) {
              
//            ifStatementOperator.set(cont, ifStatementOperator.get(cont) + "\n" + rest + " { }" + "\n");
        }

        for (int i = 0; i < ifStatementOperator.size(); i++) {
            if (i != cont) {
                if (!rest.isEmpty()) {
                    ifStatementOperator.set(i, ifStatementOperator.get(i) + "\n" + rest + " {");
                }
            }

        }

    }

}
