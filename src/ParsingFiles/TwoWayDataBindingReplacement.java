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
 * @author Shad0o0
 */
public class TwoWayDataBindingReplacement {

    ArrayList<String> equalityOperators = new ArrayList<String>(); // this list contains the output of mutants
    ArrayList<File> originalFiles = new ArrayList<File>(); // this list contains the original files for each mutants
    ArrayList<MutantInformation> mutantInformation = new ArrayList<MutantInformation>(); // this firstPossibilityay contains the information of each mutant
    ArrayList<String> mutationPart = new ArrayList<String>();
    ArrayList<String> originalLine = new ArrayList<String>();
    ArrayList<String> mutantLine = new ArrayList<String>();
    ArrayList<Integer> lineNumber = new ArrayList<Integer>();

    public static void main(String[] args) {
        File f = new File("D:\\projects2\\infoapp\\_Mutant0\\src\\app\\pages\\info\\info.page.html");
        TwoWayDataBindingReplacement eo = new TwoWayDataBindingReplacement();
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
//                for (int i = 0; i < equalityOperators.size(); i++) {
//                    String o = equalityOperators.get(i) + line + "\n";
//                    equalityOperators.set(i, o);
//                }
                // ignore all operators before the class word
                // start scanning the operators between the block of the class only
                if (line.contains("[(") && line.contains(")]")) {
                    setOperator(line, outputEqualEqual, file, lineNumber, "", "", "");
                    outputEqualEqual += line + "\n"; // keep updating the string if found
                } else {
                    outputEqualEqual += line + "\n"; // keep updating the string if  not found
                }

                ////
            }
            sc.close();
            if (mutationPart.size() > 1) {
                Scanner sc2 = new Scanner(file);
                int counter = 0;
                System.out.println(this.lineNumber.size());
                while (sc2.hasNext()) {
                    counter++;
                    String line = sc2.nextLine();
                    for (int i = 0; i < this.lineNumber.size(); i++) {
                        if (counter == this.lineNumber.get(i)) {
                            for (int j = 0; j < mutationPart.size(); j++) {
                                if (!mutationPart.get(i).equals(mutationPart.get(j))) {
                                    originalLine.add(line);

                                    String line2 = line.replace(mutationPart.get(i), mutationPart.get(j));
                                    MutantInformation m = new MutantInformation(this.lineNumber.get(i), "Data Binding Operator", line.trim(), line2.trim());
                                    System.out.println("line2: " + line2.trim());
                                    mutantLine.add(line2);
                                    equalityOperators.set(i, equalityOperators.get(i) + line2 + "\n");

                                    System.out.println(m);
                                    mutantInformation.add(m);
                                    break;
                                }
                            }
                            //System.out.println(line);
                            //System.out.println("YES");
                        } else {
                            equalityOperators.set(i, equalityOperators.get(i) + line + "\n");
                        }

                    }
                }
                //MutantInformation m = new MutantInformation(lineNumber, "Interpolation Operator", line, firstPossibility[i]);
//                mutantInformation.add(m);
                for (int i = 0; i < equalityOperators.size(); i++) {
                    System.out.println("=====================================");
                    System.out.println(equalityOperators.get(i));

                }
            }

            // Generating the real files for each mutant by sending the mutants, original files and mutant information for each mutants
            if (!equalityOperators.isEmpty()&&(equalityOperators.size()==originalFiles.size()&&originalFiles.size()==mutantInformation.size())) {
                GenerateMutantFiles gm = new GenerateMutantFiles();
                System.out.println(equalityOperators.size());
                System.out.println(originalFiles.size());
                System.out.println(mutantInformation.size());
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
if (!line.contains("<!--") || !line.contains("--")) {

            int f1 = 0;
            boolean f1Bool = false;
            int f2 = 0;
            boolean f2Bool = false;
            int f3 = 0;
            boolean f3Bool = false;
            int f4 = 0;
            boolean f4Bool = false;
            int f5 = 0;
            boolean f5Bool = false;
            boolean found = false;
//            System.out.println("Lineeeeeeeeeeeee: " + line);
//            System.out.println(line.length());
            for (int i = 0; i < line.length(); i++) {
                char a = line.charAt(i);
                if (a == '[' && !f1Bool && !f2Bool && !f3Bool && !f4Bool&&line.charAt(i+1)=='(') {
//                    System.out.println("yes1");
//                    System.out.println(i);
                    f1 = i;
                    f1Bool = true;
                }

                if (a == ']' && f1Bool && !f2Bool && !f3Bool && !f4Bool && i > f1 + 2 && line.charAt(i-1)==')') {
//                    System.out.println("yes2");
//                    System.out.println(i);
                    f2 = i;
                    f2Bool = true;
                }
                if (a == '=' && f1Bool && f2Bool && !f3Bool && !f4Bool && i < f2 + 2) {
//                    System.out.println("yes3");
//                    System.out.println(i);
                    f3 = i;
                    f3Bool = true;
                }
                if ((a == '\"' || a == '\'') && f1Bool && f2Bool && f3Bool && !f4Bool && i < f3 + 2) {
//                    System.out.println("yes4");
//                    System.out.println(i);
                    f4 = i;
                    f4Bool = true;
                } else if ((a == '\"' || a == '\'') && f4Bool) {
//                    System.out.println("yes5");
//                    System.out.println(i);
                    f5 = i;
                    f5Bool = true;
                }
                if (f5Bool) {
                    //System.out.println("asdasdsad");
                    //System.out.println();
                    String line2 = line.substring(f1, f5 + 1);
                    System.out.println("part: " + line2);

                    mutationPart.add(line2);
                    equalityOperators.add("");
                    originalFiles.add(file);
                    this.lineNumber.add(lineNumber);
                    break;

                }

//                if (f1Bool && f2Bool) {
//
//                    if (line.trim().substring(f1, f2 + 1).contains("click") && f2 > f1 + 1) {
//                        System.out.println("YESSSSSSSSSSS");
//                        System.out.println(f1);
//                        System.out.println(f2);
//                        System.out.println(line.trim().substring(f1, f2 + 1));
//                        found = true;
//                    }
//                }
//                if (found) {
//                    if (a == '=') {
//                        f3 = i;
//                        if (f2 + 2 > f3) {
//                            System.out.println("failed1");
//                            found = false;
//                            f1Bool = false;
//                            f2Bool = false;
//
//                        } else {
//                            
//                        }
//                    }
//                }
//            if(line.substring(f1, f2).contains("click")){
//                System.out.println(line); 
//            }
            }

//            System.out.println(line.trim());
//            int c1 = line.indexOf("(");
//            int c2 = line.indexOf(")");
//            String line2 = line.substring(c1, line.length());
//            //line2 = line2.substring(c1, line.length());
//            line2 = line2.replaceFirst("\"", "@@@");
//            int c4 = line.indexOf("\"");
//            line2 = line2.substring(0, c4 + 1);
//            line2 = line2.replaceFirst("@@@", "\"");
//
//            if (!line.contains("(click")) {
//                System.out.println("HEREEEEEEEEEEEEEEEE");
//                System.out.println(line2);
//                mutationPart.add(line2);
//                equalityOperators.add("");
//                originalFiles.add(file);
//                this.lineNumber.add(lineNumber);
//            }
        }

    }
}
