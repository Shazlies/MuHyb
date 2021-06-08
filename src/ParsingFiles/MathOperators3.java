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
import java.util.StringTokenizer;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Home
 */
public class MathOperators3 {

    ArrayList<String> PlustoMinus = new ArrayList<String>();
    ArrayList<String> restOfPlustoMinus = new ArrayList<String>();
    ArrayList<File> originalFiles1 = new ArrayList<File>();
    ArrayList<MutantInformation> arr = new ArrayList<MutantInformation>();

    public static void main(String[] args) {
        File f = new File("E:\\projects\\test\\_Mutant0\\src\\app\\about\\about.page.ts");
        MathOperators3 m = new MathOperators3();
        m.generateMutant(f);
    }

    public void generateMutant(File file) {

        String outputPlus = "";
        int lineNumber = 0;
        //String output11="";
        String outputMinus = "";
        String outputTimes = "";
        String outputDivide = "";
        String beforeClass = "";
        boolean startScanning = false;
        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNext()) {
                System.out.println(PlustoMinus.size()+ "//////////////////");
                for (int i = 0; i < PlustoMinus.size(); i++) {
                    System.out.println(PlustoMinus.get(i)+ "**************");
                }
                lineNumber++;

                String line = sc.nextLine();

                if (line.contains("class") && !startScanning) {
                    startScanning = true;
                    outputPlus += beforeClass;
                    outputMinus += beforeClass;
                    outputTimes += beforeClass;
                    outputDivide += beforeClass;
                } else if (!line.contains("class") && !startScanning) {
                    beforeClass += line + "\n";
                }
                if (startScanning) {

                    line = line.replaceAll("[+][+]", "@@");

                    if (!line.contains("+")) {
                        outputPlus += line.replaceAll("[@][@]", "++") + "\n";
                        //output11 += line.replaceAll("[@][@]", "++") + "\n";
                        for (int i = 0; i < PlustoMinus.size(); i++) {
                            String o = restOfPlustoMinus.get(i);
                            o += line.replaceAll("[@][@]", "++") + "\n";
                            restOfPlustoMinus.remove(i);
                            restOfPlustoMinus.add(i, o);
                        }
                    } else {
                        int number = StringUtils.countMatches(line, "+");
                        for (int i = 0; i < PlustoMinus.size(); i++) {
                            String o = restOfPlustoMinus.get(i);

                            o += line.replaceAll("[@][@]", "++") + "\n";
                            restOfPlustoMinus.remove(i);
                            restOfPlustoMinus.add(i, o);
                        }
                        setOperator(line, number, outputPlus, file, lineNumber, "+","++", "-", "*", "/");
                       // setPlusOperator(line, number, outputPlus, file, lineNumber);

                        outputPlus += line.replaceAll("[@][@]", "++") + "\n";
                        // output11 += line.replaceAll("[@][@]", "++") + "\n";
                    }

                    //////////// [-] to [+]
                    line = line.replaceAll("[@][@]", "++");
                    line = line.replaceAll("[-][-]", "@@");

                    if (!line.contains("-")) {
                        outputMinus += line.replaceAll("[@][@]", "--") + "\n";

                    } else {
                        int number = StringUtils.countMatches(line, "-");
                        setOperator(line, number, outputPlus, file, lineNumber, "-","--", "+", "*", "/");
                        //setMinusOperator(line, number, outputMinus, file, lineNumber);
                        outputMinus += line.replaceAll("[@][@]", "--") + "\n";

                    }
                    line = line.replaceAll("[@][@]", "--");
                    // from [*] to [/]
                    if (!line.contains("*")) {
                        outputTimes += line + "\n";

                    } else {
                        int number = StringUtils.countMatches(line, "*");
                        setOperator(line, number, outputPlus, file, lineNumber, "*","", "-", "+", "/");
                        //setTimesOperator(line, number, outputTimes, file, lineNumber);

                        outputTimes += line + "\n";
                    }
                    /////
                    if (!line.contains("/")) {
                        outputDivide += line + "\n";

                    } else {
                        int number = StringUtils.countMatches(line, "/");
                        setOperator(line, number, outputPlus, file, lineNumber, "/","", "-", "*", "+");
                        //setDivideOperator(line, number, outputDivide, file, lineNumber);

                        outputDivide += line + "\n";
                    }

                }
                ////

            }
            sc.close();

