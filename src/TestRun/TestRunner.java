/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestRun;

import Reports.AnalyzeResults;
import UI.IonicMuTesting;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Home
 */
public class TestRunner implements Runnable {

//    String firstMutantFile;
//    String secondMutantFile;
//    String thirdMutantFile;
//    String fourthMutantFile;
   // ArrayList<String> mutantFiles = new ArrayList<String>();
    ArrayList<File> testFiles;
    ArrayList<File> OriginalTest;
    int counter;
    static int totalTest = 0;
    String directoryToTest;
    String projectPath;

    ArrayList<String> testFilesPathes;
    String mutantFile;
    public TestRunner(String projectPath, String MutantPath, int counter, ArrayList<File> testFiles, ArrayList<String> testFilesPathes, int threadNumbers) {

        mutantFile = MutantPath + "\\Mutant" + counter + ".txt";
//        firstMutantFile = MutantPath + "\\Mutant0.txt";
//        secondMutantFile = MutantPath + "\\Mutant1.txt";
//        thirdMutantFile = MutantPath + "\\Mutant2.txt";
//        fourthMutantFile = MutantPath + "\\Mutant3.txt";
        directoryToTest = MutantPath + counter;
        this.projectPath = projectPath;
        this.testFiles = testFiles;
        this.testFilesPathes = testFilesPathes;
        System.out.println(directoryToTest + " ********************");
        totalTest++;
        System.out.println("totalTest: " + totalTest);
        this.counter = counter;
    }

    @Override
    public void run() {
        //System.out.println(counter+"  mm");
        runTestCasesAgainstMutants(directoryToTest, counter, testFiles);
    }

