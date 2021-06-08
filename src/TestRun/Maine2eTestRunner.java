/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestRun;

import Reports.AnalyzeResults;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Home
 */
public class Maine2eTestRunner {
    
    public void runTest(String projectPath){
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
          //  ar2.checkoutputColor2(output, fileName);
            // checkoutputColor(output);
            //checkString(output);
            //System.out.println(output+" \n"+"  Test No. "+(totalTest+1)+" Mutant No.: "+counter+"*******************");
            //IonicMuTesting.iterate();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
}