            ///////////////////
            for (int i = 0; i < PlustoMinus.size(); i++) {

                String str1 = PlustoMinus.get(i) + restOfPlustoMinus.get(i);
                PlustoMinus.remove(i);
                PlustoMinus.add(i, str1);
                PlustoMinus.get(i);
            }
            
            for (int i = 0; i < this.arr.size(); i++) {
                System.out.println(arr.get(i));
            }
                    
//            for (int i = 0; i < PlustoMinusLineNo.size(); i++) {
//                System.out.println(PlustoMinusLineNo.get(i)+" "+PlustoMinusOriginalLine.get(i)+" "+PlustoMinusMutationLine.get(i));
//            }
            //System.out.println(MinustoPlus.size());
            if (!PlustoMinus.isEmpty()) {
                GenerateMutantFiles gm = new GenerateMutantFiles();
                gm.generateFiles(PlustoMinus, originalFiles1, this.arr, 0);
                //System.out.println("Number of Mutants: " + MutantsGenerator.counter);
            }

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }
    
    public void setOperator(String line, int number, String output1, File file, int lineNumber, String originalOperator, String operatorReplacement, String fisrtOperator, String secondOperator, String thirdOperator){
        String tokens[] = line.split("["+originalOperator+"]");
        String arr[] = new String[number];
        String arr2[] = new String[number];
        String arr3[] = new String[number];
        //
        for (int i = 0; i < number; i++) {
            arr[i] = "";
            arr2[i] = "";
            arr3[i] = "";
        }
        int counter = 0;
        int ssNumber = 0;
        int sNumber = 0;
        if (tokens.length > 1) {
            for (int i = 0; i < tokens.length; i++) {
                String partLine = tokens[i];
                ssNumber += StringUtils.countMatches(partLine, "\'");
                sNumber += StringUtils.countMatches(partLine, "\"");
                for (int j = 0; j < number; j++) {
                    if (counter == j) {
                        if (ssNumber % 2 != 0 || sNumber % 2 != 0) {
                            arr[j] = "$^%&%&";
                            arr2[j] = "$^%&%&";
                            arr3[j] = "$^%&%&";
                        } else {
                            arr[j] += partLine.replaceAll("[@][@]", operatorReplacement) + fisrtOperator;
                            arr2[j] += partLine.replaceAll("[@][@]", operatorReplacement) + secondOperator;
                            arr3[j] += partLine.replaceAll("[@][@]", operatorReplacement) + thirdOperator;
                        }

                    } else {
                        arr[j] += partLine.replaceAll("[@][@]", operatorReplacement) + originalOperator;
                        arr2[j] += partLine.replaceAll("[@][@]", operatorReplacement) + originalOperator;
                        arr3[j] += partLine.replaceAll("[@][@]", operatorReplacement) + originalOperator;
                    }

                }
                counter++;
            }
        }
        for (int i = 0; i < number; i++) {
            if (!arr[i].contains("$^%&%&")) {
                arr[i] = arr[i].substring(0, arr[i].length() - 1);
                MutantInformation m = new MutantInformation(lineNumber, "Math Operator", line, arr[i]);
                this.arr.add(m);
                PlustoMinus.add(output1 + arr[i] + "\n");
                restOfPlustoMinus.add("");
                originalFiles1.add(file);
            }
            if (!arr2[i].contains("$^%&%&")) {
                arr2[i] = arr2[i].substring(0, arr2[i].length() - 1);
                MutantInformation m = new MutantInformation(lineNumber, "Math Operator", line, arr2[i]);
                this.arr.add(m);
                PlustoMinus.add(output1 + arr2[i] + "\n");
                restOfPlustoMinus.add("");
                originalFiles1.add(file);
            }
            if (!arr3[i].contains("$^%&%&")) {
                arr3[i] = arr3[i].substring(0, arr3[i].length() - 1);
                MutantInformation m = new MutantInformation(lineNumber, "Math Operator", line, arr3[i]);
                this.arr.add(m);
                PlustoMinus.add(output1 + arr3[i] + "\n");

                restOfPlustoMinus.add("");
                originalFiles1.add(file);
            }
        }
    }