    public void runTestCasesAgainstMutants(String mutantPathtoTest, int number, ArrayList<File> testFiles) {
        //System.out.println("Test No. "+number+" is Running..");

        try {

            File file = new File(mutantFile);
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                //System.out.println("  Test No. "+(totalTest+1)+" Mutant No.: "+counter);
                String originalPath = sc.nextLine();
                String mutantPath = sc.nextLine();
                String directoryToPath = sc.nextLine();
                String fileName = sc.nextLine();
                String mutantNumber = sc.nextLine();
                String lineNumber = sc.nextLine();
                String operator = sc.nextLine();
                String originalLine = sc.nextLine();
                String mutantLine = sc.nextLine();
                beforeTest(mutantPath, directoryToPath, fileName, testFiles, ".Mutant" + number);
                runTest(mutantPathtoTest, fileName, lineNumber, operator, originalLine, mutantLine);
                afterTest(originalPath, directoryToPath, fileName, testFiles, mutantPathtoTest, ".Mutant" + number);
                // totalTest++;
            }
            sc.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }

//        if (number == 0) {
//            try {
//
//                File file = new File(firstMutantFile);
//                Scanner sc = new Scanner(file);
//                while (sc.hasNext()) {
//                    //System.out.println("  Test No. "+(totalTest+1)+" Mutant No.: "+counter);
//                    String originalPath = sc.nextLine();
//                    String mutantPath = sc.nextLine();
//                    String directoryToPath = sc.nextLine();
//                    String fileName = sc.nextLine();
//                    String mutantNumber = sc.nextLine();
//                    String lineNumber = sc.nextLine();
//                    String operator = sc.nextLine();
//                    String originalLine = sc.nextLine();
//                    String mutantLine = sc.nextLine();
//                    beforeTest(mutantPath, directoryToPath, fileName, testFiles, ".Mutant0");
//                    runTest(mutantPathtoTest, fileName, lineNumber, operator, originalLine, mutantLine);
//                    afterTest(originalPath, directoryToPath, fileName, testFiles, mutantPathtoTest, ".Mutant0");
//                    // totalTest++;
//                }
//                sc.close();
//            } catch (FileNotFoundException ex) {
//                System.out.println(ex);
//            }
//        } else if (number == 1) {
//            try {
//                File file = new File(secondMutantFile);
//                Scanner sc = new Scanner(file);
//                while (sc.hasNext()) {
//                    // System.out.println("  Test No. "+(totalTest+1)+" Mutant No.: "+counter);
//                    String originalPath = sc.nextLine();
//                    String mutantPath = sc.nextLine();
//                    String directoryToPath = sc.nextLine();
//                    String fileName = sc.nextLine();
//                    String mutantNumber = sc.nextLine();
//                    String lineNumber = sc.nextLine();
//                    String operator = sc.nextLine();
//                    String originalLine = sc.nextLine();
//                    String mutantLine = sc.nextLine();
//                    beforeTest(mutantPath, directoryToPath, fileName, testFiles, ".Mutant1");
//                    runTest(mutantPathtoTest, fileName, lineNumber, operator, originalLine, mutantLine);
//                    afterTest(originalPath, directoryToPath, fileName, testFiles, mutantPathtoTest, ".Mutant1");
//                    //totalTest++;
//                    //System.out.println("  Test No. "+totalTest+" Mutant No.: "+counter);
//                }
//                sc.close();
//            } catch (FileNotFoundException ex) {
//                System.out.println(ex);
//            }
//        } else if (number == 2) {
//            try {
//                File file = new File(thirdMutantFile);
//                Scanner sc = new Scanner(file);
//                while (sc.hasNext()) {
//                    //System.out.println("  Test No. "+(totalTest+1)+" Mutant No.: "+counter);
//                    String originalPath = sc.nextLine();
//                    String mutantPath = sc.nextLine();
//                    String directoryToPath = sc.nextLine();
//                    String fileName = sc.nextLine();
//                    String mutantNumber = sc.nextLine();
//                    String lineNumber = sc.nextLine();
//                    String operator = sc.nextLine();
//                    String originalLine = sc.nextLine();
//                    String mutantLine = sc.nextLine();
//                    beforeTest(mutantPath, directoryToPath, fileName, testFiles, ".Mutant2");
//                    runTest(mutantPathtoTest, fileName, lineNumber, operator, originalLine, mutantLine);
//                    afterTest(originalPath, directoryToPath, fileName, testFiles, mutantPathtoTest, ".Mutant2");
//                    //totalTest++;
//                    //System.out.println("  Test No. "+totalTest+" Mutant No.: "+counter);
//                }
//                sc.close();
//            } catch (FileNotFoundException ex) {
//                System.out.println(ex);
//            }
//        } else if (number == 3) {
//            try {
//                File file = new File(fourthMutantFile);
//                Scanner sc = new Scanner(file);
//                while (sc.hasNext()) {
//
//                    String originalPath = sc.nextLine();
//                    String mutantPath = sc.nextLine();
//                    String directoryToPath = sc.nextLine();
//                    String fileName = sc.nextLine();
//                    String mutantNumber = sc.nextLine();
//                    String lineNumber = sc.nextLine();
//                    String operator = sc.nextLine();
//                    String originalLine = sc.nextLine();
//                    String mutantLine = sc.nextLine();
//                    beforeTest(mutantPath, directoryToPath, fileName, testFiles, ".Mutant3");
//                    runTest(mutantPathtoTest, fileName, lineNumber, operator, originalLine, mutantLine);
//                    afterTest(originalPath, directoryToPath, fileName, testFiles, mutantPathtoTest, ".Mutant3");
//                    //totalTest++;
//                    //System.out.println("  Test No. "+totalTest+" Mutant No.: "+counter);
//                }
//                sc.close();
//            } catch (FileNotFoundException ex) {
//                System.out.println(ex);
//            }
//        }
    }

    public void beforeTest(String mutantPath, String directoryToCopyIn, String fileName, ArrayList<File> testFiles, String mutantCounter) {
        try {
            //System.out.println("Copyying.. " + mutantPath+" "+counter);
            copyTestFiles("empty", fileName, testFiles, directoryToCopyIn, mutantCounter);

//            for (int i = 0; i < testFiles.size(); i++) {
//                String x = testFiles.get(i).getName();
//                int z = fileName.indexOf(".");
//
//                String y = fileName.replace("ts", "spec.ts");
//                if (x.equals(y)) {
//
//                } else {
//                    File beforeTestFile = new File(projectPath + "\\MuHubAppsTestFiles\\empty\\" + testFiles.get(i).getName());
//                    //System.out.println(counter+" ////"+counter);
//                    //System.out.println("before zzzz: "+testFiles.get(i).getAbsolutePath());
//                    String zzzz = (testFiles.get(i).getAbsoluteFile() + "").replace("Mutant0", "Mutant" + counter);
//                    zzzz = zzzz.replace(testFiles.get(i).getName(), "");
//                    String xx = zzzz.substring(0, zzzz.length() - 1);
//                    //System.out.println("zzzz: "+xx+"  "+counter);
//                    FileUtils.copyFileToDirectory(beforeTestFile, new File(xx));
//                    //modfiy all test fiels from it to xit or from descripe to xdescripe
//                    //System.out.println(x+ " 0.0.0.0 "+ counter);
////                    if(testFiles.get(i).renameTo(new File(afterChange))){
////                        System.out.println("DONE!!!!!!!!!!");
////                    }
////                    else{
////                        System.out.println("not DONE!!!");
////                    }
//
//                }
//            }
            File f1 = new File(mutantPath);
            String x = directoryToCopyIn.replace(fileName, "");

            String xx = x.substring(0, x.length() - 1);
            //System.out.println(xx+" //////////");

            File f2 = new File(xx);
            //System.out.println(f2.getAbsoluteFile()+" .....");
            //System.out.println(f2.getName());
            FileUtils.copyFileToDirectory(f1, f2);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void copyTestFiles(String emptyOrOriginal, String fileName, ArrayList<File> testFiles, String directoryToCopyIn, String mutantCounter) {
        for (int i = 0; i < testFiles.size(); i++) {
            String x = testFiles.get(i).getName();
            int z = fileName.indexOf(".");

            String y = fileName.replace("ts", "spec.ts");
            if (x.equals(y)) {

            } else {
                File beforeTestFile = new File(projectPath + "\\MuHubAppsTestFiles\\" + emptyOrOriginal + "\\" + testFiles.get(i).getName());
                //System.out.println(counter+" ////"+counter);
                //System.out.println("before zzzz: "+testFiles.get(i).getAbsolutePath());
//                String zz = directoryToCopyIn.replace(fileName, "");
//                zz =zz.substring(0, zz.length()-1);
//                
//                String zzzz = (testFiles.get(i).getAbsoluteFile() + "").replace("Mutant0", "Mutant" + counter);
//                zzzz = zzzz.replace(testFiles.get(i).getName(), "");
//                String xx = zzzz.substring(0, zzzz.length() - 1);
                try {
                    System.out.println(beforeTestFile + " " + testFilesPathes.get(i).replace("src", mutantCounter + "\\src") + " /*/*/*/*/*");
                    FileUtils.copyFileToDirectory(beforeTestFile, new File(testFilesPathes.get(i).replace("src", mutantCounter + "\\src")));

                } catch (IOException ex) {
                    Logger.getLogger(TestRunner.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }

    public void runTest(String projectPath, String fileName, String lineNumber, String operator, String originalLine, String mutantLine) {
        //System.out.println("Testing: "+projectPath
        System.out.println("Welcome from " + Thread.currentThread().getName());
        String output = "";

        //final String dosCommand = "cmd /c \"pushd \"" + projectPath + " && ng test --browsers ChromeHeadless --watch=false";
        try {

            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "pushd \"" + projectPath + "\"" + " && ng test --browsers ChromeHeadless --watch=false");
            builder.redirectErrorStream(true);
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                if (line == null) {
                    break;
                }
                output += line + "\n";
            }
            System.out.println("=====================");
            System.out.println(output);
            System.out.println("=======================");
            //Process process = Runtime.getRuntime().exec(dosCommand);
            //InputStream in = process.getInputStream();
//               BufferedReader stdInput = new BufferedReader(new 
//                 InputStreamReader(process.getInputStream()));
//             String s = null;
//               while ((s = stdInput.readLine()) != null) {
//                System.out.println(s);
//            }
            //System.out.println(in.read());
//            while ((ch = in.read()) != -1) {
//                output += (char) ch;
//            }// we need to kill the process
            //System.out.println(output);
            //System.out.println(output);
            AnalyzeResults ar = new AnalyzeResults();
            ar.checkoutputColor(output, fileName, lineNumber, operator, originalLine, mutantLine);
            // checkoutputColor(output);
            //checkString(output);
            //System.out.println(output+" \n"+"  Test No. "+(totalTest+1)+" Mutant No.: "+counter+"*******************");
            IonicMuTesting.iterate();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void afterTest(String originalPath, String directoryToCopyIn, String fileName, ArrayList<File> testFiles, String mutantPathtoTest, String mutantCounter) {
        try {

            copyTestFiles("Original", fileName, testFiles, directoryToCopyIn, mutantCounter);

//            for (int i = 0; i < testFiles.size(); i++) {
//                String x = testFiles.get(i).getName();
//                int z = fileName.indexOf(".");
//
//                String y = fileName.replace("ts", "spec.ts");
//                if (x.equals(y)) {
//
//                } else {
//                    File beforeTestFile = new File(projectPath + "\\MuHubAppsTestFiles\\Original\\" + testFiles.get(i).getName());
//                    //System.out.println(counter+" ////"+counter);
//                    //System.out.println("before zzzz: "+testFiles.get(i).getAbsolutePath());
//                    String zzzz = (testFiles.get(i).getAbsoluteFile() + "").replace("Mutant0", "Mutant" + counter);
//                    zzzz = zzzz.replace(testFiles.get(i).getName(), "");
//                    String xx = zzzz.substring(0, zzzz.length() - 1);
//                    FileUtils.copyFileToDirectory(beforeTestFile, new File(xx));
//                    //modfiy all test fiels from it to xit or from descripe to xdescripe
//                    //System.out.println(x+ " 0.0.0.0 "+ counter);
////                    if(testFiles.get(i).renameTo(new File(afterChange))){
////                        System.out.println("DONE!!!!!!!!!!");
////                    }
////                    else{
////                        System.out.println("not DONE!!!");
////                    }
//
//                }
//            }
            File f1 = new File(originalPath);
            String x = directoryToCopyIn.replace(fileName, "");

            String xx = x.substring(0, x.length() - 1);
            //System.out.println(xx+" //////////");
            File f2 = new File(xx);
            //System.out.println(f2.getName());
            FileUtils.copyFileToDirectory(f1, f2);
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

}
