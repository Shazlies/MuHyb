/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ParsingFiles;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Home
 */
public class MutantsGenerator {

    private static int counter = 0;
    ArrayList<Mutant> mutantsArray = new ArrayList<Mutant>();

    public void createMutantsFirstStep(String path, File file, boolean[] selectedOperators, int threadNumbers) throws IOException, InterruptedException {
        System.out.println("HERE");
        for (int i = 0; i < selectedOperators.length; i++) {
            if (selectedOperators[i]) {
                createMutantsSecondStep(path, file, i, threadNumbers);
            }
        }
    }
    
    public int getCounter(){
        return counter;
    }
    public static void setCounter(int c){
        counter += c;
    }
    public void createMutantsSecondStep(String appDirectoryRoot, File OriginalFile, int number, int threadNumbers) throws InterruptedException {
        String appMutantRoot = appDirectoryRoot + "\\.Mutant0"; // App root + "\\.Mutant"
        String filePath = OriginalFile.getAbsolutePath(); // Original file path
        //System.out.println(filePath + " filePath");
        String filePathWithoutFileName = filePath.replace(OriginalFile.getName(), ""); // File path without file name
        filePathWithoutFileName = filePathWithoutFileName.substring(0, filePathWithoutFileName.length() - 1); // remove last letter from the path "/"
        String MutantFiletoPlant = appMutantRoot + filePathWithoutFileName.replace(appDirectoryRoot, ""); // final mutant path to plant in
        System.out.println(number);
        if (number == 0) {
            BooleanOperator b = new BooleanOperator();
            b.generateMutant(OriginalFile, threadNumbers);
        }
        if (number == 1) {
            EqualityOperator b = new EqualityOperator();
            b.generateMutant(OriginalFile, threadNumbers);
        }
        if (number == 2) {
            LogicalOperators b = new LogicalOperators();
            b.generateMutant(OriginalFile, threadNumbers);
        }

        if (number == 3) {
            IncrementsOperator i = new IncrementsOperator();
            i.generateMutant(OriginalFile, threadNumbers);
        }
        if (number == 4) {
            MathOperators mo = new MathOperators();
            mo.generateMutant(OriginalFile, threadNumbers);
        }
        if (number == 5) {
            RelationalOperator ro = new RelationalOperator();
            ro.generateMutant(OriginalFile, threadNumbers);
        }
        if (number == 6) {
            IfStatementOperator ro = new IfStatementOperator();
            ro.generateMutant(OriginalFile, threadNumbers);
        }
        if (number == 7) {
            FunctionBlockOperator ro = new FunctionBlockOperator();
            ro.generateMutant(OriginalFile, threadNumbers);
        }
        if (number == 8) {
            LoopOperator lo = new LoopOperator();
            lo.generateMutant(OriginalFile, threadNumbers);
        }
        if (number == 9) {
            StatementDeletion sd = new StatementDeletion();
            sd.generateMutant(OriginalFile, threadNumbers);
        }
        //write code here to test

    }
}
