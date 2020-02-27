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
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Home
 */
public class StatementDeletion5 {

    ArrayList<String> mathematicalOperators = new ArrayList<String>(); // this list contains the output of mutants
    ArrayList<File> originalFiles = new ArrayList<File>(); // this list contains the original files for each mutants
    ArrayList<MutantInformation> mutantInformation = new ArrayList<MutantInformation>(); // this firstPossibilityay contains the information of each mutant

    /*
     * This method takes as an input to find the mathematical operators
     * and change it to other operators then it sends all the possible strings to
     * the GenerateMutantFiles class to generate real files
     */
    public static void main(String[] args) {
        File f = new File("E:\\projects\\test\\.Mutant0\\src\\app\\about\\about.page.ts");
        StatementDeletion5 b = new StatementDeletion5();
        b.generateMutant(f);

    }

    public void generateMutant(File file) {
        String outputPlus = ""; // string to save file text for plus operator
        int lineNumber = 0; // to save the line number that we will edit in
        String beforeClass = "";
        // string to save all text before the class word
        boolean startScanning = false;
        try {
            Scanner sc = new Scanner(file);
            // scan the file
            while (sc.hasNext()) {
                lineNumber++;

                String line = sc.nextLine();

                // pasting the rest of the code in all mutants
                for (int i = 0; i < mathematicalOperators.size(); i++) {
                    String o = mathematicalOperators.get(i) + line + "\n";
                    mathematicalOperators.set(i, o);
                }
                // ignore all operators before the class word
                if (line.contains("class") && !startScanning) {
                    startScanning = true;
                    outputPlus += beforeClass;

                } else if (!line.contains("class") && !startScanning) {
                    beforeClass += line + "\n";
                }
                // start scanning the operators between the block of the class only
                if (startScanning) {

                    if (line.contains("+")) {
                        setOperator(line, outputPlus, file, lineNumber, "+", "++", "-", "*", "/");
                        outputPlus += line + "\n"; // keep updating the string if found 
                    } else {
                        outputPlus += line + "\n"; // keep updating the string if  not found
                    }

                }

                ////
            }
            sc.close();

            // Generating the real files for each mutant by sending the mutants, original files and mutant information for each mutants
            if (!mathematicalOperators.isEmpty()) {
                GenerateMutantFiles gm = new GenerateMutantFiles();
                gm.generateFiles(mathematicalOperators, originalFiles, mutantInformation, 0);
            }

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }

    /*
     * This method takes each line that contains an operator and generate all possible mutants
     * as a string and saving all mutant information
     */
    public void deteStatement() {

    }

    public void setOperator(String line, String output1, File file, int lineNumber, String originalOperator, String operatorReplacement, String fisrtOperator, String secondOperator, String thirdOperator) {
        String tokens[] = line.split("[" + originalOperator + "]"); // split the line to tokens example: a = b+c; >> first token is: a = b and second token is c;
        int number = tokens.length - 1;
        // arrays that will contains the possibilities of all mutants
        String firstPossibility[] = new String[number];
        String secondPossibility[] = new String[number];
        String thirdPossibility[] = new String[number];
        // emptying the arrays
        for (int i = 0; i < number; i++) {
            firstPossibility[i] = "";
            secondPossibility[i] = "";
            thirdPossibility[i] = "";
        }
        int counter = 0;
        int singleQuoteCounter = 0; // counts how many ' found
        int doubleQuotesCounter = 0; // counts how many " found
        if (tokens.length > 1) {

            for (int i = 0; i < tokens.length; i++) {
                String partLine = tokens[i];
                singleQuoteCounter += StringUtils.countMatches(partLine, "\'");
                doubleQuotesCounter += StringUtils.countMatches(partLine, "\"");
                for (int j = 0; j < number; j++) {
                    if (counter == j) {
                        if (singleQuoteCounter % 2 != 0 || doubleQuotesCounter % 2 != 0) {
                            // single quote or a double quotes found add a sign $^%&%& to remove this later
                            firstPossibility[j] = "$^%&%&";
                            secondPossibility[j] = "$^%&%&";
                            thirdPossibility[j] = "$^%&%&";
                        } else {
                            // change the operator to other operators 
                            firstPossibility[j] += partLine.replaceAll("[@][@]", operatorReplacement) + fisrtOperator;
                            secondPossibility[j] += partLine.replaceAll("[@][@]", operatorReplacement) + secondOperator;
                            thirdPossibility[j] += partLine.replaceAll("[@][@]", operatorReplacement) + thirdOperator;
                        }

                    } else {
                        // add the original operator to the rest of each elemnt
                        firstPossibility[j] += partLine.replaceAll("[@][@]", operatorReplacement) + originalOperator;
                        secondPossibility[j] += partLine.replaceAll("[@][@]", operatorReplacement) + originalOperator;
                        thirdPossibility[j] += partLine.replaceAll("[@][@]", operatorReplacement) + originalOperator;
                    }

                }
                counter++;
            }
        }
        line = line.replaceAll("[@][@]", operatorReplacement);
        for (int i = 0; i < number; i++) {
            // add mutants but not the ones that contains $^%&%& sign we added before [it mean the operator between ' or "]
            if (!firstPossibility[i].contains("$^%&%&")) {
                firstPossibility[i] = firstPossibility[i].substring(0, firstPossibility[i].length() - originalOperator.length());
                MutantInformation m = new MutantInformation(lineNumber, "Math Operator", line, firstPossibility[i]);
                mutantInformation.add(m);
                mathematicalOperators.add(output1 + firstPossibility[i] + "\n");
                originalFiles.add(file);
            }
            if (!secondPossibility[i].contains("$^%&%&")) {
                secondPossibility[i] = secondPossibility[i].substring(0, secondPossibility[i].length() - originalOperator.length());
                MutantInformation m = new MutantInformation(lineNumber, "Math Operator", line, secondPossibility[i]);
                mutantInformation.add(m);
                mathematicalOperators.add(output1 + secondPossibility[i] + "\n");
                originalFiles.add(file);
            }
            if (!thirdPossibility[i].contains("$^%&%&")) {
                thirdPossibility[i] = thirdPossibility[i].substring(0, thirdPossibility[i].length() - originalOperator.length());
                MutantInformation m = new MutantInformation(lineNumber, "Math Operator", line, thirdPossibility[i]);
                mutantInformation.add(m);
                mathematicalOperators.add(output1 + thirdPossibility[i] + "\n");
                originalFiles.add(file);
            }
        }
    }

}
