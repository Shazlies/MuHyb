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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Home
 */
public class LoopOperator {

    ArrayList<String> ifStatementOperator = new ArrayList<String>(); // this list contains the output of mutants
    ArrayList<File> originalFiles = new ArrayList<File>(); // this list contains the original files for each mutants
    ArrayList<MutantInformation> mutantInformation = new ArrayList<MutantInformation>(); // this firstPossibilityay contains the information of each mutant

//    public static void main(String[] args) {
//        File file = new File("E:\\projects\\test\\src\\app\\home\\home.page.ts");
//        LoopOperator loop = new LoopOperator();
//        loop.generateMutant(file, 0);
//    }


    /*
     * This method takes as an input to find the mathematical operators
     * and change it to other operators then it sends all the possible strings to
     * the GenerateMutantFiles class to generate real files
     */
    public void generateMutant(File file, int threadNumbers) {

        int lineNumber = 0; // to save the line number that we will edit in
        String beforeClass = ""; // string to save all text before the class word
        boolean startScanning = false;
        try {
            Scanner sc = new Scanner(file);
            // scan the file
            while (sc.hasNext()) {
                lineNumber++;

                String line = sc.nextLine();

                // pasting the rest of the code in all mutants
                for (int i = 0; i < ifStatementOperator.size(); i++) {
                    String o = ifStatementOperator.get(i) + line + "\n";
                    ifStatementOperator.set(i, o);
                }
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
                    if (line.length() >= 5) {
                        if (line.contains("for")) {
                            String pattern = "\\b" + "for" + "\\b";
                            Pattern p = Pattern.compile(pattern);
                            Matcher m = p.matcher(line);

                            if (m.find()) {
                                p = Pattern.compile(pattern);
                                m = p.matcher(line);
                                //while (m.find()) {
                                //System.out.println(line);
                                //line = line.replaceFirst("if", "##");
                                changeInsideLoop(line, output, file, lineNumber);
                                //System.out.println(line);

                                //}
                                output += line + "\n";
                                //System.out.println(m.find());
                            } else {
                                output += line + "\n";
                            }

                        } else if (line.contains("while")) {
                            String pattern = "\\b" + "while" + "\\b";
                            Pattern p = Pattern.compile(pattern);
                            Matcher m = p.matcher(line);

                            if (m.find()) {
                                p = Pattern.compile(pattern);
                                m = p.matcher(line);
                                //while (m.find()) {
                                //System.out.println(line);
                                //line = line.replaceFirst("if", "##");
                                changeInsideLoop(line, output, file, lineNumber);
                                //System.out.println(line);

                                //}
                                output += line + "\n";
                                //System.out.println(m.find());
                            } else {
                                output += line + "\n";
                            }

                        } else {
                            output += line + "\n";
                        }
                    } else {
                        output += line + "\n";
                    }

                }

                ////
            }
            sc.close();

            // Generating the real files for each mutant by sending the mutants, original files and mutant information for each mutants
            if (!ifStatementOperator.isEmpty()) {
                GenerateMutantFiles gm = new GenerateMutantFiles();
                gm.generateFiles(ifStatementOperator, originalFiles, mutantInformation, threadNumbers);
            }
//            for (int i = 0; i < ifStatementOperator.size(); i++) {
//                System.out.println(ifStatementOperator.get(i));
//                System.out.println("===================================");
//            }

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }

    /*
     * This method takes each line that contains an operator and generate all possible mutants
     * as a string and saving all mutant information
     */
    String output = "";
//
//    private void extractIfCondition(String x) {
//
//        //return m.find();
//        Scanner s = new Scanner(x);
//        while (s.hasNext()) {
//            String line = s.nextLine();
//            for (int i = 0; i < ifStatementOperator.size(); i++) {
//                String l = ifStatementOperator.get(i);
//                l += line + "\n";
//                ifStatementOperator.set(i, l);
//            }
//            if (line.length() >= 5) {
//                if (line.contains("if")) {
//                    String pattern = "\\b" + "if" + "\\b";
//                    Pattern p = Pattern.compile(pattern);
//                    Matcher m = p.matcher(line);
//
//                    if (m.find()) {
//                        p = Pattern.compile(pattern);
//                        m = p.matcher(line);
//                        while (m.find()) {
//                            //System.out.println(line);
//                            line = line.replaceFirst("if", "##");
//                            changeInsideIf(line, output, file,lineNumber );
//                            //System.out.println(line);
//
//                        }
//                        output += line.replaceAll("[#][#]", "if") + "\n";
//                        //System.out.println(m.find());
//                    } else {
//                        output += line + "\n";
//                    }
//
//                } else {
//                    output += line + "\n";
//                }
//            } else {
//                output += line + "\n";
//            }
//        }
//        for (int i = 0; i < ifStatementOperator.size(); i++) {
//            System.out.println(ifStatementOperator.get(i));
//            System.out.println("====================");
//        }
//        // System.out.println(output);
//    }

