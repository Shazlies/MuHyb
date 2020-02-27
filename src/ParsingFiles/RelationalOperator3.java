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
public class RelationalOperator3 {

    ArrayList<String> lessThan = new ArrayList<String>();
    ArrayList<String> restOflessThan = new ArrayList<String>();
    ArrayList<File> originalFiles1 = new ArrayList<File>();

    ArrayList<String> lessThanorEqual = new ArrayList<String>();
    ArrayList<String> restOflessThanorEqual = new ArrayList<String>();
    ArrayList<File> originalFiles2 = new ArrayList<File>();

    ArrayList<String> greaterThan = new ArrayList<String>();
    ArrayList<String> restOfgreaterThan = new ArrayList<String>();
    ArrayList<File> originalFiles3 = new ArrayList<File>();

    ArrayList<String> greaterThanorEqual = new ArrayList<String>();
    ArrayList<String> restOfGreaterThanorEqual = new ArrayList<String>();
    ArrayList<File> originalFiles4 = new ArrayList<File>();

    ArrayList<String> equalEqual = new ArrayList<String>();
    ArrayList<String> restOfEqualEqual = new ArrayList<String>();
    ArrayList<File> originalFiles5 = new ArrayList<File>();

    ArrayList<String> notEqual = new ArrayList<String>();
    ArrayList<String> restOfNotEqual = new ArrayList<String>();
    ArrayList<File> originalFiles6 = new ArrayList<File>();

    public void generateMutant(File file) {

        String outputLessThanOrEqual = "";
        String outputGreaterThanOrEqual = "";
        String outputEqualEqual = "";
        String outputNotEqual = "";

        String beforeClass = "";
        boolean startScanning = false;
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                if (line.contains("class") && !startScanning) {
                    startScanning = true;
                    outputLessThanOrEqual += beforeClass;
                } else if (!line.contains("class") && !startScanning) {
                    beforeClass += line + "\n";
                }
                if (startScanning) {
                    if (!line.contains("<=")) {
                        outputLessThanOrEqual += line + "\n";
                        //output11 += line. + "\n";
                        for (int i = 0; i < lessThanorEqual.size(); i++) {
                            String o = restOflessThanorEqual.get(i);
                            o += line + "\n";
                            restOflessThanorEqual.remove(i);
                            restOflessThanorEqual.add(i, o);
                        }
                    } else {
                        int number = StringUtils.countMatches(line, "<=");
                        for (int i = 0; i < lessThanorEqual.size(); i++) {
                            String o = restOflessThanorEqual.get(i);
                            o += line + "\n";
                            restOflessThanorEqual.remove(i);
                            restOflessThanorEqual.add(i, o);
                        }
                        setLessThanOrEqualOperator(line, number, outputLessThanOrEqual, file);
                        outputLessThanOrEqual += line + "\n";
                        // output11 += line + "\n";
                    }

                    if (!line.contains(">=")) {
                        outputGreaterThanOrEqual += line + "\n";
                        //output11 += line. + "\n";
                        for (int i = 0; i < greaterThanorEqual.size(); i++) {
                            String o = restOfGreaterThanorEqual.get(i);
                            o += line + "\n";
                            restOfGreaterThanorEqual.remove(i);
                            restOfGreaterThanorEqual.add(i, o);
                        }
                    } else {
                        int number = StringUtils.countMatches(line, ">=");
                        for (int i = 0; i < greaterThanorEqual.size(); i++) {
                            String o = restOfGreaterThanorEqual.get(i);
                            o += line + "\n";
                            restOfGreaterThanorEqual.remove(i);
                            restOfGreaterThanorEqual.add(i, o);
                        }
                        setGreaterThanOrEqualOperator(line, number, outputGreaterThanOrEqual, file);
                        outputGreaterThanOrEqual += line + "\n";
                        // output11 += line + "\n";
                    }
                    if (!line.contains("==")) {
                        outputEqualEqual += line + "\n";
                        //output11 += line. + "\n";
                        for (int i = 0; i < equalEqual.size(); i++) {
                            String o = restOfEqualEqual.get(i);
                            o += line + "\n";
                            restOfEqualEqual.remove(i);
                            restOfEqualEqual.add(i, o);
                        }
                    } else {
                        int number = StringUtils.countMatches(line, "==");
                        for (int i = 0; i < equalEqual.size(); i++) {
                            String o = restOfEqualEqual.get(i);
                            o += line + "\n";
                            restOfEqualEqual.remove(i);
                            restOfEqualEqual.add(i, o);
                        }
                        setEqualEqualOperator(line, number, outputEqualEqual, file);
                        outputEqualEqual += line + "\n";
                        // output11 += line + "\n";
                    }
                    if (!line.contains("!=")) {
                        outputNotEqual += line + "\n";
                        //output11 += line. + "\n";
                        for (int i = 0; i < notEqual.size(); i++) {
                            String o = restOfNotEqual.get(i);
                            o += line + "\n";
                            restOfNotEqual.remove(i);
                            restOfNotEqual.add(i, o);
                        }
                    } else {
                        int number = StringUtils.countMatches(line, "!=");
                        for (int i = 0; i < notEqual.size(); i++) {
                            String o = restOfNotEqual.get(i);
                            o += line + "\n";
                            restOfNotEqual.remove(i);
                            restOfNotEqual.add(i, o);
                        }
                        setEqualEqualOperator(line, number, outputNotEqual, file);
                        outputNotEqual += line + "\n";
                        // output11 += line + "\n";
                    }

                }
            }
            sc.close();

