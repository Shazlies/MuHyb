/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ParsingFiles;

import static ParsingFiles.StatementDeletion2.startMethod;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Home
 */
public class StatementDeletion {

    public static void main(String[] args) {
        File f = new File("E:\\projects\\test\\.Mutant0\\src\\app\\home\\home.page.ts");
        StatementDeletion b = new StatementDeletion();
        b.generateMutant(f,4);

    }
    ArrayList<String> StatementDeletionOperators = new ArrayList<String>(); // this list contains the output of mutants
    ArrayList<File> originalFiles = new ArrayList<File>(); // this list contains the original files for each mutants
    ArrayList<MutantInformation> mutantInformation = new ArrayList<MutantInformation>(); // this firstPossibilityay contains the information of each mutant
    int counter = 0;

    public void generateMutant(File file, int threadNumbers) {

        int lineNumber = 0; // to save the line number that we will edit in
        String beforeClass = ""; // string to save all text before the class word
        boolean startScanning = false;
        String output = "";
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
                // ignore all ifStatementOperator before the class word
                if (line.contains("class") && !startScanning) {
                    startScanning = true;
                    output += beforeClass + "\n" + line;
                    continue;
                } else if (!line.contains("class") && !startScanning) {
                    beforeClass += line + "\n";
                }
                // start scanning the ifStatementOperator between the block of the class only
                if (startScanning) {
                    //System.out.println(line);
                    for (int i = 0; i < StatementDeletionOperators.size(); i++) {
                        StatementDeletionOperators.set(i, StatementDeletionOperators.get(i) + "\n" + line);
                    }
                    int openBracket = StringUtils.countMatches(line, "{");
                    int closedBracket = StringUtils.countMatches(line, "}");
                    counter += openBracket;
                    counter -= closedBracket;
                    if (counter > 0) {
                        //setOperator(line);
                        int x = StringUtils.countMatches(line, ";");
                        if (x != 0 && !line.contains("for") && !line.contains("var ") && !line.contains("let ") && !line.contains("console.") && !line.contains("return ") && !line.contains("while")) {
                            StatementDeletionOperators.add(output);
                            originalFiles.add(file);
                            MutantInformation m = new MutantInformation(lineNumber, "Statement Deletion Operator", line, "");
                            mutantInformation.add(m);
                        }

                    }
                    output += line + "\n";
                }

                ////
            }
            sc.close();
            if (!StatementDeletionOperators.isEmpty()) {
                GenerateMutantFiles gm = new GenerateMutantFiles();
                gm.generateFiles(StatementDeletionOperators, originalFiles, mutantInformation, threadNumbers);
            }

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }

    }

    public void setOperator(String line) {
  
    }

}
