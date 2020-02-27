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
import java.util.Stack;

/**
 *
 * @author Home
 */
public class BooleanLiterals {

    ArrayList<String> stringTrueWithChange = new ArrayList<String>();
    ArrayList<String> restOfTrueString = new ArrayList<String>();
    ArrayList<String> stringFalseWithChange = new ArrayList<String>();
    ArrayList<String> restOfFalseString = new ArrayList<String>();
    ArrayList<File> originalFiles1 = new ArrayList<File>();
    ArrayList<File> originalFiles2 = new ArrayList<File>();
    boolean classFound = false;
    public void generateMutant(File file) {
        //System.out.println(file.getAbsoluteFile() + " Searching here..");
        String output1 = "";
        String output2 = "";
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String line = sc.nextLine();
//                if(line.contains("class")){
//                    classFound=true;
//                }
//                if(classFound){
//                    
//                    findFunctionNames(line);
//                }
                
                if (line.contains("true")&&!line.contains("for")&&!line.contains("while")) {
                    for (int i = 0; i < stringTrueWithChange.size(); i++) {
                        String o = restOfTrueString.get(i);
                        o += line + "\n";
                        restOfTrueString.remove(i);
                        restOfTrueString.add(i, o);
                    }

                    String line2 = line.replace("true", "false");
                    //System.out.println(line);
                    stringTrueWithChange.add(output1 + line2 + "\n");
                    restOfTrueString.add("");
                    originalFiles1.add(file);

                    output1 += line + "\n";
                    //pw.println(line + "\n");

                    //MutantsGenerator.counter++;
                    //System.out.println(xxxx+" ******");
                } else {
                    output1 += line + "\n";
                    for (int i = 0; i < stringTrueWithChange.size(); i++) {
                        String o = restOfTrueString.get(i);
                        o += line + "\n";
                        restOfTrueString.remove(i);
                        restOfTrueString.add(i, o);
                    }

                    //pw.println(line + "\n");
                }
                ///////////////////////////////////

                if (line.contains("false")&&!line.contains("for")&&!line.contains("while")) {
                    for (int i = 0; i < stringFalseWithChange.size(); i++) {
                        String o = restOfFalseString.get(i);
                        o += line + "\n";
                        restOfFalseString.remove(i);
                        restOfFalseString.add(i, o);
                    }

                    String line2 = line.replace("false", "true");
                    //System.out.println(line);
                    stringFalseWithChange.add(output2 + line2 + "\n");
                    restOfFalseString.add("");
                    originalFiles2.add(file);
                    output2 += line + "\n";
                    //pw.println(line + "\n");

                    //MutantsGenerator.counter++;
                    //System.out.println(xxxx+" ******");
                } else {
                    output2 += line + "\n";
                    for (int i = 0; i < stringFalseWithChange.size(); i++) {
                        String o = restOfFalseString.get(i);
                        o += line + "\n";
                        restOfFalseString.remove(i);
                        restOfFalseString.add(i, o);
                    }

                }

            }
            sc.close();

            for (int i = 0; i < stringTrueWithChange.size(); i++) {
                String str1 = stringTrueWithChange.get(i) + restOfTrueString.get(i);
                stringTrueWithChange.remove(i);
                stringTrueWithChange.add(i, str1);

            }
//            System.out.println("*/*/*/*/*/*/");
//            for (int i = 0; i < stringTrueWithChange.size(); i++) {
//                System.out.println(stringTrueWithChange.get(i));
//            }

            for (int i = 0; i < stringFalseWithChange.size(); i++) {
                String str1 = stringFalseWithChange.get(i) + restOfFalseString.get(i);
                stringFalseWithChange.remove(i);
                stringFalseWithChange.add(i, str1);

            }

//            System.out.println("-+-+-+-+-+-+");
//            for (int i = 0; i < stringFalseWithChange.size(); i++) {
//                System.out.println(stringFalseWithChange.get(i));
//            }
            for (int i = 0; i < stringFalseWithChange.size(); i++) {
                stringTrueWithChange.add(stringFalseWithChange.get(i));
            }

            for (int i = 0; i < originalFiles2.size(); i++) {
                originalFiles1.add(originalFiles2.get(i));
            }
            if (stringTrueWithChange.size() != 0) {
                GenerateMutantFiles gm = new GenerateMutantFiles();
               // gm.generateFiles(stringTrueWithChange, originalFiles1, "Boolean Operator");
                //System.out.println("Number of Mutants: " + MutantsGenerator.counter);
            }

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }
    Stack<String> stack = new Stack<String>();

    public void findFunctionNames(String line) {
        boolean noMore = true;

        for (int i = 0; noMore; i++) {
            if (line.contains("{")) {
                stack.push("{");
                if(stack.size()==2){
                    //System.out.println(line.trim());
                    line = line.trim();
                   //System.out.println(line.indexOf("("));
                   if(line.contains("(")){
                       line = line.substring(0, line.indexOf("("));
                       line = line.trim();
                       if(line.contains(" ")){
                           line = line.substring(line.lastIndexOf(" "), line.length());
                           line = line.trim();
                           
                        //System.out.println(line.substring(0, line.indexOf("(")));
                           
                       }
                       
                   }
                   System.out.println(line);
                    
                   // System.out.println(line.substring(0, line.charAt(line.indexOf("("))));
                    
                }
                
                line = line.replace('{', '=');
                //if(line.contains("{"))
            } else {
                noMore = false;
            }
        }
        noMore=true;
        for (int i = 0; noMore; i++) {
            if (line.contains("}")) {
                stack.pop();
                //System.out.println(line);
                line = line.replace('}', '=');
                
                //if(line.contains("{"))
            } else {
                noMore = false;
            }
        }
        System.out.println("stack size= "+stack.size());
        

    }

}
