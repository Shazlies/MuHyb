/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ParsingFiles;

import TestRun.TestRun;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Home
 */
public class MutantsGenerationDeprecated {
    
    
    public void generateMutant2(String mutantOriginalFilePath, String mutantFilePath,String fileName, String pathToPlant, String mutantDirectoryRoot){
//        System.out.println(mutantOriginalFilePath + " Original");
//        System.out.println(mutantFilePath + " Mutant");
//        System.out.println(fileName+" fileName");
//        System.out.println(pathToPlant + " Path");
        
        File mutantOriginalFile = new File(mutantOriginalFilePath+"\\"+fileName);
        File mutantFile = new File(mutantFilePath+"\\"+fileName);
        File pathFile = new File(pathToPlant);
        
        //set mutant file to the project folder
        beforeTest(mutantFile, pathFile);
        
        //run test for every file
        runTest(mutantDirectoryRoot);
        
        //set original file again to the project folder
//        System.out.println("FINISH!!!!");
        afterTest(mutantOriginalFile, pathFile);
        
        
    }
    
    public void generateMutant(String copyFromDirectory, String copyToDirectory, File fileBeforeEdit, File fileAfterEdit, String replaceFilePath) throws IOException {

        File originalCopy = new File(copyFromDirectory);
        File mutantFolder = new File(copyToDirectory);
        File orginalFile = new File(copyToDirectory + "\\orginal");
        File mutantFile = new File(copyToDirectory + "\\mutant");
        System.out.println(originalCopy+" 545454");
        //String xx = fileBeforeEdit.getAbsolutePath().replace(NewMutantFilePath, "");
        //System.out.println(xx+" ,,,");
        //System.out.println(copyFromDirectory+xx);
        //System.out.println(replaceFilePath);
//        System.out.println(copyFromDirectory+ " ,,");
        //String xx = fileBeforeEdit.getAbsolutePath().replace("\\.Mutant", "");
//        System.out.println(xx.replace("E:\\ionic\\ionic-unit-testing-example-master\\ionic-unit-testing-example-master\\", "")+ " ..");
        //String xxx =originalCopy+copyToDirectory.replace(copyFromDirectory, "");
        //System.out.println(replaceFilePath+ ".......");
        //File pathFile = new File();
        //File f3 = new File(NewMutantFilePath);
//        System.out.println(fileBeforeEdit.getAbsolutePath()+" */*/*/*/*/");
//        System.out.println("originalCopy" + originalCopy.getAbsolutePath());
//        System.out.println("mutantFolder" + mutantFolder.getAbsolutePath());
//        System.out.println("orginalFile" + orginalFile.getAbsolutePath());
//        System.out.println("mutantFile" + mutantFile.getAbsolutePath());
//        System.out.println("====");
//        System.out.println(copyToDirectory + "\\path.txt");
//        System.out.println(copyToDirectory + "\\orginal\\" + fileBeforeEdit.getName());
//        System.out.println(copyToDirectory + "\\mutant\\" + fileAfterEdit.getName());
        try {
            FileUtils.copyFileToDirectory(fileBeforeEdit, orginalFile);
            FileUtils.copyFileToDirectory(fileAfterEdit, mutantFile);
            FileWriter fw = new FileWriter(copyToDirectory + "\\path.txt");
            PrintWriter pw = new PrintWriter(fw);
            pw.println(copyToDirectory + "\\orginal\\" + fileBeforeEdit.getName());
            pw.println(copyToDirectory + "\\mutant\\" + fileAfterEdit.getName());
            pw.close();

        } catch (IOException ex) {
            System.out.println(ex);
        }
        
        File replaceFilePaths = new File(replaceFilePath);
        File mutantFileAfterCopy = new File(copyToDirectory + "\\mutant\\" + fileAfterEdit.getName());
        System.out.println("Before:");
        beforeTest(mutantFileAfterCopy, replaceFilePaths);
        runTest(copyFromDirectory);
        File originalAfterCopy = new File(copyToDirectory + "\\orginal\\" + fileAfterEdit.getName());
        System.out.println("After:");
        afterTest(originalAfterCopy,replaceFilePaths);
        
//        try {
//            FileUtils.copyFileToDirectory(fileAfterEdit, f3);
//        } catch (IOException ex) {
//            System.out.println("hehehehe");
//        }
//        TestRun t = new TestRun();
//        System.out.println(NewMutantFilePath);
//        t.runTest(NewMutantFilePath);
    }
    
    public void beforeTest(File mutantFile, File path){
//        System.out.println(mutantFile.getAbsolutePath());
//        System.out.println(path.getAbsolutePath());
        try {
            FileUtils.copyFileToDirectory(mutantFile, path);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public void runTest(String path){
        TestRun t = new TestRun();
//        System.out.println(NewMutantFilePath);
       t.runTest(path);
    }
    
    public void afterTest(File originalFile, File path){
//        System.out.println(originalFile.getAbsolutePath());
//        System.out.println(path.getAbsolutePath());
        try {
            FileUtils.copyFileToDirectory(originalFile, path);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
}
