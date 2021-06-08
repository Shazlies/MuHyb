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
 * @author Shad0o0
 */
public class NavController {

    ArrayList<String> equalityOperators = new ArrayList<String>(); // this list contains the output of mutants
    ArrayList<File> originalFiles = new ArrayList<File>(); // this list contains the original files for each mutants
    ArrayList<MutantInformation> mutantInformation = new ArrayList<MutantInformation>(); // this firstPossibilityay contains the information of each mutant
    ArrayList<String> mutationPart = new ArrayList<String>();
    ArrayList<String> originalLine = new ArrayList<String>();
    ArrayList<String> mutantLine = new ArrayList<String>();
    ArrayList<Integer> lineNumber = new ArrayList<Integer>();

    public static void main(String[] args) {
        File f = new File("D:\\projects2\\ionic-angular-cart\\_Mutant0\\src\\app\\services\\cart.service.ts");
        NavController eo = new NavController();
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
               // System.out.println("here");
                String line = sc.nextLine();

                // pasting the rest of the code in all mutants
//                for (int i = 0; i < equalityOperators.size(); i++) {
//                    String o = equalityOperators.get(i) + line + "\n";
//                    equalityOperators.set(i, o);
//                }
                // ignore all operators before the class word
                // start scanning the operators between the block of the class only
                if (line.contains(".push(")) {
                    System.out.println("heeeeeeeeeeeeeeeeeeeer");
                    equalityOperators.add("");
                    originalFiles.add(file);
                    this.lineNumber.add(lineNumber);

                    outputEqualEqual += line + "\n"; // keep updating the string if found
                } else if (line.contains(".setRoot(")) {
                    equalityOperators.add("");
                    originalFiles.add(file);
                    this.lineNumber.add(lineNumber);
                } else if (line.contains(".pop(")) {
                    equalityOperators.add("");
                    originalFiles.add(file);
                    this.lineNumber.add(lineNumber);
                }
                
            }
            sc.close();
            if (this.lineNumber.size() > 0) {
                Scanner sc2 = new Scanner(file);
                int counter = 0;
                while (sc2.hasNext()) {
                    counter++;
                    String line = sc2.nextLine();
                    for (int i = 0; i < this.lineNumber.size(); i++) {
                        if (counter == this.lineNumber.get(i)) {
                            if (line.contains(".push(")) {
                                String line2 = line.replace(".push(", ".setRoot(");
                                MutantInformation m = new MutantInformation(this.lineNumber.get(i), "NavController Operator", line.trim(), line2.trim());
                                mutantLine.add(line2);
                                equalityOperators.set(i, equalityOperators.get(i) + line2 + "\n");
                                mutantInformation.add(m);
                            }
                            else if (line.contains(".setRoot(")) {
                                String line2 = line.replace(".setRoot(", ".push(");
                                MutantInformation m = new MutantInformation(this.lineNumber.get(i), "NavController Operator", line.trim(), line2.trim());
                                mutantLine.add(line2);
                                equalityOperators.set(i, equalityOperators.get(i) + line2 + "\n");
                                mutantInformation.add(m);
                            }
                            else if (line.contains(".push(")) {
                                String line2 = "";
                                MutantInformation m = new MutantInformation(this.lineNumber.get(i), "NavController Operator", line.trim(), line2.trim());
                                mutantLine.add(line2);
                                equalityOperators.set(i, equalityOperators.get(i) + line2 + "\n");
                                mutantInformation.add(m);
                            }
                            else {
                                equalityOperators.set(i, equalityOperators.get(i) + line + "\n");
                            }
                            //System.out.println(line);
                            //System.out.println("YES");
                        } else {
                            equalityOperators.set(i, equalityOperators.get(i) + line + "\n");
                        }

                    }
                }
                sc2.close();
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
            System.out.println("NOOOOOOOOOOO");
            System.out.println(ex);
        } catch(Exception ex){
            System.out.println(ex);
        }
    }

}
