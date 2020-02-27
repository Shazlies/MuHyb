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
public class FunctionParamOperator {
    

    ArrayList<String> ifStatementOperator = new ArrayList<String>(); // this list contains the output of mutants
    ArrayList<File> originalFiles = new ArrayList<File>(); // this list contains the original files for each mutants
    ArrayList<MutantInformation> mutantInformation = new ArrayList<MutantInformation>(); // this firstPossibilityay contains the information of each mutant

 

    /*
     * This method takes as an input to find the mathematical operators
     * and change it to other operators then it sends all the possible strings to
     * the GenerateMutantFiles class to generate real files
     */
    boolean found = false;

    public void generateMutant(File file) {

        int lineNumber = 0; // to save the line number that we will edit in
        String beforeClass = ""; // string to save all text before the class word
        boolean startScanning = false;

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
                // ignore all operators before the class word
                if (line.contains("class") && !startScanning) {
                    startScanning = true;
                    output += beforeClass;
                } else if (!line.contains("class") && !startScanning) {
                    beforeClass += line + "\n";
                }
                // start scanning the operators between the block of the class only
                if (startScanning) {

                    ///
                    if (line.contains("(") && line.contains(")") && !found) {
                        ifStatementOperator.add(output);
                        found = true;
                        output += line + "\n";
                    } else {
                        output += line + "\n";
                    }
                    if (found) {
                        findStringInsideFunction(line);
                        //output += line + "\n";
                    }

                }

                ////
            }
            sc.close();

            // Generating the real files for each mutant by sending the mutants, original files and mutant information for each mutants
            //System.out.println(output);
            for (int i = 0; i < ifStatementOperator.size(); i++) {
//                System.out.println("-------------------");
//                System.out.println(ifStatementOperator.get(i));
//                System.out.println("-------------------");
                //System.out.println(output.replace(inside.get(i), "{}"));

            }
            if (!ifStatementOperator.isEmpty()) {
                GenerateMutantFiles gm = new GenerateMutantFiles();
                //gm.generateFiles(ifStatementOperator, originalFiles, mutantInformation);
            }

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }

    /*
     * This method takes each line that contains an operator and generate all possible mutants
     * as a string and saving all mutant information
     */
    String output = "";
    String insideFunction = "";
    int counter = 0;
    boolean functionFound = false;
    boolean done = false;
    String newString = "";
    ArrayList<String> inside = new ArrayList<String>();

    public void findStringInsideFunction(String line) {
        //System.out.println(line);
        String partOfLine = "";
        for (int i = 0; i < line.length(); i++) {
            //System.out.print(line.charAt(i));
            if (line.charAt(i) == '{') {
                counter++;
                functionFound = true;
            }
            if (line.charAt(i) == '}') {
                counter--;
            }
            if (functionFound) {
                partOfLine += line.charAt(i);

            }
            if (!functionFound) {
                newString += line.charAt(i);
            }

        }
        if (functionFound) {
            insideFunction += partOfLine + "\n";
        }
        if (counter == 0 && functionFound) {
            found = false;
            //System.out.println("done");
            //done = true;
            //if(done){
            //System.out.println("--------------------------------------");
            //System.out.println(insideFunction );
            inside.add(insideFunction);
            //System.out.println("--------------------------------------");  
            insideFunction = "";
            //}
            //System.out.println(partOfLine+" ********");
            //insideFunction += partOfLine+"\n";
            functionFound = false;
        }

        System.out.println(newString);

    }

//    public void changeInsideIf(String line, String output, File file, int lineNumber) {
//        //System.out.println(line);
//        //System.out.println();
//        int x = line.lastIndexOf("##");
//        //System.out.println(x);
//        int counter = 0;
//
//        boolean found = false;
//        int start = -1;
//        int end = -1;
//        if (x != -1) {
//            for (int i = x; i < line.length(); i++) {
//                if (line.charAt(i) == '(') {
//                    if (!found) {
//                        start = i;
//                    }
//
//                    counter++;
//                    found = true;
//                }
//                if (line.charAt(i) == ')') {
//                    counter--;
//                }
//                //System.out.println(counter);
//                if (found && counter == 0) {
//                    end = i;
//                    break;
//                }
//            }
//
////            System.out.println("start: " + start);
////            System.out.println("end: " + end);
//            if (!line.substring(start + 1, end).equals("true") && !line.substring(start + 1, end).equals("false")) {
//                String changedtoTrue = line.substring(0, start + 1) + "true" + line.substring(end, line.length());
//                changedtoTrue = changedtoTrue.replaceAll("[#][#]", "if");
//                ifStatementOperator.add(output + "\n" + changedtoTrue);
//                originalFiles.add(file);
//                MutantInformation m = new MutantInformation(lineNumber, "If Statement Operator", line.replaceAll("[#][#]", "if"), changedtoTrue);
//                mutantInformation.add(m);
////            System.out.println(changedtoTrue);
//                String changedtoFalse = line.substring(0, start + 1) + "false" + line.substring(end, line.length());
//                changedtoFalse = changedtoFalse.replaceAll("[#][#]", "if");
//                ifStatementOperator.add(output + "\n" + changedtoFalse);
//                originalFiles.add(file);
//                m = new MutantInformation(lineNumber, "If Statement Operator", line.replaceAll("[#][#]", "if"), changedtoFalse);
//                mutantInformation.add(m);
//            }
//
////            System.out.println(output+"\n"+changedtoFalse);
//        }
//        //System.out.println(output);
//        //System.out.println("---------------");
//    }
}
