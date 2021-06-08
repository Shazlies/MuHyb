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
public class LifeCycleMethodsOperators {

    ArrayList<String> functionBlockOperator = new ArrayList<String>(); // this list contains the output of mutants
    ArrayList<File> originalFiles = new ArrayList<File>(); // this list contains the original files for each mutants
    ArrayList<MutantInformation> mutantInformation = new ArrayList<MutantInformation>(); // this firstPossibilityay contains the information of each mutant

    public static void main(String[] args) {
        File f = new File("E:\\projects\\PizzaHouses\\_Mutant0\\src\\app\\pizza\\pizza.page.ts");
        LifeCycleMethodsOperators b = new LifeCycleMethodsOperators();
        b.generateMutant(f, 4);
    }

    public void generateMutant(File file, int threadNumbers) {

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
//                for (int i = 0; i < functionBlockOperator.size(); i++) {
//                    String o = functionBlockOperator.get(i) + line + "\n";
//                    functionBlockOperator.set(i, o);
//                }
                // ignore all functionBlockOperator before the class word
                if (line.contains("class") && !startScanning) {
                    startScanning = true;
                    output += beforeClass + line + "\n";
                    continue;
                } else if (!line.contains("class") && !startScanning) {
                    beforeClass += line + "\n";
                }
                // start scanning the functionBlockOperator between the block of the class only
                if (startScanning) {
                    //System.out.println(line);
                    if (line.contains("{") && !startMethod&&(line.contains("ngOnInit")||line.contains("ngOnDestroy")||line.contains("ionViewWillEnter")||line.contains("ionViewDidEnter")||line.contains("ionViewWillLeave")||line.contains("ionViewDidLeave"))) {
                        startMethod = true;
                        //System.out.println("00000000000000000000");
                        cont++;
                        functionBlockOperator.add(output);
                        originalFiles.add(file);
                        output += line + "\n";
                    } else {
                        for (int i = 0; i < functionBlockOperator.size(); i++) {
                            if (i != cont) {
                                functionBlockOperator.set(i, functionBlockOperator.get(i) + "\n" + line);
                            }
                            if (i == cont && !startMethod) {
                                functionBlockOperator.set(i, functionBlockOperator.get(i) + "\n" + line);
                            }

                        }
                        output += line + "\n";
                    }
                    if (startMethod) {

                        setOperator(line, lineNumber);
                    }

                }

                ////
            }
            sc.close();

            // Generating the real files for each mutant by sending the mutants, original files and mutant information for each mutants
            //System.out.println(output);
            //System.out.println(trueOrFalse.size());
            for (int i = 0; i < functionBlockOperator.size(); i++) {
//                System.out.println("-------------------");
//                System.out.println(functionBlockOperator.get(i));
//                System.out.println("-------------------");

            }
            if (!functionBlockOperator.isEmpty()) {
                GenerateMutantFiles gm = new GenerateMutantFiles();
                gm.generateFiles(functionBlockOperator, originalFiles, mutantInformation, threadNumbers);
            }

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }

    /*
     * This method takes each line that contains an operator and generate all possible mutants
     * as a string and saving all mutant information
     */
    static boolean startMethod = false;
    boolean found = false;
    boolean finished = false;
    int counter = 0;

    String output = "";
    int cont = -1;

    public void setOperator(String line, int lineNumber) {
        String rest = "";
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '{') {
                counter++;
                found = true;
            }
            if (line.charAt(i) == '}') {
                counter--;
                if (counter == 0) {
                    startMethod = false;
                }
                //System.out.println("HERE");
            }
            if (!found) {
                rest += line.charAt(i);
            }

        }
        if (found && counter == 0) {
            found = false;
            // " { }");
        }
        if (!rest.isEmpty()) {
            MutantInformation m = new MutantInformation(lineNumber, "Life Cycle Function Removal Operator", "{ ... }", "{ }");
            mutantInformation.add(m);
            functionBlockOperator.set(cont, functionBlockOperator.get(cont) + "\n" + rest + " { }" + "\n");
        }

        for (int i = 0; i < functionBlockOperator.size(); i++) {
            if (i != cont) {
                if (!rest.isEmpty()) {
                    functionBlockOperator.set(i, functionBlockOperator.get(i) + "\n" + rest + " {");
                }
            }

        }
        //System.out.println(counter);
        //System.out.println(rest);

    }

//    
//    String output = "";
//    String insideFunction = "";
//    String emptyInsideFunction = "";
//    int counter = 0;
//    boolean functionFound = false;
//    boolean done = false;
//    String newString = "";
//    String test = "";
//    ArrayList<String> inside = new ArrayList<String>();
//    
//    
//    String z = "";
//    boolean f = false;
//    int c= 0;
//    public void findStringInsideFunction2(String line) {
//            //System.out.println(line);
//        for (int i = 0; i < line.length(); i++) {
//            if(line.charAt(i)!='{'&&!f){
//                z+=line.charAt(i);
//                
//                //System.out.println("hey");
//            }
//            
//            if(line.charAt(i)=='{'){
//                f = true;
//                c++;
//            }
//            if(line.charAt(i)=='}'){
//                c--;
//            }
//            if(f&&c==0){
//                
//                String mm = functionBlockOperator.get(cont)+z+" { }";
//                functionBlockOperator.set(cont, mm);
//                //System.out.println(functionBlockOperator.get(cont));
//                   System.out.println(z+" { }");
//                z = "";
//                //System.out.println("------------");
//                //System.out.println(cont);
//                cont++;
//             
//            }
//           
//            
//        }
//         if(c==0){
//                
//                f = false;
//                String mm = functionBlockOperator.get(cont)+line+"\n";
//                functionBlockOperator.set(cont, mm);
//            }
//        
//        
//        
//    }
}
