/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ParsingFiles;

import UI.IonicMuTesting;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Home
 */
public class GenerateMutantFiles {

    static int wholeMutants = 0;
    static int mutantNumber = 0;

    public void generateFiles(ArrayList<String> mutants, ArrayList<File> files, ArrayList<MutantInformation> arr, int threadNumbers) {
        MutantsGenerator.setCounter(mutants.size());
        //System.out.println("============");
        //System.out.println(mutants.size());
        //for (int i = 0; i < mutants.size(); i++) {
        //System.out.println(mutants.get(i));
        //}
        // System.out.println(files.size());
//        for (int i = 0; i < files.size(); i++) {
//            System.out.println(files.get(i).getAbsolutePath());
//        }
        for (int i = 0; i < mutants.size(); i++) {
            try {
                String filePath = files.get(i).getAbsolutePath();
                int x = filePath.indexOf("\\.Mutant0");
                String projectPath = filePath.substring(0, x);
                String tempPath = filePath.substring(0, x);
                projectPath += "\\.Mutant" + "\\Mutant" + mutantNumber;
                File originalFile = new File(projectPath + "\\Orignal");
                File mutantFile = new File(projectPath + "\\Mutant");
                File toCopyFrom = new File(tempPath + "\\" + files.get(i).getName());
                FileUtils.copyFileToDirectory(files.get(i), originalFile);
                PrintWriter pw = new PrintWriter(toCopyFrom);
                pw.print(mutants.get(i));
                pw.close();
                FileUtils.copyFileToDirectory(toCopyFrom, mutantFile);
                toCopyFrom.delete();

                //for the first time Only Generating 4 text files to save Testing proccess pathes
                if (mutantNumber == 0) {
                    try {

                        String projectTempPath = filePath.substring(0, x);
                        for (int j = 0; j < threadNumbers; j++) {
                            File mutant1 = new File(projectTempPath + "\\.Mutant\\Mutant"+j+".txt");
                            pw = new PrintWriter(mutant1);
                            pw.close();
                        }

//                        mutant1 = new File(projectTempPath + "\\.Mutant\\Mutant1.txt");
//                        pw = new PrintWriter(mutant1);
//                        pw.close();
//                        mutant1 = new File(projectTempPath + "\\.Mutant\\Mutant2.txt");
//                        pw = new PrintWriter(mutant1);
//                        pw.close();
//                        mutant1 = new File(projectTempPath + "\\.Mutant\\Mutant3.txt");
//                        pw = new PrintWriter(mutant1);
//                        pw.close();
                    } catch (FileNotFoundException ex) {

                        System.out.println(ex + " here");
                    }
                }

                int mutantTurn = mutantNumber % threadNumbers;
                
                String finalOrigialPath = projectPath + "\\Orignal\\" + files.get(i).getName();
                String finalMutantPath = projectPath + "\\Mutant\\" + files.get(i).getName();;
                String directoryToPlantIn = files.get(i).getAbsolutePath();
                String projectTempPath = filePath.substring(0, x);
                FileWriter fw = null;
                fw = new FileWriter(projectTempPath + "\\.Mutant\\Mutant"+mutantTurn+".txt", true);
                directoryToPlantIn = directoryToPlantIn.replace(".Mutant0", ".Mutant"+mutantTurn);
//                
//                switch (mutantTurn) {
//                    case 0:
//                        fw = new FileWriter(projectTempPath + "\\.Mutant\\Mutant0.txt", true);
//                        break;
//                    case 1:
//                        fw = new FileWriter(projectTempPath + "\\.Mutant\\Mutant1.txt", true);
//                        directoryToPlantIn = directoryToPlantIn.replace(".Mutant0", ".Mutant1");
//                        break;
//                    case 2:
//                        fw = new FileWriter(projectTempPath + "\\.Mutant\\Mutant2.txt", true);
//
//                        directoryToPlantIn = directoryToPlantIn.replace(".Mutant0", ".Mutant2");
//                        break;
//                    case 3:
//                        fw = new FileWriter(projectTempPath + "\\.Mutant\\Mutant3.txt", true);
//                        directoryToPlantIn = directoryToPlantIn.replace(".Mutant0", ".Mutant3");
//                        break;
//
//                }

                pw = new PrintWriter(fw);
                pw.println(finalOrigialPath);
                pw.println(finalMutantPath);
                pw.println(directoryToPlantIn);
                pw.println(files.get(i).getName());
                pw.println(mutantNumber);
                pw.println(arr.get(i).getLineCode());
                pw.println(arr.get(i).getOperator());
                pw.println(arr.get(i).getOriginalLine());
                pw.println(arr.get(i).getMutantLine());
                pw.close();
                fw.close();

                mutantNumber++;

            } catch (IOException ex) {
                System.out.println(ex);
            }

        }

    }
}
