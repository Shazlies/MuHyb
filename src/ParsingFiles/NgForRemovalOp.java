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
import java.util.StringTokenizer;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Home
 */
public class NgForRemovalOp {

    ArrayList<String> equalityOperators = new ArrayList<String>(); // this list contains the output of mutants
    ArrayList<File> originalFiles = new ArrayList<File>(); // this list contains the original files for each mutants
    ArrayList<MutantInformation> mutantInformation = new ArrayList<MutantInformation>(); // this firstPossibilityay contains the information of each mutant

    public static void main(String[] args) {
        File f = new File("E:\\projects\\PizzaHouse6\\_Mutant0\\src\\app\\pizza\\pizza.page.html");
        NgForRemovalOp eo = new NgForRemovalOp();
        eo.generateMutant(f, 4);
    }

    /*
     * This method takes as an input to find the mathematical operators
     * and change it to other operators then it sends all the possible strings to
     * the GenerateMutantFiles class to generate real files
     */
    public void generateMutant(File file, int threadNumbers) {

        int lineNumber = 0; // to save the line number that we will edit in
        String outputEqualEqual = "";
        try {
            Scanner sc = new Scanner(file);
            // scan the file
            while (sc.hasNext()) {
                lineNumber++;

                String line = sc.nextLine();

                // pasting the rest of the code in all mutants
                for (int i = 0; i < equalityOperators.size(); i++) {
                    String o = equalityOperators.get(i) + line + "\n";
                    equalityOperators.set(i, o);
                }
                // ignore all operators before the class word

                // start scanning the operators between the block of the class only
                if (line.contains("*ngFor")) {
                    setOperator(line, outputEqualEqual, file, lineNumber, "", "", "");
                    outputEqualEqual += line + "\n"; // keep updating the string if found 
                } else if (line.contains("*ngIf")) {
                    setOperator2(line, outputEqualEqual, file, lineNumber, "", "", "");
                    outputEqualEqual += line + "\n"; // keep updating the string if found 
                } else {
                    outputEqualEqual += line + "\n"; // keep updating the string if  not found
                }

                ////
            }
            sc.close();

            // Generating the real files for each mutant by sending the mutants, original files and mutant information for each mutants
            if (!equalityOperators.isEmpty()&&(equalityOperators.size()==originalFiles.size()&&originalFiles.size()==mutantInformation.size())) {
                GenerateMutantFiles gm = new GenerateMutantFiles();
                gm.generateFiles(equalityOperators, originalFiles, mutantInformation, threadNumbers);
            }

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }

    /*
     * This method takes each line that contains an operator and generate all possible mutants
     * as a string and saving all mutant information
     */
    public void setOperator(String line, String output1, File file, int lineNumber, String originalOperator, String operatorReplacement, String fisrtOperator) {

//        //String originalOperator2 = "";
//        for (int i = 0; i < originalOperator.length(); i++) {
//            originalOperator2 += "[" + originalOperator.charAt(i) + "]";
//        }
//
//        String tokens[] = line.split(originalOperator2); // split the line to tokens example: a = b+c; >> first token is: a = b and second token is c;
//
//        int number = tokens.length - 1;
        // arrays that will contains the possibilities of all mutants
        String output = "";
        int count = 0;
        boolean found = false;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '*') {
                found = true;
            }
            if (found) {
                output += line.charAt(i);
            }
            if (line.charAt(i) == '\"' && found) {
                count++;
            }
            if (count == 2) {
                found = false;
                break;
            }

        }
        System.out.println("========================");
        System.out.println(output);
        System.out.println("========================");