            for (int i = 0; i < lessThanorEqual.size(); i++) {
                String str1 = lessThanorEqual.get(i) + restOflessThanorEqual.get(i);
                lessThanorEqual.remove(i);
                lessThanorEqual.add(i, str1);
                lessThanorEqual.get(i);
            }

            for (int i = 0; i < greaterThanorEqual.size(); i++) {
                String str1 = greaterThanorEqual.get(i) + restOfGreaterThanorEqual.get(i);
                greaterThanorEqual.remove(i);
                greaterThanorEqual.add(i, str1);
                greaterThanorEqual.get(i);
            }

            for (int i = 0; i < equalEqual.size(); i++) {
                String str1 = equalEqual.get(i) + restOfEqualEqual.get(i);
                equalEqual.remove(i);
                equalEqual.add(i, str1);
                equalEqual.get(i);
            }
            for (int i = 0; i < notEqual.size(); i++) {
                String str1 = notEqual.get(i) + restOfNotEqual.get(i);
                notEqual.remove(i);
                notEqual.add(i, str1);
                notEqual.get(i);
            }
            
            for (int i = 0; i < greaterThanorEqual.size(); i++) {
                lessThanorEqual.add(greaterThanorEqual.get(i));
            }
            
            for (int i = 0; i < equalEqual.size(); i++) {
                lessThanorEqual.add(equalEqual.get(i));
            }
            for (int i = 0; i < notEqual.size(); i++) {
                lessThanorEqual.add(notEqual.get(i));
            }
            
            for (int i = 0; i < originalFiles4.size(); i++) {
                originalFiles2.add(originalFiles4.get(i));
            }
            for (int i = 0; i < originalFiles5.size(); i++) {
                originalFiles2.add(originalFiles5.get(i));
            }
            for (int i = 0; i < originalFiles6.size(); i++) {
                originalFiles2.add(originalFiles6.get(i));
            }
            
