/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reports;

import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Home
 */
public class AnalyzeResults {

    volatile static ArrayList<Results> arr = new ArrayList<Results>();
    volatile static ArrayList<ResultsForEachFile> arr3 = new ArrayList<ResultsForEachFile>();

    public static ArrayList<ResultsForEachFile> getArr3() {
        return arr3;
    }

    public static ArrayList<Results> getArr() {
        return arr;
    }

    public void checkoutputColor(String output, String fileName, String lineNumber, String operator, String originalLine, String mutantLine, String fileType) {
//        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
//        System.out.println("output: " + output);
//        System.out.println("Filename: " + fileName);
//        System.out.println("Line number: " + lineNumber);
//        System.out.println("operator: " + operator);
//        System.out.println("originalLine: " + originalLine);
//        System.out.println("mutantLine: " + mutantLine);
//        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        if (output.contains("ERROR") && !output.contains("DISCONNECTED")) {
            System.out.println("ERROR OR DISCONNECTED");
            Results r = new Results(fileName, "ERROR", 0, 0, 0);
            ResultsForEachFile rf = new ResultsForEachFile(fileName, Integer.parseInt(lineNumber), "Syntax Error", operator, originalLine, mutantLine);
            arr3.add(rf);
            arr.add(r);
        } else {
            System.out.println("ELSE");
            String[] lines = output.split("\n");
            String lastLine = lines[lines.length - 1];
            System.out.println("Last line: " + lastLine);
            int counter = 0;
            int executedIndex = lastLine.indexOf("Executed");
            String partOfLine = lastLine.substring(executedIndex, lastLine.length());
            partOfLine = partOfLine.replace("Executed", "");
            partOfLine = partOfLine.trim();
            System.out.println("partOfLine: " + partOfLine);
            //success case
            if (partOfLine.contains("SUCCESS")) {

                Results r = new Results(fileName, "SUCCESS", 0, 0, 0);
                ResultsForEachFile rf = new ResultsForEachFile(fileName, Integer.parseInt(lineNumber), "Survived", operator, originalLine, mutantLine);
                arr3.add(rf);
                arr.add(r);
                //System.out.println("All success Exectuec " + executed + " Of " + outOf);
            } // failed case
            else if (partOfLine.contains("FAILED")) {

                Results r = new Results(fileName, "FAILED", 0, 0, 0);
                ResultsForEachFile rf = new ResultsForEachFile(fileName, Integer.parseInt(lineNumber), "Killed", operator, originalLine, mutantLine);
                System.out.println("************************************");
                System.out.println(r);
                System.out.println(rf);
                System.out.println("************************************");
                arr3.add(rf);
                arr.add(r);
                //System.out.println("failed executed " + executed + " of " + outOf + " Number of failed  " + noOfFailed);
            } else if (partOfLine.contains("DISCONNECTED")) {

                Results r = new Results(fileName, "DISCONNECTED", 0, 0, 0);
                ResultsForEachFile rf = new ResultsForEachFile(fileName, Integer.parseInt(lineNumber), "Exception", operator, originalLine, mutantLine);
                arr3.add(rf);
                arr.add(r);
                //System.out.println("disconnected executed " + executed + " of " + outOf);
            } else {
                Results r = new Results(fileName, "ERROR", 0, 0, 0);
                ResultsForEachFile rf = new ResultsForEachFile(fileName, Integer.parseInt(lineNumber), "Syntax Error", operator, originalLine, mutantLine);
                arr3.add(rf);
                arr.add(r);
            }
        }

    }

    static ArrayList<Results> arr2 = new ArrayList<Results>();

    public static ArrayList<Results> getArr2() {
        return arr2;
    }

    public void checkoutputColor4(String output, ArrayList<File> files, ArrayList<Integer> numberOfTests) {
        System.out.println("COLOR4");
        //System.out.println("here");
        String[] lines = output.split("\n");
        String lastLine = lines[lines.length - 1];
        int counter = 0;
        int executedIndex = lastLine.indexOf("Executed");

        String partOfLine = lastLine.substring(executedIndex, lastLine.length());
        partOfLine = partOfLine.replace("Executed", "");
        partOfLine = partOfLine.trim();
        System.out.println(partOfLine);
        //success case
        if (partOfLine.contains("SUCCESS")) {
            int executed = 0;
            int outOf = 0;
            partOfLine = partOfLine.replace("\u001B[31m", "");
            partOfLine = partOfLine.replace("\u001B[32m", "");
            partOfLine = partOfLine.replace("\u001B[33m", "");
            StringTokenizer st = new StringTokenizer(partOfLine);
            while (st.hasMoreTokens()) {
                String word = st.nextToken();
                if (StringUtils.isNumeric(word) && counter == 0) {
                    executed = Integer.parseInt(word);
                } else if (StringUtils.isNumeric(word) && counter == 2) {
                    outOf = Integer.parseInt(word);
//System.out.println(word+"  "+word.length()+" "+word.contains("\\u001B[32m")+" not number");
                }
                counter++;
            }
            for (int i = 0; i < files.size(); i++) {
                String fileName = files.get(i).getName().replace("spec.ts", "ts");
                int num = numberOfTests.get(i);
                Results r = new Results(fileName, "SUCCESS", num, num, 0);
                System.out.println(r);
                arr2.add(r);

            }
        } // failed case
        else {
            JOptionPane.showMessageDialog(null, "Test Failed please fix your tests and run again");
            System.exit(0);
        }

    }

    public void checkoutputColor3(String output) {

        String lastLine = "";
        String[] lines = output.split("\n");
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].contains("Executed") && lines[i].contains("of") && lines[i].contains("secs") && lines[i].contains("specs")) {
                lastLine = lines[i];
                break;
            }
        }
        System.out.println(lastLine + " opopopopopo");
        //String lastLine = lines[lines.length - 1];
        int counter = 0;
        int executedIndex = lastLine.indexOf("Executed");

        String partOfLine = lastLine.substring(executedIndex, lastLine.length());
        partOfLine = partOfLine.replace("Executed", "");
        partOfLine = partOfLine.trim();

        //success case
        if (partOfLine.contains("SUCCESS")) {
            int executed = 0;
            int outOf = 0;
            partOfLine = partOfLine.replace("\u001B[31m", "");
            partOfLine = partOfLine.replace("\u001B[32m", "");
            partOfLine = partOfLine.replace("\u001B[33m", "");
            StringTokenizer st = new StringTokenizer(partOfLine);
            while (st.hasMoreTokens()) {
                String word = st.nextToken();
                if (StringUtils.isNumeric(word) && counter == 0) {
                    executed = Integer.parseInt(word);
                } else if (StringUtils.isNumeric(word) && counter == 2) {
                    outOf = Integer.parseInt(word);
//System.out.println(word+"  "+word.length()+" "+word.contains("\\u001B[32m")+" not number");
                }
                counter++;
            }

            System.out.println("Executed: " + executed);
            System.out.println("out of: " + outOf);
            Results r = new Results("E2E Files", "SUCCESS", executed, outOf, 0);
            arr2.add(r);
            //System.out.println("All success Exectuec " + executed + " Of " + outOf);
        } // failed case
        else {
            JOptionPane.showMessageDialog(null, "Test Failed please fix your tests and run again");
            System.exit(0);
        }
