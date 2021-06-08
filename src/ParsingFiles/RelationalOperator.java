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
public class RelationalOperator {

    ArrayList<String> relationallOperators = new ArrayList<String>(); // this list contains the output of mutants
    ArrayList<File> originalFiles = new ArrayList<File>(); // this list contains the original files for each mutants
    ArrayList<MutantInformation> mutantInformation = new ArrayList<MutantInformation>(); // this firstPossibilityay contains the information of each mutant



    /*
     * This method takes as an input to find the mathematical operators
     * and change it to other operators then it sends all the possible strings to
     * the GenerateMutantFiles class to generate real files
     */
    public void generateMutant(File file, int threadNumbers) {

        String outputGreater = ""; // string to save file text for plus operator
        int lineNumber = 0; // to save the line number that we will edit in
        String outputLess = ""; // string to save file text for minus operator
        String outputGreaterOrEqual = ""; // string to save file text for times operator
        String outputLessOrEqual = ""; // string to save file text for divide operator
        String beforeClass = ""; // string to save all text before the class word

        boolean startScanning = false;
        try {
            Scanner sc = new Scanner(file);
            // scan the file
            while (sc.hasNext()) {
                lineNumber++;

                String line = sc.nextLine();

                // pasting the rest of the code in all mutants
                for (int i = 0; i < relationallOperators.size(); i++) {
                    String o = relationallOperators.get(i) + line + "\n";
                    relationallOperators.set(i, o);
                }
                // ignore all operators before the class word
                if (line.contains("class") && !startScanning) {
                    startScanning = true;
                    outputGreater += beforeClass;
                    outputLess += beforeClass;
                    outputGreaterOrEqual += beforeClass;
                    outputLessOrEqual += beforeClass;
                } else if (!line.contains("class") && !startScanning) {
                    beforeClass += line + "\n";
                }
                // start scanning the operators between the block of the class only
                if (startScanning) {
                    line = line.replace(">=", "@@"); // replace >= to @@ before scanning for > operator
                    line = line.replace("=>", "##"); // replace >= to @@ before scanning for > operator
                    if (line.contains(">")&&!line.contains("for")&&!line.contains("while")) {
                        setOperator(line, outputGreater, file, lineNumber, ">", ">=", "<", "<=", ">=");
                        line = line.replace("@@", ">="); // go back and replace @@ to >= before scanning for < operator
                        line = line.replace("##", "=>"); // go back and replace @@ to >= before scanning for < operator
                        outputGreater += line + "\n"; // keep updating the string if found 
                    } else {
                        outputGreater += line.replace("@@", ">=") + "\n"; // keep updating the string if  not found
                    }

                    line = line.replace("@@", ">="); // go back and replace @@ to >= before scanning for < operator
                    line = line.replace("##", "=>"); // go back and replace @@ to >= before scanning for < operator
                    line = line.replace("<=", "@@"); // replace <= to @@ before scanning for < operator
                    if (line.contains("<")&&!line.contains("for")&&!line.contains("while")) {
                        setOperator(line, outputLess, file, lineNumber, "<", "<=", ">", ">=", "<=");
                        outputLess += line.replace("@@", "<=") + "\n"; // keep updating the string if found 
                    } else {
                        outputLess += line.replace("@@", "<=") + "\n"; // keep updating the string if  not found
                    }
                    line = line.replace("@@", "<=");
                    if (line.contains(">=")&&!line.contains("for")&&!line.contains("while")) {
                        setOperator(line, outputGreaterOrEqual, file, lineNumber, ">=", "", ">", "<", "<=");
                        //setTimesOperator(line, number, outputTimes, file, lineNumber);

                        outputGreaterOrEqual += line + "\n"; // keep updating the string if found 
                    } else {
                        outputGreaterOrEqual += line + "\n"; // keep updating the string if  not found
                    }
                    if (line.contains("<=")&&!line.contains("for")&&!line.contains("while")) {
                        setOperator(line, outputLessOrEqual, file, lineNumber, "<=", "", "<", ">", ">=");
                        outputLessOrEqual += line + "\n"; // keep updating the string if found 
                    } else {
                        outputLessOrEqual += line + "\n"; // keep updating the string if  not found
                    }

                }

                ////
            }
            sc.close();

            // Generating the real files for each mutant by sending the mutants, original files and mutant information for each mutants
            if (!relationallOperators.isEmpty()) {
                GenerateMutantFiles gm = new GenerateMutantFiles();
                gm.generateFiles(relationallOperators, originalFiles, mutantInformation, threadNumbers);
            }

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }

    /*
     * This method takes each line that contains an operator and generate all possible mutants
     * as a string and saving all mutant information
     */
    public void setOperator(String line, String output1, File file, int lineNumber, String originalOperator, String operatorReplacement, String fisrtOperator, String secondOperator, String thirdOperator) {

        String originalOperator2 = "";
        for (int i = 0; i < originalOperator.length(); i++) {
            originalOperator2 += "[" + originalOperator.charAt(i) + "]";
        }

        String tokens[] = line.split(originalOperator2); // split the line to tokens example: a = b+c; >> first token is: a = b and second token is c;

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
                            partLine = partLine.replace("[#][#]", "=>");
                            firstPossibility[j] += partLine.replace("@@", operatorReplacement) + fisrtOperator;
                            secondPossibility[j] += partLine.replace("@@", operatorReplacement) + secondOperator;
                            thirdPossibility[j] += partLine.replace("@@", operatorReplacement) + thirdOperator;
                        }

                    } else {
                        // add the original operator to the rest of each elemnt
                        partLine = partLine.replace("[#][#]", "=>");
                        firstPossibility[j] += partLine.replace("@@", operatorReplacement) + originalOperator;
                        secondPossibility[j] += partLine.replace("@@", operatorReplacement) + originalOperator;
                        thirdPossibility[j] += partLine.replace("@@", operatorReplacement) + originalOperator;
                    }

                }
                counter++;
            }
        }
        line = line.replace("@@", operatorReplacement);
        line = line.replace("[#][#]", "=>");
        for (int i = 0; i < number; i++) {
            // add mutants but not the ones that contains $^%&%& sign we added before [it mean the operator between ' or "]
            if (!firstPossibility[i].contains("$^%&%&")) {
                firstPossibility[i] = firstPossibility[i].substring(0, firstPossibility[i].length() - originalOperator.length());
                MutantInformation m = new MutantInformation(lineNumber, "Relational Operator", line, firstPossibility[i]);
                mutantInformation.add(m);
                relationallOperators.add(output1 + firstPossibility[i] + "\n");
                originalFiles.add(file);
            }
            if (!secondPossibility[i].contains("$^%&%&")) {
                secondPossibility[i] = secondPossibility[i].substring(0, secondPossibility[i].length() - originalOperator.length());
                MutantInformation m = new MutantInformation(lineNumber, "Relational Operator", line, secondPossibility[i]);
                mutantInformation.add(m);
                relationallOperators.add(output1 + secondPossibility[i] + "\n");
                originalFiles.add(file);
            }
            if (!thirdPossibility[i].contains("$^%&%&")) {
                thirdPossibility[i] = thirdPossibility[i].substring(0, thirdPossibility[i].length() - originalOperator.length());
                MutantInformation m = new MutantInformation(lineNumber, "Relational Operator", line, thirdPossibility[i]);
                mutantInformation.add(m);
                relationallOperators.add(output1 + thirdPossibility[i] + "\n");
                originalFiles.add(file);
            }
        }
    }
}