//    public void setPlusOperator(String line, int number, String output1, File file, int lineNumber) {
//
//        String tokens[] = line.split("[+]");
//        String arr[] = new String[number];
//        String arr2[] = new String[number];
//        String arr3[] = new String[number];
//        //
//        for (int i = 0; i < number; i++) {
//            arr[i] = "";
//            arr2[i] = "";
//            arr3[i] = "";
//        }
//        int counter = 0;
//        int ssNumber = 0;
//        int sNumber = 0;
//        if (tokens.length > 1) {
//            for (int i = 0; i < tokens.length; i++) {
//                String partLine = tokens[i];
//                ssNumber += StringUtils.countMatches(partLine, "\'");
//                sNumber += StringUtils.countMatches(partLine, "\"");
//                for (int j = 0; j < number; j++) {
//                    if (counter == j) {
//                        if (ssNumber % 2 != 0 || sNumber % 2 != 0) {
//                            arr[j] = "$^%&%&";
//                            arr2[j] = "$^%&%&";
//                            arr3[j] = "$^%&%&";
//                        } else {
//                            arr[j] += partLine.replaceAll("[@][@]", "++") + "-";
//                            arr2[j] += partLine.replaceAll("[@][@]", "++") + "*";
//                            arr3[j] += partLine.replaceAll("[@][@]", "++") + "/";
//                        }
//
//                    } else {
//                        arr[j] += partLine.replaceAll("[@][@]", "++") + "+";
//                        arr2[j] += partLine.replaceAll("[@][@]", "++") + "+";
//                        arr3[j] += partLine.replaceAll("[@][@]", "++") + "+";
//                    }
//
//                }
//                counter++;
//            }
//        }
//        for (int i = 0; i < number; i++) {
//            if (!arr[i].contains("$^%&%&")) {
//                arr[i] = arr[i].substring(0, arr[i].length() - 1);
//                PlustoMinusLineNo.add(lineNumber);
//                PlustoMinusOriginalLine.add(line);
//                PlustoMinusMutationLine.add(arr[i]);
//                PlustoMinusOperator.add("Math Operator");
//                PlustoMinus.add(output1 + arr[i] + "\n");
//                restOfPlustoMinus.add("");
//                originalFiles1.add(file);
//            }
//            if (!arr2[i].contains("$^%&%&")) {
//                arr2[i] = arr2[i].substring(0, arr2[i].length() - 1);
//                PlustoMinusLineNo.add(lineNumber);
//                PlustoMinusOriginalLine.add(line);
//                PlustoMinusMutationLine.add(arr2[i]);
//                PlustoMinusOperator.add("Math Operator");
//                PlustoMinus.add(output1 + arr2[i] + "\n");
//                restOfPlustoMinus.add("");
//                originalFiles1.add(file);
//            }
//            if (!arr3[i].contains("$^%&%&")) {
//                arr3[i] = arr3[i].substring(0, arr3[i].length() - 1);
//                PlustoMinusLineNo.add(lineNumber);
//                PlustoMinusOriginalLine.add(line);
//                PlustoMinusMutationLine.add(arr3[i]);
//                PlustoMinusOperator.add("Math Operator");
//                PlustoMinus.add(output1 + arr3[i] + "\n");
//
//                restOfPlustoMinus.add("");
//                originalFiles1.add(file);
//            }
//        }
//    }
//
//    public void setMinusOperator(String line, int number, String outputMinus, File file, int lineNumber) {
//        System.out.println(lineNumber);
//        String tokens[] = line.split("-");
//        String arr[] = new String[number];
//        String arr2[] = new String[number];
//        String arr3[] = new String[number];
//        //
//        for (int i = 0; i < number; i++) {
//            arr[i] = "";
//            arr2[i] = "";
//            arr3[i] = "";
//        }
//        int counter = 0;
//        int ssNumber = 0;
//        int sNumber = 0;
//
//        if (tokens.length > 1) {
//            for (int i = 0; i < tokens.length; i++) {
//                String partLine = tokens[i];
//                ssNumber += StringUtils.countMatches(partLine, "\'");
//                sNumber += StringUtils.countMatches(partLine, "\"");
//                for (int j = 0; j < number; j++) {
//                    if (counter == j) {
//
//                        if (ssNumber % 2 != 0 || sNumber % 2 != 0) {
//                            arr[j] = "$^%&%&";
//                            arr2[j] = "$^%&%&";
//                            arr3[j] = "$^%&%&";
//                        } else {
//                            arr[j] += partLine.replaceAll("[@][@]", "++") + "+";
//                            arr2[j] += partLine.replaceAll("[@][@]", "++") + "*";
//                            arr3[j] += partLine.replaceAll("[@][@]", "++") + "/";
//                        }
//
//                    } else {
//                        arr[j] += partLine.replaceAll("[@][@]", "++") + "-";
//                        arr2[j] += partLine.replaceAll("[@][@]", "++") + "-";
//                        arr3[j] += partLine.replaceAll("[@][@]", "++") + "-";
//
//                    }
//
//                }
//                counter++;
//            }
//        }
//        for (int i = 0; i < number; i++) {
//            if (!arr[i].contains("$^%&%&")) {
//                arr[i] = arr[i].substring(0, arr[i].length() - 1);
//                PlustoMinusLineNo.add(lineNumber);
//                PlustoMinusOriginalLine.add(line);
//                PlustoMinusMutationLine.add(arr[i]);
//                PlustoMinusOperator.add("Math Operator");
//                PlustoMinus.add(outputMinus + arr[i] + "\n");
//                restOfPlustoMinus.add("");
//                originalFiles1.add(file);
//            }
//            if (!arr2[i].contains("$^%&%&")) {
//                arr2[i] = arr2[i].substring(0, arr2[i].length() - 1);
//                PlustoMinusLineNo.add(lineNumber);
//                PlustoMinusOriginalLine.add(line);
//                PlustoMinusMutationLine.add(arr2[i]);
//                PlustoMinusOperator.add("Math Operator");
//                PlustoMinus.add(outputMinus + arr2[i] + "\n");
//                restOfPlustoMinus.add("");
//                originalFiles1.add(file);
//            }
//            if (!arr3[i].contains("$^%&%&")) {
//                arr3[i] = arr3[i].substring(0, arr3[i].length() - 1);
//                PlustoMinusLineNo.add(lineNumber);
//                PlustoMinusOriginalLine.add(line);
//                PlustoMinusMutationLine.add(arr3[i]);
//                PlustoMinusOperator.add("Math Operator");
//                PlustoMinus.add(outputMinus + arr3[i] + "\n");
//                restOfPlustoMinus.add("");
//                originalFiles1.add(file);
//            }
//        }
//    }
//
//    public void setTimesOperator(String line, int number, String outputTimes, File file, int lineNumber) {
//        String tokens[] = line.split("[*]");
//        String arr[] = new String[number];
//        String arr2[] = new String[number];
//        String arr3[] = new String[number];
//        //
//        for (int i = 0; i < number; i++) {
//            arr[i] = "";
//            arr2[i] = "";
//            arr3[i] = "";
//        }
//        int counter = 0;
//        int ssNumber = 0;
//        int sNumber = 0;
//
//        if (tokens.length > 1) {
//            for (int i = 0; i < tokens.length; i++) {
//                String partLine = tokens[i];
//                ssNumber += StringUtils.countMatches(partLine, "\'");
//                sNumber += StringUtils.countMatches(partLine, "\"");
//                for (int j = 0; j < number; j++) {
//                    if (counter == i) {
//                        if (ssNumber % 2 != 0 || sNumber % 2 != 0) {
//                            arr[j] = "$^%&%&";
//                            arr2[j] = "$^%&%&";
//                            arr3[j] = "$^%&%&";
//                        } else {
//                            arr[j] += partLine.replaceAll("[@][@]", "++") + "+";
//                            arr2[j] += partLine.replaceAll("[@][@]", "++") + "-";
//                            arr3[j] += partLine.replaceAll("[@][@]", "++") + "/";
//                        }
//
//                    } else {
//                        arr[j] += partLine.replaceAll("[@][@]", "++") + "*";
//                        arr2[j] += partLine.replaceAll("[@][@]", "++") + "*";
//                        arr3[j] += partLine.replaceAll("[@][@]", "++") + "*";
//
//                    }
//
//                }
//                counter++;
//            }
//        }
//
//        for (int i = 0; i < number; i++) {
//            if (!arr[i].contains("$^%&%&")) {
//                arr[i] = arr[i].substring(0, arr[i].length() - 1);
//                PlustoMinusLineNo.add(lineNumber);
//                PlustoMinusOriginalLine.add(line);
//                PlustoMinusMutationLine.add(arr[i]);
//                PlustoMinusOperator.add("Math Operator");
//                PlustoMinus.add(outputTimes + arr[i] + "\n");
//                restOfPlustoMinus.add("");
//                originalFiles1.add(file);
//            }
//            if (!arr2[i].contains("$^%&%&")) {
//                arr2[i] = arr2[i].substring(0, arr2[i].length() - 1);
//                PlustoMinusLineNo.add(lineNumber);
//                PlustoMinusOriginalLine.add(line);
//                PlustoMinusMutationLine.add(arr2[i]);
//                PlustoMinusOperator.add("Math Operator");
//                PlustoMinus.add(outputTimes + arr2[i] + "\n");
//                restOfPlustoMinus.add("");
//                originalFiles1.add(file);
//            }
//            if (!arr3[i].contains("$^%&%&")) {
//                arr3[i] = arr3[i].substring(0, arr3[i].length() - 1);
//                PlustoMinusLineNo.add(lineNumber);
//                PlustoMinusOriginalLine.add(line);
//                PlustoMinusMutationLine.add(arr3[i]);
//                PlustoMinusOperator.add("Math Operator");
//                PlustoMinus.add(outputTimes + arr3[i] + "\n");
//                restOfPlustoMinus.add("");
//                originalFiles1.add(file);
//            }
//        }
//    }
//
//    public void setDivideOperator(String line, int number, String outputDivide, File file, int lineNumber) {
//        String tokens[] = line.split("/");
//        String arr[] = new String[number];
//        String arr2[] = new String[number];
//        String arr3[] = new String[number];
//        //
//        for (int i = 0; i < number; i++) {
//            arr[i] = "";
//            arr2[i] = "";
//            arr3[i] = "";
//        }
//        int counter = 0;
//        int ssNumber = 0;
//        int sNumber = 0;
//        if (tokens.length > 1) {
//            for (int i = 0; i < tokens.length; i++) {
//                String partLine = tokens[i];
//                ssNumber += StringUtils.countMatches(partLine, "\'");
//                sNumber += StringUtils.countMatches(partLine, "\"");
//                for (int j = 0; j < number; j++) {
//                    if (counter == j) {
//
//                        if (ssNumber % 2 != 0 || sNumber % 2 != 0) {
//                            arr[j] = "$^%&%&";
//                            arr2[j] = "$^%&%&";
//                            arr3[j] = "$^%&%&";
//                        } else {
//                            arr[j] += partLine.replaceAll("[@][@]", "++") + "+";
//                            arr2[j] += partLine.replaceAll("[@][@]", "++") + "-";
//                            arr3[j] += partLine.replaceAll("[@][@]", "++") + "*";
//                        }
//
//                    } else {
//                        arr[j] += partLine.replaceAll("[@][@]", "++") + "/";
//                        arr2[j] += partLine.replaceAll("[@][@]", "++") + "/";
//                        arr3[j] += partLine.replaceAll("[@][@]", "++") + "/";
//
//                    }
//
//                }
//                counter++;
//            }
//        }
//
//        for (int i = 0; i < number; i++) {
//            if (!arr[i].contains("$^%&%&")) {
//                arr[i] = arr[i].substring(0, arr[i].length() - 1);
//                PlustoMinusLineNo.add(lineNumber);
//                PlustoMinusOriginalLine.add(line);
//                PlustoMinusMutationLine.add(arr[i]);
//                PlustoMinusOperator.add("Math Operator");
//                PlustoMinus.add(outputDivide + arr[i] + "\n");
//                restOfPlustoMinus.add("");
//                originalFiles1.add(file);
//            }
//            if (!arr2[i].contains("$^%&%&")) {
//                arr2[i] = arr2[i].substring(0, arr2[i].length() - 1);
//                PlustoMinusLineNo.add(lineNumber);
//                PlustoMinusOriginalLine.add(line);
//                PlustoMinusMutationLine.add(arr2[i]);
//                PlustoMinusOperator.add("Math Operator");
//                PlustoMinus.add(outputDivide + arr2[i] + "\n");
//                restOfPlustoMinus.add("");
//                originalFiles1.add(file);
//            }
//            if (!arr3[i].contains("$^%&%&")) {
//                arr3[i] = arr3[i].substring(0, arr3[i].length() - 1);
//                PlustoMinusLineNo.add(lineNumber);
//                PlustoMinusOriginalLine.add(line);
//                PlustoMinusMutationLine.add(arr3[i]);
//                PlustoMinusOperator.add("Math Operator");
//                PlustoMinus.add(outputDivide + arr3[i] + "\n");
//                restOfPlustoMinus.add("");
//                originalFiles1.add(file);
//            }
//        }
//    }

}
