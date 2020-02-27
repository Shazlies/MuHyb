/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestRun;

import Reports.AnalyzeResults;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Home
 */
public class MainTestRunner {

    ArrayList<File> testFiles;
    

    public MainTestRunner(String path, ArrayList<File> testFiles) {
        this.testFiles = testFiles;
    }

    public void beforeRunTestFileByFile(String path) {
        int counter = 0;
        String fileName = "";
        for (int i = 0; i < testFiles.size(); i++) {
            //System.out.println("hhhh");
            for (int j = 0; j < testFiles.size(); j++) {
                //System.out.println("hehe");
                if (counter == j) {
                    fileName = testFiles.get(i).getName();
                } else {
                    File beforeTestFile = new File(path + "\\MuHubAppsTestFiles\\empty\\" + testFiles.get(j).getName());
                    String x = testFiles.get(j).getAbsolutePath().replace(testFiles.get(j).getName(), "");
                    String xx = x.substring(0, x.length() - 1);
                    try {
                        FileUtils.copyFileToDirectory(beforeTestFile, new File(xx));
                    } catch (IOException ex) {
                        Logger.getLogger(TestRunner.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            counter++;
            runTest(path, fileName);

            for (int j = 0; j < testFiles.size(); j++) {
              //  System.out.println("hohoho");
                File beforeTestFile = new File(path + "\\MuHubAppsTestFiles\\original\\" + testFiles.get(j).getName());
                String x = testFiles.get(j).getAbsolutePath().replace(testFiles.get(j).getName(), "");
                String xx = x.substring(0, x.length() - 1);
                try {
                    FileUtils.copyFileToDirectory(beforeTestFile, new File(xx));

                } catch (IOException ex) {
                    Logger.getLogger(TestRunner.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            

        }

    }

    public void runTest(String projectPath, String fileName) {

        //System.out.println("Testing: "+projectPath
        String output = "";

        //final String dosCommand = "cmd /c \"pushd \"" + projectPath + " && ng test --browsers ChromeHeadless --watch=false";
        try {
            
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "pushd \""+projectPath+"\""+" && ng test --browsers ChromeHeadless --watch=false");
            builder.redirectErrorStream(true);
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while(true){
                line = r.readLine();
                if(line==null){
                    break;
                }
                output += line+"\n";
            }
            
            System.out.println(output);
            
            
            
            //Process process = Runtime.getRuntime().exec(dosCommand);
            //InputStream in = process.getInputStream();

//               BufferedReader stdInput = new BufferedReader(new 
//                 InputStreamReader(process.getInputStream()));
//             String s = null;
//               while ((s = stdInput.readLine()) != null) {
//                System.out.println(s);
//            }
            //System.out.println(in.read());
            //while ((ch = in.read()) != -1) {
              //  output += (char) ch;
            //}// we need to kill the process

            AnalyzeResults ar2 = new AnalyzeResults();
            ar2.checkoutputColor2(output, fileName);
            // checkoutputColor(output);
            //checkString(output);
            //System.out.println(output+" \n"+"  Test No. "+(totalTest+1)+" Mutant No.: "+counter+"*******************");
            //IonicMuTesting.iterate();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}