/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ParsingFiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Home
 */
public class IncrementsOperatorDeprecated {

    MutantsGenerationDeprecated muGen = new MutantsGenerationDeprecated();
    static int counter = 0; // counter for Mutant1 Mutant2 ...

    public void mutateOperators(String oldOperator, String newOperator, String appDirectoryRoot, File OriginalFile) throws IOException {
        String appMutantRoot = appDirectoryRoot + "\\.Mutant0"; // App root + "\\.Mutant"
        String filePath = OriginalFile.getAbsolutePath(); // Original file path
        //System.out.println(filePath + " filePath");
        String filePathWithoutFileName = filePath.replace(OriginalFile.getName(), ""); // File path without file name
        filePathWithoutFileName = filePathWithoutFileName.substring(0, filePathWithoutFileName.length() - 1); // remove last letter from the path "/"
        String MutantFiletoPlant = appMutantRoot + filePathWithoutFileName.replace(appDirectoryRoot, ""); // final mutant path to plant in
        //System.out.println(filePathWithoutFileName + " filePathWithoutFileName");
        //System.out.println(MutantFiletoPlant + " MutantFiletoPlant");
        String folderName = "\\Mutant\\Mutant"; // Establish the folder to put mutants

        String output = "";
        boolean foundOperator = false; // flag
        try {
            Scanner sc = new Scanner(OriginalFile);
            while (sc.hasNext()) { // line by line
                String line = sc.nextLine();
                if (line.contains(oldOperator) && !foundOperator) {

                    line = line.replace(oldOperator, newOperator);
                    //System.out.println(line);
                    output += line + "\n";
                    //pw.println(line + "\n");
                    foundOperator = true;
                    counter++;
                    //System.out.println(xxxx+" ******");
                } else {
                    output += line + "\n";
                    //pw.println(line + "\n");
                }

                //System.out.println(x);
            }


            //pw.close();
            //System.out.println(file.getAbsolutePath());
//            System.out.println(foundOperator);
//            File f = new File(file.getName());
System.out.println("here22");
            if (foundOperator) { // only when create mutants
                //create file only when a mutant created and put it in the directory ..\\.Mutant\\Mutant\Mutant1\\filename.ts 
//                System.out.println(foundOperator);
                //the file which has the change in mutant folder
                File mutantFile = new File(appMutantRoot + "\\Mutants\\Mutant" + counter + "\\Mutant\\");
                
                //the original file in mutant folder
                File mutantOriginalFile = new File(appMutantRoot + "\\Mutants\\Mutant" + counter + "\\Original\\");
                try {
                    FileUtils.copyFileToDirectory(OriginalFile, mutantOriginalFile); //copying original file to folder
                    FileUtils.copyFileToDirectory(OriginalFile, mutantFile); // copying original file to folder
                } catch (IOException ex) {
                    System.out.println(ex);
                }
                
                // make changes in original file to be mutant
                FileWriter fw = new FileWriter(appMutantRoot + "\\Mutants\\Mutant" + counter + "\\Mutant\\" + OriginalFile.getName());
                PrintWriter pw = new PrintWriter(fw);
                pw.print(output);
                pw.close();
                fw.close();
                muGen.generateMutant2(mutantOriginalFile.getAbsolutePath(), mutantFile.getAbsolutePath(),OriginalFile.getName(), MutantFiletoPlant, appMutantRoot);
//                
////                
////                System.out.println(appDirectoryRoot+folderName+counter+" ///////");
////                System.out.println(copyFileToPath+" Copy file to ");
                //String replaceFilePath = appDirectoryRoot + replaceFilePathSuffix.replace(file.getName(), "");
                //System.out.println(replaceFilePath + "     6");
//                //System.out.println(replaceFilePath);
//                muGen.generateMutant(appDirectoryRoot, appDirectoryRoot+folderName+counter,file, f, replaceFilePath);
//                
            }
//            f.delete();
            //System.out.println(appDirectoryRoot + "     4");
            //System.out.println(appDirectoryRoot + folderName + counter + "     5");

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }

    }

    public void decrementOperator(File file) {

    }

    public void makeMutant(String path, File file) throws IOException {
        String oldOperator = "++";
        String newOperator = "--";
        // take a copy first

        mutateOperators(oldOperator, newOperator, path, file);

    }

    public void takeOrginalCopy(String appDirectoryRoot) {
        String OrignalCopy = appDirectoryRoot + "\\.Mutant0";
        File f1 = new File(appDirectoryRoot);
        File f2 = new File(OrignalCopy);
//        System.out.println(f1.getAbsolutePath());
//        System.out.println(f2.getAbsolutePath());
//        try {
//            FileUtils.copyDirectory(f1, f2);
//        } catch (IOException ex) {
//            System.out.println("Orignal Copy Failed");
//        }
    }
}