        String tokens[] = output.split("let");
        System.out.println(tokens.length);
        int index = -1;
        for (int i = 0; i < tokens.length; i++) {
            System.out.println(tokens[i]);
            if (tokens[i].contains(" of ")) {
                System.out.println("TES");
                index = i;
                break;
            }
        }
        System.out.println("--------------------");
        if (index != -1) {

            String y = tokens[index];
            StringTokenizer s = new StringTokenizer(y);
            String[] out = new String[s.countTokens()];
            int i = 0;
            while (s.hasMoreTokens()) {
                String word = s.nextToken();
                out[i] = word;
                i++;
            }
            int index2 = -1;
            String lastOutput = "*ngIf=\"";
            for (int j = 0; j < out.length; j++) {
                if (out[j].contains("of")) {
                    index2 = j;
                }
            }

            if (index2 != -1 && index != 0) {
                String firstHalf = out[index2 - 1];
                String secondHalf = out[index2 + 1];
                String lastHalf = "";
                String thirdHalf = "";
                boolean done = false;
                for (int j = 0; j < secondHalf.length(); j++) {
                    char a = secondHalf.charAt(j);
                    if (Character.isLetter(a) && !done) {
                        thirdHalf += a;
                    } else {
                        done = true;
                    }
                    if (done) {
                        lastHalf += a;
                    }
                }
                lastOutput += thirdHalf + "[0] " + "as " + firstHalf + " " + lastHalf;

                for (int j = index2 + 2; j < out.length; j++) {
                    lastOutput += out[j] + " ";
                }

                String finalOutput = line.replace(output, lastOutput);
                System.out.println(finalOutput);
                // add mutants but not the ones that contains $^%&%& sign we added before [it mean the operator between ' or "]
                MutantInformation m = new MutantInformation(lineNumber, "Structural Directives Replace Operator", line, finalOutput);
                mutantInformation.add(m);
                equalityOperators.add(output1 + finalOutput + "\n");
                originalFiles.add(file);
            }

        }

    }

    public void setOperator2(String line, String output1, File file, int lineNumber, String originalOperator, String operatorReplacement, String fisrtOperator) {

//        //String originalOperator2 = "";
//        for (int i = 0; i < originalOperator.length(); i++) {
//            originalOperator2 += "[" + originalOperator.charAt(i) + "]";
//        }
//
//        String tokens[] = line.split(originalOperator2); // split the line to tokens example: a = b+c; >> first token is: a = b and second token is c;
//
//        int number = tokens.length - 1;
        // arrays that will contains the possibilities of all mutants
        String output = "";
        int count = 0;
        boolean found = false;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '*') {
                found = true;
            }
            if (found) {
                output += line.charAt(i);
            }
            if (line.charAt(i) == '\"' && found) {
                count++;
            }
            if (count == 2) {
                found = false;
                break;
            }

        }
        System.out.println("============ngIf============");
        System.out.println(output);
        System.out.println("========================");

        String finalOutput1 = line.replace(output, "*ngIf=\"true\"");
        String finalOutput2 = line.replace(output, "*ngIf=\"false\"");
        String finalOutput3 = line.replace(output, "*ngIf=\"null\"");
        System.out.println(finalOutput1);
        System.out.println(finalOutput2);
        System.out.println(finalOutput3);
//        String tokens[] = output.split("let");
//        System.out.println(tokens.length);
//        int index = -1;
//        for (int i = 0; i < tokens.length; i++) {
//            System.out.println(tokens[i]);
//            if (tokens[i].contains(" of ")) {
//                System.out.println("TES");
//                index = i;
//                break;
//            }
//        }
//        System.out.println("--------------------");
//        if (index != -1) {
//
//            String y = tokens[index];
//            StringTokenizer s = new StringTokenizer(y);
//            String[] out = new String[s.countTokens()];
//            int i = 0;
//            while (s.hasMoreTokens()) {
//                String word = s.nextToken();
//                out[i] = word;
//                i++;
//            }
//            int index2 = -1;
//            String lastOutput = "*ngIf=\"";
//            for (int j = 0; j < out.length; j++) {
//                if (out[j].contains("of")) {
//                    index2 = j;
//                }
//            }
//
//            if (index2 != -1 && index != 0) {
//                String firstHalf = out[index2 - 1];
//                String secondHalf = out[index2 + 1];
//                String lastHalf = "";
//                String thirdHalf = "";
//                boolean done = false;
//                for (int j = 0; j < secondHalf.length(); j++) {
//                    char a = secondHalf.charAt(j);
//                    if (Character.isLetter(a) && !done) {
//                        thirdHalf += a;
//                    } else {
//                        done = true;
//                    }
//                    if (done) {
//                        lastHalf += a;
//                    }
//                }
//                lastOutput += thirdHalf + "[0] " + "as " + firstHalf + " " + lastHalf;
//
//                for (int j = index2 + 2; j < out.length; j++) {
//                    lastOutput += out[j] + " ";
//                }
//
//                String finalOutput = line.replace(output, lastOutput);
//                System.out.println(finalOutput);
        // add mutants but not the ones that contains $^%&%& sign we added before [it mean the operator between ' or "]
        MutantInformation m = new MutantInformation(lineNumber, "Structural Directives Replace Operator", line, finalOutput1);
        mutantInformation.add(m);
        equalityOperators.add(output1 + finalOutput1 + "\n");
        originalFiles.add(file);
        
        
        MutantInformation m2 = new MutantInformation(lineNumber, "Structural Directives Replace Operator", line, finalOutput2);
        mutantInformation.add(m2);
        equalityOperators.add(output1 + finalOutput2 + "\n");
        originalFiles.add(file);
        MutantInformation m3 = new MutantInformation(lineNumber, "Structural Directives Replace Operator", line, finalOutput3);
        mutantInformation.add(m3);
        equalityOperators.add(output1 + finalOutput3 + "\n");
        originalFiles.add(file);
    }

}
