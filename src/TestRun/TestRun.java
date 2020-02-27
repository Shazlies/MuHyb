/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestRun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

/**
 *
 * @author Home
 */
public class TestRun  {
//    public static void main(String[] args) {
//        String path = "E:\\ionic\\ionic-unit-testing-example-master\\ionic-unit-testing-example-master";
//        runTest(path);
//    }
    
//    String projectPath;
//    Thread t;
//    static int counter =0;
//    public TestRun(String projectPath){
//        counter++;
//        this.projectPath = projectPath;
//        t = new Thread(this, "");
//        t.setPriority(Thread.MAX_PRIORITY);
//        t.start();
//    }
//    
//    public void run() {
//        System.out.println("Test No. "+counter);
//        runTest(projectPath);
//    }
    
    

    String output = "";
    public void runTest(String projectPath){
//        System.out.println(projectPath);
        System.out.println("here");
        
        final String dosCommand = "cmd /c \"pushd \""+projectPath+" && ng test --browsers ChromeHeadless --watch=false";
        try{
             Process process = Runtime.getRuntime().exec(dosCommand );
             InputStream in = process.getInputStream();
            int ch;
            //System.out.println(in.read());
            while((ch = in.read()) !=-1){
                output+=(char)ch;
            }// we need to kill the process
            checkString(output);
        }catch(IOException e){
            System.out.println(e);
        }
    }
    
    void checkString (String input){
        try {
            BufferedReader buf = new BufferedReader(new StringReader(input));
            String line = null;
            while((line = buf.readLine())!=null){
                //System.out.println(line);
                int len = "Success".length();
                int totalLen = line.length() - len;
                if(line.regionMatches(totalLen, "SUCCESS", 0, len)){
                    System.out.println("Success: "+line);
                }
                len = "FAILED".length();
                totalLen = line.length() - len;
                if(line.regionMatches(totalLen, "FAILED", 0, len)){
                    System.out.println("Failed: "+line);
                }
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    
    
    
    
}