            if (!lessThanorEqual.isEmpty()) {
                GenerateMutantFiles gm = new GenerateMutantFiles();
                //gm.generateFiles(lessThanorEqual, originalFiles2, "Relational Operator");
                //System.out.println("Number of Mutants: " + MutantsGenerator.counter);
            }
            

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }

    }

    public void setLessThanOrEqualOperator(String line, int number, String output1, File file) {
        String tokens[] = line.split("<=");
        String arr[] = new String[number];
        String arr2[] = new String[number];
        String arr3[] = new String[number];
        String arr4[] = new String[number];
        String arr5[] = new String[number];
        //
        for (int i = 0; i < number; i++) {
            arr[i] = "";
            arr2[i] = "";
            arr3[i] = "";
            arr4[i] = "";
            arr5[i] = "";
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
                            arr[j] += partLine + "<";
                            arr2[j] += partLine + ">";
                            arr3[j] += partLine + ">=";
                            arr4[j] += partLine + "==";
                            arr5[j] += partLine + "!=";
                        }

                    } else {
                        arr[j] += partLine + "<=";
                        arr2[j] += partLine + "<=";
                        arr3[j] += partLine + "<=";
                        arr4[j] += partLine + "<=";
                        arr5[j] += partLine + "<=";

                    }

                }
                counter++;
            }
        }
        for (int i = 0; i < number; i++) {
            if (!arr[i].contains("$^%&%&")) {
                arr[i] = arr[i].substring(0, arr[i].length() - 2);
                lessThanorEqual.add(output1 + arr[i] + "\n");
                restOflessThanorEqual.add("");
                originalFiles2.add(file);
            }
            if (!arr2[i].contains("$^%&%&")) {
                arr2[i] = arr2[i].substring(0, arr2[i].length() - 2);
                lessThanorEqual.add(output1 + arr2[i] + "\n");
                restOflessThanorEqual.add("");
                originalFiles2.add(file);
            }
            if (!arr3[i].contains("$^%&%&")) {
                arr3[i] = arr3[i].substring(0, arr3[i].length() - 2);
                lessThanorEqual.add(output1 + arr3[i] + "\n");
                restOflessThanorEqual.add("");
                originalFiles2.add(file);
            }
            if (!arr4[i].contains("$^%&%&")) {
                arr4[i] = arr4[i].substring(0, arr4[i].length() - 2);
                lessThanorEqual.add(output1 + arr4[i] + "\n");
                restOflessThanorEqual.add("");
                originalFiles2.add(file);
            }
            if (!arr5[i].contains("$^%&%&")) {
                arr5[i] = arr5[i].substring(0, arr5[i].length() - 2);
                lessThanorEqual.add(output1 + arr5[i] + "\n");
                restOflessThanorEqual.add("");
                originalFiles2.add(file);
            }
        }
    }

    public void setGreaterThanOrEqualOperator(String line, int number, String output1, File file) {
        String tokens[] = line.split(">=");
        String arr[] = new String[number];
        String arr2[] = new String[number];
        String arr3[] = new String[number];
        String arr4[] = new String[number];
        String arr5[] = new String[number];
        //
        for (int i = 0; i < number; i++) {
            arr[i] = "";
            arr2[i] = "";
            arr3[i] = "";
            arr4[i] = "";
            arr5[i] = "";
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
                            arr4[j] = "$^%&%&";
                            arr5[j] = "$^%&%&";
                        } else {
                            arr[j] += partLine + "<";
                            arr2[j] += partLine + ">";
                            arr3[j] += partLine + "<=";
                            arr4[j] += partLine + "==";
                            arr5[j] += partLine + "!=";
                        }

                    } else {
                        arr[j] += partLine + ">=";
                        arr2[j] += partLine + ">=";
                        arr3[j] += partLine + ">=";
                        arr4[j] += partLine + ">=";
                        arr5[j] += partLine + ">=";

                    }

                }
                counter++;
            }
        }
        for (int i = 0; i < number; i++) {
            if (!arr[i].contains("$^%&%&")) {
                arr[i] = arr[i].substring(0, arr[i].length() - 2);
                greaterThanorEqual.add(output1 + arr[i] + "\n");
                restOfGreaterThanorEqual.add("");
                originalFiles4.add(file);
            }

            if (!arr2[i].contains("$^%&%&")) {
                arr2[i] = arr2[i].substring(0, arr2[i].length() - 2);
                greaterThanorEqual.add(output1 + arr2[i] + "\n");
                restOfGreaterThanorEqual.add("");
                originalFiles4.add(file);
            }
            if (!arr3[i].contains("$^%&%&")) {
                arr3[i] = arr3[i].substring(0, arr3[i].length() - 2);
                greaterThanorEqual.add(output1 + arr3[i] + "\n");
                restOfGreaterThanorEqual.add("");
                originalFiles4.add(file);
            }
            if (!arr4[i].contains("$^%&%&")) {
                arr4[i] = arr4[i].substring(0, arr4[i].length() - 2);
                greaterThanorEqual.add(output1 + arr4[i] + "\n");
                restOfGreaterThanorEqual.add("");
                originalFiles4.add(file);
            }
            if (!arr5[i].contains("$^%&%&")) {
                arr5[i] = arr5[i].substring(0, arr5[i].length() - 2);
                greaterThanorEqual.add(output1 + arr5[i] + "\n");
                restOfGreaterThanorEqual.add("");
                originalFiles4.add(file);
            }
        }
    }

    public void setEqualEqualOperator(String line, int number, String output1, File file) {
        String tokens[] = line.split("==");
        String arr[] = new String[number];
        String arr2[] = new String[number];
        String arr3[] = new String[number];
        String arr4[] = new String[number];
        String arr5[] = new String[number];
        for (int i = 0; i < number; i++) {
            arr[i] = "";
            arr2[i] = "";
            arr3[i] = "";
            arr4[i] = "";
            arr5[i] = "";
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
                            arr4[j] = "$^%&%&";
                            arr5[j] = "$^%&%&";
                        } else {
                            arr[j] += partLine + "<";
                            arr2[j] += partLine + ">";
                            arr3[j] += partLine + ">=";
                            arr4[j] += partLine + "<=";
                            arr5[j] += partLine + "!=";
                        }

                    } else {
                        arr[j] += partLine + "==";
                        arr2[j] += partLine + "==";
                        arr3[j] += partLine + "==";
                        arr4[j] += partLine + "==";
                        arr5[j] += partLine + "==";

                    }

                }
                counter++;
            }
        }
        for (int i = 0; i < number; i++) {
            if (!arr[i].contains("$^%&%&")) {
                arr[i] = arr[i].substring(0, arr[i].length() - 2);
                equalEqual.add(output1 + arr[i] + "\n");
                restOfEqualEqual.add("");
                originalFiles5.add(file);
            }

            if (!arr2[i].contains("$^%&%&")) {
                arr2[i] = arr2[i].substring(0, arr2[i].length() - 2);
                equalEqual.add(output1 + arr2[i] + "\n");
                restOfEqualEqual.add("");
                originalFiles5.add(file);
            }
            if (!arr3[i].contains("$^%&%&")) {
                arr3[i] = arr3[i].substring(0, arr3[i].length() - 2);
                equalEqual.add(output1 + arr3[i] + "\n");
                restOfEqualEqual.add("");
                originalFiles5.add(file);
            }
            if (!arr4[i].contains("$^%&%&")) {
                arr4[i] = arr4[i].substring(0, arr4[i].length() - 2);
                equalEqual.add(output1 + arr4[i] + "\n");
                restOfEqualEqual.add("");
                originalFiles5.add(file);
            }
            if (!arr5[i].contains("$^%&%&")) {
                arr5[i] = arr5[i].substring(0, arr5[i].length() - 2);
                equalEqual.add(output1 + arr5[i] + "\n");
                restOfEqualEqual.add("");
                originalFiles5.add(file);
            }
        }
    }

    public void setNotEqualOperator(String line, int number, String output1, File file) {
        String tokens[] = line.split("==");
        String arr[] = new String[number];
        String arr2[] = new String[number];
        String arr3[] = new String[number];
        String arr4[] = new String[number];
        String arr5[] = new String[number];
        //
        for (int i = 0; i < number; i++) {
            arr[i] = "";
            arr2[i] = "";
            arr3[i] = "";
            arr4[i] = "";
            arr5[i] = "";
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
                            arr4[j] = "$^%&%&";
                            arr5[j] = "$^%&%&";
                        } else {
                            arr[j] += partLine + "<";
                            arr2[j] += partLine + ">";
                            arr3[j] += partLine + ">=";
                            arr4[j] += partLine + "<=";
                            arr5[j] += partLine + "==";
                        }

                    } else {
                        arr[j] += partLine + "!=";
                        arr2[j] += partLine + "!=";
                        arr3[j] += partLine + "!=";
                        arr4[j] += partLine + "!=";
                        arr5[j] += partLine + "!=";

                    }

                }
                counter++;
            }
        }
        for (int i = 0; i < number; i++) {
            if (!arr[i].contains("$^%&%&")) {
                arr[i] = arr[i].substring(0, arr[i].length() - 2);
                notEqual.add(output1 + arr[i] + "\n");
                restOfNotEqual.add("");
                originalFiles6.add(file);
            }

            if (!arr2[i].contains("$^%&%&")) {
                arr2[i] = arr2[i].substring(0, arr2[i].length() - 2);
                notEqual.add(output1 + arr2[i] + "\n");
                restOfNotEqual.add("");
                originalFiles6.add(file);
            }
            if (!arr3[i].contains("$^%&%&")) {
                arr3[i] = arr3[i].substring(0, arr3[i].length() - 2);
                notEqual.add(output1 + arr3[i] + "\n");
                restOfNotEqual.add("");
                originalFiles6.add(file);
            }
            if (!arr4[i].contains("$^%&%&")) {
                arr4[i] = arr4[i].substring(0, arr4[i].length() - 2);
                notEqual.add(output1 + arr4[i] + "\n");
                restOfNotEqual.add("");
                originalFiles6.add(file);
            }
            if (!arr5[i].contains("$^%&%&")) {
                arr5[i] = arr5[i].substring(0, arr5[i].length() - 2);
                notEqual.add(output1 + arr5[i] + "\n");
                restOfNotEqual.add("");
                originalFiles6.add(file);
            }
        }
    }

}
