/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in 
the editor.
 */
package ParsingFiles;

//import org.apache.commons..io.FileUtils;;
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
public class IncrementsOperator3 {

    ArrayList<String> stringPlusWithChange = new ArrayList<String>();
    ArrayList<String> restOfPlusString = new ArrayList<String>();
    ArrayList<String> stringMinusWithChange = new ArrayList<String>();
    ArrayList<String> restOfMinusString = new ArrayList<String>();
    ArrayList<File> originalFiles1 = new ArrayList<File>();
    ArrayList<File> originalFiles2 = new ArrayList<File>();

    public void generateMutant(File file) {
        //System.out.println(file.getAbsoluteFile() + " Searching here..");
        String output1 = "";
        String output2 = "";
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {

                String line = sc.nextLine();

                if (!line.contains("++")) {
                    output1 += line + "\n";
                    for (int i = 0; i < stringPlusWithChange.size(); i++) {
                        String o = restOfPlusString.get(i);
                        o += line + "\n";
                        restOfPlusString.remove(i);
                        restOfPlusString.add(i, o);
                    }
                } else {
                    int number = StringUtils.countMatches(line, "++");
                    for (int i = 0; i < stringPlusWithChange.size(); i++) {
                        String o = restOfPlusString.get(i);
                        o += line + "\n";
                        restOfPlusString.remove(i);
                        restOfPlusString.add(i, o);
                    }

                    StringTokenizer st = new StringTokenizer(line, "++");
                    String arr[] = new String[number];
                    for (int i = 0; i < number; i++) {
                        arr[i] = "";
                    }
                    int counter = 0;
                    while (st.hasMoreTokens()) {
                        String partLine = st.nextToken();
                        for (int i = 0; i < number; i++) {
                            if (counter == i) {
                                arr[i] += partLine + "--";

                            } else {
                                arr[i] += partLine + "++";
                            }

                        }
                        counter++;
                    }
                    for (int i = 0; i < number; i++) {
                        arr[i] = arr[i].substring(0, arr[i].length() - 2);
                        System.out.println(arr[i] + " /////////////////////");
                    }

                    for (int i = 0; i < number; i++) {
                        stringPlusWithChange.add(output1 + arr[i] + "\n");
                        restOfPlusString.add("");
                        originalFiles1.add(file);
                    }
                    output1 += line + "\n";
                }
                // System.out.println(StringUtils.);
                //String oldLine = line;

//                System.out.println(line);
//                //System.out.println(found);
//                boolean firstItr = true;
//
//                if (line.contains("++")) {
//                    System.out.println("here");
//                    for (int i = 0; i < stringPlusWithChange.size() && firstItr; i++) {
//
//                        String o = restOfPlusString.get(i);
//
//                        o += line + "\n";
//                        restOfPlusString.remove(i);
//                        restOfPlusString.add(i, o);
//
//                    }
//                    firstItr = false;
//
//                    String line2 = oldLine.replaceFirst("[+][+]", "--");
//                    //System.out.println(line);
//                    stringPlusWithChange.add(output1 + line2 + "\n");
//                    restOfPlusString.add("");
//                    originalFiles1.add(file);
//
//                    //if(!line2.contains("++")){
//                    //output1 += line + "\n";
//                    //}
//                    System.out.println(line2);
//                    //pw.println(line + "\n");
//
//                    MutantsGenerator.counter++;
//                    //System.out.println(xxxx+" ******");
//                    line = line2;
//                } else {
//                    output1 += line + "\n";
//                    for (int i = 0; i < stringPlusWithChange.size(); i++) {
//                        String o = restOfPlusString.get(i);
//                        o += line + "\n";
//                        restOfPlusString.remove(i);
//                        restOfPlusString.add(i, o);
//                    }
//                    //found = false;
//                }
//                if (line.contains("++")) {
//                    for (int i = 0; i < stringPlusWithChange.size(); i++) {
//                        String o = restOfPlusString.get(i);
//                        o += line + "\n";
//                        restOfPlusString.remove(i);
//                        restOfPlusString.add(i, o);
//                    }
//
//                    String line2 = line.replace("++", "--");
//                    //System.out.println(line);
//                    stringPlusWithChange.add(output1 + line2 + "\n");
//                    restOfPlusString.add("");
//                    originalFiles1.add(file);
//
//                    output1 += line + "\n";
//                    //pw.println(line + "\n");
//
//                    MutantsGenerator.counter++;
//                    //System.out.println(xxxx+" ******");
//                } else {
//                    output1 += line + "\n";
//                    for (int i = 0; i < stringPlusWithChange.size(); i++) {
//                        String o = restOfPlusString.get(i);
//                        o += line + "\n";
//                        restOfPlusString.remove(i);
//                        restOfPlusString.add(i, o);
//                    }
                //pw.println(line + "\n");
                ///////////////////////////////////
                
                if (!line.contains("--")) {
                    output2 += line + "\n";
                    for (int i = 0; i < stringMinusWithChange.size(); i++) {
                        String o = restOfMinusString.get(i);
                        o += line + "\n";
                        restOfMinusString.remove(i);
                        restOfMinusString.add(i, o);
                    }
                } else {
                    int number = StringUtils.countMatches(line, "--");
                    for (int i = 0; i < stringMinusWithChange.size(); i++) {
                        String o = restOfMinusString.get(i);
                        o += line + "\n";
                        restOfMinusString.remove(i);
                        restOfMinusString.add(i, o);
                    }

                    StringTokenizer st = new StringTokenizer(line, "--");
                    String arr[] = new String[number];
                    for (int i = 0; i < number; i++) {
                        arr[i] = "";
                    }
                    int counter = 0;
                    while (st.hasMoreTokens()) {
                        String partLine = st.nextToken();
                        for (int i = 0; i < number; i++) {
                            if (counter == i) {
                                arr[i] += partLine + "++";

                            } else {
                                arr[i] += partLine + "--";
                            }

                        }
                        counter++;
                    }
                    for (int i = 0; i < number; i++) {
                        arr[i] = arr[i].substring(0, arr[i].length() - 2);
                        System.out.println(arr[i] + " /////////////////////");
                    }

                    for (int i = 0; i < number; i++) {
                        stringMinusWithChange.add(output2 + arr[i] + "\n");
                        restOfMinusString.add("");
                        originalFiles2.add(file);
                    }
                    output2 += line + "\n";
                }
                
                
                /////
//                if (line.contains("--")) {
//                    for (int i = 0; i < stringMinusWithChange.size(); i++) {
//                        String o = restOfMinusString.get(i);
//                        o += line + "\n";
//                        restOfMinusString.remove(i);
//                        restOfMinusString.add(i, o);
//                    }
//
//                    String line2 = line.replace("--", "++");
//                    //System.out.println(line);
//                    stringMinusWithChange.add(output2 + line2 + "\n");
//                    restOfMinusString.add("");
//                    originalFiles2.add(file);
//
//                    output2 += line + "\n";
//                    //pw.println(line + "\n");
//
//                    MutantsGenerator.counter++;
//                    //System.out.println(xxxx+" ******");
//                } else {
//                    output2 += line + "\n";
//                    for (int i = 0; i < stringMinusWithChange.size(); i++) {
//                        String o = restOfMinusString.get(i);
//                        o += line + "\n";
//                        restOfMinusString.remove(i);
//                        restOfMinusString.add(i, o);
//                    }
//
//                }

            }
            sc.close();
            for (int i = 0; i < stringPlusWithChange.size(); i++) {
                String str1 = stringPlusWithChange.get(i) + restOfPlusString.get(i);
                stringPlusWithChange.remove(i);
                stringPlusWithChange.add(i, str1);

            }
//            System.out.println("*/*/*/*/*/*/");
//            for (int i = 0; i < stringPlusWithChange.size(); i++) {
//                System.out.println(stringPlusWithChange.get(i));
//            }
//            
//            
            for (int i = 0; i < stringMinusWithChange.size(); i++) {
                String str1 = stringMinusWithChange.get(i) + restOfMinusString.get(i);
                stringMinusWithChange.remove(i);
                stringMinusWithChange.add(i, str1);

            }
//            System.out.println("-+-+-+-+-+-+");
//            for (int i = 0; i < stringMinusWithChange.size(); i++) {
//                System.out.println(stringMinusWithChange.get(i));
//            }

            for (int i = 0; i < stringMinusWithChange.size(); i++) {
                stringPlusWithChange.add(stringMinusWithChange.get(i));
            }

            for (int i = 0; i < originalFiles2.size(); i++) {
                originalFiles1.add(originalFiles2.get(i));
            }
            if (stringPlusWithChange.size() != 0) {
                GenerateMutantFiles gm = new GenerateMutantFiles();
                //gm.generateFiles(stringPlusWithChange, originalFiles1, "Increment/Decrement Operator");
                //System.out.println("Number of Mutants: " + MutantsGenerator.counter);
            }

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }
}
