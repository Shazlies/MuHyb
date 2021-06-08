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
public class RemoveClickEvent {

    ArrayList<String> clickOperators = new ArrayList<String>(); // this list contains the output of mutants
    ArrayList<File> originalFiles = new ArrayList<File>(); // this list contains the original files for each mutants
    ArrayList<MutantInformation> mutantInformation = new ArrayList<MutantInformation>(); // this firstPossibilityay contains the information of each mutant

    public static void main(String[] args) {
        File f = new File("D:\\projects2\\infoapp\\_Mutant0\\src\\app\\pages\\info\\info.page.html");
        RemoveClickEvent eo = new RemoveClickEvent();
        eo.generateMutant(f, 4);
    }

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
                for (int i = 0; i < clickOperators.size(); i++) {
                    String o = clickOperators.get(i) + line + "\n";
                    clickOperators.set(i, o);
                }
                // ignore all operators before the class word

                // start scanning the operators between the block of the class only
                System.out.println(line);
                if (line.contains("(click)")) {
                    System.out.println("YES");
                    setOperator(line, outputEqualEqual, file, lineNumber, "==", "", "!=");
                    outputEqualEqual += line + "\n"; // keep updating the string if found 
                } else {
                    outputEqualEqual += line + "\n"; // keep updating the string if  not found
                }

//                    if (line.contains("!=")) {
//                        setOperator(line, outputNotEqual, file, lineNumber, "!=", "!==", "==");
//                        outputNotEqual += line.replaceAll("[@][@]", "!==") + "\n"; // keep updating the string if found 
//                    } else {
//                        outputNotEqual += line.replaceAll("[@][@]", "!==") + "\n"; // keep updating the string if  not found
//                    }
//                    line = line.replaceAll("[#][#]", "!==");
//                    line = line.replaceAll("[@][@]", "===");
//                    if (line.contains("===")) {
//                        setOperator(line, outputEqualEqualEqual, file, lineNumber, "===", "", "!==" );
//                        outputEqualEqualEqual += line + "\n"; // keep updating the string if found 
//                    } else {
//                        outputEqualEqualEqual += line + "\n"; // keep updating the string if  not found
//                    }
//                    if (line.contains("!==")) {
//                        setOperator(line, outputNotEqualEqual, file, lineNumber, "!==", "", "===");
//                        outputNotEqualEqual += line + "\n"; // keep updating the string if found 
//                    } else {
//                        outputNotEqualEqual += line + "\n"; // keep updating the string if  not found
//                    }
                ////
            }
            sc.close();

            // Generating the real files for each mutant by sending the mutants, original files and mutant information for each mutants
            if (!clickOperators.isEmpty()&&(clickOperators.size()==originalFiles.size()&&originalFiles.size()==mutantInformation.size())) {
                GenerateMutantFiles gm = new GenerateMutantFiles();
                gm.generateFiles(clickOperators, originalFiles, mutantInformation, threadNumbers);
            }

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }

    }

    public void setOperator(String line, String output1, File file, int lineNumber, String originalOperator, String operatorReplacement, String fisrtOperator) {

        String originalOperator2 = "[\"]";
        String output = "";
//        for (int i = 0; i < originalOperator.length(); i++) {
//            originalOperator2 += "["+originalOperator.charAt(i)+"]";
//        }

        String tokens[] = line.split(originalOperator2); // split the line to tokens example: a = b+c; >> first token is: a = b and second token is c;
        System.out.println("********************");
        int temp = -1;
        for (int i = 0; i < tokens.length; i++) {
            if (i != tokens.length - 1) {
                if (tokens[i].contains("click")) {
                    //output += tokens[i] + "\"";
                    tokens[i + 1] = "";
                } else if (tokens[i].equals("")) {

                } else {
                    output += tokens[i] + "\"";
                }
            } else {
                output += tokens[i];
            }

        }
        System.out.println(output);
        System.out.println("********************");

        MutantInformation m = new MutantInformation(lineNumber, "Click event Operator", line, output);
        mutantInformation.add(m);
        clickOperators.add(output1 + output + "\n");
        originalFiles.add(file);

    }
}