//        else if (partOfLine.contains("FAILED")) {
//            System.out.println("Failed: " + partOfLine);
//            int executed = 0;
//            int outOf = 0;
//            int noOfFailed = 0;
//            partOfLine = partOfLine.replace("\u001B[31m", "");
//            partOfLine = partOfLine.replace("\u001B[32m", "");
//            partOfLine = partOfLine.replace("\u001B[33m", "");
//            StringTokenizer st = new StringTokenizer(partOfLine);
//            System.out.println("Failed after  release colors: " + partOfLine);
//            while (st.hasMoreTokens()) {
//                String word = st.nextToken();
//                System.out.println("Word" + counter + " : " + word);
//                if (StringUtils.isNumeric(word) && counter == 0) {
//                    executed = Integer.parseInt(word);
//                } else if (StringUtils.isNumeric(word) && counter == 2) {
//                    outOf = Integer.parseInt(word);
//
////System.out.println(word+"  "+word.length()+" "+word.contains("\\u001B[32m")+" not number");
//                } else if (counter == 4) {
//                    word = word.replace("(", "");
//                    noOfFailed = Integer.parseInt(word);
//
////System.out.println(word+"  "+word.length()+" "+word.contains("\\u001B[32m")+" not number");
//                }
//                counter++;
//
//            }
//            System.out.println("Executed: " + executed);
//            System.out.println("NoOfFaild: " + noOfFailed);
//            System.out.println("out of: " + outOf);
//            Results r = new Results("E2E Files", "FAILED", executed, outOf, noOfFailed);
//            arr2.add(r);
//            //System.out.println("failed executed " + executed + " of " + outOf + " Number of failed  " + noOfFailed);
//        } else if (partOfLine.contains("DISCONNECTED")) {
//            int executed = 0;
//            int outOf = 0;
//            partOfLine = partOfLine.replace("\u001B[31m", "");
//            partOfLine = partOfLine.replace("\u001B[32m", "");
//            partOfLine = partOfLine.replace("\u001B[33m", "");
//            StringTokenizer st = new StringTokenizer(partOfLine);
//            while (st.hasMoreTokens()) {
//                String word = st.nextToken();
//                if (StringUtils.isNumeric(word) && counter == 0) {
//                    executed = Integer.parseInt(word);
//                } else if (StringUtils.isNumeric(word) && counter == 2) {
//                    outOf = Integer.parseInt(word);
//
////System.out.println(word+"  "+word.length()+" "+word.contains("\\u001B[32m")+" not number");
//                }
//                counter++;
//
//            }
//            Results r = new Results("E2E Files", "DISCONNECTED", executed, outOf, 0);
//            arr2.add(r);
//            //System.out.println("disconnected executed " + executed + " of " + outOf);
//        }
//        System.out.println("array size: " + arr2.size());
        //System.out.println("partOfLine " + partOfLine);

