/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ParsingFiles;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Home
 */
public class GenerateTestFiles {
    
    public void generateFiles2(ArrayList<File> testFiles, String projectPath){
        //int numberOfThreads = 4;
        projectPath += "//MuHubAppsTestFiles";
        File file = new File(projectPath+"//Original");
        File file2 = new File(projectPath+"//empty");
        File file3 = new File(projectPath+"//testFiles");
        file.mkdirs();
        file2.mkdirs();
        file3.mkdirs();
        ArrayList<FileWriter> fw = new ArrayList<FileWriter>();
        ArrayList<PrintWriter> pw = new ArrayList<PrintWriter>();
        ArrayList<String> str = new ArrayList<String>();

        
        for (int i = 0; i < testFiles.size(); i++) {
            try {
                FileUtils.copyFileToDirectory(testFiles.get(i), file);
                FileWriter fakeFile = new FileWriter(file2.getAbsoluteFile()+"//"+testFiles.get(i).getName());
            } catch (IOException ex) {
                //Logger.getLogger(GenerateTestFiles.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    
    }
    
}