    public void changeInsideLoop(String line, String output, File file, int lineNumber) {
        //System.out.println(line);
        //System.out.println();
        //int x = line.lastIndexOf("##");
        //System.out.println(x);
        if (line.contains("for")) {
            String[] tokens = line.split("[;]");
            if (tokens.length > 1) {
                String newLine = tokens[0] + ";" + "true" + ";" + tokens[2];
                //System.out.println(newLine);
                ifStatementOperator.add(output + newLine + "\n");
                originalFiles.add(file);
                String newLine2 = tokens[0] + ";" + "false" + ";" + tokens[2];
                ifStatementOperator.add(output + newLine2 + "\n");
                originalFiles.add(file);
                //System.out.println(newLine2);
                MutantInformation m = new MutantInformation(lineNumber, "Loop Operator", line, newLine);
                mutantInformation.add(m);
                m = new MutantInformation(lineNumber, "Loop Operator", line, newLine2);
                mutantInformation.add(m);
            }

        }

        if (line.contains("while")) {
            String[] tokens = line.split("[(]");
            if (tokens.length > 1) {

                if (tokens[tokens.length - 1].contains("{")) {
                    String newLine = tokens[0] + "(" + "true" + "){";
                    ifStatementOperator.add(output + newLine + "\n");
                    originalFiles.add(file);
                    String newLine2 = tokens[0] + "(" + "false" + "){";
                    ifStatementOperator.add(output + newLine2 + "\n");
                    originalFiles.add(file);
                    MutantInformation m = new MutantInformation(lineNumber, "Loop Operator", line, newLine);
                    mutantInformation.add(m);
                    m = new MutantInformation(lineNumber, "Loop Operator", line, newLine2);
                    mutantInformation.add(m);

                } else {
                    String newLine = tokens[0] + "(" + "true" + ")";
                    ifStatementOperator.add(output + newLine + "\n");
                    originalFiles.add(file);
                    String newLine2 = tokens[0] + "(" + "false" + ")";
                    ifStatementOperator.add(output + newLine2 + "\n");
                    originalFiles.add(file);
                    MutantInformation m = new MutantInformation(lineNumber, "Loop Operator", line, newLine);
                    mutantInformation.add(m);
                    m = new MutantInformation(lineNumber, "Loop Operator", line, newLine2);
                    mutantInformation.add(m);
                }
            }
            //System.out.println(newLine);
//              String newLine2 = tokens[0]+"("+"false"+")";
//              ifStatementOperator.add(output+newLine2+"\n");
            //System.out.println(newLine2);
        }

//        for (int i = 0; i < tokens.length; i++) {
//            System.out.println(tokens[i]);
//        }
        int counter = 0;

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
//            System.out.println("start: " + start);
//            System.out.println("end: " + end);
//            if (!line.substring(start + 1, end).equals("true") &&! line.substring(start + 1, end).equals("false")) {
//                String changedtoTrue = line.substring(0, start + 1) + "true" + line.substring(end, line.length());
//                changedtoTrue = changedtoTrue.replaceAll("[#][#]", "if");
//                ifStatementOperator.add(output + "\n" + changedtoTrue);
//                originalFiles.add(file);
//                MutantInformation m = new MutantInformation(lineNumber, "Loop Operator", line.replaceAll("[#][#]", "if"), changedtoTrue);
//                mutantInformation.add(m);
////            System.out.println(changedtoTrue);
//                String changedtoFalse = line.substring(0, start + 1) + "false" + line.substring(end, line.length());
//                changedtoFalse = changedtoFalse.replaceAll("[#][#]", "if");
//                ifStatementOperator.add(output + "\n" + changedtoFalse);
//                originalFiles.add(file);
//                m = new MutantInformation(lineNumber, "Loop Operator", line.replaceAll("[#][#]", "if"), changedtoFalse);
//                mutantInformation.add(m);
//            }
//            System.out.println(output+"\n"+changedtoFalse);
    }
    //System.out.println(output);
    //System.out.println("---------------");
}
//}
