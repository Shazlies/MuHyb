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
public class StatementDeletion3 {

    public static void main(String[] args) {
        File f = new File("E:\\projects\\test\\.Mutant0\\src\\app\\about\\about.page.ts");
        StatementDeletion3 b = new StatementDeletion3();
        b.generateMutant(f);

    }

    ArrayList<String> mathematicalOperators = new ArrayList<String>(); // this list contains the output of mutants
    ArrayList<File> originalFiles = new ArrayList<File>(); // this list contains the original files for each mutants
    ArrayList<MutantInformation> mutantInformation = new ArrayList<MutantInformation>(); // this firstPossibilityay contains the information of each mutant

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
                       // setOperator(line, outputPlus, file, lineNumber, "+", "++", "-", "*", "/");
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

}
