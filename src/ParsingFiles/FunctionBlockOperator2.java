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
public class FunctionBlockOperator2 {

    ArrayList<String> ifStatementOperator = new ArrayList<String>(); // this list contains the output of mutants
    ArrayList<Boolean> trueOrFalse = new ArrayList<Boolean>();
    ArrayList<File> originalFiles = new ArrayList<File>(); // this list contains the original files for each mutants
    ArrayList<MutantInformation> mutantInformation = new ArrayList<MutantInformation>(); // this firstPossibilityay contains the information of each mutant

    public static void main(String[] args) {

        File f = new File("E:\\projects\\test\\.Mutant0\\src\\app\\about\\about.page.ts");
        FunctionBlockOperator2 b = new FunctionBlockOperator2();
        b.generateMutant(f);

    }
    int cont = 0;
    /*
     * This method takes as an input to find the mathematical operators
     * and change it to other operators then it sends all the possible strings to
     * the GenerateMutantFiles class to generate real files
     */
    boolean found = false;

    public void generateMutant(File file) {
        String output = "";
        int lineNumber = 0; // to save the line number that we will edit in
        String beforeClass = ""; // string to save all text before the class word
        boolean startScanning = false;
//int cont = -1;
        //String insideFunction = "";
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
                    if (line.contains("(") && line.contains(")")&&!foundBrackets) {
                        //System.out.println(output);
                        System.out.println("here");
                        ifStatementOperator.add(output);
                        cont++;
                        trueOrFalse.add(false);
                        //System.out.println("MM");
                        found = true;
                        output += line + "\n";
                    } else {
                        
                        for (int i = 0; i < ifStatementOperator.size(); i++) {
                            
                        }
                        output += line + "\n";
                    }
                    if (found) {
                        findStringInsideFunction(line);
                        for (int i = 0; i < ifStatementOperator.size(); i++) {
                            ifStatementOperator.set(i, ifStatementOperator.get(i)+"\n"+line);
                        }
                        //System.out.println(line);
                        

                        //output += line + "\n";
                    }

                }

                ////
            }
            sc.close();

            // Generating the real files for each mutant by sending the mutants, original files and mutant information for each mutants
            //System.out.println(output);
            //System.out.println(trueOrFalse.size());
            for (int i = 0; i < ifStatementOperator.size(); i++) {
                System.out.println("-------------------");
                System.out.println(ifStatementOperator.get(i));
                System.out.println("-------------------");

            }
            if (!ifStatementOperator.isEmpty()) {
                GenerateMutantFiles gm = new GenerateMutantFiles();
                //gm.generateFiles(ifStatementOperator, originalFiles, mutantInformation);
            }

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }
    int counter = 0;
    boolean foundBrackets = false;

    public void findStringInsideFunction(String line) {
        //System.out.println(line);
        String part = "";
        for (int i = 0; i < line.length(); i++) {
            if (counter == 0 && !foundBrackets) {
                part += line.charAt(i);
            }
            if (line.charAt(i) == '{') {
                counter++;
                foundBrackets = true;
            }
            if (line.charAt(i) == '}') {
                counter--;
            }

        }
        
        //ifStatementOperator.set(cont-1, ifStatementOperator.get(cont-1)+part);
        //System.out.println(part);
        if(!part.isEmpty()){
            found = false;
            ifStatementOperator.set(cont-1, ifStatementOperator.get(cont-1)+part);
            //System.out.println(ifStatementOperator.get(cont-1)+"}");
            //System.out.println(part);
        }
        
        if (counter == 0&&foundBrackets) {
            foundBrackets = false;
                    
            //ifStatementOperator.set(cont-1, ifStatementOperator.get(cont-1)+part);
            //System.out.println(ifStatementOperator.get(cont-1));
            //System.out.println("here");
            //found = false;
        }

        //System.out.println("---------");
        
    }

}
