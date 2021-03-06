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
 * @author sphinx
 */
public class InterplationValueReplacement {

    ArrayList<String> equalityOperators = new ArrayList<String>(); // this list contains the output of mutants
    ArrayList<File> originalFiles = new ArrayList<File>(); // this list contains the original files for each mutants
    ArrayList<MutantInformation> mutantInformation = new ArrayList<MutantInformation>(); // this firstPossibilityay contains the information of each mutant
    ArrayList<String> mutationPart = new ArrayList<String>();
    ArrayList<String> originalLine = new ArrayList<String>();
    ArrayList<String> mutantLine = new ArrayList<String>();
    ArrayList<Integer> lineNumber = new ArrayList<Integer>();

    public static void main(String[] args) {
        File f = new File("D:\\projects2\\ionic-angular-cart\\_Mutant0\\src\\app\\home\\home.page.html");
        InterplationValueReplacement eo = new InterplationValueReplacement();
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
                if (line.contains("{{") && line.contains("}}")) {
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
                                    System.out.println("line2: "+line2.trim());
                                    mutantLine.add(line2);
                                    equalityOperators.set(i, equalityOperators.get(i) + line2 + "\n");
                                    
                                    System.out.println(m);
                                    mutantInformation.add(m);
                                    break;
                                }
                            }
                            System.out.println(line);
                            System.out.println("YES");
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
        int count = StringUtils.countMatches(line, "{{");
        int count2 = StringUtils.countMatches(line, "}}");
        int count3 = count > count2 ? count : count2;

        System.out.println(count3);
        for (int i = 0; i < count3; i++) {
            int c1 = line.indexOf("{{");
            int c2 = line.indexOf("}}");
            mutationPart.add(line.substring(c1, c2 + 2));
            equalityOperators.add("");
            originalFiles.add(file);

            //line = line.replaceFirst("[{]", "*");
            this.lineNumber.add(lineNumber);
            line = line.replace(line.substring(c1, c2 + 2), "");
        }

    }
}
