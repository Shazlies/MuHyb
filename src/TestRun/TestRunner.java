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
    boolean unitTest;
    boolean e2eTest;
    boolean bothTest;

    public TestRunner(String projectPath, String MutantPath, int counter, ArrayList<File> testFiles, ArrayList<String> testFilesPathes, int threadNumbers, boolean uniTest, boolean e2eTest, boolean bothTest) {

        mutantFile = MutantPath + "\\Mutant" + counter + ".txt";
        this.unitTest = uniTest;
        this.e2eTest = e2eTest;
        this.bothTest = bothTest;
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
        try {
            //System.out.println(counter+"  mm");
            runTestCasesAgainstMutants2(directoryToTest, counter, testFiles);
        } catch (InterruptedException ex) {
            Logger.getLogger(TestRunner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void runTestCasesAgainstMutants2(String mutantPathtoTest, int number, ArrayList<File> testFiles) throws InterruptedException {

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
                String pathToTest = projectPath + "\\_Mutant" + number;
                String fileToTest = directoryToPath.replace(pathToTest + "\\", "");
                fileToTest = fileToTest.replace(".html", ".spec.ts");
                if (unitTest) {
                    beforeTest3(mutantPath, directoryToPath, fileName, testFiles, "_Mutant" + number);
                    runTest3(pathToTest, fileToTest, fileName, lineNumber, operator, originalLine, mutantLine);
                    afterTest3(originalPath, directoryToPath, fileName, testFiles, mutantPathtoTest, "_Mutant" + number);
                }
                if (e2eTest) {
                    beforeTest2(mutantPath, directoryToPath, fileName, testFiles, "_Mutant" + number, number);
                    Thread.sleep(200);
                    //if (ShallItRun(number)) {
                       // System.out.println("ShallItRun TRUE");
                        rune2eTest(pathToTest, fileName, lineNumber, operator, originalLine, mutantLine, number, originalPath, directoryToPath);
                        Thread.sleep(200);
                   // } else {
                    //    System.out.println("ShallItRun FALSE");
                    //    addAnalyses("", fileName, lineNumber, operator, originalLine, mutantLine, "html");
                   // }
                    afterTest2(originalPath, directoryToPath, fileName);
                    Thread.sleep(200);
                }
                if (bothTest) {
                    if (originalPath.toLowerCase().charAt(originalPath.length() - 1) == 'l') {
                        beforeTest2(mutantPath, directoryToPath, fileName, testFiles, "_Mutant" + number, number);
                        Thread.sleep(200);
                      //  if (ShallItRun(number)) {

                            rune2eTest(pathToTest, fileName, lineNumber, operator, originalLine, mutantLine, number, originalPath, directoryToPath);
                            Thread.sleep(200);
                      //  } else {
                      //      addAnalyses("", fileName, lineNumber, operator, originalLine, mutantLine, "html");
                      //  }
                        afterTest2(originalPath, directoryToPath, fileName);
                        Thread.sleep(200);
                    } else {
                        beforeTest3(mutantPath, directoryToPath, fileName, testFiles, "_Mutant" + number);
                        runTest3(pathToTest, fileToTest, fileName, lineNumber, operator, originalLine, mutantLine);
                        afterTest3(originalPath, directoryToPath, fileName, testFiles, mutantPathtoTest, "_Mutant" + number);

                    }
                }

                // totalTest++;
            }
            sc.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }

    }

    public void beforeTest4(String mutantPath, String directoryToCopyIn, String fileName, ArrayList<File> testFiles, String mutantCounter) {
        try {

            File f1 = new File(mutantPath);
            String x = directoryToCopyIn.replace(fileName, "");

            String xx = x.substring(0, x.length() - 1);
            //System.out.println(xx+" //////////");

            File f2 = new File(xx);

            //Important
            FileUtils.copyFileToDirectory(f1, f2);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void runTest4(String pathToTest, String fileToTest, String fileName, String lineNumber, String operator, String originalLine, String mutantLine) {
        //System.out.println("Testing: "+projectPath
        System.out.println("Welcome from " + Thread.currentThread().getName());
        String output = "";

        //final String dosCommand = "cmd /c \"pushd \"" + projectPath + " && ng test --browsers ChromeHeadless --watch=false";
        try {
//            boolean done = false;
//            while (!done) {
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "pushd \"" + pathToTest + "\"" + " && ng test --browsers ChromeHeadless --watch=false");
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

            AnalyzeResults ar = new AnalyzeResults();

            ar.checkoutputColor(output, fileName, lineNumber, operator, originalLine, mutantLine, "ts");

            IonicMuTesting.iterate();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void afterTest4(String originalPath, String directoryToCopyIn, String fileName, ArrayList<File> testFiles, String mutantPathtoTest, String mutantCounter) {
        try {

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

    public void beforeTest3(String mutantPath, String directoryToCopyIn, String fileName, ArrayList<File> testFiles, String mutantCounter) {
        try {

            File f1 = new File(mutantPath);
            String x = directoryToCopyIn.replace(fileName, "");

            String xx = x.substring(0, x.length() - 1);
            //System.out.println(xx+" //////////");

            File f2 = new File(xx);

            //Important
            FileUtils.copyFileToDirectory(f1, f2);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void runTest3(String pathToTest, String fileToTest, String fileName, String lineNumber, String operator, String originalLine, String mutantLine) {
        //System.out.println("Testing: "+projectPath
        System.out.println("Welcome from " + Thread.currentThread().getName());
        String output = "";

        //final String dosCommand = "cmd /c \"pushd \"" + projectPath + " && ng test --browsers ChromeHeadless --watch=false";
        try {
//            boolean done = false;
//            while (!done) {
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "pushd \"" + pathToTest + "\"" + " && ng test --browsers ChromeHeadless --watch=false --include " + fileToTest);
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

            if (output.contains("Unknown option:")) {
                output = "";
                builder = new ProcessBuilder("cmd.exe", "/c", "pushd \"" + pathToTest + "\"" + " && ng test --browsers ChromeHeadless --watch=false --main " + fileToTest);
                builder.redirectErrorStream(true);
                p = builder.start();
                r = new BufferedReader(new InputStreamReader(p.getInputStream()));
                line = "";
                while (true) {
                    line = r.readLine();
                    if (line == null) {
                        break;
                    }
                    output += line + "\n";
                }

            }
//                System.out.println("=====================");
//                System.out.println(output);
//                System.out.println("=======================");

//                if (!output.contains("Executed 0 of 0 ERROR")) {
//                    done = true;
//                } else {
//                    System.out.println("again Executed 0 of 0 ERROR");
//                    output = "";
//                }
//
//            }
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

            ar.checkoutputColor(output, fileName, lineNumber, operator, originalLine, mutantLine, "ts");
            // checkoutputColor(output);
            //checkString(output);
            //System.out.println(output+" \n"+"  Test No. "+(totalTest+1)+" Mutant No.: "+counter+"*******************");
            IonicMuTesting.iterate();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void afterTest3(String originalPath, String directoryToCopyIn, String fileName, ArrayList<File> testFiles, String mutantPathtoTest, String mutantCounter) {
        try {

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
//            boolean done = false;
//            while (!done) {
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

//                if (!output.contains("Executed 0 of 0 ERROR")) {
//                    done = true;
//                } else {
//                    System.out.println("again Executed 0 of 0 ERROR");
//                    output = "";
//                }
//
//            }
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
            ar.checkoutputColor(output, fileName, lineNumber, operator, originalLine, mutantLine, "ts");
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

    public void beforeTest2(String mutantPath, String directoryToCopyIn, String fileName, ArrayList<File> testFiles, String mutantCounter, int number) {
        //String ret = "Yes";
        try {
            
            File f1 = new File(mutantPath);
            String x = directoryToCopyIn.replace(fileName, "");

            String xx = x.substring(0, x.length() - 1);
            //System.out.println(xx+" //////////");

            File f2 = new File(xx);
            //System.out.println(f2.getAbsoluteFile()+" .....");
            //System.out.println(f2.getName());
            FileUtils.copyFileToDirectory(f1, f2);

//            for (int i = 0; i < RunServers.counters.size(); i++) {
//            if (RunServers.counters.get(i) - 8101 == number) {
//                BufferedReader r = RunServers.readers.get(i);
//                
//                while (true) {
//                    String line = r.readLine();
//                    System.out.println(line);
//                    if(line.contains("Compiled successfully"))
//                        break;
//                        
//                }
//
//            }
//            
//
//        }
            
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
        //return ret;
    }

    private char appendText(final InputStreamReader inputStreamReader) {
        try {
            final char[] buf = new char[256];
            final int read = inputStreamReader.read(buf);
//            System.out.println("asasdasddddddddddddddddddddddddddddddddddddddddddddddddddd");
//            System.out.println(new String(buf));
//            System.out.println(new String(buf).length());
//            System.out.println(read);
//            System.out.println("asasdasddddddddddddddddddddddddddddddddddddddddddddddddddd");
            //int read = inputStreamReader.read(buf, 0 , inputStreamReader);
            
            if (new String(buf).contains("Failed to complile")) {
                return 'f';
            }if(new String(buf).isEmpty()){
                System.out.println("EMPTYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY");
                return 'e';
            }

        } catch (final IOException e) {
            e.printStackTrace();
        }
        return 'x';
    }

    public boolean ShallItRun(int number) {
        boolean ret = true;
        String output = "";
        for (int i = 0; i < RunServers.counters.size(); i++) {
            if (RunServers.counters.get(i) - 8101 == number) {
                InputStreamReader r = RunServers.reads.get(i);
                while (appendText(r)!='f'||appendText(r)!='f') {
                    ;
                }

            }

        }

//                while (true) {
//
//                    try {
//                        String line = r.readLine();
//                        
//                        //System.out.println(line);
//                        //System.out.println(line + "          "+i);
//                        output += line;
//                        System.out.println(line+" "+counter);
//                        if (line.contains("Failed to compile.")) {
//                            System.out.println(line);
//                            System.out.println("hereeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
////                    System.out.println("Mutant"+i);
////                    System.out.println(output);
////                    System.out.println("done");
//                            ret = false;
//                            break;
//                        }
//                        if(counter==30){
//                            ret = true;
//                            break;
//                        }
//                        counter ++;
//                    } catch (IOException ex) {
//                        Logger.getLogger(TestRunner.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//
//                }
//                System.out.println("asdasdasdasddasdasd");
//                System.out.println(output);
//                System.out.println("asdasdasdasddasdasd");
        //}
        //}
        return ret;
    }

    public void rune2eTest(String projectPath, String fileName, String lineNumber, String operator, String originalLine, String mutantLine, int numbers, String originalPath, String directoryToCopyIn) throws InterruptedException {
        //System.out.println("Testing: "+projectPath
        String output = "";
        String x = Thread.currentThread().getName();
        char a = x.charAt(x.length() - 1);
        int number = Integer.parseInt(a + "");

        String y = "4" + number + "00";
        int port = Integer.parseInt(y) + 100;
        //Thread.sleep(number*100);
        System.out.println("Welcome from " + Thread.currentThread().getName() + "PORT:  " + port);
        //final String dosCommand = "cmd /c \"pushd \"" + projectPath + " && ng test --browsers ChromeHeadless --watch=false";
        try {
//            boolean done = false;
//
//            while (!done) {
            System.out.println("i am here");
            System.out.println(projectPath);
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "pushd \"" + projectPath + "\"" + " && protractor e2e/protractor.conf.js");
            builder.redirectErrorStream(true);
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                //System.out.println(line);
//                if(line.contains("A Jasmine spec timed out.")){
//                    System.out.println("YAAAAAAAAAAAAAAAAAAAAAY");
//                    break;
//                }
                if (line == null) {
                    break;
                }
                output += line + "\n";
            }
            System.out.println("=========e2e============" + port);
            System.out.println("*********************************");
            System.out.println(lineNumber);
            System.out.println(operator);
            System.out.println(originalLine);
            System.out.println(mutantLine);
            System.out.println("*********************************");
            System.out.println(output);
            System.out.println("=======================");

            try {
            System.out.println("afterTest2");
            File f1 = new File(originalPath);
            String x1 = directoryToCopyIn.replace(fileName, "");

            String xx = x1.substring(0, x1.length() - 1);
            //System.out.println(xx+" //////////");
            File f2 = new File(xx);
            //System.out.println(f2.getName());
            FileUtils.copyFileToDirectory(f1, f2);
        } catch (Exception ex) {
            System.out.println(ex);
        }
            
            
            AnalyzeResults ar = new AnalyzeResults();
            ar.checkoutputColor4(output, fileName, lineNumber, operator, originalLine, mutantLine, "html");

            // checkoutputColor(output);
            //checkString(output);
            //System.out.println(output+" \n"+"  Test No. "+(totalTest+1)+" Mutant No.: "+counter+"*******************");
            IonicMuTesting.iterate();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public void afterTest2(String originalPath, String directoryToCopyIn, String fileName) {
        

    }
}
