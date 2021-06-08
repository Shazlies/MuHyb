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
public class InterpolationValueChangeOp {

    ArrayList<String> equalityOperators = new ArrayList<String>(); // this list contains the output of mutants
    ArrayList<File> originalFiles = new ArrayList<File>(); // this list contains the original files for each mutants
    ArrayList<MutantInformation> mutantInformation = new ArrayList<MutantInformation>(); // this firstPossibilityay contains the information of each mutant

    public static void main(String[] args) {
        File f = new File("E:\\projects\\PizzaHouse6\\_Mutant0\\src\\app\\pizza\\pizza.page.html");
        InterpolationValueChangeOp eo = new InterpolationValueChangeOp();
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
                if (line.contains("{{")&&line.contains("}}")) {
                    setOperator(line, outputEqualEqual, file, lineNumber, "", "", "");
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
        int count = StringUtils.countMatches(line, "{{");
        int count2 = StringUtils.countMatches(line, "}}");
        int number = (count < count2) ? count : count2;
        String[] test = new String[number];
        for (int i = 0; i < test.length; i++) {
            test[i] = "";
        }
        boolean found= false;
        int j=0;
        for (int i = 0; i < line.length(); i++) {
            if (i != line.length() - 1) {
                if (line.charAt(i) == '{' && line.charAt(i + 1) == '{') {
                    found=true;
                }
                
                if(found){
                    test[j]+=line.charAt(i);
                }
                if (line.charAt(i) == '}' && line.charAt(i + 1) == '}') {
                    found=false;
                    test[j] +="}";
                    j++;
                }
                
            }
        }
        
        
        
        
        
        String firstPossibility[] = new String[number];
        // emptying the arrays
        for (int i = 0; i < number; i++) {
            firstPossibility[i] = "";
        }
        
        for (int i = 0; i < firstPossibility.length; i++) {
            if(line.contains(test[i])){
                String x = line.replace(test[i], "anyWord");
                firstPossibility[i] = x;
            }
            
        }
        
        
        
        for (int i = 0; i < number; i++) {
            // add mutants but not the ones that contains $^%&%& sign we added before [it mean the operator between ' or "]
            if (!firstPossibility[i].contains("$^%&%&")) {
                firstPossibility[i] = firstPossibility[i].substring(0, firstPossibility[i].length() - originalOperator.length());
                MutantInformation m = new MutantInformation(lineNumber, "Data Binding Operator", line, firstPossibility[i]);
                mutantInformation.add(m);
                equalityOperators.add(output1 + firstPossibility[i] + "\n");
                originalFiles.add(file);
            }

        }
    }
}