//        if (x.contains("\u001B[31m")) {
//            System.out.println( "This line contains red color");
//        }
//        if (x.contains("\u001B[32m")) {
//            System.out.println( "This line contains green color");
//        }
//        if (x.contains("\u001B[33m")) {
//            System.out.println( "This line contains yellow color");
//        }
//        for (int i = 8; i < line.length; i++) {
//            //System.out.println(line[i]);
//            if(line[i].contains("\u001B[31m")){
//                System.out.println("Line number: "+i+line[i]+"This line contains red color");
//            }
//            if(line[i].contains("\u001B[32m")){
//                System.out.println("Line number: "+i+line[i]+"This line contains green color");
//            }
//            if(line[i].contains("\u001B[33m")){
//                System.out.println("Line number: "+i+line[i]+"This line contains yellow color");
//            }
//            
//        }
    }

    public void checkoutputColor4(String output, String fileName, String lineNumber, String operator, String originalLine, String mutantLine, String fileTypes) {
        
        if (output.contains("unhandled exception")) {
            Results r = new Results(fileName, "ERROR", 0, 0, 0);
            ResultsForEachFile rf = new ResultsForEachFile(fileName, Integer.parseInt(lineNumber), "Syntax Error", operator, originalLine, mutantLine);
            arr3.add(rf);
            arr.add(r);
            System.out.println(rf);
            System.out.println(r);
        }  else {
            String[] lines = output.split("\n");
            String lastLine = "";
            boolean found = false;
            for (int i = 0; i < lines.length; i++) {
                if (lines[i].contains("Executed") && lines[i].contains("of") && lines[i].contains("secs") && lines[i].contains("specs")) {
                    lastLine = lines[i];
                    found = true;
                    break;
                }
            }
            System.out.println(lastLine + " opopopopopo222222222");
            //        String lastLine = lines[lines.length - 1];
            if (found) {
                int counter = 0;
                int executedIndex = lastLine.indexOf("Executed");
                String partOfLine = lastLine.substring(executedIndex, lastLine.length());
                partOfLine = partOfLine.replace("Executed", "");
                partOfLine = partOfLine.trim();
                //success case
                if (partOfLine.contains("SUCCESS")) {
                    int executed = 0;
                    int outOf = 0;
                    partOfLine = partOfLine.replace("\u001B[31m", "");
                    partOfLine = partOfLine.replace("\u001B[32m", "");
                    partOfLine = partOfLine.replace("\u001B[33m", "");
                    StringTokenizer st = new StringTokenizer(partOfLine);
                    while (st.hasMoreTokens()) {
                        String word = st.nextToken();
                        if (StringUtils.isNumeric(word) && counter == 0) {
                            executed = Integer.parseInt(word);
                        } else if (StringUtils.isNumeric(word) && counter == 2) {
                            outOf = Integer.parseInt(word);
//System.out.println(word+"  "+word.length()+" "+word.contains("\\u001B[32m")+" not number");
                        }
                        counter++;

                    }
                    Results r = new Results(fileName, "SUCCESS", executed, outOf, 0);
                    ResultsForEachFile rf = new ResultsForEachFile(fileName, Integer.parseInt(lineNumber), "Survived", operator, originalLine, mutantLine);
                    arr3.add(rf);
                    arr.add(r);
                    System.out.println("***************e2e*******Success**************");
                    System.out.println(r);
                    System.out.println(rf);
                    System.out.println("****************e2e********************");
                    //System.out.println("All success Exectuec " + executed + " Of " + outOf);
                } // failed case
                else if (partOfLine.contains("FAILED")) {
                    int executed = 0;
                    int outOf = 0;
                    int noOfFailed = 0;
                    partOfLine = partOfLine.replace("\u001B[31m", "");
                    partOfLine = partOfLine.replace("\u001B[32m", "");
                    partOfLine = partOfLine.replace("\u001B[33m", "");
                    StringTokenizer st = new StringTokenizer(partOfLine);
                    while (st.hasMoreTokens()) {
                        String word = st.nextToken();
                        if (StringUtils.isNumeric(word) && counter == 0) {
                            executed = Integer.parseInt(word);
                        } else if (StringUtils.isNumeric(word) && counter == 2) {
                            outOf = Integer.parseInt(word);

//System.out.println(word+"  "+word.length()+" "+word.contains("\\u001B[32m")+" not number");
                        } else if (counter == 4) {
                            word = word.replace("(", "");
                            noOfFailed = Integer.parseInt(word);

//System.out.println(word+"  "+word.length()+" "+word.contains("\\u001B[32m")+" not number");
                        }
                        counter++;

                    }
                    Results r = new Results(fileName, "FAILED", executed, outOf, noOfFailed);
                    ResultsForEachFile rf = new ResultsForEachFile(fileName, Integer.parseInt(lineNumber), "Killed", operator, originalLine, mutantLine);
                    System.out.println("***************e2e********failed*************");
                    System.out.println(r);
                    System.out.println(rf);
                    System.out.println("****************e2e********************");
                    arr3.add(rf);
                    arr.add(r);
                    //System.out.println("failed executed " + executed + " of " + outOf + " Number of failed  " + noOfFailed);
                } else if (partOfLine.contains("DISCONNECTED")) {
                    int executed = 0;
                    int outOf = 0;
                    partOfLine = partOfLine.replace("\u001B[31m", "");
                    partOfLine = partOfLine.replace("\u001B[32m", "");
                    partOfLine = partOfLine.replace("\u001B[33m", "");
                    StringTokenizer st = new StringTokenizer(partOfLine);
                    while (st.hasMoreTokens()) {
                        String word = st.nextToken();
                        if (StringUtils.isNumeric(word) && counter == 0) {
                            executed = Integer.parseInt(word);
                        } else if (StringUtils.isNumeric(word) && counter == 2) {
                            outOf = Integer.parseInt(word);

//System.out.println(word+"  "+word.length()+" "+word.contains("\\u001B[32m")+" not number");
                        }
                        counter++;

                    }
                    Results r = new Results(fileName, "DISCONNECTED", executed, outOf, 0);
                    ResultsForEachFile rf = new ResultsForEachFile(fileName, Integer.parseInt(lineNumber), "Exception", operator, originalLine, mutantLine);
                    arr3.add(rf);
                    arr.add(r);
                    System.out.println("***************e2e**************disconnect*******");
                    System.out.println(r);
                    System.out.println(rf);
                    System.out.println("****************e2e********************");
                    //System.out.println("disconnected executed " + executed + " of " + outOf);
                } else {
                    Results r = new Results(fileName, "ERROR", 0, 0, 0);
                    ResultsForEachFile rf = new ResultsForEachFile(fileName, Integer.parseInt(lineNumber), "Syntax Error", operator, originalLine, mutantLine);
                    arr3.add(rf);
                    arr.add(r);
                    System.out.println(rf);
                    System.out.println(r);
                }
            } else {
                System.out.println("NOT found");
                Results r = new Results(fileName, "ERROR", 0, 0, 0);
                ResultsForEachFile rf = new ResultsForEachFile(fileName, Integer.parseInt(lineNumber), "Syntax Error", operator, originalLine, mutantLine);
                arr3.add(rf);
                arr.add(r);
                System.out.println(rf);
                System.out.println(r);
            }
        }
        //System.out.println("partOfLine " + partOfLine);

//        if (x.contains("\u001B[31m")) {
//            System.out.println( "This line contains red color");
//        }
//        if (x.contains("\u001B[32m")) {
//            System.out.println( "This line contains green color");
//        }
//        if (x.contains("\u001B[33m")) {
//            System.out.println( "This line contains yellow color");
//        }
//        for (int i = 8; i < line.length; i++) {
//            //System.out.println(line[i]);
//            if(line[i].contains("\u001B[31m")){
//                System.out.println("Line number: "+i+line[i]+"This line contains red color");
//            }
//            if(line[i].contains("\u001B[32m")){
//                System.out.println("Line number: "+i+line[i]+"This line contains green color");
//            }
//            if(line[i].contains("\u001B[33m")){
//                System.out.println("Line number: "+i+line[i]+"This line contains yellow color");
//            }
//            
//        }
    }
}
